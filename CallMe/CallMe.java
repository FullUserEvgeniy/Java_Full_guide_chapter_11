package CallMe;

// Синхронизация метода call() для правильного вывода строк
public class CallMe {
    synchronized void call(String message){
        System.out.print("["+message);
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            System.out.println("STOPPED");
        }
        System.out.println("]");
    }
}
class Caller implements Runnable{
    String message;
    CallMe target;
    Thread thread;

    public Caller(CallMe target, String message){
        this.message = message;
        this.target = target;
        thread = new Thread(this);
    }

    @Override
    public void run() {
        target.call(message);
    }
}
class Synch{
    public static void main(String[] args) {
        CallMe target = new CallMe();
        Caller caller1 = new Caller(target, "Hello");
        Caller caller2 = new Caller(target, "Synchronized");
        Caller caller3 = new Caller(target, "World");

        caller1.thread.start();
        caller2.thread.start();
        caller3.thread.start();

        try {
            caller1.thread.join();
            caller2.thread.join();
            caller3.thread.join();
        }catch (InterruptedException e){
            System.out.println("STOPPED");
        }
    }
}
/*
[Hello]
[World]
[Synchronized]
 */