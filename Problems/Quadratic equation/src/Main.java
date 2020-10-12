import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();
        double sqrt = Math.sqrt(b * b - 4 * a * c);
        double x1 = (-b + sqrt) / (2 * a);
        double x2 = (-b - sqrt) / (2 * a);
        if (x1 > x2) System.out.println(x2 + " " + x1);
        else System.out.println(x1 + " " + x2);
    }

}