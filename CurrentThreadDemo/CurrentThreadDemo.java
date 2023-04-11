package CurrentThreadDemo;

// Управление главным потоком
public class CurrentThreadDemo {
    public static void main(String[] args) {
        Thread t = Thread.currentThread();
        System.out.println("Текущий поток: "+t);
        //изменить имя потока
        t.setName("My Thread");
        System.out.println("После изменения имени: "+t);
        try {
            for (int i=5; i>0; i--){
                System.out.println(i);
                Thread.sleep(1000);
            }
        }catch (InterruptedException e){
            System.out.println("Главный поток прерван");
        }
    }
}
/*
Текущий поток: Thread[#1,main,5,main]
После изменения имени: Thread[#1,My Thread,5,main]
5
4
3
2
1
 */