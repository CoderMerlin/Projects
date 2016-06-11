package com.yc.hdmedia.utils;

import java.util.Random;

/**
 * 验证码
 * @author 
 */

public class VerifyCode {
	private  Random rd=new Random();
	public String code(){
		char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J',         
	            'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W',         
	            'X', 'Y', 'Z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		StringBuffer sbf=new StringBuffer();
		for(int i=0;i<4;i++){
			sbf.append(codeSequence[rd.nextInt(codeSequence.length)]);
		}
		return String.valueOf(sbf);
	}
}
