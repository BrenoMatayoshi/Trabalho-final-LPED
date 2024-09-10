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
            for (String string : arrayList) {
                bw.append(string + "\n");
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

            for (Integer integer : arrayList) {
                bw.append(integer.toString() + "\n");
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
                                for (int i = 0; i < listaUsuario.size(); i++) {
                                    System.out.println(i + 1 + " - " + listaUsuario.get(i));
                                }
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
                                for (int i = 0; i < listaUsuario.size(); i++) {
                                    System.out.println(i + 1 + " - " + listaUsuario.get(i));
                                }
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
        int escolha = -1, quantidadeItem = -1, confirmar = 0, opcao = -1;
        String pesquisar;
        Scanner scanner = new Scanner(System.in);
        String usuario = escolherUsuario(scanner);
        ArrayList<String> listaNomeItem = carregarDadosString(caminhoNomeItem);
        ArrayList<Integer> listaQuantidadeItem = carregarDadosInteger(caminhoQuantidadeItem);

        ArrayList<String> listaHistoricoNomeItem = carregarDadosString(caminhoHistoricoNomeItem);
        ArrayList<String> listaHistoricoDataItem = carregarDadosString(caminhoHistoricoDataItem);
        ArrayList<String> listaHistoricoUsuarioItem = carregarDadosString(caminhoHistoricoNomeNoItem);
        ArrayList<String> listaHistoricoQuantidadeItem = carregarDadosString(caminhoHistoricoQuantidadeItem);
        if (usuario.equals(".")) {
            escolha = 0;
        }
        while (escolha != 0) {
            try {
                System.out.println("\nO que você deseja fazer?\n1 - Adicionar item ao estoque.\n2 - Excluir item do estoque.\n3 - Lançar entrada/saida.\n4 - Exibir todos os itens.\n5 - Procurar.\n6 - Exibir historico.\n7 - Resetar sistema.\n0 - Encerrar Programa.");
                escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1:
                        System.out.println("Insira o nome do item:");
                        String receberNomeItem = scanner.nextLine();
                        if (verificarArrayListString(receberNomeItem, listaNomeItem) == -1) {
                            quantidadeItem = -1;
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
                                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy . HH:mm:ss");
                                    String DataHora = dataHoraAtual.format(formato);
                                    String[] dataHoraFormatada = DataHora.split("\\.");
                                    listaHistoricoDataItem.add(dataHoraFormatada[0] + "as" + dataHoraFormatada[1]);
                                    escreverDadosString(caminhoHistoricoDataItem, listaHistoricoDataItem);
                                    listaHistoricoUsuarioItem.add(usuario);
                                    escreverDadosString(caminhoHistoricoNomeNoItem, listaHistoricoUsuarioItem);
                                    listaHistoricoNomeItem.add(receberNomeItem);
                                    escreverDadosString(caminhoHistoricoNomeItem, listaHistoricoNomeItem);
                                    listaHistoricoQuantidadeItem.add(Integer.toString(quantidadeItem));
                                    escreverDadosString(caminhoHistoricoQuantidadeItem, listaHistoricoQuantidadeItem);
                                    System.out.println("\nItem adicionado com sucesso.");
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
                            System.out.println("Escolha o item que você deseja excluir.");
                            for (int i = 0; i < listaNomeItem.size(); i++) {
                                System.out.println(i + 1 + " - " + listaNomeItem.get(i));
                            }
                            try {
                                int index = scanner.nextInt();
                                scanner.nextLine();
                                if (index > 0 && index < listaNomeItem.size() + 1) {
                                    System.out.println("Confirmar?\n1 - Sim.\n2 - Não.");
                                    try {
                                        confirmar = scanner.nextInt();
                                        scanner.nextLine();
                                        if (confirmar == 1) {
                                            LocalDateTime dataHoraAtual = LocalDateTime.now();
                                            DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy . HH:mm:ss");
                                            String DataHora = dataHoraAtual.format(formato);
                                            String[] dataHoraFormatada = DataHora.split("\\.");
                                            listaHistoricoDataItem.add(dataHoraFormatada[0] + "as" + dataHoraFormatada[1]);
                                            escreverDadosString(caminhoHistoricoDataItem, listaHistoricoDataItem);
                                            listaHistoricoUsuarioItem.add(usuario);
                                            escreverDadosString(caminhoHistoricoNomeNoItem, listaHistoricoUsuarioItem);
                                            listaHistoricoQuantidadeItem.add("-1");
                                            escreverDadosString(caminhoHistoricoQuantidadeItem, listaHistoricoQuantidadeItem);
                                            listaHistoricoNomeItem.add(listaNomeItem.get(index - 1));
                                            escreverDadosString(caminhoHistoricoNomeItem, listaHistoricoNomeItem);
                                            listaNomeItem.remove(index - 1);
                                            listaQuantidadeItem.remove(index - 1);
                                            escreverDadosString(caminhoNomeItem, listaNomeItem);
                                            escreverDadosInteger(caminhoQuantidadeItem, listaQuantidadeItem);
                                            System.out.println("\nItem removido com sucesso.");
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
                            System.out.println("\nEstoque vazio.");
                        }
                        break;

                    case 3:
                        if (listaNomeItem.isEmpty()) {
                            System.out.println("\nO estoque está vazio.");
                        } else {
                        System.out.println("1 - Entrada.\n2 - Saida.");
                        try {
                            opcao = scanner.nextInt();
                            scanner.nextLine();
                            switch (opcao) {
                                case 1:
                                    System.out.println("Escolha um item:");
                                    for (int i = 0; i < listaNomeItem.size(); i++) {
                                        System.out.println(i + 1 + " - " + listaNomeItem.get(i));
                                    }
                                    try {
                                        int index = scanner.nextInt();
                                        scanner.nextLine();
                                        if (index > 0 && index < listaNomeItem.size() + 1) {
                                            System.out.println("Nome do item: " + listaNomeItem.get(index - 1) + "\nQuantidade: " + listaQuantidadeItem.get(index - 1));
                                            System.out.println("Informe a quantidade a ser adicionada.");
                                            try {
                                                int quantidadeAddRem = scanner.nextInt();
                                                scanner.nextLine();
                                                if (quantidadeAddRem > 0) {
                                                    int quantidadeAntesDaAlteracao = listaQuantidadeItem.get(index - 1);
                                                    listaQuantidadeItem.set(index - 1, listaQuantidadeItem.get(index - 1) + quantidadeAddRem);
                                                    escreverDadosInteger(caminhoQuantidadeItem, listaQuantidadeItem);
                                                    listaHistoricoUsuarioItem.add(usuario);
                                                    listaHistoricoQuantidadeItem.add("Antes: " + Integer.toString(quantidadeAntesDaAlteracao) + "," + " Entrou: " + quantidadeAddRem + ", " + "Atual:" + Integer.toString(listaQuantidadeItem.get(index - 1)));
                                                    escreverDadosString(caminhoHistoricoQuantidadeItem, listaHistoricoQuantidadeItem);
                                                    escreverDadosString(caminhoHistoricoNomeNoItem, listaHistoricoUsuarioItem);
                                                    LocalDateTime dataHoraAtual = LocalDateTime.now();
                                                    DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy . HH:mm:ss");
                                                    String DataHora = dataHoraAtual.format(formato);
                                                    String[] dataHoraFormatada = DataHora.split("\\.");
                                                    listaHistoricoDataItem.add(dataHoraFormatada[0] + "as" + dataHoraFormatada[1]);
                                                    escreverDadosString(caminhoHistoricoDataItem,listaHistoricoDataItem);
                                                    listaHistoricoNomeItem.add(listaNomeItem.get(index - 1));
                                                    escreverDadosString(caminhoHistoricoNomeItem, listaHistoricoNomeItem);
                                                    System.out.println("Entrada feita com sucesso.");
                                                } else {
                                                    System.out.println("Quantidade invalida.");
                                                }
                                            } catch (Exception e) {
                                                scanner.nextLine();
                                            }
                                        } else {
                                            System.out.println("Item não encontrado.");
                                        }
                                    } catch (Exception e) {
                                        scanner.nextLine();
                                        System.out.println("Opção invalida.");
                                    }
                                    break;

                                case 2:
                                System.out.println("Escolha um item:");
                                for (int i = 0; i < listaNomeItem.size(); i++) {
                                    System.out.println(i + 1 + " - " + listaNomeItem.get(i));
                                }
                                try {
                                    int index = scanner.nextInt();
                                    scanner.nextLine();
                                    if (index > 0 && index < listaNomeItem.size() + 1) {
                                        System.out.println("Nome do item: " + listaNomeItem.get(index - 1) + "\nQuantidade: " + listaQuantidadeItem.get(index - 1));
                                        System.out.println("Informe a quantidade a ser adicionada.");
                                        try {
                                            int quantidadeAddRem = scanner.nextInt();
                                            scanner.nextLine();
                                            if (quantidadeAddRem > 0 && quantidadeAddRem <= listaQuantidadeItem.get(index-1)) {
                                                int quantidadeAntesDaAlteracao = listaQuantidadeItem.get(index - 1);
                                                listaQuantidadeItem.set(index - 1, listaQuantidadeItem.get(index - 1) - quantidadeAddRem);
                                                escreverDadosInteger(caminhoQuantidadeItem, listaQuantidadeItem);
                                                listaHistoricoUsuarioItem.add(usuario);
                                                listaHistoricoQuantidadeItem.add("Antes: " + Integer.toString(quantidadeAntesDaAlteracao) + "," + " Saiu: " + quantidadeAddRem + ", " + "Atual:" + Integer.toString(listaQuantidadeItem.get(index - 1)));
                                                escreverDadosString(caminhoHistoricoQuantidadeItem, listaHistoricoQuantidadeItem);
                                                escreverDadosString(caminhoHistoricoNomeNoItem, listaHistoricoUsuarioItem);
                                                LocalDateTime dataHoraAtual = LocalDateTime.now();
                                                DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy . HH:mm:ss");
                                                String DataHora = dataHoraAtual.format(formato);
                                                String[] dataHoraFormatada = DataHora.split("\\.");
                                                listaHistoricoDataItem.add(dataHoraFormatada[0] + "as" + dataHoraFormatada[1]);
                                                escreverDadosString(caminhoHistoricoDataItem,listaHistoricoDataItem);
                                                listaHistoricoNomeItem.add(listaNomeItem.get(index - 1));
                                                escreverDadosString(caminhoHistoricoNomeItem, listaHistoricoNomeItem);
                                                System.out.println("Saida feita com sucesso.");
                                            } else {
                                                System.out.println("Quantidade invalida.");
                                            }
                                        } catch (Exception e) {
                                            scanner.nextLine();
                                        }
                                    } else {
                                        System.out.println("Item não encontrado.");
                                    }
                                } catch (Exception e) {
                                    scanner.nextLine();
                                    System.out.println("Opção invalida.");
                                }
                                    break;

                                default:
                                    System.out.println("Opção invalida.");
                                    break;
                            }
                        } catch (Exception e) {
                            scanner.nextLine();
                            System.out.println("Opção invalida.");
                        }
                    }

                        break;

                    case 4:
                        if (!listaNomeItem.isEmpty()) {
                            for (int i = 0; i < listaNomeItem.size(); i++) {System.out.println("\nNome do item: " + listaNomeItem.get(i) + "\nQuantidade: " + listaQuantidadeItem.get(i));
                            }
                        } else {
                            System.out.println("\no estoque está vazio.");
                        }
                        break;

                    case 5:
                        if (listaNomeItem.isEmpty()) {
                            System.out.println("\nO estoque está vazio.");
                        } else {
                            System.out.println("Escolha um item para obter suas informações: ");
                            for (int i = 0; i < listaNomeItem.size(); i++) {
                                System.out.println(i+1 + " - " + listaNomeItem.get(i));
                            }
                            try {
                                opcao = scanner.nextInt();
                                scanner.nextLine();
                                if (opcao > 0 && opcao < listaNomeItem.size()+1) {
                                    System.out.println("\nNome do item: " + listaNomeItem.get(opcao - 1) + ".\nQuantidade: " + listaQuantidadeItem.get(opcao - 1) + ".");
                                } else {
                                    System.out.println("Opção invalida.");
                                }
                            } catch (Exception e) {
                                System.out.println("Item não encontrado.");    
                                scanner.nextLine();
                            }
                        }
                        break;

                    case 6:
                        if (listaHistoricoNomeItem.isEmpty()) {
                            System.out.println("\nO histórico está vazio.");
                        } else {
                            for (int i = 0; i < listaHistoricoNomeItem.size(); i++) {
                                System.out.println("\nNome do item: " + listaHistoricoNomeItem.get(i)
                                        + (listaHistoricoQuantidadeItem.get(i).equals("-1") ? "\nItem excluido do estoque." : "\nQuantidade: " + listaHistoricoQuantidadeItem.get(i)) + "\nPor: " + listaHistoricoUsuarioItem.get(i) + "\nData: " + listaHistoricoDataItem.get(i));
                            }
                        }
                        break;
                    
                    case 7:
                    opcao = -1;
                    while (opcao != 0) {
                        System.out.println("Escolha o que você deseja resetar: \n0 - Sair.\n1 - Apenas estoque.\n2 - Apenas histórico.\n3 - Tudo.");
                        try {
                            opcao = scanner.nextInt();
                            scanner.nextLine();
                            switch (opcao) {
                                case 1:
                                    System.out.println("Todo o estoque será apagado. Conmfirmar?\n1 - Sim.\n2 - Não.");
                                    try {
                                        confirmar = scanner.nextInt();
                                        scanner.nextLine();
                                        if (confirmar == 1) {
                                            listaNomeItem.clear();
                                            escreverDadosString(caminhoNomeItem, listaNomeItem);
                                            listaQuantidadeItem.clear();
                                            escreverDadosInteger(caminhoQuantidadeItem, listaQuantidadeItem);
                                        }
                                        opcao = 0;
                                    } catch (Exception e) {
                                        scanner.nextLine();
                                        System.out.println("Opção invalida.");
                                    }
                                    break;
                            
                                case 2:
                                System.out.println("Todo o histórico será apagado. Conmfirmar?\n1 - Sim.\n2 - Não.");
                                try {
                                    confirmar = scanner.nextInt();
                                    scanner.nextLine();
                                    if (confirmar == 1) {
                                        listaHistoricoDataItem.clear();
                                        escreverDadosString(caminhoHistoricoDataItem, listaHistoricoQuantidadeItem);
                                        listaHistoricoNomeItem.clear();
                                        escreverDadosString(caminhoHistoricoNomeItem, listaHistoricoNomeItem);
                                        listaHistoricoQuantidadeItem.clear();
                                        escreverDadosString(caminhoHistoricoQuantidadeItem, listaHistoricoQuantidadeItem);  
                                        listaHistoricoUsuarioItem.clear();
                                        escreverDadosString(caminhoHistoricoNomeNoItem, listaHistoricoUsuarioItem);
                                    }
                                    opcao = 0;
                                } catch (Exception e) {
                                    scanner.nextLine();
                                    System.out.println("Opção invalida.");
                                }
                                    break;
                            
                                case 3:
                                System.out.println("Tudo será apagado. Conmfirmar?\n1 - Sim.\n2 - Não.");
                                try {
                                    confirmar = scanner.nextInt();
                                    scanner.nextLine();
                                    if (confirmar == 1) {
                                        listaNomeItem.clear();
                                        escreverDadosString(caminhoNomeItem, listaNomeItem);
                                        listaQuantidadeItem.clear();
                                        escreverDadosInteger(caminhoQuantidadeItem, listaQuantidadeItem);
                                        listaHistoricoDataItem.clear();
                                        escreverDadosString(caminhoHistoricoDataItem, listaHistoricoQuantidadeItem);
                                        listaHistoricoNomeItem.clear();
                                        escreverDadosString(caminhoHistoricoNomeItem, listaHistoricoNomeItem);
                                        listaHistoricoQuantidadeItem.clear();
                                        escreverDadosString(caminhoHistoricoQuantidadeItem, listaHistoricoQuantidadeItem);  
                                        listaHistoricoUsuarioItem.clear();
                                        escreverDadosString(caminhoHistoricoNomeNoItem, listaHistoricoUsuarioItem);
                                        }
                                        opcao = 0;
                                    } catch (Exception e) {
                                        scanner.nextLine();
                                        System.out.println("Opção invalida.");
                                    }
                                    break;

                                case 0:
                                    opcao = 0;
                                    break;
                            
                                default:
                                    System.out.println("Opção não encontrada.");
                                    break;
                            }
                        } catch (Exception e) {
                            System.out.println("Opção invalida.");
                            scanner.nextLine();
                        }
                    }
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