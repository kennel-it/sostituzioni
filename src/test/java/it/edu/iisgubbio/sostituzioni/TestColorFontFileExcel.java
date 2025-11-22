package it.edu.iisgubbio.sostituzioni;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javafx.scene.paint.Color;

public class TestColorFontFileExcel {
	private static Sheet scheda;

	private final static byte PRIMO_INSEGNANTE = 5;
	public static void main(String[] args) {
		try(FileInputStream file= new FileInputStream(Ambiente.getFileOrarioExcel())){
			Workbook excel= new XSSFWorkbook(file);
			scheda=excel.getSheet("sostegno");
			for(int ir=PRIMO_INSEGNANTE; ir<20; ir++) {
				if(ir%2!=0) { //nel file i professori si trovano nelle righe di valore dispari
					System.out.println();
					Row row=scheda.getRow(ir);
					for(int ic=0; ic<row.getLastCellNum(); ic++) {
						Cell cella=row.getCell(ic);
						CellType tipo=cella.getCellType();
						System.out.println("riga: "+ir+"; colonna: "+ic+"; tipo: "+tipo+";");
						switch (tipo) {
							case CellType.NUMERIC:
								break;
							case CellType.STRING:
								CellStyle stile=cella.getCellStyle();
								Font font=excel.getFontAt(stile.getFontIndex());
								if(font instanceof XSSFFont xssfFont) {
									byte[] rgb = xssfFont.getXSSFColor().getRGB();
									//il tipo byte è signed quindi dobbiamo fare l'AND con 0xFF(255 in int)
									//per riportarlo al valore corrispondente
									System.out.println("è una stringa con colore: RGB("+(rgb[0]&0xFF)+","+(rgb[1]&0xFF)+","+(rgb[2]&0xFF)+")");
									System.out.println("corrisponde a: "+daByteAString(rgb));
								}
								break;
							case CellType.BLANK:
								break;		
						}
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
	
	/**
	 * Trova il colore da un vettore di byte che sia Rosso o Verde o Blu o Nero
	 * @param coloreByte il colore in un vettore di byte
	 * @return il colore rispettivo della classe "Color". 
	 * 			Nel caso non vi sia un colore conosciuto verrà restituito un null
	 */
	private static Color coloreDaByte(byte[] coloreByte) {
		Color colore = null;
		int[] coloreInt = new int[3];
		for(int i=0; i<3; i++) {
			coloreInt[i]=coloreByte[i]&0xFF;
		}
		if(coloreInt[0]==255) { 
			if(coloreInt[1]==0&&coloreInt[2]==0) {
				colore=Color.RED;
			}
		} else if(coloreInt[2]==255) {
			if(coloreInt[0]==0&&coloreInt[1]==0) {
				colore=Color.BLUE;
			}
		} else if(coloreInt[1]==255){
			if(coloreInt[0]==0&&coloreInt[2]==0) {
				colore=Color.GREEN;
			}
		} else if(coloreInt[0]==0&&coloreInt[2]==0&&coloreInt[1]==0){
			colore=Color.BLACK;
		}
		return colore;
	}
	private static String daByteAString(byte[] coloreByte) {
		String colore = null;
		int[] coloreInt = new int[3];
		for(int i=0; i<3; i++) {
			coloreInt[i]=coloreByte[i]&0xFF;
		}
		if(coloreInt[0]==255) { 
			if(coloreInt[1]==0&&coloreInt[2]==0) {
				colore="Rosso";
			}
		} else if(coloreInt[2]==255) {
			if(coloreInt[0]==0&&coloreInt[1]==0) {
				colore="Blu";
			}
		} else if(coloreInt[1]==255){
			if(coloreInt[0]==0&&coloreInt[2]==0) {
				colore="Verde";
			}
		} else if(coloreInt[0]==0&&coloreInt[2]==0&&coloreInt[1]==0){
			colore="Nero";
		}
		return colore;
	}
}
