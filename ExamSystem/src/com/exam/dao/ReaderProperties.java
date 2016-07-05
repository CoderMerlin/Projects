package com.exam.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import com.exam.utils.LogUtil;


@SuppressWarnings("serial")
public class ReaderProperties extends Properties {
	private static ReaderProperties rp=new ReaderProperties();
	
	private ReaderProperties(){
		InputStream is=null;
		
		try {
			is=this.getClass().getResourceAsStream("/db.properties");
			//is=new FileInputStream(new File("src\\db.properties"));
			this.load(is); //���������ж�ȡ�����б�
		} catch (IOException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		} finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					LogUtil.log.error(e.toString());
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * ��ȡReaderProperties
	 * @return
	 */
	public static ReaderProperties getPro(){
		return rp;
	}
}
