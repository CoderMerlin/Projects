package com.yc.hdmedia.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;
import com.yc.hdmedia.utils.HDmediaData;

public class LoginInterceptor extends MethodFilterInterceptor {

	


	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1959146806672781463L;

	@Override
	protected String doIntercept(ActionInvocation invacation) throws Exception {
		Map<String,Object> session=ActionContext.getContext().getSession();//取到Session封装对象
		Object obj=session.get(HDmediaData.LOGIN_USER);
		if(obj==null){
			session.put(HDmediaData.ERROR_MSG, "请先登录");
			return "login";
		}
		return invacation.invoke();
	}

}
