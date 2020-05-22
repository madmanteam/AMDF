/**
 * <p>Copyright: Copyright (c) 2012</p>
 * @version 1.0
 */
package com.namnd.amdf.ui;

import java.awt.AWTException;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.io.File;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

import com.namnd.amdf.utils.NUtils;
import com.namnd.amdf.wave.WavInfo;
import com.namnd.amdf.widget.OpenFileDlg;

/**
 * @author namnd 0986001325 dinhnam.yt@gmail.com Thursday, October 11, 2012
 */
@SuppressWarnings("serial")
public class MainForm extends JFrame implements ActionListener {

	double width = 1200.0, height = 700.0;

	private JMenuBar menuBar;
	private JMenuItem miOpen, miOptions, miExit, miStart, miReset, miPreview,
			miWavInfo, miInfo, miAbout, miLog;
	private JRadioButtonMenuItem miSkinJava, miSkinMotif, miSkinSystem;
	private JMenu mnFile, mnProcess, mnSkin, mnHelp;
	JPanel panelWav;
	File file;
	WavInfo wavInfo;
	WavePanel wavePanel;
	boolean mStart = false;

	/**
	 * constructor
	 */
	public MainForm() {
		super(NUtils.APP_NAME);
		setSize();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		repaint();
		NUtils.setNativeLookAndFeel(NUtils.SKIN_CROSS_PLATFORM);
		addMenu();
		// this.setLayout(new GridLayout(3, 0));
		panelWav = new JPanel(new GridLayout(1, 0));
		this.add(panelWav);
	}

	/**
	 * initial size and location
	 */
	private void setSize() {
		Dimension ds = new Dimension();
		ds.setSize(width, height);
		this.setSize(ds);
		this.setLocation(80, 30);
		setIconImage(NUtils.iconApp.getImage());
	}

	/**
	 * Initial MenuBar
	 */
	private void addMenu() {
		menuBar = new JMenuBar();
		mnFile = new JMenu("File");
		mnFile.setMnemonic(KeyEvent.VK_F);
		miOpen = new JMenuItem("Open...", KeyEvent.VK_O);
		miOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O,
				InputEvent.CTRL_MASK));
		miOptions = new JMenuItem("Options", KeyEvent.VK_P);
		miOptions.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
				InputEvent.CTRL_MASK));
		miExit = new JMenuItem("Exit", KeyEvent.VK_X);
		miExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
				InputEvent.ALT_MASK));
		mnFile.add(miOpen);
		mnFile.add(miOptions);
		miOptions.addActionListener(this);
		mnFile.addSeparator();
		mnFile.add(miExit);
		miOpen.setToolTipText("Mở phân tích file wav...");
		miOpen.setAction(new OpenFileDlg(this, new JFileChooser("file/")));
		menuBar.add(mnFile);

		miExit.setToolTipText("Thoát khỏi chương trình.");
		miExit.addActionListener(this);

		mnProcess = new JMenu("Process");
		mnProcess.setMnemonic(KeyEvent.VK_P);
		miStart = new JMenuItem("Start");
		miStart.setMnemonic(KeyEvent.VK_S);
		miStart.setEnabled(file != null);
		miStart.setIcon(new ImageIcon("resources/images/ic_start.png"));
		miReset = new JMenuItem("Reset");
		miReset.setMnemonic(KeyEvent.VK_R);
		miReset.setEnabled(file != null);
		miReset.addActionListener(this);
		mnProcess.add(miStart);
		mnProcess.add(miReset);
		miStart.addActionListener(this);
		mnProcess.addSeparator();
		miPreview = new JMenuItem("Preview");
		miPreview.setMnemonic(KeyEvent.VK_V);
		miPreview.setEnabled(file != null);
		mnProcess.add(miPreview);
		miPreview.addActionListener(this);

		miWavInfo = new JMenuItem("Wave Info");
		miWavInfo.setEnabled(file != null);
		mnProcess.add(miWavInfo);
		miWavInfo.addActionListener(this);
		menuBar.add(mnProcess);

		mnSkin = new JMenu("Skins");
		mnSkin.setMnemonic(KeyEvent.VK_K);
		miSkinJava = new JRadioButtonMenuItem("Java");
		miSkinSystem = new JRadioButtonMenuItem("System");
		miSkinMotif = new JRadioButtonMenuItem("Motif");
		miSkinJava.setSelected(true);
		ButtonGroup groupSkin = new ButtonGroup();
		groupSkin.add(miSkinJava);
		groupSkin.add(miSkinSystem);
		groupSkin.add(miSkinMotif);
		mnSkin.add(miSkinJava);
		mnSkin.add(miSkinSystem);
		mnSkin.add(miSkinMotif);
		menuBar.add(mnSkin);
		miSkinJava.addActionListener(this);
		miSkinMotif.addActionListener(this);
		miSkinSystem.addActionListener(this);

		mnHelp = new JMenu("Help");
		mnHelp.setMnemonic(KeyEvent.VK_H);
		menuBar.add(mnHelp);
		miLog = new JMenuItem("View Log");
		mnHelp.add(miLog);
		miLog.addActionListener(this);
		miInfo = new JMenuItem("Đề bài");
		miAbout = new JMenuItem("About us");
		mnHelp.add(miInfo);
		miInfo.addActionListener(this);
		mnHelp.add(miAbout);
		miAbout.addActionListener(this);
		this.setJMenuBar(menuBar);
	}

	/**
	 * show wave graphic
	 * 
	 * @param file
	 *            return void
	 */
	public void show(File file) {
		this.file = file;
		AmdfLog.getInstance().addLog(file.toString());
		this.setTitle(NUtils.APP_NAME + " - " + file.getName());
		wavePanel = new WavePanel(file);
		wavePanel.setAudioDisplay();
		panelWav.removeAll();
		panelWav.add(wavePanel, BorderLayout.CENTER);
		miPreview.setEnabled(true);
		miWavInfo.setEnabled(true);
		miStart.setEnabled(true);
		miReset.setEnabled(true);
		mStart = false;
		miStart.setText("Start");
		this.wavInfo = wavePanel.getWavInfo();
		PreviewInfo.setNull();
		PreviewUI.setNull();
		SwingUtilities.updateComponentTreeUI(panelWav);

	}

	/**
	 * Main Function
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		if (SystemTray.isSupported()) {
			final TrayIcon icon = new TrayIcon(Toolkit.getDefaultToolkit()
					.getImage(NUtils.imagePath("ic_app")), NUtils.APP_NAME);
			SystemTray tray = SystemTray.getSystemTray();
			icon.setImageAutoSize(true);
			icon.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					icon.displayMessage("NamND", NUtils.APP_NAME,
							TrayIcon.MessageType.INFO);
				}
			});
			try {
				tray.add(icon);
			} catch (AWTException e) {
				System.err.println("TrayIcon could not be added.");
			}
		}
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainForm m = new MainForm();
				m.validate();
				m.setVisible(true);
			}
		});
	}

	/**
	 * Action Listener
	 */
	@Override
	public void actionPerformed(ActionEvent event) {
		Object object = event.getSource();
		if (object.equals(miOptions)) {
			OptionUI.getInstance().setVisible(true);
		} else if (object.equals(miStart)) {
			if (!mStart) {
				wavePanel.getWavePanel().get(0).setStop(false);
				(new Thread(wavePanel.getWavePanel().get(0))).start();
				mStart = true;
				miStart.setText("Pause");
			} else {
				mStart = false;
				wavePanel.getWavePanel().get(0).setStop(true);
				miStart.setText("Start");
			}
		} else if (object.equals(miReset)) {
			wavePanel.getWavePanel().get(0).reset();
			mStart = false;
			miStart.setText("Start");
		} else if (object.equals(miSkinJava)) {
			NUtils.setNativeLookAndFeel(NUtils.SKIN_CROSS_PLATFORM);
			SwingUtilities.updateComponentTreeUI(this);
		} else if (object.equals(miSkinSystem)) {
			NUtils.setNativeLookAndFeel(NUtils.SKIN_SYSTEM);
			SwingUtilities.updateComponentTreeUI(this);
		} else if (object.equals(miSkinMotif)) {
			NUtils.setNativeLookAndFeel(NUtils.SKIN_MOTIF);
			SwingUtilities.updateComponentTreeUI(this);
		} else if (object.equals(miPreview)) {
			PreviewUI.getInstance(file).setVisible(true);
		} else if (object.equals(miExit)) {
			System.exit(1);
		} else if (object.equals(miLog)) {
			AmdfLog.getInstance().setVisible(true);
		} else if (object.equals(miWavInfo)) {
			PreviewInfo.getInstance(wavInfo).setVisible(true);
		} else if (object.equals(miAbout)) {
			AboutUI.getInstance().setVisible(true);
		}
	}
}
