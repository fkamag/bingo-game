import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Bingo {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    System.out.println("--------------------");
    System.out.println("      B I N G O     ");
    System.out.println("--------------------");
    System.out.println();
    System.out.println("Digite o nome dos participantes separados por '-'"
        + " e sem espaço: ");
    String scannerPlayer = scanner.next();
    System.out.println();
    String[] players = scannerPlayer.split("-");

    String option = "";
    while (!option.equals("1") && !option.equals("2")) {
      System.out.println("Escolha a opção para gerar as cartelas");
      System.out.println("1 - Cartelas Automáticas");
      System.out.println("2 - Cartelas Manuais");
      option = scanner.next();
    }

    String cartela;
    int [][] cartelasPorJogador = new int[players.length][5];
    int number;
    Random r = new Random();

    if (option.equals("1")) {
      for (int i = 0; i < players.length; i++) {
        for (int j = 0; j < 5; j++) {
          number = r.nextInt(60)+1;
          while (true) {
            boolean isDuplicate = false;
            for (int k = 0; k < 5; k++) {
              if (cartelasPorJogador[i][k] == number) {
                isDuplicate = true;
                break;
              }
            }
            if (isDuplicate) {
              number = r.nextInt(60)+1;
            } else {
              break;
            }
          }
          cartelasPorJogador[i][j] = number;
        }
      }
    } else {
      System.out.println("Digite as cartelas utilizando o formato a seguir:");
      System.out.println("1,2,3,4,5-6,7,8,9,1-2,3,4,5,6");
      System.out.println("Seguindo a ordem dos participantes a baixo:");
      for (String p : players) {
        System.out.print(p + ", ");
      }
      System.out.println();
      cartela = scanner.next();

      String[] cartelas = cartela.split("-");

      String[] nova;
      for (int linha=0; linha< players.length; linha++) {
        nova = cartelas[linha].split(",");
        for (int coluna=0; coluna< 5; coluna++) {
          number = Integer.parseInt(nova[coluna]);
          cartelasPorJogador[linha][coluna] = number;
        }
      }
    }
    System.out.println(Arrays.deepToString(players));
    System.out.println(Arrays.deepToString(cartelasPorJogador));
  }

}
