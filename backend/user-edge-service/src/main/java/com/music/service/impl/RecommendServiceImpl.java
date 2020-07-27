package com.music.service.impl;

import com.music.domain.SparseMatrixElement;
import com.music.mapper.RecommendMapper;
import com.music.service.RecommendService;
import com.music.util.mapred.RecommendMapReduce;
import com.music.util.path.RootPath;
import org.apache.hadoop.mapreduce.Job;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    private static final String userIdName = "new_user_id";
    private static final String musicIdName = "new_music_id";
    private static final String userListName = "user_music_list";
    private static final String musicListName = "music_tag_list";

    @Resource
    RecommendMapper recommendMapper;

    String getDataPath() throws IOException {
        return RootPath.getRootPath() + File.separator + "input" + File.separator + "data";
    }

    @Override
    public boolean saveUserSparseMatrix() throws IOException {
        DataOutputStream udos = null;
        File ulist = new File(getDataPath(), userListName);
        if (!ulist.exists()) {
            ulist.createNewFile();
        }
        try {
            udos = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(ulist)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        recommendMapper.set();
        List<SparseMatrixElement> userList = recommendMapper.getHistoryList();
        for (SparseMatrixElement u : userList) {
            udos.writeChars(u.toString() + "\n");
        }
        udos.close();
        return true;
    }

    @Override
    public boolean saveMusicSparseMatrix() throws IOException {
        DataOutputStream mdos = null;
        File mlist = new File(getDataPath(), musicListName);
        if (!mlist.exists()) {
            mlist.createNewFile();
        }
        try {
            mdos = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(mlist)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        recommendMapper.set();
        List<SparseMatrixElement> musicList = recommendMapper.getMusicStyleList();
        for (SparseMatrixElement m : musicList) {
            mdos.writeChars(m.toString() + "\n");
        }
        mdos.close();
        return true;
    }

    @Override
    public boolean saveNewUserId() throws IOException {
        DataOutputStream udos = null;
        File nid = new File(getDataPath(), userIdName);
        if (!nid.exists()) {
            nid.createNewFile();
        }
        try {
            udos = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(nid)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        List<Integer> newUserId = recommendMapper.getNewUserId();
        int i = 0;
        for (Integer uid : newUserId) {
            udos.writeChars(i + "\t" + uid +"\n");
            i += 1;
        }
        udos.close();
        return true;
    }

    @Override
    public boolean saveNewMusicId() throws IOException {
        DataOutputStream mdos = null;
        File nid = new File(getDataPath(), musicIdName);
        if (!nid.exists()) {
            nid.createNewFile();
        }
        try {
            mdos = new DataOutputStream(
                    new BufferedOutputStream(new FileOutputStream(nid)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        List<Integer> newMusicId = recommendMapper.getNewMusicId();
        int i = 0;
        for (Integer mid : newMusicId) {
            mdos.writeChars(i + "\t" + mid +"\n");
            i += 1;
        }
        mdos.close();
        return true;
    }

    @Override
    public boolean recommend() throws IOException, ClassNotFoundException, InterruptedException {
        String[] args = new String[] {
                "input/"
        };
        Job job = RecommendMapReduce.getJob(args);
        boolean complete = job.waitForCompletion(true);
        boolean successful = job.isSuccessful();
        if (complete && successful) {
            System.out.println("MapReduce成功！");
            return true;
        } else {
            System.err.println("MapReduce失败！");
            return false;
        }
    }
}
