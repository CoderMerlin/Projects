package com.yc.hdmedia.filter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CheckGuanLiLogin implements Filter {
	private String errorPage="fail.jsp"; //错误页面，即如果校验没有通过，则跳到该页面
	
	
	@Override
	public void destroy() {
		System.out.println("销毁管理员登录过滤器!");
	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,FilterChain filterChain) 
			throws IOException, ServletException {
		System.out.println("正在使用管理员登录过滤器!");

		//检验该用户是否已经登陆
		HttpServletRequest request=(HttpServletRequest) servletRequest;
		HttpServletResponse response=(HttpServletResponse) servletResponse;
		
		HttpSession session=request.getSession();
		if(session.getAttribute("loginGuanLi")==null || session.getAttribute("loginGuanLi")==""){ //说明没有登录
			PrintWriter out=response.getWriter();
			//获取基址路径，即到WebRoot下
			String bassPath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
			out.print("<script>alert('请先登录...');location.href='"+bassPath+errorPage+"';</script>");
			out.flush();
			out.close();
		} else{ //说明已经登录，则调用下一个过滤器过滤
			filterChain.doFilter(servletRequest, servletResponse);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		System.out.println("生成检查管理员登录过滤器!");
		String temp=filterConfig.getInitParameter("errorPage");
		if(temp!=null){ //说明配置了初始化页面信息
			errorPage=temp;
		}
	}

}
