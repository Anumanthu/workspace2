package src;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.Iterator;

public class Practice {

    public static void main(String args[]) throws Exception {

        // System.out.println("Column");
        FileInputStream fis = new FileInputStream("C://Users//1018546//Desktop//Selenium Learning//Book2.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(fis);

        int count = wb.getNumberOfSheets();

        for (int i = 0; i < count; i++) {

            //XSSFSheet sheet = wb.getSheetAt(i);
            if (wb.getSheetName(i).equalsIgnoreCase("FIRSTXCELNAME1")) {

                XSSFSheet sheet = wb.getSheetAt(i);
                Iterator<Row> row_itr = sheet.rowIterator();

                Row first_row = row_itr.next();

                Iterator<Cell> cell_itr = first_row.cellIterator();

                int k = 0;

                Cell cv;

                while (cell_itr.hasNext()) {

                    cv = cell_itr.next();

                    if (cv.getStringCellValue().equalsIgnoreCase("testcases")) {
                        break;
                    }
                    k++;
                }
                // System.out.println(k);

                while (row_itr.hasNext()) {
                    Row Desired_row = row_itr.next();

                    System.out.println(Desired_row.getCell(k).getStringCellValue());

                    if (Desired_row.getCell(k).getStringCellValue().equalsIgnoreCase("Purchase")) {

                        Iterator<Cell> cellitr = Desired_row.iterator();

                        while (cellitr.hasNext()) {

                            Cell cvt = cellitr.next();

                            if (cvt.getCellType() == CellType.STRING)
                                System.out.println(cvt.getRichStringCellValue());

                            else
                                System.out.println(NumberToTextConverter.toText(cvt.getNumericCellValue()));

                        }
                        break;

                    }

                }

            }
        }


    }
}
