package com.exam.register;

import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;

import com.exam.utils.ShellUtil;
import com.swtdesigner.SWTResourceManager;

public class Register {

	protected Shell shell;
	private ShellUtil shellUtil=new ShellUtil();
	
	private Button button;  //学生注册
	private Button button_1;  //教师注册
	

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Register window = new Register();
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
		shell.setSize(450, 255);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		
		
		shellUtil.ShowCenter(shell);      //居中显示
		Composite composite = new Composite(shell, SWT.NONE);
		
		button = new Button(composite, SWT.NONE);
		button.setImage(SWTResourceManager.getImage(Register.class, "/images/Student_rg.gif"));
		button.setBounds(0, 0, 450, 125);
		
		button_1 = new Button(composite, SWT.NONE);
		button_1.setImage(SWTResourceManager.getImage(Register.class, "/images/Teacher_rg.gif"));
		button_1.setBounds(0, 128, 450, 125);
		
		//学生注册
		button.addMouseTrackListener(new MouseTrackAdapter(){
				//鼠标移开时
				@Override
				public void mouseExit(MouseEvent e) {
					button.setImage(SWTResourceManager.getImage(Register.class, "/images/Student_rg.gif"));
				}
				
				//鼠标移上时
				@Override
				public void mouseHover(MouseEvent e) {
					button.setImage(SWTResourceManager.getImage(Register.class, "/images/Student_rg1.gif"));
				}
			});
				button.addMouseListener(new MouseAdapter(){
					//鼠标按下时
					@Override
					public void mouseDown(MouseEvent e) {
						button.setImage(SWTResourceManager.getImage(Register.class, "/images/Student_rg2.gif"));
						
					}
					//鼠标松开时
					@Override
					public void mouseUp(MouseEvent e) {
						StudentRegister studentRegister=new StudentRegister();
						shell.dispose();
						studentRegister.open();
					}
				
				});
		
		//教师注册
		button_1.addMouseTrackListener(new MouseTrackAdapter(){
			//鼠标移开时
			@Override
			public void mouseExit(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(Register.class, "/images/Teacher_rg.gif"));
				
			}
			//鼠标移上时
			@Override
			public void mouseHover(MouseEvent e) {
				button_1.setImage(SWTResourceManager.getImage(Register.class, "/images/Teacher_rg1.gif"));
			}
			});
			button_1.addMouseListener(new MouseAdapter(){
				//鼠标按下时
				@Override
				public void mouseDown(MouseEvent e) {
					button_1.setImage(SWTResourceManager.getImage(Register.class, "/images/Teacher_rg2.gif"));
					
				}
				
				//鼠标松开时
				@Override
				public void mouseUp(MouseEvent e) {
					TeacherRegister teacherRegister=new TeacherRegister();
					shell.dispose();
					teacherRegister.open();
				}
			
			});
		
		}
}
