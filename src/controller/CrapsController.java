/*
 * TCSS 305 - Craps
 */
package controller;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import view.CrapsGUI;
/**
 * Controller Class for a game of Craps. Contains the main method
 * for running the program.
 * @version 1.0
 * @author Tyler Robinson
 */
public final class CrapsController  {
    private CrapsController() { }
    /**
     * Constructs a game of craps.
     *
     * @param theArgs Command line arguments (ignored).
     */
    public static void main(final String... theArgs) {
        try {
            UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
        } catch (final UnsupportedLookAndFeelException ex) {
            ex.printStackTrace();
        } catch (final IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (final InstantiationException ex) {
            ex.printStackTrace();
        } catch (final ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        UIManager.put("swing.boldMetal", Boolean.FALSE);
        javax.swing.SwingUtilities.invokeLater(CrapsGUI::new);
    }
 
}
