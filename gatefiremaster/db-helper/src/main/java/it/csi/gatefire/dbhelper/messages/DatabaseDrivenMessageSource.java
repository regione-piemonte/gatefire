/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.messages;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;

import it.csi.gatefire.dbhelper.model.GatefireDMessaggio;
import it.csi.gatefire.dbhelper.service.MessageResourceSrvc;

public class DatabaseDrivenMessageSource extends ReloadableResourceBundleMessageSource implements ResourceLoaderAware {
	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private MessageResourceSrvc messageResourceSrvc;

	@SuppressWarnings("unused")
	private ResourceLoader resourceLoader;

	private final Map<String, Map<String, String>> properties = new HashMap<>();

	public DatabaseDrivenMessageSource() {
		reload();
	}

	public DatabaseDrivenMessageSource(MessageResourceSrvc messageResourceSrvc) {
		this.messageResourceSrvc = messageResourceSrvc;
		reload();
	}

	@Override
	protected MessageFormat resolveCode(String code, Locale locale) {
		String msg = getText(code, locale);

		return createMessageFormat(msg, locale);

	}

	@Override
	protected String resolveCodeWithoutArguments(String code, Locale locale) {
		return getText(code, locale);
	}

	private String getText(String code, Locale locale) {
		Map<String, String> localized = properties.get(code);
		String textForCurrentLanguage = null;
		if (localized != null) {
			textForCurrentLanguage = localized.get(locale.getLanguage());
			if (textForCurrentLanguage == null) {
				textForCurrentLanguage = localized.get(Locale.ITALY.getLanguage());
			}
		}
		if (textForCurrentLanguage == null) {
			// Check parent message
			logger.debug("Fallback to properties message");
			try {
				textForCurrentLanguage = getParentMessageSource().getMessage(code, null, locale);
			} catch (Exception e) {
				logger.error("Cannot find message with code: " + code);
			}
		}
		return textForCurrentLanguage != null ? textForCurrentLanguage : code;
	}

	public void reload() {
		properties.clear();
		properties.putAll(loadTexts());
	}

	protected Map<String, Map<String, String>> loadTexts() {
		log.debug("loadTexts");
		Map<String, Map<String, String>> m = new HashMap<>();
		List<GatefireDMessaggio> texts = messageResourceSrvc.loadAllMessages();
		for (GatefireDMessaggio text : texts) {
			Map<String, String> v = new HashMap<>();
			v.put(Locale.ITALY.getLanguage(), text.getDescrizioneSintetica());
			m.put(text.getCodice(), v);
		}
		return m;
	}

	@Override
	public void setResourceLoader(ResourceLoader resourceLoader) {
		this.resourceLoader = (resourceLoader != null ? resourceLoader : new DefaultResourceLoader());
	}

}
