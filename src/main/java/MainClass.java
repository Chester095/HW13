import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Semaphore;

public class  MainClass {
    public static final int CARS_COUNT = 7;
    public static int startCounter = 1;
    public static int finishCounter = CARS_COUNT;
    public static final CountDownLatch cdl = new CountDownLatch(CARS_COUNT);
    public static final Semaphore smp = new Semaphore(CARS_COUNT/2);

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        //создаём гонку
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));

        // создаём несколько участников
        Car[] cars = new Car[CARS_COUNT];

        //для каждого участника задаём Race (длина трассы, длина тунеля, длина дороги) и произвольную скорость(20-29)
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        // запускаем потоки всех участников
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }




    }
}

