/**
 * <p>Copyright: Copyright (c) 2012</p>
 * @version 1.0
 */
package com.namnd.amdf.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;

import javax.swing.JPanel;

import com.namnd.amdf.wave.WavFileProc;
import com.namnd.amdf.wave.WavInfo;

/**
 * @author namnd
 * @mobile 0986001325
 * @email: dinhnam.yt@gmail.com
 * @Date: Thursday, October 11, 2012
 */
@SuppressWarnings("serial")
public class SingleWavePanel extends JPanel implements Runnable {

	protected WavFileProc wavFileProc;
	private int chanelIndex;

	private Color drawColor;
	private Color backGround = Color.BLACK;
	int width, height;
	double xStep;
	int length;
	double maxSecond;
	int[] samples;

	// do rong cua cua so
	int timeFrameLength;// ms

	WavePanel wp;

	/**
	 * Constructor
	 * 
	 * @param wavFileProc
	 * @param chanelIndex
	 */
	public SingleWavePanel(WavePanel wp, WavFileProc wavFileProc,
			int chanelIndex) {
		this.wp = wp;
		this.wavFileProc = wavFileProc;
		this.chanelIndex = chanelIndex;
		samples = wavFileProc.getSamples(chanelIndex);
		long sampleRate = wavFileProc.getWavInfo().getSampleRate();
		length = samples.length;
		maxSecond = 1000.0 * ((double) length / sampleRate);
		timeFrameLength = OptionUI.getInstance().getK();
		setBackground(backGround);
	}

	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		height = getHeight();
		width = getWidth();
		if (chanelIndex == 0)
			drawColor = Color.GREEN;
		else if (chanelIndex == 1)
			drawColor = Color.BLUE;
		drawChart(g);
		drawWaveForm(g, samples);
	}

	protected void drawWaveForm(Graphics g, int[] samples) {
		Graphics2D g2d = (Graphics2D) g;
		if (samples == null)
			return;
		int lineHeight = height / 2;
		int max = getMaxOff();
		int min = getMinOff();
		xStep = (width - 60.0) / length;
		double yStep = (lineHeight - 20.0) / getMaximum(max, min);
		g2d.setColor(drawColor);
		int pointX = 40;
		for (int i = 0; i < length - 1; i++) {
			g2d.draw(new Line2D.Double(pointX + i * xStep, lineHeight
					- samples[i] * yStep, pointX + (i + 1) * xStep, lineHeight
					- samples[i + 1] * yStep));
		}
		int count = 40;
		for (int i = 1; i < count + 1; i++) {
			g2d.setColor(Color.DARK_GRAY);
			g2d.drawLine(pointX + i * (width - 60) / count, 10, pointX + i
					* (width - 60) / count, height - 20);
			if (chanelIndex > 0)
				continue;
			else {
				g2d.setColor(Color.GRAY);
				if (i % 4 == 0)
					g2d.drawString("" + i * maxSecond / count, pointX - 10 + i
							* (width - 60) / count, height - 5);
			}

		}
		int countY = (int) ((max * yStep) / ((width - 60) / count)) + 1;
		g2d.setColor(Color.DARK_GRAY);
		for (int i = -countY; i < countY + 1; i++)
			if (i == 0)
				continue;
			else {
				g2d.draw(new Line2D.Double(20, lineHeight - i
						* (max * yStep / countY), width, lineHeight - i
						* (max * yStep / countY)));
				if (i != countY)
					g2d.drawString("" + i * (max / countY), 5,
							(int) (lineHeight - i * (max / countY) * yStep));
			}

		g2d.setColor(Color.WHITE);
		g2d.drawString("" + max, 5, (int) (lineHeight - max * yStep));
		g2d.drawString("" + min, 5, (int) (lineHeight - min * yStep));
		// ve cua so
		drawFrameWindow(g, xStep * getFrameLength());
	}

	public int[] getSamples() {
		return samples;
	}

	public int getMaximum(int a, int b) {
		int max = Math.abs(a);
		if (Math.abs(b) > max)
			max = Math.abs(b);
		return max;
	}

	public int getMaxOff() {
		int max = samples[0];
		int leng = samples.length;
		for (int i = 1; i < leng; i++) {
			if (samples[i] > max) {
				max = samples[i];
			}
		}
		return max;
	}

	// lay gia tri nho nhat
	public int getMinOff() {
		int min = samples[0];
		int leng = samples.length;
		for (int i = 1; i < leng; i++) {
			if (samples[i] < min) {
				min = samples[i];
			}
		}
		return min;
	}

	/**
	 * Ve cac truc toa do
	 * 
	 * @param g
	 */
	public void drawChart(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int lineHeight = height / 2;
		g2d.setColor(Color.WHITE);

		int pointX = 40;
		g2d.drawLine(pointX, 5, pointX, height - 15); // tung
		g2d.drawLine(pointX, 5, pointX + 5, 10); // mui ten truc tung
		g2d.drawLine(pointX, 5, pointX - 5, 10);
		g2d.drawLine(10, lineHeight, width - 10, lineHeight); // hoanh
		g2d.drawLine(width - 10, lineHeight, width - 15, lineHeight + 5);
		g2d.drawLine(width - 10, lineHeight, width - 15, lineHeight - 5);
		g2d.drawString("o", pointX - 3, lineHeight + 4);
		g2d.setColor(Color.WHITE);
		g2d.drawString("0", 30, lineHeight + 15);
		g2d.drawString("|", width - 60 + pointX, lineHeight + 4);
		g2d.drawString("ms", width - 20, lineHeight + 20);
		g2d.drawString("A", 10, 10);

	}

	double step = 0.0;

	protected void drawFrameWindow(Graphics g, double xwidth) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.yellow);
		g2d.draw(new Line2D.Double(40 + step, 5, xwidth + 40 + step, 5));
		g2d.draw(new Line2D.Double(40 + step, 5, 40 + step, height - 15));
		g2d.draw(new Line2D.Double(xwidth + 40 + step, 5, xwidth + 40 + step,
				height - 15));
		g2d.draw(new Line2D.Double(40 + step, height - 15, xwidth + 40 + step,
				height - 15));
	}

	public long getSampleRate() {
		return wavFileProc.getWavInfo().getSampleRate();
	}

	// tong thoi gian
	public double getMaxSecond() {
		return maxSecond;
	}

	// lay do rong cua so
	public int getFrameLength() {
		return (int) (length * timeFrameLength / maxSecond);
	}

	// information of wave file
	public WavInfo getWavInfo() {
		return wavFileProc.getWavInfo();
	}

	boolean stop = false;

	public synchronized void setStop(boolean stop) {
		this.stop = stop;
	}

	int startFrame = 0;

	public synchronized int getStartFrame() {
		return startFrame;
	}

	public synchronized void reset() {
		this.step = 0.0;
		startFrame = 0;
		stop = false;
		this.repaint();
		timeFrameLength = OptionUI.getInstance().getK();
		wp.updateGUI();
	}

	long sleepTime = 600;

	@Override
	public void run() {
		do {
			try {
				double w = xStep * getFrameLength();
				step += w / 3;
				startFrame += getFrameLength() / 3;
				wp.updateGUI();
				this.repaint();
				Thread.sleep(sleepTime);
				sleepTime += timeFrameLength;
				if ((w + 60 + step) >= width) {
					break;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
				break;
			}
		} while (!stop);
	}

}
