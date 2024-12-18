package Producer_consumer;

/**
 * @author zwy
 * @version 1.0
 * @description: TODO
 * @date 2024/8/14 20:28
 */
public class test {
        private static Integer count = 0;
        private static final Integer FULL = 10;
        private static String LOCK = "lock";

        public static void main(String[] args) {
            test test1 = new test();
            new Thread(test1.new Producer()).start();
            new Thread(test1.new Consumer()).start();
            new Thread(test1.new Producer()).start();
            new Thread(test1.new Consumer()).start();
            new Thread(test1.new Producer()).start();
            new Thread(test1.new Consumer()).start();
            new Thread(test1.new Producer()).start();
            new Thread(test1.new Consumer()).start();
        }
        class Producer implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(3000);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    synchronized (LOCK) {
                        while (count == FULL) {
                            try {
                                LOCK.wait();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        count++;
                        System.out.println(Thread.currentThread().getName() + "生产者生产，目前总共有" + count);
                        LOCK.notifyAll();
                    }
                }
            }
        }
        class Consumer implements Runnable {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (LOCK) {
                        while (count == 0) {
                            try {
                                LOCK.wait();
                            } catch (Exception e) {
                            }
                        }
                        count--;
                        System.out.println(Thread.currentThread().getName() + "消费者消费，目前总共有" + count);
                        LOCK.notifyAll();
                    }
                }
            }
        }
    }


