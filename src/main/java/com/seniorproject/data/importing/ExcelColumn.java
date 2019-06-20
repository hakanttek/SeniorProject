/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.seniorproject.data.importing;

import java.util.ArrayList;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author hakantek
 */
public class ExcelColumn extends ArrayList<Double>{
    public ExcelColumn(String fileLocation, String workbookName, String sheetName, int colIndexNumber, int initialRow, boolean header) {
        super();
        
        fileLocation = fileLocation + "\\" + workbookName + ".xlsx";
        
        /* connection with excel workbook */
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(fileLocation));
        } catch (IOException ex) {
            Logger.getLogger(ExcelColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /* connection with sheet of workbook */
        XSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        
        /* if there is a header increase row by 1 */
        initialRow += (header)?1:0;
        
        /* return data */
        while(true){
            try {
                this.add(sheet.getRow(++initialRow-1).getCell(colIndexNumber-1).getNumericCellValue());
            } 
            catch (Exception e) {
                break;
            }
        }
    }
    public ExcelColumn(String fileLocation, String workbookName, String sheetName, int colIndexNumber, int initialRow){
        super();
        
        fileLocation = fileLocation + "\\" + workbookName + ".xlsx";
        
        /* connection with excel workbook */
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(fileLocation));
        } catch (IOException ex) {
            Logger.getLogger(ExcelColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /* connection with sheet of workbook */
        XSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        
        
        /* return data */
        while(true){
            try {
                this.add(sheet.getRow(++initialRow-1).getCell(colIndexNumber-1).getNumericCellValue());
            } 
            catch (Exception e) {
                break;
            }
        }
    }
    public ExcelColumn(String fileLocation, String workbookName, String sheetName, int colIndexNumber){
        super();
        int initialRow = 1;
        fileLocation = fileLocation + "\\" + workbookName + ".xlsx";
        
        /* connection with excel workbook */
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(fileLocation));
        } catch (IOException ex) {
            Logger.getLogger(ExcelColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /* connection with sheet of workbook */
        XSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        
        
        /* return data */
        while(true){
            try {
                this.add(sheet.getRow(++initialRow-1).getCell(colIndexNumber-1).getNumericCellValue());
            } 
            catch (Exception e) {
                break;
            }
        }
    }
    public ExcelColumn(String fileLocation, String workbookName, String sheetName, String header, int initialRow) {
        
        fileLocation = fileLocation + "\\" + workbookName + ".xlsx";

        int colIndexNumber = -1;
        
        /* connection with excel workbook */
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(fileLocation));
        } catch (IOException ex) {
            Logger.getLogger(ExcelColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /* connection with sheet of workbook */
        XSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        
        /* determinate colIndexNumber */
        for(int i=1; i<=100; i++){
            try {
                if(header.equals(sheet.getRow(initialRow-1).getCell(i-1).getStringCellValue())){
                    colIndexNumber = i;
                    break;
                }
            } catch (Exception e) {}
        }

        /* return data */
        while(true){
            try {
                this.add(sheet.getRow(++initialRow-1).getCell(colIndexNumber-1).getNumericCellValue());
            } 
            catch (Exception e) {
                break;
            }
        }
    }
    public ExcelColumn(String fileLocation, String workbookName, String sheetName, String header){
        
        fileLocation = fileLocation + "\\" + workbookName + ".xlsx";

        int colIndexNumber = -1;
        
        int initialRow = 1;
        
        /* connection with excel workbook */
        XSSFWorkbook workbook = null;
        try {
            workbook = new XSSFWorkbook(new FileInputStream(fileLocation));
        } catch (IOException ex) {
            Logger.getLogger(ExcelColumn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        /* connection with sheet of workbook */
        XSSFSheet sheet = workbook.getSheetAt(workbook.getSheetIndex(sheetName));
        
        /* determinate colIndexNumber */
        for(int i=1; i<=100; i++){
            try {
                if(header.equals(sheet.getRow(initialRow-1).getCell(i-1).getStringCellValue())){
                    colIndexNumber = i;
                    break;
                }
            } catch (Exception e) {}
        }

        /* return data */
        while(true){
            try {
                this.add(sheet.getRow(++initialRow-1).getCell(colIndexNumber-1).getNumericCellValue());
            } 
            catch (Exception e) {
                break;
            }
        }
    }

    /* sum of column */
    public double sum(){
        double sum = 0;
        for(int i=0; i<this.size(); i++)
            sum += this.get(i);
        
        return sum;
    }
    
    /* mean of column */
    public double mean(){
        return this.sum()/this.size();
    }
    
    /* sample of column */
    public double ssdev(){
        double ssdev = 0;
        double mean = this.mean();
        for(int i=0; i<this.size(); i++){
            ssdev += Math.pow(this.get(i) - mean, 2);
        }
        ssdev /= this.size() - 1;
        
        return Math.sqrt(ssdev);
    }
    
    /* population standard devision of column */
    public double psdev(){
        double psdev = 0;
        double mean = this.mean();
        for(int i=0; i<this.size(); i++){
            psdev += Math.pow(this.get(i) - mean, 2);
        }
        psdev /= this.size();
        
        return Math.sqrt(psdev);
    }
    
    /* return min value */
    public double min(){
        double min = this.get(0);
        for(int i=1; i<this.size(); i++)
            if(this.get(i) < min)
                min = this.get(i);
        return min;
    }
    
    /* return max value */
    public double max(){
        double max = this.get(0);
        for(int i=1; i<this.size(); i++)
            if(this.get(i) > max)
                max = this.get(i);
        return max;
    }
    
    /* return standardized values */
    public double[] standardize(){
        double[] standardized = new double[this.size()];
        double mean = this.mean();
        double psdev = this.psdev();
        
        for(int i=0; i<this.size(); i++){
            standardized[i] = (this.get(i) - mean)/psdev;
        }
        
        return standardized;
    }
    
    /* return normalised values */
    public double[] normalization(){
        double[] normalised = new double[this.size()];
        double min = this.min();
        double max_min = this.max() - min;
        for(int i=0; i<normalised.length; i++)
            normalised[i] = (this.get(i) - min)/max_min;
        return normalised;
    }
}
