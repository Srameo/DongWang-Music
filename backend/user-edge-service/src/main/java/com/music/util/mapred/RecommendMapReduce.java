package com.music.util.mapred;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
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
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class MapReduceArgsException extends IOException {
    public MapReduceArgsException(String message) {
        super(message);
    }

    public MapReduceArgsException() {
        super("MapReduce 数据不全！");
    }
}

public class RecommendMapReduce {

    static {
        // 设置宿主机系统环境变量 HADOOP_USER_NAME = icss
        System.setProperty("HADOOP_USER_NAME", "icss");

        String osInfo = System.getProperty("os.name");
        if (osInfo.toLowerCase().contains("windows")) {
            System.setProperty("hadoop.home.dir", "HADOOP_HOME");
            System.setProperty("hadoop.tmp.dir", "HADOOP_TEMP_DIR");
        } else if (osInfo.toLowerCase().contains("mac")) {
            System.setProperty("hadoop.tmp.dir", "/Users/jinxin/Desktop/temp/hadooptemp");
        }
    }

    public static int sigmoid(double d) {
        return (int) Math.ceil(10 / (1 + Math.pow(Math.E, -d)));
    }

    public static class RecommendDataFormatMapper
            extends Mapper<LongWritable, Text, IntWritable, Text> {

        private final IntWritable row = new IntWritable();
        private final Text text = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            String[] args = line.split("\t");
            row.set(Integer.parseInt(args[0]));
            text.set(args[1] + " " + args[2]);
            context.write(row, text);
        }
    }


    public static class RecommendDataFormatReducer
            extends Reducer<IntWritable, Text, IntWritable, Text> {
        @Override
        protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            double sum = 0;int count = 0;
            List<String[]> vls = new ArrayList<>();
            for (Text value : values) {
                count++;
                String[] columnAndRating = value.toString().split(" ");
                vls.add(columnAndRating);
                sum += Integer.parseInt(columnAndRating[1]);
            }
            double mean = sum / count;
            double S = 0;
            Iterator<String[]> stringIter = vls.iterator();
            while(stringIter.hasNext()) {
                int v = Integer.parseInt(stringIter.next()[1]);
                S += (v - mean) * (v - mean);
            }
            double std = Math.sqrt(S / count);
            stringIter = vls.iterator();
            while(stringIter.hasNext()) {
                String[] temp = stringIter.next();
                int v = Integer.parseInt(temp[1]);
                double rating = (v - mean) / std;
                context.write(key, new Text(temp[0] + "\t" + sigmoid(rating)));
            }
        }
    }

    public static Job getJob(String[] args) throws IOException, MapReduceArgsException {
        // 0. 初始化 mr 的状态
        Configuration conf = new Configuration();
        Job job = Job.getInstance(conf, "My Data Format ver1.0");    // 生成一个 mr 的job
        job.setJarByClass(RecommendMapReduce.class);        // 指明 MR Job 对应执行的类 （Controller Class）

        // 1. 指明输入
        // 获取Hadoop环境中真实的命令行参数
        String[] hadoopArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (hadoopArgs.length < 2) {
            System.err.println("Args is Error, Usage LoadData <inpath> [<inpath> ...] <outpath>");
            throw new MapReduceArgsException();
        }
        for (int i = 0; i < hadoopArgs.length - 1; i++) {
            Path path = new Path(hadoopArgs[i]);
            FileInputFormat.addInputPath(job, path);
        }
        // 1-1. 指明输入类型
        job.setInputFormatClass(TextInputFormat.class); // 指明输入的是文本文件

        // 2. 指明 Mapper 类
        // 2-0. Mapper 类
        job.setMapperClass(RecommendDataFormatMapper.class);
        // 2-1. Mapper 执行后，输出（MapMid）类型
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        // 3\4\5 指定 分区、排序、规约 === 都使用默认

        // 6. 指明 Reducer
        // 6-0. reducer的个数
        job.setNumReduceTasks(1);
        // 6-1. reducer的类
        job.setReducerClass(RecommendDataFormatReducer.class);
        // 6-2. reducer输出的key和value
        job.setOutputKeyClass(IntWritable.class);
        job.setOutputValueClass(Text.class);

        // 7. 指定输出
        // 7-0. 输出的位置
        Path outPath = new Path(hadoopArgs[hadoopArgs.length - 1]);
        FileOutputFormat.setOutputPath(job, outPath);
        // 7-1. 输出的类型，文本文件可以省略
        job.setOutputFormatClass(TextOutputFormat.class);

        return job;
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException, MapReduceArgsException {
        Job job = getJob(args);

        // 8. 提交 MR job
        boolean resultCompletion = job.waitForCompletion(true);
        boolean resultSuccessful = job.isSuccessful();
        System.exit(resultCompletion && resultSuccessful ? 0 : 1);
    }

}
