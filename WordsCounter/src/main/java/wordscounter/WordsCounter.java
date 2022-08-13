package wordscounter;

import java.util.concurrent.ConcurrentHashMap;

public class WordsCounter {

    private ConcurrentHashMap<String, Integer> wordsToCounter = new ConcurrentHashMap<>();

    public static void main(String[] args) {
        WordsCounter wc = new WordsCounter();
        // load text files in parallel
        wc.load("file1.txt", "file2.txt", "file3.txt");
        // display words statistics
        wc.displayStatus();
    }

    private void load(String input1, String input2, String input3) {
        Thread thread1 = startCounterThread(input1);
        Thread thread2 = startCounterThread(input2);
        Thread thread3 = startCounterThread(input3);

        waitForThreads(thread1, thread2, thread3);
    }

    private void waitForThreads(Thread... threads) {
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.err.println("Thread is interrupted");
        }
    }

    private Thread startCounterThread(String input1) {
        CounterThread counterThread1 = new CounterThread(input1, this);
        Thread thread1 = new Thread(counterThread1);
        thread1.start();
        return thread1;
    }

    public synchronized void increaseValue(String word) {
        if (wordsToCounter.putIfAbsent(word, 1) != null) {
            wordsToCounter.replace(word, wordsToCounter.get(word) + 1);
        }
    }

    public void displayStatus() {
        wordsToCounter.forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println("** total: " + wordsToCounter.values().stream().mapToInt(e -> e).sum());
    }
}
