package it.csi.gatefire.dbhelper.model.fc;

import java.util.Date;

public class EventoFilterBean {

	private Date dataDa;

	private Date dataA;

	private String codiceTipoEvento;

	private String stato;

	private String idUtente;

	private String codiceApplicazione;
	private String codiceFiscale;

	private String nomeFile;

	private String codiceCertificationAuthority;

	private String codiceRepository;
	
	private String collocazione;

	public Date getDataDa() {
		return dataDa;
	}

	public void setDataDa(Date dataDa) {
		this.dataDa = dataDa;
	}

	public Date getDataA() {
		return dataA;
	}

	public void setDataA(Date dataA) {
		this.dataA = dataA;
	}

	public String getCodiceFiscale() {
		return codiceFiscale;
	}

	public void setCodiceFiscale(String codiceFiscale) {
		this.codiceFiscale = codiceFiscale;
	}

	public String getNomeFile() {
		return nomeFile;
	}

	public void setNomeFile(String nomeFile) {
		this.nomeFile = nomeFile;
	}

	public String getCodiceTipoEvento() {
		return codiceTipoEvento;
	}

	public void setCodiceTipoEvento(String codiceTipoEvento) {
		this.codiceTipoEvento = codiceTipoEvento;
	}

	public String getStato() {
		return stato;
	}

	public void setStato(String stato) {
		this.stato = stato;
	}

	public String getIdUtente() {
		return idUtente;
	}

	public void setIdUtente(String idUtente) {
		this.idUtente = idUtente;
	}

	public String getCodiceApplicazione() {
		return codiceApplicazione;
	}

	public void setCodiceApplicazione(String codiceApplicazione) {
		this.codiceApplicazione = codiceApplicazione;
	}

	public String getCodiceCertificationAuthority() {
		return codiceCertificationAuthority;
	}

	public void setCodiceCertificationAuthority(String codiceCertificationAuthority) {
		this.codiceCertificationAuthority = codiceCertificationAuthority;
	}

	public String getCodiceRepository() {
		return codiceRepository;
	}

	public void setCodiceRepository(String codiceRepository) {
		this.codiceRepository = codiceRepository;
	}

	public String getCollocazione() {
		return collocazione;
	}

	public void setCollocazione(String collocazione) {
		this.collocazione = collocazione;
	}

	@Override
	public String toString() {
		return "EventoFilterBean [dataDa=" + dataDa + ", dataA=" + dataA + ", codiceTipoEvento=" + codiceTipoEvento
				+ ", stato=" + stato + ", idUtente=" + idUtente + ", codiceApplicazione=" + codiceApplicazione
				+ ", codiceFiscale=" + codiceFiscale + ", nomeFile=" + nomeFile + ", codiceCertificationAuthority="
				+ codiceCertificationAuthority + ", codiceRepository=" + codiceRepository + ", collocazione="
				+ collocazione + "]";
	}

}
