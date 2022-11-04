/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/

package it.csi.gatefire.common.model;

public enum OtpCredentialsType {

    SMS("SMS"),
    ARUBACALL("ARUBACALL");
    private final String value;

    OtpCredentialsType(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static OtpCredentialsType fromValue(String v) {
        for (OtpCredentialsType c: OtpCredentialsType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
