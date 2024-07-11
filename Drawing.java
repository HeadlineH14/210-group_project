import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import java.awt.*;

/**
This is the overall project file. the file you run to start the software
@author James Hahn, Giovanna Benetti, James Sharrock, James Gardner, Sam Mayhew, Harrison Jones
 */

public class Drawing extends JFrame implements ChangeListener {
    // declares global variables
    private JTabbedPane tabs = new JTabbedPane();

    // misc
    private Project[] projectWindows = new Project[10];
    private int index = 0;
    private int selectedTab = -1;

    /**
    this is the starting method, that sets up with window and containing components
     */
    public void startProgram() {
        // sets up window
        this.setSize(1000, 1000);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        tabs.setSize(1000, 1000);
        tabs.addTab("NEW TAB", null);
        tabs.addChangeListener(this);

        addNewTab();
        tabs.setSelectedIndex(index);

        this.add(tabs);
    }

    /**
    this creates a new object of project, which are copies of all the drawing, saving, etc.
    this allows the user to have multiple tabs of the project running at once
     */
    public void addNewTab() {
        Project newProj = new Project(); // creates new tab

        projectWindows[index] = newProj; // saves tab

        tabs.addTab("TAB " + (index + 1), newProj); // adds tab to pane

        index++;
    }

    /**
    this contains the methods for what happens when the tabbed panes object is interacted with
    @param event the ChangeEvent manager
     */
    public void stateChanged(ChangeEvent event) {
        JTabbedPane source = (JTabbedPane) event.getSource();

        if (source.getSelectedIndex() == 0) {
            if(index < 10)
            {
                addNewTab();
                tabs.setSelectedIndex(index);

            }else
            {
                JOptionPane.showMessageDialog(null, "CAN'T ADD LAYER");
            }
        }
    }

    /**
     the main method the program starts from
     * @param args arguments
     */
    public static void main(String[] args) {
        Drawing draw = new Drawing();
        draw.startProgram();
    }
}