package Deadlock;

class A{
    synchronized void foo(B b){
        String name = Thread.currentThread().getName();
        System.out.println(name + " вошел в A.foo()");
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            System.out.println("A stopped");
        }
        System.out.println(name + " пытается вызвать B.last()");
        b.last();
    }
    synchronized void last(){
        System.out.println("Внутри A.last()");
    }
}
class B{
    synchronized void bar(A a){
        String name = Thread.currentThread().getName();
        System.out.println(name + " вошел в B.bar()");
        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            System.out.println("B stopped");
        }
        System.out.println(name + " пытается вызвать A.last()");
    }
    synchronized void last(){
        System.out.println("Внутри B.last()");
    }
}
public class Deadlock implements Runnable{
    A a = new A();
    B b = new B();
    Thread t;

    Deadlock(){
        Thread.currentThread().setName("Main Thread");
        t = new Thread(this, "RacingThread");
    }
    void deadlockStart(){
        t.start();
        a.foo(b);
        System.out.println("Back in The Main Stream");
    }

    @Override
    public void run() {
        b.bar(a);
        System.out.println("Back in the next stream");
    }

    public static void main(String[] args) {
        Deadlock dl = new Deadlock();
        dl.deadlockStart();
    }
}
