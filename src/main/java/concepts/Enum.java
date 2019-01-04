package concepts;

import java.util.concurrent.TimeUnit;

public class Enum {

    int age;
    String name;

    public enum IMAPTimeout {
        MINUTES(TimeUnit.MINUTES),
        SECONDS(TimeUnit.SECONDS);

        private TimeUnit time;

        private IMAPTimeout(TimeUnit time){
            this.time=time;
        }
    }

    public static void main(String args[]){
        System.out.println(IMAPTimeout.MINUTES.time.getClass());

    }

    public Enum(String  name){
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public Enum setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public Enum setAge(int age) {
        this.age = age;
        return this;
    }

}
