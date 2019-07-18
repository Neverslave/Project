package com.henry.exceldivide.Bean;
/**
 * excel文档基本信息bean
 *
 * */
public class ExcelBean {

  private   String path;
  private String filename;
  private   int  sheetsNumber;

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
