package com.exam.utils;


import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseMoveListener;
import org.eclipse.swt.events.MouseTrackAdapter;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Tray;
import org.eclipse.swt.widgets.TrayItem;

import jxl.*;
import jxl.read.biff.BiffException;

import com.swtdesigner.SWTResourceManager;


/**
 * ��幤��
 * @author rdz
 *
 */
public class ShellUtil {
	
	private boolean flag=false; //��¼����Ƿ���
	private int clickX; //�����ʱ��X���ֵ
	private int clickY; //�����ʱ��Y���ֵ
	
	/**
	 * ��ʾ����
	 * @param display
	 * @param shell ��Ҫ��ʾ���̵����
	 * @parsm name �������ȥʱҪ��ʾ������
	 */
	public void tray(Display display, final Shell shell,String name){
		Tray tray=display.getSystemTray(); //��ȡϵͳ������
		TrayItem trayItem;
		
		if(tray==null){ //ϵͳ��֧������
			MessageDialog.openError(shell, "������Ϣ", "��ǰϵͳ��֧������...");
		} else{
			trayItem=new TrayItem(tray,SWT.NONE);
			trayItem.setToolTipText(name); //�����ŵ�ͼ����ʱ��ʾ����
			
			trayItem.setImage(shell.getImage()); //��������ͼ��
			
			trayItem.addListener(SWT.Selection, new Listener(){
				public void handleEvent(Event arg0) {
					if(shell.getMinimized()){ //�����ǰ��������С����
						shell.setMinimized(false);
						//shell.setMaximized(true);
					} else{
						if(shell.getVisible()){ //�������ʾ��
							shell.setVisible(false);
						} else{
							shell.setVisible(true);
						}
					}
				}
			});
			
			final Menu menu=new Menu(shell, SWT.POP_UP); //���һ��˵�ʱ
			
			MenuItem maxItem=new MenuItem(menu, SWT.PUSH); //��ͨ��
			maxItem.setImage(SWTResourceManager.getImage(ShellUtil.class, "/images/���_����.png"));
			maxItem.setText("���");
			maxItem.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e) {
					shell.setVisible(true);
					shell.setMaximized(true);
				}
			});
			
			MenuItem minItem=new MenuItem(menu, SWT.PUSH); //��ͨ��
			minItem.setImage(SWTResourceManager.getImage(ShellUtil.class, "/images/��С��_����.png"));
			minItem.setText("��С��");
			minItem.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e) {
					shell.setVisible(true);
					shell.setMinimized(true);
				}
			});
			
			//�ָ���
			new MenuItem(menu,SWT.SEPARATOR);
			
			MenuItem exitItem=new MenuItem(menu, SWT.PUSH); //��ͨ�� 
			exitItem.setImage(SWTResourceManager.getImage(ShellUtil.class, "/images/�ر�_����.jpg"));
			exitItem.setText("�˳�");
			exitItem.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e) {
					Display.getCurrent().close();
					System.exit(0);
				}
			});
			
			
			trayItem.addListener(SWT.MenuDetect, new Listener(){ //Detect:����
				public void handleEvent(Event arg0) {
					menu.setVisible(true);
				}
				
			});
		}
	}
	
	/**
	 * ʹ����ͼ��Ӧ����С
	 * @param display
	 * @param shell Ҫ���������
	 * @param path ͼƬ�ļ�·��
	 */
	public void backgroundimageFit(final Display display,final Shell shell,final String path){
		shell.addPaintListener(new PaintListener() { 
			public void paintControl(PaintEvent arg0) {
				try {
					InputStream is=new FileInputStream(new File(path));
					ImageData id=new ImageData(is);
					shell.setBackgroundImage(new Image
							(display, id.scaledTo(shell.getSize().x, shell.getSize().y)));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * ʹ����ͼ��Ӧ����С
	 * @param display
	 * @param composite ���
	 * @param path ͼƬ·��
	 */
	public void backgroundimageFit(final Display display,final Composite composite,final String path){
		composite.addPaintListener(new PaintListener() { 
			public void paintControl(PaintEvent arg0) {
				try {
					InputStream is=new FileInputStream(new File(path));
					ImageData id=new ImageData(is);
					composite.setBackgroundImage(new Image
							(display, id.scaledTo(composite.getSize().x, composite.getSize().y)));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * ������ʾ
	 * @param display
	 * @param shell ��Ҫ������ʾ�����
	 */
	public void centerShow(Display display, Shell shell){
		shell.setLocation((display.getClientArea().width-shell.getClientArea().width)/2, 
				(display.getClientArea().height-shell.getClientArea().height)/2);
	}
	
	
	/**
	 * �ƶ����
	 * @param composite ���������
	 * @param shell �����ƶ������
	 */
	public void shellMove(Composite composite, final Shell shell){
		composite.addMouseMoveListener(new MouseMoveListener(){
			public void mouseMove(MouseEvent e) {
				if(flag){
					//�ƶ����
					shell.setLocation(shell.getLocation().x+e.x-clickX, shell.getLocation().y+e.y-clickY);
				}
			}
			
		});
		
		composite.addMouseListener(new MouseAdapter(){
			//��갴��ʱ
			public void mouseDown(MouseEvent e) {
				flag=true;
				clickX=e.x;
				clickY=e.y;
			}

			//����ɿ�ʱ
			public void mouseUp(MouseEvent e) {
				flag=false;
			}
		});
	}
	
	/**
	 * �رռ���
	 * @param shell ��Ҫ�رյ����
	 * @param label �رյ�label
	 */
	public void closeOp(final Shell shell,final Label label){
		label.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(ShellUtil.class,"/images/close_normal.png"));
			}
			
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(ShellUtil.class,"/images/close_hover.png"));
			}
		});
		
		label.addMouseListener(new MouseAdapter(){
			//��갴��ʱ
			@Override
			public void mouseDown(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(ShellUtil.class,"/images/close_active.png"));
			}
			
			//����ɿ�ʱ
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
				//System.exit(0);
			}
		});
	}
	
	/**
	 * ��С������
	 * @param shell ��Ҫ��С�������
	 * @param label ���ʻ���label
	 */
	public void minOp(final Shell shell,final Label label){
		label.addMouseTrackListener(new MouseTrackAdapter(){
			//����ƿ�ʱ
			@Override
			public void mouseExit(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(ShellUtil.class,"/images/shrink_normal.png"));
			}
			
			//�������ʱ
			@Override
			public void mouseHover(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(ShellUtil.class,"/images/shrink_hover.png"));
			}
		});
		
		label.addMouseListener(new MouseAdapter(){
			//��갴��ʱ
			@Override
			public void mouseDown(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(ShellUtil.class,"/images/shrink_active.png"));
			}
			
			//����ɿ�ʱ
			@Override
			public void mouseUp(MouseEvent e) {
				shell.setMinimized(true);
			}
		});
	}
	
	/**
	 * ��ȡexcel
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static List<String> readExcel() throws BiffException, IOException{
		List<String> list = new ArrayList<String>();//����һ��list �����洢��ȡ������
		Workbook rwb = null;
		Cell cell = null;
		
		InputStream stream = new FileInputStream(new File("bin/Test.xls"));//����������	
		rwb = Workbook.getWorkbook(stream);//��ȡExcel�ļ�����
		Sheet sheet = rwb.getSheet(0);  //��ȡ�ļ���ָ�������� Ĭ�ϵĵ�һ��
		
		for(int i=0; i<sheet.getRows(); i++){//����(��ͷ��Ŀ¼����Ҫ����1��ʼ
			String[] str = new String[sheet.getColumns()];//����һ������ �����洢ÿһ�е�ֵ
			
			for(int j=0; j<sheet.getColumns(); j++){//����
				cell = sheet.getCell(j,i);//��ȡ��i�У���j�е�ֵ    
				str[j] = cell.getContents();
				list.add(str[j]);//�Ѹջ�ȡ���д���list
				
			}
		}
		return list;
	}
	
	/**
	 * ʹLabel��ͼƬ����Ӧ
	 * @param label ͼƬҪ��ʾ��λ��
	 * @param path ͼƬ�ĵ�ַ
	 * @return
	 */
	public Image getImage(Label label,String path){
		Image image=null;
		File file=new File(path); //��ȡͼƬ
		InputStream is=null;
		ImageData id=null;
		
		try {
			is=new FileInputStream(file); //�����ķ�ʽ��ȡͼƬ
			id=new ImageData(is); //���ļ�����ΪͼƬ����
			id=id.scaledTo(label.getSize().x, label.getSize().y); //�ı�ͼƬ��С
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		image=new Image(null,id);
		
		return image;
	}
	
	
	/**
	 * ��ȡͼƬ
	 * @return
	 */
	public Image getImage(byte[] bt,int width,int height){
		Image image=null;
		
		InputStream is=null;
		ImageData id=null;
	
		is=new ByteArrayInputStream(bt);
		id=new ImageData(is); //���ļ�����ΪͼƬ����
		id=id.scaledTo(width,height); //�ı�ͼƬ��С
		image=new Image(null,id);
		
		return image;
	}
	
	/**
	 * ʹcomposite����ͼƬ����Ӧ
	 * @param is �ļ���
	 * @param width:��
	 * @param height:��
	 * @return
	 */
	public  Image ImageSize(InputStream is,int width,int height){
		ImageData data=new ImageData(is);
		data=data.scaledTo(width, height);
		return new Image(null,data);
	}
	
	/**
	 * ʹcomposite������
	 * @param shell
	 */
	public  void ShowCenter(Shell shell){
		shell.setLocation( (Display.getDefault().getClientArea().width-shell.getSize().x)/2,
				(Display.getDefault().getClientArea().height-shell.getSize().y)/2);
	}
	
	/**
	 * ʹButton��ť����ͼƬ����Ӧ
	 * @param button1����ť
	 * @param path��·��
	 * @return
	 */
	public Image showButtonImage(Button button1,String path){
		Image image=null;
		File file=new File(path); //��ȡͼƬ
		InputStream is=null;
		ImageData id=null;
		
		try {
			is=new FileInputStream(file); //�����ķ�ʽ��ȡͼƬ
			id=new ImageData(is); //���ļ�����ΪͼƬ����
			id=id.scaledTo(button1.getSize().x, button1.getSize().y); //�ı�ͼƬ��С
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		image=new Image(null,id);
		
		return image;
	}
	/**
	 * ʹLabel����ͼƬ����Ӧ
	 * @param button1����ť
	 * @param path��·��
	 * @return
	 */
	public Image showLabelImage(Label Lable_1,String path){
		Image image=null;
		File file=new File(path); //��ȡͼƬ
		InputStream is=null;
		ImageData id=null;
		
		try {
			is=new FileInputStream(file); //�����ķ�ʽ��ȡͼƬ
			id=new ImageData(is); //���ļ�����ΪͼƬ����
			id=id.scaledTo(Lable_1.getSize().x, Lable_1.getSize().y); //�ı�ͼƬ��С
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} finally{
			if(is!=null){
				try {
					is.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		image=new Image(null,id);
		
		return image;
	}
}
