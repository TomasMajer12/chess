package cz.cvut.fel.pjv.game;

public class ChessTimer{
    private long offset, currentStart;
    private boolean isStopped;
    private String color;
    private ChessBoard board;

    public ChessTimer(String color,ChessBoard board){
        offset = 0L;
        currentStart = System.currentTimeMillis();
        isStopped = true;
        this.color = color;
        this.board = board;
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
        if(board.Turn_counter % 2 != 0 && color == "white"){
            if(getTime() < 10){
                return "0:" + "0" + getTime();
            }else if(getTime() < 60){
                return "0:" + getTime();
            }else if(getTime() >= 60 && getTime()%60 < 10){
                return getTime()/60 + ":0" + getTime()%60;
            }else{
                return getTime()/60 + ":" + getTime()%60;
            }
        }else if(board.Turn_counter % 2 == 0 && color == "black"){
            if(getTime() < 10){
                return "0:" + "0" + getTime();
            }else if(getTime() < 60){
                return "0:" + getTime();
            }else if(getTime() >= 60 && getTime()%60 < 10){
                return getTime()/60 + ":0" + getTime()%60;
            }else{
                return getTime()/60 + ":" + getTime()%60;
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

