package interfaceTextual;

import java.util.Scanner;

import baseDeDados.BancoDeDadosDoGoverno;
import contexto.SistemaDoSus;

public class InterfaceTextual {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		SistemaDoSus sistemaSus = new SistemaDoSus(new BancoDeDadosDoGoverno(), Integer.MAX_VALUE);
		
		while(true)
			comandoGeral(sc, sistemaSus);
	}
	
	private static String menuGeral(Scanner sc) {
		System.out.println("Escolha um tipo de usuário:\n"
					+ "g: Funcionário do governo\n"
					+ "c: Usuário comum\n"
					+ "s: Sair");
		return sc.nextLine();
	}
	
	private static void comandoGeral(Scanner sc, SistemaDoSus sistemaSus) {
		String opcao = menuGeral(sc);
		switch (opcao) {
		case "g":
			while(comandoAdm(sc, sistemaSus));
			break;
		case "c":
			while(comandoCliente(sc, sistemaSus));
			break;
		case "s":
			sai(sc);
		default:
			System.out.println("Opção inválida, tente novamente");
		}
		
	}
	
	private static String menuAdm(Scanner sc) {
		System.out.println("Escolha uma opção: \n"
				+ "p: Adiciona profissão de risco\n"
				+ "c: Adiciona comorbidade de risco\n"
				+ "i: Muda idade mínima para vacinação\n"
				+ "e: Exibe situação dos cadastrados\n"
				+ "v: Vacina Habilitados\n"
				+ "r: retornar");
		return sc.nextLine();
	}

	private static boolean comandoAdm(Scanner sc, SistemaDoSus sistemaSus) {
		String opcao = menuAdm(sc);
		switch (opcao) {
		case "p":
			adicionaProfissao(sc, sistemaSus);
			break;
		case "c":
			adicionaComorbidade(sc, sistemaSus);
			break;
		case "i":
			mudaIdadeMinima(sc, sistemaSus);
			break;
		case "v":
			vacinaHabilitados(sistemaSus);
			break;
		case "e":
			exibeSituação(sistemaSus);
			break;
		case "r":
			return false;
		default:
			System.out.println("Opção inválida, tente novamente");
		}
		return true;
	}

	private static void exibeSituação(SistemaDoSus sistemaSus) {
		System.out.println("\n" + sistemaSus.getPanoramaAtual());
	}

	private static void vacinaHabilitados(SistemaDoSus sistemaSus) {
		sistemaSus.vacinaHabilitados();
		System.out.println("Operação realizada.");
	}

	private static void adicionaProfissao(Scanner sc, SistemaDoSus sistemaSus) {
		System.out.println("Profissão: ");
		sistemaSus.adicionaProfissaoValida(sc.nextLine());
		System.out.println("Operação realizada.");
	}

	private static void adicionaComorbidade(Scanner sc, SistemaDoSus sistemaSus) {
		System.out.println("Comorbidade: ");
		sistemaSus.adicionaComorbidadeValida(sc.nextLine());
		System.out.println("Operação realizada.");
	}

	private static void mudaIdadeMinima(Scanner sc, SistemaDoSus sistemaSus) {
		System.out.println("Idade: ");
		sistemaSus.setIdadeMinima(sc.nextInt());
		System.out.println("Operação realizada.");
	}
	
	private static String menuCliente(Scanner sc) {
		System.out.println("Escolha uma opção: \n"
				+ "c: Realiza cadastro\n"
				+ "m: Muda cadastro\n"
				+ "e: Exibe cadastro\n"
				+ "r: retornar");
		return sc.nextLine();
	}

	private static boolean comandoCliente(Scanner sc, SistemaDoSus sistemaSus) {
		String opcao = menuCliente(sc);
		switch (opcao) {
		case "c":
			realizaCadastro(sc, sistemaSus);
			break;
		case "m":
			mudaCadastro(sc, sistemaSus);
			break;
		case "e":
			exibeCadastro(sc, sistemaSus);
		case "r":
			return false;
		default:
			System.out.println("Opção inválida, tente novamente");
		}
		return true;
	}

	private static void exibeCadastro(Scanner sc, SistemaDoSus sistemaSus) {
		System.out.println("CPF do usuário: ");
		sistemaSus.exibeCadastro(sc.nextLine());
		System.out.println("Operação realizada.");
	}

	private static void mudaCadastro(Scanner sc, SistemaDoSus sistemaSus) {
		System.out.println("CPF do usuário: ");
		String cpf = sc.nextLine();
		System.out.println("Campo do cadastro a ser alterado (comorbidades serão adicionadas): ");
		String campo = sc.nextLine().toLowerCase();
		System.out.println("Novo valor: ");
		String novoValor = sc.nextLine();
		switch (campo) {
		case "comorbidades":
			sistemaSus.mudaCadastroAdicionaComorbidade(cpf, novoValor);
			break;
		case "e-mail":
			sistemaSus.mudaCadastroEmail(cpf, novoValor);
			break;
		case "endereço":
			sistemaSus.mudaCadastroEndereco(cpf, novoValor);
			break;
		case "profissão":
			sistemaSus.mudaCadastroProfissao(cpf, novoValor);
			break;
		case "telefone":
			sistemaSus.mudaCadastroTelefone(cpf, novoValor);
			break;
		default:
			System.out.println("Campo inválido");
			return;
		}
		
		System.out.println("Operação realizada.");
	}

	private static void realizaCadastro(Scanner sc, SistemaDoSus sistemaSus) {
		System.out.println("Nome: ");
		String nome = sc.nextLine();
		System.out.println("CPF: ");
		String cpf = sc.nextLine();
		System.out.println("Endereço: ");
		String endereco = sc.nextLine();
		System.out.println("Número do cartão do SUS: ");
		String numCartaoSus = sc.nextLine();
		System.out.println("E-mail: ");
		String email = sc.nextLine();
		System.out.println("Telefone: ");
		String telefone = sc.nextLine();
		System.out.println("Profissão: ");
		String profissao = sc.nextLine();
		System.out.println("Comorbidades (separadas por \", \"): ");
		String comorbidades = sc.nextLine();
		
		sistemaSus.cadastraPessoa(nome, cpf, endereco, numCartaoSus, email, telefone, profissao, comorbidades);
		
		System.out.println("Operação realizada.");
	}

	private static void sai(Scanner sc) {
		sc.close();
		System.exit(0);
	}

}
