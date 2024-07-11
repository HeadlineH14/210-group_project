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
This is the file that controlls/picks all the different tools that the user can use
@author James Hahn, Giovanna Benetti, James Sharrock, James Gardner, Sam Mayhew, Harrison Jones
 */
public class ToolPicker extends JPanel implements ActionListener {
    // private JFrame frame = new JFrame();
    private JToolBar toolbar;

    private Tool[] tools = {
            new PencilTool(),
            new BrushTool(),
            new RubberTool(),
            new LineTool(),
            new DashedLineTool(),
            new RectangleTool(),
            new RectangleOutlineTool(),
            new OvalTool(),
            new OvalOutlineTool(),
            new SemiCircleTool(),
            new SemiCircleOutlineTool(),
            new RubberTool(),
            new FillTool(),
            new BackgroundColorTool()
    };

    private int activeToolIndex = 0;
    private ArrayList<JButton> buttons = new ArrayList<JButton>();
    private JSlider slider;
    private Project project;

    /**
     the contructor for this object
     * @param pro the class that this object belongs too/is created in
     */
    public ToolPicker(Project pro) {
        this.project = pro;

        // frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        JLabel toolsLabel = new JLabel("Tools");
        toolsLabel.setBorder(new EmptyBorder(10,3,0,0));
        this.add(toolsLabel);

        toolbar = new JToolBar("Brushes", 1);
        toolbar.setFloatable(false);
        toolbar.setRollover(true);
        toolbar.addSeparator();

        this.add(toolbar);

        for (Tool tool : tools) {
            JButton button = new JButton(tool.getIcon());
            button.setToolTipText(tool.getName());
            button.addActionListener(this);
            toolbar.add(button);
            buttons.add(button);
        }

        //Undo and redo buttons
        String[] names = {"Undo", "Redo"};
        for (int i = 0; i < 2; i++) {
            JButton button = new JButton(names[i]);
            buttons.add(button);
            button.addActionListener(this);
            toolbar.add(button);
        }

        setButtonColorActive(0);

        this.setSize(100, 400);
    }

    /**
     the if statements that handles the functionality for the different buttons, GUI, etc.
     */
    public void actionPerformed(ActionEvent e) {
        if (!(e.getSource() instanceof JButton)) {
            System.out.println("Not a button");
            return;
        }
        for (int i = 0; i < buttons.size(); i++) {
            JButton button = buttons.get(i);
            if (e.getSource() == button) {
                if (i >= tools.length) { //Undo/redo button
                    if (i - tools.length == 0) {
                        //Undo
                        project.getEditor().getCurrentLayer().undo();
                    } else if (i - tools.length == 1) {
                        //Redo
                        project.getEditor().getCurrentLayer().redo();
                    }
                } else {
                    System.out.println(tools[i].getName() + " tool selected");
                    activeToolIndex = i;
                }
            }
        }
        //Set color
        for (int button = 0; button < tools.length; button++) {
            if (e.getSource() == buttons.get(button)) {
                setButtonColorActive(button);
                break;
            }
        }

    }

    /**
     changes the colour of the button for the tool that is currently selected
     * @param button the button for the tool selected
     */
    private void setButtonColorActive(int button) {
        for (int index = 0; index < tools.length; index++) {
            buttons.get(index).setBackground(new Color(230, 251, 255)); //Gray
        }
        buttons.get(button).setBackground(new Color(128, 255, 128)); //Green
    }

    /**
     returns the object of the tool that currently being used
     * @return the tool object
     */
    public Tool getActiveTool() {
        return tools[activeToolIndex];
    }

    /**
     returns the index of the active tool
     * @return the int index
     */
    public int getMode(){
        return activeToolIndex;
    }
}
