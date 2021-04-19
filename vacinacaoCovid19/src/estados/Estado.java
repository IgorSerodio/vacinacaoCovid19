package estados;

import contexto.Pessoa;

public interface Estado {

	void atualiza(Pessoa pessoa, int idadeMinima);

	void vacina(Pessoa pessoa);

}
