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
        CounterThread counterThread1 = new CounterThread(input1, this);
        CounterThread counterThread2 = new CounterThread(input2, this);
        CounterThread counterThread3 = new CounterThread(input3, this);

        Thread thread1 = new Thread(counterThread1);
        thread1.start();
        Thread thread2 = new Thread(counterThread2);
        thread2.start();
        Thread thread3 = new Thread(counterThread3);
        thread3.start();

        try {
            thread1.join();
            thread2.join();
            thread3.join();
        } catch (InterruptedException e) {
            System.err.println("Thread is interrupted");
        }
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
