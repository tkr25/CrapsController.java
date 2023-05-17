/*
 * TCSS 305 - Craps
 */
package model;
import java.util.Random;
/**
 * Static craps class for use by the GUI to play
 * a game of Craps.
 * @version 1.0
 * @author tyler robinson
 */
public final class Craps {
    /** string for player win.*/
    private static final String PLAYER_WIN = "Player";
    /** string for house win.*/
    private static final String HOUSE_WIN = "House";
    /** int for die one.*/
    private static int myFirstDie;
    /** int for number of player wins.*/
    private static int myPlayerWins;
    /** int for number of house wins.*/
    private static int myHouseWins;
    /** string for the winner.*/
    private static String myWinner;
    /** int for die two.*/
    private static int mySecondDie;
    /** int for die total.*/
    private static int myTotal;
    /** int for point.*/
    private static int myPoint;
    /** boolean to track if it's an initial roll.*/
    private static boolean myInitialRoll;
    /** int for bank amount.*/
    private static int myBank;
    /** int for bet amount.*/
    private static int myBet;
    /** random object for use by the die.*/
    private static final Random RAND = new Random();
    /**
     * private constructor to prevent instantiation.
     */
    private Craps() { }
    /**
     * Conducts an initial roll.
     */
    public static void initialRoll() {
        myWinner = "No Winner";
        roll();
        if (myTotal == 2 || myTotal == 3 || myTotal == 12) {
            myWinner = HOUSE_WIN;
            myHouseWins++;
            myBank -= myBet;
        } else if (myTotal == 7 || myTotal == 11) {
            myWinner = PLAYER_WIN;
            myPlayerWins++;
            myBank += myBet;
        } else {
            myPoint = myTotal;
            myInitialRoll = false;
        }
    }
    /**
     * Conducts an point roll.
     */
    public static void pointRoll() {
        roll();
        if (myTotal == 7) {
            myWinner = HOUSE_WIN;
            myHouseWins++;
            myPoint = 0;
            myInitialRoll = true;
            myBank -= myBet;
        } else if (myTotal == myPoint) {
            myWinner = PLAYER_WIN;
            myPlayerWins++;
            myPoint = 0;
            myInitialRoll = true;
            myBank += myBet;
        }
    }
    /**
     * Rolls the dice.
     */
    public static void roll() {
        myFirstDie = RAND.nextInt(6) + 1;
        mySecondDie = RAND.nextInt(6) + 1;
        myTotal = myFirstDie + mySecondDie;
    }

    /**
     * resets the game.
     */
    public static void reset() {
        myFirstDie = 0;
        myPlayerWins = 0;
        myHouseWins = 0;
        myWinner = null;
        mySecondDie = 0;
        myTotal = 0;
        myPoint = 0;
        myInitialRoll = true;
        myBank = 0;
        myBet = 0;

    }

    /**
     * returns if the bet amount is valid.
     * @param theBet the amount trying to be bet.
     * @return boolean for if the bet is possible.
     */
    public static boolean canBet(final int theBet) {
        return theBet > 0 && theBet <= myBank;
    }

    /**
     * sets whether it's an initial roll or not.
     * @param theBoolean to set if it's an initial roll.
     */
    public static void setMyInitialRoll(final boolean theBoolean) {
        myInitialRoll = theBoolean;
    }

    /**
     * sets the bet amount.
     * @param theBet the amount to be set.
     */
    public static void setMyBet(final int theBet) {
        myBet = theBet;
    }

    /**
     * returns amount rolled on the first die.
     * @return the die amount.
     */
    public static int getMyFirstDie() {
        return myFirstDie;
    }

    /**
     * returns number of house wins.
     * @return number of wins.
     */
    public static int getMyHouseWins() {
        return myHouseWins;
    }
    /**
     * returns number of player wins.
     * @return number of wins.
     */
    public static int getMyPlayerWins() {
        return myPlayerWins;
    }

    /**
     * returns the bet amount.
     * @return bet amount.
     */
    public static int getMyBet() {
        return myBet;
    }
    /**
     * returns amount rolled on the second die.
     * @return the die amount.
     */
    public static int getMySecondDie() {
        return mySecondDie;
    }

    /**
     * returns the two die total.
     * @return total of the two die.
     */
    public static int getMyTotal() {
        return myTotal;
    }

    /**
     * returns bank amount.
     * @return bank amount.
     */
    public static int getMyBank() {
        return myBank;
    }

    /**
     * sets bank amount.
     * @param theAmount of the new bank.
     */
    public static void setMyBank(final int theAmount) {
        myBank = theAmount;
    }

    /**
     * returns the winner.
     * @return the winner.
     */
    public static String getMyWinner() {
        return myWinner;
    }

    /**
     * returns if it is an initial roll.
     * @return if it's an initial roll.
     */
    public static boolean getMyInitialRoll() {
        return myInitialRoll;
    }

    /**
     * returns the point.
     * @return the point.
     */
    public static int getMyPoint() {
        return myPoint;
    }
}