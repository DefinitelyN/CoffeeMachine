import java.util.Scanner;

public class CoffeeMachine {
    static Scanner scanner = new Scanner(System.in);

    static int water = 400;
    static int milk = 540;
    static int beans = 120;
    static int cups = 9;
    static int money = 550;

    public static void main(String[] args) {
        serve();
    }

    static void serve() {

        while (true) {
            System.out.println("Write action (buy, fill, take, " +
                    "remaining, exit):");
            String action = scanner.nextLine().toLowerCase();
            if (action.equals("exit"))
                break;
            switch (action) {
                case "buy" -> buy();
                case "fill" -> fill();
                case "take" -> take();
                case "remaining" -> print();
                default -> System.out.println("Недопустимое действие");
            }
        }
    }

    static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, " +
                "2 - latte, 3 - cappuccino, back - to main menu:");
        String choiceCoffee = scanner.nextLine().toLowerCase();
        switch (choiceCoffee) {
            case "1" -> {
                if (water < 250) {
                    System.out.println("Sorry, not enough water!");
                } else if (beans < 16) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                } else {
                    System.out.println("I have enough resources, " +
                            "making you a coffee!");
                    water -= 250;
                    beans -= 16;
                    money += 4;
                    cups -= 1;
                }
            }
            case "2" -> {
                if (water < 350) {
                    System.out.println("Sorry, not enough water!");
                } else if (milk < 75) {
                    System.out.println("Sorry, not enough milk!");
                } else if (beans < 20) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                } else {
                    System.out.println("I have enough resources, " +
                            "making you a coffee!");
                    water -= 350;
                    milk -= 75;
                    beans -= 20;
                    money += 7;
                    cups -= 1;
                }
            }
            case "3" -> {
                if (water < 200) {
                    System.out.println("Sorry, not enough water!");
                } else if (milk < 100) {
                    System.out.println("Sorry, not enough milk!");
                } else if (beans < 12) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (cups < 1) {
                    System.out.println("Sorry, not enough disposable cups!");
                } else {
                    System.out.println("I have enough resources, " +
                            "making you a coffee!");
                    water -= 200;
                    milk -= 100;
                    beans -= 12;
                    money += 6;
                    cups -= 1;
                }
            }
            default -> System.out.println("Недопустимое действие");
        }
    }

    static void fill() {
        System.out.println("Write how many ml of water you want to add:");
        water += scanner.nextInt();
        System.out.println("Write how many ml of milk you want to add:");
        milk += scanner.nextInt();
        System.out.println("Write how many grams of coffee " +
                "beans you want to add:");
        beans += scanner.nextInt();
        System.out.println("Write how many disposable cups of " +
                "coffee you want to add:");
        cups += scanner.nextInt();
    }

    static void take() {
        System.out.println("I gave you $" + money);
        money = 0;
    }

    static void print() {
        System.out.println("The coffee machine has:");
        System.out.println(water + " ml of water");
        System.out.println(milk + " ml of milk");
        System.out.println(beans + " g of coffee beans");
        System.out.println(cups + " disposable cups");
        System.out.println(money + " of money");
    }

}
