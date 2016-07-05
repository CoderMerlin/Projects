package com.exam.teacherop;


import java.io.FileNotFoundException;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.SWT;

import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;

import com.exam.utils.ShellUtil;
import com.exam.utils.StackPanelTea;
import com.swtdesigner.SWTResourceManager;

import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;

/**
 * ��ʦ�Ĳ���
 * @author Administrator
 *
 */
public class TeacherOp {
	private ShellUtil su=new ShellUtil();
	protected static Shell shell;
	protected static Display display;
	
	
	private Button button_1; //��ʦ��Ϣ��ť
	private Button button_7; //ѧ����Ϣ��ť
	private Button button_2;  //�༶��Ϣ��ť
	private Button button_6; // ���������ť
	private Button button_5;  //�������ⰴť
	private Button button;  //����ά����ť
	private Button button_3;  //�û����鰴ť
	private Button button_4;  //�˳�ϵͳ��ť
	
	protected static String tid;
	
	@SuppressWarnings("static-access")
	public void setTid(String tid){
		this.tid=tid;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			TeacherOp window = new TeacherOp();
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
	 * @throws FileNotFoundException 
	 */
	protected void createContents(){
		shell = new Shell(SWT.NONE);
		shell.setSize(1400, 900);
		shell.setText("\u6559\u5E08\u64CD\u4F5C");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		shell.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/���ͷ��_�ο�.png"));
		su.tray(display, shell, "��ʦ����"); //��ʾ����
		su.centerShow(display, shell); //������ʾ
		
		final SashForm sashForm = new SashForm(shell, SWT.NONE);
		sashForm.setOrientation(SWT.VERTICAL);
		
		Composite composite = new Composite(sashForm, SWT.NONE);
		composite.setBackgroundImage(SWTResourceManager.getImage(TeacherOp.class, "/images/TeacherBackground.png"));
		composite.setLayout(null);

		
		su.shellMove(composite, shell);
		
		button_1 = new Button(composite, SWT.NONE);
		button_1.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/AllTeacher.gif"));
		button_1.setBounds(0, 0, 130, 120);
		
		button_7 = new Button(composite, SWT.NONE);
		button_7.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/StudentInfo.gif"));
		button_7.setBounds(130, 0, 130, 120);
		
		button_2 = new Button(composite, SWT.NONE);
		button_2.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/ClassInfo.gif"));
		button_2.setBounds(260, 0, 130, 120);
		
		
		button_6 = new Button(composite, SWT.NONE);
		button_6.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/TestOperation.gif"));
		button_6.setBounds(390, 0, 130, 120);
		
		button_5 = new Button(composite, SWT.NONE);
		button_5.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/LeadTest.gif"));
		button_5.setBounds(520, 0, 130, 120);
		
		button = new Button(composite, SWT.NONE);
		button.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/Test.gif"));
		button.setBounds(650, 0, 130, 120);
		
		button_3 = new Button(composite, SWT.NONE);
		button_3.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/UserExperience.gif"));
		button_3.setBounds(780, 0, 130, 120);
		
		button_4 = new Button(composite, SWT.NONE);
		button_4.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/Exit.gif"));
		button_4.setBounds(910, 0, 130, 120);
		
		Composite composite_1 = new Composite(sashForm, SWT.NONE);
		composite_1.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite_1.setBackgroundImage(SWTResourceManager.getImage(TeacherOp.class, "/images/RuiyouBackground.png"));
		su.backgroundimageFit(display, composite_1, "bin/images/RuiyouBackground.png");
		
		su.shellMove(composite_1, shell); //���������ƶ�
		
		composite_1.setLayout(StackPanelTea.stackLayout);  //��ջ���
		
		ShowAllTeacher showAllTeacher = new ShowAllTeacher(composite_1,SWT.NONE);
		showAllTeacher.setBackgroundImage(SWTResourceManager.getImage(TeacherOp.class, "/images/RuiyouBackground.png"));
		showAllTeacher.setBackgroundMode(SWT.INHERIT_DEFAULT);
		StackPanelTea.showAllTeacher= showAllTeacher; //��ʾ���н�ʦ
		StackPanelTea.classOp=new ClassOp(composite_1,SWT.NONE); //�԰༶�Ĳ��� 	
		StackPanelTea.intoQuestion=new IntoQuestion(composite_1,SWT.NONE); //��������	
		StackPanelTea.feedback=new Feedback(composite_1, SWT.NONE); //ʹ�÷���
		StackPanelTea.studentOp=new StudentOp(composite_1, SWT.NONE); //ѧ������
		StackPanelTea.questionBankOp=new QuestionBankOp(composite_1, SWT.NONE); //���ά��
		StackPanelTea.questionOp=new QuestionOp(composite_1, SWT.NONE);
		sashForm.setWeights(new int[] {120, 775});
	
		//��ʦ��Ϣ��ť
		button_1.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/AllTeacher.gif"));
			}
			
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/AllTeacher1.gif"));
			}
		});
			
		button_1.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
			@Override
			public void mouseDown(MouseEvent e) {
				StackPanelTea.stackLayout.topControl=StackPanelTea.showAllTeacher; //����ʾ���н�ʦ����ö�
				StackPanelTea.showAllTeacher.setVisible(true); //��ʾ ��ʾ�������
				StackPanelTea.classOp.setVisible(false); //���� �༶�������
				StackPanelTea.intoQuestion.setVisible(false); //���� �����������
				StackPanelTea.feedback.setVisible(false); //���ط������
				StackPanelTea.studentOp.setVisible(false); //����ѧ���������
				StackPanelTea.questionBankOp.setVisible(false); //��������ά�����
				StackPanelTea.questionOp.setVisible(false); //�����Ծ�������
			}
		});
		
	    //ѧ����Ϣ��ť
		button_7.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_7.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/StudentInfo.gif"));
			}
			
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_7.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/StudentInfo1.gif"));
			}
		});
			
		button_7.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
			@Override
				public void mouseDown(MouseEvent e) {
					StackPanelTea.stackLayout.topControl=StackPanelTea.studentOp;
					StackPanelTea.intoQuestion.setVisible(false);
					StackPanelTea.classOp.setVisible(false);
					StackPanelTea.showAllTeacher.setVisible(false);
					StackPanelTea.feedback.setVisible(false); //���ط������
					StackPanelTea.studentOp.setVisible(true); 
					StackPanelTea.questionBankOp.setVisible(false); //��������ά�����
					StackPanelTea.questionOp.setVisible(false); //�����Ծ�������
				}
			});
		
		
		
		//�༶��Ϣ��ť
		button_2.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/ClassInfo.gif"));
			}
			
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_2.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images//ClassInfo1.gif"));
			}
		});
			
		button_2.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
			@Override
				public void mouseDown(MouseEvent e) {
					StackPanelTea.stackLayout.topControl=StackPanelTea.classOp;
					StackPanelTea.classOp.setVisible(true);
					StackPanelTea.showAllTeacher.setVisible(false);
					StackPanelTea.intoQuestion.setVisible(false); //���� �����������
					StackPanelTea.feedback.setVisible(false); //���ط������
					StackPanelTea.studentOp.setVisible(false); //����ѧ���������
					StackPanelTea.questionBankOp.setVisible(false); //��������ά�����
					StackPanelTea.questionOp.setVisible(false); //�����Ծ�������
				}
			});
		
		
		//���������ť
		button_6.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_6.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/TestOperation.gif"));
			}
			
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_6.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images//TestOperation1.gif"));
			}
		});
		button_6.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
			@Override
				public void mouseDown(MouseEvent e) {
					StackPanelTea.stackLayout.topControl=StackPanelTea.questionOp;
					StackPanelTea.intoQuestion.setVisible(false);
					StackPanelTea.classOp.setVisible(false);
					StackPanelTea.showAllTeacher.setVisible(false);
					StackPanelTea.feedback.setVisible(false); //���ط������
					StackPanelTea.studentOp.setVisible(false); //����ѧ���������
					StackPanelTea.questionBankOp.setVisible(false);
					StackPanelTea.questionOp.setVisible(true);
				}
			});
		
		
		
		//�������ⰴť
		button_5.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_5.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/LeadTest.gif"));
			}
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_5.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/LeadTest1.gif"));
			}
		});
		button_5.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
				@Override
				public void mouseDown(MouseEvent e) {
					StackPanelTea.stackLayout.topControl=StackPanelTea.intoQuestion;
					StackPanelTea.intoQuestion.setVisible(true);
					StackPanelTea.classOp.setVisible(false);
					StackPanelTea.showAllTeacher.setVisible(false);
					StackPanelTea.feedback.setVisible(false); //���ط������
					StackPanelTea.studentOp.setVisible(false); //����ѧ���������
					StackPanelTea.questionBankOp.setVisible(false); //��������ά�����
					StackPanelTea.questionOp.setVisible(false); //�����Ծ�������
				}
			});
		
		
		
		//����ά����ť
		button.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/Test.gif"));
			}
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/Test1.gif"));
			}
		});
		button.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
			@Override
				public void mouseDown(MouseEvent e) {
					StackPanelTea.stackLayout.topControl=StackPanelTea.questionBankOp;
					StackPanelTea.intoQuestion.setVisible(false);
					StackPanelTea.classOp.setVisible(false);
					StackPanelTea.showAllTeacher.setVisible(false);
					StackPanelTea.feedback.setVisible(false); //���ط������
					StackPanelTea.studentOp.setVisible(false); //����ѧ���������
					StackPanelTea.questionBankOp.setVisible(true);
					StackPanelTea.questionOp.setVisible(false); //�����Ծ�������
				}
			});
		
		
		
		//ʹ�÷���
		button_3.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_3.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/UserExperience.gif"));
			}
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_3.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/UserExperience1.gif"));
			}
		});
		button_3.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
				@Override
				public void mouseDown(MouseEvent e) {
					StackPanelTea.stackLayout.topControl=StackPanelTea.feedback;
					StackPanelTea.intoQuestion.setVisible(false);
					StackPanelTea.classOp.setVisible(false);
					StackPanelTea.showAllTeacher.setVisible(false);
					StackPanelTea.feedback.setVisible(true); 
					StackPanelTea.studentOp.setVisible(false); //����ѧ���������
					StackPanelTea.questionBankOp.setVisible(false); //��������ά�����
					StackPanelTea.questionOp.setVisible(false); //�����Ծ�������
	
				}
			});
		
		//�˳�ϵͳ��ť
		button_4.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				button_4.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/Exit.gif"));
			}
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				button_4.setImage(SWTResourceManager.getImage(TeacherOp.class, "/images/Exit1.gif"));
			}
		});
		button_4.addMouseListener(new MouseAdapter(){
				//��갴��ʱ
				@Override
				public void mouseDown(MouseEvent e) {
					MessageBox messageBox=new MessageBox(TeacherOp.shell,SWT.ICON_WARNING | SWT.YES | SWT.NO);
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
