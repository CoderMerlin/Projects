package com.yc.hdmedia.interceptor;

import java.util.Map;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.MethodFilterInterceptor;

public class LoginInterceptor extends MethodFilterInterceptor{
	@Override
	protected String doIntercept(ActionInvocation invocation) throws Exception {
		Map<String,Object> session=ActionContext.getContext().getSession(); //ȡ��session�ķ�װ����
		Object obj=session.get("user");
		if(obj==null){
			session.put("errorMsg", "���½��,�ٽ��в���!!!");
			return "index";
		}
		return invocation.invoke();
	}
}
