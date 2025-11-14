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
