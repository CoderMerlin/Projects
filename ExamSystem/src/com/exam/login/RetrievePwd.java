package com.exam.login;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;

import com.exam.register.RegisterUtils;
import com.exam.register.SetEmail;
import com.exam.utils.RegisterInfo;
import com.exam.utils.ShellUtil;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

public class RetrievePwd {

	protected Shell shell;
	private Text text;
	private Text text_1;
	private Text text_2;
	private Text text_3;
	private SetEmail setEmail=new SetEmail();
	private RegisterUtils registerUtils=new RegisterUtils();  //注册工具包 
	private RegisterInfo registerInfo=new RegisterInfo();
	private ShellUtil shellUtil=new ShellUtil();
	private Button button_1; //教师账号
	private Button button;  //邮箱验证
	private Button button_3; //确认修改
	private Button button_2;  //学生账号
	
	private Label label_6;  //邮箱的正确与否
	private Label label_4;  //密码更新是否正确
	private Label lblSas;   //验证码是否正确
	private String Numbers; //验证码


	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
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
	 */
	protected void createContents() {
		shell = new Shell(SWT.NONE);
		shell.setBackgroundMode(SWT.INHERIT_DEFAULT);
		shell.setBackgroundImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/RetrievePwd_1.png"));
		shell.setSize(640, 500);
		
		shellUtil.ShowCenter(shell);      //居中显示
		
		Label label = new Label(shell, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label.setBounds(143, 188, 84, 23);
		label.setText("\u90AE    \u7BB1\uFF1A");
		
		text = new Text(shell, SWT.BORDER);
		text.setBounds(273, 188, 212, 26);
		
		button = new Button(shell, SWT.NONE);
		
		button.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		button.setBounds(143, 236, 104, 30);
		button.setText("\u90AE\u7BB1\u9A8C\u8BC1\uFF1A");
		
		text_1 = new Text(shell, SWT.BORDER);
		text_1.setBounds(273, 236, 131, 26);
		
		text_2 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_2.setBounds(273, 290, 212, 26);
		
		Label label_1 = new Label(shell, SWT.NONE);
		label_1.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_1.setBounds(144, 291, 89, 20);
		label_1.setText("\u65B0 \u5BC6 \u7801\uFF1A");
		
		Label label_2 = new Label(shell, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_2.setBounds(144, 349, 96, 20);
		label_2.setText("\u786E\u8BA4\u5BC6\u7801\uFF1A");
		
		text_3 = new Text(shell, SWT.BORDER | SWT.PASSWORD);
		text_3.setBounds(273, 348, 212, 26);
		
		lblSas = new Label(shell, SWT.NONE);
		lblSas.setBounds(410, 236, 32, 32);
		
		label_4 = new Label(shell, SWT.NONE);
		label_4.setBounds(491, 351, 32, 32);
		
		Label label_5 = new Label(shell, SWT.NONE);
		label_5.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_5.setBounds(283, 322, 212, 20);
		label_5.setText("\u5FC5\u987B\u75316-15\u4F4D\u6570\u5B57\u548C\u5B57\u6BCD\u7EC4\u6210");
		
		button_1 = new Button(shell, SWT.RADIO);
		
		button_1.setBounds(196, 149, 119, 32);
		button_1.setText("\u6559\u5E08\u8D26\u53F7");
		
		button_2 = new Button(shell, SWT.RADIO);
		button_2.setBounds(332, 155, 119, 20);
		button_2.setText("\u5B66\u751F\u8D26\u53F7");
		
		label_6 = new Label(shell, SWT.NONE);
		label_6.setBounds(491, 190, 32, 32);
		
		button_3 = new Button(shell, SWT.NONE);
		button_3.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		button_3.setBounds(235, 405, 156, 43);
		button_3.setText("\u786E\u5B9A\u4FEE\u6539");
		
		Label label_3 = new Label(shell, SWT.NONE);
		label_3.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/shrink_normal.png"));
		label_3.setBounds(532, 10, 45, 45);
		shellUtil.minOp(shell, label_3);
		
		Label label_7 = new Label(shell, SWT.NONE);
		label_7.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/close_normal.png"));
		label_7.setBounds(583, 10, 45, 45);
		shellUtil.closeOp(shell, label_7);
		
		
		//教师账号
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				
				//判断邮箱格式是否正确
				text.addFocusListener(new FocusAdapter() {
					public void focusLost(FocusEvent e) {
						String str=text.getText();
						registerUtils.isEmail(str);
						if(registerUtils.isEmail(str)){
							label_6.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/right.png"));
						}else{
							label_6.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/error.png"));
						}
					}
				});
				
				//发送验证码：
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						RegisterUtils registerUtils=new RegisterUtils();
				    	 String Number=null;   //存验证码
				    	 if(text.getText()==null || text.getText()==("") || text.getText().length()<0){
				    		 MessageBox mgb=new MessageBox(shell,SWT.ERROR);
								mgb.setText("警告！");
								mgb.setMessage("邮箱错误或邮箱未填！！！");
								mgb.open();
				    	 }else{
				    		 Numbers=registerUtils.RandomAuthCode(Number);
				    		 System.out.println(Numbers);
						        try {
						        	setEmail.sendMail(text.getText(), "验证码：", Numbers,  null);
								} catch (Exception ex) {
									System.out.println("邮箱地址有误！");
									ex.printStackTrace();
								}  
				    	 }
					}
				});
				
				//验证码是否正确
				text_1.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						
						text_1.addMouseTrackListener(new MouseTrackAdapter() {
							@Override
							public void mouseExit(MouseEvent e) {
								if(Numbers.equals(text_1.getText())){
									lblSas.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/right.png"));
								}else{
									lblSas.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/error.png"));
								}
							}
						});
					
					}
				});
				
				
				//验证密码是否正确   需要改
				text_3.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						text_3.addMouseTrackListener(new MouseTrackAdapter() {
							@Override
							public void mouseExit(MouseEvent e) {
								String password=text_2.getText();
								String Text7=text_3.getText();
								registerUtils.isPassWord(password);
								if(password.equals("") || registerUtils.isPassWord(password)){
									if(registerUtils.isPassWord(password) && Text7.equals(password)){
										label_4.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/right.png"));
									}else{
										label_4.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/error.png"));
									}
								}else{
									MessageBox messageBox=new MessageBox(shell,SWT.ICON_WARNING);
									messageBox.setText("警告！");
									messageBox.setMessage("密码填写有误，请重新输入！");
									messageBox.open();
									text_2.setText("");
									text_3.setText("");
								}
								
							}
						});
					}
				});
				
				
				
				//点击确认修改
				button_3.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						String tpwd=text_3.getText().trim();	//密码
						String temail=text.getText().trim();  //邮箱
						if(registerInfo.updateTpwd(tpwd, temail)>0){
							if(Numbers.equals(text_1.getText())){
								MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION|SWT.OK);
								mgb.setText("成功提示");
								mgb.setMessage("邮箱验证成功....");
								
								}else{
								MessageBox mgb=new MessageBox(shell,SWT.ICON_ERROR);
								mgb.setText("失败提示");
								mgb.setMessage("教师信息注册失败....");
								mgb.open();
							}
							
							MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION|SWT.OK);
							mgb.setText("成功提示");
							mgb.setMessage("密码修改成功！");
							mgb.open();
							Login login=new Login();
							shell.dispose();   //关闭面板
							login.open();
						}
						 
					}
				});
				
				
			}
		});
		
		
		
		
		button_2.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				//判断邮箱格式是否正确
				text.addFocusListener(new FocusAdapter() {
					public void focusLost(FocusEvent e) {
						String str=text.getText();
						registerUtils.isEmail(str);
						if(registerUtils.isEmail(str)){
							label_6.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/right.png"));
						}else{
							label_6.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/error.png"));
						}
					}
				});
				
				//发送验证码：
				button.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						RegisterUtils registerUtils=new RegisterUtils();
				    	 String Number=null;   //存验证码
				    	 if(text.getText()==null || text.getText()==("") || text.getText().length()<0){
				    		 MessageBox mgb=new MessageBox(shell,SWT.ERROR);
								mgb.setText("警告！");
								mgb.setMessage("邮箱错误或邮箱未填！！！");
								mgb.open();
				    	 }else{
				    		 Numbers=registerUtils.RandomAuthCode(Number);
				    		 System.out.println(Numbers);
						        try {
						        	setEmail.sendMail(text.getText(), "验证码：", Numbers,  null);
								} catch (Exception ex) {
									System.out.println("邮箱地址有误！");
									ex.printStackTrace();
								}  
				    	 }
					}
				});
				
				//验证码是否正确
				text_1.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						
						text_1.addMouseTrackListener(new MouseTrackAdapter() {
							@Override
							public void mouseExit(MouseEvent e) {
								if(Numbers.equals(text_1.getText())){
									lblSas.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/right.png"));
								}else{
									lblSas.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/error.png"));
								}
							}
						});
					
					}
				});
				
				
				//验证密码是否正确   需要改
				text_3.addFocusListener(new FocusAdapter() {
					@Override
					public void focusGained(FocusEvent e) {
						text_3.addMouseTrackListener(new MouseTrackAdapter() {
							@Override
							public void mouseExit(MouseEvent e) {
								String password=text_2.getText();
								String Text7=text_3.getText();
								registerUtils.isPassWord(password);
								if(password.equals("") || registerUtils.isPassWord(password)){
									if(registerUtils.isPassWord(password) && Text7.equals(password)){
										label_4.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/right.png"));
									}else{
										label_4.setImage(SWTResourceManager.getImage(RetrievePwd.class, "/images/error.png"));
									}
								}else{
									MessageBox messageBox=new MessageBox(shell,SWT.ICON_WARNING);
									messageBox.setText("警告！");
									messageBox.setMessage("密码填写有误，请重新输入！");
									messageBox.open();
									text_2.setText("");
									text_3.setText("");
								}
								
							}
						});
					}
				});
				
				
				
				//点击确认修改
				button_3.addSelectionListener(new SelectionAdapter() {
					@Override
					public void widgetSelected(SelectionEvent e) {
						String spwd=text_3.getText().trim();	//密码
						String semail=text.getText().trim();  //邮箱
						if(registerInfo.updateStupwd(spwd, semail)>0){
							if(Numbers.equals(text_1.getText())){
								MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION|SWT.OK);
								mgb.setText("成功提示");
								mgb.setMessage("邮箱验证成功....");
								
								}else{
								MessageBox mgb=new MessageBox(shell,SWT.ICON_ERROR);
								mgb.setText("失败提示");
								mgb.setMessage("教师信息注册失败....");
								mgb.open();
							}
							
							MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION|SWT.OK);
							mgb.setText("成功提示");
							mgb.setMessage("密码修改成功！");
							mgb.open();
							Login login=new Login();
							shell.dispose();   //关闭面板
							login.open();
						}
						 
					}
				});
				
				
			}
		});
		
		
		
	
		//composite 面板背景图自适应
		shell.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				try {
					shell.setBackgroundImage(  shellUtil.ImageSize(new FileInputStream(new File("bin/images/RetrievePwd_1.png")),
					shell.getBounds().width,shell.getBounds().height    )    );
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});

	}
}
