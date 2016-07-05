package com.exam.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

//TODO 增加的类
/**
 * 对注册表操作
 * @author Administrator
 *
 */
public class RegisterUtil {
	//向注册表中添加信息
	public void addRecord(Map<String,String> map){
		//userNodeForPackage 保存到HKEY_CURRENT_USER\Software
		//systemNodeForPackage 保存到HKEY_LOCAL_MACHINE\
		Preferences pre=Preferences.userNodeForPackage(RegisterUtil.class);
		if(map!=null){
			//循环将map中的信息写入注册表
			Set<String> keys=map.keySet();
			for(String key:keys){
				pre.put(key, map.get(key));
			}
		}
	}
	
	//获取存入注册表中的信息
	public Map<String,String> getRecord(){
		Map<String,String> map=null;
		try {
			Preferences pre=Preferences.userNodeForPackage(RegisterUtil.class);
			map=new HashMap<String,String>();
			String[] keys=pre.keys();
			for(String key:keys){
				map.put(key,pre.get(key, null));
			}
		} catch (BackingStoreException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 移除注册表的信息
	 * @param key 所给的键值，即要删除哪条信息
	 */
	public void removeRecord(String key){
		Preferences pre=Preferences.userNodeForPackage(RegisterUtil.class);
		pre.remove(key);
	}
}
