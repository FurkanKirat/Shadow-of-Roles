package com.rolegame.game.services;

import com.rolegame.game.gamestate.Time;

public class TimeService {
    private int dayCount = 1;
    private Time time = Time.NIGHT;

    public void toggleTimeCycle(){
        switch (time) {
            case DAY -> {
                time = Time.VOTING;
            }
            case VOTING -> time = Time.NIGHT;
            case NIGHT -> {
                time = Time.DAY;
                dayCount++;
            }
        }

    }

    public int getDayCount() {
        return dayCount;
    }


    public Time getTime() {
        return time;
    }

}
