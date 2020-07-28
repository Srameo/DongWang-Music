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
import org.apache.hadoop.mapreduce.lib.input.FileSplit;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class OldIdToNewIdMapReduce {

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

    public static class MusicIdFormatMapper
            extends Mapper<LongWritable, Text, IntWritable, Text> {

        enum FLAG {
            USER_ID,
            MUSIC_ID,
            USER_LIST,
            MUSIC_LIST,
            NONE
        }

        private FLAG flag = FLAG.NONE;
        private static final IntWritable k = new IntWritable();
        private static final Text v = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            line = line.replaceAll("[\u0000]", "");
            String[] args = line.split("\t");
            switch (flag) {
                case MUSIC_ID:
                    k.set(Integer.parseInt(args[1]));
                    v.set("i:" + args[0]);
                    break;
                case USER_LIST:
                    k.set(Integer.parseInt(args[1]));
                    v.set("v:" + args[0] + "\t" + args[2]);
                    break;
                default:
                    return;
            }
            context.write(k, v);
        }

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            String dataSourceFilename = inputSplit.getPath().getName();
            if (dataSourceFilename.contains("user_id")) {
                flag = FLAG.USER_ID;
            } else if (dataSourceFilename.contains("music_id")) {
                flag = FLAG.MUSIC_ID;
            } else if (dataSourceFilename.contains("user_music")) {
                flag = FLAG.USER_LIST;
            } else if (dataSourceFilename.contains("music_tag")) {
                flag = FLAG.MUSIC_LIST;
            }
        }
    }

    public static class MusicIdFormatReducer
            extends Reducer<IntWritable, Text, IntWritable, Text> {

        private static final Text v = new Text();
        private static final IntWritable k = new IntWritable(0);

        @Override
        protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            List<String> vlist = new ArrayList<>();
            for (Text value : values) {
                String[] vl = value.toString().split(":");
                if (vl[0].equals("i")) {
                    k.set(Integer.parseInt(vl[1]));
                } else {
                    vlist.add(vl[1]);
                }
            }
            for (String a : vlist) {
                v.set(a);
                context.write(k, v);
            }
        }
    }

    public static class UserIdFormatMapper
        extends Mapper<LongWritable, Text, IntWritable, Text> {

        enum FLAG {
            USER_ID,
            MUSIC_ID,
            USER_LIST,
            MUSIC_LIST,
            NONE
        }

        private FLAG flag = FLAG.NONE;
        private static final IntWritable k = new IntWritable();
        private static final Text v = new Text();

        @Override
        protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
            String line = value.toString();
            line = line.replaceAll("[\u0000]", "");
            String[] args = line.split("\t");
            switch (flag) {
                case USER_ID:
                    k.set(Integer.parseInt(args[1]));
                    v.set("i:" + args[0]);
                    break;
                case USER_LIST:
                    k.set(Integer.parseInt(args[1]));
                    v.set("v:" + args[0] + "\t" + args[2]);
                    break;
                default:
                    return;
            }
            context.write(k, v);
        }

        @Override
        protected void setup(Context context) throws IOException, InterruptedException {
            FileSplit inputSplit = (FileSplit) context.getInputSplit();
            String dataSourceFilename = inputSplit.getPath().getName();
            if (dataSourceFilename.contains("user_id")) {
                flag = FLAG.USER_ID;
            } else if (dataSourceFilename.contains("music_id")) {
                flag = FLAG.MUSIC_ID;
            } else if (dataSourceFilename.contains("user_music")) {
                flag = FLAG.NONE;
            } else if (dataSourceFilename.contains("music_tag")) {
                flag = FLAG.MUSIC_LIST;
            } else if (dataSourceFilename.startsWith("part-r")) {
                flag = FLAG.USER_LIST;
            }
        }
    }

    public static class UserIdFormatReducer
        extends Reducer<IntWritable, Text, IntWritable, Text> {

        private static final Text v = new Text();
        private static final IntWritable k = new IntWritable(0);

        @Override
        protected void reduce(IntWritable key, Iterable<Text> values, Context context) throws IOException, InterruptedException {
            List<String> vlist = new ArrayList<>();
            for (Text value : values) {
                String[] vl = value.toString().split(":");
                if (vl[0].equals("i")) {
                    k.set(Integer.parseInt(vl[1]));
                } else {
                    vlist.add(vl[1]);
                }
            }
            for (String a : vlist) {
                v.set(a);
                context.write(k, v);
            }
        }
    }

    public static Job getUserIdFormatJob(String[] args) throws IOException {
        // 0. 初始化 mr 的状态
        Configuration conf = new Configuration();
        org.apache.hadoop.mapreduce.Job job = org.apache.hadoop.mapreduce.Job.getInstance(conf, "My User ID Format ver1.0");    // 生成一个 mr 的job
        job.setJarByClass(OldIdToNewIdMapReduce.class);        // 指明 MR Job 对应执行的类 （Controller Class）

        // 1. 指明输入
        // 获取Hadoop环境中真实的命令行参数
        String[] hadoopArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (hadoopArgs.length < 2) {
            System.err.println("Args is Error, Usage DataFormat <inpath> [<inpath> ...] <outpath>");
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
        job.setMapperClass(OldIdToNewIdMapReduce.UserIdFormatMapper.class);
        // 2-1. Mapper 执行后，输出（MapMid）类型
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        // 3\4\5 指定 分区、排序、规约 === 都使用默认

        // 6. 指明 Reducer
        // 6-0. reducer的个数
        job.setNumReduceTasks(1);
        // 6-1. reducer的类
        job.setReducerClass(OldIdToNewIdMapReduce.UserIdFormatReducer.class);
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

    public static Job getMusicIdFormatJob(String[] args) throws IOException {
        // 0. 初始化 mr 的状态
        Configuration conf = new Configuration();
        org.apache.hadoop.mapreduce.Job job = org.apache.hadoop.mapreduce.Job.getInstance(conf, "My User ID Format ver1.0");    // 生成一个 mr 的job
        job.setJarByClass(OldIdToNewIdMapReduce.class);        // 指明 MR Job 对应执行的类 （Controller Class）

        // 1. 指明输入
        // 获取Hadoop环境中真实的命令行参数
        String[] hadoopArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
        if (hadoopArgs.length < 2) {
            System.err.println("Args is Error, Usage DataFormat <inpath> [<inpath> ...] <outpath>");
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
        job.setMapperClass(OldIdToNewIdMapReduce.MusicIdFormatMapper.class);
        // 2-1. Mapper 执行后，输出（MapMid）类型
        job.setMapOutputKeyClass(IntWritable.class);
        job.setMapOutputValueClass(Text.class);

        // 3\4\5 指定 分区、排序、规约 === 都使用默认

        // 6. 指明 Reducer
        // 6-0. reducer的个数
        job.setNumReduceTasks(1);
        // 6-1. reducer的类
        job.setReducerClass(OldIdToNewIdMapReduce.MusicIdFormatReducer.class);
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

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
        Job job1 = getMusicIdFormatJob(new String[] {
                "input/data",
                "output/data"
        });
        Job job2 = getUserIdFormatJob(new String[] {
                "input/data",
                "output/data",
                "output/temp"
        });

        // 8. 提交 MR job
        boolean resultCompletion1 = job1.waitForCompletion(true);
        boolean resultSuccessful1 = job1.isSuccessful();
        boolean resultCompletion2 = job2.waitForCompletion(true);
        boolean resultSuccessful2 = job2.isSuccessful();
        System.exit(resultCompletion1 && resultSuccessful1 && resultCompletion2 && resultSuccessful2 ? 0 : 1);
    }

}
