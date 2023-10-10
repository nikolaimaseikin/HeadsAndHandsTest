package org.game;
import java.util.Random;

public class Dice {
    public int getThrow(){
        Random random = new Random();
        return random.nextInt(6) + 1;
    }
}
