/**
 * <p>Copyright: Copyright (c) 2012</p>
 * @version 1.0
 */
package com.namnd.amdf.utils;

import javax.swing.ImageIcon;
import javax.swing.UIManager;

/**
 * @author namnd
 * @mobile 0986001325
 * @email: dinhnam.yt@gmail.com
 * @Date: Thursday, October 11, 2012
 */
public class NUtils {

	/**
	 * Application name
	 */
	public static final String APP_NAME = "Xử lý tiếng nói";
	public static final String APP_VERSION = "1.0";

	public static final int SKIN_CROSS_PLATFORM = 0;
	public static final int SKIN_MOTIF = 1;
	public static final int SKIN_SYSTEM = 2;
	public static final String imagePath = "resources/images/";
	public static final ImageIcon iconApp = new ImageIcon(imagePath("ic_app"));

	/**
	 * 
	 * @param skin
	 */
	public static void setNativeLookAndFeel(int skin) {
		try {
			switch (skin) {
			case SKIN_CROSS_PLATFORM:
				UIManager.setLookAndFeel(UIManager
						.getCrossPlatformLookAndFeelClassName());
				break;
			case SKIN_MOTIF:
				UIManager
						.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
				break;

			case SKIN_SYSTEM:
				 UIManager.setLookAndFeel(UIManager
				 .getSystemLookAndFeelClassName());
//				UIManager
//						.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
				break;
			default:
				break;
			}

		} catch (Exception e) {
			System.out.println("Error setting native LAF: " + e);
		}
	}

	public static String imagePath(String filename) {
		String path = (imagePath + filename + ".png");
		return path;
	}
}
