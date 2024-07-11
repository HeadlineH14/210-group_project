import javax.swing.ImageIcon;
import java.awt.Graphics2D;

public class BackgroundColorTool implements Tool {
    private String name = "Background";
    private ImageIcon icon = new ImageIcon(new ImageIcon("icons/background-icon.png").getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));

    BackgroundColorTool() {

    }

    public void paint(Canvas canvas, Graphics2D graphics) {
        graphics.setColor(canvas.getBrushColor());
        graphics.fillRect(0, 0, 2000, 2000);
    };

    public String getName() {
        return this.name;
    }

    public ImageIcon getIcon() {
        return this.icon;
    }
}