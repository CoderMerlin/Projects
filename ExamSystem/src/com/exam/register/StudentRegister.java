package com.exam.register;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.exam.login.Login;
import com.exam.utils.ClassInfo;
import com.exam.utils.RegisterInfo;
import com.exam.utils.ShellUtil;
import com.exam.utils.StuInfo;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.custom.CCombo;

 /**
 * ע��ѧ����Ϣ
 * @author Administrator
 *
  */
 public class StudentRegister {
	private ShellUtil su=new ShellUtil();
	
	private String path=""; //ѧ��ͼ��·��
	private ShellUtil shellUtil=new ShellUtil();
	private RegisterUtils registerUtils=new RegisterUtils();  //ע�Ṥ�߰� 
	private RegisterInfo registerInfo=new RegisterInfo(); //ע�����ݿ��ѯ��Ϣ��
	private SetEmail setEmail=new SetEmail();
	private ClassInfo classInfo=new ClassInfo(); //����ѧ����Ϣ���е����ݿ�
	private StuInfo stuInfo=new StuInfo();
	protected Shell shell;
	private Text text_1; //����
	private Text text_3; //��ַ
	private Text text_4;  //����
	private Text text_5;	//��ϵ�绰
	private Text text_6;    //QQ
	private Text text_7;  //����
	private Text text_9;   //ȷ������
	

	private Label label;  //ͷ��
	private Label label_11;  //�ж������Ƿ���ȷ��ͼ��
	private Label label_12; //�жϵ绰�����Ƿ���ȷ��ͼ��
	private Label label_14;  //�ж�����
	private Label label_15; // �ж������Ƿ���ȷ
	private Label label_18; //����λ����ʾ��
	private Button button;   //ѡ��ͼƬ��ť
	private Button button_1;   //������֤
	private Button button_3;  //�Ա�Ů
	private Button button_4;  //ע��
	private Spinner spinner;  //����
	private CCombo combo; //�༶
	private Composite composite;
	private Text text_2;
	private String Numbers; //��֤��
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StudentRegister window = new StudentRegister();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!shell.getDisplay().readAndDispatch()) {
				shell.getDisplay().sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell(SWT.NONE);
		shell.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/���ͷ��_�ο�.png"));
		shell.setSize(800, 850);
		shell.setText("\u6559\u5E08\u6CE8\u518C");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		shellUtil.ShowCenter(shell);      //������ʾ
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		composite = new Composite(sashForm, SWT.NONE);
		composite.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		composite.setBackgroundImage(SWTResourceManager.getImage(StudentRegister.class, "/images/paper_StudentRegister.png"));

		
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		label = new Label(composite, SWT.BORDER);
		label.setBounds(179, 207, 127, 124);
		
		button = new Button(composite, SWT.NONE);
		button.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/select_1.png"));
		button.setFont(SWTResourceManager.getFont("�������ռ���", 9, SWT.NORMAL));
		button.setBounds(201, 337, 81, 27);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_2.setBounds(327, 207, 81, 26);
		label_2.setText("\u59D3   \u540D\uFF1A");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(431, 209, 158, 26);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_3.setBounds(327, 258, 81, 26);
		label_3.setText("\u5BC6   \u7801\uFF1A");
		
		text_7 = new Text(composite, SWT.BORDER|SWT.PASSWORD);
		text_7.setBounds(431, 260, 158, 26);
		
		Label label_10 = new Label(composite, SWT.NONE);
		label_10.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_10.setBounds(327, 308, 98, 26);
		label_10.setText("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		
		text_9 = new Text(composite, SWT.BORDER|SWT.PASSWORD);
		text_9.setBounds(431, 311, 158, 26);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_7.setBounds(327, 357, 81, 32);
		label_7.setText("\u6027    \u522B\uFF1A");
		
		Group group = new Group(composite, SWT.NONE);
		group.setBackgroundMode(SWT.INHERIT_DEFAULT);
		group.setBounds(431, 340, 149, 56);
		
		button_3 = new Button(group, SWT.RADIO);
		button_3.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		button_3.setAlignment(SWT.CENTER);
		button_3.setBounds(89, 15, 59, 36);
		button_3.setText("\u5973");
		
		Button button_2 = new Button(group, SWT.RADIO);
		button_2.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		button_2.setAlignment(SWT.CENTER);
		button_2.setBounds(10, 15, 59, 36);
		button_2.setSelection(true);
		button_2.setText("\u7537");
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_4.setBounds(197, 478, 93, 27);
		label_4.setText("\u90AE       \u7BB1\uFF1A");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setBounds(304, 477, 317, 23);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_5.setBounds(197, 528, 97, 27);
		label_5.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		
		text_5 = new Text(composite, SWT.BORDER);
		text_5.setBounds(304, 527, 317, 23);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_6.setBounds(197, 576, 93, 27);
		label_6.setText("\u5730       \u5740\uFF1A");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setBounds(304, 575, 317, 23);
		
		
		
	
		
		button_1 = new Button(composite, SWT.NONE);   //������֤
		button_1.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/EmailRegister.gif"));
		button_1.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		button_1.setBounds(201, 637, 105, 35);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/close_normal.png"));
		label_8.setBounds(741, 25, 45, 45);
		shellUtil.closeOp(shell, label_8);
		
		Label label_9 = new Label(composite, SWT.NONE);
		label_9.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/shrink_normal.png"));
		label_9.setBounds(690, 25, 45, 45);
		shellUtil.minOp(shell, label_9);
		sashForm.setWeights(new int[] {155});
		shellUtil.shellMove(composite, shell);
		
		label_11 = new Label(composite, SWT.NONE);
		label_11.setBounds(637, 473, 32, 32);
		
		label_12 = new Label(composite, SWT.NONE);
		label_12.setBounds(637, 523, 32, 32);
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("�������ռ���", 9, SWT.NORMAL));
		text_2.setText("��������֤��");
		text_2.setBounds(327, 646, 110, 26);
		
		button_4 = new Button(composite, SWT.NONE);
		button_4.setFont(SWTResourceManager.getFont("�������ռ���", 15, SWT.NORMAL));
		
		button_4.setBounds(304, 714, 219, 43);
		button_4.setText("\u6CE8\u518C");
		
		label_14 = new Label(composite, SWT.NONE);
		label_14.setBounds(607, 207, 32, 32);
		
		label_15 = new Label(composite, SWT.NONE);
		label_15.setBounds(607, 308, 32, 32);
		
		Label lblQq = new Label(composite, SWT.NONE);
		lblQq.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		lblQq.setBounds(201, 416, 62, 20);
		lblQq.setText("Q   Q: ");
		
		text_6 = new Text(composite, SWT.BORDER);
		text_6.setBounds(269, 413, 156, 23);
		
		Label label_16 = new Label(composite, SWT.NONE);
		label_16.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_16.setBounds(431, 414, 53, 20);
		label_16.setText("\u5E74\u9F84\uFF1A");
		
		spinner = new Spinner(composite, SWT.BORDER);
		spinner.setMaximum(36);
		spinner.setMinimum(7);
		spinner.setBounds(488, 410, 57, 26);
		
		Label label_17 = new Label(composite, SWT.NONE);
		label_17.setFont(SWTResourceManager.getFont("�������ռ���", 12, SWT.NORMAL));
		label_17.setBounds(565, 416, 53, 20);
		label_17.setText("\u73ED\u7EA7\uFF1A");
		
		combo = new CCombo(composite, SWT.BORDER);
		combo.setBounds(622, 410, 92, 25);
		combo.setItems(classInfo.findCId());
		
		label_18 = new Label(composite, SWT.NONE);
		label_18.setVisible(false);
		label_18.setFont(SWTResourceManager.getFont("΢���ź�", 9, SWT.NORMAL));
		label_18.setBounds(595, 260, 178, 26);
		label_18.setText("\u75316-15\u4F4D\u6570\u5B57\u548C\u5B57\u6BCD\u7EC4\u6210");
	
		
		//��֤�����Ƿ���ȷ
		text_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text_1.setText("");  //���ԭ�е�
				
				text_1.addMouseTrackListener(new MouseTrackAdapter() {
					@Override
					public void mouseExit(MouseEvent e) {
						String name=text_1.getText();
						registerUtils.ChineseNameTest(name);
						if(name.equals("") || registerUtils.ChineseNameTest(name)){
							if(registerUtils.ChineseNameTest(name)){
								label_14.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/right.png"));
							}else{
								label_14.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/error.png"));
							}
						}else{
							MessageBox messageBox=new MessageBox(shell,SWT.ICON_WARNING);
							messageBox.setText("���棡");
							messageBox.setMessage("����Ϊ2��6λ��������������");
							messageBox.open();
						}
					}
				});
			}
		});
		
		//��һ����������  ��ʾ��ʾ��
		text_7.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label_18.setVisible(true);
			}
		});
		
		
		
		//��֤�����Ƿ���ȷ   ��Ҫ��
		text_9.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text_9.addMouseTrackListener(new MouseTrackAdapter() {
					@Override
					public void mouseExit(MouseEvent e) {
						String password=text_9.getText();
						String Text7=text_7.getText();
						registerUtils.isPassWord(password);
						if(password.equals("") || registerUtils.isPassWord(password)){
							if(registerUtils.isPassWord(password) && Text7.equals(password)){
								label_15.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/right.png"));
							}else{
								label_15.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/error.png"));
							}
						}else{
							MessageBox messageBox=new MessageBox(shell,SWT.ICON_WARNING);
							messageBox.setText("���棡");
							messageBox.setMessage("������д�������������룡");
							messageBox.open();
							text_9.setText("");
							text_7.setText("");
						}
						
					}
				});
			}
		});
		
		
		//���������Ƿ���ȷ
		text_4.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String str=text_4.getText();
				registerUtils.isEmail(str);
				if(registerUtils.isEmail(str)){
					label_11.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/right.png"));
				}else{
					label_11.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/error.png"));
				}
			}
		});
		
		//����绰�Ƿ���ȷ
		text_5.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String str=text_5.getText();
				registerUtils.isPhoneNumberValid(str);
				if(registerUtils.isPhoneNumberValid(str)){
					label_12.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/right.png"));
				}else{
					label_12.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/error.png"));
				}
			}
		});
		
		//���Ĭ�ϵ����֣���������֤��
		text_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text_2.setText("");
			}
		});
		
		
		button_1.addMouseTrackListener(new MouseTrackAdapter() {
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/EmailRegister.gif"));
			}
			//���������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/EmailRegister1.gif"));
			}
		});
		
		//������֤����
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 RegisterUtils registerUtils=new RegisterUtils();
		    	 String Number=null;   //����֤��
		    	 if(text_4.getText()==null || text_4.getText()==("") || text_4.getText().length()<0){
		    		 MessageBox mgb=new MessageBox(shell,SWT.ERROR);
						mgb.setText("���棡");
						mgb.setMessage("������������δ�����");
						mgb.open();
		    	 }else{
		    		 Numbers=registerUtils.RandomAuthCode(Number);
		    		 System.out.println(Numbers);
				        try {
				        	setEmail.sendMail(text_4.getText(), "��֤�룺", Numbers,  null);
						} catch (Exception ex) {
							System.out.println("�����ַ����");
							ex.printStackTrace();
						}  
		    	 }
			}
		});
		
		
		
		//ѡ��ͼƬ��ť
		button.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/select_1.png"));
				
			}
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button.setImage(SWTResourceManager.getImage(StudentRegister.class, "/images/select_3.png"));
			}
			});
		
		
		
		
		
		//ע��
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Map<String,Object>> list =registerInfo.findMaxStuId();
				String stuid1=(String) list.get(0).get("STUID");     //��ʦ�˺�
				int stuid11=Integer.parseInt(stuid1);
				stuid11++;
				String stuId="S"+stuid11;
				String sName=text_1.getText().trim();  //����
				String sPwd=text_9.getText().trim();	//����
				String sAddr=text_3.getText().trim();   //��ַ
				String sEmail=text_4.getText().trim();  //����
				String sTel=text_5.getText().trim();   //�绰
				String sAge=spinner.getText().trim();    //����
				String sClass=combo.getText().trim();    //�༶
				String sQq=text_6.getText().trim();   //qq
				String sSex="��";
				if(button_3.getSelection()){
					sSex="Ů";
				} else{
					sSex="��";
				}

				byte[] sPhoto=null; //��Ƭ
				if(path!=null && !"".equals(path)){
					File file=new File(path);
					sPhoto=new byte[(int) file.length()];
					InputStream is=null;
					
					try {
						is=new FileInputStream(file);
						is.read(sPhoto);
					} catch (FileNotFoundException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					} finally{
						if(is!=null){
							try {
								is.close();
							} catch (IOException e1) {
								e1.printStackTrace();
							}
						}
					}
				}
				if(stuInfo.addStudentInfo(stuId, sName, sPwd, sClass, sSex, sAge, sAddr, sQq, sEmail, sTel, sPhoto)>0){
					if(Numbers.equals(text_2.getText())){
						MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION|SWT.OK);
						mgb.setText("�ɹ���ʾ");
						mgb.setMessage("������֤�ɹ�....");
						int result=mgb.open();
						if(result==64){
//							shell.dispose();
//							Display.getCurrent().close();   //��������
//							System.exit(0);
							System.out.println("����ע��ɹ���");
						}
					} else{
						MessageBox mgb=new MessageBox(shell,SWT.ICON_ERROR);
						mgb.setText("ʧ����ʾ");
						mgb.setMessage("ѧ����Ϣע��ʧ��....");
						mgb.open();
					}
					MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION|SWT.OK);
					mgb.setText("�ɹ���ʾ");
					mgb.setMessage("ѧ����Ϣע��ɹ�....���ĵ�¼�˺�Ϊ:"+stuId);
					mgb.open();
					Login login=new Login();
					shell.dispose();   //�ر����
					login.open();
					
					
				}else{
					MessageBox messageBox=new MessageBox(shell,SWT.ERROR|SWT.RETRY|SWT.ABORT);
					messageBox.setText("ʧ����ʾ��");
					messageBox.setMessage("ע����Ϣ�����Ƿ�������д?");
					messageBox.open();
				}
			}
		});
		
		//composite ��屳��ͼ����Ӧ
		composite.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				try {
					composite.setBackgroundImage(  shellUtil.ImageSize(new FileInputStream(new File("bin/images/paper_StudentRegister.png")),
					composite.getBounds().width,composite.getBounds().height    )    );
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		//ѡ��ͼ��
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				FileDialog fd=new FileDialog(shell);
				fd.setText("ͼ��ѡ��");
				fd.setFilterExtensions(new String[]{"*.jpg","*.gif","*.png"}); //��ѡ�ļ�����
				path=fd.open();
				if(path!=null && !"".equals(path)){
					label.setImage(su.getImage(label, path)); //��label����ʾͼƬ
				}
			}
		});
	}
}
