package com.exam.studentop;

import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.exam.utils.StuInfo;
import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class Score extends Composite {
	private StuInfo si=new StuInfo();
	private Table table;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public Score(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("\u8BD5\u5377\u7F16\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(99);
		tableColumn_1.setText("\u8BD5\u5377\u540D\u79F0");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u5F00\u8003\u65F6\u95F4");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u8BE5\u95E8\u6210\u7EE9");
		
		TableCursor tableCursor = new TableCursor(table, SWT.NONE);
		
		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);
		
		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("\u67E5\u770B\u8BE6\u7EC6");

		showTable();
		
		//自动改变列宽
		composite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				int width=composite.getSize().x;
				width=width/4;
				TableColumn[] tcs=table.getColumns();
				for(int i=0;i<tcs.length;i++){
					tcs[i].setWidth(width);
				}
			}
		});
		
		//刷新
		table.addMouseListener(new MouseAdapter(){
			@Override
			public void mouseDown(MouseEvent e) {
				if(e.button==3){
					Menu menu=new Menu(table);
					table.setMenu(menu);
					
					MenuItem refersh=new MenuItem(menu, SWT.PUSH);
					refersh.setText("刷新");
					
					refersh.addSelectionListener(new SelectionAdapter(){
						@Override
						public void widgetSelected(SelectionEvent e) {
							showTable();
						}
					});
				}
			}
		});
		
		//查看详细
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//TODO 
				//System.out.println(table.getSelection()[0].getText(3));
				SeeScore ss=new SeeScore();
				ss.setValues(table.getSelection()[0].getText(0), StuOp.stuid , table.getSelection()[0].getText(3));
				ss.open();
			}
		});
	}
	
	/**
	 * 填入信息，显示表格
	 */
	@SuppressWarnings("unchecked")
	public void showTable(){
		table.removeAll();
		List<Map<String, Object>> list=si.findExamResult(StuOp.stuid);
		TableItem ti;
		for(Map map:list){
			ti=new TableItem(table, SWT.NONE);
			ti.setText(new String[]{(String) map.get("QID"),(String) map.get("QNAME"),(String) map.get("STIME"),(String) map.get("ESCORE")});
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
