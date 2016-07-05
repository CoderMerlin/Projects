package com.exam.studentop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;

import com.exam.utils.QuestionInfo;
import com.swtdesigner.SWTResourceManager;

import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * 开始考试
 * @author Administrator
 *
 */
public class ChooseExam extends Composite {

	private Table table;
	private TableItem ti;
	List<Button> btns=new ArrayList<Button>();
	private QuestionInfo qi=new QuestionInfo();
	


	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ChooseExam(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final Composite composite = new Composite(this, SWT.NONE);
		composite.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		
		Label label = new Label(composite, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		label.setFont(SWTResourceManager.getFont("方正综艺简体", 14, SWT.NORMAL));
		label.setBounds(194, 68, 126, 34);
		label.setText("\u6E29\u99A8\u63D0\u793A\uFF1A");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("方正综艺简体", 9, SWT.NORMAL));
		label_1.setBounds(194, 108, 800, 27);
		label_1.setText("\u5BF9\u4E8E\u5DF2\u7ECF\u8003\u8FC7\u6216\u8005\u4E0D\u4E3A\u5F00\u8003\u7684\u8BD5\u5377\u60A8\u5C06\u65E0\u6CD5\u770B\u5230\u3002\u5982\u679C\u60A8\u5E76\u6CA1\u6709\u8003\u8FC7\u8FD9\u5957\u8BD5\u5377\u4F46\u770B\u4E0D\u5230\u8BD5\u5377\uFF0C\u8BF7\u60A8\u53CA\u65F6\u8054\u7CFB\u8001\u5E08\u3002");
		
		table = new Table(composite, SWT.BORDER | SWT.FULL_SELECTION);
		table.setBounds(194, 159, 800, 235);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(99);
		tableColumn_1.setText("\u8BD5\u5377\u7F16\u53F7");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(101);
		tableColumn_2.setText("\u8BD5\u5377\u540D\u79F0");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(88);
		tableColumn_3.setText("\u73ED\u7EA7");
		
		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(193);
		tableColumn_4.setText("\u8003\u8BD5\u65E5\u671F");
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("\u8003\u8BD5\u65F6\u95F4");
		
		Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("方正综艺简体", 14, SWT.NORMAL));
		button.setBounds(868, 428, 126, 44);
		button.setText("\u786E\u8BA4\u5F00\u8003");
		
		showInfo();
		
		//自动改变列宽
		composite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				int width=table.getSize().x;
				width=width/5;
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
							showInfo();
						}
					});
				}
			}
		});
		
		/**
		 * 对开始考试这个按钮进行监听
		 */
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(table.getSelection()!=null && table.getSelection().length>0){
					StartExam se=new StartExam();
					se.setValues(StuOp.stuid, table.getSelection()[0].getText(0), Integer.valueOf( table.getSelection()[0].getText(4) ));
					se.open();
					showInfo();
				}
			}
		});
	}
	
	//显示可以考试的试卷
	public void showInfo(){
		table.removeAll();
		List<Map<String,Object>> list=qi.findStuTest(StuOp.stuid);
		for(Map<String,Object> map:list){
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{(String) map.get("QID"),(String) map.get("QNAME"),(String) map.get("CID"),(String) map.get("STIME"),(String) map.get("DURATION")});
		}
}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
