package it.csi.gatefire.webconsole.web.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.context.support.DelegatingMessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.support.WebApplicationObjectSupport;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;

import it.csi.gatefire.common.util.LogUtils;
import it.csi.gatefire.common.web.model.Role;
import it.csi.gatefire.common.web.model.User;
import it.csi.gatefire.dbhelper.messages.DatabaseDrivenMessageSource;

@Controller
public abstract class BaseController extends WebApplicationObjectSupport {

	protected final Logger log = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MessageSource messageSource;

	public static final String SUCCESS_MESSAGES_KEY = "successMessages";

	public static final String ERROR_MESSAGES_KEY = "errorMessages";

	public static final String WARNING_MESSAGES_KEY = "warningMessages";

	public static final String INFO_MESSAGES_KEY = "infoMessages";

	public static final String SUCCESS_MESSAGES_INLINE_KEY = "successInlineMessages";

	public static final String ERROR_MESSAGES_INLINE_KEY = "errorInlineMessages";

	public static final String WARNING_MESSAGES_INLINE_KEY = "warningInlineMessages";

	public void saveSuccessMessage(HttpServletRequest request, String msg) {
		saveMessage(request, msg, SUCCESS_MESSAGES_KEY);
	}

	public void saveSuccessMessageInline(HttpServletRequest request, String msg) {
		saveMessage(request, msg, SUCCESS_MESSAGES_INLINE_KEY);
	}

	public void saveErrorMessage(HttpServletRequest request, String msg) {
		saveMessage(request, msg, ERROR_MESSAGES_KEY);
	}

	public void saveErrorMessageInline(HttpServletRequest request, String msg) {
		saveMessage(request, msg, ERROR_MESSAGES_INLINE_KEY);
	}

	public void saveWarningMessage(HttpServletRequest request, String msg) {
		saveMessage(request, msg, WARNING_MESSAGES_KEY);
	}

	public void saveWarningMessageInline(HttpServletRequest request, String msg) {
		saveMessage(request, msg, WARNING_MESSAGES_INLINE_KEY);
	}

	public void saveInfoMessage(HttpServletRequest request, String msg) {
		saveMessage(request, msg, INFO_MESSAGES_KEY);
	}

	@SuppressWarnings("unchecked")
	private void saveMessage(HttpServletRequest request, String msg, String tipo) {
		List<String> messages = (List<String>) request.getSession().getAttribute(tipo);

		if (messages == null) {
			messages = new ArrayList<>();
		}

		messages.add(msg);
		request.getSession().setAttribute(tipo, messages);
	}

	public String getText(String msgKey, String arg) {
		return getText(msgKey, new Object[] { arg });
	}

	public String getText(String msgKey, Object[] args) {
		String ret = msgKey;

		try {
			ret = getMessageSourceAccessor().getMessage(msgKey, args);
		} catch (NoSuchMessageException | IllegalStateException | NullPointerException e) {/**/
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
			ret = getMessageSourceAccessor().getMessage(msgKey);
		} catch (NoSuchMessageException | IllegalStateException | NullPointerException e) {/**/
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
			ret = getMessageSourceAccessor().getMessage(msgKey, args, locale);
		} catch (NoSuchMessageException | IllegalStateException | NullPointerException e) {/**/
		}
		return ret;
	}

	protected void logError(String msg) {

		LogUtils.logError(log, msg);
	}

	protected void logError(Exception e) {
		LogUtils.logError(log, e);
	}

	protected void logInfo(String msg) {
		LogUtils.logInfo(log, msg);

	}

	protected void logInfo(Exception e) {
		LogUtils.logInfo(log, e);
	}

	protected void logDebug(String msg) {
		LogUtils.logDebug(log, msg);

	}

	protected void logDebug(Exception e) {

		LogUtils.logDebug(log, e);
	}

	protected String getUserName() {
		if (getCurrentUser() != null)
			return getCurrentUser().getUsername();
		return null;

	}

	protected User getCurrentUser() {
		User utente = null;
		try {
			utente = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (RuntimeException e) {/**/
		}
		return utente;
	}

	protected List<Role> getUserRoles() {
		User utente = getCurrentUser();
		if (utente != null) {
			return utente.getRoleList();
		}
		return new ArrayList<>();
	}

	@InitBinder
	protected void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(Integer.class, null, new CustomNumberEditor(Integer.class, null, true));
		binder.registerCustomEditor(Long.class, null, new CustomNumberEditor(Long.class, null, true));
		binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, null, new CustomDateEditor(dateFormat, true));
		binder.registerCustomEditor(String.class, new StringEscapeEditor(true, false));

	}

	protected String listaToString(List<String> lista) {
		StringBuilder sb = new StringBuilder("");

		boolean first = true;
		if (lista != null) {
			for (String temp : lista) {
				if (!first) {
					sb.append(", ");
				} else {
					first = false;
				}
				sb.append(temp);
			}
		}

		return sb.toString();

	}

	protected String listaToString(String[] lista) {
		StringBuilder sb = new StringBuilder("");

		boolean first = true;
		if (lista != null) {
			for (String temp : lista) {
				if (!first) {
					sb.append(", ");
				} else {
					first = false;
				}
				sb.append(temp);
			}
		}

		return sb.toString();

	}

	protected void reloadMessages() {

		// Reload Messages
		if (messageSource instanceof DatabaseDrivenMessageSource) {
			((DatabaseDrivenMessageSource) messageSource).reload();
		} else if (messageSource instanceof DelegatingMessageSource) {
			DelegatingMessageSource myMessage = ((DelegatingMessageSource) messageSource);
			if (myMessage.getParentMessageSource() instanceof DatabaseDrivenMessageSource) {
				DatabaseDrivenMessageSource db = (DatabaseDrivenMessageSource) myMessage.getParentMessageSource();
				if (db != null) {
					db.reload();
				}

			}
		}
	}
}