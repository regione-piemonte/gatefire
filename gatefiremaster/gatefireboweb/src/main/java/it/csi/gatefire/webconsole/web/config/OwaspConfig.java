package it.csi.gatefire.webconsole.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
	public class OwaspConfig {

		@Bean
		public MappingJackson2HttpMessageConverter owaspJacksonConverter() {
			return new MappingJackson2HttpMessageConverter(objectMapper());
		}

		@Bean
		@Primary
		public ObjectMapper objectMapper() {
			JsonFactory factory = new JsonFactory();
			factory.setCharacterEscapes(new OwaspCharacterEscapes());
			return new ObjectMapper(factory);
		}
	
}
