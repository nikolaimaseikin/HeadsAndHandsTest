package org.game;
public abstract class Creature {
    protected int attack;
    protected int protection;
    protected int maxHealth;
    protected int minimumDamage;
    protected int maximumDamage;
    protected int health;

    Creature(int attack, int protection, int maxHealth, int minimumDamage, int maximumDamage){
        if(isDataValid(attack, protection, maxHealth, minimumDamage, maximumDamage)){
            this.attack = attack;
            this.protection = protection;
            this.maxHealth = maxHealth;
            this.health = maxHealth;
            this.minimumDamage = minimumDamage;
            this.maximumDamage = maximumDamage;
        }
        else {
            throw new IllegalArgumentException("Введены некорректные параметры, передаваемые в конструктор.");
        }
    }

    protected static boolean isDataValid(int attack, int protection, int maxHealth, int minimumDamage, int maximumDamage){
        //Валидация параметра attack
        if(attack < 1 || attack > 30){
            return false;
        }
        //Валидация параметра protection
        if(protection < 1 || protection > 30){
            return false;
        }
        //Валидация параметра maxHealth
        if(maxHealth < 0){
            return false;
        }
        //Валидация диапазона урона
        if(minimumDamage <= 0){
           return false;
        }
        else{
            if (maximumDamage < minimumDamage){
               return false;
            }
        }
        return true;
    }

    public int getAttack(){
        return attack;
    }

    public int getMaxHealth() {
        return maxHealth;
    }

    public int getProtection() {
        return protection;
    }

    public int getMinimumDamage() {
        return minimumDamage;
    }

    public int getHealth() {
        return health;
    }

    public int getMaximumDamage() {
        return maximumDamage;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0;
        }
    }

    public boolean isAlive(){
        return health > 0;
    }

}
