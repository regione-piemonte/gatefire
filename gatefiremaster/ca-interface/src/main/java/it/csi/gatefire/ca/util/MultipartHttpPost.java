package it.csi.gatefire.ca.util;

import java.io.IOException;
import java.net.HttpURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import it.csi.gatefire.common.exception.HttpStreamException;
import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.util.ProxyUtils;

public class MultipartHttpPost {

	private HttpPost request;

	private MultipartEntityBuilder builder;

	/**
	 * 
	 * @param requestURL
	 * @param charset
	 * @param headers
	 * @param activeProfile
	 * @throws IOException
	 */
	public MultipartHttpPost(String requestURL, String activeProfile) {
		request = new HttpPost(requestURL);
		if ("dev".equalsIgnoreCase(activeProfile)) {

			request.setConfig(ProxyUtils.authenticateHttpClient());
		}

		builder = MultipartEntityBuilder.create();
		builder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

		//

	}

	/**
	 * Adds a form field to the request
	 *
	 * @param name  field name
	 * @param value field value
	 */
	public void addFormField(String name, String value) {
		builder.addTextBody(name, value, ContentType.TEXT_PLAIN);
	}

	/**
	 * 
	 * @param fieldName
	 * @param attachment
	 */
	public void addFilePartAttachment(String fieldName, Attachment attachment) {
		builder.addBinaryBody(fieldName, attachment.getFile(), ContentType.DEFAULT_BINARY, attachment.getFileName());

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

		String str = "";

		HttpEntity reqEntity = builder.build();
		request.setEntity(reqEntity);
		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(request)) {
			int retCode = response.getStatusLine().getStatusCode();

			HttpEntity resp = response.getEntity();

			if (resp != null) {

				str = EntityUtils.toString(resp);
			}
			if (retCode != HttpURLConnection.HTTP_OK && retCode != HttpURLConnection.HTTP_CREATED) {
				throw new IOException("Server returned non-OK status: " + retCode + " - Error: " + str);
			}
		}
		return str;
	}

	/**
	 * 
	 * @return
	 * @throws HttpStreamException
	 * @throws IOException
	 */
	public byte[] finishBytes() throws HttpStreamException, IOException {
		byte[] retfile = null;
		HttpEntity reqEntity = builder.build();
		request.setEntity(reqEntity);

		try (CloseableHttpClient httpClient = HttpClients.createDefault();
				CloseableHttpResponse response = httpClient.execute(request)) {
			int retCode = response.getStatusLine().getStatusCode();

			HttpEntity resp = response.getEntity();
			if (retCode == HttpURLConnection.HTTP_OK || retCode == HttpURLConnection.HTTP_CREATED) {
				if (resp != null) {
					retfile = EntityUtils.toByteArray(resp);

				}
			} else {
				String str = "";

				if (resp != null) {
					// return it as a String
					str = EntityUtils.toString(resp);
				}
				throw new HttpStreamException(str, retCode);
			}
		}
		return retfile;
	}
}
