package lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*

        System.out.println("---Задание 1 (4): Дроби---");

        try {
            System.out.println("Введите числитель и знаменатель первой дроби:");
            int num1 = readInt(sc, "Числитель: ");
            int den1 = readNonZeroInt(sc, "Знаменатель: ");
            Fraction f1 = new Fraction(num1, den1);

            System.out.println("Введите числитель и знаменатель второй дроби:");
            int num2 = readInt(sc, "Числитель: ");
            int den2 = readNonZeroInt(sc, "Знаменатель: ");
            Fraction f2 = new Fraction(num2, den2);

            System.out.println("Введите числитель и знаменатель третьей дроби:");
            int num3 = readInt(sc, "Числитель: ");
            int den3 = readNonZeroInt(sc, "Знаменатель: ");
            Fraction f3 = new Fraction(num3, den3);

            //примеры операций
            System.out.println("\n" + f1 + " + " + f2 + " = " + f1.add(f2));
            System.out.println(f1 + " - " + f2 + " = " + f1.subtract(f2));
            System.out.println(f1 + " * " + f2 + " = " + f1.multiply(f2));
            System.out.println(f1 + " / " + f2 + " = " + f1.divide(f2));

            System.out.println(f1 + " + 5 = " + f1.add(5));
            System.out.println(f1 + " - 5 = " + f1.subtract(5));
            System.out.println(f1 + " * 5 = " + f1.multiply(5));
            System.out.println(f1 + " / 5 = " + f1.divide(5));

            //сложное выражение
            Fraction result = f1.add(f2).divide(f3).subtract(5);
            System.out.println("Результат выражения: " + result);

        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

         */

        System.out.println("---Задание 1 (7): Непустые имена---");

        try {
            String last = readText(sc, "Введите фамилию (или оставьте пустым): ", true);
            String first = readText(sc, "Введите имя (или оставьте пустым): ", true);
            String middle = readText(sc, "Введите отчество (или оставьте пустым): ", true);

            Name person = new Name(
                    last.isEmpty() ? null : last,
                    first.isEmpty() ? null : first,
                    middle.isEmpty() ? null : middle
            );

            System.out.println("Создано имя: " + person);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("---Задание 2 (2): Секреты---");

        List<Secret> secrets = new ArrayList<>();
        boolean exit = false;

        while (!exit) {
            System.out.println("Меню:");
            System.out.println("1. Создать новый секрет");
            System.out.println("2. Передать секрет другому человеку");
            System.out.println("3. Показать все секреты");
            System.out.println("4. Информация о секрете");
            System.out.println("0. Назад к предыдущим заданиям");
            System.out.print("Выберите: ");

            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    createNewSecret(secrets, sc);
                    break;
                case 2:
                    shareSecret(secrets, sc);
                    break;
                case 3:
                    showAllSecrets(secrets);
                    break;
                case 4:
                    showSecretInfo(secrets, sc);
                    break;
                case 0:
                    exit = true;
                    break;
                default:
                    System.out.println("Неверный выбор!");
            }
        }

        System.out.println("---Задание 4: Дробь как Number---");

        try {
            System.out.println("Введите дробь для демонстрации методов Number:");
            int num = readInt(sc, "Числитель: ");
            int den = readNonZeroInt(sc, "Знаменатель: ");
            Fraction fraction = new Fraction(num, den);

            System.out.println("Исходная дробь: " + fraction);

            //демонстрация методов класса Number
            System.out.println("fraction.intValue() = " + fraction.intValue() + " (целая часть)");
            System.out.println("fraction.longValue() = " + fraction.longValue());
            System.out.println("fraction.floatValue() = " + fraction.floatValue());
            System.out.println("fraction.doubleValue() = " + fraction.doubleValue());

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        System.out.println("---Задание 5: Полиморфизм и сложение---");

        //демонстрация примеров
        try {
            System.out.println("Пример 1: 2 + 3/5 + 2.3");
            System.out.println("Введите дробь:");
            int num1 = readInt(sc, "Числитель: ");
            int den1 = readNonZeroInt(sc, "Знаменатель: ");
            Fraction f1 = new Fraction(num1, den1);

            double result1 = sum(2, f1, 2.3);
            System.out.println("2 + " + f1 + " + 2.3 = " + result1);

            System.out.println("Пример 2: 3.6 + 49/12 + 3 + 3/2");
            System.out.println("Введите дробь:");
            int num2 = readInt(sc, "Числитель: ");
            int den2 = readNonZeroInt(sc, "Знаменатель: ");
            Fraction f2 = new Fraction(num2, den2);

            System.out.println("Введите дробь:");
            int num3 = readInt(sc, "Числитель: ");
            int den3 = readNonZeroInt(sc, "Знаменатель: ");
            Fraction f3 = new Fraction(num3, den3);

            double result2 = sum(3.6, f2, 3, f3);
            System.out.println("3.6 + " + f2 + " + 3 + " + f3 + " = " + result2);

            System.out.println("Пример 3: 1/3 + 1");
            System.out.println("Введите дробь:");
            int num4 = readInt(sc, "Числитель: ");
            int den4 = readNonZeroInt(sc, "Знаменатель: ");
            Fraction f4 = new Fraction(num4, den4);

            double result3 = sum(f4, 1);
            System.out.println(f4 + " + 1 = " + result3);

        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }

        sc.close();
    }

    private static double sum(Number... numbers) { //принимает любое число, преобразует в double и складывает
        double result = 0;
        for (Number num : numbers) {
            result += num.doubleValue();
        }
        return result;

    }

    private static void createNewSecret(List<Secret> secrets, Scanner sc) {
        System.out.print("Введите имя хранителя: ");
        String name = sc.nextLine();
        System.out.print("Введите текст секрета: ");
        String text = sc.nextLine();

        Secret newSecret = new Secret(name, text);
        secrets.add(newSecret);
        System.out.println("Секрет создан: " + newSecret);
    }

    private static void shareSecret(List<Secret> secrets, Scanner sc) {
        if (secrets.isEmpty()) {
            System.out.println("Нет созданных секретов!");
            return;
        }

        System.out.println("Доступные секреты:");
        for (int i = 0; i < secrets.size(); i++) {
            System.out.println((i+1) + ". " + secrets.get(i));
        }

        System.out.println("Выберите номер секрета: ");
        int num = sc.nextInt() - 1;
        sc.nextLine();

        if (num < 0 || num >= secrets.size()) {
            System.out.println("Неверный номер!");
            return;
        }

        Secret original = secrets.get(num);

        if (original.getPeopleAfter() > 0) {
            System.out.println("Этот секрет уже был передан!");
            return;
        }

        System.out.print("Введите имя нового хранителя: ");
        String newName = sc.nextLine();

        Secret newSecret = new Secret(original, newName);
        secrets.add(newSecret);
        System.out.println("Секрет передан!");
    }

    private static void showAllSecrets(List<Secret> secrets) {
        if (secrets.isEmpty()) {
            System.out.println("Нет секретов!");
            return;
        }

        System.out.println("Все секреты:");
        for (int i = 0; i < secrets.size(); i++) {
            Secret s = secrets.get(i);
            System.out.println((i+1) + ". " + s + " (порядок: " + s.getOrderNumber() + ")");
        }


    }

//вспомогат методы
    private static void showSecretInfo(List<Secret> secrets, Scanner sc) {
        if (secrets.isEmpty()) {
            System.out.println("Нет секретов!");
            return;
        }

        showAllSecrets(secrets);
        System.out.println("Выберите номер секрета: ");
        int num = sc.nextInt() - 1;
        sc.nextLine();

        if (num < 0 || num >= secrets.size()) {
            System.out.println("Неверный номер!");
            return;
        }

        Secret secret = secrets.get(num);
        System.out.println("Информация о секрете:");
        System.out.println("- " + secret);
        System.out.println("- Порядковый номер: " + secret.getOrderNumber());
        System.out.println("- Людей после: " + secret.getPeopleAfter());
        System.out.println("- Следующий человек: " + secret.getNthPerson(1));
        System.out.println("- Предыдущий человек: " + secret.getNthPerson(-1));

    }

    private static int readInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                return Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите целое число.");
            }
        }
    }

    private static int readNonZeroInt(Scanner sc, String prompt) {
        while (true) {
            int value = readInt(sc, prompt);
            if (value != 0) return value;
            System.out.println("Ошибка: знаменатель не может быть 0.");
        }
    }

    private static String readText(Scanner sc, String prompt, boolean allowEmpty) {
        while (true) {
            System.out.print(prompt);
            String input = sc.nextLine().trim();

            if (input.isEmpty() && allowEmpty) return "";
            if (input.matches("[А-Яа-яA-Za-zёЁ\\s-]+")) return input;

            System.out.println("Ошибка: используйте только буквы, пробелы и дефисы.");
        }
    }

}
