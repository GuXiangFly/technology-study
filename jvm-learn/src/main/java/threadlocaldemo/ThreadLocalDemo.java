package threadlocaldemo;

public class ThreadLocalDemo {

    static ThreadLocal threadLocal = new ThreadLocal();
    public static void main(String[] args) {
        ThreadLocal<Person> tl = new ThreadLocal<>();
        ThreadLocal<Person> tl1 = new ThreadLocal<>();
        tl.set(new Person("jack"));
        tl1.set(new Person("rose"));
        Person person = tl.get();
        Person person1 = tl1.get();
        System.out.println(person1);
    }
}
