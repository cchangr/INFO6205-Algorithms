/*
 * Copyright (c) 2017. Phasmid Software
 */

package edu.neu.coe.info6205.randomwalk;

import java.util.Random;

public class RandomWalk {
    private int x = 0;
    private int y = 0;

    private final Random random = new Random();

    public void move(int dx, int dy) {
        // TODO you need to implement this
        x = x + dx;
        y = y + dy;  
    }

    private void randomWalk(int n) {
        x=0;y=0;
        for (int i = 0; i < n; i++)
            randomMove();
    }

    private void randomMove() {
        // TODO you need to implement this
        int randomnumber = random.nextInt(4);
        switch(randomnumber) {
            case 0:
                move(1,0);
                break;
            case 1:
                move(-1,0);
                break;
            case 2:
                move(0,1);
                break;
            case 3:
                move(0,-1);
                break;
        }
    }

    public double distance() {
        // return 0; // TODO you need to implement this
        double dis = Math.sqrt(x*x + y*y);
        return dis;
    }

    public static void main(String[] args) {
        //int n = Integer.parseInt(args[0]);
       
        int n[] ={25,48,97,125,168,190,220,268,290,346,428,566,877,1440,2580};
        
        RandomWalk walk = new RandomWalk();
        for(int a=0;a < n.length;a++){
            double sum = 0;
            for (int b=0;b<2000;b++){
                walk.randomWalk(n[a]);
                sum+=walk.distance();
            }
           sum = sum/2000;
           System.out.println(n[a] + " steps: " + sum); 
        }
    }
}
