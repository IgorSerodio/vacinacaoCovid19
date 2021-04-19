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
		System.out.println("Escolha um tipo de usu�rio:\n"
					+ "g: Funcion�rio do governo\n"
					+ "c: Usu�rio comum\n"
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
			System.out.println("Op��o inv�lida, tente novamente");
		}
		
	}
	
	private static String menuAdm(Scanner sc) {
		System.out.println("Escolha uma op��o: \n"
				+ "p: Adiciona profiss�o de risco\n"
				+ "c: Adiciona comorbidade de risco\n"
				+ "i: Muda idade m�nima para vacina��o\n"
				+ "e: Exibe situa��o dos cadastrados\n"
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
			exibeSitua��o(sistemaSus);
			break;
		case "r":
			return false;
		default:
			System.out.println("Op��o inv�lida, tente novamente");
		}
		return true;
	}

	private static void exibeSitua��o(SistemaDoSus sistemaSus) {
		System.out.println("\n" + sistemaSus.getPanoramaAtual());
	}

	private static void vacinaHabilitados(SistemaDoSus sistemaSus) {
		sistemaSus.vacinaHabilitados();
		System.out.println("Opera��o realizada.");
	}

	private static void adicionaProfissao(Scanner sc, SistemaDoSus sistemaSus) {
		System.out.println("Profiss�o: ");
		sistemaSus.adicionaProfissaoValida(sc.nextLine());
		System.out.println("Opera��o realizada.");
	}

	private static void adicionaComorbidade(Scanner sc, SistemaDoSus sistemaSus) {
		System.out.println("Comorbidade: ");
		sistemaSus.adicionaComorbidadeValida(sc.nextLine());
		System.out.println("Opera��o realizada.");
	}

	private static void mudaIdadeMinima(Scanner sc, SistemaDoSus sistemaSus) {
		System.out.println("Idade: ");
		sistemaSus.setIdadeMinima(sc.nextInt());
		System.out.println("Opera��o realizada.");
	}
	
	private static String menuCliente(Scanner sc) {
		System.out.println("Escolha uma op��o: \n"
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
			System.out.println("Op��o inv�lida, tente novamente");
		}
		return true;
	}

	private static void exibeCadastro(Scanner sc, SistemaDoSus sistemaSus) {
		System.out.println("CPF do usu�rio: ");
		sistemaSus.exibeCadastro(sc.nextLine());
		System.out.println("Opera��o realizada.");
	}

	private static void mudaCadastro(Scanner sc, SistemaDoSus sistemaSus) {
		System.out.println("CPF do usu�rio: ");
		String cpf = sc.nextLine();
		System.out.println("Campo do cadastro a ser alterado (comorbidades ser�o adicionadas): ");
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
		case "endere�o":
			sistemaSus.mudaCadastroEndereco(cpf, novoValor);
			break;
		case "profiss�o":
			sistemaSus.mudaCadastroProfissao(cpf, novoValor);
			break;
		case "telefone":
			sistemaSus.mudaCadastroTelefone(cpf, novoValor);
			break;
		default:
			System.out.println("Campo inv�lido");
			return;
		}
		
		System.out.println("Opera��o realizada.");
	}

	private static void realizaCadastro(Scanner sc, SistemaDoSus sistemaSus) {
		System.out.println("Nome: ");
		String nome = sc.nextLine();
		System.out.println("CPF: ");
		String cpf = sc.nextLine();
		System.out.println("Endere�o: ");
		String endereco = sc.nextLine();
		System.out.println("N�mero do cart�o do SUS: ");
		String numCartaoSus = sc.nextLine();
		System.out.println("E-mail: ");
		String email = sc.nextLine();
		System.out.println("Telefone: ");
		String telefone = sc.nextLine();
		System.out.println("Profiss�o: ");
		String profissao = sc.nextLine();
		System.out.println("Comorbidades (separadas por \", \"): ");
		String comorbidades = sc.nextLine();
		
		sistemaSus.cadastraPessoa(nome, cpf, endereco, numCartaoSus, email, telefone, profissao, comorbidades);
		
		System.out.println("Opera��o realizada.");
	}

	private static void sai(Scanner sc) {
		sc.close();
		System.exit(0);
	}

}
