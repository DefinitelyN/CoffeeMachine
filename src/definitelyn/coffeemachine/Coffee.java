package definitelyn.coffeemachine;

public class Coffee {
    int REQUIRED_WATER;
    int REQUIRED_MILK;
    int REQUIRED_BEANS;
    int REQUIRED_CUPS;
    int COST_MONEY;
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
