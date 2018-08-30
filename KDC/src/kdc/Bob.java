package kdc;

public class Bob {
	private String id_bob = "bob";
	private String k_bob = "k_bob";

	/* 1 - Envia o id cifrado para solicitar nova sessão */
	public byte[] enviarIdCifrado() throws Throwable {
		return AES.cifra(id_bob, k_bob);
	}

	/* 4 - Reencaminha a segunda mensagem para a Alice */
	public byte[] reencaminharMensagem(String k_sessao, byte[] k_bob, byte[] k_alice) throws Throwable {
		// Recebe a mensagem01 do KDC
		if (!k_bob.equals(this.k_bob.getBytes())) {
			return null;
		}

		// Reencaminha a mensagem02 para a Alice
		return AES.cifra(k_sessao, String.valueOf(k_alice));
	}

	/* 6 - O Bob recebe o nonce da Alice e gera um novo nonce */
	public byte[] enviarNovoNonce(String k_sessao, byte[] nonce) throws Throwable {
		int novo_nonce = Integer.parseInt(new String(nonce)) + 1;

		return AES.cifra(String.valueOf(novo_nonce), k_sessao);
	}

	// GETTERS E SETTERS
	public String getId_bob() {
		return id_bob;
	}

	public String getK_bob() {
		return k_bob;
	}
}