import javax.swing.*;
import java.awt.*;

/**
This is the Jpanel that displays the selected colour form ColourSelector to the user
@author James Hahn, Giovanna Benetti, James Sharrock, James Gardner, Sam Mayhew, Harrison Jones
 */
public class ColorDisplay extends JPanel
{
    private Color newColor;

    /**
     the built in paint component of the object
     changes the colour of the panel
     @param g the Graphics components of the object
     */
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        if(g instanceof Graphics2D)
        {
            Graphics2D g2d = (Graphics2D)g;
            
            //Transparent background texture
            int scale = 10;
            for (int x = 0; x < this.getWidth(); x+=scale) {
                for (int y = 0; y < this.getHeight(); y+=scale) {
                    if ((x + y) % 4 == 0) { //Fill every other square
                        g2d.setColor(Color.white);
                        g2d.fillRect(x,y,scale,scale);
                    }
                }
            }

            g2d.setColor(newColor);

            //Color overlay
            g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
            //g2d.setBackground(newColor);
            repaint();
        }
    }

    /**
     saves the colour from the ColourSelector class
     * @param col the colour int values from the ColourSelector class
     */
    public void setNewColor(Color col)
    {
        newColor = col;
    }
}
