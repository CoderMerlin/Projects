package com.exam.teacherop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

import com.exam.utils.AllSelect;
import com.exam.utils.ClassInfo;

import com.swtdesigner.SWTResourceManager;

/**
 * �༶����
 * @author Administrator
 *
 */
public class ClassOp extends Composite {
	private ClassInfo ci=new ClassInfo();
	private AllSelect as=new AllSelect();
	
	private Table table;
	private Text text_1;
	private Button button;
	private List<Button> btns=new ArrayList<Button>();

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public ClassOp(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setBackgroundImage(SWTResourceManager.getImage(ClassOp.class, "/images/RuiyouBackground.png"));
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		final Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		//ʹ���½��༶������С����
		composite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				sashForm.setWeights(new int[] {sashForm.getSize().y-150, 150});
			}
		});
		
		table = new Table(composite, SWT.FULL_SELECTION | SWT.MULTI);
		table.setBackground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		
		final TableColumn tableColumn_2 = new TableColumn(table, SWT.NONE);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u9009\u62E9");
		
		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("\u73ED\u7EA7\u7F16\u53F7");
		
		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u73ED\u7EA7\u540D\u79F0");
		
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		Label label_1 = new Label(composite_1, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setBounds(86, 57, 80, 23);
		label_1.setText("\u73ED\u7EA7\u540D\u79F0:");
		
		text_1 = new Text(composite_1, SWT.BORDER);
		text_1.setBounds(200, 58, 170, 23);
		
		Button button = new Button(composite_1, SWT.NONE);
		button.setBounds(443, 56, 80, 27);
		button.setText("\u65B0\u5EFA\u73ED\u7EA7");
		sashForm.setWeights(new int[] {260, 111});
		
		showTable();
		as.setValues(table, tableColumn_2, btns);
		as.tableOp();
		as.columnOp();
		

		//�Զ��ı��п�
		composite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				int width=composite.getSize().x;
				width=(width-50)/2;
				TableColumn[] tcs=table.getColumns();
				tcs[0].setWidth(50);
				for(int i=1;i<tcs.length;i++){
					tcs[i].setWidth(width);
				}
			}
		});
		
		//�һ�table,�����˵����в���
		table.addMouseListener(new MouseAdapter() { 
			
            public void mouseDown(MouseEvent e) {  
                if (e.button == 3) {
                    Menu menu = new Menu(table);  
                    table.setMenu(menu);
                    MenuItem delitem = new MenuItem(menu, SWT.PUSH);
                    delitem.setText("ɾ��");
                    
                    TableItem[] tis=table.getSelection();
                    if(tis.length==0){
                    	delitem.setEnabled(false);
                    }
                    
                    delitem.addListener(SWT.Selection, new Listener() {  
                        public void handleEvent(Event event) { 
                        	
                            TableItem item1 = (TableItem) table.getSelection()[0];  
                            Table parent = item1.getParent();  
                            if (parent != null) {
                            	MessageBox mgb=new MessageBox(TeacherOp.shell,SWT.ICON_INFORMATION | SWT.YES | SWT.NO);
                            	mgb.setText("ɾ����ʾ");
                            	mgb.setMessage("�Ƿ�ɾ���ð༶");
                            	if(mgb.open()==64){
                            		TableItem[] tis2=table.getSelection();
                            		for(TableItem t:tis2){
                            			ci.delClass(t.getText(1));
                            		}
                            		
                            		int[] indexs=table.getSelectionIndices(); //��ȡѡ���е�����
                            		table.remove(indexs); //��������ɾ��������
                            		
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
                            	
                            }
                        }  
                    });  
                    

                    final MenuItem scoreitem = new MenuItem(menu, SWT.PUSH);
                    scoreitem.setText("�鿴�ð�ѧ���ɼ�");
                    scoreitem.setEnabled(false);
                    
                    final MenuItem chartitem = new MenuItem(menu, SWT.PUSH);
                    chartitem.setText("�鿴�ð�ƽ���ɼ���Ϣ");
                    chartitem.setEnabled(false);

                    if(tis.length==1){
                    	scoreitem.setEnabled(true);
                    	chartitem.setEnabled(true);
                    }
                    
                    scoreitem.addListener(SWT.Selection, new Listener() {  
                    	public void handleEvent(Event event) {  
                    		TableItem item1 = (TableItem) table.getSelection()[0];  
                    		Table parent = item1.getParent();  
                    		if (parent != null) {  
                    			String cid = null;
                            	TableItem[] tis2=table.getSelection();
                				for(TableItem t:tis2){
                					cid=t.getText(1);
                				}
                    			ShowClassScore scs=new ShowClassScore(TeacherOp.shell, SWT.MIN |SWT.APPLICATION_MODAL);
                    			scs.setValue(cid);
                    			scs.open();
                    		}
                    	}  
                    }); 
                    
                    chartitem.addListener(SWT.Selection, new Listener() {  
                    	public void handleEvent(Event event) {  
                    		TableItem item1 = (TableItem) table.getSelection()[0];  
                    		Table parent = item1.getParent();  
                    		if (parent != null) {
                    			ClassScoreChart csc=new ClassScoreChart();
                    			csc.setValues(item1.getText(1));
                    			csc.open();
                    		}
                    	}  
                    }); 
                    
                }  
            }  
        });  
		
		//�½��༶
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String cname=text_1.getText().trim();
				
				if(ci.addClassInfo(cname)>0){
					showTable();
					text_1.setText("");
					MessageBox mgb=new MessageBox(TeacherOp.shell,SWT.ICON_INFORMATION);
					mgb.setText("�ɹ���ʾ");
					mgb.setMessage("�༶��Ϣ��ӳɹ�....");
					mgb.open();
				}else{
					MessageBox mgb=new MessageBox(TeacherOp.shell,SWT.ICON_ERROR);
					mgb.setText("ʧ����ʾ");
					mgb.setMessage("�༶��Ϣ���ʧ��....");
					mgb.open();
				}
			}
		});  
	}
	
	/**
	 * ������Ϣ����ʾ���
	 */
	public void showTable(){
		table.removeAll();
		List<Map<String, Object>> list=ci.findAll();
		TableItem ti;
		TableEditor te;
		for(int i=0;i<list.size();i++){
			ti=new TableItem(table, SWT.NONE);
			ti.setText(new String[]{"",(String) list.get(i).get("CID"),(String) list.get(i).get("CNAME")});
			
			te=new TableEditor(table);
			button=new Button(table,SWT.CHECK);
			button.setData("id",i);
			te.grabHorizontal=true; //��ȡˮƽ����
			te.setEditor(button, ti, 0);
			button.addSelectionListener(as.new MySelectionAdapter(button));
			btns.add(button);
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
