package definitelyn.coffeemachine;
import java.util.Scanner;

public class SystemConsole implements ICoffeeMachineControl {
    Scanner scanner = new Scanner(System.in);

    public String read() {
        return scanner.nextLine().toLowerCase();
    }

    public void write(String output) {
        System.out.println(output);
    }
}
