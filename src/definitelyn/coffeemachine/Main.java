package definitelyn.coffeemachine;

public class Main {
    public static void main(String[] args) {
        SystemConsole systemConsole = new SystemConsole();

        Machine machine = new Machine(systemConsole);
        machine.serve();

    }
}
