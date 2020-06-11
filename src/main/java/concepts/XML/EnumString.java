package concepts.XML;

import java.util.concurrent.TimeUnit;

public class EnumString {


    public enum IMAPTimeout {
        MINUTES("minutes"),
        SECONDS("seconds");

        private String time;

        private IMAPTimeout(String time){
            this.time=time;
        }
        private IMAPTimeout(){

        }
    }

    public static void main(String args[]){
        System.out.println(IMAPTimeout.MINUTES.toString());
        System.out.println(IMAPTimeout.MINUTES);

    }

//    public EnumString(String  name){
//        this.name=name;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public EnumString setName(String name) {
//        this.name = name;
//        return this;
//    }
//
//    public int getAge() {
//        return age;
//    }
//
//    public EnumString setAge(int age) {
//        this.age = age;
//        return this;
//    }

}
