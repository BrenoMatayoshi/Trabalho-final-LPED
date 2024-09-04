import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    private static final String caminhoUsuario = "usuario.txt";

    // Método para carregar os dados de um arquivo em um ArrayList
    public static ArrayList<String> carregarDadosString(String caminho) {
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho));
            String adicionarAoArrayList = br.readLine();
            while (true) {
                if (adicionarAoArrayList != null) {
                    arrayList.add(adicionarAoArrayList);
                } else {
                    br.close();
                    break;
                }
                adicionarAoArrayList = br.readLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return arrayList;
    }

    // Método para escrever os dados em um arquivo.
    // caminho = caminho do arquivo
    // arrayList = lista que voce quer escrever
    public static void escreverDadosString(String caminho, ArrayList<String> arrayList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));
            for (Object object : arrayList) {
                bw.append(object + "\n");
            }
            bw.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    // Método para verificar se um elemento existe dentro do arrayList
    // Se o retorno for -1 o elemento não existe dentro do arrayList
    public static int verificarArrayListString(String dado, ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            if (arrayList.get(i).equalsIgnoreCase(dado)) {
                return i;
            }
        }
        return -1;
    }

    // Printa todo o arrayList
    public static void printarString(ArrayList<String> arrayList) {
        for (int i = 0; i < arrayList.size(); i++) {
            System.out.println((i + 1) + " - " + arrayList.get(i));
        }
    }

    // Método para selecionar/cadastrar/remover usuario
    public static String escolherUsuario(Scanner scanner) {
        String usuario = null, receberNome;
        int escolha = -1, escolhaUsuario;
        ArrayList<String> listaUsuario = carregarDadosString(caminhoUsuario);
        while (usuario == null) {
            try {
                System.out.println("1 - Escolher usuário.\n2 - Cadastrar usuário.\n3 - Remover usuário.\n0 - Sair.");
                escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1:
                        if (!listaUsuario.isEmpty()) {
                            System.out.println("Escolha um usuário:");
                            while (true) {
                                System.out.println("0 - Voltar");
                                printarString(listaUsuario);
                                try {
                                    escolhaUsuario = scanner.nextInt();
                                    scanner.nextLine();
                                    if (escolhaUsuario == 0) {
                                        break;
                                    } else {
                                        if (escolhaUsuario < 1 || escolhaUsuario > listaUsuario.size()) {
                                            System.out.println("Usuário não encontrado.");
                                        } else {
                                            usuario = listaUsuario.get(escolhaUsuario - 1);
                                            limparTela();
                                            System.out.println("\nOlá " + usuario + ", seja bem-vindo!\n");
                                            return usuario;
                                        }
                                    }
                                } catch (Exception e) {
                                    scanner.nextLine();
                                    System.out.println("Usuário não encontrada.");
                                }
                            }
                        } else {
                            System.out.println("\nNenhum usuário cadastrado.\n");
                        }
                        break;

                    case 2:
                        System.out.println("Informe o seu nome: ");
                        receberNome = scanner.nextLine();
                        if (verificarArrayListString(receberNome, listaUsuario) == -1) {
                            listaUsuario.add(receberNome);
                            escreverDadosString(caminhoUsuario, listaUsuario);
                            System.out.println("\nUsuário adicionado com sucesso.\n");
                        } else {
                            System.out.println("Esse usuário já existe.");
                        }
                        break;

                    case 3:
                        if (!listaUsuario.isEmpty()) {
                            try {
                                System.out.println("Selecione o usuário que voce deseja remover: \n0 - Voltar");
                                printarString(listaUsuario);
                                escolhaUsuario = scanner.nextInt();
                                if (escolhaUsuario == 0) {
                                    break;
                                }
                                if (escolhaUsuario < 0 || escolhaUsuario > listaUsuario.size()) {
                                    System.out.println("Usuário não existe.");
                                } else {
                                    listaUsuario.remove(escolhaUsuario - 1);
                                    escreverDadosString(caminhoUsuario, listaUsuario);
                                    System.out.println("\nUsuário removido com sucesso.\n");
                                }
                            } catch (Exception e) {
                                System.out.println("Opção não encontrada.");
                                scanner.nextLine();
                                escolhaUsuario = -1;
                            }
                        } else {
                            System.out.println("\nNenhum usuário cadastrado.\n");
                        }
                        break;

                    case 0:
                        return ".";

                    default:
                        System.out.println("Opção não encontrada.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Opção não encontrada.");
                escolha = 0;
                scanner.nextLine();
            }
        }
        return usuario;
    }

    // Limpa a tela
    public static void limparTela() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        int escolha = -1;
        Scanner scanner = new Scanner(System.in);
        String usuario = escolherUsuario(scanner);
        if (usuario.equals(".")) {
            escolha = 0;
        }
        while (escolha != 0) {
            try {
                System.out.println("O que você deseja fazer?\n1 - Adicionar item ao estoque.\n2 - Remover\n3 - Trocar\n4 - Listar\n5 - Procurar\n6 - Exibir historico\n0 - Encerrar Programa.");
                escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1:
                        System.out.println("Insira o nome do item.");

                        break;

                    case 2:

                        break;

                    case 3:

                        break;

                    case 4:

                        break;

                    case 5:

                        break;

                    case 6:

                        break;

                    case 0:
                        System.out.println("Programa encerrado com sucesso.");
                        break;

                    default:
                        System.out.println("Opção não encontrada.");
                        break;
                }
            } catch (Exception e) {
                System.out.println("Opção não encontrada.");
                scanner.nextLine();
                escolha = -1;
            }
        }
        scanner.close();
    }
}