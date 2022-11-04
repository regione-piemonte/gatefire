package it.csi.gatefire.ca.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.util.StringUtils;

import it.csi.gatefire.ca.infocert.C;
import it.csi.gatefire.ca.infocert.CN;
import it.csi.gatefire.ca.infocert.DESCR;
import it.csi.gatefire.ca.infocert.DNQUALIF;
import it.csi.gatefire.ca.infocert.DOM;
import it.csi.gatefire.ca.infocert.EMAIL;
import it.csi.gatefire.ca.infocert.GIVEN;
import it.csi.gatefire.ca.infocert.O;
import it.csi.gatefire.ca.infocert.ORGID;
import it.csi.gatefire.ca.infocert.OU;
import it.csi.gatefire.ca.infocert.Oid;
import it.csi.gatefire.ca.infocert.PROV;
import it.csi.gatefire.ca.infocert.PSEUD;
import it.csi.gatefire.ca.infocert.PolicyInformation;
import it.csi.gatefire.ca.infocert.PolicyInformationList;
import it.csi.gatefire.ca.infocert.SER;
import it.csi.gatefire.ca.infocert.SUR;
import it.csi.gatefire.ca.infocert.SignatureTimeStamp;
import it.csi.gatefire.ca.infocert.SigningTime;
import it.csi.gatefire.ca.infocert.TITLE;
import it.csi.gatefire.common.model.CertPolicy;
import it.csi.gatefire.common.model.Certificate;
import it.csi.gatefire.common.model.Issuer;
import it.csi.gatefire.common.model.MarkVerifyResult;
import it.csi.gatefire.common.model.Signer;
import it.csi.gatefire.common.model.Subject;
import it.csi.gatefire.common.model.TimeStamp;
import it.csi.gatefire.common.util.DateUtils;

public class InfocertObjFiller {
	public static TimeStamp fillTimeStamp(SignatureTimeStamp sts) {
		TimeStamp timestamp = new TimeStamp();

		if (sts != null) {
			timestamp.setSerialNumber(sts.getTimeStampSerial());
			timestamp.setGenerationTime(DateUtils.getDateShortWithZ(sts.getTimeStampDate()));
			Subject tsSubject = fillSubject(sts.getSubject());
			timestamp.setTsAuthorityName(tsSubject.getCommonName());
			String innerStatus = null;
			if (sts.getStatus() != null) {
				innerStatus = sts.getStatus().getvalue();
			}
			timestamp.setSignValid("OK".equalsIgnoreCase(innerStatus));

		}
		return timestamp;
	}

	public static TimeStamp fillTimeStamp(it.csi.gatefire.ca.infocert.TimeStamp sts) {
		TimeStamp timestamp = new TimeStamp();

		if (sts != null) {
			timestamp.setSerialNumber(sts.getTimeStampSerial());
			timestamp.setGenerationTime(DateUtils.getDateShortWithZ(sts.getTimeStampDate()));
			Subject tsSubject = fillSubject(sts.getSubject());
			timestamp.setTsAuthorityName(tsSubject.getCommonName());
			String innerStatus = null;
			if (sts.getStatus() != null) {
				innerStatus = sts.getStatus().getvalue();
			}
			timestamp.setSignValid("OK".equalsIgnoreCase(innerStatus));

		}
		return timestamp;
	}

	public static Issuer fillIssuer(it.csi.gatefire.ca.infocert.Issuer tempIssuer) {

		Issuer issuer = new Issuer();
		List<Object> listAttr = tempIssuer.getProperties();

		Map<String, String> distinguishMap = fillMap(listAttr);

		if (distinguishMap.get("O") != null) {
			issuer.setName(distinguishMap.get("O"));
		}
		if (distinguishMap.get("ORGID") != null) {

			String[] split = distinguishMap.get("ORGID").split("-");
			if (split != null && split.length == 2) {
				issuer.setPartitaIva(split[1]);
			}

		}

		issuer.setDistinguishName(getDistName(distinguishMap));

		return issuer;
	}

	public static Map<String, String> fillMap(List<Object> listAttr) {
		Map<String, String> distinguishMap = new LinkedHashMap<>();

		for (Object issueAttr : listAttr) {
			if (issueAttr instanceof C) {

				distinguishMap.put("C", ((C) issueAttr).getvalue());
			} else if (issueAttr instanceof O) {
				distinguishMap.put("O", ((O) issueAttr).getvalue());
			} else if (issueAttr instanceof OU) {
				distinguishMap.put("OU", ((OU) issueAttr).getvalue());
			} else if (issueAttr instanceof SUR) {
				distinguishMap.put("SUR", ((SUR) issueAttr).getvalue());
			} else if (issueAttr instanceof SER) {
				distinguishMap.put("SER", ((SER) issueAttr).getvalue());

			} else if (issueAttr instanceof ORGID) {
				distinguishMap.put("ORGID", ((ORGID) issueAttr).getvalue());

			} else if (issueAttr instanceof GIVEN) {
				distinguishMap.put("GIVE", ((GIVEN) issueAttr).getvalue());
			} else if (issueAttr instanceof DNQUALIF) {
				distinguishMap.put("DNQUALIF", ((DNQUALIF) issueAttr).getvalue());
			} else if (issueAttr instanceof TITLE) {
				distinguishMap.put("TITLE", ((TITLE) issueAttr).getvalue());
			} else if (issueAttr instanceof CN) {

				distinguishMap.put("CN", ((CN) issueAttr).getvalue());
			} else if (issueAttr instanceof EMAIL) {
				distinguishMap.put("EMAIL", ((EMAIL) issueAttr).getvalue());
			} else if (issueAttr instanceof DOM) {
				distinguishMap.put("DOM", ((DOM) issueAttr).getvalue());
			} else if (issueAttr instanceof PSEUD) {
				distinguishMap.put("PSEUD", ((PSEUD) issueAttr).getvalue());
			} else if (issueAttr instanceof DESCR) {
				distinguishMap.put("DESCR", ((DESCR) issueAttr).getvalue());
			} else if (issueAttr instanceof PROV) {
				distinguishMap.put("PROV", ((PROV) issueAttr).getvalue());
			} else if (issueAttr instanceof Oid) {
				distinguishMap.put("Oid", ((Oid) issueAttr).getvalue());
			}

		}

		return distinguishMap;
	}

	public static String getDistName(Map<String, String> distinguishMap) {
		String distString = "";

		Set<String> keys = distinguishMap.keySet();

		for (String k : keys) {
			if (StringUtils.hasLength(distString)) {
				distString = k + "=" + distinguishMap.get(k) + "," + distString;
			} else {
				distString = k + "=" + distinguishMap.get(k);
			}
		}

		return distString;
	}

	public static Subject fillSubject(it.csi.gatefire.ca.infocert.Subject tempSubj) {
		Subject subject = new Subject();
		List<Object> listAttr = tempSubj.getProperties();

		Map<String, String> distinguishMap = fillMap(listAttr);

		if (distinguishMap.get("C") != null) {
			subject.setCountry(distinguishMap.get("C"));
		}

		if (distinguishMap.get("CN") != null) {
			subject.setCommonName(distinguishMap.get("CN"));
		}
		if (distinguishMap.get("SER") != null) {

			String[] split = distinguishMap.get("SER").split("-");
			if (split != null && split.length == 2) {
				subject.setCodiceFiscale(split[1]);
			}

		}

		subject.setDistinguishName(getDistName(distinguishMap));

		return subject;
	}

	public static Signer fillSigner(it.csi.gatefire.ca.infocert.Signer tempSigner) {

		Signer signer = new Signer();
		Subject subject = fillSubject(tempSigner.getSubject());
		Issuer issuer = fillIssuer(tempSigner.getIssuer());

		List<CertPolicy> certPolList = new ArrayList<>();

		PolicyInformationList policyInformationList = tempSigner.getPolicyInformationList();
		if (policyInformationList != null) {
			List<PolicyInformation> pollist = policyInformationList.getPolicyInformation();

			for (PolicyInformation policyInformation : pollist) {
				CertPolicy certPolicy = new CertPolicy();

				if (policyInformation.getPolicyQualifierList() != null
						&& policyInformation.getPolicyQualifierList().getPolicyQualifier() != null
						&& !policyInformation.getPolicyQualifierList().getPolicyQualifier().isEmpty()) {
					certPolicy.setCpsUri(
							policyInformation.getPolicyQualifierList().getPolicyQualifier().get(0).getCpsUri());
				}

				certPolicy.setCpText(policyInformation.getPolicyID());
				certPolList.add(certPolicy);
			}
		}

		Certificate certificate = new Certificate();
		certificate.setSn(tempSigner.getSerial());
		certificate.setIssuer(issuer);
		certificate.setSubject(subject);
		certificate.setCertPolicyList(certPolList);
		certificate.setValidFrom(DateUtils.getDateShortWithZ(tempSigner.getCertNotBefore()));
		certificate.setValidTo(DateUtils.getDateShortWithZ(tempSigner.getCertNotAfter()));

		certificate.setName(subject.getCommonName());
		Date ora = new Date();
		boolean dataValida = false;
		try {
			dataValida = (ora.compareTo(certificate.getValidFrom()) >= 0
					&& ora.compareTo(certificate.getValidTo()) <= 0);
		} catch (Exception e) {

			e.printStackTrace();
		}

		String status = null;
		String errorMessage = null;

		if (tempSigner.getStatus() != null) {
			status = tempSigner.getStatus().getvalue();
		}

		if (tempSigner.getErrorMessage() != null) {
			errorMessage = tempSigner.getErrorMessage().getvalue();
		}
		certificate.setTimeValid(dataValida);
		certificate.setValid("OK".equalsIgnoreCase(status));

		signer.setSignValidation(status);
		signer.setSignValidationMessage(errorMessage);

		signer.setValid("OK".equalsIgnoreCase(status));

		SigningTime signingTime = tempSigner.getSigningTime();
		Date signtimeDate = DateUtils.getDateShortWithZ(signingTime.getvalue());
		if (signtimeDate == null) {
			signer.setSignTimeStr(signingTime.getvalue());
		} else {
			signer.setSignTimeStr(signtimeDate.toString());
		}
		signer.setSignTime(signtimeDate);

		TimeStamp timestamp = fillTimeStamp(tempSigner.getSignatureTimeStamp());

		signer.setCertificate(certificate);
		signer.setTimestamp(timestamp);

		return signer;
	}

	private InfocertObjFiller() {
		super();

	}

	public static MarkVerifyResult fillMarkVerifyResult(it.csi.gatefire.ca.infocert.TimeStamp tst) {
		MarkVerifyResult markVerifyResult = new MarkVerifyResult();

		List<CertPolicy> certPolList = new ArrayList<>();

		PolicyInformationList policyInformationList = tst.getPolicyInformationList();
		if (policyInformationList != null) {
			List<PolicyInformation> pollist = policyInformationList.getPolicyInformation();

			for (PolicyInformation policyInformation : pollist) {
				CertPolicy certPolicy = new CertPolicy();

				if (policyInformation.getPolicyQualifierList() != null
						&& policyInformation.getPolicyQualifierList().getPolicyQualifier() != null
						&& !policyInformation.getPolicyQualifierList().getPolicyQualifier().isEmpty()) {
					certPolicy.setCpsUri(
							policyInformation.getPolicyQualifierList().getPolicyQualifier().get(0).getCpsUri());
				}

				certPolicy.setCpText(policyInformation.getPolicyID());
				certPolList.add(certPolicy);
			}
		}
		Subject subject = InfocertObjFiller.fillSubject(tst.getSubject());
		Issuer issuer = InfocertObjFiller.fillIssuer(tst.getIssuer());
		Certificate certificate = new Certificate();
		certificate.setSn(tst.getSerial());
		certificate.setIssuer(issuer);
		certificate.setSubject(subject);
		certificate.setCertPolicyList(certPolList);
		certificate.setValidFrom(DateUtils.getDateShortWithZ(tst.getCertNotBefore()));
		certificate.setValidTo(DateUtils.getDateShortWithZ(tst.getCertNotAfter()));

		certificate.setName(subject.getCommonName());
		Date ora = new Date();
		boolean dataValida = false;
		try {
			dataValida = (ora.compareTo(certificate.getValidFrom()) >= 0
					&& ora.compareTo(certificate.getValidTo()) <= 0);
		} catch (Exception e) {

			e.printStackTrace();
		}

		String status = null;
		String errorMessage = null;

		if (tst.getStatus() != null) {
			status = tst.getStatus().getvalue();
		}

		if (tst.getErrorMessage() != null) {
			errorMessage = tst.getErrorMessage().getvalue();
		}
		certificate.setTimeValid(dataValida);
		certificate.setValid("OK".equalsIgnoreCase(status));

		String validation = status;
		String validationMessage = errorMessage;

		TimeStamp timeStamp = InfocertObjFiller.fillTimeStamp(tst);

		markVerifyResult.setCertificate(certificate);
		markVerifyResult.setTimeStamp(timeStamp);

		markVerifyResult.setValidation(validation);
		markVerifyResult.setValidationMessage(validationMessage);

		return markVerifyResult;

	}

}
