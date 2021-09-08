

public class Car implements Runnable {
    private static int CARS_COUNT;
    private Race race;
    private int speed;
    private String name;

    public String getName() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public Car(Race race, int speed) {
        this.race = race;
        this.speed = speed;
        CARS_COUNT++;
        this.name = "Участник № " + CARS_COUNT;
    }

    private synchronized void start() {
        if (MainClass.startCounter == 1) {
            MainClass.startCounter--;
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        }
    }

    private synchronized void finish() {
        if (MainClass.finishCounter == CARS_COUNT) {
            MainClass.finishCounter--;
            System.out.println(this.name + " WIN");
        }
        else if(MainClass.finishCounter == 1){
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");}
        else MainClass.finishCounter--;
    }


    @Override
    public void run() {
        try {
            System.out.println(this.name + " готовится");
            Thread.sleep(500 + (int) (Math.random() * 800));
            System.out.println(this.name + " готов");
            MainClass.cdl.countDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            MainClass.cdl.await();   // пока счетчик не приравняется нулю, будем стоять на этой строке
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        start();
        for (int i = 0; i < race.getStages().size(); i++) {
            race.getStages().get(i).go(this);
        }
        finish();

    }

}
