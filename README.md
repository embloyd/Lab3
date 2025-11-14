# Мурай Анастасия ИТ-3 Лабораторная №3
# Задание 1
## Задача 4
### Текст задачи
Измените сущность Дробь из задачи 1.5.5. Реализуйте следующие требования: 
• Дробь не может быть изменена после создания 
• Необходимо корректно обрабатывать отрицательные значения. Учтите, что знаменатель 
не может быть отрицательным.  
Продемонстрируйте работоспособность решения на примерах.
### Алгоритм решения
Добавилась неизменяемость и геттеры.
```java
package lab3;

public final class Fraction {
    private final int numerator; //неизменяемость
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть 0");
        }
        // если знаменатель отрицательный, переносим минус в числитель
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    // НОД
    private int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }
    //операции с другой дробью (возвращают новые объекты)
    public Fraction add(Fraction other) {
        int num = this.numerator * other.denominator + other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public Fraction subtract(Fraction other) {
        int num = this.numerator * other.denominator - other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public Fraction multiply(Fraction other) {
        int num = this.numerator * other.numerator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        int num = this.numerator * other.denominator;
        int den = this.denominator * other.numerator;
        return new Fraction(num, den);
    }

    //Операции с целыми числами
    public Fraction add(int n) {
        return add(new Fraction(n, 1));
    }

    public Fraction subtract(int n) {
        return subtract(new Fraction(n, 1));
    }

    public Fraction multiply(int n) {
        return multiply(new Fraction(n, 1));
    }

    public Fraction divide(int n) {
        if (n == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return divide(new Fraction(n, 1));
    }
```

# Задание 1
## Задача 7
### Текст задачи
Измените сущность Имя из задачи 1.4.5. Гарантируйте, что: 
• Как минимум один параметр будет иметь не null значение и не пустую строку. 
• Имя неизменяемо. 
Продемонстрируйте работоспособность решения на примерах.
### Алгоритм решения
Добавилось final, normalize, проверка, упрощённые конструкторы, метод для обработки строк, геттеры.
```java
package lab3;

public final class Name {
    private final String lastName;
    private final String firstName;
    private final String middleName;

    //главный конструктор
    public Name(String lastName, String firstName, String middleName) {
        //очищаем пробелы и заменяем пустые строки на null
        lastName = normalize(lastName);
        firstName = normalize(firstName);
        middleName = normalize(middleName);

        //хотя бы одно поле должно быть непустым
        if (lastName == null && firstName == null && middleName == null) {
            throw new IllegalArgumentException("Ошибка: хотя бы одно поле должно быть задано");
        }

        this.lastName = lastName;
        this.firstName = firstName;
        this.middleName = middleName;
    }

    //доп конструкторы
    public Name(String firstName) {
        this(null, firstName, null);
    }

    public Name(String lastName, String firstName) {
        this(lastName, firstName, null);
    }

    //метод для обработки строк
    private static String normalize(String value) {
        if (value == null) return null;
        value = value.trim();
        return value.isEmpty() ? null : value;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (lastName != null) sb.append(lastName).append(" ");
        if (firstName != null) sb.append(firstName).append(" ");
        if (middleName != null) sb.append(middleName);
        return sb.toString().trim();
    }
}
```

# Задание 2
## Задача 2
### Текст задачи
Секреты никому нельзя рассказывать. Никому кроме одного самого близкого друга! Необходимо 
описать сущность Секрет, которая будет позволять хранить и передавать некоторый текст только 
одному другому хранителю. 
Состояние сущности описывают следующие сведения:  
• Текст секрета, в виде строки. 
• Имя хранителя секрета, в виде строки 
Инициализация сущности может быть выполнена следующим образом: 
• С указанием имени хранителя секрета и текста секрета, это будет означать создание 
нового секрета. 
• С указанием другого Секрета и именем хранителя секрета. В этом случае мы считаем, что 
секрет был рассказан другому человеку. При передаче секрета должно произойти 
следующее: во-первых, на консоль выводится текст вида “Имя сказал что Секрет”, где Имя 
и Секрет представляют собой имя того кто рассказывает секрет и текст секрета 
соответственно. Во-вторых, текст секрета у нового хранителя должен представлять собой 
копию текста предыдущего хранителя, но с добавлением Х случайных символов в Х 
случайных мест, где Х — это число в диапазоне от 0 до N, а Nв свою очередь это 10% от 
размера исходного текста. 
Поведение сущности описывают следующие действия: 
• Может быть приведен к строке, строковое преставление вида“Имя: это секрет!”, где Имя – 
это конкретное имя хранителя секрета, а остальное простой текст. 
• Можно узнать каким по очереди был данных хранитель секрета. 
• Можно узнать сколько еще человек узнали секрет после текущего хранителя 
• Можно получить имя N-го человек узнавшего секрет, причем N будет положительным для 
случая, когда мы хотим узнать имя следующего узнавшего секрет, и отрицательным если 
предыдущего. 
• Можно узнать разницу в количестве символов текста секрета с N-ым человеком 
Необходимо учесть следующие требования к инкапсуляции: 
• Единственным способом получить текст секрета может быт его вывод на экран при 
инициализации объекта. 
• Секрет может быть передан только одному другому человеку и не должно быть способа 
рассказать один и тот же секрет нескольким людям. 
Продемонстрируйте работоспособность решения на примерах.
### Алгоритм решения
```java
package lab3;

import java.util.Random;

public class Secret {
    private String secretText; //текст
    private String keeperName; //текущ хранитель
    private int orderNumber; //номер хранителя
    private Secret previousKeeper; //предыдущий хранитель
    private Secret nextKeeper; //следующий

    //создание для нового секрета
    public Secret(String keeperName, String secretText) {
        this.keeperName = keeperName;
        this.secretText = secretText;
        this.orderNumber = 1;
    }

    //передача секрета новому хранителю
    public Secret(Secret original, String newKeeper) {
        this.previousKeeper = original; //от кого получили
        this.keeperName = newKeeper;
        this.orderNumber = original.orderNumber + 1;

        //сообщение о передаче
        System.out.println(original.keeperName + " сказал что " + original.secretText);

        //изменение текста
        this.secretText = changeText(original.secretText);

        //у предыдущего хранителя указываем, кому он передал секрет
        original.nextKeeper = this;
    }

    private String changeText(String text) {
        if (text.length() < 3) return text;  //короткий текст не меняем

        Random rand = new Random();
        String newText = text;
        int changes = rand.nextInt(3);

        for (int i = 0; i < changes; i++) {
            int pos = rand.nextInt(newText.length()); //случайная позиция в тексте
            char c = (char)('a' + rand.nextInt(26)); //генерируем букву и вставляем
            newText = newText.substring(0, pos) + c + newText.substring(pos);
        }

        return newText;
    }

    public int getOrderNumber() { //возврат номера текущ хранителя
        return orderNumber;
    }

    public int getPeopleAfter() { //сколько узнали после текущ хранителя
        int count = 0;
        Secret current = this.nextKeeper;
        while (current != null) {
            count++;
            current = current.nextKeeper;
        }
        return count;
    }

    public String getNthPerson(int n) { //поиск имени n-го человека
        if (n == 0) return this.keeperName;

        Secret current = this;
        if (n > 0) {
            for (int i = 0; i < n; i++) {
                if (current.nextKeeper == null) return "Нет";
                current = current.nextKeeper;
            }
        } else {
            for (int i = 0; i < Math.abs(n); i++) {
                if (current.previousKeeper == null) return "Нет";
                current = current.previousKeeper;
            }
        }
        return current.keeperName;
    }

    @Override
    public String toString() {
        return keeperName + ": это секрет!";
    }

    public String getKeeperName() {
        return keeperName;
    }
}
```

# Задание 3
## Задача 1
### Текст задачи
Измените сущность Дробь, полученную в задаче 2.1.4. Гарантируйте, что невозможно создать 
такой подвид дроби, который позволял бы создавать Дроби с изменяемым состоянием.
### Алгоритм решения
```java
package lab3;
public final class Fraction {
    private final int numerator; //неизменяемость
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть 0");
        }
        // если знаменатель отрицательный, переносим минус в числитель
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    public final int getNumerator() {
        return numerator;
    }

    public final int getDenominator() {
        return denominator;
    }

    // НОД

    private final int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    @Override
    public final String toString() {
        return numerator + "/" + denominator;
    }

    //операции с другой дробью (возвращают новые объекты)
    public final Fraction add(Fraction other) {
        int num = this.numerator * other.denominator + other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public final Fraction subtract(Fraction other) {
        int num = this.numerator * other.denominator - other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public final Fraction multiply(Fraction other) {
        int num = this.numerator * other.numerator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public final Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        int num = this.numerator * other.denominator;
        int den = this.denominator * other.numerator;
        return new Fraction(num, den);
    }

    //Операции с целыми числами
    public final Fraction add(int n) {
        return add(new Fraction(n, 1));
    }

    public final Fraction subtract(int n) {
        return subtract(new Fraction(n, 1));
    }

    public final Fraction multiply(int n) {
        return multiply(new Fraction(n, 1));
    }

    public final Fraction divide(int n) {
        if (n == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return divide(new Fraction(n, 1));
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false; 
        Fraction fraction = (Fraction) obj;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public final int hashCode() {
        return 37 * numerator + denominator;
    }
}
```

# Задание 4
## Задача 2
### Текст задачи
Измените сущность Дробь, полученную в задаче 2.3.1. Дробь должна быть подтипом класса 
Number. Данный класс входит в стандартную редакцию языка Java.
### Алгоритм решения
```java
package lab3;

public final class Fraction extends Number {
    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть 0");
        }

        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    //реализация абстрактных методов класса Number
    @Override
    public int intValue() {
        return numerator / denominator;  // целая часть
    }

    @Override
    public long longValue() {
        return (long) numerator / denominator; //целая для больших чисел
    }

    @Override
    public float floatValue() {
        return (float) numerator / denominator; // дробная часть с обычной точностью
    }

    @Override
    public double doubleValue() {
        return (double) numerator / denominator; // дробная часть с высокой точностью
    }

    //геттеры
    public final int getNumerator() {
        return numerator;
    }

    public final int getDenominator() {
        return denominator;
    }

    // НОД
    private final int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }

    @Override
    public final String toString() {
        return numerator + "/" + denominator;
    }

    // Все остальные методы остаются без изменений
    public final Fraction add(Fraction other) {
        int num = this.numerator * other.denominator + other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public final Fraction subtract(Fraction other) {
        int num = this.numerator * other.denominator - other.numerator * this.denominator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public final Fraction multiply(Fraction other) {
        int num = this.numerator * other.numerator;
        int den = this.denominator * other.denominator;
        return new Fraction(num, den);
    }

    public final Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        int num = this.numerator * other.denominator;
        int den = this.denominator * other.numerator;
        return new Fraction(num, den);
    }

    public final Fraction add(int n) {
        return add(new Fraction(n, 1));
    }

    public final Fraction subtract(int n) {
        return subtract(new Fraction(n, 1));
    }

    public final Fraction multiply(int n) {
        return multiply(new Fraction(n, 1));
    }

    public final Fraction divide(int n) {
        if (n == 0) {
            throw new ArithmeticException("Деление на ноль");
        }
        return divide(new Fraction(n, 1));
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fraction fraction = (Fraction) obj;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public final int hashCode() {
        return 31 * numerator + denominator;
    }
}
```

# Задание 5
## Задача 1
### Текст задачи
Разработайте метод, который принимает набор числовых значенийи возвращает их сумму в 
вещественной форме. С использованием данного метода выполните следующие сложения: 
• 2 + 3/5 + 2.3 
• 3.6 + 49/12 + 3 + 3/2 
• 1/3 + 1 
### Алгоритм решения
Использовала готовый класс Fraction.java из задания 4 и прописала в Main.
```java
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
```

# Задание 6
## Задача 1
### Текст задачи
Измените сущность Дробь из задачи 2.4.2. Переопределите метод сравнения объектов по 
состоянию таким образом, чтобы две дроби считались одинаковыми тогда, когда у них 
одинаковые значения числителя и знаменателя.
### Алгоритм решения
Уже реализовано в задании 4.
