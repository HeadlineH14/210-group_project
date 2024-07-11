import javax.swing.ImageIcon;
import java.awt.Graphics2D;

public class BrushTool implements Tool {
    private String name = "Brush";
    private ImageIcon icon = new ImageIcon(new ImageIcon("icons/paint-brush-icon.png").getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));

    BrushTool() {

    }

    public String getName() {
        return this.name;
    }

    public ImageIcon getIcon() {
        return this.icon;
    }
}