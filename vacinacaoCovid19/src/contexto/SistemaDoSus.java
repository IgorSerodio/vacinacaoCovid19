package contexto;


import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import baseDeDados.BancoDeDadosDoGoverno;

public class SistemaDoSus {
	private Map<String, Pessoa> pessoas;
	private Set<String> comorbidadesValidas;
	private Set<String> profissoesValidas;
	private BancoDeDadosDoGoverno bancoDeDados;
	private int idadeMinima;
	
	public SistemaDoSus(BancoDeDadosDoGoverno bancoDeDados, int idadeMinimaInicial){
		this.pessoas = new HashMap<String, Pessoa>();
		this.comorbidadesValidas = new HashSet<String>();
		this.profissoesValidas = new HashSet<String>();
		this.idadeMinima = idadeMinimaInicial;
	}
	
	public void cadastraPessoa(String nome, String cpf, String endereco, String numeroCartaoSus, String email, String telefone, 
			     String profissao, String comorbidades) {
		this.pessoas.put(cpf, new Pessoa(nome, cpf, endereco, numeroCartaoSus, email, telefone, profissao, Arrays.asList(comorbidades.split(", ")), this));
	}
	
	public BancoDeDadosDoGoverno getBancoDeDados() {
		return this.bancoDeDados;
	}
	
	public boolean ehComorbidadeValida(String comorbidade) {
		return this.comorbidadesValidas.contains(comorbidade);
	}
	
	public void adicionaProfissaoValida(String profissao) {
		this.profissoesValidas.add(profissao);
	}
	
	public void adicionaComorbidadeValida(String comorbidade) {
		this.comorbidadesValidas.add(comorbidade);
	}
	
	public boolean ehProfissaoValida(String profissao) {
		return this.profissoesValidas.contains(profissao);
	}
	
	public void setIdadeMinima(int idade) {
		this.idadeMinima = idade;
	}
	
	public void mudaCadastroEndereco(String cpf, String endereco) {
		this.pessoas.get(cpf).setEndereco(endereco);
	}
	
	public void mudaCadastroEmail(String cpf, String email) {
		this.pessoas.get(cpf).setEmail(email);
	}
	
	public void mudaCadastroTelefone(String cpf, String telefone) {
		this.pessoas.get(cpf).setTelefone(telefone);
	}
	
	public void mudaCadastroProfissao(String cpf, String profissao) {
		this.pessoas.get(cpf).setProfissao(profissao);
	}
	
	public void mudaCadastroAdicionaComorbidade(String cpf, String comorbidade) {
		this.pessoas.get(cpf).adicionaComorbidade(comorbidade);
	}
	
	public void atualizaPanorama() {
		for(Pessoa pessoa: pessoas.values()) {
			pessoa.atualiza();
		}
	}
	
	public void vacinaHabilitados() {
		for(Pessoa pessoa: pessoas.values()) {
			pessoa.vacina();
		}
	}
	
	public int getIdadeMinima() {
		return this.idadeMinima;
	}
	
	public String getPanoramaAtual() {
		String resultado = "";
		for(Pessoa pessoa: pessoas.values()) {
			resultado += pessoa.toString() + "\n";
		}
		return resultado;
	}
	
	public String exibeCadastro(String cpf) {
		return this.pessoas.get(cpf).toString();
	}
}
