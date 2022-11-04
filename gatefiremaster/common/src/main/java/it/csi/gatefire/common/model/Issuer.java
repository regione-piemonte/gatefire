/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model;

public class Issuer {
    private String distinguishName;
    private String name;
    private String partitaIva;
	public String getDistinguishName() {
		return distinguishName;
	}
	public void setDistinguishName(String distinguishName) {
		this.distinguishName = distinguishName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPartitaIva() {
		return partitaIva;
	}
	public void setPartitaIva(String partitaIva) {
		this.partitaIva = partitaIva;
	}

	@Override
	public String toString() {
		return "Issuer [distinguishName=" + distinguishName + ", name=" + name + ", partitaIva=" + partitaIva + "]";
	}
}
