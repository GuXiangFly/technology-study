package cn.guxiangfly.study.threadlocal;

public class TestThreadLocal {

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();


        new Thread(new Runnable() {
            public void run() {
                threadLocal.set("thread1");
                System.out.println(Thread.currentThread().getId() + ":" + threadLocal.get());
            }
        }).start();


        for (int i = 0; i < 10; i++) {

            int finalI = i;
            new Thread(new Runnable() {
                public void run() {
                    threadLocal.set("value:" + finalI);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println( "thread id is:" + finalI + "---" + threadLocal.get());
                }
            }).start();
        }



        for (int i = 0; i < 20; i++) {

            int finalI = i;
            new Thread(new Runnable() {
                public void run() {
                    TrafficCenterThreadLocal.setWorkType("realtime");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println( "thread workType is: realtime"  + "---" + TrafficCenterThreadLocal.getWorkType());
                }
            }).start();
        }

        for (int i = 0; i < 20; i++) {

            int finalI = i;
            new Thread(new Runnable() {
                public void run() {
                    TrafficCenterThreadLocal.setWorkType("offline");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println(  "thread workType is: offline"  + "---" + TrafficCenterThreadLocal.getWorkType());
                }
            }).start();
        }




    }
}
