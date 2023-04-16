package org.example;

import org.junit.Rule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GhostTest {


    @org.junit.jupiter.api.Test
    void haunt() {
        // confirm the ghost's health increased by 3 when haunt is called and confirm the correct string is returned
        Ghost testGhost = new Ghost(20,0,3);
        assertEquals("health has increased by 3",testGhost.haunt());
        assertEquals(23,testGhost.getHealth());
    }


    @Test
    void ghostAttack() {
        Player testPlayer = new Player("Luther", 10, 1, 3);
        Ghost testGhost = new Ghost(20,0,3);
        testGhost.ghostAttack(testPlayer);
        assertEquals("the attack does no damage: ghost_attack:"+testGhost.atk+"player_defense:"+testPlayer.def, testGhost.ghostAttack(testPlayer));
    }



    @org.junit.jupiter.api.Test
    void learnToSpeak() {
        Player mockPlayer = mock(Player.class);
        when(mockPlayer.loseAtkPower()).thenReturn("player lost 1 attack power");

        Ghost testGhost = new Ghost(20,0,3);
        testGhost.learnToSpeak(mockPlayer);

        // we want to confirm the instance of the testGhost object was set to true
        assertTrue(testGhost.canSpeak);

        // we can confirm this as well by calling sayBoo()
        testGhost.sayBoo(mockPlayer);
        assertEquals("BOO!!", testGhost.sayBoo(mockPlayer));
    }
}