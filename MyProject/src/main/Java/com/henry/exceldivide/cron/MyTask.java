package com.henry.exceldivide.cron;

import com.henry.exceldivide.Utils.DeleteUtils;

public class MyTask implements  Runnable {
    @Override
    public void run() {
        String path = "K:\\project\\Project\\MyProject\\src\\main\\webapp\\upload";

        DeleteUtils.delAllFile(path);
        System.out.println("定时任务执行");
    }
}
