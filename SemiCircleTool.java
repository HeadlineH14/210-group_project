import javax.swing.ImageIcon;
import java.awt.*;

public class SemiCircleTool extends ShapeTools
{
    //declare and assign variables
    private String name = "Semicircle";
    private ImageIcon icon = new ImageIcon(new ImageIcon("icons/semicircle-icon.png").getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));

    /**
     * fillShape
     * Implementation of fill shape from parent class used to draw a semicircle
     */
    public void fillShape(Graphics2D graphics, Canvas canvas, int point1X, int point1Y, int lengthX, int lengthY)
    {
        graphics.fillArc(point1X, point1Y, lengthX, lengthY*2, 0, 180);          //draw semicircle
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