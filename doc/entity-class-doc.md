# 1. Entity
<!-- TOC -->

- [1. Entity](#1-entity)
- [2. game (package)](#2-game-package)
    - [2.1. Game](#21-game)
    - [2.2. GameDifficulty (enum)](#22-gamedifficulty-enum)
- [3. market (package)](#3-market-package)
    - [3.1. Item](#31-item)
    - [3.2. Market](#32-market)
- [4. Player (package)](#4-player-package)
    - [4.1. Player](#41-player)
    - [4.2. PlayerBuilder](#42-playerbuilder)
    - [4.3. Ship](#43-ship)
    - [4.4. ShipType (enum)](#44-shiptype-enum)
- [5. Universe (package)](#5-universe-package)
    - [5.1. Planet](#51-planet)
        - [5.1.1. Event (enum)](#511-event-enum)
        - [5.1.2. Habitats (enum)](#512-habitats-enum)
        - [5.1.3. Planet](#513-planet)
        - [5.1.4. ResourceClassification (enum)](#514-resourceclassification-enum)
        - [5.1.5. Species (enum)](#515-species-enum)
        - [5.1.6. TechLevel (enum)](#516-techlevel-enum)
    - [5.2. Coordinate](#52-coordinate)
    - [5.3. Names (enum)](#53-names-enum)
    - [5.4. SolarSystem](#54-solarsystem)
    - [5.5. Universe](#55-universe)

<!-- /TOC -->

# 2. game (package)
## 2.1. Game
Represents a model of the game 

**Attributes**
* `private GameDifficulty gameDiff`
* `private Universe universe`

**Functions**
* `public Game(GameDifficulty gameDiff)`
* `public GameDifficulty getGameDiff()`
* `public Universe getUniverse()`
* `public String toString()`

## 2.2. GameDifficulty (enum)
```
public enum GameDifficulty {
    BEGINNER,
    EASY,
    NORMAL,
    HARD,
    IMPOSSIBLE;
}
```
# 3. market (package)
## 3.1. Item
To be introduced.
## 3.2. Market
To be introduced.

# 4. Player (package)
## 4.1. Player
**Attributes**
* `private static final int MAX_POINTS = 16`
* `private String name`
* `private int[] points`
```
* 0 is pilot
* 1 is engineer
* 2 is trade
* 3 is fight
```
* `private int credits`
* `private Ship ship`

**Functions**
* `public Player(String name, int[] points, int credits, Ship ship)`
* `public String getName()`
* `public void setName(String name)`
* `public int[] getPoints()`
* `public void setPoints(int[] points)`
* `public int getCredits()`
* `public void setCredits(int credits)`
* `public Ship getShip()`
* `public void setShip(Ship ship)`
* `public String toString()`

```
public int getPilot()
public int getEngineer()
public int getTrade()
public int getFight()
```
## 4.2. PlayerBuilder
**Attributes**
* `private String name`
* `private int[] points`
* `private int credits`
* `private Ship ship`

**Functions**
* `public PlayerBuilder(String name)`
* `public PlayerBuilder(String name, int[] points, int credits, Ship ship)`
* `public PlayerBuilder setPoints(int[] points)`
* `public PlayerBuilder setCredits(int credits)`
* `public PlayerBuilder setShip(Ship ship)`
* `public Player build()`

## 4.3. Ship
**Attributes**
* `private ShipType shipType`

**Functions**
* `public Ship()`
* `public Ship(ShipType shipType)`
* `public ShipType getShipType()`
* `public void setShipType(ShipType shipType)`
* `public boolean equals(Object that)`
* `public String toString()`

## 4.4. ShipType (enum)
```
public enum ShipType {
    FLEA,
    GNAT,
    FIREFLY,
    MOSQUITO,
    BUMBLEBEE,
    BEETLE,
    HORNET,
    GRASSHOPPER,
    TERMITE,
    WASP;
}
```
# 5. Universe (package)
## 5.1. Planet

### 5.1.1. Event (enum)
```
public enum Event {
    DROUGHT,
    COLD,
    CROPFAIL,
    WAR,
    BOREDOM,
    PLAGUE,
    LACK_OF_WORKERS,
}
```
### 5.1.2. Habitats (enum)
```
public enum Habitats {

    DESERT(
            Arrays.asList(
                    Species.MACHINE,
                    Species.REPTILIAN),
            Arrays.asList(
                    ResourceClassification.LOTS_OF_WATER,
                    ResourceClassification.RICH_SOIL,
                    ResourceClassification.LOTS_OF_HERBS)),
    OCEAN(
            Arrays.asList(
                    Species.FUNGOID),
            Arrays.asList(
                    ResourceClassification.LOTS_OF_HERBS,
                    ResourceClassification.RICH_SOIL)),

    CONTINENTAL(
            Arrays.asList(
                    Species.HUMANOID,
                    Species.REPTILIAN,
                    Species.FUNGOID,
                    Species.MACHINE),
                    null),

    ARCTIC(
            Arrays.asList(
                    Species.HUMANOID),
            Arrays.asList(
                    ResourceClassification.RICH_SOIL,
                    ResourceClassification.LOTS_OF_HERBS)),

    TOMB(
            Arrays.asList(
                    Species.MACHINE),
            Arrays.asList(
                    ResourceClassification.LOTS_OF_HERBS,
                    ResourceClassification.RICH_SOIL,
                    ResourceClassification.RICH_FAUNA,
                    ResourceClassification.WEIRD_MUSHROOMS));
```
**Attributes**
* `ArrayList<Species> habitableSpecies`
* `ArrayList<ResourceClassification> impossibleResources`

**Functions**
* `Habitats(List<Species> species, List<ResourceClassification> impossibleResource)`
* `public static Habitats getRandomHabitat()`
* `public Species getRandomHabitableSpecie()`

### 5.1.3. Planet
**Attributes**
* `private String name`
* `private int radius`
* `private int orbitRadius`
* `private static final int MIN_RADIUS = 1`
* `private static final int MAX_RADIUS = 5`
* `private TechLevel techLevel`
* `private Habitats habitats`
* `private Species species`
* `private ResourceClassification resourceClass`

**Functions**
* `public Planet(String name, int orbitRadius)`
```
public String getName()
public int getRadius()
public int getOrbitRadius()
public TechLevel getTechLevel()
public ResourceClassification getResourceClass()
public int getDist(Planet other)
```
* `public int getDist(Planet other)`
* `private static int getRandomRadius()`
* `public static int distBetween(Planet p1, Planet p2)`
* `public static Planet[] generatePlanets(int size)`

### 5.1.4. ResourceClassification (enum)
```
public enum ResourceClassification {

    NO_SPECIAL_RESOURCES(35),
    MINERAL_RICH(10),
    LOTS_OF_WATER(10),
    RICH_SOIL(10),
    RICH_FAUNA(10),
    WEIRD_MUSHROOMS(10),
    LOTS_OF_HERBS(10),
    LIFELESS(5);
}
```
**Attributes**
* `private int prob`

**Functions**
* `ResourceClassification(int prob)`
* `public static ResourceClassification getRandomResourceClass(Habitats habitats)`

### 5.1.5. Species (enum)
```
public enum Species {

    HUMANOID, // Human
    MACHINE, // Machine
    FUNGOID, // Fungi
    REPTILIAN; // Reptile

}
```
### 5.1.6. TechLevel (enum)
```
public enum TechLevel {
    PRE_AGRICULTURE,
    AGRICULTURE,
    MEDIEVAL,
    RENAISSANCE,
    EARLY_INDUSTRIAL,
    INDUSTRIAL,
    POST_INDUSTRIAL,
    HI_TECH;
}
```
**Functions**
* `public static TechLevel getRandomTechLevel()`

## 5.2. Coordinate
**Attributes**
* `private int x`
* `private int y`
* `private static final int MAX_X = 150`
* `private static final int MAX_Y = 100`

**Functions**
* `public Coordinate()`
* `public Coordinate(int x, int y)`
* `public int getX()`
* `public int getY()`
* `public double distTo(Coordinate c)`
* `public boolean equals(Object that)`
* `public int hashCode()`
* `public String toString()`
* `public static double distBetween(Coordinate c1, Coordinate c2)`

## 5.3. Names (enum)
```
public static final String[] NAMES = new String[] {
        "Acamar",
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
}
```
**Attributes**
* `private static final int MIN_MOD = 1`
* `private static final int MAX_MOD = 5`

**Functions**
* `public static String generateName()`

## 5.4. SolarSystem
**Attributes**
* `public static final int MIN_PLANETS = 1`
* `public static final int MAX_PLANETS = 10`
* `private Planet[] planets`
* `private String name`
* `private Coordinate coordinate`

**Functions**
* `public SolarSystem(String name, Coordinate coordinate)`
* `public String getName()`
* `public Coordinate getCoordinate()`
* `public String toString()`

## 5.5. Universe
**Attributes**
* `private static final int MAX_SOLAR_SYSTEMS = 20`
* `private SolarSystem[] solarSystem`

**Functions**
* `public Universe()`
* `private void createUniverse()`
* `public String toString()`






