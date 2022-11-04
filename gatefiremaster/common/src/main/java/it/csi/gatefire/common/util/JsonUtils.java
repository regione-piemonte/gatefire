package it.csi.gatefire.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class JsonUtils {
	private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

	public static String fillObject(Object obj) {

		return fillObjects(obj);
	}

	private JsonUtils() {
		super();

	}

	public static String fillObjects(Object... objects) {
		StringBuilder ret = new StringBuilder("");
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		boolean first = true;

		for (Object object : objects) {
			if (!first) {
				ret.append(" | ");

			} else {
				first = false;
			}
			try {
				ret.append(mapper.writerWithDefaultPrettyPrinter().writeValueAsString(object));
			} catch (Exception e) {
				LogUtils.logError(log, e);
				ret.append(e.getMessage());
			}

		}
		return ret.toString();
	}
}
