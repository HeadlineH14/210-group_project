import javax.swing.ImageIcon;
import java.awt.*;


public class DashedLineTool implements Tool
{
    //declare and assign variables
    private String name = "DashedLine";
    private ImageIcon icon = new ImageIcon(new ImageIcon("icons/dashedline-icon.png").getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));

    int count = 0;                                  //counts how many clicks have been done
    int point1X, point1Y, point2X, point2Y = 0;     //used to track the coordinates of the 2 clicks and calculate place the end points of the line

    //created dashed stroke pattern
    float[] dashPattern = {3f, 0f, 3f};

    /**
     * paint
     * The paint method checks how many times the mouse has been clicked to decide when to place the line
     * On the first click, assigns the coordinates to point1X and point1Y
     * Draws line between the end points and resets the count
     */
    public void paint(Canvas canvas, Graphics2D graphics)
    {
        count++;
        if (count < 2)
        {
            point1X = canvas.getXCoord();
            point1Y = canvas.getYCoord();
        }

        else if (count >= 2)
        {
            point2X = canvas.getXCoord();
            point2Y = canvas.getYCoord();
            
            graphics.setStroke(new BasicStroke(canvas.getBrushSize(), BasicStroke.CAP_BUTT, BasicStroke.JOIN_ROUND, 1f, dashPattern, 2));
            graphics.setColor(canvas.getBrushColor());


            graphics.drawLine(point1X, point1Y, point2X, point2Y);
            count = 0;
        }
    }

    /**
     * getName
     * @return name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * getIcon
     * @return name
     */
    public ImageIcon getIcon()
    {
        return this.icon;
    }


}