import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static String calc(String input) throws Exception {
        input = input.trim();

        String regex = "(\\d+)\\s*([+\\-*/])\\s*(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(input);

        if (!matcher.matches()) {
            throw new Exception("Некоректное выражение! Используёте формат: число операция число");
        }

        int number1;
        int number2;
        String operation = matcher.group(2);

        try {
            number1 = Integer.parseInt(matcher.group(1));
            number2 = Integer.parseInt(matcher.group(3));
        } catch (NumberFormatException e) {
            throw new Exception("Введены не числа");
        }

        if (operation.equals("/") && number2 == 0) {
            throw new Exception("Деление на ноль запрещено!");
        }

        if (number1 < 1 || number1 > 10 || number2 < 1 || number2 > 10) {
            throw new Exception("Числа должны быть от 1 до 10 включительно!");
        }

        int result = switch (operation) {
            case "+" -> number1 + number2;
            case "-" -> number1 - number2;
            case "*" -> number1 * number2;
            case "/" -> number1 / number2;
            default -> throw new Exception("Неизвестная операция");
        };

        return String.valueOf(result);
    }

    // дл ручной проверки
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("====КАЛЬКУЛЯТОР \"Неофит\"====");

        while (true) {
            System.out.println("\nВведите выражение (или exit для выхода):");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                System.out.println("Хорошо, калькулятор завершает работу");
                break;
            }

            try {
                String result = calc(input);
                System.out.println("Результат:" + result);
            } catch (Exception e) {
                System.out.println("Ошибка:" + e.getMessage());
                break;
            }

            System.out.println("-".repeat(30));
        }
        scanner.close();
    }
}