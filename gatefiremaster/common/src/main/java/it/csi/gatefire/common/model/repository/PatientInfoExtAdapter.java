/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import javax.xml.bind.annotation.adapters.XmlAdapter;

public class PatientInfoExtAdapter
		extends XmlAdapter<PatientInfoExtXml, it.csi.gatefire.common.model.repository.PatientInfo> {
	@Override
	public PatientInfoExtXml marshal(it.csi.gatefire.common.model.repository.PatientInfo patientInfo) {
		if (patientInfo == null) {
			return null;
		}

		var xml = new PatientInfoExtXml();
		marshalList(patientInfo.getIds(), xml.getIds());
		marshalList(patientInfo.getNames(), xml.getNames());
		xml.setDateOfBirth(patientInfo.getDateOfBirth());
		xml.setGender(patientInfo.getGender());
		xml.setBirthCountry(patientInfo.getBirthCountry());
		marshalList(patientInfo.getAddresses(), xml.getAddresses());
		patientInfo.getCustomFieldIds().forEach(fieldId -> {
			var target = xml.getOther().computeIfAbsent(fieldId, dummy -> new ArrayList<>());
			marshalList(patientInfo.getHl7FieldIterator(fieldId), target);
		});
		return xml;
	}

	@Override
	public it.csi.gatefire.common.model.repository.PatientInfo unmarshal(PatientInfoExtXml xml) {
		if (xml == null) {
			return null;
		}

		var patientInfo = new it.csi.gatefire.common.model.repository.PatientInfo();
		unmarshalList(xml.getIds(), patientInfo.getIds());
		unmarshalList(xml.getNames(), patientInfo.getNames());
		patientInfo.setDateOfBirth(xml.getDateOfBirth());
		patientInfo.setGender(xml.getGender());
		patientInfo.setBirthCountry(xml.getBirthCountry());
		unmarshalList(xml.getAddresses(), patientInfo.getAddresses());
		xml.getOther().forEach(
				(fieldId, repetitions) -> unmarshalList(repetitions, patientInfo.getHl7FieldIterator(fieldId)));
		return patientInfo;
	}

	private static <T> void marshalList(ListIterator<T> source, List<T> target) {
		while (source.hasNext()) {
			target.add(source.next());
		}
	}

	private static <T> void unmarshalList(List<T> source, ListIterator<T> target) {
		for (var i = source.size() - 1; i >= 0; --i) {
			target.add(source.get(i));
		}
	}
}
