import javax.swing.ImageIcon;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.event.*;

public class RubberTool implements Tool {
    private String name = "Rubber";
    private ImageIcon icon = new ImageIcon(new ImageIcon("icons/eraser-icon.png").getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));

    RubberTool() {

    }

    public void paint(Canvas canvas, Graphics2D graphics) {
        graphics.setStroke(new BasicStroke(canvas.getBrushSize())); //Pencil has the minimum stroke

        graphics.setComposite(AlphaComposite.Clear);
        graphics.drawLine(canvas.newX, canvas.newY, canvas.oldX, canvas.oldY); // draws line from where mouse was to where it is
        graphics.setComposite(AlphaComposite.SrcOver);
    }

    public String getName() {
        return this.name;
    }

    public ImageIcon getIcon() {
        return this.icon;
    }
}