package principal;

import kdc.Alice;
import kdc.Bob;
import kdc.Kdc;

public class Principal {

	public static void main(String[] args) throws Throwable {
		Kdc kdc = new Kdc();
		Bob bob = new Bob();
		Alice alice = new Alice();

		byte[] id_cifrado = bob.enviarIdCifrado();
		System.out.println("ID CIFRADO: " + new String(id_cifrado));

		String k_sessao = String.valueOf(kdc.gerarSessao(bob.getId_bob(), id_cifrado, alice.getId_alice().getBytes()));
		System.out.println("K_SESSÃO: " + new String(k_sessao));

		byte[] k_bob = kdc.enviarMensagemParte01(k_sessao);
		byte[] k_alice = kdc.enviarMensagemParte02(k_sessao);
		System.out.println("MENSAGEM 01: " + new String(k_bob));
		System.out.println("MENSAGEM 02: " + new String(k_alice));

		byte[] k_alice_mensagem = bob.reencaminharMensagem(k_sessao, k_bob, k_alice);
		System.out.println("MENSAGEM ENCAMINHADA: " + new String(k_alice_mensagem));

		byte[] nonce = alice.enviarNonce(k_alice_mensagem, k_sessao);
		System.out.println("NONCE: " + new String(nonce));

		byte[] novo_nonce = bob.enviarNovoNonce(k_sessao, nonce);
		System.out.println("NOVO NONCE: " + new String(novo_nonce));

		boolean validar_nonce = alice.validarNovoNonce(k_sessao, novo_nonce);
		System.out.println("VALIDAR NONCE: " + validar_nonce);
	}
}
