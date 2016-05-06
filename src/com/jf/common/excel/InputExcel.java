/**
 * 
 */
package com.jf.common.excel;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.jf.model.JFUser;

/**
 * @author JF
 *
 *@date 2016年4月7日 下午3:04:29
 *
 */
public class InputExcel {

	public static void main(String[] args) {
		    InputStream is = null;
			try {
				is = new FileInputStream("D:/text.xls");
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HSSFWorkbook hssfWorkbook = null;
			try {
				hssfWorkbook = new HSSFWorkbook(is);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			HSSFSheet hssfSheet=hssfWorkbook.getSheetAt(0);
			for (int i = hssfSheet.getFirstRowNum()+1; i < hssfSheet.getLastRowNum(); i++) {
				HSSFRow hssfRow = hssfSheet.getRow(i);
				JFUser user = new JFUser();
				user.setType((int)hssfRow.getCell(3).getNumericCellValue());
				System.out.println(user.getType());
//				for (int j = 0; j < hssfRow.getLastCellNum(); j++) {
//				
//					System.out.print(hssfRow.getCell(j));
//				}
//				System.out.println();
			}
			

	}

}
