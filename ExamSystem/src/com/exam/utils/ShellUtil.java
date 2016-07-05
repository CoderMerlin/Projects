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
 * 面板工具
 * @author rdz
 *
 */
public class ShellUtil {
	
	private boolean flag=false; //记录鼠标是否按下
	private int clickX; //鼠标点击时的X轴的值
	private int clickY; //鼠标点击时的Y轴的值
	
	/**
	 * 显示托盘
	 * @param display
	 * @param shell 需要显示托盘的面板
	 * @parsm name 鼠标移上去时要显示的名称
	 */
	public void tray(Display display, final Shell shell,String name){
		Tray tray=display.getSystemTray(); //获取系统托盘区
		TrayItem trayItem;
		
		if(tray==null){ //系统不支持托盘
			MessageDialog.openError(shell, "错误信息", "当前系统不支持托盘...");
		} else{
			trayItem=new TrayItem(tray,SWT.NONE);
			trayItem.setToolTipText(name); //当鼠标放到图标上时显示名称
			
			trayItem.setImage(shell.getImage()); //设置托盘图标
			
			trayItem.addListener(SWT.Selection, new Listener(){
				public void handleEvent(Event arg0) {
					if(shell.getMinimized()){ //如果当前界面是最小化的
						shell.setMinimized(false);
						//shell.setMaximized(true);
					} else{
						if(shell.getVisible()){ //如果是显示的
							shell.setVisible(false);
						} else{
							shell.setVisible(true);
						}
					}
				}
			});
			
			final Menu menu=new Menu(shell, SWT.POP_UP); //当右击菜单时
			
			MenuItem maxItem=new MenuItem(menu, SWT.PUSH); //普通型
			maxItem.setImage(SWTResourceManager.getImage(ShellUtil.class, "/images/最大化_托盘.png"));
			maxItem.setText("最大化");
			maxItem.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e) {
					shell.setVisible(true);
					shell.setMaximized(true);
				}
			});
			
			MenuItem minItem=new MenuItem(menu, SWT.PUSH); //普通型
			minItem.setImage(SWTResourceManager.getImage(ShellUtil.class, "/images/最小化_托盘.png"));
			minItem.setText("最小化");
			minItem.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e) {
					shell.setVisible(true);
					shell.setMinimized(true);
				}
			});
			
			//分割线
			new MenuItem(menu,SWT.SEPARATOR);
			
			MenuItem exitItem=new MenuItem(menu, SWT.PUSH); //普通型 
			exitItem.setImage(SWTResourceManager.getImage(ShellUtil.class, "/images/关闭_托盘.jpg"));
			exitItem.setText("退出");
			exitItem.addSelectionListener(new SelectionAdapter(){
				public void widgetSelected(SelectionEvent e) {
					Display.getCurrent().close();
					System.exit(0);
				}
			});
			
			
			trayItem.addListener(SWT.MenuDetect, new Listener(){ //Detect:发现
				public void handleEvent(Event arg0) {
					menu.setVisible(true);
				}
				
			});
		}
	}
	
	/**
	 * 使背景图适应面板大小
	 * @param display
	 * @param shell 要操作的面板
	 * @param path 图片文件路径
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
	 * 使背景图适应面板大小
	 * @param display
	 * @param composite 板块
	 * @param path 图片路径
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
	 * 居中显示
	 * @param display
	 * @param shell 需要居中显示的面板
	 */
	public void centerShow(Display display, Shell shell){
		shell.setLocation((display.getClientArea().width-shell.getClientArea().width)/2, 
				(display.getClientArea().height-shell.getClientArea().height)/2);
	}
	
	
	/**
	 * 移动面板
	 * @param composite 监听的面板
	 * @param shell 所需移动的面板
	 */
	public void shellMove(Composite composite, final Shell shell){
		composite.addMouseMoveListener(new MouseMoveListener(){
			public void mouseMove(MouseEvent e) {
				if(flag){
					//移动面板
					shell.setLocation(shell.getLocation().x+e.x-clickX, shell.getLocation().y+e.y-clickY);
				}
			}
			
		});
		
		composite.addMouseListener(new MouseAdapter(){
			//鼠标按下时
			public void mouseDown(MouseEvent e) {
				flag=true;
				clickX=e.x;
				clickY=e.y;
			}

			//鼠标松开时
			public void mouseUp(MouseEvent e) {
				flag=false;
			}
		});
	}
	
	/**
	 * 关闭监听
	 * @param shell 需要关闭的面板
	 * @param label 关闭的label
	 */
	public void closeOp(final Shell shell,final Label label){
		label.addMouseTrackListener(new MouseTrackAdapter(){
			//鼠标移开时
			@Override
			public void mouseExit(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(ShellUtil.class,"/images/close_normal.png"));
			}
			
			//鼠标移上时
			@Override
			public void mouseHover(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(ShellUtil.class,"/images/close_hover.png"));
			}
		});
		
		label.addMouseListener(new MouseAdapter(){
			//鼠标按下时
			@Override
			public void mouseDown(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(ShellUtil.class,"/images/close_active.png"));
			}
			
			//鼠标松开时
			@Override
			public void mouseUp(MouseEvent e) {
				shell.dispose();
				//System.exit(0);
			}
		});
	}
	
	/**
	 * 最小化监听
	 * @param shell 需要最小化的面板
	 * @param label 最鲜花的label
	 */
	public void minOp(final Shell shell,final Label label){
		label.addMouseTrackListener(new MouseTrackAdapter(){
			//鼠标移开时
			@Override
			public void mouseExit(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(ShellUtil.class,"/images/shrink_normal.png"));
			}
			
			//鼠标移上时
			@Override
			public void mouseHover(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(ShellUtil.class,"/images/shrink_hover.png"));
			}
		});
		
		label.addMouseListener(new MouseAdapter(){
			//鼠标按下时
			@Override
			public void mouseDown(MouseEvent e) {
				label.setImage(SWTResourceManager.getImage(ShellUtil.class,"/images/shrink_active.png"));
			}
			
			//鼠标松开时
			@Override
			public void mouseUp(MouseEvent e) {
				shell.setMinimized(true);
			}
		});
	}
	
	/**
	 * 读取excel
	 * @return
	 * @throws BiffException
	 * @throws IOException
	 */
	public static List<String> readExcel() throws BiffException, IOException{
		List<String> list = new ArrayList<String>();//创建一个list 用来存储读取的内容
		Workbook rwb = null;
		Cell cell = null;
		
		InputStream stream = new FileInputStream(new File("bin/Test.xls"));//创建输入流	
		rwb = Workbook.getWorkbook(stream);//获取Excel文件对象
		Sheet sheet = rwb.getSheet(0);  //获取文件的指定工作表 默认的第一个
		
		for(int i=0; i<sheet.getRows(); i++){//行数(表头的目录不需要，从1开始
			String[] str = new String[sheet.getColumns()];//创建一个数组 用来存储每一列的值
			
			for(int j=0; j<sheet.getColumns(); j++){//列数
				cell = sheet.getCell(j,i);//获取第i行，第j列的值    
				str[j] = cell.getContents();
				list.add(str[j]);//把刚获取的列存入list
				
			}
		}
		return list;
	}
	
	/**
	 * 使Label框图片自适应
	 * @param label 图片要显示的位置
	 * @param path 图片的地址
	 * @return
	 */
	public Image getImage(Label label,String path){
		Image image=null;
		File file=new File(path); //获取图片
		InputStream is=null;
		ImageData id=null;
		
		try {
			is=new FileInputStream(file); //以流的方式获取图片
			id=new ImageData(is); //将文件流变为图片数据
			id=id.scaledTo(label.getSize().x, label.getSize().y); //改变图片大小
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
	 * 读取图片
	 * @return
	 */
	public Image getImage(byte[] bt,int width,int height){
		Image image=null;
		
		InputStream is=null;
		ImageData id=null;
	
		is=new ByteArrayInputStream(bt);
		id=new ImageData(is); //将文件流变为图片数据
		id=id.scaledTo(width,height); //改变图片大小
		image=new Image(null,id);
		
		return image;
	}
	
	/**
	 * 使composite背景图片自适应
	 * @param is 文件流
	 * @param width:宽
	 * @param height:高
	 * @return
	 */
	public  Image ImageSize(InputStream is,int width,int height){
		ImageData data=new ImageData(is);
		data=data.scaledTo(width, height);
		return new Image(null,data);
	}
	
	/**
	 * 使composite面板居中
	 * @param shell
	 */
	public  void ShowCenter(Shell shell){
		shell.setLocation( (Display.getDefault().getClientArea().width-shell.getSize().x)/2,
				(Display.getDefault().getClientArea().height-shell.getSize().y)/2);
	}
	
	/**
	 * 使Button按钮背景图片自适应
	 * @param button1：按钮
	 * @param path：路径
	 * @return
	 */
	public Image showButtonImage(Button button1,String path){
		Image image=null;
		File file=new File(path); //获取图片
		InputStream is=null;
		ImageData id=null;
		
		try {
			is=new FileInputStream(file); //以流的方式获取图片
			id=new ImageData(is); //将文件流变为图片数据
			id=id.scaledTo(button1.getSize().x, button1.getSize().y); //改变图片大小
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
	 * 使Label背景图片自适应
	 * @param button1：按钮
	 * @param path：路径
	 * @return
	 */
	public Image showLabelImage(Label Lable_1,String path){
		Image image=null;
		File file=new File(path); //获取图片
		InputStream is=null;
		ImageData id=null;
		
		try {
			is=new FileInputStream(file); //以流的方式获取图片
			id=new ImageData(is); //将文件流变为图片数据
			id=id.scaledTo(Lable_1.getSize().x, Lable_1.getSize().y); //改变图片大小
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
