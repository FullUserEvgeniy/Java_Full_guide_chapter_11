package NewThread3;

// Использование join() для ожидани я окончания потоков
public class NewThread3 implements Runnable{
    String name;
    Thread thread;

    NewThread3(String name){
        this.name = name;
        thread = new Thread(this, this.name);
        System.out.println("New stream: "+thread);
    }

    @Override
    public void run() {
        try {
            for (int i=5; i>0; i--){
                System.out.println(name+" : "+i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            System.out.println("Stream stopped: "+name);
        }
        System.out.println("Stream completed: "+name);
    }
}
class DemoJoin{
    public static void main(String[] args) {
        NewThread3 newThread1 = new NewThread3("One");
        NewThread3 newThread2 = new NewThread3("Two");
        NewThread3 newThread3 = new NewThread3("Three");

        newThread1.thread.start();
        newThread2.thread.start();
        newThread3.thread.start();

        System.out.println("Stream One is running: "+newThread1.thread.isAlive());
        System.out.println("Stream Two is running: "+newThread2.thread.isAlive());
        System.out.println("Stream Three is running: "+newThread3.thread.isAlive());

        try {
            System.out.println("Waiting for threads to finish");
            newThread1.thread.join();
            newThread2.thread.join();
            newThread3.thread.join();
        }catch (InterruptedException e){
            System.out.println("Maim stream stopped");
        }
        System.out.println("Stream One is running: "+newThread1.thread.isAlive());
        System.out.println("Stream Two is running: "+newThread2.thread.isAlive());
        System.out.println("Stream Three is running: "+newThread3.thread.isAlive());
        System.out.println("Main stream completed");
    }
}
/*
New stream: Thread[#23,One,5,main]
New stream: Thread[#24,Two,5,main]
New stream: Thread[#25,Three,5,main]
Stream One is running: true
Stream Two is running: true
Stream Three is running: true
Waiting for threads to finish
One : 5
Three : 5
Two : 5
Three : 4
One : 4
Two : 4
Three : 3
One : 3
Two : 3
Three : 2
One : 2
Two : 2
Two : 1
Three : 1
One : 1
Stream completed: Three
Stream completed: One
Stream completed: Two
Stream One is running: false
Stream Two is running: false
Stream Three is running: false
Main stream completed
 */