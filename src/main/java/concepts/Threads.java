package concepts;

class TestThread {
    public static void main(String args[]) {



        ThreadDemo T1 = new ThreadDemo( "Thread - 1 " );
        ThreadDemo T2 = new ThreadDemo( "Thread - 2 " );

        T1.start();
        T2.start();

        // wait for threads to end
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
        printCount();
        System.out.println("Thread " + name + " exiting.");
    }

    public void start () {
            t = new Thread (this, name);
            t.start ();
    }

    public synchronized void printCount() {
            for(int i = 5; i > 0; i--) {
                System.out.println("Counter   ---   "  + i );
            }
    }
}


