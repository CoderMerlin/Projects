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
	
	//向注册表中添加教师信息
	public void addTeacherRecord(Map<String,String> map){
		//userNodeForPackage 保存到HKEY_CURRENT_USER\Software
		//systemNodeForPackage 保存到HKEY_LOCAL_MACHINE\
		Preferences pre=Preferences.userNodeForPackage(Login.class);  //Login.class 设置的是不同文件夹的内路径
		if(map!=null){
			//循环将map中的信息写入注册表
			Set<String> keys=map.keySet();
			for(String key:keys){
				pre.put(key, map.get(key));
			}
		}
	}
	//向注册表中添加学生信息
	public void addStudentRecord(Map<String,String> map){
		//userNodeForPackage 保存到HKEY_CURRENT_USER\Software
		//systemNodeForPackage 保存到HKEY_LOCAL_MACHINE\
		Preferences pre=Preferences.userNodeForPackage(RegisterUtils.class);
		if(map!=null){
			//循环将map中的信息写入注册表
			Set<String> keys=map.keySet();
			for(String key:keys){
				pre.put(key, map.get(key));
			}
		}
	}
	
	
	
	
	//获取存入注册表中的教师信息
	public Map<String,String> getTeacherRecord(){
		Map<String,String> map=null;
		try {
			Preferences pre=Preferences.userNodeForPackage(Login.class);  //将教师放在注册表的login文件夹中
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
	
	
	//获取存入注册表中的学生信息
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

	
	//检验电话号码是否正确
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


	//检验邮箱是否正确
	public boolean isEmail(String email) {
		
		String str = "^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|" +
				"(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$";
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(email);
		return m.matches();
	}
	
	//检验姓名为中文，且约束在2到6个文字之间
	public boolean ChineseNameTest(String name) {
		String str="[\u4e00-\u9fa5]{2,6}";
		Pattern p =Pattern.compile(str);
		Matcher m =p.matcher(name);
      /*  if (!name.matches("[\u4e00-\u9fa5]{2,4}")) {
            System.out.println("只能输入2到4个汉字");
            return false;
        }else return true;*/
		return m.matches();
    }
	
	//密码由数字和字母组成，并且要同时含有数字和字母  且6位到15位
	public boolean isPassWord(String password){
		String str= "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,15}$";
		Pattern p=Pattern.compile(str);
		Matcher m=p.matcher(password);
		return m.matches();
	}
	

	//检查输入的账号是否正确
	/**
	 * 学生账号限制
	 */
	public boolean isStudentNumber(String student){
		String str = "S1[0-9]{3}";
		//还有一种写法：String str="S1[0-9][0-9][0-9]"
		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(student);
		return m.matches();
	}
	/**
	 * 教师账号限制
	 */
	public boolean isTeacherNumber(String teacher){
		String str = "T1[0-9]{3}";
		//还有一种写法：String str="S1[0-9][0-9][0-9]"

		Pattern p = Pattern.compile(str);
		Matcher m = p.matcher(teacher);
		return m.matches();
	}
	


	/**
	 * 随机生成验证码
	 * @return
	 */
	public String RandomAuthCode(String Number) {
		Random rd1=new Random();
		StringBuffer sbf=new StringBuffer();
		
		//生成随机验证码
		
		int count=0;
		int flag=0;
		
		while(count<=5){
			flag =rd1.nextInt(2); //随机判断是生成数字还是字母
			if(flag==0){  //如果是0，则生成数字
			 sbf.append(  rd1.nextInt(10) );  //[0,10)
			}else{
			 sbf.append(  (char)(rd1.nextInt(26)+97) );  //大写字母从65开始  小写字母97开始 
			}
			count++;
		
		}
			Number=sbf.toString();
			return Number;
	}
	

	
	
	
}
