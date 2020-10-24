import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(scanner.nextLine().replaceAll("\\s+", "").
                equals(scanner.nextLine().replaceAll("\\s+", "")) ?
                "true" : "false");
    }
}