package lab3;

public final class Fraction {
    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("❌ Знаменатель не может быть 0");
        }

        // если знаменатель отрицательный — переносим минус в числитель
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        int gcd = gcd(Math.abs(numerator), denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    // ✅ Геттеры (инкапсуляция)
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    // 🔧 Вспомогательный метод — НОД
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // 📄 Преобразование в строку
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    // ➕ ➖ ✖️ ➗ Операции с другой дробью
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
            throw new ArithmeticException("❌ Деление на ноль");
        }
        int num = this.numerator * other.denominator;
        int den = this.denominator * other.numerator;
        return new Fraction(num, den);
    }

    // 💬 Операции с целыми числами
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
            throw new ArithmeticException("❌ Деление на ноль");
        }
        return divide(new Fraction(n, 1));
    }
}
