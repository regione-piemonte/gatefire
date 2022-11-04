/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.gateway.service.ws;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.csi.gatefire.common.model.Attachment;
import it.csi.gatefire.common.model.AutoSignIdentity;
import it.csi.gatefire.common.model.CallInfo;
import it.csi.gatefire.common.model.FileResult;
import it.csi.gatefire.common.model.Identity;
import it.csi.gatefire.common.model.MarkIdentity;
import it.csi.gatefire.common.model.MarkInput;
import it.csi.gatefire.common.model.MarkType;
import it.csi.gatefire.common.model.MarkVerifyResult;
import it.csi.gatefire.common.model.OtpReqInput;
import it.csi.gatefire.common.model.OtpResult;
import it.csi.gatefire.common.model.PadesInput;
import it.csi.gatefire.common.model.PingResult;
import it.csi.gatefire.common.model.Result;
import it.csi.gatefire.common.model.SessionIdResult;
import it.csi.gatefire.common.model.SessionInput;
import it.csi.gatefire.common.model.SignIdentity;
import it.csi.gatefire.common.model.SignLayout;
import it.csi.gatefire.common.model.SignVerifyResult;
import it.csi.gatefire.common.util.FileUtils;
import it.csi.gatefire.dbhelper.service.BaseService;

@Service("gateFireReatWSSrvc")
public class GateFireRestWSSrvcImpl extends BaseService implements GateFireRestWSSrvc {

	@Autowired
	private GateFireWSSrvc gateFireWSSrvc;

	@Override
	public PingResult ping(String user, CallInfo callInfo) {

		return gateFireWSSrvc.ping(user, callInfo);
	}

	@Override
	public SessionIdResult openSession(SessionInput sessionInput) {
		return gateFireWSSrvc.openSession(sessionInput);
	}

	@Override
	public Result closeSession(String sessionId, SessionInput sessionInput) {
		return gateFireWSSrvc.closeSession(sessionId, sessionInput);
	}

	@Override
	public FileResult firmaPAdES(org.apache.cxf.jaxrs.ext.multipart.Attachment file, String applicationCode,
			String caCode, String codiceFiscale, String collocazione, String dominio, Integer page, Integer lowLeftX,
			Integer lowLeftY, Integer upRightX, Integer upRightY, String reason, String text, Boolean showDateTime,
			org.apache.cxf.jaxrs.ext.multipart.Attachment image, Boolean requiredMark, String markPassword,
			String markUser, String otp, String password, String user) {
		Attachment attachment = new Attachment();

		if (file != null) {
			try {
				attachment.setFile(FileUtils.dataHandlerToBytes(file.getDataHandler()));
				attachment.setFileName(file.getDataHandler().getName());
			} catch (IOException e) {

				logError(e);
			}
		}
		PadesInput padesInput = fillPadesInput(applicationCode, caCode, codiceFiscale, collocazione, dominio, page,
				lowLeftX, lowLeftY, upRightX, upRightY, reason, text, showDateTime, image, requiredMark, markPassword,
				markUser);

		Identity identity = new Identity();
		identity.setOtp(otp);
		identity.setPassword(password);
		identity.setUser(user);

		return gateFireWSSrvc.firmaPAdES(attachment, padesInput, identity);
	}

	@Override
	public FileResult firmaPAdESRemota(org.apache.cxf.jaxrs.ext.multipart.Attachment file, String applicationCode,
			String caCode, String codiceFiscale, String collocazione, String dominio, Integer page, Integer lowLeftX,
			Integer lowLeftY, Integer upRightX, Integer upRightY, String reason, String text, Boolean showDateTime,
			org.apache.cxf.jaxrs.ext.multipart.Attachment image, Boolean requiredMark, String markPassword,
			String markUser, String otp, String password, String user, String sessionId) {

		Attachment attachment = new Attachment();

		if (file != null) {
			try {
				attachment.setFile(FileUtils.dataHandlerToBytes(file.getDataHandler()));
				attachment.setFileName(file.getDataHandler().getName());
			} catch (IOException e) {

				logError(e);
			}
		}

		PadesInput padesInput = fillPadesInput(applicationCode, caCode, codiceFiscale, collocazione, dominio, page,
				lowLeftX, lowLeftY, upRightX, upRightY, reason, text, showDateTime, image, requiredMark, markPassword,
				markUser);

		SignIdentity identity = new SignIdentity();
		identity.setOtp(otp);
		identity.setPassword(password);
		identity.setUser(user);
		identity.setSessionId(sessionId);
		return gateFireWSSrvc.firmaPAdESRemota(attachment, padesInput, identity);
	}

	@Override
	public FileResult firmaPAdESAutomatica(org.apache.cxf.jaxrs.ext.multipart.Attachment file, String applicationCode,
			String caCode, String codiceFiscale, String collocazione, String dominio, Integer page, Integer lowLeftX,
			Integer lowLeftY, Integer upRightX, Integer upRightY, String reason, String text, Boolean showDateTime,
			org.apache.cxf.jaxrs.ext.multipart.Attachment image, Boolean requiredMark, String markPassword,
			String markUser, String otp, String password, String user, String delegatedDomain, String delegatedPassword,
			String delegatedUser) {
		Attachment attachment = new Attachment();

		if (file != null) {
			try {
				attachment.setFile(FileUtils.dataHandlerToBytes(file.getDataHandler()));
				attachment.setFileName(file.getDataHandler().getName());
			} catch (IOException e) {

				logError(e);
			}
		}

		PadesInput padesInput = fillPadesInput(applicationCode, caCode, codiceFiscale, collocazione, dominio, page,
				lowLeftX, lowLeftY, upRightX, upRightY, reason, text, showDateTime, image, requiredMark, markPassword,
				markUser);

		AutoSignIdentity identity = new AutoSignIdentity();
		identity.setOtp(otp);
		identity.setPassword(password);
		identity.setUser(user);
		identity.setDelegatedDomain(delegatedDomain);
		identity.setDelegatedPassword(delegatedPassword);
		identity.setDelegatedUser(delegatedUser);

		return gateFireWSSrvc.firmaPAdESAutomatica(attachment, padesInput, identity);
	}

	@Override
	public FileResult firmaPAdESMassiva(List<org.apache.cxf.jaxrs.ext.multipart.Attachment> attachmentList,
			String applicationCode, String caCode, String codiceFiscale, String collocazione, String dominio,
			Integer page, Integer lowLeftX, Integer lowLeftY, Integer upRightX, Integer upRightY, String reason,
			String text, Boolean showDateTime, org.apache.cxf.jaxrs.ext.multipart.Attachment image,
			Boolean requiredMark, String markPassword, String markUser, String otp, String password, String user) {
		List<Attachment> list = new ArrayList<>();
		if (attachmentList != null) {
			for (org.apache.cxf.jaxrs.ext.multipart.Attachment file : attachmentList) {
				Attachment attachment = new Attachment();

				if (file != null) {
					try {
						attachment.setFile(FileUtils.dataHandlerToBytes(file.getDataHandler()));
						attachment.setFileName(file.getDataHandler().getName());
					} catch (IOException e) {

						logError(e);
					}
				}
				list.add(attachment);
			}
		}

		PadesInput padesInput = fillPadesInput(applicationCode, caCode, codiceFiscale, collocazione, dominio, page,
				lowLeftX, lowLeftY, upRightX, upRightY, reason, text, showDateTime, image, requiredMark, markPassword,
				markUser);

		Identity identity = new Identity();
		identity.setOtp(otp);
		identity.setPassword(password);
		identity.setUser(user);

		return gateFireWSSrvc.firmaPAdESMassiva(list, padesInput, identity);
	}

	@Override
	public FileResult firmaPAdESMassivaRemota(List<org.apache.cxf.jaxrs.ext.multipart.Attachment> attachmentList,
			String applicationCode, String caCode, String codiceFiscale, String collocazione, String dominio,
			Integer page, Integer lowLeftX, Integer lowLeftY, Integer upRightX, Integer upRightY, String reason,
			String text, Boolean showDateTime, org.apache.cxf.jaxrs.ext.multipart.Attachment image,
			Boolean requiredMark, String markPassword, String markUser, String otp, String password, String user,
			String sessionId) {
		List<Attachment> list = new ArrayList<>();
		if (attachmentList != null) {
			for (org.apache.cxf.jaxrs.ext.multipart.Attachment file : attachmentList) {
				Attachment attachment = new Attachment();

				if (file != null) {
					try {
						attachment.setFile(FileUtils.dataHandlerToBytes(file.getDataHandler()));
						attachment.setFileName(file.getDataHandler().getName());
					} catch (IOException e) {

						logError(e);
					}
				}
				list.add(attachment);
			}
		}

		PadesInput padesInput = fillPadesInput(applicationCode, caCode, codiceFiscale, collocazione, dominio, page,
				lowLeftX, lowLeftY, upRightX, upRightY, reason, text, showDateTime, image, requiredMark, markPassword,
				markUser);
		SignIdentity identity = new SignIdentity();
		identity.setOtp(otp);
		identity.setPassword(password);
		identity.setUser(user);
		identity.setSessionId(sessionId);

		return gateFireWSSrvc.firmaPAdESMassivaRemota(list, padesInput, identity);
	}

	@Override
	public FileResult firmaPAdESMassivaAutomatica(List<org.apache.cxf.jaxrs.ext.multipart.Attachment> attachmentList,
			String applicationCode, String caCode, String codiceFiscale, String collocazione, String dominio,
			Integer page, Integer lowLeftX, Integer lowLeftY, Integer upRightX, Integer upRightY, String reason,
			String text, Boolean showDateTime, org.apache.cxf.jaxrs.ext.multipart.Attachment image,
			Boolean requiredMark, String markPassword, String markUser, String otp, String password, String user,
			String delegatedDomain, String delegatedPassword, String delegatedUser) {
		List<Attachment> list = new ArrayList<>();
		if (attachmentList != null) {
			for (org.apache.cxf.jaxrs.ext.multipart.Attachment file : attachmentList) {
				Attachment attachment = new Attachment();

				if (file != null) {
					try {
						attachment.setFile(FileUtils.dataHandlerToBytes(file.getDataHandler()));
						attachment.setFileName(file.getDataHandler().getName());
					} catch (IOException e) {

						logError(e);
					}
				}
				list.add(attachment);
			}
		}

		PadesInput padesInput = fillPadesInput(applicationCode, caCode, codiceFiscale, collocazione, dominio, page,
				lowLeftX, lowLeftY, upRightX, upRightY, reason, text, showDateTime, image, requiredMark, markPassword,
				markUser);

		AutoSignIdentity identity = new AutoSignIdentity();
		identity.setOtp(otp);
		identity.setPassword(password);
		identity.setUser(user);
		identity.setDelegatedDomain(delegatedDomain);
		identity.setDelegatedPassword(delegatedPassword);
		identity.setDelegatedUser(delegatedUser);

		return gateFireWSSrvc.firmaPAdESMassivaAutomatica(list, padesInput, identity);
	}

	@Override
	public FileResult marcaTemporale(org.apache.cxf.jaxrs.ext.multipart.Attachment file, String applicationCode,
			String caCode, String codiceFiscale, String collocazione, String dominio, String markPassword,
			String markUser, MarkType markType) {

		Attachment attachment = new Attachment();

		if (file != null) {
			try {
				attachment.setFile(FileUtils.dataHandlerToBytes(file.getDataHandler()));
				attachment.setFileName(file.getDataHandler().getName());
			} catch (IOException e) {

				logError(e);
			}
		}
		MarkInput markInput = new MarkInput();
		CallInfo callInfo = fillCalliInfo(applicationCode, caCode, codiceFiscale, collocazione, dominio);
		markInput.setCallInfo(callInfo);
		MarkIdentity markIdentity = new MarkIdentity();
		markIdentity.setUser(markUser);
		markIdentity.setPassword(markPassword);
		markInput.setMarkIdentity(markIdentity);
		if (markType != null) {
			markInput.setMarkType(markType);
		}

		return gateFireWSSrvc.marcaTemporale(attachment, markInput);
	}

	@Override
	public SignVerifyResult verificaFirma(org.apache.cxf.jaxrs.ext.multipart.Attachment file, String applicationCode,
			String caCode, String codiceFiscale, String collocazione, String dominio) {
		Attachment attachment = new Attachment();

		if (file != null) {
			try {
				attachment.setFile(FileUtils.dataHandlerToBytes(file.getDataHandler()));
				attachment.setFileName(file.getDataHandler().getName());
			} catch (IOException e) {

				logError(e);
			}
		}
		CallInfo callInfo = fillCalliInfo(applicationCode, caCode, codiceFiscale, collocazione, dominio);

		return gateFireWSSrvc.verificaFirma(attachment, callInfo);
	}

	@Override
	public MarkVerifyResult verificaMarca(org.apache.cxf.jaxrs.ext.multipart.Attachment file, String applicationCode,
			String caCode, String codiceFiscale, String collocazione, String dominio) {
		Attachment attachment = new Attachment();

		if (file != null) {
			try {
				attachment.setFile(FileUtils.dataHandlerToBytes(file.getDataHandler()));
				attachment.setFileName(file.getDataHandler().getName());
			} catch (IOException e) {

				logError(e);
			}
		}
		CallInfo callInfo = fillCalliInfo(applicationCode, caCode, codiceFiscale, collocazione, dominio);

		return gateFireWSSrvc.verificaMarca(attachment, callInfo);
	}

	@Override
	public MarkVerifyResult verificaMarcaDetached(org.apache.cxf.jaxrs.ext.multipart.Attachment mark,
			org.apache.cxf.jaxrs.ext.multipart.Attachment file, String applicationCode, String caCode,
			String codiceFiscale, String collocazione, String dominio) {
		Attachment markAtt = new Attachment();

		if (mark != null) {
			try {
				markAtt.setFile(FileUtils.dataHandlerToBytes(mark.getDataHandler()));
				markAtt.setFileName(mark.getDataHandler().getName());
			} catch (IOException e) {

				logError(e);
			}
		}

		Attachment attachment = new Attachment();

		if (file != null) {
			try {
				attachment.setFile(FileUtils.dataHandlerToBytes(file.getDataHandler()));
				attachment.setFileName(file.getDataHandler().getName());
			} catch (IOException e) {

				logError(e);
			}
		}
		CallInfo callInfo = fillCalliInfo(applicationCode, caCode, codiceFiscale, collocazione, dominio);

		return gateFireWSSrvc.verificaMarcaDetached(markAtt, attachment, callInfo);
	}

	private PadesInput fillPadesInput(String applicationCode, String caCode, String codiceFiscale, String collocazione,
			String dominio, Integer page, Integer lowLeftX, Integer lowLeftY, Integer upRightX, Integer upRightY,
			String reason, String text, Boolean showDateTime, org.apache.cxf.jaxrs.ext.multipart.Attachment image,
			Boolean requiredMark, String markPassword, String markUser) {
		PadesInput padesInput = new PadesInput();
		if (requiredMark != null) {
			padesInput.setRequiredMark(requiredMark);
		}

		CallInfo callInfo = fillCalliInfo(applicationCode, caCode, codiceFiscale, collocazione, dominio);

		padesInput.setCallInfo(callInfo);

		SignLayout signLayout = fillSignLayout(page, lowLeftX, lowLeftY, upRightX, upRightY, reason, text, showDateTime,
				image);
		padesInput.setSignLayout(signLayout);

		MarkIdentity markIdentity = new MarkIdentity();
		markIdentity.setUser(markUser);
		markIdentity.setPassword(markPassword);
		padesInput.setMarkIdentity(markIdentity);

		return padesInput;
	}

	private SignLayout fillSignLayout(Integer page, Integer lowLeftX, Integer lowLeftY, Integer upRightX,
			Integer upRightY, String reason, String text, Boolean showDateTime,
			org.apache.cxf.jaxrs.ext.multipart.Attachment image) {
		SignLayout signLayout = new SignLayout();
		if (image != null) {
			try {
				signLayout.setImage(FileUtils.dataHandlerToBytes(image.getDataHandler()));
			} catch (IOException e) {

				logError(e);
			}
		}
		signLayout.setLowLeftX(lowLeftX);
		signLayout.setLowLeftY(lowLeftY);
		signLayout.setPage(page);
		signLayout.setReason(reason);
		if (showDateTime != null) {
			signLayout.setShowDateTime(showDateTime);
		}
		signLayout.setText(text);
		signLayout.setUpRightX(upRightX);
		signLayout.setUpRightY(upRightY);

		return signLayout;
	}

	private CallInfo fillCalliInfo(String applicationCode, String caCode, String codiceFiscale, String collocazione,
			String dominio) {

		CallInfo callInfo = new CallInfo();
		callInfo.setApplicationCode(applicationCode);
		callInfo.setCaCode(caCode);
		callInfo.setCodiceFiscale(codiceFiscale);
		callInfo.setCollocazione(collocazione);
		callInfo.setDominio(dominio);

		return callInfo;
	}

	@Override
	public OtpResult richiestaOtp(OtpReqInput otpReqInput) {
		return gateFireWSSrvc.richiestaOtp(otpReqInput);
	}

	/*
	@Override
	public Result archivia(org.apache.cxf.jaxrs.ext.multipart.Attachment file, String user, String password,
			String codiceFiscale, String collocazione, List<String> accessionNumber, String assettoOrganizzativo,
			String codiceDocumentoScaricabile, List<Medico> medicoRedattore, String formatCode) {
		// TODO Auto-generated method stub
		return null;
	}*/

	

	

}
