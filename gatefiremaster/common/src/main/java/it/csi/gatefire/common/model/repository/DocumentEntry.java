package it.csi.gatefire.common.model.repository;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openehealth.ipf.commons.ihe.xds.core.metadata.Identifiable;

import lombok.EqualsAndHashCode;
import lombok.ToString;;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentEntry", propOrder = { "sourcePatientIdList", "extSourcePatientInfo" })
@XmlRootElement(name = "documentEntry")
@EqualsAndHashCode(callSuper = true, doNotUseGetters = true)
@ToString(callSuper = true, doNotUseGetters = true)
public class DocumentEntry extends org.openehealth.ipf.commons.ihe.xds.core.metadata.DocumentEntry {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private List<Identifiable> sourcePatientIdList;

	public List<Identifiable> getSourcePatientIdList() {
		return sourcePatientIdList;
	}

	public void setSourcePatientIdList(List<Identifiable> sourcePatientIdList) {
		this.sourcePatientIdList = sourcePatientIdList;
	}
	
	@XmlJavaTypeAdapter(PatientInfoExtAdapter.class)
	@XmlElement(name = "extSourcePatientInfo", type = PatientInfoExtXml.class)
	private it.csi.gatefire.common.model.repository.PatientInfo extSourcePatientInfo;

	public it.csi.gatefire.common.model.repository.PatientInfo getExtSourcePatientInfo() {
		return extSourcePatientInfo;
	}

	public void setExtSourcePatientInfo(it.csi.gatefire.common.model.repository.PatientInfo extSourcePatientInfo) {
		this.extSourcePatientInfo = extSourcePatientInfo;
	}

}
