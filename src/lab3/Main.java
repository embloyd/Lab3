package lab3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("=== Лабораторная №3 — Задание 1: Дроби ===");

        Fraction f1 = readFraction(sc, "первой");
        Fraction f2 = readFraction(sc, "второй");
        Fraction f3 = readFraction(sc, "третьей");

        System.out.println("\n✅ Созданные дроби:");
        System.out.println("f1 = " + f1);
        System.out.println("f2 = " + f2);
        System.out.println("f3 = " + f3);

        // 🔹 Примеры арифметических операций
        System.out.println("\n=== Примеры операций ===");
        System.out.println(f1 + " + " + f2 + " = " + f1.add(f2));
        System.out.println(f1 + " - " + f2 + " = " + f1.subtract(f2));
        System.out.println(f1 + " * " + f2 + " = " + f1.multiply(f2));
        System.out.println(f1 + " / " + f2 + " = " + f1.divide(f2));
        System.out.println(f1 + " + 5 = " + f1.add(5));
        System.out.println(f1 + " - 5 = " + f1.subtract(5));
        System.out.println(f1 + " * 5 = " + f1.multiply(5));
        System.out.println(f1 + " / 5 = " + f1.divide(5));

        // 🔹 Сложное выражение
        System.out.println("\n=== Сложное выражение ===");
        Fraction result = f1.add(f2).divide(f3).subtract(5);
        System.out.println("f1.add(f2).divide(f3).subtract(5) = " + result);

        sc.close();
    }

    /**
     * Метод безопасного чтения дроби с консоли с обработкой всех ошибок.
     */
    private static Fraction readFraction(Scanner sc, String label) {
        while (true) {
            try {
                System.out.println("\nВведите числитель и знаменатель " + label + " дроби:");

                System.out.print("Числитель: ");
                int num = Integer.parseInt(sc.nextLine().trim());

                System.out.print("Знаменатель: ");
                int den = Integer.parseInt(sc.nextLine().trim());

                // Попытка создать объект Fraction — внутри проверяется знаменатель и знак
                return new Fraction(num, den);

            } catch (NumberFormatException e) {
                System.out.println("❌ Ошибка: нужно вводить целые числа. Попробуйте снова.");
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("❌ Ошибка: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("⚠️ Непредвиденная ошибка: " + e.getMessage());
            }
        }
    }
}
