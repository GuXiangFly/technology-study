package threadlocaldemo;

public class M {
    @Override
    protected void finalize() throws Throwable {
        System.out.println("finalize~");
        super.finalize();
    }
}
