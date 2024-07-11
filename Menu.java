import javax.swing.*;

import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.Scanner;
import javax.imageio.ImageIO;
import java.io.File;
import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.util.ArrayList;

/**
This is the file that handles the functionlaity of files systems. Saving an image, opening one form file, etc.
@author James Hahn, Giovanna Benetti, James Sharrock, James Gardner, Sam Mayhew, Harrison Jones
 */

public class Menu extends JComponent implements ActionListener {
    // frame
    private Project project;

    // menu bar
    private JMenuBar menuBar = new JMenuBar();

    // file menu
    private JMenu fileMenu = new JMenu("File");
    private JMenuItem newOption = new JMenuItem("New");
    private JMenuItem saveOption = new JMenuItem("Save");
    private JMenuItem saveAsOption = new JMenuItem("Save As");
    private JMenuItem openOption = new JMenuItem("Open");

    // edit menu
    private JMenu editMenu = new JMenu("Edit");
    private JMenuItem undoOption = new JMenuItem("Undo");
    private JMenuItem redoOption = new JMenuItem("Redo");
    private JMenuItem resizeOption = new JMenuItem("Resize");

    // help
    private JMenu helpMenu = new JMenu("Help");
    private JMenuItem helpOption = new JMenuItem("Help");

    private int Xvalue;
    private int Yvalue;

    private String currentFilepath = "";

    private Canvas canvas;
    public int width;
    public int height;
    public JLabel label;

    private Resize Rs;

    /**
     the constructor for this object
     * @param project the class this object belongs to/is created in
     */
    public Menu(Project project) {
        this.project = project;

        Rs = new Resize(project);

        // file menu
        menuBar.add(fileMenu);
        fileMenu.add(newOption);
        fileMenu.add(saveOption);
        fileMenu.add(saveAsOption);
        fileMenu.add(openOption);

        newOption.addActionListener(this);
        saveOption.addActionListener(this);
        saveAsOption.addActionListener(this);
        openOption.addActionListener(this);

        // edit menu
        menuBar.add(editMenu);
        editMenu.add(undoOption);
        editMenu.add(redoOption);
        editMenu.add(resizeOption);

        undoOption.addActionListener(this);
        redoOption.addActionListener(this);
        resizeOption.addActionListener(this);

        // help menu
        menuBar.add(helpMenu);
        helpMenu.add(helpOption);

        helpOption.addActionListener(this);
    }

    /**
     gets the help text for the system from the .txt file and adds it to a text area
     * @return the text area object
     * @throws IOException
     */
    private JTextArea readText() throws IOException {
        JTextArea textArea = new JTextArea();
        FileReader reader = new FileReader("HelpText.txt");
        textArea.read(reader, "HelpText.txt");
        textArea.setEditable(false);

        return textArea;

    }

    /**
     the method for saving an images as a new file type/for the first time. PNG, JPEG, etc
     */
    public void saveAs() {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
            currentFilepath = fileChooser.getSelectedFile().getAbsolutePath();
            saveImage(currentFilepath);
        }
    }

    /**
     the if statements for the different buttons, etc.
     */
    public void actionPerformed(ActionEvent e) {
        // file options
        if (e.getSource() == newOption) {
            System.out.println("new"); // Debug
        } else if (e.getSource() == saveOption) {
            if (currentFilepath.length() != 0) {
                saveImage(currentFilepath);
            } else {
                saveAs();
            }
        } else if (e.getSource() == saveAsOption) {
            saveAs();
        } else if (e.getSource() == openOption) {

            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                currentFilepath = fileChooser.getSelectedFile().getAbsolutePath();
            }
            try {
                BufferedImage image = ImageIO.read(new File(currentFilepath));
                project.getEditor().addLayer(image);

            } catch (IOException ex) {
                System.out.println(ex.toString());
                System.out.println("Could not find file");
            }
        }
        // edit options
        else if (e.getSource() == undoOption) {
            project.getEditor().getCurrentLayer().undo();
        } else if (e.getSource() == redoOption) {
            project.getEditor().getCurrentLayer().redo();
        } else if (e.getSource() == resizeOption) {
            System.out.println("resize");
            Rs.createResize();

        }
        // help options
        else if (e.getSource() == helpOption) {
            System.out.println("help");

            try {
                JOptionPane.showMessageDialog(null, readText());
            } catch (IOException e1) {
                System.out.println("Error reading helptext");
            }
        }
    }

    /**
     saves the current image, all layers, to the file directory specified by the user with the given extension.
     * @param filename the file path to save the image in
     */
    private void saveImage(String filename) {
        try {
            ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
            ArrayList<Canvas> layers = project.getEditor().getLayers();
            for (int index = 0; index < layers.size(); index++) {
                if (layers.get(index) == null)
                    continue;
                images.add(layers.get(index).getImage());
            }

            BufferedImage image = mergeBufferedImages(images);

            String[] stuff = filename.split("\\.");
            String extension;

            if (stuff.length > 1) {
                extension = stuff[1];
            }else
            {
                extension = "png";
            }

            System.out.println(extension);


            File output = new File(stuff[0] + "." + extension);

            ImageIO.write(image, extension, output);
            System.out.println("Saved");
        } catch (IOException error) {
            System.out.println("error");
        }
    }

    /**
     merges all the layers into one single buffered image
     * @param images a list of all the difefrent layers
     * @return merge the final, complete image
     */
    private BufferedImage mergeBufferedImages(ArrayList<BufferedImage> images) {
        int width = 0;
        int height = 0;
        for (int index = 0; index < images.size(); index++) {
            width = Math.max(width, images.get(index).getWidth());
            height = Math.max(height, images.get(index).getHeight());
        }

        BufferedImage merge = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        Graphics mergeGraphics = merge.getGraphics();
        for (int index = 0; index < images.size(); index++) {
            mergeGraphics.drawImage(images.get(index), 0, 0, null);
        }
        mergeGraphics.dispose();

        return merge;
    }

    /**
     returns the menuBar object
     * @return menuBar the object
     */
    public JMenuBar getMenuBar() {
        return menuBar;
    }

    /**
     returns the int value for the new image size width
     * @return the X int value
     */
    public int getXResize() {
        return Xvalue;
    }

    /**
     returns the int value for the new image size height
     * @return the Y int value
     */
    public int getYresize() {
        return Yvalue;
    }

}