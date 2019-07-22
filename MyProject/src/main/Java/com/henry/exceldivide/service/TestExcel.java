package com.henry.exceldivide.service;

import com.henry.exceldivide.Utils.ZipUtils;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class TestExcel  {
    public static void main(String[] args) throws   Exception{
        File file = new File("C:\\Users\\Administrator\\Desktop\\天翼不出账.xlsx");
        InputStream ins  = new FileInputStream(file);
        Workbook wb = WorkbookFactory.create(ins);
        Sheet sheet=wb.getSheetAt(0);
        ExcelService excelService  = new ExcelService();
        List list =  excelService.getSheetTitle(sheet);
        for (int i = 0; i <list.size() ; i++) {
            System.out.println(list.get(i)+" "+ i);

        }

        String path ="C:\\Users\\Administrator\\Desktop\\1";
        String path1 ="C:\\Users\\Administrator\\Desktop\\123.zip";
        Scanner sc  = new Scanner(System.in);
        int num = sc.nextInt();
      HashMap rowByname = excelService.getRowByName(sheet,num);
        System.out.println(rowByname);
      excelService.createResult(rowByname,path,sheet,"123");
        OutputStream out  =new FileOutputStream(path1);
        ZipUtils.toZip(path,out,true);

    }
}
