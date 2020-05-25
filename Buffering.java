import javax.swing.JFrame; //For the window
import java.awt.Graphics; //For the visuals
import java.awt.Color; //To add color
import javax.swing.ImageIcon; //To add images

/**
 * Author: Shalee Qureshi
 * Date: December 26, 2019 
 * Description: This program acts as a buffering screen after the user creates an account, logins in, or continues as guest from class BusyAirField- 
 * this class must be run from class BusyAirField to ensure all portions work properly
 * 
 * Method List:
 * drawBackground (Graphics g) = Draws background of window
 * cloud (Graphics g) = Draws an array of clouds
 * paint (Graphics g) = Draws all visuals (main graphics class all graphics methods must be called here to be displayed)
 * 
 */
public class Buffering extends JFrame{
  
  ImageIcon cloud = new ImageIcon ("cloudBuffering.png"); //Declaring/Assigning an ImageIcon variable to an image
  ImageIcon tower = new ImageIcon ("controlTower.png"); //Declaring/Assigning an ImageIcon variable to an image
  ImageIcon buffer = new ImageIcon ("buffer.gif");  //Declaring/Assigning an ImageIcon variable to a gif
  
  int cloudY[]; //Declaring an array for the clouds y coordinates
  int cloudX[]; //Declaring an array for the clouds x coordinates
  
  int x = 0; //x coordinate for avatar
   
  public Buffering () {
    
    
    //Setting JFrame
    setSize(600, 400); // Setting window size
    setLocationRelativeTo(null); // Setting the windows location to the center of the display
    setResizable(false); // Making it so the user cannot resize the JFrame windows
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting its close operation
    
    //Initializing the int arrays with 20 elements  
    cloudY = new int [20];
    cloudX = new int [20];
    
    setVisible(true); //Making the window visible
    
  }//constructor 
  
  //This method draws the background of the window
  public void drawBackground (Graphics g) {
    
    g.setColor(new Color (179, 236, 255)); //Color for background  
    g.fillRect(0 , 0, 600, 400); //Making the background
    
    g.setColor(Color.YELLOW); //Color for the sun 
    g.fillOval(550, 10, 100, 100); //Making the sun
    
    g.setColor(Color.BLACK); //Color for the runway
    g.fillRect(0, 300, 600, 100); //Making the runway
    
    tower.paintIcon(this ,g , 400, 180); //Adding a control tower image 
       
  }//drawBackground Method
  
  //This method draws an array of clouds 
  public void cloud (Graphics g) {
    
    //Loop to draw an array of clouds 
    for (int i = 0; i < cloudY.length; i++) {
      
      cloudY[i] = (int) (Math.random() * 5); //Setting the y coordinate for the clouds to be random between 0 and 5
      cloudX[i] = (int) (Math.random() * 600); //Setting the x coordiante for the clouds to be random between the 0 and the width of the window
      cloud.paintIcon(this, g, cloudX[i], cloudY[i]); //Adding the clouds to the window
      
    }
     
  }//cloud Method
  
  //This method paints all the visuals and displays it
  public void paint (Graphics g) {
    
    //Loop to move the plane
    for (int i = 0; i < 30; i++) {
      
      x = x + 100; //Adding to its x coordinate to make it move
      drawBackground(g); //Calling the drawBackground Method to paint the background the avatar will be in
      cloud(g); //Calling the cloud method to paint the clouds 
      buffer.paintIcon(this ,g,  262, 162);
      Tutorial.imageAvatar.paintIcon(this ,g ,x, 300); //Drawing the avatar on top of the background
      
      //If the x coordinate is equal to the width of the window then the following will occur
      if (x == 600) {
        x = 0; //Resetting the x coordinate 
      }
      //Following try-catch is used to reduce the time it takes to move to allow it to be seen 
      try {
        Thread.sleep(100); //Slowing each movement down to 100 miliseconds
      } catch (Exception error) {
        BusyAirField.errorEnd(); //Calling the error method from class BusyAirField incase an error occurs
      }
      
    }
    //Once the loop is finished the following will occur 
    setVisible(false); //Making the frame not visible 
    BusyAirField.mainFrame(); //Calling the mainFrame Method to create the main GUI
    BusyAirField.frameMainGUI.setVisible(true); //Making this frame visible 
    
  }//paint Method
  
  public static void main(String[] args) { 
    new Buffering();
  }//main Method  
  
}//class Buffering
