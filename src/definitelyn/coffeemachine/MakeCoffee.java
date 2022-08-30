package definitelyn.coffeemachine;

public class MakeCoffee {
    public static void main(String[] args) {
        SystemConsole systemConsole = new SystemConsole();
        Machine room256 = new Machine(systemConsole);
        room256.serve();
    }
}
