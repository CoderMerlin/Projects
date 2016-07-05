package com.exam.utils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.exam.dao.DBHelper;

import jxl.*;
import jxl.read.biff.BiffException;

public class ExcelToOracle extends DBHelper{
	/**
	 * 插入excel表中的信息到Oracle中去
	 * @param filePath 文件路径
	 * @param sheetId excel中的第几张sheet表
	 * @throws BiffException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public int[] insertExcelToOracle(String filePath,int sheetId) throws BiffException, IOException {
		int[] is=new int[2];
		int is2=0;
		String[] str = null;
		List list = null; //创建一个list 用来存储读取的内容
		Cell cell = null;
		
		Sheet sheet=getSheet(filePath,sheetId);
		
		for(int i=0; i<sheet.getRows(); i++){ //行数(表头的目录不需要，从1开始)
			str = new String[sheet.getColumns()]; //创建一个数组 用来存储每一列的
			list = new ArrayList();
			String sql="insert into questionBank values(seq_questionBank_qId.nextval,?,?,?,?,?)";
			for(int j=0; j<sheet.getColumns(); j++){ //列数
				cell = sheet.getCell(j,i); //获取第i行，第j列的值    
				str[j] = cell.getContents(); //将第i行的信息存入str数组中
			}
			for(int k=0;k<str.length;k++){
				list.add(str[k]);
			}
			
			if(this.update(sql, list)>0){
				is2++;
			}
		}
		
		is[0]=sheet.getRows();
		is[1]=is2;
		return is;
	}
	
	/**
	 * 获取Sheet表
	 * @param path excel的路劲
	 * @param i excel中的第几张sheet
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	private Sheet getSheet(String path,int i) throws BiffException, IOException{
		Workbook rwb = null;
		
		InputStream stream = new FileInputStream(path);//创建输入流
		
		rwb = Workbook.getWorkbook(stream);//获取Excel文件对象

		Sheet sheet = rwb.getSheet(i);  //获取文件的指定工作表 默认的第一个
		
		return sheet;
	}
}
