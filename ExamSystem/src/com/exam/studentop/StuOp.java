package com.exam.studentop;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;

import com.exam.teacherop.Feedback;
import com.exam.utils.ShellUtil;
import com.exam.utils.StackPanelStu;
import com.swtdesigner.SWTResourceManager;

/**
 * 主界面
 * @author Administrator
 *
 */
public class StuOp {
	private Display display;
	public static Shell shell;
	private ShellUtil su=new ShellUtil();
	
	protected static String stuid;
	
	private Button button_1; //本班学生信息按钮
	private Button button_2; //考试信息按钮
	private Button button_3;  //试卷查看按钮
	private Button button_4;  //考后聊
	private Button button_5; //用户使用信息反馈按钮
	private Button button_6;  //退出系统
	

	@SuppressWarnings("static-access")
	public void setStuid(String stuid){
		this.stuid=stuid;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StuOp window = new StuOp();
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
		shell.setImage(SWTResourceManager.getImage(StuOp.class, "/images/\u777F\u53CB\u5934\u50CF_\u9542\u7A7A.png"));
		shell.setText("\u5B66\u751F\u4E13\u533A");
		shell.setSize(1400, 900);
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		su.tray(display, shell, "睿友智能考试系统");
		su.centerShow(display, shell); //居中显示
		
		final SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);

		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(StuOp.class, "/images/TeacherBackground.png"));
		composite.setLayout(null);
		su.shellMove(composite, shell);
		
		button_1 = new Button(composite, SWT.NONE);
		button_1.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TClassStudent.gif"));
		button_1.setBounds(0, 0, 130, 120);
		
		button_2 = new Button(composite, SWT.NONE);
		button_2.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TestInfo.gif"));
		button_2.setBounds(130, 0, 130, 120);
		
		button_3 = new Button(composite, SWT.NONE);
		button_3.setImage(SWTResourceManager.getImage(StuOp.class, "/images/LookTest.gif"));
		button_3.setBounds(260, 0, 130, 120);
		
		button_4 = new Button(composite, SWT.NONE);
		button_4.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TestTalk.gif"));
		button_4.setBounds(390, 0, 130, 120);
		
		button_5 = new Button(composite, SWT.NONE);
		button_5.setImage(SWTResourceManager.getImage(StuOp.class, "/images/UserExperience.gif"));
		button_5.setBounds(520, 0, 130, 120);
		
		button_6 = new Button(composite, SWT.NONE);
		button_6.setImage(SWTResourceManager.getImage(StuOp.class, "/images/Exit.gif"));
		button_6.setBounds(650, 0, 130, 120);
		
		final Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(StuOp.class, "/images/RuiyouBackground.png"));
		su.shellMove(composite_1, shell);
		composite_1.setLayout(StackPanelStu.stackLayout);
		
		ShowClassStu showClassStu = new ShowClassStu(composite_1,SWT.NONE);
		showClassStu.setBackgroundMode(SWT.INHERIT_DEFAULT);
		StackPanelStu.showClassStu=showClassStu;
		StackPanelStu.talkArea=new TalkArea(composite_1,SWT.NONE);
		StackPanelStu.test=new ChooseExam(composite_1,SWT.NONE);
		StackPanelStu.feedback=new Feedback(composite_1,SWT.NONE);
		StackPanelStu.score=new Score(composite_1,SWT.NONE);
		sashForm.setWeights(new int[] {119, 776});

		
		//显示本班所有学生
		button_1.addMouseTrackListener(new MouseTrackAdapter(){
			//鼠标移开时
			@Override
			public void mouseExit(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TClassStudent.gif"));
			}
			//鼠标移上时
			@Override
			public void mouseHover(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TClassStudent1.gif"));
			}
		});
		button_1.addMouseListener(new MouseAdapter(){
				//鼠标按下时
			@Override
			public void mouseDown(MouseEvent e) {
				StackPanelStu.stackLayout.topControl=StackPanelStu.showClassStu;
				StackPanelStu.showClassStu.setVisible(true);
				StackPanelStu.test.setVisible(false);
				StackPanelStu.talkArea.setVisible(false);
				StackPanelStu.feedback.setVisible(false);
				StackPanelStu.score.setVisible(false);
			}
				
			});
		
		
		
	
		
		//考试
		button_2.addMouseTrackListener(new MouseTrackAdapter(){
			//鼠标移开时
			@Override
			public void mouseExit(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TestInfo.gif"));
			}
			//鼠标移上时
			@Override
			public void mouseHover(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TestInfo1.gif"));
			}
		});
		button_2.addMouseListener(new MouseAdapter(){
				//鼠标按下时
			@Override
				public void mouseDown(MouseEvent e) {
					StackPanelStu.stackLayout.topControl=StackPanelStu.test;
					StackPanelStu.showClassStu.setVisible(false);
					StackPanelStu.test.setVisible(true);
					StackPanelStu.talkArea.setVisible(false);
					StackPanelStu.feedback.setVisible(false);
					StackPanelStu.score.setVisible(false);
	
				}
				
			});
		
		
		
		//试卷查看
		button_3.addMouseTrackListener(new MouseTrackAdapter(){
			//鼠标移开时
			@Override
			public void mouseExit(MouseEvent e) {
				button_3.setImage(SWTResourceManager.getImage(StuOp.class, "/images/LookTest.gif"));
			}
			//鼠标移上时
			@Override
			public void mouseHover(MouseEvent e) {
				button_3.setImage(SWTResourceManager.getImage(StuOp.class, "/images/LookTest1.gif"));
			}
		});
		button_3.addMouseListener(new MouseAdapter(){
				//鼠标按下时
			@Override
				public void mouseDown(MouseEvent e) {
					StackPanelStu.stackLayout.topControl=StackPanelStu.score;
					StackPanelStu.showClassStu.setVisible(false);
					StackPanelStu.test.setVisible(false);
					StackPanelStu.talkArea.setVisible(false);
					StackPanelStu.feedback.setVisible(false);
					StackPanelStu.score.setVisible(true);
	
				}
				
			});
		
		//考后聊
		button_4.addMouseTrackListener(new MouseTrackAdapter(){
			//鼠标移开时
			@Override
			public void mouseExit(MouseEvent e) {
				button_4.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TestTalk.gif"));
			}
			//鼠标移上时
			@Override
			public void mouseHover(MouseEvent e) {
				button_4.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TestTalk1.gif"));
			}
		});
		button_4.addMouseListener(new MouseAdapter(){
				//鼠标按下时
				@Override
				public void mouseDown(MouseEvent e) {
					StackPanelStu.stackLayout.topControl=StackPanelStu.talkArea;
					StackPanelStu.showClassStu.setVisible(false);
					StackPanelStu.test.setVisible(false);
					StackPanelStu.talkArea.setVisible(true);
					StackPanelStu.feedback.setVisible(false);
					StackPanelStu.score.setVisible(false);
				}
			});
	
		
		
		//使用反馈
		button_5.addMouseTrackListener(new MouseTrackAdapter(){
			//鼠标移开时
			@Override
			public void mouseExit(MouseEvent e) {
				button_5.setImage(SWTResourceManager.getImage(StuOp.class, "/images/UserExperience.gif"));
			}
			//鼠标移上时
			@Override
			public void mouseHover(MouseEvent e) {
				button_5.setImage(SWTResourceManager.getImage(StuOp.class, "/images/UserExperience1.gif"));
			}
		});
		button_5.addMouseListener(new MouseAdapter(){
				//鼠标按下时
			@Override
				public void mouseDown(MouseEvent e) {
					StackPanelStu.stackLayout.topControl=StackPanelStu.feedback;
					StackPanelStu.showClassStu.setVisible(false);
					StackPanelStu.test.setVisible(false);
					StackPanelStu.talkArea.setVisible(false);
					StackPanelStu.feedback.setVisible(true);
					StackPanelStu.score.setVisible(false);
				}
			});
	
		
		
		//退出
		button_6.addMouseTrackListener(new MouseTrackAdapter(){
			//鼠标移开时
			@Override
			public void mouseExit(MouseEvent e) {
				button_6.setImage(SWTResourceManager.getImage(StuOp.class, "/images/Exit.gif"));
			}
			//鼠标移上时
			@Override
			public void mouseHover(MouseEvent e) {
				button_6.setImage(SWTResourceManager.getImage(StuOp.class, "/images/Exit1.gif"));
			}
		});
		button_6.addMouseListener(new MouseAdapter(){
				//鼠标按下时
				@Override
				public void mouseDown(MouseEvent e) {
					MessageBox messageBox=new MessageBox(StuOp.shell,SWT.ICON_WARNING|SWT.YES|SWT.NO);
					messageBox.setText("警告提示！");
					messageBox.setMessage("是否退出系统？");
					int result=messageBox.open();
					if(result==64){
						shell.close();
						Display.getCurrent().close();   //消除托盘
						System.exit(0);
					}
				}
				
			});
	}
}
