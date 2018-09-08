package kdc;

public class Bob {
	private String id_bob = "bob";
	private String k_bob = "k_bobk_bobk_bob_";

	/* 1 - Envia o id cifrado para solicitar nova sessão */
	public byte[] enviarIdCifrado() throws Throwable {
		System.out.println("BOB - ID CIFRADO: " + id_bob);
		return AES.cifra(id_bob, k_bob);
	}

	/* 4 - Reencaminha a segunda mensagem para a Alice */
	public byte[] reencaminharMensagem(byte[] k_sessao, byte[] mensagem01, byte[] mensagem02) throws Throwable {
		String mensagem01_decifra = AES.decifra(mensagem01, k_bob);
		String k_sessao_decifra = AES.decifra(k_sessao, k_bob);

		// Recebe a mensagem01 do KDC
		if (!mensagem01_decifra.equals(k_sessao_decifra)) {
			return null;
		}

		// Reencaminha a mensagem02 para a Alice exatamente como foi recebida
		System.out.println("BOB - MENSAGEM ENCAMINHADA (CIFRADA): " + new String(mensagem02));
		return mensagem02;
	}

	/* 6 - O Bob recebe o nonce da Alice e gera um novo nonce */
	public byte[] enviarNovoNonce(byte[] k_sessao, byte[] nonce) throws Throwable {
		String k_sessao_decifra = AES.decifra(k_sessao, k_bob);
		String nonce_decifra = AES.decifra(nonce, k_sessao_decifra);

		String novo_nonce = nonce_decifra + 1; // Concatena o nonce. Ex.: 1001

		System.out.println("BOB - NOVO NONCE: " + novo_nonce);
		return AES.cifra(novo_nonce, k_sessao_decifra);
	}

	// GETTERS E SETTERS
	public String getId_bob() {
		return id_bob;
	}
}