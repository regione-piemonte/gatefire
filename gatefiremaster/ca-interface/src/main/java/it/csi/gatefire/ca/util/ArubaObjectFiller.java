package it.csi.gatefire.ca.util;

import java.util.ArrayList;
import java.util.List;

import it.csi.gatefire.ca.aruba.actalisvol.CertIssuer;
import it.csi.gatefire.ca.aruba.actalisvol.CertRevocationInfo;
import it.csi.gatefire.ca.aruba.actalisvol.CertSubject;
import it.csi.gatefire.ca.aruba.actalisvol.SignerTimeStamp;
import it.csi.gatefire.common.model.CertPolicy;
import it.csi.gatefire.common.model.Certificate;
import it.csi.gatefire.common.model.Issuer;
import it.csi.gatefire.common.model.RevocationInfo;
import it.csi.gatefire.common.model.SignatureInfo;
import it.csi.gatefire.common.model.Signer;
import it.csi.gatefire.common.model.Subject;
import it.csi.gatefire.common.model.TimeStamp;

public class ArubaObjectFiller {

	private ArubaObjectFiller() {
		super();

	}

	public static List<Signer> fillSignerList(List<it.csi.gatefire.ca.aruba.actalisvol.Signer> inlist) {
		List<Signer> list = new ArrayList<>();
		for (it.csi.gatefire.ca.aruba.actalisvol.Signer signer : inlist) {

			list.add(fillSigner(signer));
		}

		return list;
	}

	public static Signer fillSigner(it.csi.gatefire.ca.aruba.actalisvol.Signer inSigner) {
		Signer signer = null;
		if (inSigner != null) {
			signer = new Signer();

			signer.setValid(inSigner.getSignatureInfo().isSigValid());
			signer.setSignValidation(inSigner.getSigValidation());
			signer.setSignValidationMessage(inSigner.getSigValidationMessage());
			if (inSigner.getSignatureInfo().getSigTime() != null) {
				signer.setSignTime(inSigner.getSignatureInfo().getSigTime().toGregorianCalendar().getTime());
				signer.setSignTimeStr(signer.getSignTime().toString());
			}

			signer.setCertificate(fillCertificate(inSigner.getCertificate()));

			signer.setTimestamp(fillTimeStamp(inSigner.getTimeStamp()));

		}
		return signer;
	}

	public static SignatureInfo fillSignatureInfo(it.csi.gatefire.ca.aruba.actalisvol.SignatureInfo inSi) {
		SignatureInfo si = null;
		if (inSi != null) {
			si = new SignatureInfo();
			si.setCorrupted(inSi.isSigCorrupted());
			si.setSignAlgorithm(inSi.getSigAlgorithm());
			if (inSi.getSigTime() != null) {
				si.setSignTime(inSi.getSigTime().toGregorianCalendar().getTime());
			}
			si.setSignValue(inSi.getSigValue());
			si.setValid(inSi.isSigValid());
		}
		return si;

	}

	public static TimeStamp fillTimeStamp(SignerTimeStamp inTs) {
		TimeStamp ts = null;
		if (inTs != null) {
			ts = new TimeStamp();

			if (inTs.getTsGenTime() != null) {
				ts.setGenerationTime(inTs.getTsGenTime().toGregorianCalendar().getTime());
			}
			ts.setSerialNumber(inTs.getTsSerialNumber());

			ts.setSignValid(inTs.isTsSignVaild());
			ts.setTsAuthorityName(inTs.getTsTsaName());

		}
		return ts;
	}

	public static Certificate fillCertificate(it.csi.gatefire.ca.aruba.actalisvol.CertificateVOL inCert) {
		Certificate cert = null;
		if (inCert != null) {
			cert = new Certificate();
			cert.setCertPolicyList(fillPolicyList(inCert.getCertPolicy()));

			cert.setValid(inCert.isCertValid());
			cert.setTimeValid(inCert.isCertTimeValid());
			if (inCert.getCertValFrom() != null) {
				cert.setValidFrom(inCert.getCertValFrom().toGregorianCalendar().getTime());
			}
			if (inCert.getCertValUntil() != null) {
				cert.setValidTo(inCert.getCertValUntil().toGregorianCalendar().getTime());
			}
			cert.setName(inCert.getCertName());
			cert.setSn(inCert.getCertSerialNo());

			cert.setSubject(fillSubject(inCert.getCertSubject()));

			cert.setIssuer(fillIssuer(inCert.getCertIssuer()));
			cert.setRevocationInfo(fillrevocatioInfo(inCert.getCertRevocation()));

		}
		return cert;
	}

	public static List<CertPolicy> fillPolicyList(List<it.csi.gatefire.ca.aruba.actalisvol.CertPolicy> inList) {
		List<CertPolicy> list = new ArrayList<>();

		for (it.csi.gatefire.ca.aruba.actalisvol.CertPolicy certPolicy : inList) {
			list.add(fillPolicy(certPolicy));
		}

		return list;
	}

	public static CertPolicy fillPolicy(it.csi.gatefire.ca.aruba.actalisvol.CertPolicy inCert) {
		CertPolicy certPolicy = null;

		if (inCert != null) {
			certPolicy = new CertPolicy();
			certPolicy.setCpsUri(inCert.getCertCpsUri());
			certPolicy.setCpText(inCert.getCertPolText());
		}

		return certPolicy;
	}

	public static Subject fillSubject(CertSubject certSubject) {
		Subject subject = null;

		if (certSubject != null) {
			subject = new Subject();
			subject.setCodiceFiscale(certSubject.getCodiceFiscale());
			subject.setCommonName(certSubject.getCommonName());
			subject.setCountry(certSubject.getCountry());
			subject.setDistinguishName(certSubject.getSubjectDistinguishedName());
			subject.setOrganization(certSubject.getOrganization());
			subject.setRole(certSubject.getRole());
		}

		return subject;
	}

	public static Issuer fillIssuer(CertIssuer certIssuer) {
		Issuer issuer = null;

		if (certIssuer != null) {
			issuer = new Issuer();

			issuer.setDistinguishName(certIssuer.getIssuerDistinguishName());
			issuer.setName(certIssuer.getIssuerName());
			issuer.setPartitaIva(certIssuer.getIssuerPartitaIva());
		}

		return issuer;
	}

	public static RevocationInfo fillrevocatioInfo(CertRevocationInfo inRev) {
		RevocationInfo rev = null;
		if (inRev != null)

		{
			rev = new RevocationInfo();
			if (inRev.getRevocationDate() != null) {
				rev.setRevocationDate(inRev.getRevocationDate().toGregorianCalendar().getTime());
			}
			if (inRev.getCrlInfo() != null) {
				if (inRev.getCrlInfo().getExpiredCertsOnCrl() != null) {
					rev.setExpired(inRev.getCrlInfo().getExpiredCertsOnCrl().toGregorianCalendar().getTime());
				}
				if (inRev.getCrlInfo().getCrlThisUpdate() != null) {
					rev.setThisUpdate(inRev.getCrlInfo().getCrlThisUpdate().toGregorianCalendar().getTime());
				}
			}
		}

		return rev;
	}
}
