package Utils;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

//class to read data from excel file, convert to json object and add to json array
public class Excel_reader
{
    public JSONArray excel()throws FileNotFoundException, IOException
    {
        String xlpath = "src\\main\\resources\\Payload_input.xlsx";
        FileInputStream inputStream = new FileInputStream(xlpath);

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet =workbook.getSheetAt(0);

        int rows = sheet.getLastRowNum();
        int columns = sheet.getRow(0).getLastCellNum();

        JSONArray arr = new JSONArray();

        for(int r=0;r<=rows;r++)
        {
            JSONObject input = new JSONObject();

            XSSFRow row = sheet.getRow(r);
            for(int c=0; c<columns; c++) {
                XSSFCell cell = row.getCell(c);

                switch (c) {
                    case 0 ->
                        input.put("title", cell.getStringCellValue());


                    case 1 ->
                        input.put("price", cell.getNumericCellValue());


                    case 2 ->
                        input.put("description", cell.getStringCellValue());


                    case 3 ->
                        input.put("image", cell.getStringCellValue());

                    case  4 ->
                        input.put("category", cell.getStringCellValue());
                }

            }
            arr.put(input);
        }

        return arr;
    }
}
