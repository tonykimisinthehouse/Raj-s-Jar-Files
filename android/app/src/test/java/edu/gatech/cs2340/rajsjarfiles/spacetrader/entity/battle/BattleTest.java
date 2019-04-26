package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.battle;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Scanner;

import edu.gatech.cs2340.rajsjarfiles.spacetrader.R;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.Item;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.market.TradeGoods;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Player;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Ship;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.Weapon;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.AttackAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.BribeAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.EncounterState;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.PlayerEncounterAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.RunAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.SubmitAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.player.action.SurrenderAction;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.Coordinate;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe.SolarSystem;
import edu.gatech.cs2340.rajsjarfiles.spacetrader.views.EncounterActivity;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class BattleTest {

    SolarSystem ss = new SolarSystem("Test planet", new Coordinate(1,1));

    @Test
    public void testBattleManagerConstructor() {

        Player player = new Player(new Player.PlayerBuilder("Justin", ss));

        final String expectedStartText = "Battle has started.";
        final String expectedExecuteTurnText = "Turn has executed.";

        BattleManager bm = new BattleManager(player) {
            @Override
            public String startBattle() {
                return expectedStartText;
            }

            @Override
            public String executeTurn(PlayerEncounterAction pea, EncounterState es) {
                return expectedExecuteTurnText;
            }
        };

        assertEquals(expectedStartText, bm.startBattle());
        assertEquals(expectedExecuteTurnText, bm.executeTurn(new RunAction()
                , new EncounterState()));

        Ship ship = bm.getOtherShip();
        assertNotNull(ship);
        assertTrue(!ship.getWeapons().isEmpty());
        assertTrue(ship.getHealth() > 0);
    }

    @Test
    public void testPoliceBattleManager() {
        Player player = new Player(new Player.PlayerBuilder("Justin", ss));

        BattleManager bm = new PoliceBattleManager(player);

        String startText = bm.startBattle();
        assertNotNull(startText);
        assertNotEquals("", startText);

        String executeText = bm.executeTurn(new AttackAction(), new EncounterState());
        assertNotNull(executeText);
        assertNotEquals("", executeText);
    }

    @Test
    public void testRunAction() {
        Player player = new Player(new Player.PlayerBuilder("Justin", ss));
        player.getShip().addWeapon(Weapon.BEAM_LASER);

        BattleManager bm = new PoliceBattleManager(player);
        bm.startBattle();

        EncounterState es = new EncounterState();

        while (!es.isOver()) {
            String ret = bm.executeTurn(new RunAction(), es);
            assertNotNull(ret);
            assertNotEquals("", ret);
        }
        assertTrue(es.isOver());
    }

    @Test
    public void testAttackAction() {
        Player player = new Player(new Player.PlayerBuilder("Justin", ss));
        player.getShip().addWeapon(Weapon.BEAM_LASER);

        BattleManager bm = new PoliceBattleManager(player);
        bm.startBattle();

        EncounterState es = new EncounterState();

        while (!es.isOver()) {
            String ret = bm.executeTurn(new AttackAction(), es);
            assertNotNull(ret);
            assertNotEquals("", ret);
        }
        assertTrue(es.isOver());

        //test that at least one is dead by the end of the encounter
        Ship other = bm.getOtherShip();
        assertTrue(player.getShip().getHealth() == 0 ^ other.getHealth() == 0);
    }

    //public static void main(String[] args) {
    public void testPoliceEncounter() {
        Scanner scan = new Scanner(System.in);

        Player.PlayerBuilder playerBuilder = new Player.PlayerBuilder("Justin", ss);
        Player player = new Player(playerBuilder);
        player.getShip().addWeapon(Weapon.PULSE_LASER);
        player.getShip().addGood(new Item.ItemBuilder(TradeGoods.NARCOTICS).quantity(1).price(2)
                .build());
        player.getShip().addGood(new Item.ItemBuilder(TradeGoods.FIREARMS).quantity(4).price(4)
                .build());
        player.getShip().addGood(new Item.ItemBuilder(TradeGoods.ORE).quantity(2).price(18)
                .build());

        BattleManager bm = new PoliceBattleManager(player);
        System.out.println(bm.startBattle());
        System.out.println("The test is beginning :O");

        String option = "";
        while (!"q".equals(option) && !"Q".equals(option)) {

            System.out.println("---------------------------------------");
            System.out.println(player.getName()
                    + " has a " + player.getShip().toString()
                    + " with " + player.getShip().getHealth() + " health.");
            System.out.println("The (maybe) enemy has a " + bm.getOtherShip().getShipType()
                    + " with " + bm.getOtherShip().getHealth() + " health.");
            System.out.println("---------------------------------------\n");

            System.out.println("What will you do? (Type in a number)");
            System.out.println("1. Attack");
            System.out.println("2. Run");
            System.out.println("3. Submit");
            System.out.println("4. Bribe");
            System.out.println("Q. Quit\n\n");

            option = scan.nextLine();

            EncounterState es = new EncounterState();
            String ret = "";
            switch (option) {
                case "1":
                    ret = bm.executeTurn(new AttackAction(), es);
                    break;
                case "2":
                    ret = bm.executeTurn(new RunAction(), es);
                    break;
                case "3":
                    ret = bm.executeTurn(new SubmitAction(), es);
                    break;
                case "4":
                    ret = bm.executeTurn(new BribeAction(), es);
                    break;
            }
            System.out.println(ret);
            if (es.isOver()) {
                System.out.println("The encounter is over.");
                break;
            }
        }
    }

//    public static void main(String[] args) {
//        public void testPirateEncounter() {
//        Scanner scan = new Scanner(System.in);
//
//        Player.PlayerBuilder playerBuilder = new Player.PlayerBuilder("Justin", ss);
//        Player player = new Player(playerBuilder);
//        player.getShip().addWeapon(Weapon.PULSE_LASER);
//        player.getShip().addGood(new Item.ItemBuilder(TradeGoods.NARCOTICS).quantity(1).price(2)
//                .build());
//        player.getShip().addGood(new Item.ItemBuilder(TradeGoods.FIREARMS).quantity(4).price(4)
//                .build());
//        player.getShip().addGood(new Item.ItemBuilder(TradeGoods.ORE).quantity(2).price(18)
//                .build());
//
//        player.getShip().emptyCargo();
//
//        BattleManager bm = new PirateBattleManager(player);
//        System.out.println(bm.startBattle());
//        System.out.println("The test is beginning :O");
//
//        String option = "";
//        while (!"q".equals(option) && !"Q".equals(option)) {
//
//            System.out.println("---------------------------------------");
//            System.out.println(player.getName()
//                    + " has a " + player.getShip().toString()
//                    + " with " + player.getShip().getHealth() + " health.");
//            System.out.println("The (maybe) enemy has a " + bm.getOtherShip().getShipType()
//                    + " with " + bm.getOtherShip().getHealth() + " health.");
//            System.out.println("---------------------------------------\n");
//
//            System.out.println("What will you do? (Type in a number)");
//            System.out.println("1. Attack");
//            System.out.println("2. Run");
//            System.out.println("3. Surrender");
//            System.out.println("Q. Quit\n\n");
//
//            option = scan.nextLine();
//
//            EncounterState es = new EncounterState();
//            String ret = "";
//            switch (option) {
//                case "1":
//                    ret = bm.executeTurn(new AttackAction(), es);
//                    break;
//                case "2":
//                    ret = bm.executeTurn(new RunAction(), es);
//                    break;
//                case "3":
//                    ret = bm.executeTurn(new SurrenderAction(), es);
//                    break;
//            }
//            System.out.println(ret);
//            if (es.isOver()) {
//                System.out.println("The encounter is over.");
//                break;
//            }
//        }
//    }
}
