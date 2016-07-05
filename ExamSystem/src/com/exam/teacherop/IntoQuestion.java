package com.exam.teacherop;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Label;

import com.exam.utils.ClassInfo;
import com.exam.utils.QuestionInfo;
import com.exam.utils.ShellUtil;

import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;


public class IntoQuestion extends Composite {
	private ShellUtil su=new ShellUtil();
	private QuestionInfo qi=new QuestionInfo();
	private ClassInfo ci=new ClassInfo();
	private Text text_2;
	private String[] courseName=qi.findcourseName(); //�γ�����
	private String[] qbKinds=qi.fingQbkind(); //��Ŀ����
	

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public IntoQuestion(Composite parent, int style) {
		super(parent, style);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final Composite composite = new Composite(this, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(IntoQuestion.class, "/images/RuiyouBackground.png"));
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setLayout(null);
		
		//�ı�����Сʹ��������Ӧ
		composite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
					try {
						composite.setBackgroundImage(  su.ImageSize(new FileInputStream(new File("bin/images/RuiyouBackground.png")),
								composite.getBounds().width,composite.getBounds().height    )    );
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					}
			}
		});
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label.setBounds(261, 118, 101, 26);
		label.setText("\u73ED\u3000\u3000\u7EA7:");
		
		final Combo combo = new Combo(composite, SWT.NONE);
		combo.setBounds(368, 118, 145, 25);
		combo.setItems(ci.findCId()); //���ð༶���
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_1.setBounds(656, 193, 101, 26);
		label_1.setText("\u5B66\u671F\u7F16\u53F7:");
		
		Group group = new Group(composite, SWT.NONE);
		group.setBounds(769, 169, 222, 50);
		
		Button button = new Button(group, SWT.RADIO);
		button.setFont(SWTResourceManager.getFont("�������ռ���", 9, SWT.NORMAL));
		button.setSelection(true);
		button.setBounds(10, 23, 97, 17);
		button.setText("\u671F\u4E2D");
		
		final Button button_1 = new Button(group, SWT.RADIO);
		button_1.setFont(SWTResourceManager.getFont("�������ռ���", 9, SWT.NORMAL));
		button_1.setBounds(115, 23, 97, 17);
		button_1.setText("\u671F\u672B");
		
		Label lblmin = new Label(composite, SWT.NONE);
		lblmin.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		lblmin.setBounds(261, 338, 101, 26);
		lblmin.setText("\u8003\u8BD5\u65F6\u957F:");
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_3.setBounds(261, 259, 101, 28);
		label_3.setText("\u9898\u76EE\u6570\u91CF:");
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_4.setBounds(656, 259, 86, 28);
		label_4.setText("\u8BD5\u5377\u540D\u79F0:");
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setBounds(769, 258, 222, 23);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_5.setBounds(656, 118, 86, 26);
		label_5.setText("\u8BFE\u7A0B\u540D:");
		
		final CCombo combo_1 = new CCombo(composite, SWT.BORDER);
		combo_1.setBounds(769, 118, 133, 21);
		combo_1.setItems(courseName); //���ÿγ�����
		
		
		Button button_2 = new Button(composite, SWT.NONE);
		button_2.setFont(SWTResourceManager.getFont("�������ռ���", 14, SWT.NORMAL));
		button_2.setBounds(859, 434, 132, 50);
		button_2.setText("\u5BFC\u5165\u8BD5\u9898");
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_6.setBounds(261, 186, 101, 26);
		label_6.setText("\u9898\u76EE\u7C7B\u578B:");
		
		final CCombo combo_2 = new CCombo(composite, SWT.BORDER);
		combo_2.setItems(qbKinds); //������Ŀ����
		combo_2.setBounds(368, 186, 145, 26);
		
		final Spinner spinner = new Spinner(composite, SWT.BORDER);
		spinner.setSelection(10);
		spinner.setBounds(368, 259, 145, 26);
		
		final Spinner spinner_1 = new Spinner(composite, SWT.BORDER);
		spinner_1.setSelection(20);
		spinner_1.setBounds(368, 336, 145, 26);
		
		
		//���ÿ�ѡ��Ŀ�����ֵ
		combo_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String coursename=combo_1.getText();
				String qbkind=combo_2.getText();
				if(coursename!=null && !"".equals(coursename) && qbkind!=null && !"".equals(qbkind)  ){
					spinner.setMaximum((int)qi.findCQNum(coursename, qbkind));
				}
			}
		});
		combo_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				String coursename=combo_1.getText();
				String qbkind=combo_2.getText();
				if(coursename!=null && !"".equals(coursename) && qbkind!=null && !"".equals(qbkind)  ){
					spinner.setMaximum((int)qi.findCQNum(coursename, qbkind));
				}
			}
		});
		
		
		//��������
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Object> list=new ArrayList<Object>(); //�洢��Ҫ�������Ϣ
				//System.out.println(dateTime.getYear()+"/"+(dateTime.getMonth()+1)+"/"+dateTime.getDay());
				String cid=combo.getText(); //��ȡ�༶���
				String tid=TeacherOp.tid; //��ʦ���
				String qName=text_2.getText(); //�Ծ���
				String qnumber=spinner.getText(); //��Ŀ����
				
				
				//String sTime=dateTime.getYear()+"/"+(dateTime.getMonth()+1)+"/"+dateTime.getDay(); //��������
				String duration=spinner_1.getText(); //��ȡ����ʱ��
				String qterm="����"; //ѧ��
				if(button_1.getSelection()){
					qterm="��ĩ";
				}
				
				qi.setValue(Integer.valueOf(qnumber),combo_1.getText(),combo_2.getText()); //��ס������������Ŀ����Ϳγ���      �������ȡ��Ŀ
				
				Collections.addAll(list, cid,tid,qName,qnumber,duration,qterm);
				
				if(qi.insertQuestion(list)>0){
					MessageBox mgb=new MessageBox(TeacherOp.shell,SWT.ICON_INFORMATION);
					mgb.setText("����ɹ�");
					mgb.setMessage(text_2.getText()+"�Ծ����ɹ�");
					mgb.open();
					
					qi.insertQuestionNextOp(list);
					
					//���ؼ�����������
					combo.setText("");
					combo_1.setText("");
					combo_2.setText("");
					spinner.setSelection(10);
					spinner.setSelection(20);
					text_2.setText("");
				} else{
					MessageBox mgb=new MessageBox(TeacherOp.shell,SWT.ICON_ERROR);
					mgb.setText("����ʧ��");
					mgb.setMessage(text_2.getText()+"�Ծ���ʧ��");
					mgb.open();
				}
			}
		});

		//�һ�composite,ˢ��
		composite.addMouseListener(new MouseAdapter() {  
            public void mouseDown(MouseEvent e) {  
                if (e.button == 3) {
                    Menu menu = new Menu(composite);  
                    composite.setMenu(menu);
                    MenuItem delitem = new MenuItem(menu, SWT.PUSH);
                    delitem.setText("ˢ��");
                    delitem.addListener(SWT.Selection, new Listener() {  
                        public void handleEvent(Event event) {
                        	ci=new ClassInfo(); //ˢ�¿γ̱�
                        	qi=new QuestionInfo(); //ˢ����Ŀ
                        	combo.setItems(ci.findCId()); //���ð༶���
                        	combo_2.setItems(qi.fingQbkind()); //������Ŀ����
                        	combo_1.setItems(qi.findcourseName()); //���ÿγ�����
                        }  
                    });  
                }  
            }  
        });  
	}
	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
