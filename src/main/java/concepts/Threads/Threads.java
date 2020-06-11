package concepts.Threads;

class TestThread {
    public static void main(String args[]) throws InterruptedException {

        ThreadDemo T1 = new ThreadDemo( "Thread - 1" );
        ThreadDemo T2 = new ThreadDemo( "Thread - 2" );

        T1.start();
        //Thread.sleep(5000L);
        T2.start();

        try {
            T1.join();
            T2.join();
        } catch ( Exception e) {
            System.out.println("Interrupted");
        }
    }

}


class ThreadDemo extends Thread {
    private Thread t;
    private String name;

    ThreadDemo( String name) {
        this.name = name;
    }

    public void run() {
        //printCount();
        //System.out.println("Thread " + name + " exiting.");
        if(name.equalsIgnoreCase("Thread - 1")){
            System.out.println("Setting T1");
            System.setProperty("age","20");
        }
        if(name.equalsIgnoreCase("Thread - 2")){
            System.out.println("Setting T2");
            System.setProperty("age","30");
        }
        //System.out.println(System.getProperty("age"));

        if(name.equalsIgnoreCase("Thread - 1")){
            System.out.println("T1"+System.getProperty("age"));

        }
        if(name.equalsIgnoreCase("Thread - 2")){
            System.out.println("T2"+System.getProperty("age"));

        }
        if(name.equalsIgnoreCase("Thread - 1")){
            System.out.println("T1"+System.getProperty("age"));

        }
    }

    public void start () {
            t = new Thread (this, name);
            t.start ();
    }
//
//    public synchronized void printCount() {
//            for(int i = 5; i > 0; i--) {
//                System.out.println("Counter   ---   "  + i );
//            }
//    }
}



