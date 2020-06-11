package concepts.Threads;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;


public class MyTest {

        @Test(invocationCount = 100)
        public void testOne() {

                Thread t1 = new classOne(200);
                Thread t2 = new classOne(400);

                t1.start();

                t2.start();


        }

}



class classOne extends Thread {



        int a =100;

        public classOne(int a) {
                this.a = a;
        }


        public int getValue(){
               return a;
        }

        @Override
        public void run() {
                Date myDate = new Date();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

                System.out.println(getValue() + "--------"+simpleDateFormat.format(myDate));
                System.out.println(getValue());
        }
}





