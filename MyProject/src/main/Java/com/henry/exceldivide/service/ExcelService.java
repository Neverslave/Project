package com.henry.exceldivide.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        for (int i = 0; i <numbers ; i++) {
            titles.add(row.getCell(i).getStringCellValue());
        }
        return  titles;
    }
/***
 * 获取每个拆分内容对应的行号
 * */
    public HashMap<String,List<Integer>> getRowByName(@org.jetbrains.annotations.NotNull Sheet sheet, int index){
        HashMap<String,List<Integer> >rowByName = new HashMap<>();
        int numbers = sheet.getLastRowNum();
        for (int i = 1; i <numbers ; i++) {
            String  rowValue =sheet.getRow(i).getCell(index).getStringCellValue(); //每一行的值
            if(rowByName.containsKey(rowValue)){
                rowByName.get(rowValue).add(i);
            }
            else{
                List<Integer> list = new ArrayList<>();
                list.add(i);
                rowByName.put(sheet.getRow(i).getCell(index).getStringCellValue(),list );
            }
        }


        return rowByName;
    }

    public Boolean createResult(HashMap<String,ArrayList<Integer>> rowByName,String path,Sheet sheet,String filename)
    throws  Exception {
        File file = new File(path);
        if (!file.exists()) {//如果文件夹不存在
            file.mkdir();//创建文件夹
        }
        Row headrow = sheet.getRow(0);
        int num = 1;
        for (Map.Entry<String, ArrayList<Integer>> entry : rowByName.entrySet()) {
            Workbook wb = new XSSFWorkbook();
            wb.createSheet();
            Sheet newSheet = wb.getSheetAt(0);
            Row title = newSheet.createRow(0);
            copyRow(headrow, title);
            ArrayList list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                copyRow(sheet.getRow((int) list.get(i)), newSheet.createRow(num));
                num++;
            }
            String filePath = path + filename + entry.getKey();
            try (OutputStream outputStream = new FileOutputStream(filePath)) {
                wb.write(outputStream);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                return false;
            } catch (IOException e) {
                e.printStackTrace();
                return false;


            }

        }
        return true;
    }




    public static void copyRow(Row fromRow,Row distRow){
        for (int i = 0; i <fromRow.getLastCellNum() ; i++) {
            copyCell(fromRow.getCell(i),distRow.getCell(i));
        }

    }

    public static void copyCell(Cell fromCell,Cell distCell){
        distCell.setCellValue(fromCell.getStringCellValue());

    }






}