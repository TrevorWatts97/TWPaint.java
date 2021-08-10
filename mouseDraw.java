package mouseDraw;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  {@code mouseDraw} is class that implements playing the game
 *  graphically.
 *  @version 2021032300
 *  @author Trevor Watts
 */

public class mouseDraw {
	/**
	 *  Creates the game to play
	 *  @param arg arguments to the playing field
	 */
    public static void main(String arg[])
    {
        EventQueue.invokeLater(() -> {
                CanvasFrame      frame;

                frame = new CanvasFrame();

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setTitle("TWPaint");
                frame.setVisible(true);
            });
    }
}
