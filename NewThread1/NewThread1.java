package NewThread1;

// Создание второго потока
class NewThread1 implements Runnable{
    Thread t;

    NewThread1(){
        // Создать новый, второй поток
        t = new Thread(this, " Demo Thread");
        System.out.println("Дочерний поток : " + t);
    }
    // Это точка входа для второго потока .
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println("Дочерний поток : " + i);
                Thread.sleep(500);
                }
            } catch (InterruptedException e) {
            System.out.println("Дочерний поток прерван");
        }
        System.out.println("Завершение дочернего потока");
    }
}

class ThreadDemo {
    public static void main(String[] args ){
        NewThread1 nt = new NewThread1(); // создать новый поток
        nt.t.start();// запустить поток
        try {
            for (int i=5; i>0; i--) {
                System.out.println("Главный поток: "+i);
                Thread.sleep(1000);
            }
        } catch (InterruptedException е ) {
            System.out.println("Главный поток прерван" ) ;
        }
        System.out.println("Завершение главного потока" ) ;
    }
}
/*
Дочерний поток : Thread[#23, Demo Thread,5,main]
Дочерний поток : 5
Главный поток: 5
Дочерний поток : 4
Главный поток: 4
Дочерний поток : 3
Дочерний поток : 2
Главный поток: 3
Дочерний поток : 1
Завершение дочернего потока
Главный поток: 2
Главный поток: 1
Завершение главного потока
 */