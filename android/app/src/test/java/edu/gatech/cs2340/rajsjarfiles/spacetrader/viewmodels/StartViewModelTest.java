package edu.gatech.cs2340.rajsjarfiles.spacetrader.viewmodels;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StartViewModelTest {

    @Test
    public void testPlayerNameValidWhenEmpty() {

        Boolean playerName = (StartViewModel.playerNameValid(""));
        assertEquals(playerName, false);
    }


    @Test
    public void testPlayerNameValidWhenNull() {

        Boolean playerName = (StartViewModel.playerNameValid(null));
        assertEquals(playerName, false);
    }

    @Test
    public void testPlayerNameValidNotWhiteSpace() {

        Boolean playerName = (StartViewModel.playerNameValid("Your boy 50¢"));
        assertEquals(playerName, false);
    }

    @Test
    public void testPlayerNameValid() {

        Boolean playerName = (StartViewModel.playerNameValid("Tony Kim"));
        assertEquals(playerName, true);
    }


}
