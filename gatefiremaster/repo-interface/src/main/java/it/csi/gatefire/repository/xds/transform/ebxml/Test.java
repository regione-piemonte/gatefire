/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.repository.xds.transform.ebxml;

import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import it.csi.gatefire.common.consts.security.PasswordUtils;
import it.csi.gatefire.common.model.repository.DocumentEntry;
import it.csi.gatefire.repository.xds.FseXdsJaxbDataBinding;

public class Test {

	public static void main(String[] args) {
String salt = PasswordUtils.generateSalt();
String psw ="ceppa";

String encrypted = PasswordUtils.encrypt(psw, salt);

System.out.println(encrypted);

System.out.println(PasswordUtils.decrypt(encrypted, salt));
		/*Documento doc = new Documento();

		doc.setFirmatoDigitalmente(false);

		try {
			System.out.println(isNull(doc));
			doc = new Documento();
			System.out.println(isNull(doc));
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stubtring

		String[] list = { "PID-3|TRNNLL53H10I462M^^^&amp;2.16.840.1.113883.2.9.4.3.2&amp;ISO",
				"PID-3|6921767^^^&amp;2.16.840.1.113883.2.9.2.10&amp;ISO", "PID-5|TRENTASEI^NELLO",
				"PID-7|195306160101", "PID-8|M", "PID-11|^^SASSUOLO^^^100^N^^036040",
				"PID-11|Via Leopardi Giacomo 10^^COLLEGNO^^^100^L^^001090", "PID-26|100" };

		List<String> slotValues = Arrays.asList(list);

		PatientInfoTransformer patientInfoTransformer = new PatientInfoTransformer();

		DocumentEntry docEntry = new DocumentEntry();

		PatientInfo patientInfo = patientInfoTransformer.fromHL7(slotValues);
		docEntry.setSourcePatientInfo(patientInfo);
		((it.csi.gatefire.common.model.repository.DocumentEntry) docEntry)
				.setExtSourcePatientInfo(patientInfoTransformer.fromHL7ext(slotValues));
*/
		// jaxbObjectToXML(docEntry);
	}

	private static boolean isNull(Object obj) throws IllegalAccessException {

		for (Field f : obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			if (f.get(obj) != null)
				return false;
		}
		return true;
	}

	private static boolean isNull2(Object obj) {
		Field[] field = obj.getClass().getDeclaredFields();

		for (int j = 0; j < field.length; j++) {
			String name = field[j].getName();
			name = name.substring(0, 1).toUpperCase() + name.substring(1);

			try {
				Method m = obj.getClass().getMethod("get" + name);
				Object value = m.invoke(obj);
				if (value != null) {
					return false;
				}
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return true;
	}

	private static void jaxbObjectToXML(DocumentEntry employee) {
		try {
			// Create JAXB Context
			JAXBContext jaxbContext = JAXBContext.newInstance(DocumentEntry.class);

			// Create Marshaller
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

			jaxbMarshaller.setListener(new FseXdsJaxbDataBinding().getMarshallerListener());

			// Required formatting??
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			// Print XML String to Console
			StringWriter sw = new StringWriter();

			// Write XML to StringWriter
			jaxbMarshaller.marshal(employee, sw);

			// Verify XML Content
			String xmlContent = sw.toString();
			System.out.println(xmlContent);

		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}
