package com.exam.studentop;

import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import com.exam.utils.QuestionInfo;
import com.exam.utils.ShellUtil;
import org.eclipse.swt.widgets.Label;
import com.swtdesigner.SWTResourceManager;

public class SeeScore {

	protected Shell shell;
	private Display display;
	private Text text;
	private Text text_1;
	private ShellUtil su=new ShellUtil();

	private QuestionInfo qi=new QuestionInfo();
	private String qid="";
	private String stuid="";
	private String escore="";
	
	public void setValues(String qid,String stuid,String escore){
		this.qid=qid;
		this.stuid=stuid;
		this.escore=escore;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			SeeScore window = new SeeScore();
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
		shell = new Shell(SWT.MIN | SWT.APPLICATION_MODAL);
		shell.setSize(1000, 618);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		su.centerShow(display, shell); //将shell居中显示
		
		Composite parentComposite = new Composite(shell, SWT.NONE);
		parentComposite.setLayout(new FillLayout());
		ScrolledComposite scrolledComposite = new ScrolledComposite(parentComposite, SWT.V_SCROLL);
		scrolledComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);

		Composite mainComposite = new Composite(scrolledComposite,SWT.NONE);
		scrolledComposite.setContent(mainComposite);
		mainComposite.setBackground(Display.getCurrent().getSystemColor (SWT.COLOR_WHITE));
		GridData data = new GridData(GridData.FILL_BOTH);
		mainComposite.setLayoutData(data);
		mainComposite.setLayout(null);
		
		Label label = new Label(mainComposite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label.setAlignment(SWT.CENTER);
		label.setBounds(350, 0, 300, 50);
		label.setText("学生"+stuid+"的"+qid+"试卷详细结果"+"\n"+"成绩为:"+escore);
		
		
		List<Object> result=qi.findExamResult(stuid, qid);
		
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinHeight((Integer)result.get(0)*200+100); //设置垂直距离
		
		//将题目写在mainComposite上 
		for(int i=0;i<(Integer)result.get(0);i++){
			text = new Text(mainComposite, SWT.BORDER | SWT.CANCEL | SWT.MULTI | SWT.WRAP);
			text.setBounds(300, 60+(i*200), 400, 100);
			text.setEditable(false);
			text.setText("第"+(i+1)+"题:"+((String[])result.get(1))[i]);
			
			text_1 = new Text(mainComposite, SWT.BORDER | SWT.V_SCROLL | SWT.CANCEL | SWT.MULTI | SWT.WRAP);
			text_1.setBounds(300, 170+(i*200), 400, 60);
			text_1.setEditable(false);
			text_1.setText("该题正确答案为:"+((String[])result.get(2))[i]+"\n"+"您的答案为:"+((String[])result.get(3))[i]);
		}
	}
}
