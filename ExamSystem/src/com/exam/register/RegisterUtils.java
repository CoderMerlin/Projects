package com.exam.register;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.exam.login.Login;
import com.exam.register.RegisterUtils;


public class RegisterUtils {
	
	//��ע�������ӽ�ʦ��Ϣ
	public void addTeacherRecord(Map<String,String> map){
		//userNodeForPackage ���浽HKEY_CURRENT_USER\Software
		//systemNodeForPackage ���浽HKEY_LOCAL_MACHINE\
		Preferences pre=Preferences.userNodeForPackage(Login.class);  //Login.class ���õ��ǲ�ͬ�ļ��е���·��
		if(map!=null){
			//ѭ����map�е���Ϣд��ע���
			Set<String> keys=map.keySet();
			for(String key:keys){
				pre.put(key, map.get(key));
			}
		}
	}
	//��ע��������ѧ����Ϣ
	public void addStudentRecord(Map<String,String> map){
		//userNodeForPackage ���浽HKEY_CURRENT_USER\Software
		//systemNodeForPackage ���浽HKEY_LOCAL_MACHINE\
		Preferences pre=Preferences.userNodeForPackage(RegisterUtils.class);
		if(map!=null){
			//ѭ����map�е���Ϣд��ע���
			Set<String> keys=map.keySet();
			for(String key:keys){
				pre.put(key, map.get(key));
			}
		}
	}
	
	
	
	
	//��ȡ����ע����еĽ�ʦ��Ϣ
	public Map<String,String> getTeacherRecord(){
		Map<String,String> map=null;
		try {
			Preferences pre=Preferences.userNodeForPackage(Login.class);  //����ʦ����ע����login�ļ�����
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
	
	
	//��ȡ����ע����е�ѧ����Ϣ
	public Map<String,String> getStudentRecord(){
		Map<String,String> map=null;
		try {
			Preferences pre=Preferences.userNodeForPackage(RegisterUtils.class);
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

	
	//����绰�����Ƿ���ȷ
	public boolean isPhoneNumberValid(String phoneNumber) {
		boolean isValid = false;
		
		
		String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|" +
				"(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";
		CharSequence inputStr = phoneNumber;
		
		Pattern pattern = Pattern.compile(expression);
		
		Matcher matcher = pattern.matcher(inputStr);
		
		if (matcher.matches()) {
		isValid = true;
		}
		return isValid;
	}


	//���������Ƿ���ȷ
	public boolean isEmail(String email) {
		
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|" +
				"(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	//��������Ϊ���ģ���Լ����2��6������֮��
	public boolean ChineseNameTest(String name) {
		String str="[\u4e00-\u9fa5]{2,6}";
		Pattern p =Pattern.compile(str);
		Matcher m =p.matcher(name);
      /*  if (!name.matches("[\u4e00-\u9fa5]{2,4}")) {
            System.out.println("ֻ������2��4������");
            return false;
        }else return true;*/
		return m.matches();
    }
	
	//���������ֺ���ĸ��ɣ�����Ҫͬʱ�������ֺ���ĸ  ��6λ��15λ
	public boolean isPassWord(String password){
		String str= "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$";
		Pattern p=Pattern.compile(str);
		Matcher m=p.matcher(password);
		return m.matches();
	}
	

	//���������˺��Ƿ���ȷ
	/**
	 * ѧ���˺�����
	 */
	public boolean isStudentNumber(String student){
		String str = "S1[0-9]{3}";
		//����һ��д����String str="S1[0-9][0-9][0-9]"
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(student);
		return m.matches();
	}
	/**
	 * ��ʦ�˺�����
	 */
	public boolean isTeacherNumber(String teacher){
		String str = "T1[0-9]{3}";
		//����һ��д����String str="S1[0-9][0-9][0-9]"

		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(teacher);
		return m.matches();
	}
	


	/**
	 * ���������֤��
	 * @return
	 */
	public String RandomAuthCode(String Number) {
		Random rd1=new Random();
		StringBuffer sbf=new StringBuffer();
		
		//���������֤��
		
		int count=0;
		int flag=0;
		
		while(count<=5){
			flag =rd1.nextInt(2); //����ж����������ֻ�����ĸ
			if(flag==0){  //�����0������������
			 sbf.append(  rd1.nextInt(10) );  //[0,10)
			}else{
			 sbf.append(  (char)(rd1.nextInt(26)+97) );  //��д��ĸ��65��ʼ  Сд��ĸ97��ʼ 
			}
			count++;
		
		}
			Number=sbf.toString();
			return Number;
	}
	

	
	
	
}
