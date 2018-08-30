package kdc;

public class Kdc {
	private String k_alice = "k_alice";
	private String k_bob = "k_bob";

	/* 2 */
	public byte[] gerarSessao(String id, byte[] id_cifrado, byte[] destinatario) throws Throwable {
		String id_decifrado = AES.decifra(id_cifrado, k_bob);

		// Verifica se o solicitante é realmente o Bob
		if (!id_decifrado.equals(id)) {
			return null;
		}

		// Gera a sessão 
		return AES.cifra(destinatario, k_bob);
	}

	/* 3 - Envia a mensagem em duas partes */
	public byte[] enviarMensagemParte01(String k_sessao) throws Throwable {
		return AES.cifra(k_sessao, k_bob);
	}

	/* 3.1 - Como o Bob não tem a k_alice decifrada, ele encaminha para a Alice a mesma mensagem */
	public byte[] enviarMensagemParte02(String k_sessao) throws Throwable {
		return AES.cifra(k_sessao, k_alice);
	}
}
