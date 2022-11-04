package it.csi.gatefire.repository.validator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.util.StringUtils;

import it.csi.gatefire.common.model.AssertionIdentity;
import it.csi.gatefire.common.model.ErrorField;
import it.csi.gatefire.common.model.ITIMetadata;
import it.csi.gatefire.common.model.ITIMetadataUpdate;
import it.csi.gatefire.common.model.RepositoryDocIdInput;
import it.csi.gatefire.common.model.RepositoryInput;
import it.csi.gatefire.common.model.RepositoryMetadataUpdateInput;
import it.csi.gatefire.common.model.RepositoryUndoInput;
import it.csi.gatefire.common.model.repository.Documento;
import it.csi.gatefire.common.model.repository.Episodio;
import it.csi.gatefire.common.model.repository.Indirizzo;
import it.csi.gatefire.common.model.repository.Paziente;
import it.csi.gatefire.common.model.repository.Richiesta;
import it.csi.gatefire.common.model.repository.Utente;
import it.csi.gatefire.common.model.repository.consts.RepoConsts;
import it.csi.gatefire.dbhelper.model.GatefireDXdscode;

public class RepoValidator {
	public static final String IDENTITY_PATH = "identity";

	private RepoValidator() {
		super();

	}

	public static List<ErrorField> validate(RepositoryInput input, Map<String, List<GatefireDXdscode>> map,
			boolean authRequired, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {

			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<RepositoryInput>> violations = validator.validate(input);

			for (ConstraintViolation<RepositoryInput> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}

			if (input.getMetadata() != null) {
				list.addAll(validate(input.getMetadata(), map, "metadata"));
			}
		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}

		return list;
	}

	public static List<ErrorField> validate(RepositoryMetadataUpdateInput input, boolean authRequired, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {

			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<RepositoryMetadataUpdateInput>> violations = validator.validate(input);

			for (ConstraintViolation<RepositoryMetadataUpdateInput> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}
			ITIMetadataUpdate metadata = input.getMetadata();
			if (metadata != null) {
				Documento doc = metadata.getDocumento();
				if (doc == null) {
					var err = new ErrorField(prepend + "metadata.documento", ErrorField.CAMPO_OBBLIGATORIO);
					list.add(err);
				} else {
					try {
						if (isNull(doc)) {
							var err = new ErrorField(prepend + "metadata.documento",
									"Specificare almeno un campo da aggiornare");
							list.add(err);
						}
					} catch (IllegalAccessException e) {

						e.printStackTrace();
					}
				}

				if (metadata.getRichiesta() == null) {
					var err = new ErrorField(prepend + "metadata.richiesta", ErrorField.CAMPO_OBBLIGATORIO);
					list.add(err);
				} else {
					Set<ConstraintViolation<Richiesta>> richiestaViolation = validator
							.validate(metadata.getRichiesta());
					for (ConstraintViolation<Richiesta> constraintViolation : richiestaViolation) {
						ErrorField err = new ErrorField(
								prepend + "metadata.richiesta." + constraintViolation.getPropertyPath().toString(),
								constraintViolation.getMessage());
						list.add(err);

					}
				}
			}

		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}

		return list;
	}

	public static List<ErrorField> validate(Documento input, Map<String, List<GatefireDXdscode>> map, String prefix) {

		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<Documento>> violations = validator.validate(input);

			for (ConstraintViolation<Documento> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}
			if ("1".equalsIgnoreCase(input.getInvioFSE())) {
				if (!StringUtils.hasLength(input.getPagatoTicket())) {
					ErrorField err = new ErrorField(prepend + "pagatoTicket",
							ErrorField.CAMPO_OBBLIGATORIO + " quando invioFSE=1");
					list.add(err);
				}
			}
			if (input.getFirmatoDigitalmente() != null && input.getFirmatoDigitalmente().booleanValue()) {
				if (input.getTipoFirma() == null) {
					ErrorField err = new ErrorField(prepend + "tipoFirma",
							ErrorField.CAMPO_OBBLIGATORIO + " quando firmatoDicitalmente=true");
					list.add(err);
				}
			}

			if (input.getScaricabileDalCittadino() != null && input.getScaricabileDalCittadino().booleanValue()) {
				if (input.getCodicePin() == null) {
					ErrorField err = new ErrorField(prepend + "codicePin",
							ErrorField.CAMPO_OBBLIGATORIO + " quando scaricabileDalCittadino=true");
					list.add(err);
				}
			}

			if (StringUtils.hasLength(input.getTipologiaStrutturaProdDoc())
					&& !codeFound(input.getTipologiaStrutturaProdDoc(),
							map.get(RepoConsts.HEALTHCARE_FACILITY_TYPE_CODE))) {
				ErrorField err = new ErrorField(prepend + "tipologiaStrutturaProdDoc",
						input.getTipologiaStrutturaProdDoc() + ": " + ErrorField.VALORE_INVALIDO);
				list.add(err);
			}
			if (StringUtils.hasLength(input.getTipologiaDocumentoAlto())
					&& !codeFound(input.getTipologiaDocumentoAlto(), map.get(RepoConsts.CLASS_CODE))) {
				ErrorField err = new ErrorField(prepend + "tipologiaDocumentoAlto",
						input.getTipologiaDocumentoAlto() + ": " + ErrorField.VALORE_INVALIDO);
				list.add(err);
			}
			if (StringUtils.hasLength(input.getTipologiaDocumentoMedio())
					&& !codeFound(input.getTipologiaDocumentoMedio(), map.get(RepoConsts.TYPE_CODE))) {
				ErrorField err = new ErrorField(prepend + "tipologiaDocumentoMedio",
						input.getTipologiaDocumentoMedio() + ": " + ErrorField.VALORE_INVALIDO);
				list.add(err);
			}
			if (StringUtils.hasLength(input.getFormatCode())
					&& !codeFound(input.getFormatCode(), map.get(RepoConsts.FORMAT_CODE))) {
				ErrorField err = new ErrorField(prepend + "formatCode",
						input.getFormatCode() + ": " + ErrorField.VALORE_INVALIDO);
				list.add(err);
			}
			if (StringUtils.hasLength(input.getAssettoOrganizzativo())
					&& !codeFound(input.getAssettoOrganizzativo(), map.get(RepoConsts.PRACTICE_SETTING_CODE))) {
				ErrorField err = new ErrorField(prepend + "assettoOrganizzativo",
						input.getAssettoOrganizzativo() + ": " + ErrorField.VALORE_INVALIDO);
				list.add(err);
			}

			if (StringUtils.hasLength(input.getLivelloRiservatezza())
					&& !codeFound(input.getLivelloRiservatezza(), map.get(RepoConsts.CONFIDENTIALITY_CODE))) {
				ErrorField err = new ErrorField(prepend + "livelloRiservatezza",
						input.getLivelloRiservatezza() + ": " + ErrorField.VALORE_INVALIDO);
				list.add(err);
			}

		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}
		return list;
	}

	public static List<ErrorField> validate(Utente input, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<Utente>> violations = validator.validate(input);

			for (ConstraintViolation<Utente> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}

			if (!StringUtils.hasLength(input.getCodiceStruttura())
					&& !StringUtils.hasLength(input.getCodiceMatricola())) {
				ErrorField err = new ErrorField(prepend + "codiceStruttura",
						"specificare codiceStruttura o codiceMatricola");
				list.add(err);
				err = new ErrorField(prepend + "codiceMatricola", "specificare codiceStruttura o codiceMatricola");
				list.add(err);
			}

		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}
		return list;
	}

	public static List<ErrorField> validate(Paziente input, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<Paziente>> violations = validator.validate(input);

			for (ConstraintViolation<Paziente> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}

			if (input.getIndirizzoResidenza() != null) {

				Set<ConstraintViolation<Indirizzo>> addressViolations = validator
						.validate(input.getIndirizzoResidenza());

				for (ConstraintViolation<Indirizzo> constraintViolation : addressViolations) {
					ErrorField err = new ErrorField(
							prepend + "indirizzoResidenza." + constraintViolation.getPropertyPath().toString(),
							constraintViolation.getMessage());
					list.add(err);

				}

			}
			// verificare indirizzo di residenza se presente

			/*
			 * if (!StringUtils.hasLength(input.getCodiceStruttura()) &&
			 * !StringUtils.hasLength(input.getCodiceMatricola())) { ErrorField err = new
			 * ErrorField(prepend + "codiceStruttura",
			 * "specificare codiceStruttura o codiceMatricola"); list.add(err); err = new
			 * ErrorField(prepend + "codiceMatricola",
			 * "specificare codiceStruttura o codiceMatricola"); list.add(err); }
			 * 
			 * if (StringUtils.hasLength(input.getCodiceStruttura()) &&
			 * !codeFound(input.getCodiceStruttura(), map.get(RepoConsts.STS11))) {
			 * ErrorField err = new ErrorField(prepend + "codiceStruttura",
			 * input.getCodiceStruttura() + ": " + ErrorField.VALORE_INVALIDO);
			 * list.add(err); }
			 */
		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}
		return list;
	}

	public static List<ErrorField> validate(Richiesta input, Map<String, List<GatefireDXdscode>> map, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<Richiesta>> violations = validator.validate(input);

			for (ConstraintViolation<Richiesta> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}

			if (StringUtils.hasLength(input.getCodiceAzienda())
					&& !codeFound(input.getCodiceAzienda(), map.get(RepoConsts.FLS11))) {
				ErrorField err = new ErrorField(prepend + "codiceAzienda",
						input.getCodiceAzienda() + ": " + ErrorField.VALORE_INVALIDO);
				list.add(err);
			}

			if (input.getUtente() != null) {
				list.addAll(validate(input.getUtente(), prepend + "utente"));
			}

		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}
		return list;
	}

	public static List<ErrorField> validate(Episodio input, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<Episodio>> violations = validator.validate(input);

			for (ConstraintViolation<Episodio> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}

			if (StringUtils.hasLength(input.getCodiceLuogoDimissione()) && input.getDataFine() == null) {
				ErrorField err = new ErrorField(prepend + "dataFine",
						"Campo obbligatorio quando specificato il codiceLuogoDimissione");
				list.add(err);
			}

		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}
		return list;
	}

	public static List<ErrorField> validate(ITIMetadata input, Map<String, List<GatefireDXdscode>> map, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<ITIMetadata>> violations = validator.validate(input);

			for (ConstraintViolation<ITIMetadata> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}

			if (input.getDocumento() != null) {
				list.addAll(validate(input.getDocumento(), map, prepend + "documento"));
			}
			if (input.getRichiesta() != null) {
				list.addAll(validate(input.getRichiesta(), map, prepend + "richiesta"));
			}
			if (input.getPaziente() != null) {
				list.addAll(validate(input.getRichiesta(), map, prepend + "paziente"));
			}
			if (input.getEpisodio() != null) {
				list.addAll(validate(input.getEpisodio(), prepend + "episodio"));
			}

		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}
		return list;
	}

	public static boolean codeFound(String code, List<GatefireDXdscode> list) {
		for (GatefireDXdscode xdscode : list) {
			if (xdscode.getCode().equalsIgnoreCase(code)) {

				return true;
			}
		}

		return

		false;

	}

	public static List<ErrorField> validate(RepositoryDocIdInput input, boolean authRequired, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {

			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<RepositoryDocIdInput>> violations = validator.validate(input);

			for (ConstraintViolation<RepositoryDocIdInput> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}

		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}

		return list;
	}

	public static List<ErrorField> validate(RepositoryUndoInput input, boolean authRequired, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {

			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<RepositoryUndoInput>> violations = validator.validate(input);

			for (ConstraintViolation<RepositoryUndoInput> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}

		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}

		return list;
	}

	private static boolean isNull(Object obj) throws IllegalAccessException {

		for (Field f : obj.getClass().getDeclaredFields()) {
			f.setAccessible(true);
			if (f.get(obj) != null)
				return false;
		}
		return true;
	}
}
