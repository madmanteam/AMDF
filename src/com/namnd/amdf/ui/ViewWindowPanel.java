package com.namnd.amdf.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import com.namnd.amdf.wave.WavFileProc;

@SuppressWarnings("serial")
public class ViewWindowPanel extends JPanel implements Runnable {

	protected WavFileProc wavFileProc;
	private int chanelIndex;
	
	int width, height;
	private Color backGround = Color.BLACK;

	public ViewWindowPanel(WavFileProc wavFileProc, int chanelIndex) {
		this.wavFileProc = wavFileProc;
		this.setChanelIndex(chanelIndex);
		setBackground(backGround);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		height = getHeight();
		width = getWidth();
		drawChart(g);
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
		g2d.drawString("o", pointX - 2, lineHeight + 4);
		g2d.setColor(Color.WHITE);
		g2d.drawString("0", 30, lineHeight + 15);
		g2d.drawString("|", width - 60 + pointX, lineHeight + 4);
		g2d.drawString("ms", width - 15, lineHeight + 20);
		g2d.drawString("A", 10, 10);

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	public int getChanelIndex() {
		return chanelIndex;
	}

	public void setChanelIndex(int chanelIndex) {
		this.chanelIndex = chanelIndex;
	}

}
