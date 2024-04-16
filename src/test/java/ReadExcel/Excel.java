package ReadExcel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.testng.annotations.DataProvider;

public class Excel {
	
	// File Input = the file
	// Get the data into a object[][]
	
	// Step 1 : File Input 
    static Workbook book ;
    static Sheet sheet ;
    public static String SHEET_NAME = System.getProperty("user.dir")+"D:\\TestData.xlsx";
	
	public static Object[][] getTestDataFromExcel(String sheetName){
		
		try {
			FileInputStream file = null ;
			file = new FileInputStream(SHEET_NAME);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	   // book = WorkbookFactory.create(file);
	    
	    sheet = book.getSheet(sheetName);
	    
	    Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
	    for(int i=0;i<sheet.getLastRowNum();i++) {
	    	for(int j=0;j<sheet.getRow(0).getLastCellNum();j++) {
	    		data[i][j]= sheet.getRow(i+1).getCell(j).toString();
	    	}
	    }
	    
	    
		return data;
		
	}

	
	@DataProvider
	public Object[][] getdata(){
		return getTestDataFromExcel("Sheet1");
	}

}
