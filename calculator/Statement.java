
// Statement.java
import java.util.Scanner;
import java.util.function.Function;
import java.util.NoSuchElementException;
import java.util.InputMismatchException;
import java.util.Hashtable;

/**
 * Class Statement is the workhorse of the calculator. It initializes the
 * hashtable to store the command and its associated lambda function. To run the
 * command, its name is used to lookup the table to retrieve the lambda, which
 * is then applied to the arguments.
 * 
 * There is a lot of code to handle errors, which are due to: (a) unknown
 * command, (b) wrong type of arguments, (c) too many or too few arguments. Such
 * code clutters the main code and obscures the key idea. There is room for
 * improvement!
 * 
 */

public class Statement {
	// static members and methods
	private static Hashtable<String, Function<Double[], Double>> commandTable;
	// This stores the function for each command

	private static Hashtable<String, Integer> argsTable;
	// This stores the number of arguments for each command

	public static Statement parse(Input input) {
		return new Statement(input.getLine());
	}

	private static Function<Double[], Double> error = x -> 0.0;

	private static Function<Double[], Double> addition = x -> x[0] + x[1];

	private static Function<Double[], Double> multiplication = x -> x[0] * x[1];

	private static Function<Double[], Double> reciprocal = x -> 1.0 / x[0];

	private static Function<Double[], Double> percentage = x -> x[0] * x[1] / 100.0;

	public static void initialize() {

		commandTable = new Hashtable<>();
		argsTable = new Hashtable<>();

		commandTable.put("err", error);
		argsTable.put("err", 0);

		commandTable.put("add", addition);
		argsTable.put("add", 2);

		commandTable.put("mult", multiplication);
		argsTable.put("mult", 2);

		commandTable.put("recip", reciprocal);
		argsTable.put("recip", 1);

		commandTable.put("%", percentage);
		argsTable.put("%", 2);
	}

	// Instance member and methods
	private String command;
	private Double[] arguments;
	private String message;

	public Statement(String input) {
		// Input format: <command> <arg1> [<arg2>]

		Scanner sc = new Scanner(input);

		// Parse command
		String c = sc.next();
		if (!commandTable.containsKey(c)) {
			this.command = "err";
			this.message = String.format("Bad command! %s", c);
			return;
		}
		this.command = c;

		// Parse arguments
		// some commands may have one argument or two arguments
		int numberArgs = argsTable.get(this.command);
		this.arguments = new Double[numberArgs];

		try {
			for (int i = 0; i < numberArgs; i++) {
				this.arguments[i] = sc.nextDouble();
			}
			if (sc.hasNext()) {
				this.command = "err";
				this.message = "Too many arguments!";
			}

		} catch (InputMismatchException e) {
			this.command = "err";
			this.message = "Bad arguments!";

		} catch (NoSuchElementException e) {
			this.command = "err";
			this.message = "Too few arguments!";
		}
	}

	public Result evaluate() {
		if (!this.command.equals("err")) {
			double r = commandTable.get(this.command).apply(this.arguments);
			this.message = String.format("%f", r);
		}
		return new Result(this.message);
	}

}
