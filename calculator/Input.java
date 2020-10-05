// reads input

import java.util.Scanner;

/**
 * Class Input reads a line from the system console (System.in). It displays a
 * numbered prompt.
 * 
 */

public class Input {

    // Static members and methods
    private static int lineNo = 0;
    static Scanner sc = new Scanner(System.in).useDelimiter("\\n");

    public static Input readInput() {
        return new Input();
    }

    // Instance members and methods
    private final String line;

    private Input() {
        lineNo++;
        System.out.printf("[%d]=> ", lineNo);
        this.line = sc.next();
    }

    public String getLine() {
        return this.line;
    }

    public int getLineNum() {
        return this.lineNo;
    }

}
