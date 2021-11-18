package br.com.diario_de_saude.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public abstract class Encrypt {

	public static String password(String senha) throws NoSuchAlgorithmException {
		MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
		messageDigest.update(senha.getBytes());
		return new String(messageDigest.digest());
	}
}
