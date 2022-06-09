package threadlocaldemo;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;

public class TestWeakReference {

    private static final List<Object> LIST = new LinkedList<>();

    public static void main(String[] args) {

        //new PhantomReference<>(new Date(), Queue)
        SoftReference<byte[]> m = new SoftReference<>(new byte[1024*1024*10]);

        System.out.println(m.get());
        System.gc();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(m.get());

        byte[] bytes = new byte[1024 * 1024 * 11];
        System.out.println(m.get());
    }
}
