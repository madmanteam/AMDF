package com.namnd.amdf.ui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.LayoutStyle;

import com.namnd.amdf.utils.NUtils;

@SuppressWarnings("serial")
public final class AmdfLog extends JFrame implements ActionListener {

	private JTextArea txtLog;
	public static AmdfLog appLog;

	public static synchronized AmdfLog getInstance() {
		if (appLog == null) {
			appLog = new AmdfLog();
		}
		return appLog;
	}

	private AmdfLog() {
		setTitle(NUtils.APP_NAME);
		initComponents();
		setLocation();
		this.setAlwaysOnTop(true);
	}

	private void initComponents() {

		JScrollPane jScrollPane1 = new JScrollPane();
		txtLog = new JTextArea();
		JButton jButton1 = new JButton();

		jScrollPane1.setViewportView(txtLog);

		jButton1.setText("Copy to clipboard");
		jButton1.addActionListener(this);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addComponent(jScrollPane1, GroupLayout.Alignment.TRAILING)
				.addGroup(
						layout.createSequentialGroup()
								.addContainerGap()
								.addComponent(jButton1,
										GroupLayout.DEFAULT_SIZE, 380,
										Short.MAX_VALUE).addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(
				GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addComponent(jScrollPane1,
										GroupLayout.DEFAULT_SIZE, 270,
										Short.MAX_VALUE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(jButton1).addGap(6, 6, 6)));
		txtLog.setEditable(false);

		pack();
	}// </editor-fold>

	public void addLog(String str) {
		if (!txtLog.getText().isEmpty()) {
			txtLog.append("\n");
		}
		txtLog.append(str);

	}

	private void setLocation() {
		Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit()
				.getScreenSize());
		int wdwLeft = screenSize.width / 2;
		setLocation(wdwLeft, 5);
		setSize(500, 500);
		setIconImage(NUtils.iconApp.getImage());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StringSelection strSelect = new StringSelection(txtLog.getText());
		Toolkit.getDefaultToolkit().getSystemClipboard().setContents(strSelect, strSelect);
	}
}
