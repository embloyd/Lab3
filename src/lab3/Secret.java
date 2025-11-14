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