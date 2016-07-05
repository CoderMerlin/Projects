package com.exam.teacherop;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;

import com.exam.register.TeacherRegister;
import com.exam.utils.ShellUtil;
import com.exam.utils.TeacherInfo;
import com.swtdesigner.SWTResourceManager;

/**
 * 显示所有教师信息
 * @author Administrator
 *
 */
public class ShowAllTeacher extends Composite {
	private TeacherInfo ti=new TeacherInfo();
	private ShellUtil su=new ShellUtil();
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ShowAllTeacher(Composite parent, int style) {
		super(parent, SWT.NONE);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setLinesVisible(true);
		table.setHeaderVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("        \u7167\u7247");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u7F16\u53F7");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u59D3\u540D");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u6027\u522B");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u5730\u5740");
		
		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("\u8054\u7CFB\u65B9\u5F0F");
		
		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("\u90AE\u7BB1");
		
		showTable();
		
		//自动适应列宽
		composite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				int width=composite.getSize().x;
				width=(width-102)/6;
				TableColumn[] cols=table.getColumns();
				for(int i=1;i<cols.length;i++){
					cols[i].setWidth(width);
				}
			}
		});
		
		//更新教师信息，只能操作自己的数据
		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				if(e.button==3){
					Menu menu=new Menu(table);
					table.setMenu(menu);
					
					final TableItem[] tis=table.getSelection();
					if(tis[0].getText(1).equals(TeacherOp.tid)){
						MenuItem update=new MenuItem(menu, SWT.PUSH);
						update.setText("更新信息");
						
						update.addSelectionListener(new SelectionAdapter(){
							@Override
							public void widgetSelected(SelectionEvent e) {
								UpdateTeaInfo uti=new UpdateTeaInfo();
								uti.setValues(new String[]{tis[0].getText(2),tis[0].getText(3),tis[0].getText(6),tis[0].getText(5),tis[0].getText(4)});
								uti.open();
								showTable();
							}
						});
					}
				}
			}
		});
	}
	
	/**
	 * 填入信息，显示表格
	 */
	public void showTable(){
		table.removeAll();
		List<Map<String, Object>> list=ti.findAll();
		TableItem ti;
		for(Map<String, Object> map:list){
			ti=new TableItem(table, SWT.NONE);
			if(map.get("TPHOTO")==null){
				ti.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/2.gif"));
			} else{
				ti.setImage( su.getImage((byte[]) map.get("TPHOTO"), 100, 100) );
			}
			ti.setText(new String[]{"",(String) map.get("TID"),(String) map.get("TNAME"),(String) map.get("TSEX"),(String) map.get("TADDR"),(String) map.get("TTEL"),(String) map.get("TEMAIL")});
		}
	}


	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
