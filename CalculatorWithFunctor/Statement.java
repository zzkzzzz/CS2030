// Statement.java

import java.util.Scanner;
import java.util.function.Function;
import java.util.Hashtable;

/**
   Class Statement is the workhorse of the calculator.
   It initializes the hashtable to store the command and its associated
   lambda function. To run the command, its name is used to lookup the table
   to retrieve the lambda, which is then applied to the arguments.

   There are only 2 places to detect for problems:
   i. when the command given is unknown
   ii. when there are too many arguments

   In both cases we simply throw an exception. We no longer have to
   handle exceptions thrown by the scanner.

   This class is immutable: we never change the state; instead,
   we return a new instance containing the new state.
 */

public class Statement {
    // static members and methods
    private static Hashtable<String, Function<Double[],Double>> commandTable;
    // This stores the function for each command
    
    private static Hashtable<String, Integer> argsTable;
    // This stores the number of arguments for each command

    public static void initialize() {

	commandTable = new Hashtable<>();
	argsTable = new Hashtable<>();

	commandTable.put("add", x -> x[0] + x[1]);
	argsTable.put("add", 2);
	
	commandTable.put("mult", x -> x[0] * x[1]);
	argsTable.put("mult", 2);
	
	commandTable.put("recip", x -> 1.0 / x[0]);
	argsTable.put("recip", 1);
	
	commandTable.put("%", x -> x[0] * x[1] / 100.0);
	argsTable.put("%", 2);
    }
    
    // Instance member and methods
    private final String command;
    private final Double[] arguments;
    private final Scanner scanner;

    public Statement(Input input) {
        this(new Scanner(input.getLine()), null, null);
    }

    private Statement(Scanner sc, String c, Double[] args) {
        this.scanner = sc;
        this.command = c;
        this.arguments = args;
    }
    
    public Statement parseCommand() {
        String c = scanner.next();
        if (!commandTable.containsKey(c))
            throw new RuntimeException(c);//unknown command
        
        return new Statement(this.scanner, c, null);
    }

    public Statement parseArguments() {
	int numberArgs = argsTable.get(this.command);
	Double[] args = new Double[numberArgs];
        for (int i = 0; i < numberArgs; i++) {
            args[i] = scanner.nextDouble();
        }

        if (scanner.hasNext()) //too many arguments
            throw new IllegalArgumentException(                                               String.format("Too many arguments: %s", scanner.nextLine()));

        return new Statement(this.scanner, this.command, args);
    }

    public Result evaluate() {
        double r = commandTable.get(this.command).apply(this.arguments);
        return new Result(String.format("%f", r));
    }


}

