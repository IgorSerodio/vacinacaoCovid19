package estados;

import contexto.Pessoa;

public class Habilitado2Dose implements Estado {

	@Override
	public void atualiza(Pessoa pessoa, int idadeMinima) {}

	@Override
	public void vacina(Pessoa pessoa) {
		pessoa.mudaEstado(new Finalizado());
	}

	@Override
	public String toString() {
		return "Habilitada para tomar 2ª dose";
	}
}
