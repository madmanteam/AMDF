/**
 * <p>Copyright: Copyright (c) 2012</p>
 * @version 1.0
 */
package com.namnd.amdf.ui;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.LayoutStyle;

import com.namnd.amdf.utils.NUtils;

/**
 * @author namnd
 * @mobile 0986001325
 * @email: dinhnam.yt@gmail.com
 * @Date: Thursday, October 11, 2012
 */
@SuppressWarnings("serial")
public class AboutUI extends JFrame {

	private JLabel jLabel1, jLabel2, jLabel3, jLabel4, jLabel5, jLabel6,
			jLabel7, jLabel8, jLabel9;
	private JLabel logo;

	private final String teacher = "PGS-TS: Trịnh Văn Loan.";
	private final String nameInfo1 = "Nguyễn Đình Nam - 20073731.";
	private final String nameInfo2 = "Nguyễn Minh Tùng - 2006xxxx.";
	private static AboutUI aboutUI;

	public static AboutUI getInstance() {
		if (aboutUI == null)
			aboutUI = new AboutUI();
		return aboutUI;
	}

	private AboutUI() {
		initComponents();
	}

	public void abc() {

	}

	private void initComponents() {
		this.setTitle(NUtils.APP_NAME);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(380, 320);
		this.setResizable(false);
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		// Move the window
		this.setLocation(x, y);
		setIconImage(NUtils.iconApp.getImage());
		this.setAlwaysOnTop(true);
		logo = new JLabel();
		jLabel1 = new JLabel();
		jLabel2 = new JLabel();
		jLabel3 = new JLabel();
		jLabel4 = new JLabel();
		jLabel5 = new JLabel();
		jLabel6 = new JLabel();
		jLabel7 = new JLabel();
		jLabel8 = new JLabel();
		jLabel9 = new JLabel();

		GroupLayout jPanel4Layout = new GroupLayout(logo);
		logo.setLayout(jPanel4Layout);
		jPanel4Layout.setHorizontalGroup(jPanel4Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGap(0, 114, Short.MAX_VALUE));
		jPanel4Layout.setVerticalGroup(jPanel4Layout.createParallelGroup(
				GroupLayout.Alignment.LEADING).addGap(0, 108, Short.MAX_VALUE));
		logo.setIcon(NUtils.iconApp);
		jLabel1.setFont(new Font("Tahoma", 1, 18)); // NOI18N
		jLabel1.setText(NUtils.APP_NAME);

		jLabel2.setFont(new Font("Tahoma", 2, 12)); // NOI18N
		jLabel2.setText("Version " + NUtils.APP_VERSION);

		jLabel3.setFont(new Font("Tahoma", 2, 12)); // NOI18N
		jLabel3.setText("Coypyright (c) 2012");

		jLabel4.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		jLabel4.setText(nameInfo1);

		jLabel5.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		jLabel5.setText(nameInfo2);

		jLabel6.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		jLabel6.setText("Thầy giáo hướng dẫn:");

		jLabel7.setFont(new Font("Tahoma", 0, 12)); // NOI18N
		jLabel7.setText("Nhóm SV:");

		jLabel8.setFont(new Font("Tahoma", 1, 12)); // NOI18N
		jLabel8.setText("Lớp Kỹ Thuật Máy Tính - K52");

		jLabel9.setFont(new Font("Tahoma", 1, 13)); // NOI18N
		jLabel9.setText(teacher);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		logo,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)
																.addGap(31, 31,
																		31)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addComponent(
																						jLabel1,
																						GroupLayout.PREFERRED_SIZE,
																						174,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel2,
																						GroupLayout.PREFERRED_SIZE,
																						79,
																						GroupLayout.PREFERRED_SIZE)
																				.addComponent(
																						jLabel3,
																						GroupLayout.PREFERRED_SIZE,
																						131,
																						GroupLayout.PREFERRED_SIZE))
																.addGap(0,
																		0,
																		Short.MAX_VALUE))
												.addGroup(
														GroupLayout.Alignment.TRAILING,
														layout.createSequentialGroup()
																.addGap(0,
																		0,
																		Short.MAX_VALUE)
																.addComponent(
																		jLabel5,
																		GroupLayout.PREFERRED_SIZE,
																		230,
																		GroupLayout.PREFERRED_SIZE))
												.addGroup(
														layout.createSequentialGroup()
																.addGap(55, 55,
																		55)
																.addGroup(
																		layout.createParallelGroup(
																				GroupLayout.Alignment.LEADING)
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										jLabel8,
																										GroupLayout.DEFAULT_SIZE,
																										GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addGap(33,
																										33,
																										33))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										jLabel7)
																								.addPreferredGap(
																										LayoutStyle.ComponentPlacement.RELATED,
																										GroupLayout.DEFAULT_SIZE,
																										Short.MAX_VALUE)
																								.addComponent(
																										jLabel4,
																										GroupLayout.PREFERRED_SIZE,
																										230,
																										GroupLayout.PREFERRED_SIZE))
																				.addGroup(
																						layout.createSequentialGroup()
																								.addComponent(
																										jLabel6,
																										GroupLayout.PREFERRED_SIZE,
																										242,
																										GroupLayout.PREFERRED_SIZE)
																								.addGap(0,
																										0,
																										Short.MAX_VALUE)))))
								.addContainerGap())
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING)
								.addGroup(
										GroupLayout.Alignment.TRAILING,
										layout.createSequentialGroup()
												.addContainerGap(137,
														Short.MAX_VALUE)
												.addComponent(
														jLabel9,
														GroupLayout.PREFERRED_SIZE,
														230,
														GroupLayout.PREFERRED_SIZE)
												.addGap(0, 0, Short.MAX_VALUE))));
		layout.setVerticalGroup(layout
				.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(
						layout.createSequentialGroup()
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.LEADING)
												.addGroup(
														layout.createSequentialGroup()
																.addGap(32, 32,
																		32)
																.addComponent(
																		jLabel1,
																		GroupLayout.PREFERRED_SIZE,
																		24,
																		GroupLayout.PREFERRED_SIZE)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.RELATED)
																.addComponent(
																		jLabel2)
																.addPreferredGap(
																		LayoutStyle.ComponentPlacement.UNRELATED)
																.addComponent(
																		jLabel3))
												.addGroup(
														layout.createSequentialGroup()
																.addContainerGap()
																.addComponent(
																		logo,
																		GroupLayout.PREFERRED_SIZE,
																		GroupLayout.DEFAULT_SIZE,
																		GroupLayout.PREFERRED_SIZE)))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jLabel6,
										GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE)
								.addGap(29, 29, 29)
								.addGroup(
										layout.createParallelGroup(
												GroupLayout.Alignment.BASELINE)
												.addComponent(
														jLabel4,
														GroupLayout.PREFERRED_SIZE,
														23,
														GroupLayout.PREFERRED_SIZE)
												.addComponent(jLabel7))
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.UNRELATED)
								.addComponent(jLabel5,
										GroupLayout.PREFERRED_SIZE, 23,
										GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
										LayoutStyle.ComponentPlacement.RELATED,
										GroupLayout.DEFAULT_SIZE,
										Short.MAX_VALUE).addComponent(jLabel8)
								.addContainerGap())
				.addGroup(
						layout.createParallelGroup(
								GroupLayout.Alignment.LEADING).addGroup(
								GroupLayout.Alignment.TRAILING,
								layout.createSequentialGroup()
										.addContainerGap(156, Short.MAX_VALUE)
										.addComponent(jLabel9,
												GroupLayout.PREFERRED_SIZE, 23,
												GroupLayout.PREFERRED_SIZE)
										.addGap(120, 120, 120))));
	}
}
