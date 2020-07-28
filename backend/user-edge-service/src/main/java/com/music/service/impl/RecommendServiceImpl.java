package com.music.service.impl;

import com.music.domain.SparseMatrixElement;
import com.music.mapper.RecommendMapper;
import com.music.service.RecommendService;
import com.music.util.mapred.OldIdToNewIdMapReduce;
import com.music.util.mapred.RecommendMapReduce;
import com.music.util.path.RootPath;
import org.apache.hadoop.mapreduce.Job;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.List;

@Service
public class RecommendServiceImpl implements RecommendService {

    private static final String userIdName = "new_user_id.dat";
    private static final String musicIdName = "new_music_id.dat";
    private static final String userListName = "user_music_list.dat";
    private static final String musicListName = "music_tag_list.dat";

    @Resource
    RecommendMapper recommendMapper;

    String getDataPath() throws IOException {
        return RootPath.getRootPath() + File.separator + "input" + File.separator + "data";
    }

    @Override
    public boolean saveUserSparseMatrix() throws IOException {
//        DataOutputStream udos = null;
        FileWriter udos = null;
//        File ulist = new File(getDataPath(), userListName);
//        if (!ulist.exists()) {
//            ulist.createNewFile();
//        }
        try {
//            udos = new DataOutputStream(
//                    new BufferedOutputStream(new FileOutputStream(ulist)));
            udos = new FileWriter(new File(getDataPath(), userListName), false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        recommendMapper.set();
        List<SparseMatrixElement> userList = recommendMapper.getHistoryList();
        for (SparseMatrixElement u : userList) {
            udos.write(u.toString() + "\n");
        }
        udos.close();
        return true;
    }

    @Override
    public boolean saveMusicSparseMatrix() throws IOException {
//        DataOutputStream mdos = null;
        FileWriter mdos = null;
//        File mlist = new File(getDataPath(), musicListName);
//        if (!mlist.exists()) {
//            mlist.createNewFile();
//        }
        try {
//            mdos = new DataOutputStream(
//                    new BufferedOutputStream(new FileOutputStream(mlist)));
            mdos = new FileWriter(new File(getDataPath(), musicListName), false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        recommendMapper.set();
        List<SparseMatrixElement> musicList = recommendMapper.getMusicStyleList();
        for (SparseMatrixElement m : musicList) {
            mdos.write(m.toString() + "\n");
        }
        mdos.close();
        return true;
    }

    @Override
    public boolean saveNewUserId() throws IOException {
//        DataOutputStream udos = null;
        FileWriter udos = null;
//        File nid = new File(getDataPath(), userIdName);
//        if (!nid.exists()) {
//            nid.createNewFile();
//        }
        try {
//            udos = new DataOutputStream(
//                    new BufferedOutputStream(new FileOutputStream(nid)));
            udos = new FileWriter(new File(getDataPath(), userIdName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        List<Integer> newUserId = recommendMapper.getNewUserId();
        Integer i = 0;
        for (Integer uid : newUserId) {
            udos.write((i.toString() + "\t" + uid.toString() +"\n"));
            i += 1;
        }
        udos.close();
        return true;
    }

    @Override
    public boolean saveNewMusicId() throws IOException {
//        DataOutputStream mdos = null;
        FileWriter mdos = null;
//        File nid = new File(getDataPath(), musicIdName);
//        if (!nid.exists()) {
//            nid.createNewFile();
//        }
        try {
//            mdos = new DataOutputStream(
//                    new BufferedOutputStream(new FileOutputStream(nid)));
            mdos = new FileWriter(new File(getDataPath(), musicIdName), false);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
        List<Integer> newMusicId = recommendMapper.getNewMusicId();
        Integer i = 0;
        for (Integer mid : newMusicId) {
            mdos.write((i.toString() + "\t" + mid.toString() +"\n"));
            i += 1;
        }
        mdos.close();
        return true;
    }

    @Override
    public boolean recommendDataFormat() throws IOException, ClassNotFoundException, InterruptedException {
        // 将数据库的数据存储到本地
        this.saveNewUserId();
        this.saveNewMusicId();
        this.saveUserSparseMatrix();
        this.saveMusicSparseMatrix();
        System.out.println("save data finished!");

        Job job = OldIdToNewIdMapReduce.getMusicIdFormatJob(new String[] {
                RootPath.getRootPath() + "/input/data",
                RootPath.getRootPath() + "/output/data"
        });
        boolean complete = job.waitForCompletion(true);
        boolean successful = job.isSuccessful();
        if (!complete || !successful) {
            System.err.println("MapReduce失败！");
            return false;
        }
        System.out.println("Music ID format finished!");

        job = OldIdToNewIdMapReduce.getUserIdFormatJob(new String[] {
                RootPath.getRootPath() + "/input/data",
                RootPath.getRootPath() + "/output/data",
                RootPath.getRootPath() + "/output/formatted"
        });
        complete = job.waitForCompletion(true);
        successful = job.isSuccessful();
        if (!complete || !successful) {
            System.err.println("MapReduce失败！");
            return false;
        }
        System.out.println("User ID format finished!");

        job = RecommendMapReduce.getJob(new String[] {
                RootPath.getRootPath() + "/output/formatted",
                RootPath.getRootPath() + "/output/temp"
        });
        complete = job.waitForCompletion(true);
        successful = job.isSuccessful();
        if (!complete || !successful) {
            System.err.println("MapReduce失败！");
            return false;
        }
        System.out.println("评分 ok！");
        System.out.println("MapReduce 成功！");
        return false;
    }
}
