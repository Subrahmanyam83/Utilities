package concepts;


import org.testng.annotations.Test;

@Test
class MyTest {

        @Test
        public void t1() {
                System.setProperty("dummy.property", "42");

                // Keep printing value of "dummy.property" forever.
                {
                        System.out.println("T111111.."+System.getProperty("dummy.property"));
                        try {
                                Thread.sleep(500);
                        } catch (Exception e) {}
                }
        }

        @Test
        public void t2(){
                System.setProperty("dummy.property", "52");

                // Keep printing value of "dummy.property" forever.
                 {
                        System.out.println("T22222.."+System.getProperty("dummy.property"));
                        try {
                                Thread.sleep(500);
                        } catch (Exception e) {}
                }
        }
}



