package com.exam.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.exam.utils.LogUtil;


/**
 * DBHelper
 * @author rdz
 *
 */
public class DBHelper {
	private Connection con=null;
	private PreparedStatement pstmt=null;
	private ResultSet rs=null;
	
	static{ //��Ϊ����������������ֻ����һ�� ���Էŵ���̬����ȥ
		try {
			Class.forName(ReaderProperties.getPro().getProperty("driver"));
		} catch (ClassNotFoundException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * ��ȡ����
	 * @return ����
	 */
	public Connection getConnection(){
		try { //db,properties�б�����user��password �����������ַ�ʽ,ֻ�ܵ�����ȡ�û���������
			con=DriverManager.getConnection(
					ReaderProperties.getPro().getProperty("url"),
					ReaderProperties.getPro().getProperty("user"),
					ReaderProperties.getPro().getProperty("password") );
			
		} catch (SQLException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		}
		
		return con;
	}
	
	/**
	 * �ر���Դ
	 * @param con Ҫ�رյ�����
	 * @param pstmt Ҫ�رյ�Ԥ�����
	 * @param rs Ҫ�رյĽ����
	 */
	public void closeAll(Connection con,PreparedStatement pstmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				LogUtil.log.error(e.toString());
				e.printStackTrace();
			}
		}
		
		if(pstmt!=null){
			try {
				pstmt.close();
			} catch (SQLException e) {
				LogUtil.log.error(e.toString());
				e.printStackTrace();
			}
		}
		
		if(con!=null){
			try {
				con.close();
			} catch (SQLException e) {
				LogUtil.log.error(e.toString());
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * ��ռλ����ֵ
	 * @param pstmt Ҫ������sql���
	 * @param params Ҫִ�е�sql����ж�Ӧ��ռλ����ֵ Ϊnull��û��ռλ��
	 * @throws SQLException
	 */
	public void setValue(PreparedStatement pstmt,List<Object> params) throws SQLException{
		//��ռλ����ֵ
		if(params!=null && params.size()>0){
			Object obj=null;
			String type="";
			for(int i=0;i<params.size();i++){
				obj=params.get(i);
				if(obj!=null){
					type=obj.getClass().getName();
					if("[B".equals(type)){
						pstmt.setBytes(i+1, (byte[]) obj);
					} else{
						pstmt.setString(i+1, String.valueOf(obj));
					}
				} else{
					pstmt.setString(i+1,String.valueOf(obj) );
				}
			}
		}
	}
	
	/**
	 * ���²���
	 * @param sql Ҫ������sql���
	 * @param params Ҫִ�е�sql����ж�Ӧ��ռλ����ֵ Ϊnull��û��ռλ��
	 * @return
	 */
	public int update(String sql,List<Object> params){
		int result=0;
		
		try {
			con=this.getConnection();	
			pstmt=con.prepareStatement(sql);
			
			this.setValue(pstmt, params);
			
			result=pstmt.executeUpdate(); //ִ�����
		} catch (SQLException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstmt, null);
		}
		return result;
	}
	
	/**
	 * ��ѯ�����
	 * @param sql Ҫִ�е�sql���
	 * @param params Ҫִ�е�sql����ж�Ӧ��ռλ����ֵ Ϊnull��û��ռλ��
	 * @return
	 */
	public List<Map<String, Object> > select(String sql,List<Object> params){
		List<Map<String, Object> > list=new ArrayList<Map<String, Object> >();
		Map<String, Object> map=null; //������Ϊ�����Զ�Ӧ��ֵΪ��
		
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			this.setValue(pstmt, params);
			rs=pstmt.executeQuery();
			
			ResultSetMetaData rsmd= rs.getMetaData();//��ȡԪ����
			//��Ԫ�����л�ȡ�е���Ϣ
			
			String[] colNames=new String[ rsmd.getColumnCount() ];;
			for(int i=0;i<colNames.length;i++){
				colNames[i]=rsmd.getColumnName(i+1);
			}
			
			while(rs.next()){
				map=new HashMap<String, Object>();
				Object obj=null;
				String type;
				if(colNames!=null && colNames.length>0){ 
					//ѭ��ȡ��ÿһ��ֵ
					for(String s:colNames){
						obj=rs.getObject(s);
						if(obj!=null){
							type=obj.getClass().getName();
							if("oracle.sql.BLOB".equals(type)){
								Blob blob=rs.getBlob(s);
								byte[] bt=null;
								InputStream is=blob.getBinaryStream();
								if(is!=null){
									bt=new byte[(int) blob.length()];
									try {
										is.read(bt);
									} catch (IOException e) {
										e.printStackTrace();
									} finally{
										if(is!=null){
											try {
												is.close();
											} catch (IOException e) {
												e.printStackTrace();
											}
										}
									}
									map.put(s, bt);
								} else{
									map.put(s, null);
								}
							} else{
								map.put(s,String.valueOf(obj));
							}
						} else{
							map.put(s, null);
						}
					}
				}
				list.add(map);
			}
		} catch (SQLException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstmt, rs);
		}
		return list;
	}
	
	/**
	 * �ۺϲ�ѯ
	 * @param sql��Ҫִ�е�sql���
	 * @param params��Ҫִ�е�sql����ж�Ӧ��ռλ����ֵ Ϊnull��û��ռλ��
	 * @return
	 */
	public double selectPloymer(String sql,List<Object> params){
		double result=0;
		
		con=this.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			this.setValue(pstmt, params);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				result=rs.getDouble(1);
			}
			
		} catch (SQLException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstmt, rs);
		}
		
		return result;
	}
	
	/**
	 * �ۺϲ�ѯ
	 * @param sql��Ҫִ�е�sql���
	 * @param params��Ҫִ�е�sql����ж�Ӧ��ռλ����ֵ Ϊnull��û��ռλ��
	 * @return
	 */
	public List<Double> selectPloymers(String sql,List<Object> params){
		List<Double> result=new ArrayList<Double>();;
		
		con=this.getConnection();
		try {
			pstmt=con.prepareStatement(sql);
			this.setValue(pstmt, params);
			
			rs=pstmt.executeQuery();
			
			if(rs.next()){
				for(int i=0;i<rs.getMetaData().getColumnCount();i++){
					result.add(rs.getDouble(i+1));
				}
			}
			
		} catch (SQLException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstmt, rs);
		}
		
		return result;
	}
	
	/**
	 * DDL����
	 * @param sql Ҫִ�е����
	 * @return
	 */
	public boolean createOp(String sql){
		boolean bl=false;
		
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			bl=pstmt.execute();
			
		} catch (SQLException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstmt, rs);
		}
		return bl;
	}
}
