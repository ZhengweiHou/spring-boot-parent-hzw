
/**
 * @ClassName asproftest
 * @Description TODO
 * @Author houzw
 * @Date 2023/10/9
 **/
public class Asproftest {
    public static void main(String[] args) {

        for (int i =0; i<3; i++){
            new Thread(() -> {
                while (true){
                    try {
                        test1();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
        for (int i =0; i<3; i++){
            new Thread(() -> {
                while (true){
                    try {
                        test2();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }).start();
        }
        while (true){
            try {
                test3();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

    }

    public static void test1() throws InterruptedException {
        Thread.sleep(10);
    }

    public static void test2() throws InterruptedException {
        Thread.sleep(30);
    }

    public static void test3() throws InterruptedException {
        Thread.sleep(50);
    }
}
