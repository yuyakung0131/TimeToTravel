package com.member.errorMethod;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ErrorMethod {
	public boolean isContainChinese(String str){
	    Pattern p = Pattern.compile("[\u4E00-\u9FA5|\\！|\\，|\\。|\\（|\\）|\\《|\\》|\\“|\\”|\\？|\\：|\\；|\\【|\\】]");
	    Matcher m = p.matcher(str);
	    return m.find();
	}
	
	 public  boolean isChineseStr(String str){  
	        Pattern pattern = Pattern.compile("[\u4e00-\u9fa5]");  
	        char c[] = str.toCharArray();  
	        for(int i=0;i<c.length;i++){  
	            Matcher matcher = pattern.matcher(String.valueOf(c[i]));  
	            if(!matcher.matches()){  
	                return false;  
	            }  
	        }  
	        return true;  
	    }  

	 public  boolean isEnglishStr(String str){  
	        Pattern pattern = Pattern.compile("[a-zA-Z]");  
	        char c[] = str.toCharArray();  
	        for(int i=0;i<c.length;i++){  
	            Matcher matcher = pattern.matcher(String.valueOf(c[i]));  
	            if(!matcher.matches()){  
	                return false;  
	            }  
	        }  
	        return true;  
	    }  
}
