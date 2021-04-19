package estados;

import contexto.Pessoa;

public class Finalizado implements Estado {

	@Override
	public void atualiza(Pessoa pessoa, int idadeMinima) {}

	@Override
	public void vacina(Pessoa pessoa) {}
	
	@Override
	public String toString() {
		return "Tomou 2ª dose. Vacinação finalizada";
	}

}
