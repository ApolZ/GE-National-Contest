package application.service;

/**
 * Created by ApolZ on 2016/4/28.
 */

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import application.entity.Onsite;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExportExcel {

    public static void export(List<Onsite> onsiteList, OutputStream out) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("Sheet1");
        HSSFRow row ;

        row= sheet.createRow(0);
        row.createCell(0).setCellValue("ToolkeeperID");
        row.createCell(1).setCellValue("ToolboxID");
        row.createCell(2).setCellValue("EngineerID");
        row.createCell(3).setCellValue("ToolID");
        row.createCell(4).setCellValue("Address");
        row.createCell(5).setCellValue("LendingTime");
        row.createCell(6).setCellValue("ReturnTime");
        row.createCell(7).setCellValue("ReturnStatus");
        row.createCell(8).setCellValue("Remarks");
        row.createCell(9).setCellValue("OrderID");


        for (int i=0;i<onsiteList.size();i++) {
            System.out.println(i);
            row = sheet.createRow( i+1);
            row.createCell(0).setCellValue(onsiteList.get(i).getToolkeeperID());
            row.createCell(1).setCellValue(onsiteList.get(i).getToolboxID());
            row.createCell(2).setCellValue(onsiteList.get(i).getEngineerID());
            row.createCell(3).setCellValue(onsiteList.get(i).getToolID());
            row.createCell(4).setCellValue(onsiteList.get(i).getAddress());
            row.createCell(5).setCellValue(onsiteList.get(i).getLendingTime());
            row.createCell(6).setCellValue(onsiteList.get(i).getReturnTime());
            row.createCell(7).setCellValue(onsiteList.get(i).getReturnStatus());
            row.createCell(8).setCellValue(onsiteList.get(i).getRemarks());
            row.createCell(9).setCellValue(onsiteList.get(i).getOrderID());
        }
        try {
            wb.write(out);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

