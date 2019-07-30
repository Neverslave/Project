package com.henry.exceldivide.Bean;

import org.apache.poi.ss.usermodel.Workbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * excel文档基本信息bean
 *
 * */
public class ExcelBean {
    private  String zipPath;

    public String getZipPath() {
        return zipPath;
    }

    public void setZipPath(String zipPath) {
        this.zipPath = zipPath;
    }

    //上传路径
  private   String path;
//真实路径
    public String getRealPath() {
        return realPath;
    }

    public void setRealPath(String realPath) {
        this.realPath = realPath;
    }

    private String realPath;
  //文件名
  private String filename;
  //拥有的sheet数量
  private   int  sheetsNumber;
  // workbook对象
  private Workbook workbook;
  private Map<String, List> SheetTitles;
    //存放sheet 和相对的标题list
    public Map<String, List> getSheetTitles() {
        return SheetTitles;
    }

    public void setSheetTitles(Map<String, List> sheetTitles) {
        SheetTitles = sheetTitles;
    }

    public Workbook getWorkbook() {
        return workbook;
    }

    public void setWorkbook(Workbook workbook) {
        this.workbook = workbook;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public int getSheetsNumber() {
        return sheetsNumber;
    }

    public void setSheetsNumber(int sheetsNumber) {
        this.sheetsNumber = sheetsNumber;
    }
}
