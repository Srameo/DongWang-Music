package com.music.util.path;

import java.io.File;
import java.io.IOException;

public class RootPath {

    private static final String projectName = "backend";

    public static String getRootPath() throws IOException {
        File directory = new File("");// 参数为空
        String nowPath = directory.getCanonicalPath();
        int index = nowPath.indexOf(projectName);
        String rootPath = nowPath;
        if (index != -1) {
            System.out.println(nowPath);
            rootPath = nowPath.substring(0, index + projectName.length());
        }
        return rootPath;
    }

    public static void main(String[] args) throws IOException {
        System.out.println(getRootPath());
    }
}
