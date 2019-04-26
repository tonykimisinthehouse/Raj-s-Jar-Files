package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action;

import org.junit.Test;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.TradeGoods;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Weapon;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Coordinate;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.SolarSystem;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Test different actions the player can take during an encounter.
 */
public class PlayerEncounterActionTest {
    SolarSystem ss = new SolarSystem("Test planet", new Coordinate(1,1));


    /**
     * Test the run action.
     */
    @Test
    public void testRunAction() {
        Player player = new Player(new Player.PlayerBuilder("Justin", ss));
        player.getShip().addWeapon(Weapon.BEAM_LASER);

        PlayerEncounterAction pea = new RunAction();
        EncounterState es = new EncounterState();

        while(!es.isOver()) {
            String ret = pea.doAction(player, Ship.getRandomShipWithWeapons(), es);
            assertNotNull(ret);
            assertNotEquals("", ret);
            player.getShip().setHealth(100);
        }
        assertTrue(es.isOver());

        player.getShip().setHealth(5);
        while (player.getShip().getHealth() > 0) {
            String ret = pea.doAction(player, Ship.getRandomShipWithWeapons(), es);
            assertNotNull(ret);
            assertNotEquals("", ret);
        }
    }

    /**
     * Test the attack action where the player wins
     */
    @Test
    public void testAttackActionPlayerWins() {
        Player player = new Player(new Player.PlayerBuilder("Justin", ss));
        player.getShip().addWeapon(Weapon.BEAM_LASER);

        PlayerEncounterAction pea = new AttackAction();
        EncounterState es = new EncounterState();

        Ship other = Ship.getRandomShipWithWeapons();

        while(!es.isOver()) {
            String ret = pea.doAction(player, other, es);
            assertNotNull(ret);
            assertNotEquals("", ret);
            player.getShip().setHealth(100);
            System.out.println(ret);
        }
        assertTrue(es.isOver());

        //test that at least one is dead by the end of the encounter
        assertTrue((player.getShip().getHealth() == 0) ^ (other.getHealth() == 0));
    }

    /**
     * Test the attack action where the police win
     */
    @Test
    public void testAttackActionPoliceWin() {
        Player player = new Player(new Player.PlayerBuilder("Justin", ss));
        player.getShip().addWeapon(Weapon.BEAM_LASER);

        PlayerEncounterAction pea = new AttackAction();
        EncounterState es = new EncounterState();

        Ship other = Ship.getRandomShipWithWeapons();

        while(!es.isOver()) {
            String ret = pea.doAction(player, other, es);
            assertNotNull(ret);
            assertNotEquals("", ret);
            other.setHealth(100);
            System.out.println(ret);
        }
        assertTrue(es.isOver());

        //test that at least one is dead by the end of the encounter
        assertTrue((player.getShip().getHealth() == 0) ^ (other.getHealth() == 0));
    }

    /**
     * Test the submit action where the player has no illegal goods.
     */
    @Test
    public void testSubmitNoIllegalGoods() {
        Player player = new Player(new Player.PlayerBuilder("Justin", ss));
        player.getShip().addWeapon(Weapon.BEAM_LASER);

        PlayerEncounterAction pea = new SubmitAction();
        EncounterState es = new EncounterState();

        Ship other = Ship.getRandomShipWithWeapons();

        while(!es.isOver()) {
            String ret = pea.doAction(player, other, es);
            assertNotNull(ret);
            assertNotEquals("", ret);
        }
        assertTrue(es.isOver());
    }

    /**
     * Test the submit action where the player has illegal goods.
     */
    @Test
    public void testSubmitIllegalGoods() {
        Player player = new Player(new Player.PlayerBuilder("Justin", ss));
        player.getShip().addWeapon(Weapon.BEAM_LASER);
        player.getShip().addGood(
                new Item.ItemBuilder(TradeGoods.NARCOTICS).price(2).quantity(3).build());

        int origMoney = player.getWallet().getCredits();

        PlayerEncounterAction pea = new SubmitAction();
        EncounterState es = new EncounterState();

        Ship other = Ship.getRandomShipWithWeapons();

        while(!es.isOver()) {
            String ret = pea.doAction(player, other, es);
            assertNotNull(ret);
            assertNotEquals("", ret);
        }
        assertTrue(es.isOver());

        //make sure goods are gone and money is 0.2 of the original value
        assertTrue((player.getWallet().getCredits() == 0)
                || (origMoney > player.getWallet().getCredits()));
        assertTrue(!player.getShip().hasIllegalGoods());
    }

    /**
     * Test the bribe action.
     */
    @Test
    public void testBribeAction() {
        Player player = new Player(new Player.PlayerBuilder("Justin", ss));
        player.getShip().addWeapon(Weapon.BEAM_LASER);
        player.getShip().addGood(
                new Item.ItemBuilder(TradeGoods.NARCOTICS).price(2).quantity(3).build());

        int origMoney = player.getWallet().getCredits();

        PlayerEncounterAction pea = new BribeAction();
        EncounterState es = new EncounterState();

        Ship other = Ship.getRandomShipWithWeapons();

        while(!es.isOver()) {
            String ret = pea.doAction(player, other, es);
            assertNotNull(ret);
            assertNotEquals("", ret);
        }
        assertTrue(es.isOver());

        //make sure goods are gone and money is halved
        assertTrue((player.getWallet().getCredits() == 0)
                || (origMoney > player.getWallet().getCredits()));
    }

    /**
     * Test the bribe action.
     */
    @Test
    public void testSurrenderAction() {
        Player player = new Player(new Player.PlayerBuilder("Justin", ss));
        player.getShip().addWeapon(Weapon.BEAM_LASER);
        player.getShip().addGood(
                new Item.ItemBuilder(TradeGoods.NARCOTICS).price(2).quantity(3).build());

        PlayerEncounterAction pea = new SurrenderAction();
        EncounterState es = new EncounterState();

        Ship other = Ship.getRandomShipWithWeapons();

        assertTrue(player.getShip().hasGoods());

        while(!es.isOver()) {
            String ret = pea.doAction(player, other, es);
            assertNotNull(ret);
            assertNotEquals("", ret);
        }
        assertTrue(es.isOver());

        assertTrue(!player.getShip().hasGoods());
    }

    /**
     * Test the bribe action.
     */
    @Test
    public void testSurrenderActionNoGoods() {
        Player player = new Player(new Player.PlayerBuilder("Justin", ss));
        player.getShip().addWeapon(Weapon.BEAM_LASER);

        int origMoney = player.getWallet().getCredits();

        PlayerEncounterAction pea = new SurrenderAction();
        EncounterState es = new EncounterState();

        Ship other = Ship.getRandomShipWithWeapons();

        assertTrue(!player.getShip().hasGoods());

        while(!es.isOver()) {
            String ret = pea.doAction(player, other, es);
            assertNotNull(ret);
            assertNotEquals("", ret);
        }
        assertTrue(es.isOver());

        assertTrue(!player.getShip().hasGoods());

        //make sure goods are gone and money is halved
        assertTrue((player.getWallet().getCredits() == 0)
                || (origMoney > player.getWallet().getCredits()));
    }
}
