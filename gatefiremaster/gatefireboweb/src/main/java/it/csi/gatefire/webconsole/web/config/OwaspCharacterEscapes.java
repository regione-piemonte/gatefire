package it.csi.gatefire.webconsole.web.config;

import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;

public class OwaspCharacterEscapes extends CharacterEscapes {
	private final int[] escapes;

	public OwaspCharacterEscapes() {
		escapes = standardAsciiEscapesForJSON();
		for (int i = 0; i < escapes.length; i++) {
			if (!(Character.isAlphabetic(i) || Character.isDigit(i))) {
				escapes[i] = CharacterEscapes.ESCAPE_CUSTOM;
			}
		}
	}

	@Override
	public SerializableString getEscapeSequence(int ch) {
		String unicode = String.format("\\u%04x", ch);
		return new SerializedString(unicode);
	}

	@Override
	public int[] getEscapeCodesForAscii() {
		return escapes;
	}

	private static final long serialVersionUID = 8140493311454723880L;
}