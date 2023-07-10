package com.nopcommerce.utills;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReadData {
    private FileInputStream inputStream;
    private XSSFWorkbook xssfWorkbook;
    private XSSFSheet sheet;


    public  ReadData(String filename) throws IOException {
        String filepath="./src/main/resources/testData/"+filename+".xlsx";
        inputStream=new FileInputStream(filepath);
        xssfWorkbook=new XSSFWorkbook(inputStream);
        sheet=xssfWorkbook.getSheet("sheet1");
    }
    public String getData( int row,int cell)
    {
       return sheet.getRow(row).getCell(cell).getStringCellValue();
    }
   public List<String>getData(int row)

   {
       List<String> rowData=new ArrayList<String>();
       int cellcount=sheet.getRow(0).getLastCellNum();
       for (int i=0;i<cellcount;i ++) {
           String data = sheet.getRow(row).getCell(i).getStringCellValue();
           rowData.add(data);
       }
       return rowData;
   }
    public String[][]getData ()
    {
      int lastrowno=  sheet.getLastRowNum();
      int cellCount=sheet.getRow(0).getLastCellNum();
      String info[][]=new String[lastrowno][cellCount];
      int k=0,l;
      for (int i=1;i<lastrowno;i++)
      {
          l=0;
          for (int j=0;j<cellCount;j++)
          {
            String data=sheet.getRow(i).getCell(j).getStringCellValue();
            info[k][l]=data;
            l++;
          }
          k++;

      }
      return info;


    }
    public static void main(String[] args) throws Exception {
        ReadData readData = new ReadData("LOGINPAGE");
        /*String data=readData.getData(1,1);
        System.out.println(data);
        List<String> rowData=readData.getData(1);
        System.out.println(rowData);

        List<String> colData= readData.getData("email");
        System.out.println("Col Data : "+colData);
*/
        String info[][]= readData.getData();

        for (int i=0;i<info.length;i++)
        {
            for (int j=0;j<info[i].length;j++)
            {
                System.out.print(info[i][j]+" ");
            }
            System.out.println();
        }
    }


}
