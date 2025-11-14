/*
package lab3;
//для задания 1(7) появилось final и геттеры
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
    // в задании 3(1) final чтобы нельзя было переопределить в подклассах, если они бы были
    private final int gcd(int a, int b) {
        while (b != 0) {
            int t = b;
            b = a % b;
            a = t;
        }
        return a;
    }
// для задания 3(1) тут везде final
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

    //для задания 3(1) добавляем equals и hashCode для корректного сравнения дробей
    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true; //если тот же самый объект
        if (obj == null || getClass() != obj.getClass()) return false; //если пустой или не он
        Fraction fraction = (Fraction) obj;
        return numerator == fraction.numerator && denominator == fraction.denominator; //сравнение
    }

    @Override
    public final int hashCode() { //для соозд ячеек
        return 37 * numerator + denominator;
    }
}
*/
//задача 4(2)
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
