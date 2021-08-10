package mouseDraw;

import java.util.*;
import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *  {@code FieldComponent} is the class that contains the
 *  {@code JComponent} of the field.
 *  @version 2021032100
 *  @author Trevor Watts
 */
class CanvasComponent extends JComponent {
	private JPopupMenu			popupMenu;
	private static final String shapeNames[] =
			{"Rectangle", "Ellipse", "Free form"};
	private Rectangle2D.Double	rectangle;
	private Ellipse2D.Double    ellipse;
	private Path2D.Double		freeForm;
	private String		        shapeToDraw = shapeNames[0];
	private ArrayList<Shape>    shapes;

	/**
	 *	This is the class that manages the button
	 */
    private class MouseHandler extends MouseAdapter
    {
		Point startingPoint;

		/*
		 *	Handles what happens when mouse is pressed
		 */
		public void mousePressed(MouseEvent event)
		{
			if(event.getButton() != MouseEvent.BUTTON1){
				return;
			}

			startingPoint = event.getPoint();
		}

		/*
		 *	Handles what happens when mouse is dragged
		 */
		public void mouseDragged(MouseEvent event)
		{
			if(shapeToDraw.equals(shapeNames[0])){
				/*
				 *	User chose rectangle
				 */
				if(rectangle == null){
					/*
					 *	Create new rectangle
					 */
					rectangle = new Rectangle2D.Double();
				}
				rectangle.setFrameFromCenter(startingPoint,
									 event.getPoint());
			}

			else if(shapeToDraw.equals(shapeNames[1])){
				/*
				 *	User chose ellipse
				 */
				if(ellipse == null){
					/*
					 *	Create new ellipse
					 */
					ellipse = new Ellipse2D.Double();
				}
				ellipse.setFrameFromCenter(startingPoint,
									event.getPoint());
			}
			else if(shapeToDraw.equals(shapeNames[2])){
				/*
				 *	User chose Free form
				 */
				if(freeForm == null){
					/*
					 *	Create new Free form line
					 */
					freeForm = new Path2D.Double();
					freeForm.moveTo(event.getX(),event.getY());
				}
				freeForm.lineTo(event.getX(),event.getY());

			}
			repaint();
		}

		/*
		 *	Handles what happens when the mouse is released
		 */
		public void mouseReleased(MouseEvent event)
		{
			if(shapeToDraw.equals(shapeNames[0])){
				/*
				 *	User chose rectangle
				 */
				if(rectangle != null){
					/*
					 *	Rectangle was too small to be saved
					 */
					shapes.add(rectangle);
				}
				rectangle = null;
			}
			else if(shapeToDraw.equals(shapeNames[1])){
				/*
				 *	User chose ellipse
				 */
				if(ellipse != null){
					/*
					 *	Ellipse was too small to be saved
					 */
					shapes.add(ellipse);
				}
				ellipse = null;
			}
			else if(shapeToDraw.equals(shapeNames[2])){
				/*
				 *	User chose Free Form
				 */
				shapes.add(freeForm);
				freeForm = null;
			}
		}
    }

    /**
     *  Construct the Component that contains and manages
     *  the canvas.
     */
    public CanvasComponent()
    {
		boolean		    selected;
		ButtonGroup	    buttonGroup;
		MouseHandler	mouseHandler;
		shapes = new ArrayList<Shape>();

		mouseHandler = new MouseHandler();
		addMouseListener(mouseHandler);
		addMouseMotionListener(mouseHandler);

		popupMenu = new JPopupMenu();
		buttonGroup = new ButtonGroup();
		selected = true;
		/*
		 *	Loop through radio buttons
		 *  and give selections for each
		 *  shape the user can use
		 */
		for (String which : shapeNames){
			JRadioButtonMenuItem thisButton;

			thisButton = new JRadioButtonMenuItem(which, selected);
			thisButton.addActionListener(event ->
											shapeToDraw = which);
			buttonGroup.add(thisButton);
			popupMenu.add(thisButton);
			selected = false;
		}

		setComponentPopupMenu(popupMenu);

    }

	/**
	 *	Gives the preferred size for the canvas
	 */
	public Dimension getPreferredSize()
	{
		return(new Dimension(800, 450));
	}
    /**
     *  Provide Swing a way to redraw our canvas.
     */
    public void paintComponent(Graphics graphics)
    {
		Graphics2D	graphics2D;

		graphics2D =  (Graphics2D)graphics;

		/*
		 *	Drawing all saved shapes
		 */
		for(int i = 0; i < shapes.size(); i++){
			graphics2D.draw(shapes.get(i));
		}
		if(rectangle != null){
			/*
			 *	user currently painted rectangle
			 */
			graphics2D.draw(rectangle);
		}
		if(ellipse != null){
			/*
			 *	user currently painted ellipse
			 */
			graphics2D.draw(ellipse);
		}
		if (freeForm != null){
			/*
			 *	user currently painted line
			 */
			graphics2D.draw(freeForm);
		}
    }
}