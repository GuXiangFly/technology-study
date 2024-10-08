package threadlocaldemo;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.LinkedList;
import java.util.List;

public class TestPhantomReference {

    private static final List<Object> LIST = new LinkedList<>();

    private static final ReferenceQueue<M> QUEUE = new ReferenceQueue<>();

    public static void main(String[] args) {

        PhantomReference<M> phantomReference = new PhantomReference<>(new M(),QUEUE);

        System.out.println(phantomReference.get());

        new Thread(()->{
            while (true){
                LIST.add(new byte[1024*1024]);
                System.out.println(phantomReference.get());
                try {
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        }).start();

        new Thread(()->{
            while (true){
                Reference<? extends M> poll = QUEUE.poll();
                if (poll != null){
                    System.out.println("虚引用被回收了~~~" + poll);
                }
            }
        }).start();



    }
}
