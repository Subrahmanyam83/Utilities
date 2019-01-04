package concepts.Threads;

class ThreadDemo extends Thread {

    public void initiate () {
        Thread t = new Thread(){
            public void run(){
                printCount();
            }
        };
        t.start();
    }

    private synchronized void printCount() {
        for(int i = 5; i > 0; i--) {
            System.out.println("Counter   ---   "  + i );
        }
    }
}

class TestThread {

    public static void main(String args[]) {
        ThreadDemo T1 = new ThreadDemo();
        ThreadDemo T2 = new ThreadDemo();

        T1.initiate();
        T2.initiate();
    }
}
































//class PrintDemo {
//    public synchronized void printCount() {
//        for(int i = 5; i > 0; i--) {
//            System.out.println("Counter   ---   "  + i );
//        }
//    }
//}
//
//class ThreadDemo extends Thread {
//    private Thread t;
//    private String threadName;
//    PrintDemo  PD;
//
//    ThreadDemo( String name,  PrintDemo pd) {
//        threadName = name;
//        PD = pd;
//    }
//
//    public void run() {
//        PD.printCount();
//    }
//
//    public void start () {
//        if (t == null) {
//            t = new Thread (this, threadName);
//            t.start ();
//        }
//    }
//}
//
//class TestThread {
//
//    public static void main(String args[]) {
//        PrintDemo PD = new PrintDemo();
//
//        ThreadDemo T1 = new ThreadDemo( "Thread - 1 ", PD );
//        ThreadDemo T2 = new ThreadDemo( "Thread - 2 ", PD );
//
//        T1.start();
//        T2.start();
//    }
//}
