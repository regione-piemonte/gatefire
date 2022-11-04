/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.dbhelper.service;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.context.support.DelegatingMessageSource;

import it.csi.gatefire.common.util.LogUtils;
import it.csi.gatefire.dbhelper.messages.DatabaseDrivenMessageSource;

public class BaseService extends ApplicationObjectSupport {

	@Autowired
	private MessageSource messageSource;

	public void setMessageSource(MessageSource messageSource) {
		this.messageSource = messageSource;
	}

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	protected void logInfo(String msg) {
		LogUtils.logInfo(log, msg);

	}

	protected void logInfo(String metodo, String msg) {
		LogUtils.logInfo(log, metodo + " - " + msg);
	}

	protected void logDebug(String msg) {
		LogUtils.logDebug(log, msg);

	}

	protected void logDebug(String metodo, String msg) {
		LogUtils.logDebug(log, metodo + " - " + msg);
	}

	protected void logError(String msg) {
		LogUtils.logError(log, msg);
	}

	protected void logError(Exception e) {
		LogUtils.logError(log, e);
	}

	protected void logError(String metodo, String msg) {
		LogUtils.logError(log, metodo + " - " + msg);
	}

	protected void logWarn(String msg) {
		LogUtils.logWarn(log, msg);
	}

	public String getText(String msgKey, String arg) {
		return getText(msgKey, new Object[] { arg });
	}

	public String getText(String msgKey, Object[] args) {
		String ret = msgKey;

		try {
			ret = messageSource.getMessage(msgKey, args, Locale.ITALY);

		} catch (NoSuchMessageException | IllegalStateException e) {/**/
		}
		return ret;

	}

	public String getMultiText(String msgKey, String... args) {
		StringBuilder lines = new StringBuilder("");
		for (int i = 0; i < args.length; i++) {
			try {
				String arg = args[i];
				if (lines.length() > 0) {
					lines.append("\r\n");
				}
				lines.append(getText(msgKey + "." + (i + 1), arg));
			} catch (NoSuchMessageException | IllegalStateException e) {/**/
			}
		}
		return lines.toString();
	}

	public String getText(String msgKey) {
		String ret = msgKey;

		try {
			ret = messageSource.getMessage(msgKey, null, Locale.ITALY);
		} catch (NoSuchMessageException | IllegalStateException e) {/**/
		}
		return ret;
	}

	/**
	 * Convenient method for getting a i18n key's value with a single string
	 * argument.
	 * 
	 * @param msgKey
	 * @param arg
	 * @param locale the current locale
	 * @return
	 */
	public String getText(String msgKey, String arg, Locale locale) {
		return getText(msgKey, new Object[] { arg }, locale);
	}

	/**
	 * Convenience method for getting a i18n key's value with arguments.
	 * 
	 * @param msgKey
	 * @param args
	 * @param locale the current locale
	 * @return
	 */
	public String getText(String msgKey, Object[] args, Locale locale) {
		String ret = msgKey;
		try {
			ret = messageSource.getMessage(msgKey, args, locale);
		} catch (NoSuchMessageException | IllegalStateException e) {/**/
		}
		return ret;
	}

	protected void reloadMessages() {

		// Reload Messages
		if (messageSource instanceof DatabaseDrivenMessageSource) {
			((DatabaseDrivenMessageSource) messageSource).reload();
		} else if (messageSource instanceof DelegatingMessageSource) {
			DelegatingMessageSource myMessage = ((DelegatingMessageSource) messageSource);
			if (myMessage.getParentMessageSource() instanceof DatabaseDrivenMessageSource) {
				((DatabaseDrivenMessageSource) myMessage.getParentMessageSource()).reload();
			}
		}

	}
}
