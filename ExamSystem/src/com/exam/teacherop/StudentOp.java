package com.exam.teacherop;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.exam.utils.ClassInfo;
import com.exam.utils.OracleToExcel;
import com.exam.utils.QuestionInfo;
import com.exam.utils.ShellUtil;
import com.exam.utils.StuInfo;
import com.swtdesigner.SWTResourceManager;

import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class StudentOp extends Composite {
	private ClassInfo ci=new ClassInfo();
	private StuInfo si=new StuInfo();
	private QuestionInfo qi=new QuestionInfo();
	private ShellUtil shellUtil=new ShellUtil();
	private Table table;
	
	private CCombo combo;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public StudentOp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("\u5B66\u751F\u7167\u7247");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u5B66\u53F7");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u59D3\u540D");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u6240\u5728\u73ED\u7EA7");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("\u6027\u522B");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("\u5E74\u9F84");
		
		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("\u8054\u7CFB\u65B9\u5F0F");
		
		TableColumn tblclmnQq = new TableColumn(table, SWT.CENTER);
		tblclmnQq.setWidth(100);
		tblclmnQq.setText("\u5730\u5740");
		
		TableColumn tblclmnQq_1 = new TableColumn(table, SWT.CENTER);
		tblclmnQq_1.setWidth(100);
		tblclmnQq_1.setText("QQ");
		
//		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
//		Menu menu = new Menu(tableCursor);
//		tableCursor.setMenu(menu);
		
//		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
//		//menuItem.setImage(SWTResourceManager.getImage(StudentOp.class, "/images/删除.jpg"));
//		menuItem.setText("\u5220\u9664");
//		
//		MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
//		menuItem_1.setText("\u5BFC\u51FA\u8BE5\u73ED\u82B1\u540D\u518C");
//		
//		MenuItem menuItem_2 = new MenuItem(menu, SWT.NONE);
//		menuItem_2.setText("\u663E\u793A\u6240\u6709\u5B66\u751F");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u90AE\u7BB1");
		
		showInfo();
		
		combo = new CCombo(table, SWT.BORDER);
		combo.setEditable(false);
		combo.setVisible(false);
		combo.setItems(ci.findCId());
		combo.setBounds(390, 24, 145, 21);
		
		//显示或者不显示选择班级的下拉框
		tableColumn_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e){ //单击显示
				combo.setVisible(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent e) { //双击隐藏
				combo.setVisible(false);
			}
		});
		
		//选择班级
		combo.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				//System.out.println(combo.getText());
				showInfo(combo.getText());
			}
		});
		
		
		//自动适应列宽
		composite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				int width=composite.getSize().x;
				width=(width-105)/9;
				TableColumn[] cols=table.getColumns();
				cols[0].setWidth(105);
				for(int i=1;i<cols.length;i++){
					cols[i].setWidth(width);
				}
			}
		});
		
		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				if(e.button==3){
					Menu menu=new Menu(table);
					table.setMenu(menu);
					
					MenuItem delItem=new MenuItem(menu,SWT.PUSH);
					delItem.setText("删除");
					delItem.addSelectionListener(new SelectionAdapter(){
						public void widgetSelected(SelectionEvent e) {
							MessageBox mg=new MessageBox(TeacherOp.shell, SWT.ICON_INFORMATION | SWT.YES | SWT.NO);
							mg.setText("警告提示!");
							mg.setMessage("您是否确定删除该生信息?");
							int choose=mg.open();
							if(choose==64){ //如果选择是,删除该生
								TableItem[] tis=table.getSelection();
								for(TableItem t:tis){ //将学生状态变为不存在
									si.delStu(t.getText(1));
								}
								table.remove(table.getSelectionIndices()); //从table中移除该条信息
							} 
						};
					});
					
					MenuItem exportItem=new MenuItem(menu, SWT.PUSH);
					exportItem.setText("导出花名册");
					exportItem.addSelectionListener(new SelectionAdapter(){
						public void widgetSelected(SelectionEvent e) {
							FileDialog fd=new FileDialog(TeacherOp.shell, SWT.SAVE);
							fd.setText("导出花名册");
							fd.setFilterExtensions(new String[]{"*.xls"});
							fd.setFileName(ci.findCName(table.getSelection()[0].getText(3))+"花名册");
							String filePath=fd.open();
							
							if(filePath!=null && !"".equals(filePath)){
								OracleToExcel ote=new OracleToExcel(filePath , table.getSelection()[0].getText(3), TeacherOp.shell);
								ote.start();
							}
						}
					});
					
					MenuItem showAllItm=new MenuItem(menu, SWT.PUSH);
					showAllItm.setText("显示所有学生");
					showAllItm.addSelectionListener(new SelectionAdapter(){
						public void widgetSelected(SelectionEvent e) {
							showInfo();
						}
					});
					
					MenuItem update=new MenuItem(menu, SWT.PUSH);
					update.setText("更新该生信息");
					update.addSelectionListener(new SelectionAdapter(){
						public void widgetSelected(SelectionEvent e) {
							TableItem[] tis=table.getSelection();
							UpdateStuInfo us=new UpdateStuInfo();
							us.setValues(new String[]{tis[0].getText(2),tis[0].getText(4),tis[0].getText(5),tis[0].getText(3),tis[0].getText(9),tis[0].getText(6),tis[0].getText(7),tis[0].getText(8)},tis[0].getText(1));
							us.open();
							showInfo();
						}
					});
					
					MenuItem StuAllScore =new MenuItem(menu,SWT.PUSH);
					StuAllScore.setText("该生的所有考试统计");
					StuAllScore.addSelectionListener(new SelectionAdapter(){
						public void widgetSelected(SelectionEvent e) {
							TableItem[] tis=table.getSelection();
							String stuid=tis[0].getText(1);
							StuScoreJF stuScoreJF=new StuScoreJF(stuid);
							stuScoreJF.open();
						}
					});
					
				}
			}
		});
		
	}
	
	
	/**
	 * 显示所有学生信息按照学号排序
	 */
	public void showInfo(){
			table.removeAll();
			List<Map<String,Object>> list=si.findAllStu();
			TableItem ti;
			for(int i=0;i<list.size();i++){
				ti=new TableItem(table,SWT.NONE);
				
				if(list.get(i).get("SPHOTO")==null){
					ti.setImage(SWTResourceManager.getImage(StudentOp.class, "/images/1.gif"));
				}else{
					ti.setImage(shellUtil.getImage((byte[])list.get(i).get("SPHOTO"), 100, 100));
				}
				ti.setText(new String[]{null,(String) list.get(i).get("STUID"),(String) list.get(i).get("SNAME"),(String) list.get(i).get("CID"),(String) list.get(i).get("SSEX"),(String) list.get(i).get("SAGE"),(String) list.get(i).get("STEL"),(String) list.get(i).get("SADDR"),(String) list.get(i).get("SQQ"),(String) list.get(i).get("SEMAIL")});
			}
	}
	
	/**
	 * 显示改班存在的学生信息按照学号排序
	 */
	public void showInfo(String cid){
			table.removeAll();
			List<Map<String,Object>> list=ci.findClassStuInfo(cid);
			TableItem ti;
			for(int i=0;i<list.size();i++){
				ti=new TableItem(table,SWT.NONE);
				
				if(list.get(i).get("SPHOTO")==null){
					ti.setImage(SWTResourceManager.getImage(StudentOp.class, "/images/1.gif"));
				}else{
					ti.setImage(shellUtil.getImage((byte[])list.get(i).get("SPHOTO"), 100, 100));
				}
				ti.setText(new String[]{null,(String) list.get(i).get("STUID"),(String) list.get(i).get("SNAME"),(String) list.get(i).get("CID"),(String) list.get(i).get("SSEX"),(String) list.get(i).get("SAGE"),(String) list.get(i).get("SADDR"),(String) list.get(i).get("SQQ"),(String) list.get(i).get("SEMAIL")});
			}
	}
	

//	//每个成绩查看中的
//	class MySelectionAdapter extends SelectionAdapter{
//		private MenuItem mi;
//		
//		public MySelectionAdapter(MenuItem mi){
//			this.mi=mi;
//		}
//		public void widgetSelected(SelectionEvent e){
//			
//		}
//	}		

	

}
