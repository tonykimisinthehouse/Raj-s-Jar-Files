package edu.gatech.cs2340.rajsjarfiles.spacetrader.entity.universe;

import java.util.Random;

/**
 * All of the possible names for the planets and solar system.
 */
public class Names {
    public static final String[] NAMES = new String[] {
        "Acamar",
        /*
         * The alternate personality for The Nameless One
         * in "Planescape: Torment"
         */
        "Adahn",
        "Aldea",
        "Andevian",
        "Antedi",
        "Balosnee",
        "Baratas",
        "Brax",         // One of the heroes in Master of Magic
        "Bretel",       // This is a Dutch device for keeping your pants up.
        "Calondia",
        "Campor",
        "Capelle",      // The city I lived in while programming this game
        "Carzon",
        "Castor",       // A Greek demi-god
        "Cestus",
        "Cheron",
        "Courteney",    // After Courteney Cox…
        "Daled",
        "Damast",
        "Davlos",
        "Deneb",
        "Deneva",
        "Devidia",
        "Draylon",
        "Drema",
        "Endor",
        "Esmee",        // One of the witches in Pratchett's Discworld
        "Exo",
        "Ferris",       // Iron
        "Festen",       // A great Scandinavian movie
        "Fourmi",       // An ant, in French
        "Frolix",       // A solar system in one of Philip K. Dick's novels
        "Gemulon",
        "Guinifer",     // One way of writing the name of king Arthur's wife
        "Hades",        // The underworld
        "Hamlet",       // From Shakespeare
        "Helena",       // Of Troy
        "Hulst",        // A Dutch plant
        "Iodine",       // An element
        "Iralius",
        "Janus",        // A seldom encountered Dutch boy's name
        "Japori",
        "Jarada",
        "Jason",        // A Greek hero
        "Kaylon",
        "Khefka",
        "Kira",         // My dog's name
        "Klaatu",       // From a classic SF movie
        "Klaestron",
        "Korma",        // An Indian sauce
        "Kravat",       // Interesting spelling of the French word for "tie"
        "Krios",
        "Laertes",      // A king in a Greek tragedy
        "Largo",
        "Lave",         // The starting system in Elite
        "Ligon",
        "Lowry",        // The name of the "hero" in Terry Gilliam's "Brazil"
        "Magrat",       // The second of the witches in Pratchett's Discworld
        "Malcoria",
        "Melina",
        "Mentar",       // The Psilon home system in Master of Orion
        "Merik",
        "Mintaka",
        "Montor",       // A city in Ultima III and Ultima VII part 2
        "Mordan",
        "Myrthe",       // The name of my daughter
        "Nelvana",
        /*
         * An interesting spelling of a word meaning "nothing"
         * in Dutch
         */
        "Nix",
        "Nyle",         // An interesting spelling of the great river
        "Odet",
        "Og",           // The last of the witches in Pratchett's Discworld
        "Omega",        // The end of it all
        "Omphalos",     // Greek for navel
        "Orias",
        "Othello",      // From Shakespeare
        "Parade",       // This word means the same in Dutch and in English
        "Penthara",
        "Picard",       // The enigmatic captain from ST:TNG
        "Pollux",       // Brother of Castor
        "Quator",
        "Rakhar",
        "Ran",          // A film by Akira Kurosawa
        "Regulas",
        "Relva",
        "Rhymus",
        "Rochani",
        "Rubicum",      // The river Ceasar crossed to get into Rome
        "Rutia",
        "Sarpeidon",
        "Sefalla",
        "Seltrice",
        "Sigma",
        "Sol",          // That's our own solar system
        "Somari",
        "Stakoron",
        "Styris",
        "Talani",
        "Tamus",
        "Tantalos",     // A king from a Greek tragedy
        "Tanuga",
        "Tarchannen",
        "Terosa",
        "Thera",        // A seldom encountered Dutch girl's name
        "Titan",        // The largest moon of Jupiter
        "Torin",        // A hero from Master of Magic
        "Triacus",
        "Turkana",
        "Tyrus",
        /*
         * A god from AD&D, which has a prominent role in
         * Baldur's Gate
         */
        "Umberlee",
        "Utopia",       // The ultimate goal
        "Vadera",
        "Vagra",
        "Vandor",
        "Ventax",
        "Xenon",
        "Xerxes",       // A Greek hero
        "Yew",          // A city which is in almost all of the Ultima games
        "Yojimbo",      // A film by Akira Kurosawa
        "Zalkon",
        "Zuul"          // From the first Ghostbusters movie
    };

    private static final int MIN_MOD = 1;   //the max letter modifications
    private static final int MAX_MOD = 5;

    private static Random rand = new Random();

    /**
     * Generates a random name that is used for planets and solar systems.
     *
     * @return a name.
     */
    public static String generateName() {
        String name = "";
        int addMod = rand.nextInt(7);
        int randName = rand.nextInt(NAMES.length);
        if (addMod == 0) {  //add letter modifications
            int iter = rand.nextInt(MAX_MOD - MIN_MOD + 1) + MIN_MOD;
            for (int i = 0; i < iter; i++) {
                int randLetter = rand.nextInt(26) + 65;
                name += (char) randLetter;
            }
            name += "-";
        }
        name += NAMES[randName];
        return name;
    }
}
