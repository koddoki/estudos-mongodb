package com.example.mdbspringboot;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import com.example.mdbspringboot.model.Samurai;
import com.example.mdbspringboot.repository.SamuraiRepository;

@SpringBootApplication
@EnableMongoRepositories
public class L5rCharacterManager implements CommandLineRunner {

	@Autowired
	SamuraiRepository samuraiRepo;

	public static void main(String[] args) {
		SpringApplication.run(L5rCharacterManager.class, args);
	}

	@Override
	public void run(String... args) {
		while (true) {
			construirMenu();
		}

	}

	public int construirMenu() {
		System.out.println("Bem vindo ao depósito de pergonagens L5R");
		System.out.println("O que deseja fazer?");
		System.out.println("1 Criar um personagem");
		System.out.println("2 Visualizar um personagem");
		System.out.println("3 Editar um personagem");
		System.out.println("4 Deletar um personagem");
		System.out.println("5 Sair");
		System.out.println("---------------------------------");

		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		System.out.println("Teste depois do scanner");
		switch (n) {
		case 1:
			System.out.println("Voce digitou 1");
			return switchCriarPersonagem();
		case 2:
			System.out.println("Voce digitou 2");
			return listarTodosPersonagens();
		case 3:
			System.out.println("Voce digitou 3");
			return switchAtualizarPersonagem();
		case 4:
			System.out.println("Voce digitou 4");
			return switchDeletarPersonagem();
		case 5:
			System.out.println("Voce digitou 5");
			System.exit(0);
		default:
			System.out.println("Você digitou uma ação invalida!");
			System.out.println("---------------------------------");
			return construirMenu();
		}

	}

	// CREATE

	public int switchCriarPersonagem() {
		while (true) {
			System.out.println("Você selecionou \"1- Criar um personagem\"");
			System.out.println("O que deseja fazer?");
			System.out.println("1 Criar um personagem");
			System.out.println("2 Preencher o BD com personagens padrões");
			System.out.println("3 Voltar");
			System.out.println("---------------------------------");

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			int n = scanner.nextInt();
			switch (n) {
			case 1:
				System.out.println("Voce digitou 1");
				return criarPersonagem();
			case 2:
				System.out.println("Voce digitou 2");
				criarPersonagensPadroes();
				System.out.println("Placeholders criados");
				System.out.println("---------------------------------");
			case 3:
				System.out.println("Voce digitou 3");
				return construirMenu();
			default:
				System.out.println("Você digitou uma ação invalida!");
				System.out.println("---------------------------------");
			}
		}
	}

	int criarPersonagem() {
		Samurai samurai = new Samurai();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int i;
		String s;

		System.out.println("Informe o nome do seu personagem:");
		s = scanner.nextLine();

		if (samuraiRepo.findItemByName(s) != null) {
			System.out.println("Este nome já existe!");
			return 0;
		}
		;
		samurai.setName(s);
		System.out.println("Informe a familia do seu personagem:");
		s = scanner.nextLine();
		samurai.setFamilyName(s);
		System.out.println("Informe a escola do seu personagem:");
		s = scanner.nextLine();
		samurai.setSchool(s);
		System.out.println("Informe o clã do seu personagem:");
		s = scanner.nextLine();
		samurai.setClan(s);

		System.out.println("Informe a honra do seu peraonagem: ");
		i = scanner.nextInt();
		samurai.setHonor(i);
		System.out.println("Informe a gloria do seu peraonagem: ");
		i = scanner.nextInt();
		samurai.setGlory(i);
		System.out.println("Informe o status do seu peraonagem: ");
		i = scanner.nextInt();
		samurai.setStatus(i);

		samuraiRepo.save(samurai);
		return 0;
	}

	int criarPersonagensPadroes() {
		System.out.println("Data creation started...");

		samuraiRepo.save(new Samurai("Roku", "Mirumoto", "Taoist Blade", "Dragon", 65, 45, 30));
		samuraiRepo.save(new Samurai("Goku", "Togashi", "Tattooed Monk", "Dragon", 58, 40, 30));
		samuraiRepo.save(new Samurai("Zuko", "Akodo", "General", "Lion", 63, 55, 35));

		System.out.println("Data creation complete...");

		return 0;
	}

	// READ

	public int listarTodosPersonagens() {
		samuraiRepo.findAll().forEach(samurai -> System.out.println(getInformacoesPersonagens(samurai)));
		return 0;
	}

	public String getInformacoesPersonagens(Samurai samurai) {
		System.out.println("Nome: " + samurai.getFamilyName() + " " + samurai.getName() + ", \nClã: "
				+ samurai.getClan() + ", \nEscola: " + samurai.getSchool() + ", \nHonra: " + samurai.getHonor()
				+ " | Gloria: " + samurai.getGlory() + " | Status: " + samurai.getStatus());
		return "";
	}

	public int listarBasicoTodosPersonagens() {
		samuraiRepo.findAll().forEach(samurai -> System.out.println(getInformacoesBasicasPersonagens(samurai)));
		return 0;
	}

	public String getInformacoesBasicasPersonagens(Samurai samurai) {
		System.out.println("Nome: " + samurai.getName() + ", \nFamilia " + samurai.getFamilyName() + ", \nClã: "
				+ samurai.getClan());
		return "";
	}

	// UPDATE
	
	public int switchAtualizarPersonagem() {
		while (true) {
			System.out.println("Você selecionou \"3- Editar um personagem\"");
			System.out.println("O que deseja fazer?");
			System.out.println("1 Atualizar um personagem pelo nome");
			System.out.println("2 Visualizar uma lista com o nome de todos os personagens");
			System.out.println("3 Voltar");
			System.out.println("---------------------------------");

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			int n = scanner.nextInt();
			switch (n) {
			case 1:
				System.out.println("Voce digitou 1");
				return controladorAtualizarPersonagem();
			case 2:
				System.out.println("Voce digitou 2");
				listarBasicoTodosPersonagens();
				break;
			case 3:
				System.out.println("Voce digitou 3");
				return 0;
			default:
				System.out.println("Você digitou uma ação invalida!");
				System.out.println("---------------------------------");
				break;
			}
		}
	}

	public int controladorAtualizarPersonagem() {
		System.out.println("Informe o nome do Samurai que deseje alterar: ");
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String s = scanner.nextLine();

		Samurai samurai = samuraiRepo.findItemByName(s);

		samurai = atualizarPersonagem(samurai);

		samuraiRepo.save(samurai);

		return 0;
	}

	Samurai atualizarPersonagem(Samurai samurai) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String s;
		int number;

		System.out.println("-- Apenas precione enter caso não queira alterar os valores --");
		System.out.println("Informe a familia do seu personagem:");
		s = scanner.nextLine();
		if (s != "") {
			samurai.setFamilyName(s);
		}
		System.out.println("Informe a escola do seu personagem:");
		s = scanner.nextLine();
		if (s != "") {
			samurai.setSchool(s);
		}
		System.out.println("Informe o clã do seu personagem:");
		s = scanner.nextLine();
		if (s != "") {
			samurai.setClan(s);
		}
		System.out.println("Informe a honra do seu peraonagem: ");
		s = scanner.nextLine();
		if (s != "") {
			number = Integer.parseInt(s);
			samurai.setHonor(number);
		}
		System.out.println("Informe a gloria do seu peraonagem: ");
		s = scanner.nextLine();
		if (s != "") {
			number = Integer.parseInt(s);
			samurai.setGlory(number);
		}
		System.out.println("Informe o status do seu peraonagem: ");
		s = scanner.nextLine();
		if (s != "") {
			number = Integer.parseInt(s);
			samurai.setStatus(number);
		}
		return samurai;
	}

	// DELETE

	public int switchDeletarPersonagem() {
		System.out.println("Você selecionou \"4- Deletar um personagem\"");
		while (true) {
			System.out.println("O que deseja fazer?");
			System.out.println("1 Deletar um personagem pelo nome");
			System.out.println("2 Deletar todos os personagens");
			System.out.println("3 Voltar");
			System.out.println("---------------------------------");

			@SuppressWarnings("resource")
			Scanner scanner = new Scanner(System.in);
			int n = scanner.nextInt();
			switch (n) {
			case 1:
				System.out.println("Voce digitou 1");
				return deletarPersonagem();
			case 2:
				System.out.println("Voce digitou 2");
				return deletarTodosPersonagens();
			case 3:
				System.out.println("Voce digitou 3");
				System.out.println("---------------------------------");
				return 0;
			default:
				System.out.println("Você digitou uma ação invalida!");
				System.out.println("---------------------------------");
				break;
			}
		}
	}

	public int deletarPersonagem() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String nome;
		System.out.println("Insira o nome do personagem a ser deletado:");
		nome = scanner.nextLine();

		if (samuraiRepo.findItemByName(nome) == null) {
			System.out.println("ERRO: Este personagem não existe!");
			return 0;
		}
		;
		samuraiRepo.deleteById(nome);
		System.out.println("Personagem chamado de \"" + nome + "\" foi deletado com sucesso.");
		return 0;
	}

	public int deletarTodosPersonagens() {
		samuraiRepo.findAll().forEach(samurai -> samuraiRepo.delete(samurai));
		return 0;
	}
}