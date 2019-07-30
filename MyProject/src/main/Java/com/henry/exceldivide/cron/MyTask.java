package com.henry.exceldivide.cron;

import com.henry.exceldivide.Utils.DeleteUtils;

import java.io.File;

public class MyTask implements  Runnable {
    @Override
    public void run() {
        String path = "src"+ File.separator+"main"+File.separator+"webapp"+File.separator+"upload";
        DeleteUtils.delAllFile(path);
    }
}
