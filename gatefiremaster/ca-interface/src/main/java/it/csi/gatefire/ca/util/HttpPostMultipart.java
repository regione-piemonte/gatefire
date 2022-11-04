/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.ca.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.Map;
import java.util.UUID;

import org.apache.tika.Tika;

import it.csi.gatefire.common.exception.HttpStreamException;
import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.util.FileUtils;

public class HttpPostMultipart {
	private final String boundary;
	private static final String LINE = "\r\n";
	private HttpURLConnection httpConn;
	private String charset;
	private OutputStream outputStream;
	private PrintWriter writer;

	private static String CONTENT_DISP = "Content-Disposition: form-data; name=\"";

	/**
	 * This constructor initializes a new HTTP POST request with content type is set
	 * to multipart/form-data
	 *
	 * @param requestURL
	 * @param charset
	 * @param headers
	 * @throws IOException
	 */
	public HttpPostMultipart(String requestURL, String charset, Map<String, String> headers) throws IOException {
		this.charset = charset;
		boundary = UUID.randomUUID().toString();
		URL url = new URL(requestURL);
		httpConn = (HttpURLConnection) url.openConnection();
		httpConn.setUseCaches(false);
		httpConn.setDoOutput(true); // indicates POST method
		httpConn.setDoInput(true);
		httpConn.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary);
		if (headers != null && headers.size() > 0) {
			Iterator<String> it = headers.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				String value = headers.get(key);
				httpConn.setRequestProperty(key, value);
			}
		}
		outputStream = httpConn.getOutputStream();
		writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);
	}

	/**
	 * Adds a form field to the request
	 *
	 * @param name  field name
	 * @param value field value
	 */
	public void addFormField(String name, String value) {
		writer.append("--" + boundary).append(LINE);
		writer.append(CONTENT_DISP + name + "\"").append(LINE);
		writer.append("Content-Type: text/plain; charset=" + charset).append(LINE);
		writer.append(LINE);
		writer.append(value).append(LINE);
		writer.flush();
	}

	/**
	 * Adds a upload file section to the request
	 *
	 * @param fieldName
	 * @param uploadFile
	 * @throws IOException
	 */
	public void addFilePart(String fieldName, File uploadFile) throws IOException {

		String contentType = new Tika().detect(uploadFile);

		String fileName = uploadFile.getName();

		writer.append("--" + boundary).append(LINE);
		writer.append(CONTENT_DISP + fieldName + "\"; filename=\"" + fileName + "\"").append(LINE);
		writer.append("Content-Type: " + contentType).append(LINE);
		writer.append("Content-Transfer-Encoding: binary").append(LINE);
		writer.append(LINE);
		writer.flush();

		try (FileInputStream inputStream = new FileInputStream(uploadFile)) {
			byte[] buffer = new byte[4096];
			int bytesRead = -1;
			while ((bytesRead = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, bytesRead);
			}
			outputStream.flush();
		}
		writer.append(LINE);
		writer.flush();
	}

	/**
	 * Adds a upload file section to the request
	 *
	 * @param fieldName
	 * @param uploadFile
	 * @throws IOException
	 */
	public void addFilePartAttachment(String fieldName, Attachment attachment) throws IOException {
		String fileName = attachment.getFileName();

		String contentType = new Tika().detect(attachment.getFile());

		writer.append("--" + boundary).append(LINE);
		writer.append(CONTENT_DISP + fieldName + "\"; filename=\"" + fileName + "\"").append(LINE);
		writer.append("Content-Type: " + contentType).append(LINE);
		writer.append("Content-Transfer-Encoding: binary").append(LINE);
		writer.append(LINE);
		writer.flush();
		outputStream.write(attachment.getFile());

		outputStream.flush();

		writer.append(LINE);
		writer.flush();
	}

	/**
	 * 
	 * Completes the request and receives response from the server.
	 *
	 * @return String as response in case the server returned status OK, otherwise
	 *         an exception is thrown.
	 * @throws IOException
	 */
	public String finish() throws IOException {
		String response = "";
		writer.flush();
		writer.append("--" + boundary + "--").append(LINE);
		writer.close();

		// checks server's status code first
		int status = httpConn.getResponseCode();
		if (status == HttpURLConnection.HTTP_OK || status == HttpURLConnection.HTTP_CREATED) {
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = httpConn.getInputStream().read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
			response = result.toString(this.charset);
			httpConn.disconnect();
		} else {

			throw new IOException("Server returned non-OK status: " + status + " - Error: "
					+ FileUtils.convertStreamToString(httpConn.getErrorStream()));

		}
		return response;
	}

	/**
	 * Completes the request and receives response from the server.
	 *
	 * @return String as response in case the server returned status OK, otherwise
	 *         an exception is thrown.
	 * @throws IOException
	 */
	public byte[] finishBytes() throws HttpStreamException, IOException {

		writer.flush();
		writer.append("--" + boundary + "--").append(LINE);
		writer.close();

		// checks server's status code first
		int status = httpConn.getResponseCode();
		if (status == HttpURLConnection.HTTP_OK || status == HttpURLConnection.HTTP_CREATED) {
			ByteArrayOutputStream result = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int length;
			while ((length = httpConn.getInputStream().read(buffer)) != -1) {
				result.write(buffer, 0, length);
			}
			byte[] ret = result.toByteArray();
			httpConn.disconnect();
			return ret;
		} else {
			throw new HttpStreamException(FileUtils.convertStreamToString(httpConn.getErrorStream()), status);
		}

	}
}
