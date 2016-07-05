package com.exam.studentop;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;

import com.exam.utils.QuestionInfo;
import com.exam.utils.ShellUtil;
import com.exam.utils.StuInfo;

import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.custom.ScrolledComposite;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Button;
import com.swtdesigner.SWTResourceManager;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;

/**
 * ��ʼ����
 * @author Administrator
 *
 */
public class StartExam {
	private String stuid; //ѧ�����
	private String qid; //�Ծ���
	int minute; 
	long time;//���Գ���ʱ��
	
	private ShellUtil su=new ShellUtil();
	private StuInfo si=new StuInfo();
	private QuestionInfo qi=new QuestionInfo();

	protected Shell shell;
	private Display display;
	private SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss"); //����ʱ��ʽ
	private Timer timer=new Timer(); //��ʱ��
	
	private Text text;
	private Text text_1;
	private Label label;
	private Group[] group;
	private Button button_1,button_2,button_3,button_4;
	private String skey=""; //ѧ����,��"_"�ֿ�
	private Button button;
	private Event event;
	
	private boolean isSelect=false;
	
	/**
	 * ��ȡ����
	 * @param stuid ѧ�����
	 * @param qid �Ծ���
	 * @param time ���Գ���ʱ��
	 */
	@SuppressWarnings("deprecation")
	public void setValues(String stuid,String qid,int minute){
		this.stuid=stuid;
		this.qid=qid;
		this.minute=minute;
		this.time=new Date(1900,2,2,0,0,0).getTime()+1000*60*minute;
	}
	
	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			StartExam window = new StartExam();
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
		shell = new Shell(SWT.NONE  | SWT.APPLICATION_MODAL);
		shell.setSize(1000, 618);
		shell.setText("SWT Application");
		shell.setLayout(new FillLayout(SWT.HORIZONTAL));
		su.centerShow(display, shell); //��shell������ʾ

		Composite parentComposite = new Composite(shell, SWT.NONE);
		parentComposite.setLayout(new FillLayout());
		ScrolledComposite scrolledComposite = new ScrolledComposite(parentComposite, SWT.V_SCROLL);
		scrolledComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);

		Composite mainComposite = new Composite(scrolledComposite,SWT.NONE);
		mainComposite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		scrolledComposite.setContent(mainComposite);
		//mainComposite.setBackground(Display.getCurrent().getSystemColor (SWT.COLOR_WHITE));
		GridData data = new GridData(GridData.FILL_BOTH);
		mainComposite.setLayoutData(data);
		mainComposite.setLayout(null);
		
		List<Object> result=qi.findTest(qid); //��ȡ�Ծ����Ϣ	TODO
		if(result.get(2)==null || ((String[])result.get(2)).length==0 || ((String[])result.get(2))[0]==null){
			isSelect=false;
		} else{
			isSelect=true;
		}

		//ʹ��mainComposite�ܹ���
		scrolledComposite.setExpandHorizontal(true);
		scrolledComposite.setExpandVertical(true);
		scrolledComposite.setMinHeight((Integer)result.get(0)*200+100); //���ô�ֱ����
		
		label = new Label(mainComposite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label.setForeground(SWTResourceManager.getColor(SWT.COLOR_RED));
		label.setBounds(345, 0, 332, 45);
		
		Label label_1 = new Label(mainComposite, SWT.WRAP);
		label_1.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		label_1.setBounds(82, 0, 233, 51);
		label_1.setText("ѧ��"+stuid+"��"+qi.findTestName(qid)+"�Ծ�");
		
		button = new Button(mainComposite, SWT.NONE);
		button.setBounds(460, (Integer)result.get(0)*200+50, 80, 27); //����button��λ��
		button.setEnabled(false);
		button.setText("�ύ");
		
		su.shellMove(mainComposite, shell); //�ƶ����
		
		group=new Group[ (Integer)result.get(0) ];
		//����Ŀд��mainComposite�� 
		for(int i=0;i<(Integer)result.get(0);i++){
			text = new Text(mainComposite, SWT.BORDER);
			text.setBounds(200, 50+(i*200), 600, 55);
			text.setEditable(false);
			text.setText("��"+(i+1)+"��:"+((String[])result.get(1))[i]);
			
			group[i] = new Group(mainComposite, SWT.NONE);
			group[i].setBounds(200, 110+(i*200), 600, 102);
			group[i].setText(""+(i+1));
			
			
			if(!isSelect){
				text_1 = new Text(group[i], SWT.BORDER);
				text_1.setBounds(0,0,600, 102);
			} else{
				button_1 = new Button(group[i], SWT.RADIO);
				button_1.setBounds(10, 20, 500, 20);
				button_1.setText(( (String[])result.get(2) )[i].split("_")[0]);
				button_1.setSelection(true);

				button_2 = new Button(group[i], SWT.RADIO);
				button_2.setBounds(10, 40, 500, 20);
				button_2.setText(( (String[])result.get(2) )[i].split("_")[1]);

				button_3 = new Button(group[i], SWT.RADIO);
				button_3.setBounds(10, 60, 500, 20);
				button_3.setText(( (String[])result.get(2) )[i].split("_")[2]);

				button_4 = new Button(group[i], SWT.RADIO);
				button_4.setBounds(10, 80, 500, 20);
				button_4.setText(( (String[])result.get(2) )[i].split("_")[3]);
			}
		
		}
		
		//�����ύ��ť
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				display .syncExec( new Runnable() {
					public void run() {
						button.setEnabled(true);
					}
				});
			}		 
		}, 1000*5); 
		
		
		//�ύ
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				skey="";
				for(Group g:group){
					if(isSelect){ //���Ϊ��ѡ��
						Control[] c=g.getChildren();
						Button tmpb=null;
						for (Control control : c) {
							tmpb=(Button) control;
							if(tmpb.getSelection()){
								skey+=tmpb.getText().substring(0,1)+"_";
							}
						}
					} else{
						Control[] c=g.getChildren();
						Text tmpb=null;
						for (Control control : c) {
							tmpb=(Text) control;
							skey+=tmpb.getText()+"_";
						}
					}
				}

				if(si.submit(stuid, qid, skey)>0){
					MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION);
					mgb.setText("�ύ��ʾ");
					mgb.setMessage("�Ծ��ύ�ɹ�,���Խ��潫�ڵ��ȷ����ر�");
					mgb.open();
					si.submitNextOp(qid);
					shell.dispose();
				} else{
					MessageBox mgb=new MessageBox(shell,SWT.ICON_ERROR);
					mgb.setText("�ύ��ʾ");
					mgb.setMessage("�Ծ��ύʧ��!!!");
					mgb.open();
				}
				
			}
			
		});
		
		//�¼���
		event=new Event();
		event.widget=button;
		
		downTime();
		prompt();
	}
	
	/**
	 * ��ʾ����ʱ
	 */
	public void downTime(){
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				display .syncExec( new Runnable() {
					public void run() {
						if(!shell.isDisposed()){
							label.setText("���Ե���ʱ:"+sdf.format(new Date(time)));
							time-=1000;
						}
					}
				});
			}		 
		}, 0,1000); //�ӳ�0�룬ÿһ�����һ��
	}
	
	/**
	 * ��ʾ���˳�
	 */
	public void prompt(){
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				if(!shell.isDisposed()){
					display .syncExec( new Runnable() {
						public void run() {
							MessageBox mgb=new MessageBox(shell,SWT.ICON_INFORMATION);
							mgb.setText("ʱ����ʾ");
							mgb.setMessage("����ʱ���Ѳ��㣬��ע��ʱ��!!!");
							mgb.open();

							exit();
						}
					});
				}
			}		 
		}, 1000*60*(minute-5)); //��ֻʣ5����ʱ��ʾʱ�䲻��
	}
	
	public void exit(){
		timer.schedule(new TimerTask() {
			@Override
			public void run() {
				display .syncExec( new Runnable() {
					public void run() {
						button.notifyListeners(SWT.Selection, event); //���˳�֮ǰִ���ύ�Ĳ���(�¼���)
						shell.dispose();
					}
				});
			}		 
		}, 1000*60*5);  //����ʾ���5���Ӻ�رտ��Խ���
	}
	
	/**
	 * �Զ�����
	 */
	public void autoSubmit(int minute) {  
		Timer timer = new Timer();  
		timer.schedule(new TimerTask() {  
			public void run() { 
				if(!shell.isDisposed()){
					
							skey="";
							for(final Group g:group){
								if(isSelect){ //���Ϊ��ѡ��
									display .syncExec( new Runnable() {
										public void run() {
											Control[] c=g.getChildren();
											Button tmpb=null;
											for (Control control : c) {
												tmpb=(Button) control;
												if(tmpb.getSelection()){
													skey+=tmpb.getText().substring(0,1)+"_";
												}
											}
										}
									});
								} else{
									display .syncExec( new Runnable() {
										public void run() {
											Control[] c=g.getChildren();
											Text tmpb=null;
											for (Control control : c) {
												tmpb=(Text) control;
												skey+=tmpb.getText()+"_";
											}
										}
									});
								}
							}
							si.submit(stuid, qid, skey);
						//}
					//});
				}
			}  
		}, 0, ((int)(minute/10))*1000*60);  //�ӳ�0��ִ��,֮��ÿ���ܷ�������1/10���Զ�ִ��һ�ν������
	}  

}
