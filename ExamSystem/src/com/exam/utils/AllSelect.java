package com.exam.utils;

import java.util.List;


import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;


/**
 * ��ѡ��Ĳ���
 * @author Administrator
 *
 */
public class AllSelect {
	private Table table;
	private TableColumn tableColumn;
	private List<Button> btns;
	
	public void setValues(Table table,TableColumn tableColumn,List<Button> btns){
		this.table=table;
		this.tableColumn=tableColumn;
		this.btns=btns;
	}
	
	public void tableOp(){
		table.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e) {
				int index=table.getSelectionIndex(); //��ȡѡ���е�����
				if(btns.get(index).getSelection()){
					btns.get(index).setSelection(false);
				} else{
					btns.get(index).setSelection(true);
				}
				setChecked();
			}
		});
	}
	
	public void columnOp(){
		//ȫѡ,ȫ��ѡ
		tableColumn.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				boolean flag=true;
				for(Button b:btns){
					if(!b.getSelection()){
						flag=false;
						break;
					}
				}
				if(flag){
					for(Button b:btns){
						b.setSelection(false);
						tableColumn.setText("ȫѡ");
					}
					table.deselectAll();
				} else{
					for(Button b:btns){
						b.setSelection(true);
						tableColumn.setText("ȫ��ѡ");
					}
					table.selectAll();
				}
			}
		});
	}
	
	/**
	 * ����ѡ�����²���
	 */
	public void setCheckbox(){ 
		TableEditor te;
		TableItem[] tis=table.getItems();
		
		for(int i=0;i<tis.length;i++){
			te=new TableEditor(table);
			te.grabHorizontal=true;
			te.setEditor(btns.get(i), tis[i], 0);
		}
	}
	
	private void setChecked(){
		for(int i=0;i<btns.size();i++){
			if(btns.get(i).getSelection()){
				table.select(i);
			} else{
				table.deselect(i);
			}
		}
	}
	
	public class MySelectionAdapter extends SelectionAdapter{
		private Button bt;
		
		public MySelectionAdapter(Button bt){
			this.bt=bt;
		}
		
		@Override
		public void widgetSelected(SelectionEvent e) {
			if(bt.getSelection()){
				table.select((Integer)bt.getData("id"));
			} else{
				table.deselect((Integer)bt.getData("id"));
			}
			
			boolean flag=true;
			for(Button b:btns){
				if(!b.getSelection()){
					flag=false;
					tableColumn.setText("ȫѡ");
				}
			}
			
			if(flag){
				tableColumn.setText("ȫ��ѡ");
			}
		}
	}
}
