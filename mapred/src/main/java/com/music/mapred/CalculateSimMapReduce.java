package com.music.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.DoubleWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.Reducer;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;

public class CalculateSimMapReduce {

    static {
        // 设置宿主机系统环境变量 HADOOP_USER_NAME = icss
        System.setProperty("HADOOP_USER_NAME", "icss");

        String osInfo = System.getProperty("os.name");
        if (osInfo.toLowerCase().contains("windows")) {
            System.setProperty("hadoop.home.dir", "HADOOP_HOME");
            System.setProperty("hadoop.tmp.dir", "HADOOP_TEMP_DIR");
        } else {
            System.setProperty("hadoop.tmp.dir", "/Users/jinxin/Desktop/temp/hadooptemp");
        }
    }

    public static class CalculateSimMapper
        extends Mapper<LongWritable, Text, Text, Text> {

        private static long row = 0;
        private static long column = 0;
        private static final Text k = new Text();
        private static final Text v = new Text();

        public static void setRow(long row) {
            CalculateSimMapper.row = row;
        }

        public static void setColumn(long column) {
            CalculateSimMapper.column = column;
        }

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String[] records = value.toString().split("\t");
            long r = Integer.parseInt(records[0]);
            long c = Integer.parseInt(records[1]);
            int num = Integer.parseInt(records[2]);

            v.set("1" + ":" + c + "," + num);
            for (int j = 0; j < row; j++) {
                k.set(r + "\t" + (j + 1));
                context.write(k, v);
            }
            // 将数据转置之后存入
            v.set("2:" + c + "," + num);
            for (int j = 0; j < column; j++) {
                k.set((j + 1) + "\t" + r);
                context.write(k, v);
            }
            System.err.println("Mapper " + r + " " + c);
        }
    }

    public static class CalculateSimReducer
        extends Reducer<Text, Text, Text, DoubleWritable> {
        private static final DoubleWritable v = new DoubleWritable();
        private static long column = 0;

        public static void setColumn(long column) {
            CalculateSimReducer.column = column;
        }

        @Override
        protected void reduce(Text key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            int[] a = new int[(int) column];
            int[] b = new int[(int) column];
            for (Text value : values) {
                String[] vs = value.toString().split(":");
                String[] ints = vs[1].split(",");
                if (vs[0].equals("1")) {
                    a[Integer.parseInt(ints[0]) - 1] = Integer
                            .parseInt(ints[1]);
                } else {
                    b[Integer.parseInt(ints[0]) - 1] = Integer
                            .parseInt(ints[1]);
                }
            }
            // 用矩阵A的行乘矩阵B的列
            int sum = 0;
            int sumA = 0;
            int sumB = 0;
            for (int i = 0; i < column; i++) {
                sum += a[i] * b[i];
                sumA += a[i] * a[i];
                sumB += b[i] * b[i];
            }
            v.set(sum / (Math.sqrt(sumA) * Math.sqrt(sumB)));
            System.err.println("Reducer " + key.toString());
            context.write(key, v);
        }
    }

    private static void error() {
        System.err.println("Args is Error, Usage CalculateSim <row> <column> <inpath> [<inpath> ...] <outpath>");
        System.exit(2);
    }

    public static Job getJob(String[] args) throws IOException {
        // 0. 初始化 mr 的状态
        Configuration conf = new Configuration();
        org.apache.hadoop.mapreduce.Job job = org.apache.hadoop.mapreduce.Job.getInstance(conf, "My Calculate Sim ver1.0");    // 生成一个 mr 的job
        job.setJarByClass(CalculateSimMapReduce.class);        // 指明 MR Job 对应执行的类 （Controller Class）

        // 1. 指明输入
        // 获取Hadoop环境中真实的命令行参数
        String[] hadoopArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (hadoopArgs.length < 4) {
            error();
        }
        // 设置矩阵行列
        int column, row;
        try {
            column = Integer.parseInt(hadoopArgs[0]);
            row = Integer.parseInt(hadoopArgs[1]);
            CalculateSimMapper.setColumn(column);
            CalculateSimMapper.setRow(row);
            CalculateSimReducer.setColumn(column);
        } catch (Exception e) {
            error();
        }
        for (int i = 2; i < hadoopArgs.length - 1; i++) {
            Path path = new Path(hadoopArgs[i]);
            FileInputFormat.addInputPath(job, path);
        }
        // 1-1. 指明输入类型
        job.setInputFormatClass(TextInputFormat.class); // 指明输入的是文本文件

        // 2. 指明 Mapper 类
        // 2-0. Mapper 类
        job.setMapperClass(CalculateSimMapper.class);
        // 2-1. Mapper 执行后，输出（MapMid）类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(Text.class);

        // 3\4\5 指定 分区、排序、规约 === 都使用默认

        // 6. 指明 Reducer
        // 6-0. reducer的个数
        job.setNumReduceTasks(1);
        // 6-1. reducer的类
        job.setReducerClass(CalculateSimReducer.class);
        // 6-2. reducer输出的key和value
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(DoubleWritable.class);

        // 7. 指定输出
        // 7-0. 输出的位置
        Path outPath = new Path(hadoopArgs[hadoopArgs.length - 1]);
        FileOutputFormat.setOutputPath(job, outPath);
        // 7-1. 输出的类型，文本文件可以省略
        job.setOutputFormatClass(TextOutputFormat.class);

        return job;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job = getJob(args);

        // 8. 提交 MR job
        boolean resultCompletion = job.waitForCompletion(true);
        boolean resultSuccessful = job.isSuccessful();
        System.exit(resultCompletion && resultSuccessful ? 0 : 1);
    }
}
