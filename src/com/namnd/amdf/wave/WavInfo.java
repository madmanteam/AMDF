/*
 * <p>Copyright: Copyright (c) 2012</p>
 * @version 1.0
 */
package com.namnd.amdf.wave;

/**
 * @author namnd
 * @email: namnd.bka@gmail.com
 * @Date: Thursday, October 11, 2012
 */
public class WavInfo {

	/**
	 * wav file name
	 */
	String fileName;
	/**
	 * @Size: 4 bytes
	 * @Function: contain "RIFF" as ASCII code
	 */
	String chunkID;
	/**
	 * @Size: 4 bytes
	 * @Function: tong kich thuoc cac truong phia sau
	 *            (ChunkSize=4+(8+Subchunk1Size)+(8+Subchunk2Size))
	 */
	long chunkSize;
	/**
	 * @Size: 4 bytes
	 * @Function: chua chuoi "WAVE"
	 */
	String format;
	/**
	 * @Size: 4 bytes
	 * @Function: chua chuoi "fmt"
	 */
	String subChunk1ID;
	/**
	 * @Size: 4 bytes
	 * @Function: cho biet tong kich thuoc cac truong thuoc tinh dung phia sau
	 *            no voi wave file ko nen: SubChunk1Size = 16
	 */
	int subChunk1Size;
	/**
	 * @Size: 2 bytes
	 * @Function: dang nen cua du lieu trong file wav 0: ko xac dinh 1: khong
	 *            nen (PCM - Pulse Code Modulation) 
	 *            80: MPEG 
	 *            49: GSM 6.10 
	 *            17: IMA ADPCM
	 */
	int audioFormat;
	/**
	 * @Size: 2 bytes
	 * @Function: so kenh cua file wave 1: Mono 2: Stereo
	 */
	int numChanel;
	/**
	 * @Size: 4 bytes
	 * @Function: so_mau/1second == tan so lay mau
	 */
	long sampleRate;
	/**
	 * @Size: 4 bytes
	 * @Function: cho biet so_byte/1second ung voi tan so lay mau tren
	 *            (ByteRate=SampleRate*NumChannels*(BitsperSample/8))
	 */
	long byteRate;
	/**
	 * @Size: 2 bytes
	 * @Function: so_byte cua 1 mau bao gom cac kenh
	 */
	int blockAlign;
	/**
	 * @Size: 2 bytes
	 * @Function: so_byte cua 1 mau chi tinh 1 kenh 8 bits = 8 16 bits = 16
	 */
	int bitPerSample;
	/**
	 * @Size: 4 bytes
	 * @Function: chua chuoi "data"
	 */
	String subChunk2ID;
	/**
	 * @size: 4 bytes
	 * @Function: cho biet kich thuoc cua du lieu am thanh tho trong truong data
	 *            (Subchunk2Size=NumSamples*NumChannels*BitsperSample/8)
	 */
	int subChunk2Size;

	public WavInfo(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public String getChunkID() {
		return chunkID;
	}

	public void setChunkID(String chunkID) {
		this.chunkID = chunkID;
	}

	public long getChunkSize() {
		return chunkSize;
	}

	public void setChunkSize(long chunkSize) {
		this.chunkSize = chunkSize;
	}

	public String getFormat() {
		return format;
	}

	public void setFormat(String format) {
		this.format = format;
	}

	public String getSubChunk1ID() {
		return subChunk1ID;
	}

	public void setSubChunk1ID(String subChunk1ID) {
		this.subChunk1ID = subChunk1ID;
	}

	public int getSubChunk1Size() {
		return subChunk1Size;
	}

	public void setSubChunk1Size(int subChunk1Size) {
		this.subChunk1Size = subChunk1Size;
	}

	public String getSubChunk2ID() {
		return subChunk2ID;
	}

	public void setSubChunk2ID(String subChunk2ID) {
		this.subChunk2ID = subChunk2ID;
	}

	public int getSubChunk2Size() {
		return subChunk2Size;
	}

	public void setSubChunk2Size(int subChunk2Size) {
		this.subChunk2Size = subChunk2Size;
	}

	public int getAudioFormat() {
		return audioFormat;
	}

	public void setAudioFormat(int audioFormat) {
		this.audioFormat = audioFormat;
	}

	public int getNumChanel() {
		return numChanel;
	}

	public void setNumChanel(int numChanel) {
		this.numChanel = numChanel;
	}

	public long getSampleRate() {
		return sampleRate;
	}

	public void setSampleRate(long sampleRate) {
		this.sampleRate = sampleRate;
	}

	public long getByteRate() {
		return byteRate;
	}

	public void setByteRate(long byteRate) {
		this.byteRate = byteRate;
	}

	public int getBlockAlign() {
		return blockAlign;
	}

	public void setBlockAlign(int blockAlign) {
		this.blockAlign = blockAlign;
	}

	public int getBitPerSample() {
		return bitPerSample;
	}

	public void setBitPerSample(int bitPerSample) {
		this.bitPerSample = bitPerSample;
	}

	@Override
	public String toString() {
		return "WavInfo [chunkID=" + chunkID + ", chunkSize=" + chunkSize
				+ ", format=" + format + ", subChunk1ID=" + subChunk1ID
				+ ", subChunk1Size=" + subChunk1Size + ", subChunk2ID="
				+ subChunk2ID + ", subChunk2Size=" + subChunk2Size
				+ ", audioFormat=" + audioFormat + ", numChanel=" + numChanel
				+ ", sampleRate=" + sampleRate + ", byteRate=" + byteRate
				+ ", blockAlign=" + blockAlign + ", bitPerSample="
				+ bitPerSample + "]";
	}

}
