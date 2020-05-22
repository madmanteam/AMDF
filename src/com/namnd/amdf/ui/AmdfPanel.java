package com.namnd.amdf.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JSplitPane;

@SuppressWarnings("serial")
public final class AmdfPanel extends JSplitPane {

	int[] samples;
	private final Color backGround = Color.BLACK;
	int pointX = 40;
	double maxSecond;
	int frameLength;
	int maxOff, minOff;
	private final Color drawColor;
	FrameWindow amdfWindow;
	DkWindow dkWindow;
	F0Window f0Window;
	long sampleRate;
	int startFrame;

	public AmdfPanel(int[] samples, int startFrame, int frameLength,
			long sampleRate) {
		super(HORIZONTAL_SPLIT);
		this.samples = samples;
		this.frameLength = frameLength;
		this.sampleRate = sampleRate;
		this.startFrame = startFrame;
		this.maxOff = getMaxOff();
		this.minOff = getMinOff();
		drawColor = Color.GREEN;
		maxSecond = 1000.0 * ((double) frameLength / sampleRate);
		this.setLayout(new GridLayout(0, 2));
		amdfWindow = new FrameWindow();
		dkWindow = new DkWindow();
		f0Window = new F0Window();
		setOneTouchExpandable(true);
		setDividerLocation(600);
		this.setLeftComponent(amdfWindow);
		this.setRightComponent(dkWindow);
	}

	public F0Window getF0Window() {
		return f0Window;
	}

	public void setLength(int length) {
		this.frameLength = length;
	}

	public void setStartFrame(int startFrame) {
		this.startFrame = startFrame;
	}

	public void updateGUI() {
		amdfWindow.repaint();
		dkWindow.repaint();
		f0Window.repaint();
	}

	public int[] getSamplesInFrame() {
		int[] returnValues = new int[frameLength];
		for (int i = 0; i < frameLength; i++) {
			try {
				returnValues[i] = samples[startFrame + i];
			} catch (ArrayIndexOutOfBoundsException e) {
				returnValues[i] = 0;
			}
		}
		return returnValues;
	}

	public int getMaximum(int a, int b) {
		int max = Math.abs(a);
		if (Math.abs(b) > max)
			max = Math.abs(b);
		return max;
	}

	public int getMaxOff() {
		int max = samples[0];
		int length = samples.length;
		for (int i = 1; i < length; i++) {
			if (samples[i] > max) {
				max = samples[i];
			}
		}
		return max;
	}

	// lay gia tri nho nhat
	public int getMinOff() {
		int min = samples[0];
		int length = samples.length;
		for (int i = 1; i < length; i++) {
			if (samples[i] < min) {
				min = samples[i];
			}
		}
		return min;
	}

	class DkWindow extends JPanel {
		int width, height;
		double xStep;

		public DkWindow() {
			setBackground(backGround);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			height = getHeight();
			width = getWidth();
			drawChart(g);
			drawDk(g, width, height);
		}

		protected void drawDk(Graphics g, int width, int height) {
			Graphics2D g2d = (Graphics2D) g;
			if (samples == null)
				return;
			int lineHeight = height - 20;
			xStep = (width - 60.0) / frameLength;
			double yStep = (lineHeight - 20.0) / getMaxDk();
			int count = 40;
			for (int i = 1; i < count + 1; i++) {
				g2d.setColor(Color.GRAY);
				if (i % 4 == 0)
					g2d.drawString("" + i * frameLength / count, pointX - 10
							+ i * (width - 60) / count, height - 5);

			}
			g2d.setColor(drawColor);
			for (int i = 0; i < dk().length - 1; i++) {
				g2d.draw(new Line2D.Double(pointX + i * xStep, lineHeight
						- dk()[i] * yStep, pointX + (i + 1) * xStep, lineHeight
						- dk()[i + 1] * yStep));
			}
			int position = getDkMinPosition();
			g2d.setColor(Color.RED);
			if (position < (frameLength / 2))
				g2d.draw(new Line2D.Double(pointX + position * xStep, 10,
						pointX + position * xStep, height - 20));

		}

		public int[] dk() {
			int[] returnValues = new int[frameLength];
			for (int k = 0; k < frameLength; k++) {
				returnValues[k] = 0;
				for (int n = 0; n < frameLength; n++) {
					if ((n + startFrame) < k)
						returnValues[k] += getAbs(samples[n + startFrame], 0);
					else if ((n + startFrame - k) >= samples.length)
						returnValues[k] += 0;
					else if (((n + startFrame) >= samples.length)
							&& ((n + startFrame - k) < samples.length))
						returnValues[k] += getAbs(0,
								samples[n + startFrame - k]);
					else
						returnValues[k] += getAbs(samples[n + startFrame],
								samples[n + startFrame - k]);
				}
			}
			return returnValues;
		}

		public int getDkMinPosition() {
			List<Integer> ilist = new ArrayList<>();
			List<Integer> iIndex = new ArrayList<>();
			for (int i = 2; i < frameLength - 2; i++) {
				if ((dk()[i] < dk()[i + 1]) && (dk()[i] < dk()[i - 1])
						&& (dk()[i] < dk()[i + 2]) && (dk()[i] < dk()[i - 2])) {
					ilist.add(dk()[i]);
					iIndex.add(i);
				}
			}
			int returnValues = 0;
			if (ilist.isEmpty())
				return returnValues;
			int minValues = ilist.get(0);
			int index = iIndex.get(0);
			for (int i = 1; i < ilist.size(); i++) {
				int value = ilist.get(i);
				int pos = iIndex.get(i);
				if ((minValues > value) && (pos < (frameLength / 2))) {
					minValues = value;
					index = pos;
				}
			}
			returnValues = index;
			return returnValues;
		}

		public int getMaxDk() {
			int max = dk()[0];
			for (int i = 1; i < frameLength; i++) {
				if (dk()[i] > max) {
					max = dk()[i];
				}
			}
			return max;
		}

		public int getAbs(int a, int b) {
			return Math.abs(a - b);
		}

		/**
		 * Ve cac truc toa do
		 * 
		 * @param g
		 */
		public void drawChart(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			int lineHeight = height - 20;
			g2d.setColor(Color.WHITE);
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
			g2d.drawString("k", width - 18, lineHeight - 7);
			g2d.drawString("Dk", 10, 15);

		}
	}

	class FrameWindow extends JPanel {
		int width, height;
		double xStep;

		public FrameWindow() {
			setBackground(backGround);
		}

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			height = getHeight();
			width = getWidth();
			drawChart(g);
			drawFrame(g, width, height);
		}

		protected void drawFrame(Graphics g, int width, int height) {
			Graphics2D g2d = (Graphics2D) g;
			if (samples == null)
				return;
			int lineHeight = height / 2;
			xStep = (width - 60.0) / frameLength;
			double yStep = (lineHeight - 20.0) / getMaximum(maxOff, minOff);
			g2d.setColor(drawColor);
			int[] frames = getSamplesInFrame();
			for (int i = 0; i < frameLength - 1; i++) {
				g2d.draw(new Line2D.Double(pointX + i * xStep, lineHeight
						- frames[i] * yStep, pointX + (i + 1) * xStep,
						lineHeight - frames[i + 1] * yStep));
			}
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
			g2d.drawString("ms", width - 25, lineHeight + 20);
			g2d.drawString("A", 10, 15);

		}
	}

	public class F0Window extends JPanel {
		int width, height;
		List<Integer> minValues;
		List<Double> listF0;
		double xStep;
		int max = 250;
		public F0Window() {
			minValues = new ArrayList<>();
			listF0 = new ArrayList<>();
			setBackground(backGround);
		}

		int countList = 0;

		@Override
		protected void paintComponent(Graphics g) {
			super.paintComponent(g);
			height = getHeight();
			width = getWidth();
			drawChart(g);
			drawF0(g, width, height);
			if (!listF0.isEmpty()) {
				countList++;
				if (countList == listF0.size())
					AmdfLog.getInstance().addLog(
							"[" + countList + "]\t"
									+ listF0.get(listF0.size() - 1));
			}
		}

		// ve F0
		protected void drawF0(Graphics g, int width, int height) {
			Graphics2D g2d = (Graphics2D) g;
			if (samples == null)
				return;
			int lineHeight = height - 20;
			xStep = (width - 60.0) / samples.length;
			double yStep = (lineHeight - 20.0) / max;
			double totalTime = 1000.0 * ((double) samples.length / sampleRate);
			int count = 40;
			for (int i = 1; i < count + 1; i++) {
				g2d.setColor(Color.DARK_GRAY);
				g2d.drawLine(pointX + i * (width - 60) / count, 10, pointX + i
						* (width - 60) / count, height - 20);
				g2d.setColor(Color.GRAY);
				if (i % 4 == 0)
					g2d.drawString("" + i * totalTime / count, pointX - 10 + i
							* (width - 60) / count, lineHeight + 16);

			}
			int countY = (int) ((max * yStep) / ((width - 60) / count)) + 1;
			g2d.setColor(Color.DARK_GRAY);
			for (int i = -countY; i < countY + 1; i++)
				if (i != 0) {
					g2d.draw(new Line2D.Double(20, lineHeight - i
							* (max * yStep / countY), width, lineHeight - i
							* (max * yStep / countY)));
					if (i != countY)
						g2d.drawString("" + i * (max / countY), 5,
								(int) (lineHeight - i * (max / countY) * yStep));
				}

			g2d.setColor(Color.WHITE);
			g2d.drawString("" + max, 5, (int) (lineHeight - max * yStep));
			double w = xStep * frameLength; // do rong cua frame
			double step = w / 3; // frame dich 1/3 do rong
			if (startFrame == 0) {
				minValues.clear();
				listF0.clear();
			}
			int position = dkWindow.getDkMinPosition();
			minValues.add(position);
			g2d.setColor(drawColor);
			listF0.clear();
			for (int i = 0; i < minValues.size(); i++) {
				double f0 = getF0(minValues.get(i));
				if ((minValues.get(i) < (frameLength / 2)) && (f0 < max)) {
					listF0.add(f0);
					g2d.drawString("*", (float) (pointX + (w / 2) + i * step),
							(float) (lineHeight + 7 - f0 * yStep)); // +7
					// de
					// lay
					// chinh
					// giu
					// dau
					// *
					// if
					// (listF0.size()
					// >
					// 1)
					// {
					// double preF0 = (1.0 / minValues.get(i - 1))
					// * sampleRate;
					// g2d.draw(new Line2D.Double(
					// (float) (pointX + w / 2 + (i - 1) * step),
					// (float) (lineHeight - preF0 * yStep),
					// (float) (pointX + w / 2 + i * step),
					// (float) (lineHeight - f0 * yStep)));
					// }
				}
			}
		}

		// tinh f0
		public double getF0(int position) {
			return (1.0 / position) * sampleRate;
		}

		public void drawChart(Graphics g) {
			Graphics2D g2d = (Graphics2D) g;
			int lineHeight = height - 20;
			g2d.setColor(Color.WHITE);
			g2d.drawLine(pointX, 5, pointX, height - 15); // tung
			g2d.drawLine(pointX, 5, pointX + 5, 10); // mui ten truc tung
			g2d.drawLine(pointX, 5, pointX - 5, 10);
			g2d.drawLine(10, lineHeight, width - 10, lineHeight); // hoanh
			g2d.drawLine(width - 10, lineHeight, width - 15, lineHeight + 5);
			g2d.drawLine(width - 10, lineHeight, width - 15, lineHeight - 5);
			g2d.drawString("o", pointX - 4, lineHeight + 5);
			g2d.setColor(Color.WHITE);
			g2d.drawString("0", 30, lineHeight + 15);
			g2d.drawString("|", width - 60 + pointX, lineHeight + 4);
			g2d.drawString("ms", width - 25, lineHeight - 10);
			g2d.drawString("F0", 50, 15);
		}
	}
}
