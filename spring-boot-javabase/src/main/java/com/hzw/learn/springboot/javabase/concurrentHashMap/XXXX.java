import java.util.HashMap;

/**
 * @ClassName HashMapLoopLinkTest
 * @Description TODO
 * @Author houzw
 * @Date 2023/8/9
 **/
public class XXXX {
//    public static void main(String[] args) {
//        HashMap<Long, String> hmap = new HashMap<Long, String>();
//
//        new Thread(()->{
//            for (long i=0;;i++){
//                hmap.get(i);
//            }
//        }).start();
//
//        for (int i=0; i<10;i++){
//            new Thread(()->{
//                hmap.put(1l,"hello");
//            }).start();
//        }
//    }

    public static void main(String[] args) {
        final HashMap<String, Integer> hashMap = new HashMap<>();
        final int numThreads = 10; // 增加线程数量
        final int numOperations = 10000; // 增加操作次数

        Runnable putTask = () -> {
            for (int i = 0; i < numOperations; i++) {
                int value = (int) Thread.currentThread().getId() * numOperations + i;
                hashMap.put(String.valueOf(value), value);
            }
        };

        Runnable getTask = () -> {
            for (int i = 0; i < numOperations; i++) {
                int key = (int) (Math.random() * (numThreads * numOperations));
                Integer value = hashMap.get(String.valueOf(key));
                if (value == null) {
                    System.out.println("Value not found for key: " + key);
                }
            }
        };

        Thread[] putThreads = new Thread[numThreads];
        Thread[] getThreads = new Thread[numThreads];

        for (int i = 0; i < numThreads; i++) {
            putThreads[i] = new Thread(putTask);
            getThreads[i] = new Thread(getTask);
            putThreads[i].start();
            getThreads[i].start();
        }

        for (int i = 0; i < numThreads; i++) {
            try {
                putThreads[i].join();
                getThreads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
