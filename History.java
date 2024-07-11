import java.util.*;
import java.awt.image.*;
import java.awt.Image;
import java.awt.*;

public class History {
    private Stack<BufferedImage> undo = new Stack<BufferedImage>();
    private Stack<BufferedImage> redo = new Stack<BufferedImage>();

    History(BufferedImage image) {
        this.append(image);
    }

    public BufferedImage undo() {
        if (undo.size() == 0)
            return null;

        BufferedImage image = undo.pop();
        if (undo.size() == 0)
            undo.push(image);
        else
            redo.push(clone(image));
        return clone(image);
    }

    public BufferedImage redo() {
        if (redo.size() == 0)
            return null;
        BufferedImage image = redo.pop();
        undo.push(clone(image));
        return clone(image);
    }

    public void append(BufferedImage image) {
        redo = new Stack<BufferedImage>(); //Clear redo stack
        undo.push(clone(image));
    }

    //Avoid pass by reference
    private BufferedImage clone(BufferedImage image) {
        BufferedImage newImage = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        Graphics graphics = newImage.getGraphics();
        graphics.drawImage(image, 0, 0, null);
        graphics.dispose();
        return newImage;
    }
}