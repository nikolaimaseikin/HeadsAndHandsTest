package org.game;

import org.game.Player;
import org.game.Monster;
import org.game.Dice;

import java.util.Random;

public class Game {
    static boolean checkSuccessAttack(int attackModifier){
        for(int i = 0; i < attackModifier; i++){
            Dice dice = new Dice();
            if(dice.getThrow() > 4){
                return true;
            }
        }
        return false;
    }

    static int getAttackModifier(Creature attacker, Creature defender){
        return attacker.getAttack() - defender.getProtection() + 1;
    }

    static int calculateDamageValue(Creature attacker){
        Random random = new Random();
        return random.nextInt((attacker.getMaximumDamage() - attacker.getMinimumDamage()) + 1) + attacker.getMinimumDamage();
    }

    static void startBattle(Creature attacker, Creature defender){
        System.out.println("Определяем успех битвы...");
        int attackModifier = getAttackModifier(attacker, defender);
        if(checkSuccessAttack(attackModifier)){
            int damage = calculateDamageValue(attacker);
            defender.takeDamage(damage);
            System.out.println(String.format("Удача на стороне нападающего! Произведён удар с уроном: %d", damage));
        }
        else {
            System.out.println("Удар не состоялся");
        }
    }

    public static void main(String[] args) {
        boolean switchRole = false;
        Player player = new Player(10, 5, 100, 1, 6);
        Monster monster = new Monster( 8, 6, 100, 1, 7);

        System.out.println("***Start Game***");
        while (player.isAlive() && monster.isAlive()){
            if(!switchRole){
                //Атакует player
                System.out.println("===Атакует Player===");
                startBattle(player, monster);
            }
            else{
                System.out.println("===Атакует Monster===");
                startBattle(monster, player);
            }
            //Если player нуждается в лечении, то он применяет аптечку
            if(player.checkNeedHeal()){
                player.heal();
            }
            //Смена роли нападающего и защищающегося
            switchRole = !switchRole;
            //Вывод очков здоровья после битвы
            System.out.println("Player Health: " + player.getHealth());
            System.out.println("Monster Health: " + monster.getHealth());
        }
        System.out.println("***End Game***");
        //Определение победителя
        if (player.isAlive()) {
            System.out.println("Player wins!");
        } else {
            System.out.println("Monster wins!");
        }
    }
}