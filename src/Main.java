
import java.util.*;


public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Thread> threads = new ArrayList<>();
        Runnable runnable = () -> {
            String[] texts = new String[5];
            for (int i = 0; i < texts.length; i++) {
                texts[i] = generateText("aab", 30_000);
            }
            long startTs = System.currentTimeMillis(); // start time
            for (String text : texts) {
                int maxSize = 0;
                for (int i = 0; i < text.length(); i++) {
                    for (int j = 0; j < text.length(); j++) {
                        if (i >= j) {
                            continue;
                        }
                        boolean bFound = false;
                        for (int k = i; k < j; k++) {
                            if (text.charAt(k) == 'b') {
                                bFound = true;
                                break;
                            }
                        }
                        if (!bFound && maxSize < j - i) {
                            maxSize = j - i;
                        }
                    }
                }
                System.out.println(text.substring(0, 100) + " -> " + maxSize);
                long endTs = System.currentTimeMillis(); // end time

                System.out.println("Time: " + (endTs - startTs) + "ms");

            }
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        };
        Thread thread = new Thread(runnable);
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        Thread thread3 = new Thread(runnable);
        thread.start();
        thread1.start();
        thread2.start();
        thread3.start();
        for (Thread threades : threads) {
            try {
                thread.join(); // зависаем, ждём когда поток объект которого лежит в thread завершится
                thread1.join();
                thread2.join();
                thread3.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        String[] texts = new String[5];
        for (int i = 0; i < texts.length; i++) {
            texts[i] = generateText("aab", 30_000);
        }

        long startTs = System.currentTimeMillis(); // start time
        for (String text : texts) {
            int maxSize = 0;
            for (int i = 0; i < text.length(); i++) {
                for (int j = 0; j < text.length(); j++) {
                    if (i >= j) {
                        continue;
                    }
                    boolean bFound = false;
                    for (int k = i; k < j; k++) {
                        if (text.charAt(k) == 'b') {
                            bFound = true;
                            break;
                        }
                    }
                    if (!bFound && maxSize < j - i) {
                        maxSize = j - i;
                    }
                }
            }
            System.out.println(text.substring(0, 100) + " -> " + maxSize);
        }
        long endTs = System.currentTimeMillis(); // end time

        System.out.println("Time: " + (endTs - startTs) + "ms");

    }


    public static String generateText(String letters, int length) {
        Random random = new Random();
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < length; i++) {
            text.append(letters.charAt(random.nextInt(letters.length())));
        }
        return text.toString();
    }
}