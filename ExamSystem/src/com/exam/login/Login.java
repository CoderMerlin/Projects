package com.exam.login;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Text;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import com.exam.dao.UserInfoDao;
import com.exam.register.Register;
import com.exam.register.RegisterUtils;
import com.exam.studentop.StuOp;
import com.exam.teacherop.TeacherOp;
import com.exam.utils.ShellUtil;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;


/**
 * 登录界面
 * @author Administrator
 *
 */
public class Login {

	protected Shell shell;
	private Text text_1;       //密码框
	private Label label_2;    //显示错误框
	private Button button;   // 考生登录
	private Button button_1;  //教师按钮
	private Button button_2;  //登录按钮
	private Button button_3;  //记住密码框
	private Link link;   //注册
	private Link link_1;//找回密码
	private CCombo combo; //学生下拉菜单
	
	private Composite composite; //
	private Display display;
	
	private ShellUtil shellUtil =new ShellUtil();
	private RegisterUtils registerUtils= new RegisterUtils();
	private UserInfoDao userInfoDao=new UserInfoDao();
	private Map<String,String> map=null;
	private CCombo combo_1;
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Login window = new Login();
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
	 */
	protected void createContents() {
		shell = new Shell(SWT.NONE);
		shell.setFullScreen(true);
		shell.setImage(SWTResourceManager.getImage(Login.class, "/images/睿友头像_镂空.png"));
		shellUtil.tray(display,shell,"睿友");  //启动托盘
		shell.setSize(1400, 600);
		shell.setText("睿友");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		ShowCenter(shell);      //居中显示
		
		
		composite = new Composite(shell, SWT.NONE);
		composite.setBackground(SWTResourceManager.getColor(SWT.COLOR_WIDGET_LIGHT_SHADOW));
		composite.setBackgroundImage(SWTResourceManager.getImage(Login.class, "/images/blackboard..png"));
        composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
        composite.setLayout(null);
        
		
		
		Label label = new Label(composite, SWT.NONE);
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label.setBounds(624, 228, 76, 25);
		
		label.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.BOLD));
		label.setText("账  号：");
		
		button = new Button(composite, SWT.RADIO);
		button.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button.setBounds(653, 181, 130, 25);
		
		button.setText("考生登录");
		
		button_1 = new Button(composite, SWT.RADIO);
		button_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button_1.setSelection(true);
		button_1.setBounds(806, 183, 119, 20);
		button_1.setText("教师登录");
		
		Label label_1 = new Label(composite, SWT.NONE);
		label_1.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		label_1.setBounds(624, 298, 76, 25);
		label_1.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.BOLD));
		label_1.setText("密  码：");
		
		text_1 = new Text(composite, SWT.BORDER | SWT.PASSWORD);
		text_1.setBounds(726, 296, 211, 26);
		
		button_2 = new Button(composite, SWT.NONE);
		button_2.setImage(SWTResourceManager.getImage(Login.class, "/images/Login_1.gif"));
		button_2.setBounds(817, 374, 100, 30);
		
		shellUtil.shellMove(composite, shell);
		
		combo = new CCombo(composite, SWT.BORDER);
		combo.setBounds(726, 227, 211, 26);
		
		button_3 = new Button(composite, SWT.CHECK);
		button_3.setSelection(false);
	
		button_3.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		button_3.setBounds(953, 299, 107, 20);
		button_3.setFont(SWTResourceManager.getFont("宋体", 12, SWT.NORMAL));
		button_3.setText("记住密码");
		
		label_2 = new Label(composite, SWT.HORIZONTAL);
		label_2.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label_2.setAlignment(SWT.CENTER);
		label_2.setBounds(663, 150, 220, 25);
		label_2.setFont(SWTResourceManager.getFont("微软雅黑", 10, SWT.BOLD));
		
		link = new Link(composite, SWT.NONE);
		link.setForeground(SWTResourceManager.getColor(SWT.COLOR_WHITE));
		link.setFont(SWTResourceManager.getFont("方正综艺简体", 10, SWT.BOLD));
		link.setBounds(693, 358, 118, 20);
		link.setText("<a>注      册</a>");
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setImage(SWTResourceManager.getImage(Login.class, "/images/close_normal.png"));
		label_3.setBounds(1319, 29, 47, 43);
		shellUtil.closeOp(shell, label_3);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setImage(SWTResourceManager.getImage(Login.class, "/images/shrink_normal.png"));
		label_4.setBounds(1266, 27, 47, 43);
		shellUtil.minOp(shell, label_4);
		
		combo_1 = new CCombo(composite, SWT.BORDER);
		combo_1.setBounds(726, 227, 211, 26);
		
		link_1 = new Link(composite, SWT.NONE);
		
		link_1.setFont(SWTResourceManager.getFont("方正综艺简体", 10, SWT.NORMAL));
		link_1.setBounds(693, 384, 118, 20);
		link_1.setText("<a>找 回 密 码</a>");
	
		
		//读取学生账号监听
		//从注册表中读取保存的账号信息
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				combo.setVisible(true);
				combo_1.setVisible(false);
				if(button.getSelection()){
					map=registerUtils.getStudentRecord();
					if(map!=null&&!"".equals(map)&&map.size()>0){
						Set<String> keys=map.keySet();
						for(String key:keys){
							combo.add(key);   //将所有的账号循环添加到下拉列表中
						}
						combo.select(0);  //默认选中第一个
						//获取当前账号的密码
						text_1.setText(map.get( combo.getText().trim() ) );
					}
				}
				
			}
		});
		
		//当值发生改变时,获取对应账号的密码
		combo.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0){
				if(map.get( combo.getText().trim() ) != null ){
					text_1.setText( map.get( combo.getText().trim() ) );
				}
			}
		});
		

		//读取教师账号监听
		button_1.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				if(button_1.getSelection()){
					combo.setVisible(false);
					combo_1.setVisible(true);
					map=registerUtils.getTeacherRecord();
					if(map!=null && !"".equals(map) && map.size()>0){
						Set<String> keys=map.keySet();
						for(String key:keys){
							if(key!=null && !"".equals(key)){
								combo_1.add(key);   //将所有的账号循环添加到下拉列表中
							}
						}
						combo_1.select(0);  //默认选中第一个
						//获取当前账号的密码
						text_1.setText(map.get( combo_1.getText().trim() ) );
					}
				}
			}
		});
		
		//教师的combo_1框当值发生改变时,获取对应账号的密码
		combo_1.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent arg0){
				if(map.get( combo_1.getText().trim() ) != null ){
					text_1.setText( map.get( combo_1.getText().trim() ) );
				}
			}
		});
	
////		//从注册表中读取保存的账号信息
		
		
		//账号框
		combo.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label_2.setText("");
			}
		});
		text_1.addFocusListener(new FocusAdapter() {
			@Override
			public void focusGained(FocusEvent e) {
				label_2.setText("");
			}
		});
		
		//改变面板大小使背景自适应
		composite.addControlListener(new ControlAdapter() {   
			public void controlResized(ControlEvent e) {
				try {
					composite.setBackgroundImage(  shellUtil.ImageSize(new FileInputStream(new File("bin/images/blackboard..png")),
					composite.getBounds().width,composite.getBounds().height    )    );
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		
		//出发注册面板
		link.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				Register register=new Register();
				shell.dispose();  
				register.open();
				
			}
		});
		
		//找回密码
		link_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseUp(MouseEvent e) {
				RetrievePwd retrievePwd =new RetrievePwd();
				shell.dispose();
				retrievePwd.open();
			}
		});
		
		//登录鼠标移开时
		button_2.addMouseTrackListener(new MouseTrackAdapter() {
			@Override
			public void mouseExit(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(Login.class, "/images/Login_1.gif"));
			}
			//鼠标移上时
			@Override
			public void mouseHover(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(Login.class, "/images/Login_2.gif"));
			}
		});
		
		//点击登录
		button_2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				
				String susid=combo.getText().trim();
				String tusid=combo_1.getText().trim();
				String pwd=text_1.getText().trim();
				if(button.getSelection()){   //学生登录进来
					if(susid==null ||"".equals(susid)){
						label_2.setText("请输入账号...");
					}
					
					if(pwd==null ||"".equals(pwd)){
						label_2.setText("请输入密码...");
					}
					
					//访问数据库__学生
					Map<String,Object> userInfo=userInfoDao.studentLogin(susid, pwd);
					if(userInfo!=null){
						//判断用户是否选择了保存密码
						if(button_3.getSelection()){  //选择记住当前账号
							Map<String,String> map=new HashMap<String,String>();
							map.put(susid, pwd);
							registerUtils.addStudentRecord(map);
						}else{  //取消记住当前账号
							
						}
						StuOp stuOp=new StuOp();
						stuOp.setStuid(combo.getText());
						shell.dispose();
						stuOp.open();
					}else{
						label_2.setText("用户名或密码错误...");
					}
				}else if(button_1.getSelection()){ //否则就是教师登录
					if(tusid==null ||"".equals(tusid)){
						label_2.setText("请输入账号...");
					}
					
					if(pwd==null ||"".equals(pwd)){
						label_2.setText("请输入密码...");
					}
					
					Map<String,Object> userInfo=userInfoDao.teacherLogin(tusid, pwd);
					if(userInfo!=null){
						//判断用户是否选择了保存密码
						Map<String,String> map=new HashMap<String,String>();
						if(button_3.getSelection()){  //选择记住当前账号
							System.out.println(map);
							map.put(tusid, pwd);
							registerUtils.addTeacherRecord(map);
						}else{  //取消记住当前账号
							
						}
						TeacherOp teacherOp=new TeacherOp();
						teacherOp.setTid(combo_1.getText());
						shell.dispose();
						teacherOp.open();
					}else{
						MessageBox messageBox1=new MessageBox(shell,SWT.ICON_ERROR|SWT.RETRY|SWT.CANCEL);
						messageBox1.setText("错误提示！");
						messageBox1.setMessage("用户名或密码错误！");
						
						int result=messageBox1.open();
						if(result==256){ //如果取消，说明不做任何事情
						}else if(result==1024){  //重试
							combo.setText("");    //清除账号框
							text_1.setText(""); //清除密码框
						}
						label_2.setText("用户名或密码错误...");
					}
				}
			}
		});
	}
	
	//使面板居中
	public static void ShowCenter(Shell shell){
		shell.setLocation( (Display.getDefault().getClientArea().width-shell.getSize().x)/2,
				(Display.getDefault().getClientArea().height-shell.getSize().y)/2);
	}
}

