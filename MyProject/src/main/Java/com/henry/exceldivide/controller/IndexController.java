package com.henry.exceldivide.controller;

import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;


public class IndexController extends Controller {
    public void index(){
        render("/views/index.html");
    }

    public void upload(){
        UploadFile  uploadFile = getFile();
        System.out.println(uploadFile.getFileName());

    }
}
