import java.util.ArrayList;
import java.util.List;

/**
 * Lab: JVM Memory Analysis
 * 
 * TODO: Complete this application to demonstrate memory behavior
 * 
 * Run with: java -Xms128m -Xmx256m -Xlog:gc*:file=gc.log MemoryLabApp
 */
public class MemoryLabApp {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== JVM Memory Lab ===");
        printMemoryStatus("Initial");

        List<byte[]> memoryBlocks = new ArrayList<>();

        // TODO: Implement the following
        // 1. Allocate memory in a loop (e.g., 1MB chunks)
        // 2. Print memory status after each allocation
        // 3. Handle OutOfMemoryError gracefully
        // 4. Add a small delay between allocations for observation

        final int CHUNK_SIZE = 1024 * 1024; // 1MB
        int count = 0;
        try {
            while (true) {
                memoryBlocks.add(new byte[CHUNK_SIZE]);
                count++;
                printMemoryStatus("After allocation " + count);
                Thread.sleep(500); // 0.5 second delay
            }
        } catch (OutOfMemoryError e) {
            System.out.println("OutOfMemoryError caught after " + count + " allocations.");
            printMemoryStatus("OutOfMemoryError");
        }

        printMemoryStatus("Final");
    }

    /**
     * Helper method to print current memory status
     */
    private static void printMemoryStatus(String label) {
        Runtime runtime = Runtime.getRuntime();
        long maxMemory = runtime.maxMemory() / (1024 * 1024);
        long totalMemory = runtime.totalMemory() / (1024 * 1024);
        long freeMemory = runtime.freeMemory() / (1024 * 1024);
        long usedMemory = totalMemory - freeMemory;

        System.out.printf("[%s] Max: %d MB, Total: %d MB, Used: %d MB, Free: %d MB%n",
                label, maxMemory, totalMemory, usedMemory, freeMemory);
    }
}
