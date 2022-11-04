package it.csi.gatefire.common.model.repository;

import java.util.Date;

import javax.validation.constraints.NotNull;

public class Paziente extends Persona {
	
	private String idAura = "-1"; // documentEntry.sourcePatientId ID_AURA_ASSIGNING_AUTH
	// documententry.sourcepatientInfo.id

	@NotNull(message = "Campo obbligatorio")
	private String comuneDiNascita;// documententry.sourcepatientInfo .address tipo N

	@NotNull(message = "Campo obbligatorio")
	private String codiceNazioneDiNascita;// documententry.sourcepatientInfo .address tipo N

	@NotNull(message = "Campo obbligatorio")
	private String codIstatComuneDiNascita;// documententry.sourcepatientInfo .address tipo N

	@NotNull(message = "Campo obbligatorio")
	private Date dataDiNascita;// documententry.sourcepatientInfo .address tipo N

	@NotNull(message = "Campo obbligatorio")
	private String sesso;// documententry.sourcepatientInfo .address tipo N

	private String identificativoGenitoreTutore;// TODO BOH

	private Indirizzo indirizzoResidenza;

	public String getCodiceNazioneDiNascita() {
		return codiceNazioneDiNascita;
	}

	public void setCodiceNazioneDiNascita(String codiceNazioneDiNascita) {
		this.codiceNazioneDiNascita = codiceNazioneDiNascita;
	}

	public String getCodIstatComuneDiNascita() {
		return codIstatComuneDiNascita;
	}

	public void setCodIstatComuneDiNascita(String codIstatComuneDiNascita) {
		this.codIstatComuneDiNascita = codIstatComuneDiNascita;
	}

	public Indirizzo getIndirizzoResidenza() {
		return indirizzoResidenza;
	}

	public void setIndirizzoResidenza(Indirizzo indirizzoResidenza) {
		this.indirizzoResidenza = indirizzoResidenza;
	}

	public String getIdAura() {
		return idAura;
	}

	public void setIdAura(String idAura) {
		this.idAura = idAura;
	}

	public String getComuneDiNascita() {
		return comuneDiNascita;
	}

	public void setComuneDiNascita(String comuneDiNascita) {
		if (comuneDiNascita != null) {
			this.comuneDiNascita = comuneDiNascita.toUpperCase();
		} else {
			this.comuneDiNascita = null;
		}
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getSesso() {
		return sesso;
	}

	public void setSesso(String sesso) {
		if (sesso != null) {
			this.sesso = sesso.toUpperCase();
		} else {
			this.sesso = sesso;
		}
	}

	public String getIdentificativoGenitoreTutore() {
		return identificativoGenitoreTutore;
	}

	public void setIdentificativoGenitoreTutore(String identificativoGenitoreTutore) {
		this.identificativoGenitoreTutore = identificativoGenitoreTutore;
	}

}