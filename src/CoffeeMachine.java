import java.util.Scanner;

public class CoffeeMachine {
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

    public static void main(String[] args) {
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        coffeeMachine.serve();
    }

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
        Espresso espresso = new Espresso();
        Latte latte = new Latte();
        Cappuccino cappuccino = new Cappuccino();
        System.out.println("What do you want to buy (espresso, " +
                "latte, cappuccino)? Type \"back\" to return main menu:");
        String choiceCoffee = scanner.nextLine().toLowerCase();
        switch (choiceCoffee) {
            case "espresso" -> {
                return espresso.cook();
            }
            case "latte" -> {
                return latte.cook();
            }
            case "cappuccino" -> {
                return cappuccino.cook();
            }
            case "back" -> {
                return "Returning home";
            }
            default -> {
                return ERROR_GENERAL;
            }
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

    class Coffee {
        int REQUIRED_WATER;
        int REQUIRED_MILK;
        int REQUIRED_BEANS;
        int REQUIRED_CUPS;
        int COST_MONEY;

        String cook() {
            if (current_water < this.REQUIRED_WATER) {
                return ERROR_WATER;
            } else if (current_milk < this.REQUIRED_MILK) {
                return ERROR_MILK;
            } else if (current_beans < this.REQUIRED_BEANS) {
                return ERROR_BEANS;
            } else if (current_cups < this.REQUIRED_CUPS) {
                return ERROR_CUPS;
            } else {
                current_water -= this.REQUIRED_WATER;
                current_milk -= this.REQUIRED_MILK;
                current_beans -= this.REQUIRED_BEANS;
                current_cups -= this.REQUIRED_CUPS;
                current_money += this.COST_MONEY;
                return SUCCESS_COOK;
            }
        }
    }

    class Espresso extends Coffee {
        Espresso() {
            this.REQUIRED_WATER = 215;
            this.REQUIRED_MILK = 0;
            this.REQUIRED_BEANS = 16;
            this.REQUIRED_CUPS = 1;
            this.COST_MONEY = 4;
        }
    }

    class Latte extends Coffee {
        Latte() {
            this.REQUIRED_WATER = 350;
            this.REQUIRED_MILK = 75;
            this.REQUIRED_BEANS = 20;
            this.REQUIRED_CUPS = 1;
            this.COST_MONEY = 7;
        }
    }

    class Cappuccino extends Coffee {
        Cappuccino() {
            this.REQUIRED_WATER = 200;
            this.REQUIRED_MILK = 100;
            this.REQUIRED_BEANS = 12;
            this.REQUIRED_CUPS = 1;
            this.COST_MONEY = 6;
        }
    }
}
