import java.util.*;

public class Calculator {

    private static List<String> history = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Calculator!");

        while (true) {
            printMenu();
            System.out.println("Select an operation (or type 'exit' to exit):");
            String choice = scanner.nextLine().trim();

            if (choice.equalsIgnoreCase("exit")) {
                System.out.println("Exiting calculator...");
                break;
            }

            String result = "";
            if (choice.equals("1")) {
                System.out.println("Enter an arithmetic expression (for example, 2 + 3):");
                String input = scanner.nextLine().trim();
                result = calculateBasicArithmetic(input);
            } else if (choice.equals("2")) {
                System.out.println("Enter the base and exponent (eg 2, 3):");
                String input = scanner.nextLine().trim();
                result = calculatePower(input);
            } else if (choice.equals("3")) {
                System.out.println("Enter number:");
                String input = scanner.nextLine().trim();
                result = calculateSqrt(input);
            } else if (choice.equals("4")) {
                System.out.println("Enter number:");
                String input = scanner.nextLine().trim();
                result = calculateAbs(input);
            } else if (choice.equals("5")) {
                System.out.println("Enter number:");
                String input = scanner.nextLine().trim();
                result = calculateRound(input);
            } else if (choice.equalsIgnoreCase("history")) {
                printHistory();
                continue;
            } else {
                System.out.println("Incorrect selection. Please try again.");
                continue;
            }

            System.out.println("Result: " + result);
            history.add(choice + " = " + result);

            System.out.println("Do you want to continue? (y/n):");
            String continueChoice = scanner.nextLine().trim();
            if (continueChoice.equalsIgnoreCase("n")) {
                System.out.println("Thank you for using the Calculator!");
                break;
            }
        }
    }

    private static void printMenu() {
        System.out.println("1. Arithmetic operations (+, -, *, /, %)");
        System.out.println("2. power(base, indicator)");
        System.out.println("3. sqrt(number)");
        System.out.println("4. abs(number)");
        System.out.println("5. round(number)");
        System.out.println("history - Show history");
    }

    private static String calculateBasicArithmetic(String input) {
        String[] parts = input.split(" ");
        if (parts.length != 3) {
            return "Error: Invalid format for arithmetic expression.";
        }

        double num1 = 0;
        double num2 = 0;
        boolean validInput = true;

        // Проверяем, что оба числа действительные
        if (isValidNumber(parts[0])) {
            num1 = Double.parseDouble(parts[0]);
        } else {
            validInput = false;
            return "Error: Invalid format of first number.";
        }

        if (isValidNumber(parts[2])) {
            num2 = Double.parseDouble(parts[2]);
        } else {
            validInput = false;
            return "Error: Invalid format for second number.";
        }

        if (!validInput) {
            return "Error: Invalid numbers.";
        }

        String operator = parts[1];
        double result = 0;
        if (operator.equals("+")) {
            result = num1 + num2;
        } else if (operator.equals("-")) {
            result = num1 - num2;
        } else if (operator.equals("*")) {
            result = num1 * num2;
        } else if (operator.equals("/")) {
            if (num2 == 0) {
                return "Error: Division by zero.";
            }
            result = num1 / num2;
        } else if (operator.equals("%")) {
            if (num2 == 0) {
                return "Error: Division by zero.";
            }
            result = num1 % num2;
        } else {
            return "Error: Invalid operator: " + operator;
        }
        return String.valueOf(result);
    }

    private static String calculatePower(String input) {
        String[] parts = input.split(",");
        if (parts.length != 2) {
            return "Error: Invalid format for power function.";
        }

        double base = 0;
        double exponent = 0;

        if (!isValidNumber(parts[0].trim())) {
            return "Error: Invalid base format.";
        } else {
            base = Double.parseDouble(parts[0].trim());
        }

        if (!isValidNumber(parts[1].trim())) {
            return "Error: Invalid indicator format.";
        } else {
            exponent = Double.parseDouble(parts[1].trim());
        }

        return String.valueOf(Math.pow(base, exponent));
    }

    private static String calculateSqrt(String input) {
        if (!isValidNumber(input)) {
            return "Error: Invalid number format.";
        }

        double number = Double.parseDouble(input.trim());
        if (number < 0) {
            return "Error: You cannot take the square root of a negative number.";
        }
        return String.valueOf(Math.sqrt(number));
    }

    private static String calculateAbs(String input) {
        if (!isValidNumber(input)) {
            return "Error: Invalid number format.";
        }

        double number = Double.parseDouble(input.trim());
        return String.valueOf(Math.abs(number));
    }

    private static String calculateRound(String input) {
        if (!isValidNumber(input)) {
            return "Error: Invalid number format.";
        }

        double number = Double.parseDouble(input.trim());
        return String.valueOf(Math.round(number));
    }

    private static boolean isValidNumber(String str) {
        // Проверяем, является ли строка числом
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static void printHistory() {
        if (history.isEmpty()) {
            System.out.println("History is empty.");
        } else {
            System.out.println("Calculation history:");
            for (String entry : history) {
                System.out.println(entry);
            }
        }
    }
}
