package src;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;

public class ExcelReading {

    public static void main(String args[]) throws Exception {

        // System.out.println("Column");
        FileInputStream fis = new FileInputStream("C://Users//ankindinti//Desktop//Selenium Learning//Book2.xlsx");

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        //XSSFSheet sheettemp=wb.getSheet("FIRSTXCELNAME1");


        int sheetcount = wb.getNumberOfSheets();

        for (int i = 0; i < sheetcount; i++) {
            XSSFSheet sheet = wb.getSheetAt(i);

            // Get access to desired sheet
            if (sheet.getSheetName().equalsIgnoreCase("FIRSTXCELNAME1")) {

                // Get access to all the rows

                // System.out.println(sheet.getLastRowNum()); //getting last rownumber or how
                // many rows are present

                Iterator<Row> rows = sheet.rowIterator();
                // Get first row using iterator
                Row first_row = rows.next();

                // Get access to all the cells from first row using iterator

                Iterator<Cell> cells = first_row.cellIterator();
                int k = 0;

                while (cells.hasNext()) {
                    Cell ce = cells.next();

                    if (ce.getStringCellValue().equalsIgnoreCase("testcases")) {
                        break;
                    }
                    k++;

                }

                // System.out.println(k);

                while (rows.hasNext()) {
                    Row desired_row = rows.next();

                    if (desired_row.getCell(k).getStringCellValue().equalsIgnoreCase("purchase")) {

                        Iterator<Cell> cell1 = desired_row.cellIterator();
                        while (cell1.hasNext()) {

                            Cell cv = cell1.next();

                            if (cv.getCellType() == CellType.STRING)

                                System.out.println(cv.getStringCellValue());
                                // System.out.println(cv.toString());

                            else

                                System.out.println(NumberToTextConverter.toText(cv.getNumericCellValue()));

                        }
                    }
                }

            }

        }

    }

}
