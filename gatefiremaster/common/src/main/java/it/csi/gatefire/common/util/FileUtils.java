/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.util;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.awt.image.WritableRaster;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.activation.DataHandler;
import javax.imageio.ImageIO;

public class FileUtils {

	private FileUtils() {
		super();

	}

	public static byte[] dataHandlerToBytes(DataHandler handler) throws IOException {
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		handler.writeTo(output);
		return output.toByteArray();
	}

	public static byte[] extractBytesFromImg(String imageName) throws IOException {
		// open image
		File imgPath = new File(imageName);
		BufferedImage bufferedImage = ImageIO.read(imgPath);

		// get DataBufferBytes from Raster
		WritableRaster raster = bufferedImage.getRaster();
		DataBufferByte data = (DataBufferByte) raster.getDataBuffer();

		return (data.getData());
	}

	public static String getEstensioneFile(String filename) {
		String extension = "";

		try {
			int dotPos = filename.lastIndexOf(".");
			extension = filename.substring(dotPos + 1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return extension;
	}

	public static String getNomeNoEstensione(String fileName) {

		// Handle null case specially.

		if (fileName == null)
			return null;

		// Get position of last '.'.

		int pos = fileName.lastIndexOf(".");

		// If there wasn't any '.' just return the string as is.

		if (pos == -1)
			return fileName;

		// Otherwise return the string, up to the dot.

		return fileName.substring(0, pos);

	}

	public static DataHandler dataHandlerFormBytes(byte[] bytes) {
		return new DataHandler(bytes, "application/octet-stream");
	}

	public static String formatFileSize(Long bytes) {

		if (bytes >= 1000000000) {
			BigDecimal ret = new BigDecimal(bytes / 1000000000).setScale(2, RoundingMode.HALF_UP);

			return ret + " GB";
		}
		if (bytes >= 1000000) {
			BigDecimal ret = new BigDecimal(bytes / 1000000).setScale(2, RoundingMode.HALF_UP);
			return ret + " MB";
		}
		BigDecimal ret = new BigDecimal(bytes / 1000).setScale(2, RoundingMode.HALF_UP);

		return ret + " KB";
	}

	public static String convertStreamToString(InputStream is) {

		BufferedReader reader = new BufferedReader(new InputStreamReader(is));

		StringBuilder sb = new StringBuilder("");

		String line = null;

		try {

			while ((line = reader.readLine()) != null) {

				sb.append(line + "\n");

			}

		} catch (IOException e) {

			e.printStackTrace();

		} finally {

			try {

				is.close();

			} catch (IOException e) {

				e.printStackTrace();

			}

		}

		return sb.toString();

	}

	

}
