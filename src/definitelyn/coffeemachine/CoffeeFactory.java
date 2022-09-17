package definitelyn.coffeemachine;

public class CoffeeFactory {
    public Coffee getCoffee(CoffeeTypes type) {
        Coffee coffee;
        switch (type) {
            case ESPRESSO -> coffee = new Espresso();
            case LATTE -> coffee = new Latte();
            case CAPPUCCINO -> coffee = new Cappuccino();
            default -> coffee = null;
        }
        return coffee;
    }
}
