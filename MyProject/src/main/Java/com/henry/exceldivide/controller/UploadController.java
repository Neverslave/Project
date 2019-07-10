package com.henry.exceldivide.controller;

import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.upload.UploadFile;

public class UploadController  extends Controller {

    public void index(){
        UploadFile uploadFile = getFile();
        System.out.println(uploadFile.getFileName());
        System.out.println(uploadFile.getUploadPath());
        Ret ret = new Ret().set("msg","上传成功");

        renderJson(ret);
    }
}
