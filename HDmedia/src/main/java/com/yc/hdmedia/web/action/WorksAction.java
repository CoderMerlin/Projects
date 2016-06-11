package com.yc.hdmedia.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yc.hdmedia.entity.WorksBean;
import com.yc.hdmedia.service.WorksService;

@Controller("worksAction")
public class WorksAction implements SessionAware{
	@Autowired
	public WorksService worksService;
	private Map<String, Object> session;
	
	public String find(){
		//查询书画页面的信息
		List<WorksBean> works=worksService.findAllWorks();
		List<WorksBean> Work_gdsf=worksService.findWork_gdsf();
		List<WorksBean> Work_gdhh=worksService.findWork_gdhh();
		List<WorksBean> Work_jxdsh=worksService.findWork_jxdsh();
		List<WorksBean> Work_xdsh=worksService.findWork_xdsh();
		List<WorksBean> Work_yhds=worksService.findWork_yhds();
		List<WorksBean> Work_px=worksService.findWork_px();
		List<WorksBean> Work_lb=worksService.findWork_lb();
		session.put("works", works);
		session.put("Work_gdsf", Work_gdsf);
		session.put("Work_gdhh", Work_gdhh);
		session.put("Work_jxdsh", Work_jxdsh);
		session.put("Work_xdsh", Work_xdsh);
		session.put("Work_yhds", Work_yhds);
		session.put("Work_px", Work_px);
		session.put("Work_lb", Work_lb);
		return "worksSuccess";
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	
}
