package com.exam.utils;


import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.exam.dao.DBHelper;

import jxl.*;
import jxl.read.biff.BiffException;

public class ExcelToOracle extends DBHelper{
	/**
	 * ����excel���е���Ϣ��Oracle��ȥ
	 * @param filePath �ļ�·��
	 * @param sheetId excel�еĵڼ���sheet��
	 * @throws BiffException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	public int[] insertExcelToOracle(String filePath,int sheetId) throws BiffException, IOException {
		int[] is=new int[2];
		int is2=0;
		String[] str = null;
		List list = null; //����һ��list �����洢��ȡ������
		Cell cell = null;
		
		Sheet sheet=getSheet(filePath,sheetId);
		
		for(int i=0; i<sheet.getRows(); i++){ //����(��ͷ��Ŀ¼����Ҫ����1��ʼ)
			str = new String[sheet.getColumns()]; //����һ������ �����洢ÿһ�е�
			list = new ArrayList();
			String sql="insert into questionBank values(seq_questionBank_qId.nextval,?,?,?,?,?)";
			for(int j=0; j<sheet.getColumns(); j++){ //����
				cell = sheet.getCell(j,i); //��ȡ��i�У���j�е�ֵ    
				str[j] = cell.getContents(); //����i�е���Ϣ����str������
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
	 * ��ȡSheet��
	 * @param path excel��·��
	 * @param i excel�еĵڼ���sheet
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	private Sheet getSheet(String path,int i) throws BiffException, IOException{
		Workbook rwb = null;
		
		InputStream stream = new FileInputStream(path);//����������
		
		rwb = Workbook.getWorkbook(stream);//��ȡExcel�ļ�����

		Sheet sheet = rwb.getSheet(i);  //��ȡ�ļ���ָ�������� Ĭ�ϵĵ�һ��
		
		return sheet;
	}
}
