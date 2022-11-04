/*******************************************************************************
* Copyright Regione Piemonte - 2022
* SPDX-License-Identifier: EUPL-1.2
******************************************************************************/
package it.csi.gatefire.common.consts;

public class ParamsConsts {
	private ParamsConsts() {
		super();

	}

	public static final String PARAM_CA_ENDPOINT_ARSS = "ENDPOINT_ARSS";
	public static final String PARAM_CA_ENDPOINT_VOL = "ENDPOINT_VOL";
	public static final String PARAM_CA_ENDPOINT_PROXY_SIGN = "ENDPOINT_PROXY_SIGN";
	public static final String PARAM_CA_ENDPOINT_PROXY_SIGN_VERIFY = "ENDPOINT_PROXY_SIGN_VERIFY";

	public static final String PARAM_GEN_VERIFICATION_SERVICE = "VERIFICATION_SERVICE";
	public static final String PARAM_GEN_ENDPOINT_GATEWAY = "ENDPOINT_GATEWAY";

	public static final String PARAM_CA_ARUBA_SIGNATURE_LEVEL = "signatureLevel";
	public static final String PARAM_CA_ARUBA_SIGNATURE_LEVEL_DEFAULT = "LT";
	public static final String PARAM_CA_ARUBA_CERT_ID = "certID";
	public static final String PARAM_CA_ARUBA_CERT_ID_DEFAULT = "AS0";
	public static final String PARAM_CA_ARUBA_PROFILE = "profile";
	public static final String PARAM_CA_ARUBA_PROFILE_DEFAULT = null;
	public static final String PARAM_CA_ARUBA_TYPE_HSM = "auth.cosign";
	public static final String PARAM_CA_ARUBA_TYPE_HSM_DEFAULT = "COSIGN";

	public static final String PARAM_CA_ARUBA_TYPE_OTP_AUTH_DEFAULT = null;

	public static final String PARAM_CA_ARUBA_OTP_DOMAIN = "identity.otpDomain";
	public static final String PARAM_CA_ARUBA_OTP_AUTOMATICA = "identity.otpAutomatica";
	public static final String PARAM_CA_ARUBA_DELEGATED_DOMAIN = "identity.delegatedDomain";
	public static final String PARAM_CA_ARUBA_OTP = "identity.otp";

	public static final String PARAM_CA_LAYOUT_LOW_LEFT_X = "signLayout.lowLeftX";
	public static final String PARAM_CA_LAYOUT_LOW_LEFT_Y = "signLayout.lowLeftY";
	public static final String PARAM_CA_LAYOUT_UP_RIGHT_X = "signLayout.upRightX";
	public static final String PARAM_CA_LAYOUT_UP_RIGHT_Y = "signLayout.upRightY";
	public static final String PARAM_CA_LAYOUT_PAGE = "signLayout.page";

	

	public static final String TAG_CA_ARUBA = "ARUBA";
	public static final String TAG_CA_INFOCERT = "INFOCERT";

	public static final String PARAM_MAX_FILE_SIZE = "MAX_FILE_SIZE";
	public static final String PARAM_MAX_N_FILE_MASSIVE = "MAX_N_FILES_MASSIVE";
	public static final String PARAM_MAX_FILE_SIZE_MASSIVE = "MAX_FILE_SIZE_MASSIVE";

}
