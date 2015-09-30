package ohtu.miniprojekti5000.logic;

import java.util.List;
import java.util.Scanner;
import ohtu.miniprojekti5000.domain.ReferenceInterface;

public class IO {
    private final Scanner scanner;

    public IO() {
        scanner = new Scanner(System.in);
    }

    // Anna parametrina arraylist komennoista
    public void printAvailableCommands(int state) {
        switch (state) {
            case 0: // print: "add book"
            {
                System.out.println("available commands: add book");
            }
            break;
            case 1: // print: "add book, make bibtex"
            {
                System.out.println("available commands: add book, make bibtex");
            }
            break;
        }
    }

    public void printReferences(List<ReferenceInterface> references) {
        for (ReferenceInterface ref : references) {
            System.out.println(ref.toString());
        }
    }
    
    public String readLine() {
        return scanner.nextLine();
    }
}
