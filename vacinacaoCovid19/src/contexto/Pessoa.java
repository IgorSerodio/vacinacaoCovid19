package contexto;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import estados.Estado;
import estados.NaoHabilitada;

public class Pessoa {
	
	private String nome;
	private String cpf;
	private String endereco;
	private String numeroCartaoSus;
	private String email;
	private String telefone;
	private String profissao;
	private List<String> comorbidades;
	private Estado estado;
	private SistemaDoSus sistema;

	public Pessoa(String nome, String cpf, String endereco, String numeroCartaoSus, String email, String telefone,
			String profissao, List<String> comorbidades, SistemaDoSus sistema) {
		this.nome = nome;
		this.cpf = cpf;
		this.endereco = endereco;
		this.numeroCartaoSus = numeroCartaoSus;
		this.email = email;
		this.telefone = telefone;
		this.profissao = profissao;
		this.comorbidades = comorbidades;
		this.sistema = sistema;
		this.estado = new NaoHabilitada();
		atualiza();
	}
	
	public SistemaDoSus getSistema() {
		return this.sistema;
	}
	
	public long getIdade() {
		LocalDate hoje = LocalDate.now();
		LocalDate nascimento = this.sistema.getBancoDeDados().getDataDeNascimento(this.cpf);
		
		return ChronoUnit.YEARS.between(nascimento, hoje);
	}
	
	public boolean temComorbidadeValida() {
		for(String comorbidade: comorbidades) {
			if(this.sistema.ehComorbidadeValida(comorbidade)) {
				return true;
			}
		}
		
		return false;
	}
	
	public boolean temProfissaoValida() {
		return this.sistema.ehProfissaoValida(this.profissao);
	}

	public void atualiza() {
		this.estado.atualiza(this, this.sistema.getIdadeMinima());
	}
	
	public void mudaEstado(Estado estado) {
		this.estado = estado;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
	}

	public void adicionaComorbidade(String comorbidade) {
		this.comorbidades.add(comorbidade);
	}
	
	@Override
	public int hashCode() {
		return this.cpf.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if(o == null) {
			return false;
		}
		
		if(o == this) {
			return true;
		}
		
		if(!(o instanceof Pessoa)) {
			return false;
		}
		
		Pessoa outra = (Pessoa) o;
		
		if(this.cpf.equals(outra.cpf)) {
			return true;
		}
		
		return false;
	}
	
	@Override
	public String toString() {
		return "Nome: " + nome + ", CPF: " + cpf + ", Número do cartão do SUS: " + numeroCartaoSus + ", E-mail: " + 
	           email + ", Telefone: " + telefone + ", Endereço: " + endereco + ", Profissão: " + profissao + " - " 
				+ estado.toString();
	}

	public void vacina() {
		atualiza();
		this.estado.vacina(this);
	}
}
