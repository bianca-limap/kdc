
public class Principal {

	public static void main(String[] args) {
		Kdc kdc = new Kdc();
		Bob bob = new Bob();
		Alice alice = new Alice();
		
		bob.getIdBob();
		//kdc.gerarSessao(bob.getIdBob(), bob.getK_bob(), alice);
	}

}
