package com.yc.hdmedia.web.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.GongGao;
import com.yc.hdmedia.service.GongGaoService;

@Controller("gongGaoAction")
public class GongGaoAction implements ModelDriven<GongGao>, SessionAware {

	@Autowired
	private GongGaoService gongGaoService;
	private GongGao gonggao;
	private int page;
	private int rows;
	private File[] ggyl1;
	private String[] ggyl1FileName;
	private String[] ggyl1ContentType;
	private Map<String, Object> session;

	public void setGonggao(GongGao gonggao) {
		this.gonggao = gonggao;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	private Map<String, Object> DataMap = new HashMap<String, Object>();

	public Map<String, Object> getDataMap() {
		return DataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}

	// Ìí¼Ó
	public String add() {
		LogManager.getLogger().debug("Ìí¼Ó...");
		System.out.println(gonggao);
		System.out.println(ggyl1+"@@@"+ggyl1FileName[0]);
		int result = gongGaoService.addGongGao(gonggao);
		if (result > 0) {
			return "index";
		}
		return "fail";
	}

	public String getAllGongGao() {
		DataMap.clear();
		List<GongGao> gongGaos = gongGaoService.getAllGongGao(page, rows);
		if (gongGaos != null) {
			DataMap.put("total", gongGaoService.total());
			DataMap.put("rows", gongGaos);
			return "success";
		}
		return "fail";
	}

	// ÐÞ¸Ä
	public String update() {
		return null;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public GongGao getModel() {
		return gonggao = new GongGao();
	}

	public File[] getGgyl1() {
		return ggyl1;
	}

	public void setGgyl1(File[] ggyl1) {
		this.ggyl1 = ggyl1;
	}

	public String[] getGgyl1FileName() {
		return ggyl1FileName;
	}

	public void setGgyl1FileName(String[] ggyl1FileName) {
		this.ggyl1FileName = ggyl1FileName;
	}

	public String[] getGgyl1ContenType() {
		return ggyl1ContentType;
	}

	public void setGgyl1ContenType(String[] ggyl1ContenType) {
		this.ggyl1ContentType = ggyl1ContenType;
	}

}
