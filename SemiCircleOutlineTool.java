import javax.swing.ImageIcon;
import java.awt.*;

public class SemiCircleOutlineTool extends ShapeTools
{
    //declare and assign variables
    private String name = "Semicircle(Outline)";
    private ImageIcon icon = new ImageIcon(new ImageIcon("icons/semicircleOutline-icon.png").getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));

    /**
     * fillShape
     * Implementation of fill shape from parent class used to draw a semicircle
     */
    public void fillShape(Graphics2D graphics, Canvas canvas, int point1X, int point1Y, int lengthX, int lengthY)
    {
        graphics.setStroke(new BasicStroke(canvas.getBrushSize()));
        graphics.drawArc(point1X, point1Y, lengthX, lengthY*2, 0, 180);          //draw semicircle
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