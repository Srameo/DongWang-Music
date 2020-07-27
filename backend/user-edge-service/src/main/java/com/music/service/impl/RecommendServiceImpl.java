package com.music.service.impl;

import com.music.service.RecommendService;
import com.music.util.mapred.RecommendMapReduce;
import org.apache.hadoop.mapreduce.Job;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RecommendServiceImpl implements RecommendService {
    @Override
    public boolean recommend() throws IOException, ClassNotFoundException, InterruptedException {
        String[] args = new String[] {
                "input/"
        };
        Job job = RecommendMapReduce.getJob(args);
        boolean complete = job.waitForCompletion(true);
        boolean successful = job.isSuccessful();
        if (complete && successful) {
            System.err.println("MapReduce成功！");
            return true;
        } else {
            System.err.println("MapReduce失败！");
            return false;
        }
    }
}
