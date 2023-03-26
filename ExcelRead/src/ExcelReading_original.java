package src;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;


public class ExcelReading_original {

    public ArrayList<String> getData(String testcasename) throws Exception {

        FileInputStream fis = new FileInputStream("C://Users//ankindinti//Desktop//Selenium Learning//Book2.");

        XSSFWorkbook wb = new XSSFWorkbook(fis);

        int sheets = wb.getNumberOfSheets();

        ArrayList<String> a = new ArrayList<String>();

        for (int i = 0; i < sheets; i++) {
            if (wb.getSheetAt(i).getSheetName().equalsIgnoreCase("FIRSTXCELNAME1")) {
                XSSFSheet sheet = wb.getSheetAt(i);

                XSSFRow row = sheet.getRow(0);

                //Iterator<Row>row=sheet.rowIterator();

                Iterator<Cell> cell = row.cellIterator();

                int column = 0;
                int k = 0;

                while (cell.hasNext()) {
                    if (cell.next().getStringCellValue().equalsIgnoreCase(testcasename)) {
                        column = k;
                        break;
                    }
                    k++;
                }

                //System.out.println(k);
                Iterator<Row> rows = sheet.iterator();


                while (rows.hasNext()) {
                    Row r = rows.next();

                    if (r.getCell(column).getStringCellValue().equalsIgnoreCase("purchase")) {
                        Iterator<Cell> cell1 = r.cellIterator();
                        while (cell1.hasNext()) {
                            Cell cv = cell1.next();

                            if (cv.getCellType() == CellType.STRING)
                                a.add(cv.getStringCellValue());
                            else
                                a.add(NumberToTextConverter.toText(cv.getNumericCellValue()));

                            //System.out.println(cell1.next().getStringCellValue());

                        }


                    }
                }


            }

        }

        return a;
    }

}
