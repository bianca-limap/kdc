package kdc;

public class Alice {
	private String id_alice = "alice";
	private String k_alice = "k_alice";
	private int nonce = 100;

	/* 5 - Envia a mensagem em duas partes */
	public byte[] enviarNonce(byte[] k_alice_mensagem, String k_sessao) throws Throwable {
		if (!String.valueOf(k_alice_mensagem).equals(k_alice)) {
			return null;
		}

		return AES.cifra(String.valueOf(nonce), k_sessao);
	}

	/* 7 - Alice compara o nonce de Bob com o que foi previamente combinado entre ambos */
	public boolean validarNovoNonce(String k_sessao, byte[] novo_nonce) throws Throwable {
		int funcao = nonce + 1;
		boolean validacao = false;

		if (funcao == Integer.parseInt(new String(novo_nonce))) {
			validacao = true;
		}

		return validacao;
	}

	// GETTERS E SETTERS
	public String getK_alice() {
		return k_alice;
	}

	public String getId_alice() {
		return id_alice;
	}

	public int getNonce() {
		return nonce;
	}
}
