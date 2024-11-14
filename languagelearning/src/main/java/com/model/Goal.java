package com.model;

public class Goal {
    private GoalType goalType;
    private int tracker;
    private int goalAmt;
    private boolean isWeekly;

    public Goal(GoalType goalType, int goalAmt){
        this.goalType=goalType;
        this.tracker=0;
        this.goalAmt=goalAmt;
        this.isWeekly=false;
    }

    public GoalType getGoalType(){
        return goalType;
    }

    public boolean metGoal(int tracker){
        return tracker>=goalAmt;
    }

    public void updateProgress(boolean addProgress){
        if(addProgress==true)
            tracker++;
    }

    public int currentGoalProgress(){
        return tracker;
    }

    public void setWeekly(boolean isWeekly){
        this.isWeekly=isWeekly;
    }

    public String toString(){
        return "Goal: "+ goalType+
                "\n Goal Amount: "+ goalAmt+
                "\nCurrent progress: "+ tracker;
    }
}
