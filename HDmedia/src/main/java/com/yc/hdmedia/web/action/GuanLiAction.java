package com.yc.hdmedia.web.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.GuanLi;
import com.yc.hdmedia.service.GuanLiService;
import com.yc.hdmedia.utils.HDmediaData;
import com.yc.hdmedia.utils.PageUtil;

@Controller("guanLiAction")
public class GuanLiAction implements ModelDriven<GuanLi>,SessionAware {
	@Autowired
	private GuanLiService guanLiService;
	private Map<String, Object> session;
	private GuanLi guanLi;
	private int page;
	private int rows;
	private int numb;
	private String toglids;
	private String Path="";
	
	public String getToglids() {
		return toglids;
	}
	public void setToglids(String toglids) {
		this.toglids = toglids;
	}
	public int getNumb() {
		return numb;
	}
	public void setNumb(int numb) {
		this.numb = numb;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public void setGuanLi(GuanLi guanLi) {
		this.guanLi = guanLi;
	}

	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}

	//管理员登陆操作
		public String login(){
			GuanLi guanlis=guanLiService.login(guanLi);
			//能登录
			if(guanlis!=null){
				//查询哪个管理员登录的并加登录时间
				String glTime;
				Date currDate = Calendar.getInstance().getTime();
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				glTime= sdf.format(currDate);
				System.out.println("管理的时间"+glTime);
				int result=guanLiService.updateGuanLiLoginTime(guanlis.getGlid(),glTime);
				session.put(HDmediaData.LOGIN_GUANLI,guanLi);
				return "guanLiIndex";
			}
			session.put(HDmediaData.ERROR_MSG, "管理员名或密码错误!");
			return "guanli";
		}
		
	//管理员登陆注销
		public String loginOut(){
			session.put("loginGuanLi",null);
			return "guanli";
		}
	
	/**
	 * 查询所有的管理员
	 * @author HM
下午9:43:01
	 * @return
	 */
	public String getAllGuanLi(){
		DataMap.clear(); 
		List<GuanLi> guanLis=guanLiService.findAllGuanLis(page,rows);
		if(guanLis!=null){
			for (GuanLi guanLi : guanLis) {
				String glindentity=guanLi.getGlindentity();
				String glidents=glindentity.substring(0,3)+"*****"+glindentity.substring(11,glindentity.length());
				guanLi.setGlindentity(glidents);
			}
			DataMap.put("total",guanLiService.total());
			DataMap.put("rows", guanLis);
			return "success";
		}
		return "fail";
	}
	
	
	
	public String addGuanLi(){
		int result=guanLiService.addGuanLi(guanLi);
		if(result>0){
			DataMap.put("result", result);
			return "success";
		}
		return "fail";
	}
	public String getGuanLiById(){
		int glid=guanLi.getGlid();
		GuanLi gl=guanLiService.getGuanLiById(glid);
		if(gl!=null){
			DataMap.put("guanLi", gl);
			return "success";
		}
		return "fail";
	}
	public String updateGuanLi(){
		int result=guanLiService.updateGuanLiInfo(guanLi);
		if(result>0){
			DataMap.put("result", result);
			return "success";
		}
		return "fail";
	}
	public String deleteGuanLiById(){
		String gls=ServletActionContext.getRequest().getParameter("glids");
		String[] glis=gls.split(",");
		int[] glids=new int[glis.length];     
        for (int i=0; i<glids.length; i++) {
        	glids[i] = Integer.parseInt(glis[i]);
        }
        int result=guanLiService.del(glids);
		if(result>0){
			DataMap.put("delId", result);
			return "success";
		}
		return "fail";
	}
	
	
	/**
	 * 导出管理员信息
	 */
	public String oracleGlInfoToExcel(){
		DataMap.clear();
		String fileName = "";  
		String[] title = {"管理员id","管理员名成","密码","性别","邮箱","手机号","真实姓名","身份证","最后登录时间","状态","预留1","预留2"};
		List<GuanLi> guanLis =guanLiService.getoracleGlInfoToExcel(toglids);
		
		//创建Excel工作簿
				HSSFWorkbook workbook = new HSSFWorkbook();
				//创建一个工作表sheet
				HSSFSheet sheet = workbook.createSheet();
				//创建第一行
				Row row = sheet.createRow(0);
				HSSFCell cell = null;
				
				//插入第一行数据 id,name,sex
				for(int i=0;i<title.length;i++){
					cell = (HSSFCell) row.createCell(i);
					cell.setCellValue(title[i]);
				}
				
				//追加数据
				for(int i=1;i<=numb+1;i++){
					HSSFRow nextrow = sheet.createRow(i);
					HSSFCell cell2 = nextrow.createCell(0);
					cell2.setCellValue(String.valueOf(guanLis.get(i-1).getGlid()));
					cell2 = nextrow.createCell(1);
					cell2.setCellValue( String.valueOf( guanLis.get(i-1).getGlname()));
					cell2 = nextrow.createCell(2);
					cell2.setCellValue(String.valueOf(guanLis.get(i-1).getGlpwd()));
					
					cell2 = nextrow.createCell(3);
					cell2.setCellValue( String.valueOf(guanLis.get(i-1).getGlsex()));
					
					cell2 = nextrow.createCell(4);
					cell2.setCellValue(String.valueOf( guanLis.get(i-1).getGlemail()));
					
					cell2 = nextrow.createCell(5);
					cell2.setCellValue(String.valueOf( guanLis.get(i-1).getGlphone()));
					
					cell2 = nextrow.createCell(6);
					cell2.setCellValue(String.valueOf( guanLis.get(i-1).getGlphone()));
					
					cell2 = nextrow.createCell(7);
					cell2.setCellValue(String.valueOf( guanLis.get(i-1).getGlindentity()));
					
					cell2 = nextrow.createCell(8);
					cell2.setCellValue(String.valueOf( guanLis.get(i-1).getGlzhtime()));
					cell2 = nextrow.createCell(9);
					cell2.setCellValue(String.valueOf( guanLis.get(i-1).getGlstatus()));
					cell2 = nextrow.createCell(10);
					cell2.setCellValue(String.valueOf( guanLis.get(i-1).getGlyl1()));
					cell2 = nextrow.createCell(11);
					cell2.setCellValue(String.valueOf( guanLis.get(i-1).getGlyl2()));
					
				}
				
				SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");
				Date d=null;
					//创建一个文件
					File file = null;
					try {
						d=new Date();
						fileName="f:/管理员花名册"+sdf.format(d)+".xls";
						Path=fileName;
						System.out.println(Path);
						file=new File(Path);
						file.createNewFile();
						//将Excel内容存盘
						FileOutputStream stream = FileUtils.openOutputStream(file);
						workbook.write(stream);
						stream.close();
					} catch (IOException e) {
						e.printStackTrace();
					}

		if(guanLis!=null){
			DataMap.put("result", guanLis);
			return "success";
		}
		return "fail";
	}
	
	@Override
	public GuanLi getModel() {
		return guanLi=new GuanLi();
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

}
