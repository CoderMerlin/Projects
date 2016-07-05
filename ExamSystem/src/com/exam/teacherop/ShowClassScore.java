package com.exam.teacherop;

import org.eclipse.swt.widgets.Dialog;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.layout.FillLayout;

import com.exam.utils.ClassInfo;

public class ShowClassScore extends Dialog {
	private ClassInfo ci=new ClassInfo();

	protected Object result;
	protected Shell shell;
	private Table table;
	
	private String cid;
	
	public void setValue(String cid){
		this.cid=cid;
	}
	
	/**
	 * Create the dialog.
	 * @param parent
	 * @param style
	 */
	public ShowClassScore(Shell parent, int style) {
		super(parent, style);
		setText("SWT Dialog");
	}

	/**
	 * Open the dialog.
	 * @return the result
	 */
	public Object open() {
		createContents();
		shell.open();
		shell.layout();
		Display display = getParent().getDisplay();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
		return result;
	}

	/**
	 * Create contents of the dialog.
	 */
	private void createContents() {
		shell = new Shell(getParent(), getStyle());
		shell.setSize(700, 432);
		shell.setText(cid+"班成绩一览");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		table = new Table(shell, SWT.BORDER | SWT.FULL_SELECTION);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		TableColumn tableColumn = new TableColumn(table, SWT.NONE);
		tableColumn.setWidth(100);
		tableColumn.setText("      \u5B66\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u59D3\u540D");
		
		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u8BD5\u5377\u540D");
		
		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u6210\u7EE9");
		
		TableItem ti;
		for(int i=0;i<(Integer)ci.findStuEscoreInfo(cid).get(4);i++){ //将信息填入表格
			ti=new TableItem(table,SWT.NONE);
			ti.setText(new String[]{"     "+((String[])ci.findStuEscoreInfo(cid).get(0))[i],((String[])ci.findStuEscoreInfo(cid).get(1))[i],((String[])ci.findStuEscoreInfo(cid).get(2))[i],((String[])ci.findStuEscoreInfo(cid).get(3))[i]});
		}
		
		//自动改变列宽
		shell.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				int width=shell.getSize().x;
				width=(width-100)/3;
				TableColumn[] tcs=table.getColumns();
				for(int i=1;i<tcs.length;i++){
					tcs[i].setWidth(width);
				}
			}
		});
	}

}
