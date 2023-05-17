/*
 * TCSS 305 - Craps
 */
package view;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.Objects;
import javax.print.attribute.standard.Media;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import model.Craps;

/**
 * Draws and assigns listeners for behaviors for a game of craps.
 * call craps model for game behaviors.
 * @version 1.0
 * @author tyler robinson
 */
public class CrapsGUI extends JFrame {
    /** The window title. */
    private static final String TITLE = "Craps";

    /** The panel width and panel spacing. */
    private static final int PANEL_WIDTH = 130;
    /** The panel height. */
    private static final int PANEL_HEIGHT = 320;
    /** frame size. */
    private static final  Dimension FRAME_SIZE = new Dimension(390, 320);
    // constants to capture screen dimensions
    /** A ToolKit. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    /** The Dimension of the screen. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    /** The button height. */
    private static final int BUTTON_HEIGHT = 50;
    /** The field height and spacing. */
    private static final int FIELD_HEIGHT = 25;
    /** The field width. */
    private static final int FIELD_WIDTH = 100;
    /** The x margin basis. */
    private static final int X_MARGIN = 10;
    /** The y spacing. */
    private static final int Y_SPACING = 25;
    /** button for initial roll. */
    private final JButton myInitialRollButton;
    /** button for point roll. */
    private final JButton myPointRollButton;
    /** button for new game. */
    private final JButton myNewGameButton;
    /** menu item to start game. */
    private final JMenuItem myStartItem;
    /** menu item for initial roll button. */
    private final JMenuItem myInitialRollItem;
    /** menu item for new game button. */
    private final JMenuItem myNewGameItem;
    /** menu item to exit game. */
    private final JMenuItem myExitItem;
    /** menu item for point roll button. */
    private final JMenuItem myPointRollItem;
    /** menu item to display all shortcuts. */
    private final JMenuItem myShortCutItem;
    /** menu item to display program information. */
    private final JMenuItem myAboutProgramItem;
    /** menu item to display craps rules. */
    private final JMenuItem myAboutCrapsItem;
    /** text field to display die information from the model. */
    private final JTextField myDieOneText;
//    /** JPanel to display die.*/
//    private final JPanel myDieOnePanel;

    /** text field to display bank information from the model. */
    private final JTextField myBankText;
    /** text field to display bet information from the model. */
    private final JTextField myBetText;
    /** text field to display die information from the model. */
    private final JTextField myDieTwoText;
    /** text field to display die total from the model. */
    private final JTextField myTotalText;
    /** text field to display roll winner from the model. */
    private final JTextField myWinnerText;
    /** text field to display number of player wins from the model. */
    private final JTextField myPlayerWinsText;
    /** text field to display number of house wins from the model. */
    private final JTextField myHouseWinsText;
    /** text field to display the point from the model. */
    private final JTextField myPointText;

    /**
     * CrapsGUI instantiates a game of craps.
     */
    public CrapsGUI() {
        super(TITLE);

        myInitialRollButton = new JButton("Initial Roll");
        myBetText = new JTextField();
        myBankText = new JTextField();
        myPointRollButton = new JButton("Point Roll");
        myDieOneText = new JTextField();
        myHouseWinsText = new JTextField();
        myPlayerWinsText = new JTextField();
        myPointText = new JTextField();
        myNewGameButton = new JButton("New Game");
        myDieTwoText = new JTextField();
        myTotalText = new JTextField();
        myWinnerText = new JTextField();
        myStartItem = new JMenuItem("Start Game");
        myInitialRollItem = new JMenuItem("Initial Roll");
        myNewGameItem = new JMenuItem("New Game");
        myExitItem = new JMenuItem("Exit");
        myPointRollItem = new JMenuItem("Point Roll");
        myAboutProgramItem = new JMenuItem("Program");
        myAboutCrapsItem = new JMenuItem(TITLE);
        myShortCutItem = new JMenuItem("Short Cuts");
//        myDieOnePanel = new JPanel(null);

        setInitialState();
        createAndShowGUI();
    }

    /**
     * adds everything to the frame instantiated in craps gui.
     */
    public void createAndShowGUI() {
        //Create and set up the window.
        super.setSize(FRAME_SIZE);
        super.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        super.setResizable(false);
        super.setLayout(null);
//        dieOnePanel();
        setJMenuBar(createMenuBar());
//        add(myDieOnePanel);
        add(leftPanel());
        add(centerPanel());
        add(rightPanel());
        addListeners();

        //Display the window.
        setLocation(SCREEN_SIZE.width / 2 - super.getWidth() / 2,
                SCREEN_SIZE.height / 2 - super.getHeight() / 2);
        setVisible(true);
    }
//    private void dieOnePanel() {
//        myDieOnePanel.setBackground(Color.BLUE);
//        myDieOnePanel.setBounds(PANEL_WIDTH, 0, 25, 25);
//    }

    /**
     * creates the menu bar for the game of craps.
     * @return jmenubar for craps game.
     */
    private JMenuBar createMenuBar() {
        final JMenuBar menuBar = new JMenuBar();

        final JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        final JMenu aboutMenu = new JMenu("About");
        aboutMenu.setMnemonic(KeyEvent.VK_A);

        aboutMenu.add(myAboutProgramItem);
        aboutMenu.add(myAboutCrapsItem);
        aboutMenu.add(myShortCutItem);
        fileMenu.add(myStartItem);
        fileMenu.add(myNewGameItem);
        fileMenu.add(myInitialRollItem);
        fileMenu.add(myPointRollItem);
        fileMenu.add(myExitItem);

        menuBar.add(fileMenu);
        menuBar.add(aboutMenu);

        return menuBar;
    }

    /**
     * gathers bank amount from user input.
     */
    private void bankDialog() {
        boolean accepted = false;
        while (!accepted) {
            final int bankAmount = tryParse(JOptionPane.showInputDialog("Enter a bank amount"
                    + " greater than 0"));
            if (bankAmount > 0) {
                Craps.setMyBank(bankAmount);
                myBankText.setText("" + Craps.getMyBank());
                accepted = true;
            } else {
                JOptionPane.showMessageDialog(null, "You did not "
                      +  "enter an integer greater than 0.");
            }
        }
    }

    /**
     * sets everything to initially be shut off.
     */
    private void setInitialState() {
        Craps.setMyInitialRoll(true);

        myInitialRollButton.setEnabled(false);
        myBetText.setEditable(false);
        myPointRollButton.setEnabled(false);
        myNewGameButton.setEnabled(false);
        myBankText.setEditable(false);
        myDieOneText.setEditable(false);
        myHouseWinsText.setEditable(false);
        myPlayerWinsText.setEditable(false);
        myPointText.setEditable(false);
        myDieTwoText.setEditable(false);
        myTotalText.setEditable(false);
        myWinnerText.setEditable(false);
    }
    /**
     * creates the right panel for the game of craps.
     * @return jpanel to add to the frame.
     */
    private JPanel rightPanel() {
        final JPanel rightPanel = new JPanel(null);
        rightPanel.setBounds(PANEL_WIDTH * 2, 0, PANEL_WIDTH, PANEL_HEIGHT);
        rightPanel.setBackground(Color.ORANGE);

        final JLabel point = new JLabel("Point");
        final JLabel bank = new JLabel("Bank");
        final JLabel bet = new JLabel("Bet");
        final JLabel money = new JLabel("$");
        final JLabel money2 = new JLabel("$");

        point.setBounds(X_MARGIN, Y_SPACING, FIELD_WIDTH, FIELD_HEIGHT);
        myPointText.setBounds(X_MARGIN, Y_SPACING * 2, FIELD_WIDTH, FIELD_HEIGHT);
        bank.setBounds(X_MARGIN, Y_SPACING * 3, FIELD_WIDTH, FIELD_HEIGHT);
        money.setBounds(X_MARGIN - 10, Y_SPACING * 4, FIELD_WIDTH, FIELD_HEIGHT);
        myBankText.setBounds(X_MARGIN, Y_SPACING * 4, FIELD_WIDTH, FIELD_HEIGHT);
        bet.setBounds(X_MARGIN, Y_SPACING * 5, FIELD_WIDTH, FIELD_HEIGHT);
        money2.setBounds(X_MARGIN - 10, Y_SPACING * 6, FIELD_WIDTH, FIELD_HEIGHT);
        myBetText.setBounds(X_MARGIN, Y_SPACING * 6, FIELD_WIDTH, FIELD_HEIGHT);
        myNewGameButton.setBounds(X_MARGIN, Y_SPACING * 8, FIELD_WIDTH,
                BUTTON_HEIGHT);

        rightPanel.add(myPointText);
        rightPanel.add(myBankText);
        rightPanel.add(myBetText);
        rightPanel.add(myNewGameButton);
        rightPanel.add(bank);
        rightPanel.add(bet);
        rightPanel.add(point);
        rightPanel.add(money2);
        rightPanel.add(money);

        return rightPanel;
    }
    /**
     * creates the center panel for the game of craps.
     * @return jpanel to add to the frame.
     */
    private JPanel centerPanel() {
        final JPanel centerPanel = new JPanel(null);
        centerPanel.setBounds(PANEL_WIDTH, 0, PANEL_WIDTH, PANEL_HEIGHT);
        centerPanel.setBackground(Color.RED);

        final JLabel dieOne = new JLabel("Die One");
        final JLabel dieTwo = new JLabel("Die Two");
        final JLabel total  = new JLabel("Total");


        total.setBounds(X_MARGIN, Y_SPACING, FIELD_WIDTH, FIELD_HEIGHT);
        myTotalText.setBounds(X_MARGIN, Y_SPACING * 2, FIELD_WIDTH, FIELD_HEIGHT);
        dieOne.setBounds(X_MARGIN, Y_SPACING * 3, FIELD_WIDTH, FIELD_HEIGHT);
        myDieOneText.setBounds(X_MARGIN, Y_SPACING * 4, FIELD_WIDTH, FIELD_HEIGHT);
        dieTwo.setBounds(X_MARGIN, Y_SPACING * 5, FIELD_WIDTH, FIELD_HEIGHT);
        myDieTwoText.setBounds(X_MARGIN, Y_SPACING * 6, FIELD_WIDTH, FIELD_HEIGHT);
        myPointRollButton.setBounds(X_MARGIN, Y_SPACING * 8, FIELD_WIDTH,
                BUTTON_HEIGHT);


        centerPanel.add(myDieOneText);
        centerPanel.add(myDieTwoText);
        centerPanel.add(myTotalText);
        centerPanel.add(myPointRollButton);
        centerPanel.add(dieTwo);
        centerPanel.add(total);
        centerPanel.add(dieOne);

        return centerPanel;
    }
    /**
     * creates the left panel for the game of craps.
     * @return jpanel to add to the frame.
     */
    private JPanel leftPanel() {
        final JPanel leftPanel = new JPanel(null);
        leftPanel.setBackground(Color.GREEN);
        leftPanel.setBounds(0, 0, PANEL_WIDTH, PANEL_HEIGHT);

        final JLabel winner = new JLabel("Winner");
        final JLabel house = new JLabel("House Wins");
        final JLabel player = new JLabel("Player Wins");

        winner.setBounds(X_MARGIN, Y_SPACING, FIELD_WIDTH, FIELD_HEIGHT);
        myWinnerText.setBounds(X_MARGIN, Y_SPACING * 2, FIELD_WIDTH, FIELD_HEIGHT);
        house.setBounds(X_MARGIN, Y_SPACING * 3, FIELD_WIDTH, FIELD_HEIGHT);
        myHouseWinsText.setBounds(X_MARGIN, Y_SPACING * 4, FIELD_WIDTH, FIELD_HEIGHT);
        player.setBounds(X_MARGIN, Y_SPACING * 5, FIELD_WIDTH, FIELD_HEIGHT);
        myPlayerWinsText.setBounds(X_MARGIN, Y_SPACING * 6, FIELD_WIDTH, FIELD_HEIGHT);
        myInitialRollButton.setBounds(X_MARGIN, Y_SPACING * 8, FIELD_WIDTH,
                BUTTON_HEIGHT);

        leftPanel.add(myWinnerText);
        leftPanel.add(myHouseWinsText);
        leftPanel.add(myInitialRollButton);
        leftPanel.add(myPlayerWinsText);
        leftPanel.add(winner);
        leftPanel.add(house);
        leftPanel.add(player);

        return leftPanel;
    }

    /**
     * method for closing current game and starting a new one.
     */
    private void startNewGame() {
        final int confirmed = JOptionPane.showConfirmDialog(null,
                "Do you want to start a new Game?", "New Game Message Box",
                JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            Craps.reset();
            setVisible(false);
            new CrapsGUI();
            dispose();
        }
    }
//    private String chooseImageFileName(final int theDie) {
//        String fileName = "";
//        if (theDie == 1) {
//            fileName = "dice-six-faces-one.png";
//        } else if (theDie == 2) {
//            fileName = "dice-six-faces-two.png";
//        } else if (theDie == 3) {
//            fileName = "dice-six-faces-three.png";
//        } else if (theDie == 4) {
//            fileName = "dice-six-faces-four.png";
//        } else if (theDie == 5) {
//            fileName = "dice-six-faces-five.png";
//        } else if (theDie == 6) {
//            fileName = "dice-six-faces-six.png";
//        }
//        return fileName;
//    }
//    public void displayDice() {
//
//        final ImageIcon dieOneIcon = new ImageIcon(chooseImageFileName(Craps.getMyFirstDie()));
//        final JLabel dieOneLabel = new JLabel();
//        dieOneLabel.setBounds(0, 0, 25, 25);
//        dieOneLabel.setIcon(dieOneIcon);
//        myDieOnePanel.add(dieOneLabel);
//
//    }
//    private void chooseSound() {
//        final String file;
//        if (Objects.equals(Craps.getMyWinner(), "Player")) {
//            file = "333404__jayfrosting__cheer-2.wav";
//            playSound(file);
//        } else if (Objects.equals(Craps.getMyWinner(), "House")) {
//            file = "336997__tim-kahn__boo-01.wav";
//            playSound(file);
//        }
//    }
//    public void playSound(final String theFilePath) {
//        try {
//            AudioInputStream audioInput = AudioSystem.getAudioInputStream
//                    (new File(theFilePath));
//            Clip sound = AudioSystem.getClip();
//            sound.open(audioInput);
//            sound.start();
//        } catch (final Exception e) {
//            JOptionPane.showMessageDialog(null, "Audio File not found");
//        }
//    }

    /**
     * adds listeners to all necessary gui items.
     */
    private void addListeners() {
        myShortCutItem.setMnemonic(KeyEvent.VK_H);
        myNewGameButton.setMnemonic(KeyEvent.VK_N);
        myInitialRollButton.setMnemonic(KeyEvent.VK_I);
        myPointRollButton.setMnemonic(KeyEvent.VK_P);
        myNewGameItem.setMnemonic(KeyEvent.VK_N);
        myInitialRollItem.setMnemonic(KeyEvent.VK_I);
        myPointRollItem.setMnemonic(KeyEvent.VK_P);
        myStartItem.setMnemonic(KeyEvent.VK_S);
        myExitItem.setMnemonic(KeyEvent.VK_E);
        myAboutCrapsItem.setMnemonic(KeyEvent.VK_C);
        myAboutProgramItem.setMnemonic(KeyEvent.VK_O);

        myNewGameItem.addActionListener(theEvent -> myNewGameButton.doClick());
        myInitialRollItem.addActionListener(theEvent -> myInitialRollButton.doClick());
        myPointRollItem.addActionListener(theEvent -> myPointRollButton.doClick());
        myStartItem.addActionListener(theEvent -> {
            myBetText.setEditable(true);
            myInitialRollButton.setEnabled(true);
            myNewGameButton.setEnabled(true);
            bankDialog();
            myStartItem.setEnabled(false);
        });
        myAboutProgramItem.addActionListener(theEvent ->
            JOptionPane.showMessageDialog(null, """
                                Author: Tyler Robinson\s
                                Version: 1.0\s
                                Java 19""", "About Program",
                        JOptionPane.INFORMATION_MESSAGE));
        myShortCutItem.addActionListener(theEvent ->
                JOptionPane.showMessageDialog(null, """
                                Button Short Cuts are ALT +:
                                I: Initial Roll, P: Point Roll, N: New Game
                                To open menus ALT +:
                                F: File Menu, A: About Menu
                                With menu's open to open items ALT or SHIFT +:
                                I: Initial Roll, P: Point Roll, N: New Game
                                S: Start Game, E: Exit, C: Craps, O: Program
                                H: Short Cuts
                                """, "About Craps",
                        JOptionPane.INFORMATION_MESSAGE));
        myAboutCrapsItem.addActionListener(theEvent ->
                JOptionPane.showMessageDialog(null, """
                                Must place a bet to roll.
                                On the initial roll:
                                2, 3, or 12 looses and 7 or 11 wins.
                                Rolling 4, 5, 6, 8, 9, or 10 establishes a Point.
                                Now the goal is to re-roll the Point (win)
                                before rolling a 7 (loose).""", "About Craps",
                        JOptionPane.INFORMATION_MESSAGE));
        myExitItem.addActionListener(theEvent -> dispose());

        this.addWindowListener(new WindowAdapter() {
            /**
             * changes window closing to ask if user is sure.
             * @param theEvent the event to be processed
             */
            public void windowClosing(final WindowEvent theEvent) {
                final int confirmed = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to exit the program?",
                        "Exit Program Message Box",
                        JOptionPane.YES_NO_OPTION);
                if (confirmed == JOptionPane.YES_OPTION) {
                    dispose();
                }
            }
        });

        myBetText.addActionListener(theEvent -> {
            if (Craps.canBet(tryParse(myBetText.getText()))) {
                Craps.setMyBet(tryParse(myBetText.getText()));
                myBetText.setText("" + Craps.getMyBet());
            } else {
                myBetText.setText("0");
                Craps.setMyBet(0);
            }
        });

        myBetText.addFocusListener(new FocusAdapter() {
            /**
             * inner method to submit text when the value field looses focus.
             * @param theEvent the event to be processed
             */
            @Override
            public void focusLost(final FocusEvent theEvent) {
                if (Craps.canBet(tryParse(myBetText.getText()))) {
                    Craps.setMyBet(tryParse(myBetText.getText()));

                } else {
                    myBetText.setText("0");
                    Craps.setMyBet(0);
                }
            }
        });

        myNewGameButton.addActionListener(theEvent -> startNewGame());
        myPointRollButton.addActionListener(theEvent -> {
            Craps.pointRoll();

            myDieOneText.setText("" + Craps.getMyFirstDie());
            myDieTwoText.setText("" + Craps.getMySecondDie());
            myTotalText.setText("" + Craps.getMyTotal());
            myWinnerText.setText("" + Craps.getMyWinner());
            myPlayerWinsText.setText("" + Craps.getMyPlayerWins());
            myHouseWinsText.setText("" + Craps.getMyHouseWins());
            myBankText.setText("$ " + Craps.getMyBank());

            if (Craps.getMyInitialRoll()) {
                myInitialRollButton.setEnabled(true);
                myPointRollButton.setEnabled(false);
                myBetText.setEditable(true);
                myNewGameButton.setEnabled(true);
            } else {
                myBetText.setEditable(false);
                myInitialRollButton.setEnabled(false);
                myPointRollButton.setEnabled(true);
                myNewGameButton.setEnabled(false);
            }

            if (Craps.getMyPoint() == 0) {
                myPointText.setText("No Point");
            } else {
                myPointText.setText("" + Craps.getMyPoint());
            }

            if (Craps.getMyBank() == 0) {
                startNewGame();
            }
        });
        myInitialRollButton.addActionListener(theEvent -> {
            if (Craps.canBet(Craps.getMyBet())) {
                Craps.initialRoll();

                myDieOneText.setText("" + Craps.getMyFirstDie());
                myDieTwoText.setText("" + Craps.getMySecondDie());
                myTotalText.setText("" + Craps.getMyTotal());
                myWinnerText.setText("" + Craps.getMyWinner());
                myPlayerWinsText.setText("" + Craps.getMyPlayerWins());
                myHouseWinsText.setText("" + Craps.getMyHouseWins());
                myBankText.setText("" + Craps.getMyBank());

                if (Craps.getMyPoint() == 0) {
                    myPointText.setText("No Point");
                } else {
                    myPointText.setText("" + Craps.getMyPoint());
                }

                if (Craps.getMyInitialRoll()) {
                    myInitialRollButton.setEnabled(true);
                    myPointRollButton.setEnabled(false);
                    myBetText.setEditable(true);
                    myNewGameButton.setEnabled(true);
                } else {
                    myBetText.setEditable(false);
                    myInitialRollButton.setEnabled(false);
                    myPointRollButton.setEnabled(true);
                    myNewGameButton.setEnabled(false);
                }

                if (Craps.getMyBank() == 0) {
                    startNewGame();
                }
            }
        });
    }

    /**
     * validates integer input from user. Closes program with null result from joptionpane.
     * @param theText user  input.
     * @return integer form of the text.
     */
    private Integer tryParse(final String theText) {
        if (theText == null) {
            JOptionPane.showMessageDialog(null, "Exiting program now!");
            System.exit(0);
        }

        try {
            return Integer.parseInt(theText);
        } catch (final NumberFormatException exception) {
            return 0;
        }
    }
}
