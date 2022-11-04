/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.json;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class PasswordSerializer extends JsonSerializer<String> {

	@Override
	public void serialize(String value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException, JsonProcessingException {
		String ret = null;
		if (value != null) {
			ret = "*****";
		}
		jgen.writeString(ret);

	}

}
