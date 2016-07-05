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
	
	static{ //因为驱动在整个程序中只运行一次 所以放到静态块中去
		try {
			Class.forName(ReaderProperties.getPro().getProperty("driver"));
		} catch (ClassNotFoundException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取连接
	 * @return 连接
	 */
	public Connection getConnection(){
		try { //db,properties中必须是user和password 否则不能用这种方式,只能单独获取用户名和密码
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
	 * 关闭资源
	 * @param con 要关闭的连接
	 * @param pstmt 要关闭的预编译快
	 * @param rs 要关闭的结果集
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
	 * 给占位符赋值
	 * @param pstmt 要操作的sql语句
	 * @param params 要执行的sql语句中对应的占位符的值 为null则没有占位符
	 * @throws SQLException
	 */
	public void setValue(PreparedStatement pstmt,List<Object> params) throws SQLException{
		//给占位符赋值
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
	 * 更新操作
	 * @param sql 要操作的sql语句
	 * @param params 要执行的sql语句中对应的占位符的值 为null则没有占位符
	 * @return
	 */
	public int update(String sql,List<Object> params){
		int result=0;
		
		try {
			con=this.getConnection();	
			pstmt=con.prepareStatement(sql);
			
			this.setValue(pstmt, params);
			
			result=pstmt.executeUpdate(); //执行语句
		} catch (SQLException e) {
			LogUtil.log.error(e.toString());
			e.printStackTrace();
		} finally{
			this.closeAll(con, pstmt, null);
		}
		return result;
	}
	
	/**
	 * 查询结果集
	 * @param sql 要执行的sql语句
	 * @param params 要执行的sql语句中对应的占位符的值 为null则没有占位符
	 * @return
	 */
	public List<Map<String, Object> > select(String sql,List<Object> params){
		List<Map<String, Object> > list=new ArrayList<Map<String, Object> >();
		Map<String, Object> map=null; //以列名为键，以对应的值为键
		
		try {
			con=this.getConnection();
			pstmt=con.prepareStatement(sql);
			this.setValue(pstmt, params);
			rs=pstmt.executeQuery();
			
			ResultSetMetaData rsmd= rs.getMetaData();//获取元数据
			//从元数据中获取列的信息
			
			String[] colNames=new String[ rsmd.getColumnCount() ];;
			for(int i=0;i<colNames.length;i++){
				colNames[i]=rsmd.getColumnName(i+1);
			}
			
			while(rs.next()){
				map=new HashMap<String, Object>();
				Object obj=null;
				String type;
				if(colNames!=null && colNames.length>0){ 
					//循环取出每一个值
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
	 * 聚合查询
	 * @param sql　要执行的sql语句
	 * @param params　要执行的sql语句中对应的占位符的值 为null则没有占位符
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
	 * 聚合查询
	 * @param sql　要执行的sql语句
	 * @param params　要执行的sql语句中对应的占位符的值 为null则没有占位符
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
	 * DDL操作
	 * @param sql 要执行的语句
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
