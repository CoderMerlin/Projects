package com.exam.teacherop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Text;

import com.exam.studentop.StuOp;
import com.exam.utils.SendEmail;
import com.exam.utils.ShellUtil;
import com.swtdesigner.SWTResourceManager;

import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;

/**
 * 使用反馈
 * @author Administrator
 *
 */
public class Feedback extends Composite {
	private ShellUtil su=new ShellUtil();
	private Text text;
	private Text text_2;
	private Text text_1;
	private String filePath="";
	
	//TODO　private String tEmail="";
	
	public static Image ImageSize(InputStream is,int width,int height){
		ImageData data=new ImageData(is);
		data=data.scaledTo(width, height);
		return new Image(null,data);
	}

	/**
	 * Create the composite.
	 * @param parent
	 * @param style
	 * @throws FileNotFoundException 
	 */
	public Feedback(Composite parent, int style) {
		super(parent, style);
		setBackgroundMode(SWT.INHERIT_DEFAULT);
		setLayout(new FillLayout(SWT.HORIZONTAL));
		
		final Composite composite = new Composite(this, SWT.NONE);
		composite.setBackgroundMode(SWT.INHERIT_DEFAULT);
		composite.setBackgroundImage(SWTResourceManager.getImage(Feedback.class, "/images/RuiyouBackground.png"));
		composite.setLayout(null);
		//改变面板大小使背景自适应
		composite.addControlListener(new ControlAdapter() {   
			public void controlResized(ControlEvent e) {
				try {
					composite.setBackgroundImage(  su.ImageSize(new FileInputStream(new File("bin/images/RuiyouBackground.png")),
					composite.getBounds().width,composite.getBounds().height    )    );
				} catch (FileNotFoundException e1) {
					e1.printStackTrace();
				}
			}
		});
		
		Label label_2 = new Label(composite, SWT.NONE);
		label_2.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label_2.setBounds(231, 269, 95, 27);
		label_2.setText("\u6B63\u3000\u3000\u6587:");
		
		text_2 = new Text(composite, SWT.BORDER | SWT.WRAP | SWT.V_SCROLL | SWT.MULTI);
		text_2.setBounds(232, 302, 654, 211);
		text_2.setFont(SWTResourceManager.getFont("Microsoft YaHei UI", 12, SWT.NORMAL));
		
		Label label = new Label(composite, SWT.NONE);
		label.setFont(SWTResourceManager.getFont("方正综艺简体", 12, SWT.NORMAL));
		label.setBounds(231, 117, 108, 27);
		label.setText("\u6807\u3000\u3000\u9898:");
		
		text = new Text(composite, SWT.BORDER);
		text.setBounds(368, 117, 360, 27);
		
		final Button button = new Button(composite, SWT.NONE);
		button.setFont(SWTResourceManager.getFont("方正综艺简体", 14, SWT.NORMAL));
		button.setBounds(759, 551, 126, 40);
		button.setEnabled(false);
		button.setText("\u53D1\u9001\u53CD\u9988");
		
		text_1 = new Text(composite, SWT.BORDER);
		text_1.setBounds(368, 193, 360, 27);
		
		Label label_4 = new Label(composite, SWT.NONE);
		label_4.setImage(SWTResourceManager.getImage(Feedback.class, "/images/\u6DFB\u52A0\u56FE\u7247.png"));
		label_4.setBounds(236, 193, 80, 27);
		
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseDown(MouseEvent e) {
				FileDialog fd;
				
				try {
					fd = new FileDialog(TeacherOp.shell,SWT.OPEN);
				} catch (Exception e1) {
					fd = new FileDialog(StuOp.shell,SWT.OPEN);
				}
				
				fd.setText("添加附件");
				fd.setFilterPath("SystemRoot");
				fd.setFilterExtensions(new String[]{"*.jpg","*.gif","*.png"});
				filePath=fd.open();
				if(filePath!=null && !"".equals(filePath)){
					text_1.setText(filePath);
				}
			}
		});
		
		//激活发送
		text.addFocusListener(new FocusAdapter() {
			@Override
			public void focusLost(FocusEvent e) {
				if(!"".equals(text.getText())){
					button.setEnabled(true);
				} else{
					button.setEnabled(false);
				}
			}
		});
		
		//发送邮件
		button.addSelectionListener(new SelectionAdapter() {
			@Override
			public void widgetSelected(SelectionEvent e) {
				String to="942311090@qq.com";
				String title=text.getText();
				String body=text_2.getText();
				
				List<String> filepath=new ArrayList<String>();
				filepath.add(text_1.getText());
				
				SendEmail se=new SendEmail("smtp.qq.com","2285458934@qq.com","ruiyou123","2285458934@qq.com","反馈"); //TODO 改变内容
				
				try {
					boolean haveSend=false; //邮件是否发送成功
					if(text_1.getText().length()==0 || text_1.getText()==null){ //如果没有附件文件时
						haveSend=se.sendMail(to, title, body, null);
					} else{ //如果有附加文件时
						haveSend=se.sendMail(to, title, body, filepath);
					}
					
					if( haveSend ){
						MessageBox mgb=new MessageBox(TeacherOp.shell,SWT.ICON_INFORMATION);
						mgb.setText("发送成功");
						mgb.setMessage("问题反馈发送成功!!!");
						mgb.open();
					} else{
						MessageBox mgb=new MessageBox(TeacherOp.shell,SWT.ICON_ERROR);
						mgb.setText("发送失败");
						mgb.setMessage("问题反馈发送失败!!!");
						mgb.open();
					}
				} catch (AddressException e1) {
					e1.printStackTrace();
				} catch (UnsupportedEncodingException e1) {
					e1.printStackTrace();
				} catch (MessagingException e1) {
					e1.printStackTrace();
				}
			}
		});
	}

	@Override
	protected void checkSubclass() {
		// Disable the check that prevents subclassing of SWT components
	}
}
