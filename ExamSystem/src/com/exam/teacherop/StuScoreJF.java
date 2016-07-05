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
		shell.setText(stuid+"的所有考试成绩报表");
		
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
		// 绘图数据集  
        DefaultCategoryDataset dataSet = new DefaultCategoryDataset(); 
        String[] score=new String[list.size()];  //存分数
        String[] stuids=new String[list.size()];   //存学号
        String[] qname=new String[list.size()];
		for(int i=0;i<list.size();i++){
			score[i] =(String) list.get(i).get("ESCORE");
			stuids[i]=(String) list.get(i).get("STUID");
			qname[i]=(String) list.get(i).get("QNAME");
			
		}
		for(int j=0;j<list.size();j++){
			dataSet.addValue(Integer.valueOf(score[j]), stuids[j], qname[j]);
		}
		JFreeChart  chart = ChartFactory.createLineChart(stuid+"学生的成绩报表", "所考科目试卷", stuid+"所的分数", dataSet,  
	            PlotOrientation.VERTICAL, // 绘制方向  
	            true, // 显示图例  
	            true, // 采用标准生成器  
	            false // 是否生成超链接  
	            );  
	   // chart.getTitle().setFont(titleFont); // 设置标题字体  
	    //chart.getLegend().setItemFont(font);// 设置图例类别字体  
	    chart.setBackgroundPaint(ChartColor.WHITE);// 设置背景色   
	    //获取绘图区对象  
	    CategoryPlot plot = chart.getCategoryPlot();  
	    plot.setBackgroundPaint(ChartColor.LIGHT_GRAY); // 设置绘图区背景色  
	    plot.setRangeGridlinePaint(ChartColor.WHITE); // 设置水平方向背景线颜色  
	    plot.setRangeGridlinesVisible(true);// 设置是否显示水平方向背景线,默认值为true  
	    plot.setDomainGridlinePaint(ChartColor.WHITE); // 设置垂直方向背景线颜色  
	    plot.setDomainGridlinesVisible(true); // 设置是否显示垂直方向背景线,默认值为false  
	    
	      
	    CategoryAxis domainAxis = plot.getDomainAxis();     
	/*  domainAxis.setLabelFont(font); // 设置横轴字体  
	    domainAxis.setTickLabelFont(font);// 设置坐标轴标尺值字体  */	    
	    domainAxis.setLowerMargin(0.01);// 左边距 边框距离  
	    domainAxis.setUpperMargin(0.06);// 右边距 边框距离,防止最后边的一个数据靠近了坐标轴。  
	    domainAxis.setMaximumCategoryLabelLines(2);  
	      
	    ValueAxis rangeAxis = plot.getRangeAxis();  
	    rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());//Y轴显示整数  
	    rangeAxis.setAutoRangeMinimumSize(1);   //最小跨度  
	    rangeAxis.setUpperMargin(0.18);//上边距,防止最大的一个数据靠近了坐标轴。     
	    rangeAxis.setLowerBound(0);   //最小值显示0  
	    rangeAxis.setAutoRange(false);   //不自动分配Y轴数据  
	    rangeAxis.setTickMarkStroke(new BasicStroke(1.6f));     // 设置坐标标记大小  
	    rangeAxis.setTickMarkPaint(ChartColor.BLACK);     // 设置坐标标记颜色  

	      
	      
	 // 获取折线对象  
	    LineAndShapeRenderer renderer = (LineAndShapeRenderer) plot.getRenderer();  
	    BasicStroke realLine = new BasicStroke(1.8f); // 设置实线  
	    // 设置虚线  
	    float dashes[] = { 5.0f };   
	    BasicStroke brokenLine = new BasicStroke(2.2f, // 线条粗细  
	            BasicStroke.CAP_ROUND, // 端点风格  
	            BasicStroke.JOIN_ROUND, // 折点风格  
	            8f, dashes, 0.6f);   
	    for (int i = 0; i < dataSet.getRowCount(); i++) {  
	        if (i % 2 == 0)  
	            renderer.setSeriesStroke(i, realLine); // 利用实线绘制  
	        else  
	            renderer.setSeriesStroke(i, brokenLine); // 利用虚线绘制  
	    }  
	      
	    plot.setNoDataMessage("无对应的数据，请重新查询。");  
//	    plot.setNoDataMessageFont(titleFont);//字体的大小  
	    plot.setNoDataMessagePaint(ChartColor.RED);//字体颜色  
	    
	    return chart;
	}
	

}
