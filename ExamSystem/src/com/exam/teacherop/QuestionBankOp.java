package com.exam.teacherop;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import jxl.read.biff.BiffException;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableEditor;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import com.exam.utils.AllSelect;
import com.exam.utils.ExcelToOracle;
import com.exam.utils.QuestionInfo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Label;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.widgets.Combo;

/**
 * 题库维护
 * @author Administrator
 *
 */
public class QuestionBankOp extends Composite {
	private ExcelToOracle eto=new ExcelToOracle();
	private QuestionInfo qi=new QuestionInfo();
	private Table table;
	private TableItem ti;
	private TableEditor te;
	private TableEditor te2;

	private AllSelect as=new AllSelect();
	private Button button;
	private CCombo combo;
	private List<CCombo> ccos=new ArrayList<CCombo>();
	private List<Button> btns=new ArrayList<Button>();
	private Text text;
	private Text text_1;
	private String filePath="";

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public QuestionBankOp(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));

		Composite composite = new Composite(this, SWT.NONE);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));

		SashForm sashForm = new SashForm(composite, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		final Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setLayout(new FillLayout(SWT.HORIZONTAL));

		table = new Table(composite_1, SWT.BORDER | SWT.FULL_SELECTION | SWT.MULTI);
		table.setHeaderVisible(true);
		table.setLinesVisible(true);

		final TableColumn tableColumn_6 = new TableColumn(table, SWT.NONE);
		tableColumn_6.setWidth(50);
		tableColumn_6.setText("\u9009\u62E9");

		TableColumn tableColumn = new TableColumn(table, SWT.CENTER);
		tableColumn.setWidth(100);
		tableColumn.setText("\u9898\u76EE\u7F16\u53F7");

		TableColumn tableColumn_1 = new TableColumn(table, SWT.CENTER);
		tableColumn_1.setWidth(100);
		tableColumn_1.setText("\u8BFE\u7A0B\u540D\u79F0");

		TableColumn tableColumn_2 = new TableColumn(table, SWT.CENTER);
		tableColumn_2.setWidth(100);
		tableColumn_2.setText("\u9898\u76EE\u5185\u5BB9");

		TableColumn tableColumn_3 = new TableColumn(table, SWT.CENTER);
		tableColumn_3.setWidth(100);
		tableColumn_3.setText("\u9898\u76EE\u9009\u9879");

		TableColumn tableColumn_4 = new TableColumn(table, SWT.CENTER);
		tableColumn_4.setWidth(100);
		tableColumn_4.setText("\u6B63\u786E\u7B54\u6848");

		final TableColumn tableColumn_5 = new TableColumn(table, SWT.CENTER);
		tableColumn_5.setWidth(100);
		tableColumn_5.setText("\u9898\u76EE\u79CD\u7C7B");

		Composite composite_2 = new Composite(sashForm, SWT.NONE);

		Label label = new Label(composite_2, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label.setBounds(23, 20, 88, 27);
		label.setText("\u8BFE\u7A0B\u540D:");

		text = new Text(composite_2, SWT.BORDER);
		text.setBounds(117, 21, 171, 27);

		final Button button_1 = new Button(composite_2, SWT.NONE);
		button_1.setEnabled(false);
		button_1.setBounds(323, 21, 80, 27);
		button_1.setText("\u6DFB\u52A0\u8BFE\u7A0B");

		Label label_1 = new Label(composite_2, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_1.setBounds(23, 72, 107, 27);
		label_1.setText("\u6587\u4EF6\u8DEF\u5F84:");

		text_1 = new Text(composite_2, SWT.BORDER);
		text_1.setBounds(158, 71, 384, 27);

		Button button_2 = new Button(composite_2, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("方正综艺简体", 9, SWT.NORMAL));
		button_2.setBounds(572, 73, 88, 27);
		button_2.setText("\u6279\u91CF\u5BFC\u5165");

		Label label_2 = new Label(composite_2, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_2.setBounds(619, 20, 88, 27);
		label_2.setText("\u53EA\u663E\u793A:");

		final Combo combo_1 = new Combo(composite_2, SWT.NONE);
		combo_1.setBounds(713, 19, 131, 25);
		combo_1.setItems(new String[] {"\u6240\u6709", "Java", "Oracle"});

		//		Button button_3 = new Button(composite_2, SWT.NONE);
		//		button_3.setBounds(686, 72, 80, 27);
		//		button_3.setText("\u663E\u793A\u6240\u6709");
		//		sashForm.setWeights(new int[] {222, 75});

		showTable();
		as.setValues(table, tableColumn_6, btns);
		as.tableOp();
		as.columnOp();

		//只显示该门课程
		combo_1.addSelectionListener(new SelectionAdapter(){
			@Override
			public void widgetSelected(SelectionEvent e) {
				if("所有".equals(combo_1.getText().trim())){
					showTable();
				} else{
					showTable(combo_1.getText());
				}
				updateInfo();
			}
		});

//		//全部显示
//		button_3.addSelectionListener(new SelectionAdapter() {
//			@Override
//			public void widgetSelected(SelectionEvent e) {
//				showTable();
//			}
//		});


		
		updateInfo();
		
		
		//自动适应列宽
		composite_1.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				int width=composite_1.getSize().x;
				width=(width-50)/6;
				TableColumn[] cols=table.getColumns();
				cols[0].setWidth(50);
				for(int i=1;i<cols.length;i++){
					cols[i].setWidth(width);
				}
			}
		});

		final TableEditor editor = new TableEditor(table);
		sashForm.setWeights(new int[] {379, 133});
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;

		//对表格的内容进行修改
		table.addMouseListener(new MouseAdapter() {
			public void mouseDown(MouseEvent event) {
				Control cont = editor.getEditor();
				if (cont != null){
					cont.dispose();
				}

				Point pt = new Point(event.x, event.y);
				final TableItem item = table.getItem(pt);
				if (item == null) {
					return;
				}
				int column = -1;
				for (int i = 0, n = table.getColumnCount(); i < n; i++) {
					Rectangle rect = item.getBounds(i);
					if (rect.contains(pt)) {
						column = i;
						break;
					}
				}

				if (column == 0 || column==1 || column==6) { //不能改变第一列和第二列的值 和最后一列
					return;
				}

				final Text text = new Text(table, SWT.NONE);
				text.setForeground(item.getForeground());

				text.setText(item.getText(column));
				text.setForeground(item.getForeground());
				text.selectAll();
				text.setFocus();

				//约束单选题的答案框的输入
				if(column==5 && "单选题".equals(ccos.get(table.getSelectionIndex()).getText().trim())){
					final String key=text.getText().trim(); //记录现在的答案

					final List<String> rkeys=new ArrayList<String>(); //用于记录可选的答案
					String[] rkey=item.getText(4).split("_"); //获取可选的答案
					for(String r:rkey){ //可选的答案
						rkeys.add(r.substring(0, 1).trim());
					}

					text.addFocusListener(new FocusAdapter() {
						public void focusLost(FocusEvent e) {
							if(rkeys.contains(text.getText())){
							} else{ //如果输入的答案不是可选的答案，则变回原来的答案
								text.setText(key);
							}
						}
					});
				}

				editor.minimumWidth = text.getBounds().width;

				editor.setEditor(text, item, column);

				final int col = column;

				text.addModifyListener(new ModifyListener() {
					public void modifyText(ModifyEvent event) { 
						item.setText(col, text.getText());
						String content=text.getText(); //获取修改的内容

						String qbid=item.getText(1); //获取要修改的题目的编号

						String param=""; //获取要修改什么
						switch (col) {
						case 2:
							param="courseName";
							break;
						case 3:
							param="qbContent";
							break;
						case 4:
							param="qbOption";
							break;
						case 5:
							param="rkey";
							break;
						}
						qi.updateQuestion(qbid, param, content);
					}
				});
			}
		});

		//右击table,弹出删除操作
		table.addMouseListener(new MouseAdapter() {  
			public void mouseDown(MouseEvent e) {  
				if (e.button == 3) {  
					Menu menu = new Menu(table);  
					table.setMenu(menu);  
					final MenuItem delItem = new MenuItem(menu, SWT.PUSH);
					delItem.setText("删除");

					if(table.getSelectionCount()==0){ //如果没有选中行，则不能对删除菜单进行操作
						delItem.setEnabled(false);
					}

					delItem.addListener(SWT.Selection, new Listener() {  
						public void handleEvent(Event event) {
							TableItem item1 = (TableItem) table.getSelection()[0];  
							Table parent = item1.getParent();  
							if (parent != null) {  
								TableItem[] tis=table.getSelection();
								for(TableItem t:tis){
									qi.delQuestion(t.getText(1)); //获取选中行的题目编号
								}

								int[] indexs=table.getSelectionIndices(); //获取选中行的索引
								table.remove(indexs); //根据索引删除所在行

								List<Button> bts=new ArrayList<Button>();
								List<CCombo> ccs=new ArrayList<CCombo>();
								for(int i:indexs){
									bts.add(btns.get(i));
									ccs.add(ccos.get(i));
								}

								for(Button b:bts){ //删除复选框
									btns.remove(b);
									b.dispose();
								}

								for(CCombo c:ccs){ //删除下拉框
									ccos.remove(c);
									c.dispose();
								}
								as.setCheckbox();
							}
						}  
					});
				}  
			}  
		});

		//激活添加课程
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(text.getText()==null || "".equals(text.getText())){
					button_1.setEnabled(false);
				} else{
					button_1.setEnabled(true);
				}
			}
		});

		//添加课程
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String courseName=text.getText();
				if(qi.insertCourse(courseName)>0){
					MessageBox mgb=new MessageBox(TeacherOp.shell,SWT.ICON_INFORMATION);
					mgb.setText("添加成功");
					mgb.setMessage("课程信息添加成功!!!");
					mgb.open();
				} else{
					MessageBox mgb=new MessageBox(TeacherOp.shell,SWT.ICON_ERROR);
					mgb.setText("添加失败");
					mgb.setMessage("课程信息添加失败!!!");
					mgb.open();
				}
			}
		});

		//批量导入
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd=new FileDialog(TeacherOp.shell,SWT.OPEN);
				fd.setText("导入文件");
				fd.setFilterPath("SystemRoot");
				fd.setFilterExtensions(new String[]{"*.xls"});
				filePath=fd.open();

				if(filePath!=null && !"".equals(filePath)){ //如果文件不为空
					text_1.setText(filePath);
					try {
						int[] result=eto.insertExcelToOracle(filePath, 0);
						if(result[0]==result[1]){
							clearTableAll();
							showTable();
							updateInfo();
						} else if(result[0]>result[1] && result[1]!=0){
							clearTableAll();
							showTable();
							updateInfo();
							MessageBox mgb=new MessageBox(TeacherOp.shell,SWT.ICON_INFORMATION);
							mgb.setText("导入提示");
							mgb.setMessage("试题部分导入成功，其余部分可能为试题重复");
							mgb.open();
						} else if(result[1]==0){
							MessageBox mgb=new MessageBox(TeacherOp.shell,SWT.ICON_ERROR);
							mgb.setText("导入提示");
							mgb.setMessage("试题导入失败");
							mgb.open();
						}
					} catch (BiffException e2) {
						e2.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		});
	}

	/**
	 * 清除表格中的所有信息
	 */
	public void clearTableAll(){
		List<Button> bts=new ArrayList<Button>();
		List<CCombo> ccs=new ArrayList<CCombo>();
		for(int i=0;i<table.getItemCount();i++){
			bts.add(btns.get(i));
			ccs.add(ccos.get(i));
		}
		for(Button b:bts){ //删除复选框
			btns.remove(b);
			b.dispose();
		}
		for(CCombo c:ccs){ //删除下拉框
			ccos.remove(c);
			c.dispose();
		}
		table.removeAll();
	}

	/**
	 * 填入信息，显示表格
	 */
	public void showTable(){
		clearTableAll();
		//table.removeAll();

		List<Map<String, Object>> list=qi.findAllQuestion();

		for(int i=0;i<list.size();i++){
			ti=new TableItem(table, SWT.NONE);
			ti.setText(new String[]{"",(String) list.get(i).get("QBID"),(String) list.get(i).get("COURSENAME"),(String) list.get(i).get("QBCONTENT"),(String) list.get(i).get("QBOPTION"),(String) list.get(i).get("RKEY"),""});

			te=new TableEditor(table);
			button=new Button(table,SWT.CHECK);
			button.setData("id",i);
			te.grabHorizontal=true; //攫取水平布局
			te.grabVertical=true;
			te.setEditor(button, ti, 0);
			button.addSelectionListener(as.new MySelectionAdapter(button));
			btns.add(button);

			te2=new TableEditor(table);
			combo=new CCombo(table,SWT.NONE);
			combo.setData("id",ti.getText(1));
			combo.setEditable(false);
			te2.grabHorizontal=true;
			te.grabVertical=true;
			te2.setEditor(combo, ti, 6);
			combo.setItems((String[]) qi.findQBquestion(ti.getText(1)).get(0));
			combo.setText((String) qi.findQBquestion(ti.getText(1)).get(1));
			ccos.add(combo);
		}
	}

	/**
	 * 填入信息，显示表格
	 */
	public void showTable(String coursename){
		clearTableAll();

		List<Map<String, Object>> list=qi.findAllQuestion(coursename);

		for(int i=0;i<list.size();i++){
			ti=new TableItem(table, SWT.NONE);
			ti.setText(new String[]{"",(String) list.get(i).get("QBID"),(String) list.get(i).get("COURSENAME"),(String) list.get(i).get("QBCONTENT"),(String) list.get(i).get("QBOPTION"),(String) list.get(i).get("RKEY"),""});

			te=new TableEditor(table);
			button=new Button(table,SWT.CHECK);
			button.setData("id",i);
			te.grabHorizontal=true; //攫取水平布局
			te.grabVertical=true;
			te.setEditor(button, ti, 0);
			button.addSelectionListener(as.new MySelectionAdapter(button));
			btns.add(button);

			te2=new TableEditor(table);
			combo=new CCombo(table,SWT.NONE);
			combo.setData("id",ti.getText(1));
			combo.setEditable(false);
			te2.grabHorizontal=true;
			te.grabVertical=true;
			te2.setEditor(combo, ti, 6);
			combo.setItems((String[]) qi.findQBquestion(ti.getText(1)).get(0));
			combo.setText((String) qi.findQBquestion(ti.getText(1)).get(1));
			ccos.add(combo);
		}
	}
	
	/**
	 * 提示更新之后的操作
	 */
	public void updateInfo(){
		for(final CCombo c:ccos){
			c.addSelectionListener(new SelectionAdapter() {
				@Override
				public void widgetSelected(SelectionEvent e) {
					MessageBox mg=new MessageBox(TeacherOp.shell,SWT.ICON_INFORMATION);
					mg.setText("警告提示！");
					mg.setMessage("您的题目种类已更改,请尽快修改题目内容!!!!!");
					mg.open();
					
					qi.updateQuestion((String)c.getData("id"), "qbKind", c.getText());
				}
			});
		}
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
