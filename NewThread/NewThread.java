package NewThread;

// Создание второго потока путем расширения класса Thread
public class NewThread extends Thread{
    NewThread(){
        //создать новый поток
        super("Demo Thread");
        System.out.println("Дочерний поток "+this);
    }
    public void run(){
        try {
            for (int i=5; i>0; i--){
                System.out.println("Дочерний поток "+i);
                Thread.sleep(500);
            }
        }catch (InterruptedException e){
            System.out.println("Дочерний поток прерван");;
        }
        System.out.println("Завершение дочернего потока");
    }
}
class ExtendThread{
    public static void main(String[] args) {
        NewThread newThread = new NewThread();
        newThread.start();
        try {
            for (int i=5; i>0; i--){
                System.out.println("Дочерний поток "+i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            System.out.println("Главный поток прерван");
        }
        System.out.println("Завершение главного потока");
    }
}
/*
Дочерний поток Thread[#23,Demo Thread,5,main]
Дочерний поток 5
Дочерний поток 5
Дочерний поток 4
Дочерний поток 3
Дочерний поток 4
Дочерний поток 2
Дочерний поток 3
Дочерний поток 1
Завершение дочернего потока
Дочерний поток 2
Дочерний поток 1
Завершение главного потока
 */
