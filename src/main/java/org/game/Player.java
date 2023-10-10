package org.game;

import org.game.Healable;
import org.game.Creature;

public class Player extends Creature implements Healable {
    private int heal_counter;

    Player(int attack, int protection, int health, int minimumDamage, int maximumDamage) {
        super(attack, protection, health, minimumDamage, maximumDamage);
    }

    public int getHealCounter() {
        return heal_counter;
    }

    public boolean checkNeedHeal(){
        if(health < (maxHealth * 0.3)){
            return true;
        }
        return false;
    }

    @Override
    public void heal() {
        if(heal_counter < 4){
            health = (int) (this.maxHealth * 0.3);
            heal_counter++;
            System.out.println(String.format("Player применил аптечку. Аптечек осталось %d", 4-heal_counter));
        }
        else{
            System.out.println("Лечение не удалось. Аптечек не осталось");
        }
    }
}
