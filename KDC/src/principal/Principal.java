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

		byte[] k_sessao = kdc.gerarSessao(bob.getId_bob(), id_cifrado, "SESSAO_BOB_ALICE");

		byte[] mensagem01 = kdc.enviarMensagemParte01(k_sessao);
		byte[] mensagem02 = kdc.enviarMensagemParte02(k_sessao);

		byte[] k_mensagem02 = bob.reencaminharMensagem(k_sessao, mensagem01, mensagem02);

		byte[] nonce = alice.enviarNonce(k_mensagem02, k_sessao);

		byte[] novo_nonce = bob.enviarNovoNonce(k_sessao, nonce);

		boolean validar_nonce = alice.validarNovoNonce(k_sessao, novo_nonce);

		if (validar_nonce) {
			System.out.println("RESULTADO FINAL: Nonce válido!");
		}
		else {
			System.out.println("RESULTADO FINAL: Nonce inválido!");
		}
	}
}
