import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Bingo {

  public static void main(String[] args) {

    Scanner scanner = new Scanner(System.in);

    String[] players = inputPlayers(scanner);

    String cardOption = cardMenu(scanner);

    String cartela;
    int [][] cartelasPorJogador = new int[players.length][5];
    int [] cartelaGerada = new int[5];
    int number;
    Random random = new Random();

    if (cardOption.equals("1")) {
      automaticCard(players.length, cartelaGerada, random, cartelasPorJogador);
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

    String raffleOption = "";
    raffleOption = raffleMenu(raffleOption, scanner);

    int [] allRaffleNumbers = new int[60];
    int [][] correctNumbers = new int[players.length][5];


    if (raffleOption.equals("1")) {
      boolean isContinue = true;
      int round = 0;
      int [] hits = new int[players.length];
      while (isContinue) {
        round += 1;
        int indiceInicial = round * 5 - 5;
        int [] raffleNumbers = new int[5];
        for (int i = 0; i < 5; i++) {
          number = random.nextInt(60)+1;
          while (true) {
            boolean isDuplicate = false;
            for (int j = 0; j < 60; j++) {
              if (allRaffleNumbers[j] == number) {
                isDuplicate = true;
                break;
              }
            }
            if (isDuplicate) {
              number = random.nextInt(60)+1;
            } else {
              break;
            }
          }
          raffleNumbers[i] = number;
          allRaffleNumbers[indiceInicial] = number;
          indiceInicial += 1;
        }
        System.out.println("Rodada " + round);
        System.out.println("Números sorteados: " + Arrays.toString(raffleNumbers));
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
        for (int i = 0; i < players.length; i++) {
          int sum = 0;
          for (int j = 0; j < 5; j++) {
            sum += correctNumbers[i][j];
          }
          hits[i] = sum;
        }
        int valueFirst = 0;
        for (int hit : hits) {
          if (hit > valueFirst) {
            valueFirst = hit;
          }
        }

        showRanking(hits, players, 3);

        if (valueFirst == 5) {
          break;
        }

        String optionContinue = "";
        while (!optionContinue.equals("c")) {
          System.out.println("Digite 'x' para encerrar ou 'c' para continuar:");
          optionContinue = scanner.next();
          if (optionContinue.equals("x")) {
            isContinue = false;
            break;
          }
        }
      }
      endBingo(round, allRaffleNumbers, hits, players);
    } else {
      System.out.println("Fazer sorteio Manual");
    }
  }


  private static String raffleMenu(String raffleOption, Scanner scanner) {
    while (!raffleOption.equals("1") && !raffleOption.equals("2")) {
      System.out.println("-----------------------------------");
      System.out.println("Vamos começar o sorteio, escolha a opção:");
      System.out.println("1 - Sorteio Automático");
      System.out.println("2 - Sorteio Manual");
      raffleOption = scanner.next();
    }
    return raffleOption;
  }

  private static void showRaffleNumbers(int countRaffleNumbers, int[] allRaffleNumbers) {
    int[] drawnNumbers = new int[countRaffleNumbers];
    int count = 0;
    for (int number : allRaffleNumbers) {
      if (number != 0) {
        drawnNumbers[count] = number;
        count += 1;
      }
    }
    for (int i = 0; i < drawnNumbers.length; i++) {
      if (i % 5 == 0) {
        System.out.println();
      }
      System.out.printf("%2d ", drawnNumbers[i]);
    }
  }

  private static String[] inputPlayers(Scanner scanner) {
    System.out.println("-------------------------------------------------");
    System.out.println(" B E M - V I N D O   A O   N O S S O   B I N G O  ");
    System.out.println("-------------------------------------------------");
    System.out.println();
    System.out.println("Digite o nome dos participantes separados por '-'"
        + " e sem espaço: ");
    String scannerPlayer = scanner.next();
    System.out.println();
    return scannerPlayer.split("-");
  }

  private static String cardMenu(Scanner scanner) {
    String cardOption = "";
    while (!cardOption.equals("1") && !cardOption.equals("2")) {
      System.out.println("Escolha a opção para gerar as cartelas");
      System.out.println("1 - Cartelas Automáticas");
      System.out.println("2 - Cartelas Manuais");
      cardOption = scanner.next();
    }
    return cardOption;
  }

  private static void automaticCard(int players, int[] cartelaGerada, Random random,
      int[][] cartelasPorJogador) {
    for (int i = 0; i < players; i++) {
      for (int j = 0; j < 5; j++) {
        cartelaGerada[j] = getNumber(cartelaGerada, random);
      }
      Arrays.sort(cartelaGerada);
      System.out.println("entrei aqui");
      if (!isDuplicateCard(cartelaGerada, cartelasPorJogador, i)) {
        cartelasPorJogador[i] = cartelaGerada;
        cartelaGerada = new int[5];
      } else {
        i -= 1;
      }
    }
  }

  private static boolean isDuplicateCard(int[] cartelaGerada, int[][] cartelasPorJogador, int i) {
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
    return isDuplicateCard;
  }

  private static int getNumber(int[] generatedCard, Random random) {
    int number = random.nextInt(60)+1;
    while (true) {
      boolean isDuplicate = false;
      for (int k = 0; k < 5; k++) {
        if (generatedCard[k] == number) {
          isDuplicate = true;
          break;
        }
      }
      if (isDuplicate) {
        number = random.nextInt(60)+1;
      } else {
        break;
      }
    }
    return number;
  }

  private static void showRanking(int[] scores, String[] players, int positions) {
    System.out.println("Ranking: ");
    int[] ranks = getRanks(scores);
    for (int i = 1; i <= positions; i++) {
      for (int j=0; j < ranks.length; j++) {
        if (i == ranks[j]) {
          System.out.printf("%2dº lugar - %10s com %d acertos\n", i, players[j], scores[j]);
        }
      }
    }
  }

  private static int[] getRanks(int[] scores) {
    int n = scores.length;
    int[][] scoreIndexPairs = new int[n][2];
    for (int i = 0; i < n; i++) {
      scoreIndexPairs[i][0] = scores[i];
      scoreIndexPairs[i][1] = i;
    }
    Arrays.sort(scoreIndexPairs, (a, b) -> b[0] - a[0]);
    int[] ranks = new int[n];
    for (int i = 0; i < n; i++) {
      ranks[scoreIndexPairs[i][1]] = i + 1;
    }
    return ranks;
  }
  
  private static void endBingo(int round, int[] allRaffleNumbers, int[] hits, String[] players) {
    System.out.println();
    System.out.println("*&*&*&*&*&*&*&*&*");
    System.out.println("* * B I N G O * *");
    System.out.println("*&*&*&*&*&*&*&*&*");
    System.out.println();
    System.out.println("Partida encerrada com " + round + " rodadas");
    System.out.println();
    int countRaffleNumbers = 0;
    for (int allRaffleNumber : allRaffleNumbers) {
      if (allRaffleNumber == 0) {
        break;
      }
      countRaffleNumbers += 1;
    }
    System.out.println("Foram sorteados " + countRaffleNumbers + " números");
    System.out.println();
    System.out.println("Números Sorteados por ordem de sorteio:");
    showRaffleNumbers(countRaffleNumbers, allRaffleNumbers);
    System.out.println();
    Arrays.sort(allRaffleNumbers);
    System.out.println();
    System.out.println("Números Sorteados por ordem de números:");
    showRaffleNumbers(countRaffleNumbers, allRaffleNumbers);
    System.out.println();
    System.out.println();
    showRanking(hits, players, players.length);
  }
}
