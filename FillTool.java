import javax.swing.ImageIcon;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.*;
import java.util.HashSet;

public class FillTool implements Tool
{
    //declare and assign variables
    private String name = "Fill";
    private ImageIcon icon = new ImageIcon(new ImageIcon("icons/fill-icon.png").getImage().getScaledInstance(20, 20,  java.awt.Image.SCALE_SMOOTH));

    //Need a Stack/Queue Data Structure to use for the x and y values of the floodFill recursions

    private int xPos;
    private int yPos;
    private int width;
    private int height;
    /**
     *  paint
     *  The paint method in the FillTool class is used to fill the entire canvas with a single colour
     * */ 
    public void paint(Canvas canvas, Graphics2D graphics)
    {
        graphics.setColor(canvas.getBrushColor());      //sets color to change the canvas to
        xPos = canvas.getXCoord();
        yPos = canvas.getYCoord();
        width = canvas.getWidth();
        height = canvas.getHeight();
        BufferedImage canvasImage = canvas.getImage();
        
        floodFill(xPos, yPos, canvasImage.getRGB(xPos, yPos), canvasImage, graphics, canvas);
    }

    public void floodFill(int x, int y, int oldCol, BufferedImage image, Graphics2D graphics, Canvas canvas)
    {
        Queue q = new Queue();
        q.enqueue(boundaryX(x), boundaryY(y));

        while(q.front != null){
            int tempx = q.front.key1;
            int tempy = q.front.key2;
            q.dequeue();

            if(image.getRGB(tempx, tempy) == oldCol){
                // System.out.println("x: " + tempx + " y: " + tempy);
                graphics.fillRect(tempx, tempy, 1, 1);

                int[][] targets = {
                    {boundaryX(tempx-1), boundaryY(tempy)}, //WEST
                    {boundaryX(tempx+1), boundaryY(tempy)}, //EAST
                    {boundaryX(tempx), boundaryY(tempy-1)}, //NORTH
                    {boundaryX(tempx), boundaryY(tempy+1)}  //SOUTH
                };

                for (int i = 0; i < 4; i++) {
                    if (!q.historyHas(targets[i][0], targets[i][1])) {
                        q.enqueue(targets[i][0], targets[i][1]); 
                    }
                }

            }
        }
        
        /*
        x = boundaryX(x); //Keeps x within area
        y = boundaryY(y); //Keeps y within area
        int Col = image.getRGB(x, y);
        if(Col == oldCol){
            
            graphics.fillRect(x, y, 1, 1);
            //Maybe append x and y values that have been filled in to an array and then check that for each
            //recursion the values being inputted don't match so that it doesnt't stackoverflowerror
            floodFill(x-1, y, oldCol, image, graphics, canvas); //WEST
            floodFill(x+1, y, oldCol, image, graphics, canvas); //EAST
            floodFill(x, y-1, oldCol, image, graphics, canvas); //NORTH
            floodFill(x, y+1, oldCol, image, graphics, canvas); //SOUTH
        }else{
            return;
        }
        */
    }

    public int boundaryX(int x){   //Keeps coordinates within canvas area
        if(x >= width-1){
            x = width-1;
        }else if(x <= 1){
            x = 1;
        }
        return x;
    }

    public int boundaryY(int y){   //Keeps coordinates within canvas area
        if(y >= height-1){
            y = height-1;
        }else if(y <= 1){
            y = 1;
        }
        return y;
    }



    /**
     * getName
     * The getName function returns the name of the FillTool to be used in other classes
     * @return name
     */
    public String getName()
    {
        return this.name;
    }

    /**
     * getIcon
     * The getIcon function returns the icon held in the FillTool class to be used in other classes
     * @return icon
     */
    public ImageIcon getIcon()
    {
        return this.icon;
    }
}

// Java program for linked-list implementation of queue
 
// A linked list (LL) node to store a queue entry
class QNode {
    public int key1;
    public int key2;
    public QNode next;
 
    // constructor to create a new linked list node
    public QNode(int key1, int key2)
    {
        this.key1 = key1;
        this.key2 = key2;
        this.next = null;
    }
}
 
// A class to represent a queue
// The queue, front stores the front node of LL and rear
// stores the last node of LL
class Queue {
    QNode front, rear;

    // Previously existing queue key pairs
    HashSet<String> history = new HashSet<String>();
 
    public Queue() { this.front = this.rear = null; }
 
    // Method to add an key to the queue.
    void enqueue(int key1, int key2)
    {
 
        // Create a new LL node
        QNode temp = new QNode(key1, key2);
        history.add(Integer.toString(key1) + ", " + Integer.toString(key2));
 
        // If queue is empty, then new node is front and
        // rear both
        if (this.rear == null) {
            this.front = this.rear = temp;
            return;
        }
 
        // Add the new node at the end of queue and change
        // rear
        this.rear.next = temp;
        this.rear = temp;
    }
 
    // Method to remove an key from queue.
    void dequeue()
    {
        // If queue is empty, return NULL.
        if (this.front == null)
            return;
 
        // Store previous front and move front one node
        // ahead
        QNode temp = this.front;
        this.front = this.front.next;
 
        // If front becomes NULL, then change rear also as
        // NULL
        if (this.front == null)
            this.rear = null;
    }

    // Method to check if the given key pair is in the history
    boolean historyHas(int key1, int key2) {
        String match = Integer.toString(key1) + ", " + Integer.toString(key2);
        if (history.contains(match)) {
            return true;
        }
        return false;
    }

}

/* 
Color c = new Color(Col, true);
&& (canvas.getBrushColor()  !=  c)
*/

/*
floodFill(tempx-1, tempy, oldCol, image, graphics, canvas); //WEST
floodFill(tempx+1, tempy, oldCol, image, graphics, canvas); //EAST
floodFill(tempx, tempy-1, oldCol, image, graphics, canvas); //NORTH
floodFill(tempx, tempy+1, oldCol, image, graphics, canvas); //SOUTH
 */
