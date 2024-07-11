import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.border.EmptyBorder;
import javax.xml.transform.Templates;
import java.util.Hashtable;

import java.awt.event.*;
import java.awt.*;
import java.util.ArrayList;

/**
This is the class that handles funtionality for the differen layers of the image
@author James Hahn, Giovanna Benetti, James Sharrock, James Gardner, Sam Mayhew, Harrison Jones
 */

public class LayerSelector extends JPanel implements ActionListener {
    private ArrayList<JButton> layerButtons = new ArrayList<JButton>();
    private JPanel layerWindow = new JPanel();
    private JButton addNewLayerButton = new JButton();
    private JButton deleteLayerButton = new JButton();
    private JButton hideLayerButton = new JButton();
    private Project project;

    /**
     the contructor for this object
     * @param pro the class that this object belongs to/is made in
     */
    public LayerSelector(Project pro) {
        this.project = pro;

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.setPreferredSize(new Dimension(0, 500));
        JLabel titleLabel = new JLabel("Layers");
        titleLabel.setBorder(new EmptyBorder(10,3,0,0));
        this.add(titleLabel);

        //Layers listed vertically
        layerWindow.setLayout(new BoxLayout(layerWindow, BoxLayout.Y_AXIS));
        layerWindow.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(layerWindow);

        JPanel layerAndModificationButtons = new JPanel();
        layerAndModificationButtons.setLayout(new GridLayout(1, 3));

        // sets up buttons window
        addNewLayerButton.setPreferredSize(new Dimension(110, 60));
        addNewLayerButton.setText("New Layer");
        addNewLayerButton.addActionListener(this);
        addNewLayerButton.setBackground(new Color(230, 251, 255));
        layerAndModificationButtons.add(addNewLayerButton);

        deleteLayerButton.setPreferredSize(new Dimension(130, 60));
        deleteLayerButton.setText("Delete Layer");
        deleteLayerButton.addActionListener(this);
        deleteLayerButton.setBackground(new Color(230, 251, 255));
        layerAndModificationButtons.add(deleteLayerButton);

        hideLayerButton.setPreferredSize(new Dimension(110, 60));
        hideLayerButton.setText("Hide Layer");
        hideLayerButton.addActionListener(this);
        hideLayerButton.setBackground(new Color(230, 251, 255));
        layerAndModificationButtons.add(hideLayerButton);

        layerAndModificationButtons.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        layerAndModificationButtons.setAlignmentX(Component.LEFT_ALIGNMENT);
        this.add(layerAndModificationButtons);
    }

    /**
     the if statements for the different buttons
     @param e the ActionEvent manager
     */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addNewLayerButton) // button for adding new layer
        {
            project.getEditor().addLayer();
        }  
        else if (e.getSource() == deleteLayerButton)
        {
            System.out.println("Delete");
            project.getEditor().deleteCurrentLayer();
        }else if(e.getSource() == hideLayerButton)
        {
            System.out.println("hide/show");
            if(project.getEditor().getCurrentLayer().isHidden())
            {
                project.getEditor().getCurrentLayer().showLayer();
                hideLayerButton.setBackground(new Color(230, 251, 255));
                hideLayerButton.setText("Hide Layer");
            }else
            {
                project.getEditor().getCurrentLayer().hideLayer();
                hideLayerButton.setBackground(new Color(255, 204, 253));
                hideLayerButton.setText("Show Layer");
            }
        }
        else {
            for (int button = 0; button < layerButtons.size(); button++) {
                if (e.getSource() == layerButtons.get(button)) {
                    project.getEditor().setCurrentLayer(button);
                    if(project.getEditor().getCurrentLayer().isHidden())
                    {
                        hideLayerButton.setBackground(new Color(255, 204, 253));
                        hideLayerButton.setText("Show Layer");
                    }else
                    {
                        hideLayerButton.setBackground(new Color(230, 251, 255));
                        hideLayerButton.setText("Hide Layer");
                    }
                }
            }
        }
    }

    /**
     this is the method that handles the deleting of selected layers
     * @param index the index of the selected layer
     */
    public void deleteLayerButton(int index){
        JButton toRemove = layerButtons.get(index);
        layerWindow.remove(toRemove);
        layerButtons.remove(index);
        project.getEditor().setCurrentLayer(Math.min(project.getEditor().getCurrentLayerIndex(), layerButtons.size()- 1));
        this.revalidate();
        this.repaint();
    }
    
    /**
     changes the colour of buttons for selected and un-selected layers
     * @param layerNumber the index of the most recently selected layer
     */
    public void setSelectedLayer(int layerNumber) {
        try {
            System.out.println("Layer "+ layerNumber + " selected");

            for (int index = 0; index < layerButtons.size(); index++) {
                layerButtons.get(index).setBackground(new Color(230, 251, 255)); //Gray
            }
            layerButtons.get(layerNumber).setBackground(new Color(128, 255, 128)); //Green
        } catch (NullPointerException e) {
            System.out.println("LayerSelector: setSelectedLayer null pointer exception");
        }
    }
    
    /**
     add a new layer to the image, new layer button to the screen, saves the new layer in a list
     */
    public void addLayerButton() {
        JButton button = new JButton(); // create sbutton

        // sets up button
        button.setName("layer" + layerButtons.size());
        button.setText("layer " + layerButtons.size());
        button.addActionListener(this);
        button.setBackground(new Color(230, 251, 255)); //Gray
        if (layerButtons.size() == 0) {
            button.setBackground(new Color(128, 255, 128)); //Green
        }
        // layerWindow.setLayout(null);
        layerButtons.add(button);

        layerWindow.add(button); // adds button to window
        button.setMaximumSize(new Dimension(Integer.MAX_VALUE, 40));

        this.revalidate();
        this.repaint();

        System.out.println("added button");
    }
}
