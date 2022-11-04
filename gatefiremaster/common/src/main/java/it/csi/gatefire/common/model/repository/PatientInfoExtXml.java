package it.csi.gatefire.common.model.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.openehealth.ipf.commons.ihe.xds.core.metadata.Address;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Identifiable;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Name;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.Timestamp;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.jaxbadapters.StringMap;
import org.openehealth.ipf.commons.ihe.xds.core.metadata.jaxbadapters.StringMapAdapter;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PatientInfoExt")
public class PatientInfoExtXml {
	@XmlElement(name = "id")
	private List<Identifiable> ids;

	@XmlElement(name = "name")
	private List<Name> names;

	@XmlElement(name = "birthTime")
	private Timestamp dateOfBirth;

	@XmlElement(name = "gender")
	private String gender;

	@XmlElement(name = "birthCountry")
	private String birthCountry;

	@XmlElement(name = "address")
	private List<Address> addresses;

	@XmlJavaTypeAdapter(StringMapAdapter.class)
	@XmlElement(name = "other", type = StringMap.class)
	private Map<String, List<String>> other;

	public List<Identifiable> getIds() {
		if (ids == null) {
			ids = new ArrayList<>();
		}
		return ids;
	}

	public void setIds(List<Identifiable> ids) {
		this.ids = ids;
	}

	public List<Name> getNames() {
		if (names == null) {
			names = new ArrayList<>();
		}
		return names;
	}

	public void setNames(List<Name> names) {
		this.names = names;
	}

	public Timestamp getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Timestamp dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Address> getAddresses() {
		if (addresses == null) {
			addresses = new ArrayList<>();
		}
		return addresses;
	}

	public String getBirthCountry() {
		return birthCountry;
	}

	public void setBirthCountry(String birthCountry) {
		this.birthCountry = birthCountry;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	public Map<String, List<String>> getOther() {
		if (other == null) {
			other = new HashMap<>();
		}
		return other;
	}

	public void setOther(Map<String, List<String>> other) {
		this.other = other;
	}
}
