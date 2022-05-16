package cz.cvut.fel.pjv.game;

public class ChessTimer{
    private long offset, currentStart;
    private boolean isStopped;
    private String color;
    private ChessBoard board;
    private int maxTime;

    public ChessTimer(String color,ChessBoard board,int maxTime){
        offset = 0L;
        currentStart = System.currentTimeMillis();
        isStopped = true;
        this.color = color;
        this.board = board;
        this.maxTime = maxTime;
    }

    public void start() {
        if(isStopped) {
            currentStart = System.currentTimeMillis() - offset;
        }
        isStopped = false;
    }

    public void restart(){
        currentStart =  System.currentTimeMillis() - offset;
    }

    public void stop(){
        if(!isStopped) {
            offset = System.currentTimeMillis() - currentStart;
        }
        isStopped = true;
    }

    public String get_formated_time(){
        long currentTime = maxTime - getTime();
        if(board.Turn_counter % 2 != 0 && color == "white"){
            if(currentTime < 10){
                return "0:" + "0" + currentTime;
            }else if(currentTime < 60){
                return "0:" + currentTime;
            }else if(currentTime >= 60 && currentTime%60 < 10){
                return currentTime/60 + ":0" + currentTime%60;
            }else{
                return currentTime/60 + ":" + currentTime%60;
            }
        }else if(board.Turn_counter % 2 == 0 && color == "black"){
            if(currentTime < 10){
                return "0:" + "0" + currentTime;
            }else if(currentTime < 60){
                return "0:" + currentTime;
            }else if(currentTime >= 60 && currentTime%60 < 10){
                return currentTime/60 + ":0" + currentTime%60;
            }else{
                return currentTime/60 + ":" + currentTime%60;
            }
        }else{
            restart();
            return "0:00";
        }
    }

    public long getTime() {
        if(!isStopped){
            return (System.currentTimeMillis() - currentStart) / 1000;
        }else{
            return offset;
        }
    }

}

