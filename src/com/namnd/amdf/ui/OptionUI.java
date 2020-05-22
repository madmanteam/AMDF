package com.namnd.amdf.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.namnd.amdf.utils.NUtils;

@SuppressWarnings("serial")
public class OptionUI extends JFrame {

	private static OptionUI optionUI;

	JTextField jtxtFrame;

	public int getK() {
		int out = 0;
		try {
			out = Integer.parseInt(jtxtFrame.getText());
		} catch (NumberFormatException e) {
			out = 20;
		}
		return out;
	}

	private OptionUI() {
		JPanel jp = new JPanel(new GridLayout(0, 2));
		jp.add(new JLabel(" K(ms):"));
		jtxtFrame = new JTextField(30);
		jp.add(jtxtFrame);
		jtxtFrame.setText("20");
		this.add(jp);
		setLocation();
		this.setAlwaysOnTop(true);
	}

	public void setText(String input) {
		String s = jtxtFrame.getText() + "\n";
		jtxtFrame.setText(s + input);
	}

	private void setLocation() {
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setResizable(false);
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		setLocation(x, y);
		setSize(100, 60);
		setIconImage(NUtils.iconApp.getImage());
	}

	public static OptionUI getInstance() {
		if (optionUI == null)
			optionUI = new OptionUI();
		return optionUI;
	}
}
