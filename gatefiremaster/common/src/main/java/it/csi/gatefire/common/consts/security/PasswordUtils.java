package it.csi.gatefire.common.consts.security;

import org.springframework.security.crypto.encrypt.Encryptors;
import org.springframework.security.crypto.encrypt.TextEncryptor;
import org.springframework.security.crypto.keygen.KeyGenerators;

public class PasswordUtils {

	public static final String PSW = "xxxxxxxxxxxxxxxxxxxxxxxx";

	public static String generateSalt() {

		return KeyGenerators.string().generateKey();

	}

	public static String encrypt(String psw, String salt) {
		TextEncryptor te = Encryptors.text(PSW, salt);

		return te.encrypt(psw);

	}

	public static String decrypt(String encryptedPsw, String salt) {
		TextEncryptor te = Encryptors.text(PSW, salt);

		return te.decrypt(encryptedPsw);
	}

	private PasswordUtils() {
		super();

	}

}
