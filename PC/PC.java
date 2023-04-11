package PC;

class Q{
    int n;
    boolean valueSet = false;

    synchronized int get(){
        while (!valueSet){
            try {
                wait();
            }catch (InterruptedException e){
                System.out.println("Intercepted InterruptedException");
            }
        }
        System.out.println("Received: "+n);
        valueSet = false;
        notify();
        return n;
    }

    synchronized void put(int n){
        while (valueSet){
            try {
                wait();
                Thread.sleep(500);
            }catch (InterruptedException e){
                System.out.println("Intercepted InterruptedException");
            }
        }
        this.n = n;
        valueSet = true;
        notify();
        System.out.println("Sent: "+n);
    }
}
class Producer implements Runnable{
    Q q;
    Thread t;

    Producer(Q q){
        this.q = q;
        t = new Thread(this, "Producer");
    }

    @Override
    public void run() {
        int i = 0;

        while (true){
            q.put(i++);
        }
    }
}
class Consumer implements Runnable{
    Q q;
    Thread t;

    Consumer(Q q){
        this.q = q;
        t = new Thread(this, "Consumer");
    }

    @Override
    public void run() {
        while (true){
            q.get();
        }
    }
}
public class PC {
    public static void main(String[] args) {
        Q q = new Q();
        Producer p = new Producer(q);
        Consumer c = new Consumer(q);

        p.t.start();
        c.t.start();

        System.out.println("Press <Ctrl+C> for stop program");
    }
}
/*
Press <Ctrl+C> for stop program
Sent: 0
Received: 0
Sent: 1
Received: 1
Sent: 2
Received: 2
Sent: 3
Received: 3
.....
.....
 */
//321
