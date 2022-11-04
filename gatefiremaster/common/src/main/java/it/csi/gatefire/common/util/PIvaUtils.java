/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.util;

public class PIvaUtils {
	public static boolean isValid(String partitaIva) {
		if (partitaIva == null) {
			return false;
		}

		if (partitaIva.length() == 0) {
			return true;
		}

		int i, c, s;
		if (partitaIva.length() != 11) {
			return false;
		}

		for (i = 0; i < 11; i++) {
			if (partitaIva.charAt(i) < '0' || partitaIva.charAt(i) > '9') {
				return false;
			}
		}

		s = 0;
		for (i = 0; i <= 9; i += 2) {
			s += partitaIva.charAt(i) - '0';
		}
		for (i = 1; i <= 9; i += 2) {
			c = 2 * (partitaIva.charAt(i) - '0');
			if (c > 9) {
				c = c - 9;
			}
			s += c;
		}
		return (10 - s % 10) % 10 == partitaIva.charAt(10) - '0';
	}
}
