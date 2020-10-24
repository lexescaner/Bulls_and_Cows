import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double determinant = b * b - 4 * a * c;
        double sqrt = Math.sqrt(determinant);

        double root1 = (-b + sqrt) / (2 * a);
        double root2 = (-b - sqrt) / (2 * a);
        System.out.printf("%f %f", Math.min(root1, root2), Math.max(root1, root2));

    }
}