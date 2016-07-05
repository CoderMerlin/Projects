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
	 * @param cid �༶���
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
		shell.setText("ƽ������״ͼ");

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

		//����Chart ͼ�����	��������	��������	���� ��ʾ��ʽ	��ʾ��ʾ ? ?
		JFreeChart chart = ChartFactory.createBarChart(cname+"�ɼ�һ��","","ƽ����", dcd,PlotOrientation.VERTICAL,true,true,false);

		chart.setBackgroundPaint(Color.white); //����ɫ

		CategoryPlot plot = (CategoryPlot) chart.getPlot(); //���ͼ���м䲿�֣���plot
		plot.setBackgroundPaint(Color.lightGray); //����ͼ����ɫ
		//plot.setDomainGridlinePaint(Color.white); //��ʾ���붥���ľ���
		plot.setDomainGridlinesVisible(true);
		plot.setRangeGridlinePaint(Color.white); //�����ߵ���ɫ

		final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
		rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

		BarRenderer renderer = (BarRenderer) plot.getRenderer();
		renderer.setDrawBarOutline(false);

		//�������Ӽ�ľ���
//		BarRenderer render = (BarRenderer) plot.getRenderer();
//		if(testNum<=5){
//			render.setItemMargin(0.7);
//		}

		return chart;
	}
}
