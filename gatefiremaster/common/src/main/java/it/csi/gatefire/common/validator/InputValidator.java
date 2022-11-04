package it.csi.gatefire.common.validator;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.springframework.util.StringUtils;

import it.csi.gatefire.common.consts.ParamsConsts;
import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.model.AutoSignIdentity;
import it.csi.gatefire.common.model.CallInfo;
import it.csi.gatefire.common.model.ErrorField;
import it.csi.gatefire.common.model.Identity;
import it.csi.gatefire.common.model.MarkIdentity;
import it.csi.gatefire.common.model.MarkInput;
import it.csi.gatefire.common.model.OtpReqInput;
import it.csi.gatefire.common.model.PadesInput;
import it.csi.gatefire.common.model.SessionInput;
import it.csi.gatefire.common.model.SignIdentity;
import it.csi.gatefire.common.util.FileUtils;

public class InputValidator {

	public static final String ATTACHMENT_PATH = "attachment";

	public static final String CALLINFO_PATH = "callInfo";

	public static final String IDENTITY_PATH = "identity";

	public static List<ErrorField> validate(PadesInput input, String tagCA, List<String> appList, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {

			var factory = Validation.buildDefaultValidatorFactory();
			var validator = factory.getValidator();

			Set<ConstraintViolation<PadesInput>> violations = validator.validate(input);

			for (ConstraintViolation<PadesInput> constraintViolation : violations) {
				var err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}

			list.addAll(validate(input.getCallInfo(), tagCA, appList, prepend + CALLINFO_PATH));
			if (input.isRequiredMark()) {
				list.addAll(validate(input.getMarkIdentity(), prepend + "markIdentity"));

			}
		} else {
			var err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}

		return list;
	}

	

	public static List<ErrorField> validate(SessionInput input, String tagCA, List<String> appList,
			boolean closeSession, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {

			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<SessionInput>> violations = validator.validate(input);

			for (ConstraintViolation<SessionInput> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}
			list.addAll(validate(input.getSignIdentity(), tagCA, closeSession, prepend + "signIdentity"));
			list.addAll(validate(input.getCallInfo(), tagCA, appList, prepend + CALLINFO_PATH));
		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}

		return list;
	}

	public static List<ErrorField> validate(OtpReqInput input, String tagCA, List<String> appList, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {

			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<OtpReqInput>> violations = validator.validate(input);

			for (ConstraintViolation<OtpReqInput> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}
			list.addAll(validate(input.getIdentity(), tagCA, prepend + "identity", false));
			list.addAll(validate(input.getCallInfo(), tagCA, appList, prepend + CALLINFO_PATH));
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(tagCA) && input.getOtpCredentialsType() == null) {
				ErrorField err = new ErrorField(prepend + "otpCredentialsType", ErrorField.CAMPO_OBBLIGATORIO);
				list.add(err);

			}
		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}

		return list;
	}

	public static List<ErrorField> validate(MarkInput input, String tagCA, List<String> appList, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {

			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<MarkInput>> violations = validator.validate(input);

			for (ConstraintViolation<MarkInput> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}
			list.addAll(validate(input.getCallInfo(), tagCA, appList, prepend + "signIdentity"));
		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}

		return list;
	}

	public static List<ErrorField> validate(SignIdentity input, String tagCA, boolean closeSession, String prefix) {

		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<SignIdentity>> violations = validator.validate(input);

			for (ConstraintViolation<SignIdentity> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}
			if (!StringUtils.hasLength(input.getPassword())) {
				ErrorField err = new ErrorField("password", ErrorField.CAMPO_OBBLIGATORIO);
				list.add(err);
			}

			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(tagCA) && !StringUtils.hasLength(input.getSessionId())
					&& !StringUtils.hasLength(input.getOtp()) && !closeSession) {
				ErrorField err = new ErrorField(prepend + "otp", "specificare Otp o sessionId");
				list.add(err);
				err = new ErrorField(prepend + "sessionId", "specificare Otp o sessionId");
				list.add(err);

			} else if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(tagCA) && !StringUtils.hasLength(input.getOtp())) {
				ErrorField err = new ErrorField(prepend + "otp", "specificare Otp");
				list.add(err);

			}

		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}
		return list;
	}

	public static List<ErrorField> validate(Identity input, String tagCA, String prefix, boolean firma) {

		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<Identity>> violations = validator.validate(input);

			for (ConstraintViolation<Identity> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}
			if ((ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(tagCA) || firma)
					&& !StringUtils.hasLength(input.getPassword())) {
				ErrorField err = new ErrorField(prepend + "password", ErrorField.CAMPO_OBBLIGATORIO);
				list.add(err);
			}

		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}
		return list;
	}

	public static List<ErrorField> validate(AutoSignIdentity input, String tagCA, String prefix) {

		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<AutoSignIdentity>> violations = validator.validate(input);

			for (ConstraintViolation<AutoSignIdentity> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}
			if (ParamsConsts.TAG_CA_ARUBA.equalsIgnoreCase(tagCA)) {

				if (!StringUtils.hasLength(input.getDelegatedUser())) {
					if (!StringUtils.hasLength(input.getPassword())) {
						ErrorField err = new ErrorField(prepend + "password", ErrorField.CAMPO_OBBLIGATORIO);
						list.add(err);
					}
				} else {
					if (!StringUtils.hasLength(input.getDelegatedPassword())) {
						ErrorField err = new ErrorField(prepend + "delegatedPassword", ErrorField.CAMPO_OBBLIGATORIO);
						list.add(err);
					}

				}
			}
			if (ParamsConsts.TAG_CA_INFOCERT.equalsIgnoreCase(tagCA) && !StringUtils.hasLength(input.getPassword())) {
				ErrorField err = new ErrorField(prepend + "password", ErrorField.CAMPO_OBBLIGATORIO);
				list.add(err);

			}

		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}
		return list;
	}

	public static List<ErrorField> validate(MarkIdentity input, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<MarkIdentity>> violations = validator.validate(input);

			for (ConstraintViolation<MarkIdentity> constraintViolation : violations) {
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

	public static List<ErrorField> validate(CallInfo input, String tagCa, List<String> appList, String prefix) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<CallInfo>> violations = validator.validate(input);

			for (ConstraintViolation<CallInfo> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}

			if (input.getCaCode() != null && tagCa == null) {
				ErrorField err = new ErrorField(prepend + "caCode",
						"Codice CA [" + input.getCaCode() + "] sconosciuto.");
				list.add(err);
			}

			if (input.getApplicationCode() != null && !appList.contains(input.getApplicationCode().trim())) {
				ErrorField err = new ErrorField(prepend + "applicationCode",
						"Codice Applicazione  [" + input.getApplicationCode() + "] sconosciuto.");
				list.add(err);
			}

			if (StringUtils.hasLength(input.getCodiceFiscale())) {
				/*FiscalCodeUtils.FiscalCodeParserResult res = FiscalCodeUtils.parseFiscalCode(input.getCodiceFiscale());
				if (res.getResultCode() != 0 && !PIvaUtils.isValid(input.getCodiceFiscale())) {
					ErrorField err = new ErrorField(prepend + "codiceFiscale", "CF/PIva invalido: " + res.getMessage());
					list.add(err);

				}*/
			}
		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}
		return list;
	}

	private InputValidator() {
		super();

	}

	public static List<ErrorField> validate(Attachment input, String prefix, Long maxsize) {
		String prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null) {
			ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
			Validator validator = factory.getValidator();

			Set<ConstraintViolation<Attachment>> violations = validator.validate(input);

			for (ConstraintViolation<Attachment> constraintViolation : violations) {
				ErrorField err = new ErrorField(prepend + constraintViolation.getPropertyPath().toString(),
						constraintViolation.getMessage());
				list.add(err);

			}

			if (input.getFile() == null || input.getFile().length == 0) {
				ErrorField err = new ErrorField(prepend + "file", "File vuoto");
				list.add(err);
			} else if (maxsize != null && input.getFile().length > maxsize) {
				ErrorField err = new ErrorField(prepend + "file",
						"Dimensioni eccessive del file; massimo: " + FileUtils.formatFileSize(maxsize));
				list.add(err);
			}

		} else {
			ErrorField err = new ErrorField(prefix, ErrorField.CAMPO_OBBLIGATORIO);
			list.add(err);
		}
		return list;
	}

	public static List<ErrorField> validate(List<Attachment> input, String prefix, Long maxsize, Long maxNFiles) {
		var prepend = "";
		if (StringUtils.hasLength(prefix)) {
			prepend = prefix + ".";
		}
		List<ErrorField> list = new ArrayList<>();
		if (input != null && !input.isEmpty()) {
			if (maxNFiles != null && input.size() > maxNFiles) {
				ErrorField err = new ErrorField(prepend + "",
						"Caricati " + input.size() + " files. Max numero files [" + maxNFiles + "]");
				list.add(err);
			}

			if (maxsize != null) {
				long dimTotale = 0;
				for (Attachment attachment : input) {
					if (attachment.getFile() != null) {
						dimTotale += attachment.getFile().length;
					}
				}

				if (dimTotale > maxsize) {
					ErrorField err = new ErrorField(prepend + "file", "Dimensioni complessiva dei file: ["
							+ FileUtils.formatFileSize(dimTotale) + "]. massimo: " + FileUtils.formatFileSize(maxsize));
					list.add(err);
				}
			}

		}
		return list;
	}

	
}
