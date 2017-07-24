package org.great.util;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author Xiejun 导出及读取excel工具
 * @since 2017-2-21 15:14:25未测试
 */
public class ToExcel {

	public static ArrayList<ArrayList<String>> readExcel(String FileUrl) {
		ArrayList<ArrayList<String>> arraylist = new ArrayList<ArrayList<String>>();
		try {
			File excelFile = new File(FileUrl); // 创建文件对象

			FileInputStream is = new FileInputStream(excelFile); // 文件流
			Workbook workbook = WorkbookFactory.create(is); // 这种方式 Excel
															// 2003/2007/2010
															// 都是可以处理的
			int sheetCount = workbook.getNumberOfSheets(); // Sheet的数量
			// 遍历每个Sheet
			for (int s = 0; s < sheetCount; s++) {
				Sheet sheet = workbook.getSheetAt(s);
				int rowCount = sheet.getPhysicalNumberOfRows(); // 获取总行数
				// 遍历每一行
				ArrayList<String> list = new ArrayList<String>();
				for (int r = 0; r < rowCount; r++) {
					Row row = sheet.getRow(r);
					int cellCount = row.getPhysicalNumberOfCells(); // 获取总列数
					// 遍历每一列
					list = new ArrayList<String>();
					for (int c = 0; c < cellCount; c++) {
						Cell cell = row.getCell(c);
						int cellType = cell.getCellType();
						String cellValue = null;
						switch (cellType) {
						case Cell.CELL_TYPE_STRING: // 文本
							cellValue = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_NUMERIC: // 数字、日期
							cellValue = String.valueOf((int) cell
									.getNumericCellValue()); // 数字
							break;
						case Cell.CELL_TYPE_BOOLEAN: // 布尔型
							cellValue = String.valueOf(cell
									.getBooleanCellValue());
							break;
						case Cell.CELL_TYPE_BLANK: // 空白
							cellValue = cell.getStringCellValue();
							break;
						case Cell.CELL_TYPE_ERROR: // 错误
							cellValue = "错误";
							break;
						case Cell.CELL_TYPE_FORMULA: // 公式
							cellValue = "错误";
							break;
						default:
							cellValue = "错误";
						}
						list.add(cellValue);
					}
					arraylist.add(list);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return arraylist;
	}

	// 写入excel方法
	public static void writer(String fileName, String fileType,
			ArrayList<String[]> array) throws IOException {

		// 创建工作文档对象
		Workbook wb = null;
		if (fileType.equals("xls")) {
			wb = new HSSFWorkbook();
		} else if (fileType.equals("xlsx")) {
			wb = new XSSFWorkbook();
		} else {
			System.out.println("您的文档格式不正确！");
		}

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFCellStyle styles = workbook.createCellStyle();
		HSSFCellStyle styles1 = workbook.createCellStyle();
		HSSFFont font_data_default = workbook.createFont();

		styles.setFont(font_data_default);
		styles.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
		styles.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 居中
		styles.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		styles.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		styles.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		styles.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框

		styles1.setFont(font_data_default);
		styles1.setAlignment(HSSFCellStyle.ALIGN_CENTER);// 居中
		styles1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 居中
		styles1.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框
		styles1.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框
		styles1.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框
		styles1.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框
		styles1.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex());
		styles1.setFillPattern(CellStyle.SOLID_FOREGROUND);
		styles.setWrapText(true);// 自動換行

		HSSFSheet sheet = workbook.createSheet(fileName);

		sheet.setDefaultColumnWidth((short) 20);
		sheet.setDefaultRowHeight((short) 800);
		sheet.setVerticallyCenter(true);
		// 创建sheet对象
		// Sheet sheet1 = (Sheet) wb.createSheet("sheet1");
		// 循环写入行数据
		for (int i = 0; i < array.size(); i++) {
			HSSFRow row = sheet.createRow(i);
			// 循环写入列数据
			for (int j = 0; j < array.get(i).length; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(array.get(i)[j]);
			}
		}
		// 创建文件流
		OutputStream stream = new FileOutputStream(fileName + "." + fileType);
		// 写入数据
		wb.write(stream);
		// 关闭文件流
		stream.close();
	}

	public static File encrypt(File file) {
		DataOutputStream output = null;
		FileInputStream input = null;
		File fileexam = new File("examstu.temp");
		try {
			output = new DataOutputStream(new BufferedOutputStream(
					new FileOutputStream(fileexam)));
			// File file = new File("考试题目.xls");
			// if(file.exists()){
			// System.out.println("存在");
			// }else{
			// System.out.println("不存在");
			// }
			input = new FileInputStream(file);
			output.writeUTF("654123");// 文件加密码
			output.writeLong(file.length());
			byte[] data = new byte[1024];
			while (true) {
				int rdlen = input.read(data);
				// System.out.println("读取长度是" + rdlen);
				if (rdlen < 0) {
					break;
				}
				output.write(data, 0, rdlen);
				output.flush();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {// 关闭流
			try {
				output.close();
				input.close();
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		return fileexam;
	}

}
