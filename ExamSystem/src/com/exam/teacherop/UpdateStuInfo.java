package com.exam.teacherop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
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

import com.exam.register.RegisterUtils;
import com.exam.utils.ClassInfo;
import com.exam.utils.ShellUtil;
import com.exam.utils.StuInfo;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.widgets.Spinner;
import org.eclipse.swt.custom.CCombo;

/**
 * 注册学生信息
 * @author Administrator
 *
 */
public class UpdateStuInfo {
	private String[] values;
	private String stuid;
	private ShellUtil su=new ShellUtil();

	private RegisterUtils registerUtils=new RegisterUtils();  //注册工具包 
	private String path=""; //学生图像路径
	private ShellUtil shellUtil=new ShellUtil();
	private ClassInfo classInfo=new ClassInfo(); //调用学生信息表中的数据库
	private StuInfo si=new StuInfo();
	protected Shell shell;
	private Text text_1; //姓名
	private Text text_3; //地址
	private Text text_4;  //邮箱
	private Text text_5;	//联系电话
	private Text text_6;    //QQ

	private Label label;  //头像
	private Label label_11;  //判断邮箱是否正确的图标
	private Label label_12; //判断电话号码是否正确的图标
	private Label label_14;  //判断姓名
	private Button button;   //选择图片按钮
	private Button button_3;  //性别女
	private Button button_4;  //注册
	private Spinner spinner;  //年龄
	private CCombo combo; //班级
	private Composite composite;
	
	public void setValues(String[] values,String stuid){
		this.values=values;
		this.stuid=stuid;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			UpdateStuInfo window = new UpdateStuInfo();
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
		shell.setSize(710, 750);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));

		shellUtil.ShowCenter(shell);      //居中显示

		SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		composite = new Composite(sashForm, SWT.NONE);
		composite.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		composite.setBackgroundImage(SWTResourceManager.getImage(UpdateStuInfo.class, "/images/updateStuInfo.png"));


		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);

		label = new Label(composite, SWT.BORDER);
		label.setBounds(149, 153, 102, 102);

		button = new Button(composite, SWT.NONE);
		button.setImage(SWTResourceManager.getImage(UpdateStuInfo.class, "/images/select_1.png"));
		button.setFont(SWTResourceManager.getFont("方正综艺简体", 9, SWT.NORMAL));
		button.setBounds(157, 269, 81, 27);

		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_2.setBounds(312, 152, 81, 26);
		label_2.setText("\u59D3   \u540D\uFF1A");

		text_1 = new Text(composite, SWT.BORDER);
		text_1.setText(values[0]);
		text_1.setBounds(416, 151, 158, 26);

		Label label_7 = new Label(composite, SWT.NONE);
		label_7.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_7.setBounds(312, 257, 81, 32);
		label_7.setText("\u6027   \u522B\uFF1A");

		Group group = new Group(composite, SWT.NONE);
		group.setBackgroundMode(SWT.INHERIT_DEFAULT);
		group.setBounds(416, 226, 158, 56);

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
		label_4.setBounds(137, 384, 93, 27);
		label_4.setText("\u90AE       \u7BB1\uFF1A");

		text_4 = new Text(composite, SWT.BORDER);
		text_4.setText(values[4]);
		text_4.setBounds(235, 383, 317, 23);

		Label label_5 = new Label(composite, SWT.NONE);
		label_5.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_5.setBounds(137, 442, 97, 27);
		label_5.setText("\u8054\u7CFB\u7535\u8BDD\uFF1A");

		text_5 = new Text(composite, SWT.BORDER);
		text_5.setText(values[5]);
		text_5.setBounds(235, 441, 317, 23);

		Label label_6 = new Label(composite, SWT.NONE);
		label_6.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_6.setBounds(136, 512, 93, 27);
		label_6.setText("\u5730       \u5740\uFF1A");

		text_3 = new Text(composite, SWT.BORDER);
		text_3.setText(values[6]);
		text_3.setBounds(235, 511, 317, 23);
		sashForm.setWeights(new int[] {155});
		shellUtil.shellMove(composite, shell);

		label_11 = new Label(composite, SWT.NONE);
		label_11.setBounds(574, 383, 32, 32);

		label_12 = new Label(composite, SWT.NONE);
		label_12.setBounds(574, 437, 32, 32);

		button_4 = new Button(composite, SWT.NONE);
		button_4.setFont(SWTResourceManager.getFont("方正综艺简体", 15, SWT.NORMAL));

		button_4.setBounds(242, 602, 219, 43);
		button_4.setText("\u4FEE\u6539\u4FE1\u606F");

		label_14 = new Label(composite, SWT.NONE);
		label_14.setBounds(590, 153, 32, 32);

		Label lblQq = new Label(composite, SWT.NONE);
		lblQq.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		lblQq.setBounds(312, 198, 81, 20);
		lblQq.setText("Q     Q: ");

		text_6 = new Text(composite, SWT.BORDER);
		text_6.setText(values[7]);
		text_6.setBounds(416, 197, 158, 23);

		Label label_16 = new Label(composite, SWT.NONE);
		label_16.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_16.setBounds(136, 334, 91, 20);
		label_16.setText("\u5E74       \u9F84\uFF1A");

		spinner = new Spinner(composite, SWT.BORDER);
		spinner.setMaximum(36);
		spinner.setMinimum(7);
		spinner.setSelection(Integer.valueOf(values[2]));
		spinner.setBounds(253, 333, 66, 26);

		Label label_17 = new Label(composite, SWT.NONE);
		label_17.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_17.setBounds(371, 334, 76, 23);
		label_17.setText("\u73ED   \u7EA7\uFF1A");

		combo = new CCombo(composite, SWT.BORDER);
		combo.setText(values[3]);
		combo.setBounds(471, 334, 92, 25);
		combo.setItems(classInfo.findCId());
		
		Label lblNew = new Label(composite, SWT.NONE);
		lblNew.setImage(SWTResourceManager.getImage(UpdateStuInfo.class, "/images/shrink_normal.png"));
		lblNew.setBounds(601, 10, 45, 45);
		shellUtil.minOp(shell, lblNew);
		
		Label label_3 = new Label(composite, SWT.NONE);
		label_3.setImage(SWTResourceManager.getImage(UpdateStuInfo.class, "/images/close_normal.png"));
		label_3.setBounds(652, 10, 45, 45);
		shellUtil.closeOp(shell, label_3);


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
								label_14.setImage(SWTResourceManager.getImage(UpdateStuInfo.class, "/images/right.png"));
							}else{
								label_14.setImage(SWTResourceManager.getImage(UpdateStuInfo.class, "/images/error.png"));
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


		//检验邮箱是否正确
		text_4.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String str=text_4.getText();
				registerUtils.isEmail(str);
				if(registerUtils.isEmail(str)){
					label_11.setImage(SWTResourceManager.getImage(UpdateStuInfo.class, "/images/right.png"));
				}else{
					label_11.setImage(SWTResourceManager.getImage(UpdateStuInfo.class, "/images/error.png"));
				}
			}
		});

		//检验电话是否正确
		text_5.addFocusListener(new FocusAdapter() {
			public void focusLost(FocusEvent e) {
				String str=text_5.getText();
				registerUtils.isPhoneNumberValid(str);
				if(registerUtils.isPhoneNumberValid(str)){
					label_12.setImage(SWTResourceManager.getImage(UpdateStuInfo.class, "/images/right.png"));
				}else{
					label_12.setImage(SWTResourceManager.getImage(UpdateStuInfo.class, "/images/error.png"));
				}
			}
		});


		//选择图片按钮
		button.addMouseTrackListener(new MouseTrackAdapter(){
			//鼠标移开时
			@Override
			public void mouseExit(MouseEvent e) {
				button.setImage(SWTResourceManager.getImage(UpdateStuInfo.class, "/images/select_1.png"));

			}
			//鼠标移上时
			@Override
			public void mouseHover(MouseEvent e) {
				button.setImage(SWTResourceManager.getImage(UpdateStuInfo.class, "/images/select_3.png"));
			}
		});

		//注册
		button_4.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String sName=text_1.getText().trim();  //姓名
				String sQq=text_6.getText().trim();  //QQ
				String sAddr=text_3.getText().trim();   //地址
				String sEmail=text_4.getText().trim();  //邮箱
				String sTel=text_5.getText().trim();   //电话
				String sAge=spinner.getText().trim();    //年龄
				String sClass=combo.getText().trim();    //班级
				String sSex="男";
				if(button_3.getSelection()){
					sSex="女";
				} else{
					sSex="男";
				}

				byte[] sPhoto=null; //照片
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
					
					if(si.updateStuInfo(stuid, sName, sClass, sSex, sAge, sAddr,sQq, sEmail, sTel,sPhoto)>0){
						MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION);
						mgb.setText("更新成功");
						mgb.setMessage(stuid+"学生信息更新成功");
						mgb.open();
						shell.dispose();
					} else{
						MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION);
						mgb.setText("更新失败");
						mgb.setMessage(stuid+"学生信息更新失败");
						mgb.open();
					}
				}else{
					if(si.updateStuInfo(stuid, sName, sClass, sSex, sAge, sAddr, sEmail, sTel)>0){
						MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION);
						mgb.setText("更新成功");
						mgb.setMessage(stuid+"学生信息更新成功");
						mgb.open();
						shell.dispose();
					} else{
						MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION);
						mgb.setText("更新失败");
						mgb.setMessage(stuid+"学生信息更新失败");
						mgb.open();
					}
				}
				
			}
		});
		
		//composite 面板背景图自适应
		composite.addControlListener(new ControlAdapter() {
			public void controlResized(ControlEvent e) {
				try {
					composite.setBackgroundImage(  shellUtil.ImageSize(new FileInputStream(new File("bin/images/updateStuInfo.png")),
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
