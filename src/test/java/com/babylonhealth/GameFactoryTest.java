package com.babylonhealth;

import org.junit.Test;

public class GameFactoryTest {

    @Test
    public void testBuild() throws Exception {
        new GameFactory().createGame(getClass().getResourceAsStream("/example.field"));

    }
}
