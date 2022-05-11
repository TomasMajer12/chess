package cz.cvut.fel.pjv.game;

import static java.lang.Thread.sleep;

public class ChessTimer{
    private long offset, currentStart;
    private boolean isStopped,running;

    public ChessTimer(){
        offset = 0L;
        currentStart = System.currentTimeMillis();
        isStopped = true;

    }

    public void start() {
        if(isStopped) {
            currentStart = System.currentTimeMillis() - offset;
        }
        running = true;
        isStopped = false;
    }

    public void restart(){
        currentStart =  System.currentTimeMillis() - offset;
    }

    public void stop(){
        if(!isStopped) {
            offset = System.currentTimeMillis() - currentStart;
        }
        running = false;
        isStopped = true;
    }

    public boolean isRunning(){
        return running;
    }

    public long getTime() {
        if(!isStopped){
            return (System.currentTimeMillis() - currentStart) / 1000;
        }else{
            return offset;
        }
    }
}

