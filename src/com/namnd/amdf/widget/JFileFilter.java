/**
 * <p>Copyright: Copyright (c) 2012</p>
 * @version 1.0
 */
package com.namnd.amdf.widget;

import java.io.File;

import javax.swing.filechooser.FileFilter;
/**
 * @author namnd
 * @mobile 0986001325
 * @email: dinhnam.yt@gmail.com
 * @Date: Thursday, October 11, 2012
 */
class JFileFilter extends FileFilter {
	String filter;

	public JFileFilter(String filter) {
		this.filter = filter;
	}

	public boolean accept(File file) {
		String filename = file.getName();
		if (filename.endsWith("." + filter)
				|| filename.endsWith("." + filter.toUpperCase()))
			return true;
		else
			return false;
	}

	public String getDescription() {
		return "*." + filter;
	}
}