package cz.cvut.fel.pjv.game;

public class TimerThread implements Runnable{
    private ChessTimer timer;

    @Override
    public void run() {
        timer = new ChessTimer();
        timer.start();
        while (timer.isRunning()){
            if(timer.getTime() > 15){
                timer.restart();
            }
            System.out.println(timer.getTime());

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
