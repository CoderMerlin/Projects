package com.exam.studentop;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;

import com.exam.utils.ShellUtil;
import com.exam.utils.StuInfo;
import com.swtdesigner.SWTResourceManager;

import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * �²���
 * @author Administrator
 *
 */
public class TalkArea extends Composite {
	
	private StuInfo stuInfo=new StuInfo();  //ѧ����Ϣ
	private ShellUtil shellUtil=new ShellUtil();  //shellUtil���߰�
//	List<Map<String,Object>> list1 =stuInfo.findTalkContentStuId(StuOp.stuid);
	
	private Text text;
	private Text text_2;  //�������۵İ�ť
	private Button button_2;  //��ÿһ����ӵ����¼�
	private Button button_1;  //��ÿһ���ظ�����¼�
	private Button button_3;  //���������۰�ť����¼�
	List<Button> button_22=new ArrayList<Button>();  //�����е�button�ŵ�һ��������
	Composite composite;
	ScrolledComposite scrolledComposite ;
	Composite mainComposite ;
	private Composite composite_1; 
	private Composite composite_2;
	

	
	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 */
	public TalkArea(Composite parent, int style) {
		
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		List<Map<String,Object>> list =stuInfo.findAllTalkContent();
		
		SashForm sashForm = new SashForm(this, SWT.NONE);
		sashForm.setBackgroundImage(SWTResourceManager.getImage(TalkArea.class, "/images/RuiyouBackground.png"));
		sashForm.setBackgroundMode(SWT.INHERIT_DEFAULT);
		sashForm.setOrientation(SWT.VERTICAL);
		
		composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setLayout(new FillLayout(SWT.HORIZONTAL));
		

		scrolledComposite = new ScrolledComposite(composite, SWT.V_SCROLL);
		scrolledComposite.setBackgroundImage(SWTResourceManager.getImage(TalkArea.class, "/images/RuiyouBackground.png"));
		scrolledComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);

		mainComposite = new Composite(scrolledComposite,SWT.NONE);
		mainComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		scrolledComposite.setContent(mainComposite);
		mainComposite.setBackground(Display.getCurrent().getSystemColor (SWT.COLOR_WHITE));
		GridData data = new GridData(GridData.FILL_BOTH);
		mainComposite.setLayoutData(data);
		mainComposite.setLayout(null);
		
		composite_2 = new Composite(mainComposite, SWT.NONE);
		composite_2.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_2.setBounds(700, 10, 550,list.size()*400);
		
	
		
	//ʹ��mainComposite�ܹ���
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinHeight(145+list.size()*220+240); //���ô�ֱ����
		
		
		
		composite_1 = new Composite(sashForm, SWT.NONE);
		
		text = new Text(composite_1, SWT.BORDER | SWT.V_SCROLL | SWT.MULTI| SWT.WRAP);
		text.setText("\u53D1\u9001\u8BC4\u8BBA....");
		text.setBounds(30, 10, 530, 179);
		
		Button buttont = new Button(composite_1, SWT.NONE);
		
		buttont.setBounds(580, 162, 80, 27);
		buttont.setText("\u53D1\u9001");
		
		text_2 = new Text(composite_1, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_2.setVisible(false);
		text_2.setBounds(756, 10, 530, 179);
		text_2.setText("���ͻظ�....");
		
		button_3 = new Button(composite_1, SWT.NONE);
		button_3.setVisible(false);
		button_3.setBounds(1292, 162, 80, 27);
		button_3.setText("\u53D1\u9001");
		sashForm.setWeights(new int[] {220, 77});
		
		showAllTalkContent();
		
		
		//���͵������ı���
		text.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
				text.setText("");
			}
		});
		
		text_2.addFocusListener(new FocusAdapter() {
			public void focusGained(FocusEvent e) {
//				List<Map<String,Object>> list =stuInfo.findAllTalkContent();
//				List<Map<String,Object>> list1 =stuInfo.findTalkContentStuId(StuOp.stuid);
//				text_2.setText(list1.get(0).get("SNAME")+"   �ظ�     "+list.get(id).get("SNAME")+":  ");
				text_2.setText("");
			}
		});
	
		
		
		//���Ͱ�ť
		buttont.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Map<String,Object>> list =stuInfo.findAllTalkContent();
				List<Map<String,Object>> list1 =stuInfo.findTalkContentStuId(StuOp.stuid);
				if(text.getText()==null || text.getText().equals("")){
					MessageBox mgb=new MessageBox(StuOp.shell,SWT.ERROR);
					mgb.setText("����");
					mgb.setMessage("������Ϣ����Ϊ�գ�����");
					mgb.open();
				}else{
					
					//����
					Text text1=new Text(mainComposite,SWT.BORDER | SWT.V_SCROLL | SWT.MULTI | SWT.WRAP);
					text1.setBounds(126, 30+list.size()*120, 500, 80);
					text1.setText(text.getText());
					text1.setEnabled(false);
					
					//����
					Label label2 =new Label(mainComposite, SWT.NONE);
					label2.setAlignment(SWT.CENTER);
					label2.setBounds(21, 120+list.size()*120, 100, 20);
					label2.setText((String) list1.get(0).get("SNAME"));
					
					//ͷ��
					Label label1 =new Label(mainComposite, SWT.NONE);
					label1.setBounds(21, 30+list.size()*120, 100, 80);
					if(list1.get(0).get("SPHOTO")==null){
						label1.setImage(SWTResourceManager.getImage(TalkArea.class,"/images/1.gif"));
					}else{
						label1.setImage(shellUtil.getImage((byte[])list1.get(0).get("SPHOTO"),100,80));
					}
					
					
					//�ظ�
					Button button1 = new Button(mainComposite, SWT.NONE);
					button1.setBounds(480, 115+list.size()*120, 70, 30);
					button1.setText("\u56DE\u590D");
					
					
					//����
					Button button2 =new Button(mainComposite, SWT.NONE);
					button2.setImage(SWTResourceManager.getImage(TalkArea.class, "/images/praise.png"));
					button2.setBounds(550, 115+list.size()*120, 70, 30);
					if(list.size()==0){
						button2.setText("1");
					}else{
						button2.setText((String) list.get(list.size()-1).get("TPRAISE"));
					}
					
					//�浽���ݿ���
					@SuppressWarnings("unused")
					int list3 =stuInfo.addTalkContent(StuOp.stuid, text1.getText());
					refershTalk();
				}
				
			}
		});
	}
	//����ˢ��һ�����۵Ľ���
	public void refershTalk(){
		mainComposite.dispose();
		mainComposite = new Composite(scrolledComposite,SWT.NONE);
		mainComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		scrolledComposite.setContent(mainComposite);
		mainComposite.setBackground(Display.getCurrent().getSystemColor (SWT.COLOR_WHITE));
		GridData data = new GridData(GridData.FILL_BOTH);
		mainComposite.setLayoutData(data);
		mainComposite.setLayout(null);
		
		showAllTalkContent();
	}
	
	//����ˢ��һ�»ظ�����
	public void refershDiscuss(){
		composite_2.dispose();
		composite_2 = new Composite(mainComposite, SWT.NONE);
		composite_2.setBounds(700, 10, 550, 1000);
		
		
		
		
	}
	
	//��ʾ���е�����
	public void showAllTalkContent(){
		Text text1;
		Label label1; //ͷ��
		Label label2;  //����
		List<Map<String,Object>> list =stuInfo.findAllTalkContent();
		for(int i=0;i<list.size();i++){
			//̸������
			text1 =new Text(mainComposite,SWT.BORDER | SWT.V_SCROLL | SWT.MULTI| SWT.WRAP);  //WRAP �Զ�����
			text1.setBounds(126, 30+i*120, 500, 80);
			text1.setText((String) list.get(i).get("TALKCONTENT"));
			text1.setEnabled(false);
			
			//�����ݿ�����ȥͷ��
			label1 =new Label(mainComposite, SWT.NONE);
			label1.setBounds(21, 30+i*120, 100, 80);
			if(list.get(i).get("SPHOTO")==null){
				label1.setImage(SWTResourceManager.getImage(TalkArea.class,"/images/1.gif"));
			}else{
				label1.setImage(shellUtil.getImage((byte[])list.get(i).get("SPHOTO"),100,80));
			}
			
			//����
			label2 =new Label(mainComposite, SWT.NONE);
			label2.setAlignment(SWT.CENTER);
			label2.setBounds(21, 120+i*120, 100, 20);;
			label2.setText((String) list.get(i).get("SNAME"));
			//�ظ�
			button_1 = new Button(mainComposite, SWT.NONE);
			button_1.setData("did",i);
			button_1.addSelectionListener(new MySelectionAdapter2(button_1));
			button_1.setBounds(480, 115+i*120, 70, 30);
			button_1.setText("\u56DE\u590D");
			
			//����
			button_2 =new Button(mainComposite, SWT.NONE);
			button_2.setData("ttid",i);
			button_2.addSelectionListener(new MySelectionAdapter1(button_2));
			button_2.setImage(SWTResourceManager.getImage(TalkArea.class, "/images/praise.png"));
			button_2.setBounds(550, 115+i*120, 70, 30);
			button_2.setText((String) list.get(i).get("TPRAISE"));
			button_22.add(button_2);
		}
	}
	
	
	
	//�ڲ���  ���е��޵�button
	class MySelectionAdapter1 extends SelectionAdapter{
		private Button bt;
		String s1;
		Integer id;
		public MySelectionAdapter1(Button bt){
			this.bt=bt;
		}
		public void widgetSelected(SelectionEvent e){   //û���˺�ֻ����һ��
				List<Map<String,Object>> list =stuInfo.findAllTalkContent();
				id=(Integer) bt.getData("ttid");  //��ȡ�����ť�ı��
				String s=(String) list.get(id).get("TPRAISE");
				int a =Integer.valueOf(s).intValue();  
				a++;
				s1=String.valueOf(a);
				bt.setText(s1); 
				bt.setImage(SWTResourceManager.getImage(TalkArea.class, "/images/praise1.png"));
				//����  �������ݿ�
				@SuppressWarnings("unused")
				int lis2=stuInfo.addTalkTpraise(s1, (String) list.get(id).get("STUID"));
		}
	}
	
	//���еĻظ�
	class MySelectionAdapter2 extends SelectionAdapter{
		private Button bt;
		private Integer id;
		public MySelectionAdapter2(Button bt){
			this.bt=bt;
		}
		public void widgetSelected(SelectionEvent e){
			composite_2.dispose();
			composite_2 = new Composite(mainComposite, SWT.NONE);
			composite_2.setBounds(700, 10, 550, 1000);
			List<Map<String,Object>> list =stuInfo.findAllTalkContent();
			id=(Integer) bt.getData("did");  //��ȡ�����ť�ı��
			String ttid=(String) list.get(id).get("TTID");
			List<Map<String,Object>> list3=stuInfo.findThisTalkAllDiscussContent(ttid);
			for(int i=0; i<list3.size();i++){
				Label label1 =new Label(composite_2, SWT.NONE);
				label1.setBounds(10, 30+i*120, 400, 20);
				label1.setText(list3.get(i).get("SNAME")+"  �ظ�    "+list.get(id).get("SNAME"));
				//�ظ��˵�����
				Text text1 =new Text(composite_2, SWT.BORDER );
				text1.setBounds(10,60+i*120,500,50);
				text1.setText((String) list3.get(i).get("DISCUSSCONTENT"));
				text1.setEnabled(false);
			}
				text_2.setVisible(true);
				button_3.setVisible(true);
			
		
				button_3.addSelectionListener(new SelectionAdapter() {
					@Override
				
					public void widgetSelected(SelectionEvent e) {
						System.out.println("���");
						List<Map<String,Object>> list1 =stuInfo.findTalkContentStuId(StuOp.stuid);
						List<Map<String,Object>> list =stuInfo.findAllTalkContent();
						String ttid1=(String) list.get(id).get("TTID");
						List<Map<String,Object>> list3=stuInfo.findThisTalkAllDiscussContent(ttid1);
						
						if(text_2.getText()==null || text_2.getText()==("")){
							MessageBox mgb=new MessageBox(StuOp.shell,SWT.ERROR);
							mgb.setText("����");
							mgb.setMessage("������Ϣ����Ϊ�գ�����");
							mgb.open();
						}else if(list3==null){
							Label label3 =new Label(composite_2, SWT.NONE);
							label3.setBounds(10, 30, 400, 20);
							label3.setText(list1.get(0).get("SNAME")+"   �ظ�     "+list.get(id).get("SNAME")+":  ");
							//�ظ��˵�����
							Text text3 =new Text(composite_2, SWT.BORDER );
							text3.setBounds(10,60,500,50);
							text3.setText(text_2.getText());
							text3.setEnabled(false);
							
							String ttid= (String) list.get(id).get("TTID");
							String stuid=StuOp.stuid;
							String DiscussContent =text_2.getText().trim();
							@SuppressWarnings("unused")
							int lis3=stuInfo.addDiscussContent(ttid, stuid, DiscussContent);
//							text_2.setText("");
						}else{
							Label label3 =new Label(composite_2, SWT.NONE);
							label3.setBounds(10, 30+list3.size()*120, 400, 20);
							label3.setText(list1.get(0).get("SNAME")+"   �ظ�     "+list.get(id).get("SNAME")+":  ");
							//�ظ��˵�����
							Text text3 =new Text(composite_2, SWT.BORDER );
							text3.setBounds(10,60+list3.size()*120,500,50);
							text3.setText(text_2.getText());
							text3.setEnabled(false);
							
							String ttid= (String) list.get(id).get("TTID");
							String stuid=StuOp.stuid;
							String DiscussContent =text_2.getText().trim();
							@SuppressWarnings("unused")
							int lis3=stuInfo.addDiscussContent(ttid, stuid, DiscussContent);
//							text_2.setText("");
						}
					}
				});
		}
	}
}
