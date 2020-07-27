package com.music.service;

import java.io.IOException;

public interface RecommendService {
    boolean recommend() throws IOException, ClassNotFoundException, InterruptedException;
}
