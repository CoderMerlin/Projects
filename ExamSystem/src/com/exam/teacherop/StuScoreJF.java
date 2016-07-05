package com.exam.teacherop;

import java.awt.BasicStroke;
import java.util.List;
import java.util.Map;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.jfree.chart.ChartColor;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.LineAndShapeRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;

import com.exam.utils.StuInfo;
import com.swtdesigner.SWTResourceManager;

public class StuScoreJF {
	

	protected Shell shell;
	private StuInfo stuInfo=new StuInfo();
	private String stuid;
	/**
	 * Launch the application.
	 * @param args
	 */
	
	public StuScoreJF(String stuid){
		this.stuid=stuid;
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
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
		shell = new Shell();
		shell.setImage(SWTResourceManager.getImage(ClassScoreChart.class, "/images/\u777F\u53CB\u5934\u50CF_\u9542\u7A7A.png"));
		shell.setSize(900, 600);
		shell.setText(stuid+"�����п��Գɼ�����");
		
		JFreeChart chart=createStuScore();
		final ChartComposite frame = new ChartComposite(shell, SWT.NONE, chart, true);
		frame.pack();
		shell.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				frame.setBounds(0, 0, shell.getClientArea().width, shell.getClientArea().height);
			}
		});
		
	}
	
	private JFreeChart createStuScore(){
		List<Map<String,Object>> list=stuInfo.findStuAllExamScore(stuid);
		// ��ͼ���ݼ�  
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset(); 
        String[] score=new String[list.size()];  //�����
        String[] stuids=new String[list.size()];   //��ѧ��
        String[] qname=new String[list.size()];
		for(int i=0;i<list.size();i++){
			score[i] =(String) list.get(i).get("ESCORE");
			stuids[i]=(String) list.get(i).get("STUID");
			qname[i]=(String) list.get(i).get("QNAME");
			
		}
		for(int j=0;j<list.size();j++){
			dataSet.addValue(Integer.valueOf(score[j]), stuids[j], qname[j]);
		}
		JFreeChart  chart = ChartFactory.createLineChart(stuid+"ѧ���ĳɼ�����", "������Ŀ�Ծ�", stuid+"���ķ���", dataSet,  
	            PlotOrientation.VERTICAL, // ���Ʒ���  
	            true, // ��ʾͼ��  
	            true, // ���ñ�׼������  
	            false // �Ƿ����ɳ�����  
	            );  
	   // chart.getTitle().setFont(titleFont); // ���ñ�������  
	    //chart.getLegend().setItemFont(font);// ����ͼ���������  
	    chart.setBackgroundPaint(ChartColor.WHITE);// ���ñ���ɫ   
	    //��ȡ��ͼ������  
	    CategoryPlot plot = chart.getCategoryPlot();  
	    plot.setBackgroundPaint(ChartColor.LIGHT_GRAY); // ���û�ͼ������ɫ  
	    plot.setRangeGridlinePaint(ChartColor.WHITE); // ����ˮƽ���򱳾�����ɫ  
	    plot.setRangeGridlinesVisible(true);// �����Ƿ���ʾˮƽ���򱳾���,Ĭ��ֵΪtrue  
	    plot.setDomainGridlinePaint(ChartColor.WHITE); // ���ô�ֱ���򱳾�����ɫ  
	    plot.setDomainGridlinesVisible(true); // �����Ƿ���ʾ��ֱ���򱳾���,Ĭ��ֵΪfalse  
	    
	      
	    CategoryAxis domainAxis = plot.getDomainAxis();     
	/*  domainAxis.setLabelFont(font); // ���ú�������  
	    domainAxis.setTickLabelFont(font);// ������������ֵ����  */	    
	    domainAxis.setLowerMargin(0.01);// ��߾� �߿����  
	    domainAxis.setUpperMargin(0.06);// �ұ߾� �߿����,��ֹ���ߵ�һ�����ݿ����������ᡣ  
	    domainAxis.setMaximumCategoryLabelLines(2);  
	      
	    ValueAxis rangeAxis = plot.getRangeAxis();  
	    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//Y����ʾ����  
	    rangeAxis.setAutoRangeMinimumSize(1);   //��С���  
	    rangeAxis.setUpperMargin(0.18);//�ϱ߾�,��ֹ����һ�����ݿ����������ᡣ     
	    rangeAxis.setLowerBound(0);   //��Сֵ��ʾ0  
	    rangeAxis.setAutoRange(false);   //���Զ�����Y������  
	    rangeAxis.setTickMarkStroke(new BasicStroke(1.6f));     // ���������Ǵ�С  
	    rangeAxis.setTickMarkPaint(ChartColor.BLACK);     // ������������ɫ  

	      
	      
	 // ��ȡ���߶���  
	    LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();  
	    BasicStroke realLine = new BasicStroke(1.8f); // ����ʵ��  
	    // ��������  
	    float dashes[] = { 5.0f };   
	    BasicStroke brokenLine = new BasicStroke(2.2f, // ������ϸ  
	            BasicStroke.CAP_ROUND, // �˵���  
	            BasicStroke.JOIN_ROUND, // �۵���  
	            8f, dashes, 0.6f);   
	    for (int i = 0; i < dataSet.getRowCount(); i++) {  
	        if (i % 2 == 0)  
	            renderer.setSeriesStroke(i, realLine); // ����ʵ�߻���  
	        else  
	            renderer.setSeriesStroke(i, brokenLine); // �������߻���  
	    }  
	      
	    plot.setNoDataMessage("�޶�Ӧ�����ݣ������²�ѯ��");  
//	    plot.setNoDataMessageFont(titleFont);//����Ĵ�С  
	    plot.setNoDataMessagePaint(ChartColor.RED);//������ɫ  
	    
	    return chart;
	}
	

}
