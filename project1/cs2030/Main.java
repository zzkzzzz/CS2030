package cs2030;

/**
* The Simulator.
*
* @author  Zheng Zikang
* @version 1.0
* @since   2020-09-23
*/
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import cs2030.simulator.Schedule;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Double> arriveList = new ArrayList<>();
        int numOfServers = sc.nextInt();

        while (sc.hasNextDouble()) {
            double arrivalTime = sc.nextDouble();
            arriveList.add(arrivalTime);
        }

        Schedule schedule = new Schedule(numOfServers, arriveList);
        schedule.printEeventList();
        schedule.printStatistics();
    }
}
