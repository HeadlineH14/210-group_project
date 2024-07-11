import javax.swing.*;
import javax.swing.event.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
This is the object that allows the user to change the colour of the brush
@author James Hahn, Giovanna Benetti, James Sharrock, James Gardner, Sam Mayhew, Harrison Jones
 */
public class ColorSelector extends JPanel implements ChangeListener {
    // declares local variables
    // panel
    private JPanel panel = new JPanel();
    private JPanel sliderPanel = new JPanel();
    private ColorDisplay colorDisplay = new ColorDisplay();
    // sliders
    private JSlider redSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
    private JSlider greenSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
    private JSlider blueSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 0);
    private JSlider alphaSlider = new JSlider(JSlider.HORIZONTAL, 0, 255, 255);
    private JSlider brushSizeSlider;
    // lables
    private JLabel redLbl = new JLabel("Red: ");
    private JLabel greenLbl = new JLabel("Green: ");
    private JLabel blueLbl = new JLabel("Blue: ");
    private JLabel alphaLbl = new JLabel("Opacity: ");
    // misc
    private int rgbaVal[] = { 0, 0, 0, 255 };
    private Color newColor = new Color(0, 0, 0, 255);
    private int brushSize = 20;
    private int width = 400;
    private int height = 500;
    private Project project;

    /**
     the constructor for the object
     * @param pro the class that this object is created in/belongs to
     */
    public ColorSelector(Project pro) {
        project = pro;

        // sets up panel
        this.setSize(width, height);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        panel.setSize(width, height);
        panel.setLayout(new GridLayout(2, 1));
        panel.setVisible(true);

        colorDisplay.setLocation(1, 1);
        colorDisplay.setVisible(true);

        sliderPanel.setSize(width, height / 2);
        sliderPanel.setLayout(new GridLayout(4, 2));

        // sets up sliders
        redSlider.setMinorTickSpacing(25);
        redSlider.setPaintTicks(true);
        redSlider.addChangeListener(this);
        redSlider.setSize(260, 20);
        redSlider.setLocation(1, 1);

        greenSlider.setMinorTickSpacing(25);
        greenSlider.setPaintTicks(true);
        greenSlider.addChangeListener(this);
        greenSlider.setSize(260, 20);
        greenSlider.setLocation(2, 1);

        blueSlider.setMinorTickSpacing(25);
        blueSlider.setPaintTicks(true);
        blueSlider.addChangeListener(this);
        blueSlider.setSize(260, 20);
        blueSlider.setLocation(3, 1);

        alphaSlider.setMinorTickSpacing(25);
        alphaSlider.setPaintTicks(true);
        alphaSlider.addChangeListener(this);
        alphaSlider.setSize(260, 20);
        alphaSlider.setLocation(4, 1);

        // sets up labels
        redLbl.setSize(70, 15);
        redLbl.setLocation(1, 2);
        redLbl.setText("Red: " + rgbaVal[0]);

        greenLbl.setSize(70, 15);
        greenLbl.setLocation(2, 2);
        greenLbl.setText("Green: " + rgbaVal[1]);

        blueLbl.setSize(70, 15);
        blueLbl.setLocation(3, 2);
        blueLbl.setText("Blue: " + rgbaVal[2]);

        alphaLbl.setSize(70, 15);
        alphaLbl.setLocation(4, 2);
        alphaLbl.setText("Opacity: " + rgbaVal[3]);

        // adds components to panel
        this.add(panel);
        sliderPanel.add(redLbl);
        sliderPanel.add(redSlider);
        sliderPanel.add(greenLbl);
        sliderPanel.add(greenSlider);
        sliderPanel.add(blueLbl);
        sliderPanel.add(blueSlider);
        sliderPanel.add(alphaLbl);
        sliderPanel.add(alphaSlider);
        panel.add(sliderPanel);
        panel.add(colorDisplay);

        brushSizeSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 20);
        brushSizeSlider.setSize(100, 200);

        JPanel sliderPanel = new JPanel();
        sliderPanel.setLayout(new BoxLayout(sliderPanel, BoxLayout.Y_AXIS));

        JLabel sliderLabel = new JLabel("Brush Thickness");
        sliderLabel.setBorder(new EmptyBorder(10,0,10,0));
        sliderPanel.add(sliderLabel);
        sliderPanel.add(brushSizeSlider);

        this.add(sliderPanel);

        brushSizeSlider.setMajorTickSpacing(50);
        brushSizeSlider.setMinorTickSpacing(10);
        brushSizeSlider.setPaintLabels(true);
        brushSizeSlider.setPaintTicks(true);
        brushSizeSlider.addChangeListener(this);
        
        colorDisplay.setNewColor(Color.black);
        colorDisplay.revalidate();
        colorDisplay.repaint();
    }

    /**
     changes the size of the brush to the one the user selected
     */
    public void updateBrushSize() {
        brushSize = brushSizeSlider.getValue();
    }

    /**
     functionality that handles when the colour sliders are changes
     @param event the ChangeEvent manager
     */
    public void stateChanged(ChangeEvent event) {
        if (event.getSource() == redSlider) {
            rgbaVal[0] = redSlider.getValue();
            redLbl.setText("Red: " + rgbaVal[0]);
        } else if (event.getSource() == greenSlider) {
            rgbaVal[1] = greenSlider.getValue();
            greenLbl.setText("Green: " + rgbaVal[1]);
        } else if (event.getSource() == blueSlider) {
            rgbaVal[2] = blueSlider.getValue();
            blueLbl.setText("Blue: " + rgbaVal[2]);
        } else if (event.getSource() == alphaSlider) {
            rgbaVal[3] = alphaSlider.getValue();
            alphaLbl.setText("Opacity: " + rgbaVal[3]);
        } else if (event.getSource() == brushSizeSlider) {
            updateBrushSize();
        }
        updateColor();
    }

    /**
     changes the colour of the display window to show the user the colour they selected
     updates in real time
     */
    public void updateColor() {
        newColor = new Color(rgbaVal[0], rgbaVal[1], rgbaVal[2], rgbaVal[3]);
        colorDisplay.setNewColor(newColor);
        colorDisplay.paintComponent(colorDisplay.getGraphics());
    }

    /**
     returns the choses color
     * @return newColour the int values of the selected colour
     */
    public Color getColor() {
        return newColor;
    }

    /**
     returns the int value for the new brush size
     * @return the int for the new brush size
     */
    public int getBrushSize() {
        return brushSize;
    }
}