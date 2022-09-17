package definitelyn.coffeemachine;

public class Machine {

    private final ICoffeeMachineControl iControl;

    Machine (ICoffeeMachineControl control) {
        iControl = control;
    }

    int current_water = 5000;
    int current_milk = 540;
    int current_beans = 120;
    int current_cups = 9;
    int current_money = 0;

    final String ERROR_GENERAL = "Incorrect action, retry.";
    final String ERROR_WATER = "Sorry, not enough water!";
    final String ERROR_MILK = "Sorry, not enough milk!";
    final String ERROR_BEANS = "Sorry, not enough coffee beans!";
    final String ERROR_CUPS = "Sorry, not enough disposable cups!";
    final String SUCCESS_COOK = "I have enough resources, cooking";

    void serve() {
        while (true) {
            iControl.write("===============");
            iControl.write("Write action (\"B\" for buy, " +
                            "\"F\" for fill, \"T\" for take, " +
                    "\"R\" for remaining, \"E\" for exit):");
            String action = iControl.read();
            if (action.equals("e"))
                break;
            switch (action) {
                case "b" -> iControl.write(buy());
                case "f" -> {
                    fill();
                    remaining();
                }
                case "t" -> take();
                case "r" -> remaining();
                default -> iControl.write(ERROR_GENERAL);
            }
        }
    }

    CoffeeFactory factory = new CoffeeFactory();
    String buy() {
        iControl.write("What do you want to buy (\"E\" for espresso, " +
                "\"L\" for latte, \"C\" for cappuccino)? " +
                "Type \"R\" to return main menu:");
        String choiceCoffee = iControl.read();
        switch (choiceCoffee) {
            case "e" -> {
                return cook(factory.getCoffee(CoffeeTypes.ESPRESSO));
            }
            case "l" -> {
                return cook(factory.getCoffee(CoffeeTypes.LATTE));
            }
            case "c" -> {
                return cook(factory.getCoffee(CoffeeTypes.CAPPUCCINO));
            }
            case "r" -> {
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
        try {
            iControl.write("Write how many ml of water added:");
            current_water += Integer.parseInt(iControl.read());
            iControl.write("Write how many ml of milk added:");
            current_milk += Integer.parseInt(iControl.read());
            iControl.write("Write how many grams of coffee added:");
            current_beans += Integer.parseInt(iControl.read());
            iControl.write("Write how many disposable cups added:");
            current_cups += Integer.parseInt(iControl.read());
        } catch (Exception e) {
            iControl.write(ERROR_GENERAL);
        }
    }

    void take() {
        iControl.write("Output cash $" + current_money);
        current_money = 0;
    }

    void remaining() {
        iControl.write("The coffee machine has:");
        iControl.write(current_water + " ml of water");
        iControl.write(current_milk + " ml of milk");
        iControl.write(current_beans + " g of coffee beans");
        iControl.write(current_cups + " disposable cups");
        iControl.write(current_money + " of money");
    }
}
