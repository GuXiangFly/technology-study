package cn.guxiangfly.strategy;

public class Dog {

    public Dog(int age) {
        this.age = age;
    }

    int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "age=" + age +
                '}';
    }
}
