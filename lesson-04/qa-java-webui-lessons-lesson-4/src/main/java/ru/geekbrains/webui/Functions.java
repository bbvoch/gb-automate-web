package ru.geekbrains.webui;

//класс с функциями для тестирования
public class Functions {
    //1 функция для тестирования
    public boolean isNumberEven(Integer number) {
        return number % 2 == 0;
    }

    public boolean isPositive(Integer number) {
        return number > 0;
    }

    public boolean isPrime(Integer number) {
        if (number < 0) {
            return false;
        } else if (number == 1) {
            return true;
        } else {
            for (int i = 2; i <= Math.sqrt(number); i++) {
                if (number % i == 0) {
                    return false;
                }
            }
            return true;
        }
    }

    public boolean isPalindrome(String word) {
        //конец рекурсии
        if (word.length() < 2)
            return true;
        //проверяем, что начальный символ равен конечному
        if (word.charAt(0) != word.charAt(word.length() - 1))
            return false;
        //вызов рекурсии
        return isPalindrome(word.substring(1, word.length() - 1));
    }
}
