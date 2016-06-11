package com.yc.hdmedia.web.action;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.HuitieBean;
import com.yc.hdmedia.entity.TieZiBean;
import com.yc.hdmedia.service.HuiTieService;
import com.yc.hdmedia.service.TieZiService;


@Controller("huitiesAction")
public class HuitiesAction implements ModelDriven<HuitieBean>,SessionAware{
	
	@Autowired
	private TieZiService tieZiService;
	@Autowired
	private HuiTieService huitieService;
	private HuitieBean huitieBean;
	private Map<String, Object> session;
	
	
	
	public String findHuitie(){
		String tid=ServletActionContext.getRequest().getParameter("tid"); 
		System.out.println(tid);
		int id=Integer.parseInt(tid);
		session.put("tid", id);
		TieZiBean TZ= tieZiService.findByTids(id);
		session.put("TZ", TZ);
		if(TZ.getTzphoto()!=null){
			String s=TZ.getTzphoto();
			String[] tzphoto=s.split(",");
			session.put("tzphoto", tzphoto);
		}
		HuitieBean ht=huitieService.HuitieCount(id);
		session.put("huitiecount", ht);
		String html;//要写入的HTML
		List<String> list=new ArrayList<String>();
		int pageSize=1;//页数
		int pageNum=ht.getHtcount();//总条数
		if(pageNum==0){//条数为0
			pageSize=0;
		}else if(pageNum<=8){//条数小于12
			pageSize=1;
			html="<input name='pageNo' value="+pageSize+" type='submit'/>";
			list.add(html);
		}else if(pageNum%12>0){ //条数超过12条
			pageSize=pageNum/8+1;
			for(int i=1;i<=pageSize;i++){
				html="<input name='pageNo' value="+i+" type='submit'/>";
				list.add(html);
			}
		}
		session.put("list", list);
		session.put("pageSize", pageSize);
		int pageNo=1;//当前页数
		if(huitieBean.getPageNo()!=0){
			pageNo=huitieBean.getPageNo();
			if(pageNo>pageSize){
				pageNo=pageSize;
			}
			if(pageNo==0){
				pageNo=1;
			}
		}
		session.put("pageNo", pageNo);
		List<HuitieBean> huitieBean=huitieService.findHuitieBean(pageNo, 8, id);
		session.put("HuitieBean", huitieBean);
		return "HuitieSuccess";
	}
	
	
	
	
	public HuitieBean getModel() {
		this.huitieBean=new HuitieBean();
		return huitieBean;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	
	
}
