package definitelyn.coffeemachine;

public class Machine {


    ICoffeeMachineControl _control;

    Machine (ICoffeeMachineControl control) {
        _control = control;
    }

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
            _control.write("===============");
            _control.write("Write action (\"B\" for buy, " +
                            "\"F\" for fill, \"T\" for take, " +
                    "\"R\" for remaining, \"E\" for exit):");
            String action = _control.read();
            if (action.equals("e"))
                break;
            switch (action) {
                case "b" -> _control.write(buy());
                case "f" -> fill();
                case "t" -> take();
                case "r" -> remaining();
                default -> _control.write(ERROR_GENERAL);
            }
        }
    }

    String buy() {
        _control.write("What do you want to buy (\"E\" for espresso, " +
                "\"L\" for latte, \"C\" for cappuccino)? " +
                "Type \"R\" to return main menu:");
        String choiceCoffee = _control.read();
        switch (choiceCoffee) {
            case "e" -> {
                return cook(espresso);
            }
            case "l" -> {
                return cook(latte);
            }
            case "c" -> {
                return cook(cappuccino);
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
            _control.write("Write how many ml of water added:");
            current_water += Integer.parseInt(_control.read());
            _control.write("Write how many ml of milk added:");
            current_milk += Integer.parseInt(_control.read());
            _control.write("Write how many grams of coffee added:");
            current_beans += Integer.parseInt(_control.read());
            _control.write("Write how many disposable cups added:");
            current_cups += Integer.parseInt(_control.read());
        } catch (Exception e) {
            _control.write(ERROR_GENERAL);
        }
    }

    void take() {
        _control.write("Output cash $" + current_money);
        current_money = 0;
    }

    void remaining() {
        _control.write("The coffee machine has:");
        _control.write(current_water + " ml of water");
        _control.write(current_milk + " ml of milk");
        _control.write(current_beans + " g of coffee beans");
        _control.write(current_cups + " disposable cups");
        _control.write(current_money + " of money");
    }
}
