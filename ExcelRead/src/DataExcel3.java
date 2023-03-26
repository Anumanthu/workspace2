package src;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class DataExcel3 {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("D://Book1.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("FIRSTXCELNAME");
        XSSFRow row = sheet.getRow(2);
        XSSFCell cell = row.getCell(2);
        String value = cell.getStringCellValue();
        System.out.println(value);
        // we can directly get particular cell value as shown below
        // System.out.println(sheet.getRow(2).getCell(0).toString);

    }
}
