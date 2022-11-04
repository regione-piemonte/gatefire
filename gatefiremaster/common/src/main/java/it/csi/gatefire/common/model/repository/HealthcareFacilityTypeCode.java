/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.model.repository;

import it.csi.gatefire.common.model.repository.consts.RepoConsts;

public enum HealthcareFacilityTypeCode {
	Ospedale(RepoConsts.HEALTHCARE_FACILITY_CODING_SCHEME), Prevenzione(RepoConsts.HEALTHCARE_FACILITY_CODING_SCHEME),
	Territorio(RepoConsts.HEALTHCARE_FACILITY_CODING_SCHEME), SistemaTS(RepoConsts.HEALTHCARE_FACILITY_CODING_SCHEME);

	private final String code;

	HealthcareFacilityTypeCode(String code) {
		this.code = code;
	}

	public static HealthcareFacilityTypeCode fromValue(String v) {
		for (HealthcareFacilityTypeCode c : HealthcareFacilityTypeCode.values()) {
			if (c.code.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}
