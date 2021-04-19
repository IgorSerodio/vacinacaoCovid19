package baseDeDados;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

//Abstração de um banco de dados com informações de pessoas no Brasil. Na prática gera dados aleatórios. 
public class BancoDeDadosDoGoverno {
	
	Map<String, LocalDate> datasDeNascimento = new HashMap<String, LocalDate>();
	
	public LocalDate getDataDeNascimento(String cpf) {
		if(datasDeNascimento.containsKey(cpf)) {
			return datasDeNascimento.get(cpf);
		}
		LocalDate data = LocalDate.now();
		LocalDate nascimento = data.minusDays((long)Math.random()*150*365);
		this.datasDeNascimento.put(cpf, nascimento);
		return nascimento;
	}
	
}
