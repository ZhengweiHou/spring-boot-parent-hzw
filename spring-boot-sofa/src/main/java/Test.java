public class Test {
    public static void main(String[] args) throws InterruptedException {
        Integer times = new Integer(args[0]);

        while (times >0){
            System.out.println("sleep times:" + times);
            times --;
            Thread.sleep(1000);
        }

    }
}
