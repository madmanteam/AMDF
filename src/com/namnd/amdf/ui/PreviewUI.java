/*
 * <p>Copyright: Copyright (c) 2012</p>
 * @version 1.0
 */
package com.namnd.amdf.ui;

import java.awt.BorderLayout;
import java.io.File;

import javax.swing.JFrame;

import com.namnd.amdf.utils.NUtils;

/**
 * @author namnd
 * @email: namnd.bka@gmail.com
 * @Date: Thursday, October 11, 2012
 */
@SuppressWarnings("serial")
public class PreviewUI extends JFrame {

	private static PreviewUI previewUI;

	/**
	 * Singleton
	 * 
	 * @param file {@link File}
	 * @return an instance of {@link PreviewUI}
	 */
	public static PreviewUI getInstance(File file) {
		if (previewUI == null)
			previewUI = new PreviewUI(file);
		return previewUI;
	}

	public static void setNull() {
		previewUI = null;
	}

	private PreviewUI(File file) {
		this.setTitle(NUtils.APP_NAME + " - " + file.getName());
		WavePanel wavePanel = new WavePanel(file);
		wavePanel.setAudioDisplay();
		this.add(wavePanel.createChanelDisplay(wavePanel.getWavePanel().get(0),
				0), BorderLayout.CENTER);
		this.setSize(1200, 700);
		this.setLocation(80, 30);
		setIconImage(NUtils.iconApp.getImage());
	}
}
