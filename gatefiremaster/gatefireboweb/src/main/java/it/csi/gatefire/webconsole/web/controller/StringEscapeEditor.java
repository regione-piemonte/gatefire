/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.webconsole.web.controller;

import java.beans.PropertyEditorSupport;

import org.springframework.web.util.HtmlUtils;
import org.springframework.web.util.JavaScriptUtils;

public class StringEscapeEditor extends PropertyEditorSupport {
	private boolean escapeHTML;
	private boolean escapeJavaScript;

	public StringEscapeEditor() {
		super();
	}

	public StringEscapeEditor(boolean escapeHTML, boolean escapeJavaScript) {
		super();
		this.escapeHTML = escapeHTML;
		this.escapeJavaScript = escapeJavaScript;

	}

	@Override
	public void setAsText(String text) {
		if (text == null) {
			setValue(null);
		} else {
			String value = text;
			if (escapeHTML) {
				value = HtmlUtils.htmlEscape(value);
			}
			if (escapeJavaScript) {
				value = JavaScriptUtils.javaScriptEscape(value);
			}

			setValue(value);
		}
	}

	@Override
	public String getAsText() {
		Object value = getValue();
		return (value != null ? value.toString() : "");
	}
}
