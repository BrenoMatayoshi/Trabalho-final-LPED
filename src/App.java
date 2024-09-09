import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
    private static final String caminhoUsuario = "usuario.txt";
    private static final String caminhoNomeItem = "nomeItem.txt";
    private static final String caminhoQuantidadeItem = "quantidadeItem.txt";

    private static final String caminhoHistoricoDataItem = "historicoDataItem.txt";
    private static final String caminhoHistoricoNomeItem = "historicoNomeItem.txt";
    private static final String caminhoHistoricoNomeNoItem = "historicoNomeNoItem.txt";
    private static final String caminhoHistoricoQuantidadeItem = "historicoQuantidadeItem.txt";
    private static final String caminhoHistoricoColocouTirou = "historicoColocouTirou.txt";

    // Método para carregar os dados de um arquivo em um ArrayList tipo String
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

    // Método para carregar os dados de um arquivo em um ArrayList tipo Int
    public static ArrayList<Integer> carregarDadosInteger(String caminho) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(caminho));
            String adicionarAoArrayList = br.readLine();
            while (true) {
                if (adicionarAoArrayList != null) {
                    arrayList.add(Integer.parseInt(adicionarAoArrayList));
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

    // Método para escrever os dados em um arquivo.
    // caminho = caminho do arquivo
    // arrayList = lista que voce quer escrever
    public static void escreverDadosInteger(String caminho, ArrayList<Integer> arrayList) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(caminho));

            for (Object object : arrayList) {
                bw.append(object.toString() + "\n");
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
        int escolha = -1, quantidadeItem = -1, confirmar, escolherItem;
        String pesquisar;
        Scanner scanner = new Scanner(System.in);
        String usuario = escolherUsuario(scanner);
        ArrayList<String> listaNomeItem = carregarDadosString(caminhoNomeItem);
        ArrayList<Integer> listaQuantidadeItem = carregarDadosInteger(caminhoQuantidadeItem);

        ArrayList<String> listaHistoricoNomeItem = carregarDadosString(caminhoHistoricoNomeItem);
        ArrayList<String> listaHistoricoDataItem = carregarDadosString(caminhoHistoricoDataItem);
        ArrayList<String> listaHistoricoUsuarioItem = carregarDadosString(caminhoHistoricoNomeNoItem);
        ArrayList<String> listaHistoricoColocouTirou = carregarDadosString(caminhoHistoricoColocouTirou);
        ArrayList<Integer> listaHistoricoQuantidadeItem = carregarDadosInteger(caminhoHistoricoQuantidadeItem);
        if (usuario.equals(".")) {
            escolha = 0;
        }
        while (escolha != 0) {
            try {
                System.out.println(
                        "O que você deseja fazer?\n1 - Adicionar item ao estoque.\n2 - Excluir item do estoque.\n3 - Trocar\n4 - Listar\n5 - Procurar\n6 - Exibir historico\n0 - Encerrar Programa.");
                escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1:
                        System.out.println("Insira o nome do item:");
                        String receberNomeItem = scanner.nextLine();
                        if (verificarArrayListString(receberNomeItem, listaNomeItem) == -1) {
                            while (quantidadeItem == -1) {
                                try {
                                    System.out.println("Insira a quantidade:");
                                    quantidadeItem = scanner.nextInt();
                                    scanner.nextLine();
                                    listaNomeItem.add(receberNomeItem);
                                    escreverDadosString(caminhoNomeItem, listaNomeItem);
                                    listaQuantidadeItem.add(quantidadeItem);
                                    escreverDadosInteger(caminhoQuantidadeItem, listaQuantidadeItem);
                                    LocalDateTime dataHoraAtual = LocalDateTime.now();
                                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                                    String dataHoraFormatada = dataHoraAtual.format(formato);
                                    listaHistoricoDataItem.add(dataHoraFormatada);
                                    escreverDadosString(caminhoHistoricoDataItem, listaHistoricoDataItem);
                                    listaHistoricoUsuarioItem.add(usuario);
                                    escreverDadosString(caminhoHistoricoNomeNoItem, listaHistoricoUsuarioItem);
                                    listaHistoricoNomeItem.add(receberNomeItem);
                                    escreverDadosString(caminhoHistoricoNomeItem, listaHistoricoNomeItem);
                                    listaHistoricoQuantidadeItem.add(quantidadeItem);
                                    escreverDadosInteger(caminhoHistoricoQuantidadeItem, listaHistoricoQuantidadeItem);
                                    listaHistoricoColocouTirou.add("True");
                                    escreverDadosString(caminhoHistoricoColocouTirou, listaHistoricoColocouTirou);
                                } catch (Exception e) {
                                    scanner.nextLine();
                                    quantidadeItem = -1;
                                }
                            }
                        } else {
                            System.out.println("O item já existe.");
                        }
                        break;

                    case 2:
                        if (listaNomeItem.size() != 0) {
                            printarString(listaNomeItem);
                            System.out.println("Escolha o item que você deseja excluir.");
                            try {
                                escolherItem = scanner.nextInt();
                                scanner.nextLine();
                                pesquisar = listaNomeItem.get(escolherItem - 1);
                                int index = verificarArrayListString(pesquisar, listaNomeItem);
                                if (index != -1) {
                                    System.out.println("Confirmar?\n1 - Sim.\n2 - Não.");
                                    try {
                                        confirmar = scanner.nextInt();
                                        scanner.nextLine();
                                        if (confirmar == 1) {
                                            LocalDateTime dataHoraAtual = LocalDateTime.now();
                                            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
                                            String dataHoraFormatada = dataHoraAtual.format(formato);
                                            listaHistoricoDataItem.add(dataHoraFormatada);
                                            escreverDadosString(caminhoHistoricoDataItem, listaHistoricoDataItem);
                                            listaHistoricoColocouTirou.add("False");
                                            escreverDadosString(caminhoHistoricoColocouTirou, listaHistoricoColocouTirou);
                                            listaHistoricoUsuarioItem.add(usuario);
                                            escreverDadosString(caminhoHistoricoNomeNoItem, listaHistoricoUsuarioItem);
                                            listaHistoricoQuantidadeItem.add(-1);
                                            escreverDadosInteger(caminhoHistoricoQuantidadeItem, listaHistoricoQuantidadeItem);
                                            listaHistoricoNomeItem.add(listaNomeItem.get(index));
                                            escreverDadosString(caminhoHistoricoNomeItem, listaHistoricoNomeItem);
                                            listaNomeItem.remove(index);
                                            listaQuantidadeItem.remove(index);
                                            escreverDadosString(caminhoNomeItem, listaNomeItem);
                                            escreverDadosInteger(caminhoQuantidadeItem, listaQuantidadeItem);
                                            System.out.println("\nItem removido com sucesso.\n");
                                        }
                                    } catch (Exception e) {
                                        System.out.println(e);
                                        scanner.nextLine();
                                    }
                                } else {
                                    System.out.println("Item não encontrado.");
                                }
                            } catch (Exception e) {
                                System.out.println("Opção não encontrada.");
                                scanner.nextLine();
                            }
                        } else {
                            System.out.println("Estoque vazio.");
                        }
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