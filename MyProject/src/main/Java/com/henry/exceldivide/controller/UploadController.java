package com.henry.exceldivide.controller;

import com.henry.exceldivide.Bean.ExcelBean;
import com.henry.exceldivide.Utils.ZipUtils;
import com.henry.exceldivide.service.ExcelService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.upload.UploadFile;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UploadController  extends Controller {
    @Inject
    ExcelService excelService ;
    @Inject
    ExcelBean excelBean;
    /**
     * 上传文件
     * 返回获取到的标题
     * */
    public void index() throws  Exception{
        InputStream ins = null;
        Workbook wb = null;
        UploadFile uploadFile = getFile();
        String path =uploadFile.getUploadPath();
        String filename =uploadFile.getFileName();
        String realpath = path+File.separator+filename;
        ins = new FileInputStream(new File(realpath));
        wb = WorkbookFactory.create(ins);
        int sheetName =excelService.getSheetNumber(wb);
        Map<String, List> sheetNameTitleList  = new HashMap<>();
        for (int i = 0; i <sheetName ; i++) {
            sheetNameTitleList.put(wb.getSheetName(i),excelService.getSheetTitle(wb.getSheetAt(i)));
        }
        excelBean.setWorkbook(wb);
        excelBean.setFilename(filename);
        excelBean.setPath(realpath);
        excelBean.setRealPath(path);
        excelBean.setSheetsNumber(excelService.getSheetNumber(wb));
        excelBean.setSheetTitles(sheetNameTitleList);
        //渲染
        renderJson(sheetNameTitleList);
        ins.close();
    }

    /**获取标题数量*/

    public void getTitle() throws Exception{
        InputStream ins = null;
        Workbook wb = excelBean.getWorkbook();
        String path = excelBean.getPath().replace(".xlsx","");
        String title = getPara("title");
        String option = getPara("option");
        String sheetName =getPara("sheetName");
        String uploadPath = excelBean.getRealPath();
        Sheet sheet=wb.getSheet(sheetName);
         int i  =(int)(Math.random()*100);
         //结果文件夹位置
        String folder = path+i;
       //获取标题列表
        List list = excelService.getSheetTitle(sheet);
        int num = list.indexOf(option);
        HashMap rowByname = excelService.getRowByName(sheet,num);
        excelService.createResult(rowByname,folder,sheet,title);
        String zipPath = uploadPath+File.separator+title+".zip";
        OutputStream out  =new FileOutputStream(zipPath);
        ZipUtils.toZip(folder,out,true);
        String ZipFile = title+".zip";
        excelBean.setZipPath(ZipFile);
        renderJson("res","压缩成功");
    }


    public void  getZip(){
        System.out.println(excelBean.getZipPath());
        renderFile(excelBean.getZipPath());
    }
}
