import java.util.Arrays;
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

    System.out.println(Arrays.deepToString(players));
  }

}
