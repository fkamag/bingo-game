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
    int [] cartelaGerada = new int[5];
    int number;
    Random r = new Random();

    if (option.equals("1")) {
      for (int i = 0; i < players.length; i++) {
        for (int j = 0; j < 5; j++) {
          number = r.nextInt(60)+1;
          while (true) {
            boolean isDuplicate = false;
            for (int k = 0; k < 5; k++) {
              if (cartelaGerada[k] == number) {
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
          cartelaGerada[j] = number;
        }
        Arrays.sort(cartelaGerada);
        boolean isDuplicateCard = false;
        if (i != 0) {
          for (int j = 0; j <= i; j++) {
            int counter = 0;
            for (int k = 0; k < 5; k++) {
              if (cartelasPorJogador[j][k] == cartelaGerada[k]) {
                counter += 1;
              } else {
                break;
              }
            }
            if (counter == 5) {
              isDuplicateCard = true;
              break;
            }
          }
        }
        if (!isDuplicateCard) {
          cartelasPorJogador[i] = cartelaGerada;
          cartelaGerada = new int[5];
        } else {
          i -= 1;
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

    System.out.println();
    System.out.println("-----------------------------------");
    System.out.println("-----------Participantes-----------");
    System.out.println("-----------------------------------");
    for (int i = 0; i < players.length; i++) {
      System.out.printf("%2d - %10s \t - ", i+1,players[i]);
      for (int j=0; j < 5; j++) {
        System.out.printf("%2d ", cartelasPorJogador[i][j]);
      }
      System.out.println();
    }

    String optionRaffle = "";
    while (!optionRaffle.equals("1") && !optionRaffle.equals("2")) {
      System.out.println("-----------------------------------");
      System.out.println("Vamos começar o sorteio, escolha a opção:");
      System.out.println("1 - Sorteio Automático");
      System.out.println("2 - Sorteio Manual");
      optionRaffle = scanner.next();
    }

    int [] allRaffleNumbers = new int[60];
    int [][] correctNumbers = new int[players.length][5];


    if (optionRaffle.equals("1")) {
      boolean isContinue = true;
      int round = 0;
      while (isContinue) {
        round += 1;
        int indiceInicial = round * 5 - 5;
        for (int i = 0; i < 5; i++) {
          number = r.nextInt(60)+1;
          while (true) {
            boolean isDuplicate = false;
            for (int j = 0; j < 60; j++) {
              if (allRaffleNumbers[j] == number) {
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
          allRaffleNumbers[indiceInicial] = number;
          indiceInicial += 1;
        }
        System.out.println("Números sorteados: " + Arrays.toString(allRaffleNumbers));
        for (int i = 0; i < players.length; i++) {
          for (int j = 0; j < 5; j++) {
            for (int allRaffleNumber : allRaffleNumbers) {
              if (allRaffleNumber == 0) {
                break;
              }
              if (allRaffleNumber == cartelasPorJogador[i][j]) {
                correctNumbers[i][j] = 1;
                break;
              }
            }
          }
        }
        System.out.println(Arrays.deepToString(correctNumbers));

        System.out.println("Digite 'X' para encerrar ou tecle 'enter' para continuar:");
        String optionContinue = scanner.next();
        if (optionContinue.equals("X")) {
          isContinue = false;
        }
      }

    } else {
      System.out.println("Fazer sorteio Manual");
    }
  }
}
