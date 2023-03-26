package src;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;

public class UdemyData {

    public static void main(String[] args) throws IOException {

        FileInputStream fis = new FileInputStream("D://Book1.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheet("FIRSTXCELNAME");
        XSSFRow row = sheet.getRow(1);
        XSSFCell cell = row.getCell(1);
        String value = cell.getStringCellValue();
        System.out.println(value);

        int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
        for (int i = 0; i < rowCount + 1; i++) {

            row = sheet.getRow(i);

            for (int j = 0; j < row.getLastCellNum(); j++) {

                System.out.print(row.getCell(j).getStringCellValue() + "|| ");

            }
            System.out.println();

        }

    }

}
