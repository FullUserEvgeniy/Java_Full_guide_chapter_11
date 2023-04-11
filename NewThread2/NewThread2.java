package NewThread2;

// Создание множества потоков
public class NewThread2 implements Runnable{
    String name;
    Thread thread;

    NewThread2(String name){
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
class MultiThreadDemo{
    public static void main(String[] args) {
        NewThread2 newThread1 = new NewThread2("One");
        NewThread2 newThread2 = new NewThread2("Two");
        NewThread2 newThread3 = new NewThread2("Three");

        newThread1.thread.start();
        newThread2.thread.start();
        newThread3.thread.start();

        try {
            Thread.sleep(10000);
        }catch (InterruptedException e){
            System.out.println("Main stream stopped");
        }
        System.out.println("Main stream completed");
    }
}
/*
New stream: Thread[#23,One,5,main]
New stream: Thread[#24,Two,5,main]
New stream: Thread[#25,Three,5,main]
One : 5
Three : 5
Two : 5
Three : 4
Two : 4
One : 4
Three : 3
Two : 3
One : 3
One : 2
Two : 2
Three : 2
One : 1
Three : 1
Two : 1
Stream completed: Two
Stream completed: One
Stream completed: Three
Main stream completed
 */