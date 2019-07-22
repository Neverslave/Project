package com.henry.exceldivide.controller;

import com.henry.exceldivide.Bean.ExcelBean;
import com.henry.exceldivide.service.ExcelService;
import com.jfinal.aop.Inject;
import com.jfinal.core.Controller;
import com.jfinal.kit.Ret;
import com.jfinal.upload.UploadFile;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;

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
        String realpath = path+'\\'+filename;
        ins = new FileInputStream(new File(realpath));
        wb = WorkbookFactory.create(ins);
        Sheet sheet=wb.getSheetAt(0);
        List list =excelService.getSheetTitle(sheet);
        excelBean.setFilename(filename);
        excelBean.setPath(realpath);
        excelBean.setSheetsNumber(excelService.getSheetNumber(wb));
        ins.close();
        renderJson(list);
    }

    public void getTitle() throws Exception{
        InputStream ins = null;
        Workbook wb = null;
        String title = getPara("title");
        ins = new FileInputStream(new File(excelBean.getPath()));
        wb = WorkbookFactory.create(ins);
        Sheet sheet =wb.getSheetAt(0);
        int rowNumber = sheet.getLastRowNum();




    }

}
