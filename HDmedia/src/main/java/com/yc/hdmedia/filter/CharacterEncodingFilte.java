package com.yc.hdmedia.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CharacterEncodingFilte implements Filter {
private String encoding="utf-8";
	
	public void destroy() {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) req;
		HttpServletResponse response=(HttpServletResponse) resp;
		
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		
		response.setContentType("text/html;charset="+encoding);
		
		//修改完后，调用下一个过滤器
		chain.doFilter(req, resp);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		if(filterConfig.getInitParameter("encoding")!=null){
			encoding=filterConfig.getInitParameter("encoding");
		}
	}
}
