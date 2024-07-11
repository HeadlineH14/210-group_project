import javax.swing.ImageIcon;
import javax.swing.table.AbstractTableModel;

import java.awt.*;

public abstract class ShapeTools implements Tool
{
    //declare and assign variables
    private int count = 0;                                              //number of clicks      
    private int point1X, point1Y, point2X, point2Y = 0;                 //coordinates of mouse clicks
    private int lengthX, lengthY = 0;                                   //lengths of sides


    /**
     * paint
     * The paint method checks how many times the mouse has been clicked to decide when to place the line
     * On the first click, assigns the coordinates to point1X and point1Y
     * Calculates the lengths of the sides and which coordinate is the upper left corner, then draws a shape and resets count
     */
    public void paint(Canvas canvas, Graphics2D graphics)
    {
        count++;                                    //increment count when clicked
        
        //first click
        if (count < 2)                                      
        {   
            //assign coordinates
            point1X = canvas.newX;
            point1Y = canvas.newY;
        }

        //second click
        else if (count >= 2)
        {
            //assign coordinates
            point2X = canvas.newX;
            point2Y = canvas.newY;

            //checks which coordinate is more on the left then calculates length and assigns point1X as the upper left corner
            if (point1X > point2X)
            {
                lengthX = point1X - point2X;
                point1X = point2X;
            }
            else if (point1X < point2X)
            {
                lengthX = point2X - point1X;
            }

            //checks which coordinate is higher up then calculates length and assigns point1X as the upper left corner
            if (point1Y > point2Y)
            {
                lengthY = point1Y - point2Y;
                point1Y = point2Y;
            }
            else if (point1Y < point2Y)
            {
                lengthY = point2Y - point1Y;
            }

            graphics.setColor(canvas.getBrushColor());                      //sets colour that is selected

            fillShape(graphics, canvas, point1X, point1Y, lengthX, lengthY);


            count = 0;                                                      //reset count
        }
    }

    //function to draw each shape
    public abstract void fillShape(Graphics2D graphics, Canvas canvas, int point1X, int point1Y, int lengthX, int lengthY);

    //abstract functions to get child variable names 
    public abstract String getName();
    public abstract ImageIcon getIcon();
}