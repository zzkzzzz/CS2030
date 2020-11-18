
// javac -d classes cs2030/simulator/*.java    
// java -cp classes cs2030/Main.java 1 2 2 10 1.0 1.0       
/**
* The Simulator.
*
* @author  Zheng Zikang
* @version 1.0
* @since   2020-09-23
*/

import cs2030.simulator.Schedule;

public class Main {
    /**
     * Main function of the Event Simulator.
     * 
     * @param args user input args
     */
    public static void main(String[] args) {
        // initialize the schedule event according to given user input
        Schedule schedule = Schedule.initializeSchedule(args);
        // print Eevent List
        schedule.printEeventList();
        // print Statistics
        schedule.printStatistics();
    }
}
