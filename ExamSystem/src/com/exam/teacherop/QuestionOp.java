package com.exam.teacherop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.exam.utils.AllSelect;
import com.exam.utils.QuestionInfo;

import org.eclipse.swt.custom.TableCursor;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 试卷操作
 * @author Administrator
 *
 */
public class QuestionOp extends Composite {
	private AllSelect as=new AllSelect();
	private QuestionInfo qi=new QuestionInfo();
	private Table table;

	private Button button;
	private List<Button> btns=new ArrayList<Button>();
	private TableItem[] tis;

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public QuestionOp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		final Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION |SWT.MULTI);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		TableColumn tableColumn_9 = new TableColumn(table, SWT.NONE);
		tableColumn_9.setWidth(100);
		tableColumn_9.setText("\u9009\u62E9");

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(116);
		tableColumn.setText("\u73ED\u7EA7\u7F16\u53F7");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u6559\u5E08\u7F16\u53F7");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u8BD5\u5377\u7F16\u53F7");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u8BD5\u5377\u540D\u79F0");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u5B66\u671F");

		TableColumn tableColumn_8 = new TableColumn(table, SWT.CENTER);
		tableColumn_8.setWidth(100);
		tableColumn_8.setText("\u9898\u76EE\u6570\u91CF");

		TableColumn tableColumn_6 = new TableColumn(table, SWT.CENTER);
		tableColumn_6.setWidth(100);
		tableColumn_6.setText("\u5F00\u8003\u65F6\u95F4");

		TableColumn tableColumn_7 = new TableColumn(table, SWT.CENTER);
		tableColumn_7.setWidth(100);
		tableColumn_7.setText("\u8003\u8BD5\u6301\u7EED\u65F6\u95F4");

		TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("\u8BD5\u5377\u72B6\u6001");

		TableCursor tableCursor = new TableCursor(table, SWT.NONE);

		Menu menu = new Menu(tableCursor);
		tableCursor.setMenu(menu);

		MenuItem menuItem = new MenuItem(menu, SWT.NONE);
		menuItem.setText("\u5220\u9664");

		new MenuItem(menu, SWT.SEPARATOR);

		final MenuItem menuItem_1 = new MenuItem(menu, SWT.NONE);
		menuItem_1.setText("\u5F00\u8003");
		menuItem_1.setEnabled(false);


		final MenuItem menuItem_2 = new MenuItem(menu, SWT.NONE);
		menuItem_2.setText("\u9605\u5377");
		menuItem_2.setEnabled(false);

		new MenuItem(menu, SWT.SEPARATOR);

		MenuItem menuItem_3 = new MenuItem(menu, SWT.NONE);
		menuItem_3.setText("\u5237\u65B0");

		showTable();
		as.setValues(table, tableColumn_9, btns);
		as.tableOp();
		as.columnOp();

		//自动适应列宽
		composite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				int width=composite.getSize().x;
				width=(width-50)/9;
				TableColumn[] cols=table.getColumns();
				cols[0].setWidth(49);
				for(int i=1;i<cols.length;i++){
					cols[i].setWidth(width);
				}
			}
		});

		//激活按钮
		tableCursor.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				tis=table.getSelection();
				for(TableItem t:tis){
					if("未考".equals(qi.findQexists( t.getText(3).trim() ))){ //激活开考Item
						menuItem_1.setEnabled(true);
						menuItem_2.setEnabled(false);
					} else{
						menuItem_1.setEnabled(false);
						menuItem_2.setEnabled(true);
					}
				}

			}
		});

		//删除
		menuItem.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				for(TableItem t:tis){
					qi.delTest(t.getText(3).trim());
				}

				int[] indexs=table.getSelectionIndices(); //获取选中行的索引
				table.remove(indexs); //根据索引删除所在行

				List<Button> bts=new ArrayList<Button>();
				for(int i:indexs){
					bts.add(btns.get(i));
				}

				for(Button b:bts){
					btns.remove(b);
					b.dispose();
				}
				as.setCheckbox();
			}
		});

		//开考
		menuItem_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				for(TableItem t:tis){
					if("未考".equals(qi.findQexists(t.getText(3).trim()))){
						qi.startTest(t.getText(3).trim());
						qi.startTestNextOp(t.getText(3).trim());
						shutTest(t.getText(3).trim(),Integer.valueOf(t.getText(8).trim()));
					}
				}
				showTable2();
			}
		});

		//阅卷
		menuItem_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				TableItem[] tis=table.getSelection();
				for(TableItem t:tis){
					if(!"未考".equals(qi.findQexists(t.getText(3).trim()))){
						qi.readTest(t.getText(3).trim());
					}
				}
				showTable2();
			}
		});

		//刷新
		menuItem_3.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				showTable();
			}
		});

		//刷新
		table.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent e) {
				if (e.button == 3) {
					Menu menu = new Menu(table);
					table.setMenu(menu);
					MenuItem delItem = new MenuItem(menu, SWT.PUSH);
					delItem.setText("刷新");
					delItem.addListener(SWT.Selection, new Listener() {
						public void handleEvent(Event event) {
							showTable();
						}
					});
				}
			}
		});
	}
	
	/**
	 * 在经过一定时间后就试卷状态改为未阅、已考		即阻止迟到的考生考试
	 * @param qid 试卷编号
	 * @param minute 考试时长
	 */
	public void shutTest(final String qid,int minute) {  
		Timer timer = new Timer();  
		timer.schedule(new TimerTask() {
			public void run() {
				TeacherOp.display.syncExec( new Runnable() {
					public void run() {
						qi.shutTest(qid);
						showTable2();
					}
				});
			}  
		}, 1000*30); //在开考后经过考试时长的1/3时则阻止考试开始
	}

	/**
	 * 填入信息，显示表格，增加复选框
	 */
	public void showTable(){
		table.removeAll();
		List<Map<String, Object>> list=qi.findAllTest();
		TableItem ti;
		TableEditor te;
		for(int i=0;i<list.size();i++){
			ti=new TableItem(table, SWT.NONE);
			ti.setText(new String[]{"",(String) list.get(i).get("CID"),(String) list.get(i).get("TID"),(String) list.get(i).get("QID"),(String) list.get(i).get("QNAME"),
					(String) list.get(i).get("QTERM"),(String) list.get(i).get("QNUMBER"),(String) list.get(i).get("STIME"),(String) list.get(i).get("DURATION"),(String) list.get(i).get("QEXISTS")});

			te=new TableEditor(table);
			button=new Button(table,SWT.CHECK);
			button.setData("id",i);
			te.grabHorizontal=true; //攫取水平布局
			te.setEditor(button, ti, 0);
			button.addSelectionListener(as.new MySelectionAdapter(button));
			btns.add(button);
		}
	}

	/**
	 * 填入信息，显示表格
	 */
	public void showTable2(){
		table.removeAll();
		List<Map<String, Object>> list=qi.findAllTest();
		TableItem ti;
		for(int i=0;i<list.size();i++){
			ti=new TableItem(table, SWT.NONE);
			ti.setText(new String[]{"",(String) list.get(i).get("CID"),(String) list.get(i).get("TID"),(String) list.get(i).get("QID"),(String) list.get(i).get("QNAME"),
					(String) list.get(i).get("QTERM"),(String) list.get(i).get("QNUMBER"),(String) list.get(i).get("STIME"),(String) list.get(i).get("DURATION"),(String) list.get(i).get("QEXISTS")});
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
