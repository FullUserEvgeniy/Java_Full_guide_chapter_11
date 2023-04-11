package SuspendResume;

// Приостановка и возобновление современным способом
class NewThread implements Runnable{
    String name;
    Thread t;
    boolean suspendFlag;

    NewThread(String threadName){
        name = threadName;
        t = new Thread(this, name);
        System.out.println("New stream "+name);
    }

    @Override
    public void run() {
        try {
            for (int i=20; i>0; i--){
                System.out.println(name + ": " + i);
                Thread.sleep(150);
                synchronized (this){
                    while (suspendFlag){
                        wait();
                    }
                }
            }
        }catch (InterruptedException e){
            System.out.println(name + " stopped");
        }
        System.out.println(name + " completed");
    }
    synchronized void mySuspend(){
        suspendFlag = true;
    }
    synchronized void myResume(){
        suspendFlag = false;
        notify();
    }
}
public class SuspendResume {
    public static void main(String[] args) {
        NewThread newThread1 = new NewThread("One");
        NewThread newThread2 = new NewThread("Two");

        newThread1.t.start();
        newThread2.t.start();

        try {
            Thread.sleep(1000);
            newThread1.mySuspend();
            System.out.println("Suspending the One stream");
            Thread.sleep(1000);
            newThread1.myResume();
            System.out.println("Resuming the One Stream");
            newThread2.mySuspend();
            System.out.println("Suspending the Two stream");
            Thread.sleep(1000);
            newThread2.myResume();
            System.out.println("Resuming the Two Stream");
        }catch (InterruptedException e){
            System.out.println("Main stream stopped");
        }
        try {
            System.out.println("Waiting for threads to finish");
            newThread1.t.join();
            newThread2.t.join();
        }catch (InterruptedException e){
            System.out.println("Main stream stopped");
        }
        System.out.println("Main stream completed");
    }
}
/*
New stream One
New stream Two
Two: 20
One: 20
Two: 19
One: 19
One: 18
Two: 18
One: 17
Two: 17
Two: 16
One: 16
Two: 15
One: 15
Two: 14
One: 14
Suspending the One stream
Two: 13
Two: 12
Two: 11
Two: 10
Two: 9
Two: 8
Resuming the One Stream
One: 13
Suspending the Two stream
One: 12
One: 11
One: 10
One: 9
One: 8
One: 7
Resuming the Two Stream
Waiting for threads to finish
Two: 7
One: 6
Two: 6
One: 5
Two: 5
One: 4
Two: 4
One: 3
Two: 3
One: 2
Two: 2
One: 1
Two: 1
One completed
Two completed
Main stream completed
 */