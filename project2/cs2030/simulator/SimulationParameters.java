package cs2030.simulator;

/**
 * Stores the parameters of the simulation.
 */

// NOT USING
// CodeCrunch not allow to declare public static final attribute
public class SimulationParameters {
    public static final double SERVICE_TIME = 1.0;
    // ARRIVE → SERVE → DONE
    // ARRIVE → WAIT → SERVE → DONE
    // ARRIVE → LEAVE
    // Priority 0: Arrive
    // Priority 1: Wait
    // Priority 2: Serve
    // Priority 3: Done, Leave
    public static final int ARRIVE_EVENT_PRIORITY = 0;
    public static final int WAIT_EVENT_PRIORITY = 1;
    public static final int SERVE_EVENT_PRIORITY = 2;
    public static final int DONE_EVENT_PRIORITY = 3;
    public static final int LEAVE_EVENT_PRIORITY = 3;

}
