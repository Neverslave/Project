package com.henry.exceldivide.service;

import org.apache.poi.ss.usermodel.*;

import java.util.ArrayList;
import java.util.List;

public class ExcelService {

/**
 * 获取活跃sheet 数量
 * */
    public  int getSheetNumber(Workbook workbook){
        int numbers = workbook.getNumberOfSheets();
        int sheetsNumbers  = 0;
        for (int i = 0; i <numbers; i++) {
            if(workbook.getSheetAt(i).getLastRowNum()!=0){
                sheetsNumbers ++ ;
            }
        }


        return  sheetsNumbers;
    }

    /**
     * 获取每页的标题
     *
     * */
    public List getSheetTitle(Sheet sheet){
        Row  row =sheet.getRow(0);
        List titles = new ArrayList();
        int numbers = row.getLastCellNum();
        System.out.println(numbers);
        for (int i = 0; i <numbers ; i++) {
            titles.add(row.getCell(i).getStringCellValue());
        }
        return  titles;
    }


}