import javax.swing.ImageIcon;
import java.awt.*;

public class OvalTool extends ShapeTools
{
    //decalare and assign variables
    private String name = "Oval";
    private ImageIcon icon = new ImageIcon(new ImageIcon("icons/oval-icon.png").getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));

    /**
     * fillShape
     * Implementation of fill shape from parent class used to draw an oval
     */
    public void fillShape(Graphics2D graphics, Canvas canvas, int point1X, int point1Y, int lengthX, int lengthY) 
    {
        graphics.fillOval(point1X, point1Y, lengthX, lengthY);          //draw oval
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
     * @return icon
     */
    public ImageIcon getIcon()
    {
        return this.icon;
    }
}