/*
 * <p>Copyright: Copyright (c) 2012</p>
 * @version 1.0
 */
package com.namnd.amdf.widget;

import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.AbstractAction;
import javax.swing.JFileChooser;

import com.namnd.amdf.ui.MainForm;

/**
 * @author namnd
 * @email: namnd.bka@gmail.com
 * @Date: Thursday, October 11, 2012
 */
@SuppressWarnings("serial")
public class OpenFileDlg extends AbstractAction {

	MainForm context;
	JFileChooser chooser;

	public OpenFileDlg(MainForm context, JFileChooser chooser) {
		super("Open...");
		this.context = context;
		this.chooser = chooser;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		chooser.setFileFilter(new JFileFilter("wav"));
		int resuft = chooser.showOpenDialog(context);
		File file = chooser.getSelectedFile();
		if (resuft == JFileChooser.APPROVE_OPTION) {
			context.show(file);
		}
	}
}
