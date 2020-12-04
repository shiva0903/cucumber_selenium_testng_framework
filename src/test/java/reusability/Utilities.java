package reusability;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import step_definitions.TestRunner;

/**
 * This class contains methods that deals with external files
 * @author p.nvs.vivek
 *
 */
public class Utilities {

	@SuppressWarnings("resource")
	public static HashMap<String, String> readExcel_dataDriven(String path, String sheetname) throws IOException
	{
		HashMap<String, String> hm = new HashMap<String, String>();
		FileInputStream fis = new FileInputStream(path);
		Workbook wb = new XSSFWorkbook(fis);
		Sheet sheet = wb.getSheet(sheetname);
		Row row = sheet.getRow(TestRunner.activerow);
			if(row!=null)
			{
				for(int j=0; j<row.getLastCellNum();j++)
				{
				Cell cell = row.getCell(j);
				if(cell!=null)
				{
					hm.put(sheet.getRow(0).getCell(j).toString(), cell.toString());
				}
				}
			}
		return hm;
	}

}
