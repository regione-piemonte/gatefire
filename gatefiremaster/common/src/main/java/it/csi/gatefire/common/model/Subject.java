package it.csi.gatefire.common.model;

public class Subject {
	private String distinguishName;
	private String commonName;
	private String codiceFiscale;
	private String role;
	private String country;
	private String organization;


	public String getDistinguishName() {
		return distinguishName;
	}

	public void setDistinguishName(String distinguishName) {
		this.distinguishName = distinguishName;
	}

	public String getCommonName() {
		return commonName;
	}

	public void setCommonName(String commonName) {
		this.commonName = commonName;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getOrganization() {
		return organization;
	}

	public void setOrganization(String organization) {
		this.organization = organization;
	}

	@Override
	public String toString() {
		return "Subject [distinguishName=" + distinguishName + ", commonName=" + commonName + ", codiceFiscale="
				+ codiceFiscale + ", role=" + role + ", country=" + country + ", organization=" + organization + "]";
	}

}
