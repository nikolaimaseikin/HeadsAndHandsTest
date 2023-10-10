package org.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CreatureTest {
    private Creature creature;

    @Before
    public void setUp() throws Exception {
       creature = new Creature(10, 5, 100, 1, 6) {
            // Переопределение абстрактных методов для анонимного подкласса Creature
            @Override
            public void takeDamage(int damage) {
                super.takeDamage(damage);
            }

            @Override
            public boolean isAlive() {
                return super.isAlive();
            }
        };
    }

    @Test
    public void isDataValid() {
        // Правильные параметры
        assertTrue(Creature.isDataValid(10, 5, 100, 1, 6));

        // Некорректное значение attack (меньше 1)
        assertFalse(Creature.isDataValid(0, 5, 100, 1, 6));

        // Некорректное значение attack (больше 30)
        assertFalse(Creature.isDataValid(31, 5, 100, 1, 6));

        // Некорректное значение protection (меньше 1)
        assertFalse(Creature.isDataValid(10, 0, 100, 1, 6));

        // Некорректное значение protection (больше 30)
        assertFalse(Creature.isDataValid(10, 31, 100, 1, 6));

        // Некорректное значение maxHealth (отрицательное)
        assertFalse(Creature.isDataValid(10, 5, -100, 1, 6));

        // Некорректное значение minimumDamage (меньше 1)
        assertFalse(Creature.isDataValid(10, 5, 100, 0, 6));

        // Некорректное значение maximumDamage (меньше minimumDamage)
        assertFalse(Creature.isDataValid(10, 5, 100, 6, 5));
    }

    @Test
    public void takeDamage() {
        int initialHealth = creature.getHealth();

        // Наносим урон с положительным значением
        int damage = 20;
        creature.takeDamage(damage);

        // Убедимся, что здоровье уменьшилось на правильное количество
        assertEquals(initialHealth - damage, creature.getHealth());

        // Попытаемся нанести урон больше текущего здоровья
        damage = 200;
        creature.takeDamage(damage);

        // Убедимся, что здоровье стало равным 0
        assertEquals(0, creature.getHealth());
    }

    @Test
    public void testIsAliveWhenHealthIsGreaterThanZero() {
        // Убедимся, что существо живо при здоровье больше 0
        assertTrue(creature.isAlive());

        // Уменьшим здоровье, но оставим его больше 0
        creature.takeDamage(20);

        // Проверим, что существо всё равно живо
        assertTrue(creature.isAlive());
    }

    @Test
    public void testIsAliveWhenHealthIsZero() {
        // Устанавливаем здоровье существа в 0
        creature.takeDamage(creature.getHealth());

        // Проверяем, что существо мертво
        assertFalse(creature.isAlive());
    }

    @Test
    public void testIsAliveWhenHealthIsNegative() {
        // Устанавливаем отрицательное здоровье существа
        creature.takeDamage(creature.getHealth() + 10);

        // Проверяем, что существо всё равно мертво
        assertFalse(creature.isAlive());
    }
}