package org.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {
    private Player player;
    @Before
    public void setUp() {
        player = new Player(10, 5, 100, 1, 6);
    }

    @Test
    public void testCheckNeedHealWhenHealthIsLessThan30Percent() {
        // Установим здоровье меньше 30% от максимального
        player.takeDamage((int) (player.getMaxHealth() * 0.71));

        // Проверим, что метод checkNeedHeal возвращает true
        assertTrue(player.checkNeedHeal());
    }

    @Test
    public void testCheckNeedHealWhenHealthIsEqualTo30Percent() {
        // Установим здоровье равным 30% максимального
        player.takeDamage((int) (player.getMaxHealth() * 0.70));

        // Проверим, что метод checkNeedHeal возвращает False
        assertFalse(player.checkNeedHeal());
    }

    @Test
    public void testCheckNeedHealWhenHealthIsGreaterThan30Percent() {
        // Установим здоровье больше 30% максимального
        player.takeDamage((int) (player.getMaxHealth() * 0.31));

        // Проверим, что метод checkNeedHeal возвращает false
        assertFalse(player.checkNeedHeal());
    }

    @Test
    public void testHeal() {
        //Нанесём урон, чтобы здоровье было меньше 30 процентов от максимального
        player.takeDamage((int) (player.getMaxHealth() * 0.72));
        player.heal();

        // Проверим, что здоровье увеличилось
        assertTrue(player.getHealth() == player.getMaxHealth() * 0.3);
        assertEquals(3, 4-player.getHealCounter());

        //Нанесём урон, чтобы здоровье было меньше 30 процентов от максимального
        player.takeDamage((int) (player.getMaxHealth() * 0.75));
        // Попытка лечения после первого успешного лечения
         player.heal();

        // Проверим, здоровье увеличилось, но и аптечек осталось 2
        assertTrue(player.getHealth() == player.getMaxHealth() * 0.3);
        assertEquals(2, 4-player.getHealCounter());
    }
}