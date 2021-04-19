package estados;

import contexto.Pessoa;

public class NaoHabilitada implements Estado {

	@Override
	public void atualiza(Pessoa pessoa, int idadeMinima) {
		if(pessoa.getIdade() >= idadeMinima || pessoa.temComorbidadeValida() || pessoa.temProfissaoValida()) {
			pessoa.mudaEstado(new Habilitado1Dose());
		}
	}

	@Override
	public void vacina(Pessoa pessoa) {}
	
	@Override
	public String toString() {
		return "Não habilitada para vacina";
	}
}
