package concepts.Threads;

public class ThreadConcept {

    public static void main(String args[]){
        Bank b1= new Bank();
        Bank b2= new Bank();

        b1.start();
        b2.start();
    }
}

class Bank extends Thread {

    private Thread thread;

    private void printNumbers(){
        for(int i=0;i<5;i++){
            System.out.println("Printing "+i);
        }
    }

    public void run() {
        /*Just adding synchronize over the method that is being called will make that method to be accessed only by one thread.*/
        synchronized (this) {
            this.printNumbers();
        }
    }

    public void start(){
        thread = new Thread(this);
        thread.start();
    }
}
