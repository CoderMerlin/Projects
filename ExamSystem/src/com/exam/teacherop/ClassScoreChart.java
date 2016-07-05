package com.exam.teacherop;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import com.exam.utils.ClassInfo;
import java.awt.Color;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.experimental.chart.swt.ChartComposite;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.events.PaintEvent;
import com.swtdesigner.SWTResourceManager;

public class ClassScoreChart {

	protected Shell shell;

	private ClassInfo ci=new ClassInfo();
	private String cid;

	/**
	 * @param cid 班级编号
	 */
	public void setValues(String cid){
		this.cid=cid;
	}

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			ClassScoreChart window = new ClassScoreChart();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
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
		shell.setSize(1000, 618);
		shell.setText("平均分柱状图");

		JFreeChart chart = createChart();
		final ChartComposite frame = new ChartComposite(shell, SWT.NONE, chart, true);
		frame.pack();

		shell.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				frame.setBounds(0, 0, shell.getClientArea().width, shell.getClientArea().height);
			}
		});
	}

	private JFreeChart createChart() {
		String cname=ci.findCName(cid);
		DefaultCategoryDataset dcd=new DefaultCategoryDataset();
		int testNum=(Integer) ci.findAVGScore(cid).get(0);
		for(int i=0;i<testNum;i++){
			dcd.addValue(Double.valueOf(((String[])ci.findAVGScore(cid).get(1))[i]), ((String[])ci.findAVGScore(cid).get(2))[i], "");
		}

		//创建Chart 图标标题	横轴名字	纵轴名字	数据 显示方式	显示提示 ? ?
		JFreeChart chart = ChartFactory.createBarChart(cname+"成绩一览","","平均分", dcd,PlotOrientation.VERTICAL,true,true,false);

		chart.setBackgroundPaint(Color.white); //背景色

		CategoryPlot plot = (CategoryPlot) chart.getPlot(); //获得图表中间部分，即plot
		plot.setBackgroundPaint(Color.lightGray); //设置图表背景色
		//plot.setDomainGridlinePaint(Color.white); //显示距离顶部的距离
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.white); //横轴线的颜色

		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		//设置柱子间的距离
//		BarRenderer render = (BarRenderer) plot.getRenderer();
//		if(testNum<=5){
//			render.setItemMargin(0.7);
//		}

		return chart;
	}
}
