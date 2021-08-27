import java.util.ArrayList;
import java.util.Arrays;

public class MainClass {
    public static final int CARS_COUNT = 4;

    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");

        //создаём гонку
        Race race = new Race(new Road(60), new Tunnel(), new Road(40));

        // создаём несколько участников
        Car[] cars = new Car[CARS_COUNT];
        //TODO выяснить что делает Race
        //для каждого участника задаём Race и произвольную скорость(20-29)
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10));
        }

        // запускаем потоки всех участников
        for (int i = 0; i < cars.length; i++) {
            new Thread(cars[i]).start();
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
//        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

