package src;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

public class ReadExeclData {

    public static ArrayList<String> getData(String sheetname, String testcasename) throws Exception {


        ArrayList<String> al = new ArrayList<String>();
        FileInputStream fis = new FileInputStream("C://Users//ankindinti//Desktop//Selenium Learning//Book2.xlsx");

        XSSFWorkbook wb = new XSSFWorkbook(fis);

        int sheetcount = wb.getNumberOfSheets();
        XSSFSheet sh;
        XSSFRow row;
        XSSFCell cell = null;


        Iterator rowiterator;
        Iterator celliterator;

        String cellvalue;


        for (int i = 0; i < sheetcount; i++) {

            if (wb.getSheetAt(i).getSheetName().equalsIgnoreCase(sheetname)) {
                sh = wb.getSheetAt(i);

                row = sh.getRow(0);

                celliterator = row.cellIterator();


                while (celliterator.hasNext()) {
                    //cell=celliterator.next();

                    cell = (XSSFCell) celliterator.next();
                    cellvalue = cell.getStringCellValue();

                    if (cellvalue.equalsIgnoreCase(testcasename))
                        break;
                }

                int cellindex = cell.getColumnIndex();
                row = cell.getRow();

                rowiterator = sh.iterator();


                while (rowiterator.hasNext()) {

                    row = (XSSFRow) rowiterator.next();

                    if (row.getCell(cellindex).getStringCellValue().equalsIgnoreCase("AddProfile")) {
                        break;
                    }

                }

                celliterator = row.cellIterator();

                while (celliterator.hasNext()) {
                    cell = (XSSFCell) celliterator.next();
                    cellvalue = cell.getStringCellValue();

                    if (cell.getCellType() == CellType.STRING)
                        al.add(cellvalue);

                    else
                        al.add(NumberToTextConverter.toText(cell.getNumericCellValue()));

                }

                //rowiterator=row.iterator().next().getStringCellValue();


            }
        }

        return al;

    }

}
