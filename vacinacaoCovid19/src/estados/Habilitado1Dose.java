package estados;

import contexto.Pessoa;

public class Habilitado1Dose implements Estado {
	
	@Override
	public void atualiza(Pessoa pessoa, int idadeMinima) {}

	@Override
	public void vacina(Pessoa pessoa) {
		pessoa.mudaEstado(new Tomou1Dose());
	}

	@Override
	public String toString() {
		return "Habilitada para tomar 1ª dose";
	}
}
