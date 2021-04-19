package estados;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import contexto.Pessoa;

public class Tomou1Dose implements Estado {
	
	private LocalDate dataDaVacina;
	
	public Tomou1Dose() {
		this.dataDaVacina = LocalDate.now();
	}

	@Override
	public void atualiza(Pessoa pessoa, int idadeMinima) {
		if(ChronoUnit.DAYS.between(this.dataDaVacina, LocalDate.now()) >= 20) {
			pessoa.mudaEstado(new Habilitado2Dose());
		}
	}

	@Override
	public void vacina(Pessoa pessoa) {}
	
	@Override
	public String toString() {
		return "Tomou 1ª dose";
	}

}
