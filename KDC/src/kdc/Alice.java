package kdc;

public class Alice {
	private String k_alice = "k_bobk_bobk_bob_";
	private String nonce = "100";

	/* 5 - Envia a mensagem em duas partes */
	public byte[] enviarNonce(byte[] k_mensagem02, byte[] k_sessao) throws Throwable {
		String mensagem02_decifra = AES.decifra(k_mensagem02, k_alice);
		String k_sessao_decifra = AES.decifra(k_sessao, k_alice);

		if (!mensagem02_decifra.equals(k_sessao_decifra)) {
			return null;
		}

		System.out.println("ALICE - NONCE: " + nonce);
		return AES.cifra(nonce, k_sessao_decifra);
	}

	/* 7 - Alice compara o nonce de Bob com o que foi previamente combinado entre ambos */
	public boolean validarNovoNonce(byte[] k_sessao, byte[] novo_nonce) throws Throwable {
		String k_sessao_decifra = AES.decifra(k_sessao, k_alice);
		String novo_nonce_decifra = AES.decifra(novo_nonce, k_sessao_decifra);

		String funcao = nonce + 1; // Verifica se o nonce está concatenado. Ex.: 1001
		boolean validacao = false;

		if (funcao.equals(novo_nonce_decifra)) {
			validacao = true;
		}

		return validacao;
	}
}