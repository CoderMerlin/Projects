package com.exam.studentop;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.exam.teacherop.UpdateStuInfo;
import com.exam.utils.ShellUtil;
import com.exam.utils.StuInfo;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 显示所有学生
 * @author Administrator
 *
 */
public class ShowClassStu extends Composite {
	private StuInfo si=new StuInfo();
	private ShellUtil su=new ShellUtil();
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ShowClassStu(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("     \u5B66\u751F\u7167\u7247");
		
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
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u90AE\u7BB1");
		
		showInfo();
		
		table.setSelection(0);
		
		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				if(e.button==3){
					Menu menu=new Menu(table);
					table.setMenu(menu);
					
					final TableItem[] tis=table.getSelection();
					if(tis[0].getText(1).equals(StuOp.stuid)){
						MenuItem update=new MenuItem(menu,SWT.PUSH);
						update.setText("更新信息");
						update.addSelectionListener(new SelectionAdapter(){
							@Override
							public void widgetSelected(SelectionEvent e) {
								UpdateStuInfo us=new UpdateStuInfo();
								us.setValues(new String[]{tis[0].getText(2),tis[0].getText(4),tis[0].getText(5),tis[0].getText(3),tis[0].getText(9),tis[0].getText(6),tis[0].getText(7),tis[0].getText(8)},StuOp.stuid);
								us.open();
								showInfo();
							}
						});
					}
				}
			}
		});

		//列宽自适应
		composite.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				int width=composite.getSize().x;
				width=(width-102)/9;
				TableColumn[] cols=table.getColumns();
				for(int i=1;i<cols.length;i++){
					cols[i].setWidth(width);
				}
			}
		});
	}
	
	public void showInfo(){
			table.removeAll();
			List<Map<String,Object>> list=si.findClassStu(StuOp.stuid);
			TableItem ti;
			for(Map<String,Object> map:list){
				ti=new TableItem(table,SWT.NONE);
				
				if(map.get("SPHOTO")==null){
					ti.setImage(SWTResourceManager.getImage(ShowClassStu.class, "/images/2.gif"));
				}else{
					ti.setImage(su.getImage((byte[])map.get("SPHOTO"), 100, 100));
				}
				ti.setText(new String[]{"",(String) map.get("STUID"),(String) map.get("SNAME"),(String) map.get("CID"),(String) map.get("SSEX"),(String) map.get("SAGE"),(String) map.get("STEL"),(String) map.get("SADDR"),(String) map.get("SQQ"),(String) map.get("SEMAIL")});
			}
	}


}
