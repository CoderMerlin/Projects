package com.yc.hdmedia.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchUtil {
	
	//搜索时进行关键字截取之前的部分
	public  String keyWordBefore(String keyword, String content) {
        Pattern pattern = Pattern.compile(".{0,100}" + keyword);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
        	String search=matcher.group();
        	search=search.split(keyword)[0];
        	return search;
        }
		return null;
    }
	
	//搜索时进行关键字截取之后的部分
		public  String keyWordAfter(String keyword, String content) {
	        Pattern pattern = Pattern.compile(keyword+".{0,100}");
	        Matcher matcher = pattern.matcher(content);
	        while (matcher.find()) {
	        	String search=matcher.group();
	        	search=search.split(keyword)[1];
	        	return search;
	        }
	        return null;
	    }

/*	public static void main(String[] args) {
		String keyword="Java";
		String content="sdasdaljjklj 刷卡时惊呆了 Java,saldjlaksjdl asj diwn ioajdk jJavajklasjdiq中爱上多久了数量对";
		SearchUtil su=new SearchUtil();
		System.out.println(su.keyWordAfter(keyword, content));
		System.out.println(su.keyWordBefore(keyword, content));
		
	}*/
}
