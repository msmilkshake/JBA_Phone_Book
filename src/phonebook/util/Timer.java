package phonebook.util;

import java.util.concurrent.TimeUnit;

public class Timer {
    private long startTime;
    private long elapsed;
    
    public void start() {
        startTime = System.currentTimeMillis();
    }
    
    public void stop() {
        elapsed = System.currentTimeMillis() - startTime;
    }
    
    private static long getMins(long time) {
        return TimeUnit.MILLISECONDS.toMinutes(time);
    }
    
    private static long getSecs(long time) {
        long secs = time - getMins(time) * 60000L;
        return TimeUnit.MILLISECONDS.toSeconds(time);
    }
    
    private static long getMs(long time) {
        return time - getMins(time) * 60000L - getSecs(time) * 1000L;
    }
    
    public String toString() {
        return longToString(elapsed);
    }
    
    public long getStartTime() {
        return startTime;
    }
    
    public long getElapsed() {
        return elapsed;
    }
    
    public static String longToString(long time) {
        return getMs(time) + "min. " +
                getSecs(time) + " sec. " +
                getMs(time) + " ms.";
    }
}
