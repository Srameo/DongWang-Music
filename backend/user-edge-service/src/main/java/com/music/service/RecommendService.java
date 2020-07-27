package com.music.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface RecommendService {
    boolean saveUserSparseMatrix() throws IOException;
    boolean saveMusicSparseMatrix() throws IOException;
    boolean saveNewUserId() throws IOException;
    boolean saveNewMusicId() throws IOException;
    boolean recommend() throws IOException, ClassNotFoundException, InterruptedException;
}
