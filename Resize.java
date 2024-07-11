import javax.swing.JFrame;
import javax.swing.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.ArrayList;

/**
This is the file that allows the user to change the size (in pixels) of the image that want to draw on
@author James Hahn, Giovanna Benetti, James Sharrock, James Gardner, Sam Mayhew, Harrison Jones
 */
public class Resize extends JFrame implements ActionListener {
    public int Xvalue;
    public int Yvalue;
    public JButton confimResize;
    public JTextArea xField;
    public JTextArea yField;
    private Project project;

    /**
     the constructor for this object.
     Sets up the window and all components contained within
     * @param pro the Project class this object belongs to/is created in
     */
    public Resize(Project pro) {
        JPanel resizePanel = new JPanel();
        xField = new JTextArea(1, 5);
        yField = new JTextArea(1, 5);
        project = pro;
        JLabel xLabel = new JLabel("X size");
        JLabel yLabel = new JLabel("Y size");
        confimResize = new JButton("ok");
        resizePanel.add(xLabel);
        this.setSize(150, 120);
        this.add(resizePanel);
        resizePanel.add(xField);
        resizePanel.add(yLabel);
        resizePanel.add(yField);
        resizePanel.add(confimResize);
        confimResize.addActionListener(this);

    }

    /**
     sets the window visible
     */
    public void createResize() {
        this.setVisible(true);
    }

    /**
     Holds the methods for when buttons are clicked
     @param e the ActionEvent manager
    */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confimResize) {
            String Xstring = xField.getText();
            String Ystring = yField.getText();
            Xvalue = Integer.parseInt(Xstring);
            Yvalue = Integer.parseInt(Ystring);
            System.out.println(Xvalue);
            System.out.println(Yvalue);
            project.getEditor().Resize(Xvalue, Yvalue);
            this.setVisible(false);
        }

    }
}