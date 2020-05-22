/**
 * <p>Copyright: Copyright (c) 2012</p>
 * @version 1.0
 */
package com.namnd.amdf.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.namnd.amdf.utils.NUtils;
import com.namnd.amdf.wave.WavInfo;

/**
 * @author namnd
 * @mobile 0986001325
 * @email: dinhnam.yt@gmail.com
 * @Date: Thursday, October 11, 2012
 */
@SuppressWarnings("serial")
public class PreviewInfo extends JFrame {

	private static PreviewInfo previewInfo;

	/**
	 * Singleton Display information of wav file
	 * 
	 * @param wavInfo {@link WavInfo}
	 * @return an instance of {@link PreviewInfo}
	 */
	public static PreviewInfo getInstance(WavInfo wavInfo) {
		if (previewInfo == null) {
			previewInfo = new PreviewInfo(wavInfo);
		}
		return previewInfo;
	}

	public static void setNull() {
		previewInfo = null;
	}

	private PreviewInfo(WavInfo wavInfo) {
		this.setTitle(NUtils.APP_NAME + " - " + wavInfo.getFileName());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(500, 280);
		this.setResizable(false);
		int w = this.getSize().width;
		int h = this.getSize().height;
		int x = (dim.width - w) / 2;
		int y = (dim.height - h) / 2;
		// Move the window
		this.setLocation(x, y);
		setIconImage(NUtils.iconApp.getImage());
		this.setAlwaysOnTop(true);
		String[][] data = new String[][] {
				{ "ChunkID", "4", wavInfo.getChunkID() },
				{ "ChunkSize", "4", "" + wavInfo.getChunkSize() },
				{ "Format", "4", wavInfo.getFormat() },
				{ "SubChunk1ID", "4", wavInfo.getSubChunk1ID() },
				{ "SubChunk1Size", "4", wavInfo.getSubChunk1Size() + "" },
				{ "AudioFormat", "2", wavInfo.getAudioFormat() + "" },
				{ "NumChannels", "2", "" + wavInfo.getNumChanel() },
				{ "SampleRate", "4", wavInfo.getSampleRate() + "" },
				{ "ByteRate", "4", wavInfo.getByteRate() + "" },
				{ "BlockAlign", "2", wavInfo.getBlockAlign() + "" },
				{ "BitPerSample", "2", wavInfo.getBitPerSample() + "" },
				{ "SubChunk2ID", "4", wavInfo.getSubChunk2ID() },
				{ "SubChunk2Size", "4", wavInfo.getSubChunk2Size() + "" } };
		String[] titles = new String[]{"Name", "Size(bytes)", "Value"};
		JTable table = new JTable(data, titles);
		JScrollPane scrollPane = new JScrollPane(table);
		this.getContentPane().add(scrollPane, BorderLayout.CENTER);
		table.setFont(new Font("Arial", Font.PLAIN, 16));
		table.setEnabled(false);
	}
}
