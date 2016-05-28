package com.hoa.game.Tools;

import com.hoa.game.HoA;

import java.util.TimerTask;

/**
 * Created by BMW on 28/05/2016.
 */
public class CountDownTimer extends TimerTask {
    private int count;
    private Runnable onFinish;
    private boolean running;

    public CountDownTimer(int count, Runnable onFinish) {
        this.count = count;
        this.onFinish = onFinish;
    }

    @Override
    public void run() {
        count--;
        //@TODO here you can display `count` where you need (alerady in seconds)
        //System.out.println("Count is: " + count);
        if (count <= 0){
            cancel();
            onFinish.run();
        }

    }

    public int getCount(){
        return count;
    }
}