package it.csi.gatefire.common.util;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

import org.apache.cxf.endpoint.Client;
import org.apache.cxf.transport.http.HTTPConduit;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;

public class ProxyUtils {
	public static void authenticate(Client client) {
		try {
			String proxyServer = System.getProperty("proxy.server");
			String proxyPort = System.getProperty("proxy.port");
			String user = System.getProperty("proxy.user").trim();
			String password = System.getProperty("proxy.password").trim();

			HTTPConduit http = (HTTPConduit) client.getConduit();
			http.getClient().setProxyServer(proxyServer);
			http.getClient().setProxyServerPort(Integer.valueOf(proxyPort));
			http.getProxyAuthorization().setUserName(user);
			http.getProxyAuthorization().setPassword(password);

			System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");
			System.setProperty("http.proxyHost", proxyServer);
			System.setProperty("http.proxyPort", proxyPort);
			System.setProperty("https.proxyHost", proxyServer);
			System.setProperty("https.proxyPort", proxyPort);

			Authenticator.setDefault(new Authenticator() {
				@Override
				protected PasswordAuthentication getPasswordAuthentication() {

					return new PasswordAuthentication(user, password.toCharArray());
				}
			});
		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	public static void authenticate() {

		String proxyServer = System.getProperty("proxy.server");
		String proxyPort = System.getProperty("proxy.port");
		String user = System.getProperty("proxy.user").trim();
		String password = System.getProperty("proxy.password").trim();

		System.setProperty("http.proxyHost", proxyServer);
		System.setProperty("http.proxyPort", proxyPort);
		System.setProperty("https.proxyHost", proxyServer);
		System.setProperty("https.proxyPort", proxyPort);

		System.setProperty("jdk.http.auth.tunneling.disabledSchemes", "");

		Authenticator.setDefault(new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {

				return new PasswordAuthentication(user, password.toCharArray());
			}
		});
	}

	public static RequestConfig authenticateHttpClient() {

		String proxyServer = System.getProperty("proxy.server");
		String proxyPort = System.getProperty("proxy.port");

		return RequestConfig.custom().setProxy(new HttpHost(proxyServer, Integer.valueOf(proxyPort))).build();

	}

	private ProxyUtils() {
		super();

	}
}
