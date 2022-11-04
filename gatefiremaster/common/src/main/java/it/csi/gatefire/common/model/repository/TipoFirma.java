package it.csi.gatefire.common.model.repository;

public enum TipoFirma {

	PADES("PB"), CADES("C");

	private String value;

	TipoFirma(String value) {
		this.value = value;
	}

	public static TipoFirma fromValue(String value) {
		for (TipoFirma c : TipoFirma.values()) {
			if (c.value.equals(value)) {
				return c;
			}
		}
		throw new IllegalArgumentException(value);
	}

	public String getTipo() {
		return value;
	}

}
