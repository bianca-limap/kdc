package kdc;

public class Kdc {
	private String k_alice = "k_bobk_bobk_bob_";
	private String k_bob = "k_bobk_bobk_bob_";

	/* 2 */
	public byte[] gerarSessao(String id, byte[] id_cifrado, String destinatario) throws Throwable {
		String id_decifrado = AES.decifra(id_cifrado, k_bob);

		// Verifica se o solicitante � realmente o Bob
		if (!id_decifrado.equals(id)) {
			return null;
		}

		// Gera a sess�o 
		System.out.println("KDC - K_SESS�O: " + destinatario);
		return AES.cifra(destinatario, k_bob);
	}

	/* 3 - Envia a mensagem em duas partes */
	public byte[] enviarMensagemParte01(byte[] k_sessao) throws Throwable {
		String k_sessao_decifrado = AES.decifra(k_sessao, k_bob);

		System.out.println("KDC - MENSAGEM 01: " + k_sessao_decifrado);
		return AES.cifra(k_sessao_decifrado, k_bob);
	}

	/* 3.1 - Como o Bob n�o tem a k_alice decifrada, ele encaminhar� para a Alice essa mesma mensagem */
	public byte[] enviarMensagemParte02(byte[] k_sessao) throws Throwable {
		String k_sessao_decifrado = AES.decifra(k_sessao, k_bob);

		System.out.println("KDC - MENSAGEM 02: " + k_sessao_decifrado);
		return AES.cifra(k_sessao_decifrado, k_alice);
	}
}
