/**
 * <p>Copyright: Copyright (c) 2012</p>
 * @version 1.0
 */
package com.namnd.amdf.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.namnd.amdf.ui.AmdfPanel.F0Window;
import com.namnd.amdf.wave.WavFileProc;
import com.namnd.amdf.wave.WavInfo;

/**
 * @author namnd
 * @mobile 0986001325
 * @email: dinhnam.yt@gmail.com
 * @Date: Thursday, October 11, 2012
 */
@SuppressWarnings("serial")
public class WavePanel extends JPanel {

	/**
	 * 
	 */
	public static final String TAG = "WavPanel";

	private List<SingleWavePanel> singleChanalWaveForm;
	private List<AmdfPanel> amdfPanelList;
	private WavFileProc wavFileProc = null;
	File file;

	public WavePanel(File file) {
		this.file = file;
		setLayout(new GridLayout(3, 1));
		singleChanalWaveForm = new ArrayList<SingleWavePanel>();
		amdfPanelList = new ArrayList<AmdfPanel>();
	}

	public void setAudioDisplay() {
		if (singleChanalWaveForm == null) {
			singleChanalWaveForm = new ArrayList<SingleWavePanel>();
		}
		wavFileProc = new WavFileProc(file);
		for (int i = 0; i < wavFileProc.getWavInfo().getNumChanel(); i++) {
			SingleWavePanel waveFormPanel = new SingleWavePanel(this,
					wavFileProc, i);
			AmdfPanel amdfPanel = new AmdfPanel(waveFormPanel.getSamples(),
					waveFormPanel.getStartFrame(),
					waveFormPanel.getFrameLength(),
					waveFormPanel.getSampleRate());
			singleChanalWaveForm.add(waveFormPanel);
			amdfPanelList.add(amdfPanel);
			add(createChanelDisplay(waveFormPanel, i));
			add(createChanelAmdfDisplay(amdfPanel, "Amdf"));
			add(createChanelF0Display(amdfPanel.getF0Window(), "F0"));
		}
	}

	public void updateGUI() {
		for (int i = 0; i < wavFileProc.getWavInfo().getNumChanel(); i++) {
			SingleWavePanel waveFormPanel = singleChanalWaveForm.get(i);
			AmdfPanel amdfPanel = amdfPanelList.get(i);
			amdfPanel.setStartFrame(waveFormPanel.getStartFrame());
			amdfPanel.setLength(waveFormPanel.getFrameLength());
			amdfPanel.updateGUI();
		}
	}

	public List<SingleWavePanel> getWavePanel() {
		return singleChanalWaveForm;
	}

	public JComponent createChanelDisplay(SingleWavePanel waveForm, int index) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(waveForm, BorderLayout.CENTER);
		JLabel label = new JLabel("Chanel " + ++index);
		panel.add(label, BorderLayout.NORTH);
		return panel;
	}

	public JComponent createChanelAmdfDisplay(AmdfPanel amdfPanel, String title) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(amdfPanel, BorderLayout.CENTER);
		JLabel label = new JLabel(title);
		panel.add(label, BorderLayout.NORTH);
		return panel;
	}

	public JComponent createChanelF0Display(F0Window f0Window, String title) {
		JPanel panel = new JPanel(new BorderLayout());
		panel.add(f0Window, BorderLayout.CENTER);
		JLabel label = new JLabel(title);
		panel.add(label, BorderLayout.NORTH);
		return panel;
	}

	public WavInfo getWavInfo() {
		return wavFileProc.getWavInfo();
	}
}
