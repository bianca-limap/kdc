import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class Kdc {

	private String k_alice = "alice";
	private String k_bob = "bob";

	public String gerarSessao(String id, byte[] id_cifrado, byte[] destinatario)
			throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, NoSuchAlgorithmException,
			NoSuchPaddingException, UnsupportedEncodingException {

		byte[] sessao = AES.cifra(destinatario, k_bob);

		return new String(sessao);

	}

}
