package com.henry.exceldivide.service;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.poi.ss.usermodel.Row.MissingCellPolicy.CREATE_NULL_AS_BLANK;

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
    public HashMap<String,List<Integer>>  getRowByName( Sheet sheet, int index){
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

        for (Map.Entry<String, ArrayList<Integer>> entry : rowByName.entrySet()) {
            int num = 1;
            Workbook wb = new XSSFWorkbook();
            wb.createSheet();
            Sheet newSheet = wb.getSheetAt(0);
            Row title = newSheet.createRow(0);
            copyRow(wb,headrow, title);
            ArrayList list = entry.getValue();
            for (int i = 0; i < list.size(); i++) {
                copyRow(wb,sheet.getRow((int) list.get(i)), newSheet.createRow(num));
                num++;
            }
            String filePath = path +File.separator+ filename +"-"+ entry.getKey()+".xlsx";
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




    public static void copyRow(Workbook wb,Row fromRow,Row distRow){
        for (int i = 0; i <fromRow.getLastCellNum() ; i++) {
            Cell cell = fromRow.getCell(i);
            if(fromRow.getCell(i)==null){
                cell=fromRow.createCell(i);
            }
            copyCell(wb,cell,distRow.getCell(i,CREATE_NULL_AS_BLANK));
        }

    }

    public static void copyCell(Workbook wb,Cell fromCell,Cell distCell){
        CellStyle  newCellStyle =wb.createCellStyle();
        copyCellStyle(fromCell.getCellStyle(),newCellStyle);
        distCell.setCellStyle(newCellStyle);
       if(fromCell.getCellType()==CellType.BLANK){
           distCell.setBlank();
       }
       else if(fromCell.getCellType()==CellType.BOOLEAN){
           distCell.setCellValue(fromCell.getBooleanCellValue());
       }
       else if(fromCell.getCellType()==CellType.NUMERIC){
           if (DateUtil.isCellDateFormatted(fromCell)) {// 判断单元格是否属于日期格式
             distCell.setCellValue(fromCell.getDateCellValue());
           }
           else{
               distCell.setCellValue(fromCell.getNumericCellValue());
           }
       }
       else  if(fromCell.getCellType()==CellType.STRING){
           distCell.setCellValue(fromCell.getStringCellValue());
       }
       else if(fromCell.getCellType()==CellType.FORMULA){
           distCell.setCellValue(fromCell.getCellFormula());
       }


    }


    /**
     * 复制单元格格式
     * */
    public static void copyCellStyle(CellStyle fromStyle,
                                     CellStyle toStyle) {
        toStyle.setAlignment(fromStyle.getAlignment());
        //边框和边框颜色
        toStyle.setBorderBottom(fromStyle.getBorderBottom());
        toStyle.setBorderLeft(fromStyle.getBorderLeft());
        toStyle.setBorderRight(fromStyle.getBorderRight());
        toStyle.setBorderTop(fromStyle.getBorderTop());
        toStyle.setTopBorderColor(fromStyle.getTopBorderColor());
        toStyle.setBottomBorderColor(fromStyle.getBottomBorderColor());
        toStyle.setRightBorderColor(fromStyle.getRightBorderColor());
        toStyle.setLeftBorderColor(fromStyle.getLeftBorderColor());

        //背景和前景
        toStyle.setFillBackgroundColor(fromStyle.getFillBackgroundColor());
        toStyle.setFillForegroundColor(fromStyle.getFillForegroundColor());

        toStyle.setDataFormat(fromStyle.getDataFormat());
        toStyle.setFillPattern(fromStyle.getFillPattern());
//		toStyle.setFont(fromStyle.getFont(null));
        toStyle.setHidden(fromStyle.getHidden());
        toStyle.setIndention(fromStyle.getIndention());//首行缩进
        toStyle.setLocked(fromStyle.getLocked());
        toStyle.setRotation(fromStyle.getRotation());//旋转
        toStyle.setVerticalAlignment(fromStyle.getVerticalAlignment());
        toStyle.setWrapText(fromStyle.getWrapText());

    }







}