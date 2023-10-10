package org.game;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiceTest {
    private Dice dice;

    @Before
    public void setUp() {
        dice = new Dice();
    }

    @Test
    public void testGetThrow() {
        // Проведем множество бросков кубика и убедимся, что результаты находятся в правильном диапазоне
        for (int i = 0; i < 1000; i++) {
            int result = dice.getThrow();
            assertTrue(result >= 1);
            assertTrue(result <= 6);
        }
    }
}