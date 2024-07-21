package performance;

import java.util.function.Supplier;

public class PerformanceTester {

    /**
     * Measures and returns the execution time of a given algorithm.
     *
     * @param algorithm The algorithm to be tested, provided as a Supplier.
     * @return The time taken to execute the algorithm, in milliseconds.
     */
    public static long measureExecutionTime(Supplier<Void> algorithm) {
        long startTime = System.currentTimeMillis();
        algorithm.get();
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
}
