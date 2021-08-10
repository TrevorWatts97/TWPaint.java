package mouseDraw;

import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  {@code SpreadsheetFrame} is the class that
 *  contains the {@code JFrame}
 *  of the spreadsheet.
 *  @version 2021033000
 *  @author Trevor Watts
 */
class CanvasFrame extends JFrame {

	/**
	 *  Constructs the frame of the spreadsheet
	 */
    public CanvasFrame() {
        add(new CanvasComponent());
        pack();

        /*
         *  Center the frame in the screen.
         */
        setLocationRelativeTo(null);
    }
}