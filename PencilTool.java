import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.*;

public class PencilTool implements Tool {
    private String name = "Pencil";
    private ImageIcon icon = new ImageIcon(new ImageIcon("icons/pencil-draw-icon.png").getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));

    PencilTool() {

    }

    public void paint(Canvas canvas, Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(1)); //Pencil has the minimum stroke

        graphics.setColor(canvas.getBrushColor());
        graphics.drawLine(canvas.newX, canvas.newY, canvas.oldX, canvas.oldY); // draws line from where mouse was to where it is
    }

    public String getName() {
        return this.name;
    }

    public ImageIcon getIcon() {
        return this.icon;
    }
}