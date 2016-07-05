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
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.exam.login.Login;
import com.exam.utils.RegisterInfo;
import com.exam.utils.ShellUtil;
import com.exam.utils.TeacherInfo;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.MouseEvent;

 /**
 * 注册学生信息
 * @author Administrator
 *
  */
 public class TeacherRegister {
	private ShellUtil su=new ShellUtil();
	private String path=""; //学生图像路径
	private ShellUtil shellUtil=new ShellUtil();
	private RegisterUtils registerUtils=new RegisterUtils();  //注册工具包 
	private RegisterInfo registerInfo=new RegisterInfo(); //注册数据库查询信息包
	private TeacherInfo teacherInfo =new TeacherInfo();
	private SetEmail setEmail=new SetEmail();
	private Display display;
	protected Shell shell;
	private Text text_1; //姓名
	private Text text_3; //地址
	private Text text_4;  //邮箱
	private Text text_5;	//联系电话
	private Text text_7;  //密码
	private Text text_9;   //确认密码

	private Label label;  //头像
	private Label label_11;  //判断邮箱是否正确的图标
	private Label label_12; //判断电话号码是否正确的图标
	private Label label_14;  //判断姓名
	private Label label_15; // 判断密码是否正确
	private Label label_16; //给输入密码提示框
	private Button button_3;  //性别女
	private Button button_4;  //注册
	private Composite composite;
	private Text text_2;
	private String Numbers; //验证码
	private Button button;  //头像选择按钮
	private Button button_1; //注册按钮
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TeacherRegister window = new TeacherRegister();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 * @wbp.parser.entryPoint
	 */
	protected void createContents() {
		shell = new Shell(SWT.NONE);
		shell.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/睿友头像_镂空.png"));
		shell.setSize(800, 850);
		shell.setText("\u6559\u5E08\u6CE8\u518C");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		shellUtil.ShowCenter(shell);      //居中显示
		
		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		composite = new Composite(sashForm, SWT.NONE);
		composite.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		composite.setBackgroundImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/paper_TeacherRegister.png"));

		
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		
		label = new Label(composite, SWT.BORDER);
		
		label.setBounds(179, 207, 127, 124);
		
		button = new Button(composite, SWT.NONE);
		button.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/select_1.png"));
		button.setFont(SWTResourceManager.getFont("方正综艺简体", 9, SWT.NORMAL));
		button.setBounds(197, 339, 80, 27);
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_2.setBounds(327, 217, 81, 26);
		label_2.setText("\u59D3   \u540D\uFF1A");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(431, 216, 158, 26);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_3.setBounds(327, 264, 81, 26);
		label_3.setText("\u5BC6   \u7801\uFF1A");
		
		text_7 = new Text(composite, SWT.BORDER|SWT.PASSWORD);
		text_7.setBounds(431, 266, 158, 26);
		
		Label label_10 = new Label(composite, SWT.NONE);
		label_10.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_10.setBounds(327, 316, 98, 26);
		label_10.setText("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		
		text_9 = new Text(composite, SWT.BORDER|SWT.PASSWORD);
		
		text_9.setBounds(431, 319, 158, 26);
		
		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_7.setBounds(327, 375, 81, 32);
		label_7.setText("\u6027    \u522B\uFF1A");
		
		Group group = new Group(composite, SWT.NONE);
		group.setBackgroundMode(SWT.INHERIT_DEFAULT);
		group.setBounds(431, 358, 149, 56);
		
		button_3 = new Button(group, SWT.RADIO);
		button_3.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		button_3.setAlignment(SWT.CENTER);
		button_3.setBounds(89, 15, 59, 36);
		button_3.setText("\u5973");
		
		Button button_2 = new Button(group, SWT.RADIO);
		button_2.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		button_2.setAlignment(SWT.CENTER);
		button_2.setBounds(10, 15, 59, 36);
		button_2.setSelection(true);
		button_2.setText("\u7537");
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_4.setBounds(197, 437, 93, 27);
		label_4.setText("\u90AE       \u7BB1\uFF1A");
		
		text_4 = new Text(composite, SWT.BORDER);
		text_4.setBounds(304, 436, 317, 23);
		
		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_5.setBounds(197, 504, 97, 27);
		label_5.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");
		
		text_5 = new Text(composite, SWT.BORDER);
		text_5.setBounds(304, 503, 317, 23);
		
		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_6.setBounds(197, 576, 93, 27);
		label_6.setText("\u5730       \u5740\uFF1A");
		
		text_3 = new Text(composite, SWT.BORDER);
		text_3.setBounds(304, 575, 317, 23);
		
		
		
	
		
		button_1 = new Button(composite, SWT.NONE);   //邮箱注册
		button_1.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/EmailRegister.gif"));
		button_1.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		button_1.setBounds(197, 629, 100, 30);
		
		Label label_8 = new Label(composite, SWT.NONE);
		label_8.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/close_normal.png"));
		label_8.setBounds(741, 25, 47, 43);
		shellUtil.closeOp(shell, label_8);
		
		Label label_9 = new Label(composite, SWT.NONE);
		label_9.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/shrink_normal.png"));
		label_9.setBounds(690, 25, 47, 43);
		shellUtil.minOp(shell, label_9);
		sashForm.setWeights(new int[] {155});
		shellUtil.shellMove(composite, shell);
		
		label_11 = new Label(composite, SWT.NONE);
		label_11.setBounds(637, 432, 32, 32);
		
		label_12 = new Label(composite, SWT.NONE);
		label_12.setBounds(637, 499, 32, 32);
		
		text_2 = new Text(composite, SWT.BORDER);
		text_2.setFont(SWTResourceManager.getFont("方正综艺简体", 9, SWT.NORMAL));
		text_2.setText("请输入验证码");
		text_2.setBounds(304, 632, 104, 26);
		
		button_4 = new Button(composite, SWT.NONE);
		button_4.setFont(SWTResourceManager.getFont("方正综艺简体", 15, SWT.NORMAL));
		
		button_4.setBounds(269, 727, 233, 43);
		button_4.setText("\u6CE8\u518C");
		
		label_14 = new Label(composite, SWT.NONE);
		label_14.setBounds(607, 219, 32, 32);
		
		label_15 = new Label(composite, SWT.NONE);
		label_15.setBounds(607, 316, 32, 32);
		
		label_16 = new Label(composite, SWT.NONE);
		label_16.setVisible(false);
		label_16.setBounds(597, 266, 179, 20);
		label_16.setText("\u75316-15\u4F4D\u6570\u5B57\u548C\u5B57\u6BCD\u7EC4\u6210");
		
		

		//选择图片按钮
		button.addMouseTrackListener(new MouseTrackAdapter(){
			//鼠标移开时
			@Override
			public void mouseExit(MouseEvent e) {
				button.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/select_1.png"));

			}

			//鼠标移上时
			@Override
			public void mouseHover(MouseEvent e) {
				button.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/select_3.png"));
			}
		});

		
		//验证姓名是否正确
		text_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text_1.setText("");  //清空原有的
				
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
							messageBox.setText("警告！");
							messageBox.setMessage("必须为2到6位的中文名！！！");
							messageBox.open();
						}
					}
				});
			}
		});
		//给输入密码提示框
		text_7.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label_16.setVisible(true);
			}
		});
	
		
		//验证密码是否正确   需要改
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
								label_15.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/right.png"));
							}else{
								label_15.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/error.png"));
							}
						}else{
							MessageBox messageBox=new MessageBox(shell,SWT.ICON_WARNING);
							messageBox.setText("警告！");
							messageBox.setMessage("密码填写有误，请重新输入！");
							messageBox.open();
							text_9.setText("");
							text_7.setText("");
						}
						
					}
				});
			}
		});
		
		
		//检验邮箱是否正确
		text_4.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String str=text_4.getText();
				registerUtils.isEmail(str);
				if(registerUtils.isEmail(str)){
					label_11.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/right.png"));
				}else{
					label_11.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/error.png"));
				}
			}
		});
		
		//检验电话是否正确
		text_5.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String str=text_5.getText();
				registerUtils.isPhoneNumberValid(str);
				if(registerUtils.isPhoneNumberValid(str)){
					label_12.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/right.png"));
				}else{
					label_12.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/error.png"));
				}
			}
		});
		
		//清空默认的文字：请输入验证码
		text_2.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				text_2.setText("");
			}
		});
		
		button_1.addMouseTrackListener(new MouseTrackAdapter() {
			//鼠标离开时
			@Override
			public void mouseExit(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/EmailRegister.gif"));
			}
			//鼠标放在上面时
			@Override
			public void mouseHover(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(TeacherRegister.class, "/images/EmailRegister1.gif"));
			}
		});
		
		
		//邮箱验证监听
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				 RegisterUtils registerUtils=new RegisterUtils();
		    	 String Number=null;   //存验证码
		    	 if(text_4.getText()==null || text_4.getText()==("") || text_4.getText().length()<0){
		    		 MessageBox mgb=new MessageBox(shell,SWT.ERROR);
						mgb.setText("警告！");
						mgb.setMessage("邮箱错误或邮箱未填！！！");
						mgb.open();
		    	 }else{
		    		 Numbers=registerUtils.RandomAuthCode(Number);
		    		 System.out.println(Numbers);
				        try {
				        	setEmail.sendMail(text_4.getText(), "验证码：", Numbers,  null);
						} catch (Exception ex) {
							System.out.println("邮箱地址有误！");
							ex.printStackTrace();
						}  
		    	 }
			}
		});
		

		
		//注册
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				List<Map<String,Object>> list =registerInfo.findMaxTid();
				String tid1=(String) list.get(0).get("TID");     //教师账号
				int tid11=Integer.parseInt(tid1);
				tid11++;
				String tid="T"+tid11;
				String tname=text_1.getText().trim();  //姓名
				String tpwd=text_9.getText().trim();	//密码
				String taddr=text_3.getText().trim();   //地址
				String temail=text_4.getText().trim();  //邮箱
				String ttel=text_5.getText().trim();   //电话
				String tsex="男";
				if(button_3.getSelection()){
					tsex="女";
				} else{
					tsex="男";
				}

				byte[] tphoto=null; //照片
				if(path!=null && !"".equals(path)){
					File file=new File(path);
					tphoto=new byte[(int) file.length()];
					InputStream is=null;
					
					try {
						is=new FileInputStream(file);
						is.read(tphoto);
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
				if(teacherInfo.addTeacherInfo(tid, tname, tpwd, tphoto, tsex, taddr, ttel, temail)>0){
					
					if(Numbers.equals(text_2.getText())){
						MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION|SWT.OK);
						mgb.setText("成功提示");
						mgb.setMessage("邮箱验证成功....");
					} else{
						MessageBox mgb=new MessageBox(shell,SWT.ICON_ERROR);
						mgb.setText("失败提示");
						mgb.setMessage("教师信息注册失败....");
						mgb.open();
					}
				
					MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION|SWT.OK);
					mgb.setText("成功提示");
					mgb.setMessage("教师信息注册成功...."+"你的登录账号为:"+tid);
					mgb.open();
					Login login=new Login();
					shell.dispose();   //关闭面板
					login.open();
				}
					
			}
		});
		
		
		

		
		
		//composite 面板背景图自适应
		composite.addControlListener(new ControlAdapter() {
			@Override
			public void controlResized(ControlEvent e) {
				try {
					composite.setBackgroundImage(  shellUtil.ImageSize(new FileInputStream(new File("bin/images/paper_TeacherRegister.png")),
					composite.getBounds().width,composite.getBounds().height    )    );
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		//选择图像
		button.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
				FileDialog fd=new FileDialog(shell);
				fd.setText("图像选择");
				fd.setFilterExtensions(new String[]{"*.jpg","*.gif","*.png"}); //可选文件类型
				path=fd.open();
				if(path!=null && !"".equals(path)){
					label.setImage(su.getImage(label, path)); //在label中显示图片
				}
			}
		});
	}
}
