import java.awt.Graphics; //To create visuals
import java.awt.Color; //To add color to the visuals
import javax.swing.JFrame; //To create the windows (GUIs)
import javax.swing.Timer; //To repaint the screen after a set time interval
import java.awt.event.KeyListener; //To listen to keyboard keys
import java.awt.event.KeyEvent; //Abstract methods to perform actions when invoked
import java.awt.event.ActionListener; //To listen to actions that occur
import java.awt.event.ActionEvent; //To perform tasks when an action is invoked
import java.awt.Rectangle; //Used for collision detection 
import java.io.IOException; //Incase an error occurs with the file 
import javax.swing.JLabel; //To create labels
import javax.swing.JTextArea; //Method of displaying information
import javax.swing.JButton; //To create buttons
import javax.swing.ImageIcon; //To add images
import java.io.FileWriter; //To open a stream to write to a file
import java.io.PrintWriter; //To write to a file stream
import java.awt.Font; //To alter text properties
import javax.swing.JOptionPane; //To show dialog boxes

/**
 * Date: December 26, 2019
 * Author: Shalee Qureshi
 * Description: This program creates/runs the actual game the user will play. This class runs either when the user completes the tutorial-
 * from the Tutorial class or if the user wants to skip the tutorial and go right to the real game from the BusyAirField class
 * 
 * Method List:
 * 
 * paint(Graphics g) = Draws the entire game
 * drawBackground(Graphics g) = Draws the game background
 * game(Graphics g) = This is where the avatar is added to the window 
 * drawObjects(Graphics g) = This method is responsible for creating the randomly spawning objects
 * nameIndex (String array[], String name) = Gets the index of the string element from a file
 * sortDescending (int array[], String array2[]) = Sorts an integer array and its corresponding string array in descending order (greatest to least) 
 * score() = This method will be responsible for all score related things
 * loseMsg() = This method will display a window when the user loses the game
 * actionPerformed(ActionEvent e) = Invoked when an action occurs - used to perform tasks when components are acted on
 * keyPressed(KeyEvent e) = Invoked when the user presses keys on the keyboard and performs tasks based on the key that is pressed
 * keyReleased(KeyEvent e) = Invoked when the user releases a key - not used
 * keyTyped(KeyEvent e) = Invoked when the user types - not used
 */

public class RealGame extends JFrame implements ActionListener, KeyListener {
  
  
  //Declaring x,y coordinates for the avatar
  int xAvatar = 200;
  int yAvatar = 200;
  
  Timer timer; //Creating a timer which will repaint the window
  int timeCounter = 0; //Counts time 
  
  //Declaring x,y coordinates for the object
  int xObject = Tutorial.width + 100;
  int yObject[]; 
  int objectWidth = 75;
  int objectHeight = 75;
  
  //Color color[] = {Color.RED, Color.BLACK, Color.BLUE, Color.YELLOW, Color.PINK, Color.WHITE}; //Declaring/Initializing an array for color
  
  int arraySize = 3; //Declaring/Initializing the array size for the objects 
  int speed = 6; //Declaring/Initializing the speed of the objects 
  
  Rectangle rectObject, rectAvatar; //Creating two rectangles to make collision detection easier
  
  JFrame frameLose = new JFrame (BusyAirField.frameName); //Creating a JFrame for the window that will appear when the user loses the game
  
  ImageIcon background = new ImageIcon("sky.png"); //For the background 
  ImageIcon rocket = new ImageIcon("rocket180.png"); //For the rocket
  
  //JComponents found in frameLose
  JButton btnPlayAgain = new JButton ("Play Again");
  JLabel lblLose = new JLabel ("<html><u>LeaderBoard");
  JTextArea paneScore = new JTextArea ();
  JLabel lblTop5 = new JLabel ("<html><u>Top 5");
  JButton btnTutorial = new JButton ("Tutorial");
  
  //Declaring two arrays to read from a file and get the top 5 scores from the file to display 
  static int scores[];
  String userName[];
  
  String file = "scores.txt"; //Declaring/Initializing a variable for the file 
  
  
  String output = "\nPlayer\tScore\n======\t=====\n"; //Used to format the JTextArea in frameLose
  
  //Constructor
  public RealGame() {
    
    super(BusyAirField.frameName); //Name of window must be first
    
    //Setting JFrame
    setSize(Tutorial.width, Tutorial.height); // Setting window size
    setLocationRelativeTo(null); // Setting the windows location to the center of the display
    setResizable(false); // Making it so the user cannot resize the JFrame windows
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting its close operation
    addKeyListener(this); // Adding a key listener to the window
    
    //Setting the timer to a 50 milisecond delay
    timer = new Timer(50, this);
    
    //initializing the elements to all the arrays 
    yObject = new int [arraySize];
    scores = new int [BusyAirField.elements]; //Making the arraysize equal to that of the arrays in class BusyAirField
    userName = new String [BusyAirField.elements];
    
    rectAvatar = new Rectangle  (xAvatar, yAvatar, 88, 49); //Creating the rectangle for the avatar and settings its coordinates/dimensions 
    
    //Loop to manage the object's y coordinates 
    for (int i = 0; i < yObject.length; i++) {
      yObject[i] = (int) (Math.random() * 700); //Setting the y value for all yObject arrays 
      
      rectObject = new Rectangle (xObject, yObject[i], objectWidth, objectHeight); //Creating the rectangle for the objects 
      
    }
    setVisible(true); // Making the window visible
    
  }//constructor
  
  //This method will draw the visuals (entire game really)
  public void paint(Graphics g) {
    
    game(g); //Calling movingObjects method to run
    drawObjects(g); //Calling the drawObjects method to draw the obstacles
    System.gc(); //Calling JVM garbage collection
    
  }//paint Method
  
  //This method draws the background of the game
  public void drawBackground(Graphics g) {
    
    background.paintIcon(this ,g, 0, 0);
    
  }//drawBackground Method
  
  //This method is responsible for drawing the avatar the player will control, displaying the score, and making the game harder based on the duration the user is playing 
  public void game(Graphics g) {
    
    drawBackground(g); //Calling drawBackground method to paint the background
    
    g.setColor(Color.BLACK); //Setting the color for the background for the score
    g.fillRect(0, 0, 140, 80); //Creating a rectangle at specified coordinates
    
    g.setColor(Color.WHITE); //Setting the color for the score's text color 
    g.setFont(new Font ("Arial", Font.BOLD, 20)); //Setting the font for the score
    g.drawString("Score: " + timeCounter, 10, 50); //Adding the score 
    
    Tutorial.imageAvatar.paintIcon(this, g, xAvatar, yAvatar); //Adding the avatar
    
  }//game Method
  
  //This method draws the objects the user must evade
  public void drawObjects(Graphics g) {
    
    timer.start();  //Starting the timer so it repaints the window at a regular time interval
    
    int limit = Math.round(timeCounter + 100) / 100; //Creating a variable that will be used to increase the speed
    
    //If the timeCounter is greater than the interval of 100 - 1 above it then the following will occur
    if (timeCounter + 1 > 100 * limit - 1) {
      speed = speed +  4; //Adding 2 to speed
    }
    
    //Loop to create an array of objects
    for (int i = 0; i < arraySize; i++) {
      
      xObject = xObject - speed; //Subtracting the x coordinate of the objects by the set speed 

      rocket.paintIcon(this, g, xObject, yObject[i]); //Displays the rocket
      
      //Setting the rectangles dimensions to be accurate to the images on the window so they can be compared 
      rectObject.setRect(xObject, yObject[i], objectWidth, objectHeight);
      rectAvatar.setRect(xAvatar, yAvatar, 88, 49);
      
      //If the two rectangles intersect (collide) then the following will occur 
      if (rectObject.intersects(rectAvatar)) {
        setVisible(false); //Making the window false
        score();
        JOptionPane.showMessageDialog(null, "Your Score is: " + timeCounter);
        timer.stop(); //Stopping the timer 
        loseMsg(); //Calling the loseMsg Method to display a window
        break; //Exiting the loop
      }
      System.gc(); //Calls JVM to perform garbage collection
      //If the two rectangles do not intersect then their coordinates will be reset once they reach the following set x coordinate in the condition
      if (xObject <= -50) {
        //New loop to reset the element's values
        for (int j = 0; j < yObject.length; j++) {
          xObject = Tutorial.width + 100; //Setting the x value for all xObject arrays
          yObject[j] = (int) (Math.random() * 700); //Setting the y value for all yObject arrays 
        }
      }
    }
    
  }//drawObjects Method  
  
  //This method searches an array and returns an index if the two parameters match
  public static int nameIndex (String array[], String name){
  
    //Loop to go through the array
    for (int i = 0; i < array.length; i++) {
      //If the contents of the array is equal to the name then the following will occur 
      if (array[i].equals(name)) {
      return i; //Returning the index
      }    
    }
  
  return -1; //Returning an index of -1 meaning it was not found
  
  }//nameIndex Method
  
  //This method sorts an array in descending order along with its corresponding string array 
  public void sortDescending (int array[], String array2[]) {
  
        //Loop to run through the size of the array
    for (int i = 0; i < array.length; i++) {
      //Loop to ensure all parts of the array is sorted through
      for (int j = 0; j < array.length - 1; j++) {
        //If the array values are less than the following elements the following will occur
        if (array[j] < array[j + 1]) {
          //Putting the current element in a temporary location
          int tempLocation = array[j];
          //Putting the array ahead of the current into the current location 
          array[j] = array [j + 1];
          //Putting the new ahead element in the temp location
          array [j + 1] = tempLocation;
          
          //Swapping the string array now to make sure the elements in the 2 arrays match 
          String tempLoc = array2[j];
          array2[j] = array2[j + 1];
          array2[j + 1] = tempLoc;
        } 
      }
    }
  
  }//sortDescending Method
  
  //This method reads from a file, gets the index and updates the player score in the file
  public void score () {

    BusyAirField.readFile("", userName, BusyAirField.playerPassword, scores, file); //This method reads from a file by calling the readFile Method from class BusyAirField
    int index = nameIndex(userName, BusyAirField.loginName); //Getting the index of the name

    try{
    FileWriter fileW1 = new FileWriter (file);
    PrintWriter output1 = new PrintWriter (fileW1);
    
    for (int i = 0; i < scores.length; i++) {
      if (userName[i] != userName[index]) {
      output1.println(userName[i] + "\n" + scores[i]);
      }
    
    }
    output1.println(userName[index] + "\n" + timeCounter);
    output1.close();
    } catch (IOException error) {}
  }//score Method
  
  //This method creates the window when the user loses the game 
  public void loseMsg() {
    
    setVisible(false); //Making the graphics window not visible
    
    BusyAirField.frameSetter(frameLose, 400, 300); //Calling frameSetter Method from class BusyAirField to create the frame size, color, etc.
    
    //Adding fonts to JComponents
    btnPlayAgain.setFont(BusyAirField.fontBtn);
    lblLose.setFont(BusyAirField.fontLabel);
    lblTop5.setFont(BusyAirField.fontLabel);
    btnTutorial.setFont(BusyAirField.fontBtn);
      
    //Changing background color of button
    btnPlayAgain.setBackground(Color.GRAY);
    btnTutorial.setBackground(Color.GRAY);
    
    
    //Altering JTextArea
    paneScore.setEditable(false);
    paneScore.setTabSize(15); 
    paneScore.setFont(new Font ("Arial", Font.BOLD, 15));
    
    score();
    sortDescending (scores, userName);
    
    for (int i = 0; i < scores.length; i++) {
      
      
      output = output + userName[i] + "\t" + scores[i] + "\n";
      
    } 
    
    paneScore.setText(output);
    //Setting/Adding JComponents (x, y, width, height) 
    
    lblLose.setBounds(140, 20, 150, 30);
    frameLose.add(lblLose);
    
    lblTop5.setBounds(170, 40 , 50, 50);
    frameLose.add(lblTop5);
    
    btnTutorial.setBounds(0, 220, 140, 50);
    frameLose.add(btnTutorial);
    
    btnPlayAgain.setBounds(140, 220, 140, 50);
    frameLose.add(btnPlayAgain);
    
    BusyAirField.btnExit.setBounds(280, 220, 120, 50);
    frameLose.add(BusyAirField.btnExit);
    
    paneScore.setBounds(50, 60, 300, 160);
    frameLose.add(paneScore); 
    
    //Adding ActionListener to buttons
    btnPlayAgain.addActionListener(this);
    btnTutorial.addActionListener(this);
    
    frameLose.setVisible(true); //Making this window visible 
    
    
    
  }//loseMsg Method  
  
  //This method is responsible for all actions occurring in the method
  public void actionPerformed(ActionEvent e) {
    //Every 50 miliseconds the timer will repaint the window
    if (e.getSource() == timer) {
      repaint(); //Repainting the window
      //If the timer is running then the following will occur 
      if (timer.isRunning() == true) {
        timeCounter++; //Adding 1 to counter (this is used to determine the user's score and make the game harder) 
      }
      System.gc(); //Performs garbage collection after the code above executes 
    }
    //If the user would like to play again after losing then the following will occur
    else if (e.getSource() == btnPlayAgain) {
      frameLose.dispose(); //Closing the lose frame
      new RealGame(); //Recalling the class
    }
    //If the user presses the tutorial button then the following will occur
    else if (e.getSource() == btnTutorial) {
      frameLose.dispose(); //Closing the losing frame
      new Tutorial (); //Calling tutorial class to run
    }
  }//actionPerformed Method
  
  //This method is responsible for performing tasks depending on what button is pressed
  public void keyPressed(KeyEvent e) {
    
    repaint(); //Repainting the visuals every time a key is pressed
    
    //If the user presses either S or the down arrow key the following will occur
    if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
      //Checking to see if the avatar does not leave the frame and if it does not the following will occur
      if (yAvatar < 720 - 150) {
        yAvatar = yAvatar + 50; //Adding the avatar's y position by 50
      }
    }
    //If the user presses either the W or the up arrow key the following will occur
    else if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
      //Checking to see if the avatar does not leave the frame and if it does not the following will occur
      if (yAvatar > 50) {
        yAvatar = yAvatar - 50; //Subtracting the avatar's y position
      }
    }
    //If the user presses either the A or the left arrow key the following will occur
    else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
      //Checking to see if the avatar does not leave the frame and if it does not the following will occur
      if (xAvatar > 50) {
        xAvatar = xAvatar - 50;
      }
    }
    //If the user presses either the D or right arrow key the following will occur
    else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
      //Checking to see if the avatar does not leave the frame and if it does not the following will occur
      if (xAvatar <= Tutorial.width) {
        xAvatar = xAvatar + 50;
      }
    }
    
  }//keyPressed Method
  
  public void keyReleased(KeyEvent e) {
    
  }//keyReleased Method
  
  public void keyTyped(KeyEvent e) {
    
  }//keyTyped Method
  
  public static void main(String[] args) {
    
    new RealGame();
    
    
  }//main Method
  
}//class RealGame