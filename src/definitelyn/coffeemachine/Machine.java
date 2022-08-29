package definitelyn.coffeemachine;
import java.util.Scanner;

public class Machine {

    Scanner scanner = new Scanner(System.in);
    int current_water = 400;
    int current_milk = 540;
    int current_beans = 120;
    int current_cups = 9;
    int current_money = 550;

    final String ERROR_GENERAL = "Incorrect action, retry.";
    final String ERROR_WATER = "Sorry, not enough water!";
    final String ERROR_MILK = "Sorry, not enough milk!";
    final String ERROR_BEANS = "Sorry, not enough coffee beans!";
    final String ERROR_CUPS = "Sorry, not enough disposable cups!";
    final String SUCCESS_COOK = "I have enough resources, cooking";

    Espresso espresso = new Espresso();
    Latte latte = new Latte();
    Cappuccino cappuccino = new Cappuccino();

    void serve() {
        while (true) {
            System.out.println("===============");
            System.out.println("Write action (buy, fill, take, " +
                    "remaining, exit):");
            String action = scanner.nextLine().toLowerCase();
            if (action.equals("exit"))
                break;
            switch (action) {
                case "buy" -> System.out.println(buy());
                case "fill" -> fill();
                case "take" -> take();
                case "remaining" -> remaining();
                default -> System.out.println(ERROR_GENERAL);
            }
        }
    }

    String buy() {
        System.out.println("What do you want to buy (espresso, " +
                "latte, cappuccino)? Type \"back\" to return main menu:");
        String choiceCoffee = scanner.nextLine().toLowerCase();
        switch (choiceCoffee) {
            case "espresso" -> {
                return cook(espresso);
            }
            case "latte" -> {
                return cook(latte);
            }
            case "cappuccino" -> {
                return cook(cappuccino);
            }
            case "back" -> {
                return "Returning home";
            }
            default -> {
                return ERROR_GENERAL;
            }
        }
    }

    String cook(Coffee coffee) {
        if (current_water < coffee.REQUIRED_WATER) {
            return ERROR_WATER;
        } else if (current_milk < coffee.REQUIRED_MILK) {
            return ERROR_MILK;
        } else if (current_beans < coffee.REQUIRED_BEANS) {
            return ERROR_BEANS;
        } else if (current_cups < coffee.REQUIRED_CUPS) {
            return ERROR_CUPS;
        } else {
            current_water -= coffee.REQUIRED_WATER;
            current_milk -= coffee.REQUIRED_MILK;
            current_beans -= coffee.REQUIRED_BEANS;
            current_cups -= coffee.REQUIRED_CUPS;
            current_money += coffee.COST_MONEY;
            return SUCCESS_COOK;
        }
    }

    void fill() {
        System.out.println("Write how many ml of water added:");
        current_water += scanner.nextInt();
        System.out.println("Write how many ml of milk added:");
        current_milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee added:");
        current_beans += scanner.nextInt();
        System.out.println("Write how many disposable cups added:");
        current_cups += scanner.nextInt();
    }

    void take() {
        System.out.println("Output cash $" + current_money);
        current_money = 0;
    }

    void remaining() {
        System.out.println("The coffee machine has:");
        System.out.println(current_water + " ml of water");
        System.out.println(current_milk + " ml of milk");
        System.out.println(current_beans + " g of coffee beans");
        System.out.println(current_cups + " disposable cups");
        System.out.println(current_money + " of money");
    }
}
