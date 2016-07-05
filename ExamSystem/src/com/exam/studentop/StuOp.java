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
 * ������
 * @author Administrator
 *
 */
public class StuOp {
	private Display display;
	public static Shell shell;
	private ShellUtil su=new ShellUtil();
	
	protected static String stuid;
	
	private Button button_1; //����ѧ����Ϣ��ť
	private Button button_2; //������Ϣ��ť
	private Button button_3;  //�Ծ�鿴��ť
	private Button button_4;  //������
	private Button button_5; //�û�ʹ����Ϣ������ť
	private Button button_6;  //�˳�ϵͳ
	

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
		
		su.tray(display, shell, "������ܿ���ϵͳ");
		su.centerShow(display, shell); //������ʾ
		
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

		
		//��ʾ��������ѧ��
		button_1.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TClassStudent.gif"));
			}
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TClassStudent1.gif"));
			}
		});
		button_1.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
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
		
		
		
	
		
		//����
		button_2.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TestInfo.gif"));
			}
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TestInfo1.gif"));
			}
		});
		button_2.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
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
		
		
		
		//�Ծ�鿴
		button_3.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_3.setImage(SWTResourceManager.getImage(StuOp.class, "/images/LookTest.gif"));
			}
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_3.setImage(SWTResourceManager.getImage(StuOp.class, "/images/LookTest1.gif"));
			}
		});
		button_3.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
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
		
		//������
		button_4.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_4.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TestTalk.gif"));
			}
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_4.setImage(SWTResourceManager.getImage(StuOp.class, "/images/TestTalk1.gif"));
			}
		});
		button_4.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
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
	
		
		
		//ʹ�÷���
		button_5.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_5.setImage(SWTResourceManager.getImage(StuOp.class, "/images/UserExperience.gif"));
			}
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_5.setImage(SWTResourceManager.getImage(StuOp.class, "/images/UserExperience1.gif"));
			}
		});
		button_5.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
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
	
		
		
		//�˳�
		button_6.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_6.setImage(SWTResourceManager.getImage(StuOp.class, "/images/Exit.gif"));
			}
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_6.setImage(SWTResourceManager.getImage(StuOp.class, "/images/Exit1.gif"));
			}
		});
		button_6.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
				@Override
				public void mouseDown(MouseEvent e) {
					MessageBox messageBox=new MessageBox(StuOp.shell,SWT.ICON_WARNING|SWT.YES|SWT.NO);
					messageBox.setText("������ʾ��");
					messageBox.setMessage("�Ƿ��˳�ϵͳ��");
					int result=messageBox.open();
					if(result==64){
						shell.close();
						Display.getCurrent().close();   //��������
						System.exit(0);
					}
				}
				
			});
	}
}
