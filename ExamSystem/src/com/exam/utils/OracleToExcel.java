package com.exam.utils;

import jxl.*; 
import jxl.format.UnderlineStyle;
import jxl.write.*; 
import jxl.write.biff.RowsExceededException;

import java.io.*; 
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;


/**
 * ������д��excel
 * @author Administrator
 *
 */
public class OracleToExcel {
	private ClassInfo ci=new ClassInfo();
	
	private String path="";
	private String cid="";
	private Shell shell;
	
	/**
	 * @param path �ļ�·��
	 * @param cid �༶���
	 * @param shell ���
	 */
	public OracleToExcel(String path, String cid, Shell shell){
		this.path=path;
		this.cid=cid;
		this.shell=shell;
	}
	
	public void start(){ 

		try {
			File fl = new File(path);
			fl.createNewFile();
			OutputStream os = new FileOutputStream(fl); 
			writeExcel(os);
			
			MessageBox mgb=new MessageBox(shell, SWT.ICON_INFORMATION);
			mgb.setText("�����ɹ�");
			mgb.setMessage(ci.findCName(cid)+"�����ᵼ���ɹ�");
			mgb.open();
		} catch (Exception e) {
			MessageBox mgb=new MessageBox(shell, SWT.ICON_ERROR);
			mgb.setText("����ʧ��");
			mgb.setMessage(ci.findCName(cid)+"�����ᵼ��ʧ��");
			mgb.open();
			e.printStackTrace();
		}
	} 
	
	public void writeExcel(OutputStream os) throws IOException, RowsExceededException, WriteException{ 
			WritableWorkbook wwb = Workbook.createWorkbook(os); 
			
			WritableSheet ws = wwb.createSheet("Sheet1",0); //����Excel������ ָ�����ƺ�λ�� 
			
			WritableFont wfc = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD,false,UnderlineStyle.NO_UNDERLINE,jxl.format.Colour.RED); 
			WritableCellFormat wcfFC = new WritableCellFormat(wfc);
			
			ws.addCell( new Label(0,0,"ѧ��",wcfFC) );
			ws.addCell( new Label(1,0,"����",wcfFC) );
			ws.addCell( new Label(2,0,"���ڰ༶",wcfFC) );
			ws.addCell( new Label(3,0,"�Ա�",wcfFC) );
			ws.addCell( new Label(4,0,"����",wcfFC) );
			ws.addCell( new Label(5,0,"��ַ",wcfFC) );
			ws.addCell( new Label(6,0,"QQ",wcfFC) );
			ws.addCell( new Label(7,0,"����",wcfFC) );

			List<Map<String, Object>> list=ci.findClassStuInfo(cid);
			
			Label label0,label1,label2,label3,label4,label5,label6,label7;
			for(int i=0;i<ci.getNum(cid);i++){
				label0=new Label(0, (i+1), (String) list.get(i).get("STUID"));
				ws.addCell(label0);
				
				label1=new Label(1, (i+1), (String) list.get(i).get("SNAME"));
				ws.addCell(label1);
				
				label2=new Label(2, (i+1), ci.findCName(cid));
				ws.addCell(label2);
				
				label3=new Label(3, (i+1), (String) list.get(i).get("SSEX"));
				ws.addCell(label3);
				
				label4=new Label(4, (i+1), (String) list.get(i).get("SAGE"));
				ws.addCell(label4);
				
				label5=new Label(5, (i+1), (String) list.get(i).get("SADDR"));
				ws.addCell(label5);
				
				label6=new Label(6, (i+1), (String) list.get(i).get("SQQ"));
				ws.addCell(label6);
				
				label7=new Label(7, (i+1), (String) list.get(i).get("SEMAIL"));
				ws.addCell(label7);
			}
			
			wwb.write(); //д�빤���� 
			wwb.close(); 
	} 
} 
