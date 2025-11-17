package it.edu.iisgubbio.sostituzioni;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class TestColorFontFileExcel {
	private static Sheet scheda;
	
	public static void main(String[] args) {
		try(FileInputStream file= new FileInputStream(Ambiente.getFileOrarioExcel())){
			Workbook excel= new XSSFWorkbook(file);
			scheda=excel.getSheet("sostegno");
			
			scheda.getLastRowNum();
			for(int i=0; i<scheda.getLastRowNum(); i++) {
				Row row=scheda.getRow(i);
				for(int ic=0; ic<row.getLastCellNum(); ic++) {
					Cell cella=row.getCell(ic);
					CellStyle stile=cella.getCellStyle();
					Font font=excel.getFontAt(stile.getFontIndex());
					if(font instanceof XSSFFont xssfFont) {
						System.out.println(xssfFont.getXSSFColor()); 
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
