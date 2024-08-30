//import java.io.BufferedReader;
//import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static String escolherUsuario(Scanner scanner) {
        String usuario = null;
        int escolha;
        while (usuario == null) {
            try {
                System.out.println("1 - Escolher usuário.\n2 - Cadastrar usuário.");
                escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1:
                        // A fazer
                        break;

                    case 2:
                        // A fazer
                        break;

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

    public static void main(String[] args) throws Exception {
        int escolha = -1;
        Scanner scanner = new Scanner(System.in);
        String usuario = escolherUsuario(scanner);
        System.out.println("Olá " + usuario + " seja bem vindo.");
        while (escolha != 0) {
            try {
                System.out.println("O que você deseja fazer?\n1 - Add\n2 - Remover\n3 - Trocar\n4 - Listar\n5 - Procurar\n6 - Exibir historico\n0 - Encerrar Programa.");
                escolha = scanner.nextInt();
                scanner.nextLine();
                switch (escolha) {
                    case 1:
                        
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
    }
}