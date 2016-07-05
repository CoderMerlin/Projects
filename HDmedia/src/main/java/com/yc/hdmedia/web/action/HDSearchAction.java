package com.yc.hdmedia.web.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.HDSearch;
import com.yc.hdmedia.service.HDSearchService;
import com.yc.hdmedia.utils.SearchUtil;

@Controller("hDSearchAction")
public class HDSearchAction implements ModelDriven<HDSearch>, SessionAware{
	
	private HDSearch hDSearch;
	private Map<String, Object> session;
	@Autowired
	private HDSearchService hDSearchService;
	
	private Map<String,Object> DataMap=new HashMap<String,Object>();

	

	/**
	 * 根据内容搜索相关帖子
	 * @return
	 */
	public String getTeiZiByContent(){
		DataMap.clear();
		String keyword=hDSearch.getSrchtxt();
		List<HDSearch> searchs=hDSearchService.getTeiZiByContent(hDSearch.getSrchtxt());
		//返回页面数据
		LogManager.getLogger().debug("查询到的帖子信息"+searchs);
		SearchUtil su=new SearchUtil();
		List<String> keyWordBefore=new ArrayList<String>();
		List<String> keyWordAfter=new ArrayList<String>();
		
		for (int i = 0; i < searchs.size(); i++) {
			String content=searchs.get(i).getTztext();
			keyWordBefore.add(su.keyWordBefore(keyword, content));
		}
		for (int i = 0; i < searchs.size(); i++) {
			String content=searchs.get(i).getTztext();
			keyWordAfter.add(su.keyWordAfter(keyword, content));
		}
		
		System.out.println("获取到的关键内容之后"+keyWordAfter);
		List<Map> seach2=new ArrayList<Map>();
		Map map = new HashMap();
		for (int i = 0; i < searchs.size(); i++) {
			map.put("ltid", searchs.get(i).getLtid());
			map.put("ltname", searchs.get(i).getLtname());
			map.put("tzclick", searchs.get(i).getTzclick());
			map.put("tzname", searchs.get(i).getTzname());
			map.put("keyWordBefore", keyWordBefore.get(i));
			map.put("keyWordAfter", keyWordAfter.get(i));
			map.put("tztime", searchs.get(i).getTztime());
			map.put("yhname", searchs.get(i).getYhname());
			map.put("keyword",keyword);
		}
		seach2.add(map);
		if(searchs!=null){
			session.put("searchs", seach2);
			return "searchSuccess";
		}
		return "fail";
	}
	

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	@Override
	public HDSearch getModel() {
		return hDSearch=new HDSearch();
	}

}
