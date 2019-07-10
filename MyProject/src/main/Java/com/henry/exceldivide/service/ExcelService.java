package com.henry.exceldivide.service;

import org.apache.poi.ss.usermodel.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ExcelService {
    public static void main(String[] args) throws Exception {
        InputStream ins = null;
        Workbook wb = null;

        ins = new FileInputStream(new File("C:\\Users\\zhuwanqi\\Desktop\\天翼不出账.xlsx"));
        wb = WorkbookFactory.create(ins);
        ins.close();
        //3.得到Excel工作表对象
        Sheet sheet = wb.getSheetAt(0);//总行数
        int trLength = sheet.getLastRowNum();
        //4.得到Excel工作表的行
        Row row = sheet.getRow(0);
        //总列数
        int tdLength = row.getLastCellNum();
        //5.得到Excel工作表指定行的单元格
        Cell cell = row.getCell((short) 1);
        for (int i = 5; i < trLength; i++) {
            //得到Excel工作表的行
            Row row1 = sheet.getRow(i);
            for (int j = 0; j < tdLength; j++) {
                //得到Excel工作表指定行的单元格
                Cell cell1 = row1.getCell(j);

            }
        }
    }
}