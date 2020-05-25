import java.awt.Graphics; //To create visuals
import java.awt.Color; //To add color to the visuals
import java.awt.Font; //To alter the default font
import javax.swing.JFrame; //To create the window
import javax.swing.JOptionPane; //For the user of Dialog boxes
import javax.swing.ImageIcon; //To add visuals
import javax.swing.JButton; //Way for user to perform actions on the interface
import javax.swing.JLabel; //Method of displaying information
import java.awt.event.KeyListener; //To listen to keyboard keys
import java.awt.event.KeyEvent; //Abstract methods to perform actions when invoked
import java.awt.event.ActionListener; //To listen to actions that occur
import java.awt.event.ActionEvent; //To perform tasks when an action is invoked
import java.awt.event.MouseListener; //To listen to the user's mouse
import java.awt.event.MouseEvent; //To perform actions based off user's mouse

/**
 * Date: December 26, 2019
 * Author: Shalee Qureshi
 * Description: This program allows the user to play a tutorial of the actual game. It is called upon from the main class called BusyAirSpace
 *
 * Method List:
 * 
 * paint (Graphics g) = Paints all graphics
 * drawBackground (Graphics g) = Draws the background for all stages of the tutorial
 * drawFirstLevel (Graphics g) = Draws the first level of the tutorial - including the avatar
 * drawSecondLevel (Graphics g) = Draws the second level of the tutorial - including the avatar
 * repeatedComponents (JFrame frame) = Includes JComponents that are used in loseMsg() and completion() (method is here to avoid repetition)
 * loseMsg () = Displays a window to the user when they lose the tutorial
 * completion() = Displays a window to the user when they complete the tutorial
 * escapeMsg() = Displays a window to the user when they click the escape button on their keyboard during the game
 * avatarCheckLevel1 () = Checks if the avatar's position -in the first level-comes into contact with select coordinates and calls loseMsg() method if it does
 * avatarCheckLevel2 () = Checks if the avatar's position -in the second level- comes into contact with select coordinates and calls loseMsg() method if it does
 * actionPerformed (ActionEvent e) = Invoked when an action occurs and performs a task when specific components are acted on by the user
 * keyPressed (KeyEvent e) = Method invoked when user presses a key and performs a task when specific keys are pressed by the user
 * keyReleased (KeyEvent e) = Method invoked when user releases a key and performs a task - not used
 * keyTyped (KeyEvent e) = Method invoked when user types a key and performs a task - not used
 * mouseClicked (MouseEvent e) = Invoked when the mouse button has been clicked - not used
 * mouseExited(MouseEvent e) = Invoked when the mouse exits a component - not used
 * mousePressed(MouseEvent e) = Invoked when a mouse button has been pressed on a component - not used
 * mouseEntered(MouseEvent e) = Invoked when a mouse button hovers on a component (used to display a small blurb when the user hovers a button)
 * mouseReleased(MouseEvent e) = Invoked when a mouse button has been released on a component - not used
 */

public class Tutorial extends JFrame implements KeyListener, ActionListener, MouseListener {
  
  // Declaring/Initializing global variables
  
  static final int width = 1280; // Width of window (final so it cannot be changed)
  static final int height = 720; // Height of window (final so it cannot be changed)
  
  int yPosition = 250; // yPosition variable (will be for the player controlled avatar for level 1)
  int xPosition = 10; // XPosition variable (will be for the player controlled avatar for level 1)
  int xPositionLevel2 = 10; //xPosition variable (will be for the player controlled avatar for level 2)
  int yPositionLevel2 = 300; //yPosition variable (will be for the player controlled avatar for level 2)
  
  //JFrames
  JFrame frameLose = new JFrame(BusyAirField.frameName); //JFrame to create a window when the player loses
  JFrame frameDone = new JFrame(BusyAirField.frameName); //JFrame to create a window when the player completes the tutorial
  JFrame frameEsc = new JFrame(BusyAirField.frameName); //JFrame to create a window when the player presses the escape button
  
  //JComponenets found in frameLose
  JLabel lblLose = new JLabel("<html><u>CRASH!");
  //btnPlayAgain, lblPic, lblInPic2 and lblPic2 are also found in and frameDone
  JButton btnPlayAgain = new JButton("Play Again"); //Creating a button to give the user an opportunity to play again
  JLabel lblPic = new JLabel(new ImageIcon("plane3Small.png")); //Creating an image on a label
  JLabel lblPic2 = new JLabel(new ImageIcon("cloud.png")); //Creating an image on a label
  JLabel lblInPic2 = new JLabel("Mission Failed"); //Creating a label inside of lblPic2
  
  //JComponents found in frameDone
  JLabel lblCongrat = new JLabel("<html><u>Great Work Captain!");
  JButton btnRealGame = new JButton("Real Game");
  
  //Found in frameEsc
  JButton btnBackToGame = new JButton("Back");
  
  //Avatar controlled by the user
  static ImageIcon imageAvatar = new ImageIcon("plane2.png"); // Declaring an image
  
  //Creating a color used by all objects/obstacles
  Color objectColor = new Color(123, 197, 240);
  
  // The Constructor
  public Tutorial() {
    
    super("Busy AirSpace"); // Title of window must be first
    
    //Setting JFrame
    setSize(width, height); // Setting window size
    setLocationRelativeTo(null); // Setting the windows location to the center of the display
    setResizable(false); // Making it so the user cannot resize the JFrame windows
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting its close operation
    addKeyListener(this); // Adding a key listener to the window
    
    setVisible(true); // Making the window visible
    
  }// Constructor
  
  // The following method is responsible for painting the graphics
  public void paint(Graphics g) {
    
    //If the player completes level 1 by getting to the other size of the window the following will occur
    if (xPosition > 1280) {
      drawSecondLevel(g); //Drawing the second level of the tutorial
    } else {
      drawFirstLevel(g); // Calling the drawFirstBackground method to paint the background of the window
    }
    
  }// paint Method
  
  //This method draws the background for all stages in the game
  public void drawBackground(Graphics g) {
    g.setColor(Color.BLACK); // Making the background color of the window
    g.fillRect(0, 0, width, height); // Making a rectangle equal size to the window to set it as a background
  }//drawBackground Method
  
  // This method draws the first level of the tutorial
  public void drawFirstLevel(Graphics g) {
    
    //First we need to draw the background before drawing the avatar
    drawBackground(g); //Calling the drawBackground method to draw the background
    g.setColor(objectColor); // Setting the color for all shapes
    
    //Now we must create the obstacles
    
    // Creating the top half of the shape (x, y, width, height)
    g.fillRect(400, 0, 880, 200);
    
    // Creating the bottom half of the shape (x, y, width, height)
    g.fillRect(410, 490, 880, 230);
    
    //Creating the obstacle in the path (x, y, width, height)
    g.fillRect(1000, 200, 280, 120);
    
    // Drawing the avatar now that we have drawn the background
    imageAvatar.paintIcon(this, g, xPosition, yPosition); // xPosition and yPosition is altered by the user using key bindings
    
    avatarCheckLevel1(); //Calling the avatarCheckLevel1 method to determine if the user loses or not
    
    // Try catch used incase there is a problem with the Thread.sleep
    try {
      
      Thread.sleep(10); //Allows the movement to slow down for us to see
      
    } catch (Exception error) {
      
      BusyAirField.errorEnd(); // Calling errorEnd method from class BusyAirField to display a message and terminate the program
    }
    
  }// drawFirstLevel Method
  
  //This method draws the second level of the tutorial
  public void drawSecondLevel(Graphics g) {
    
    //First we need to draw the background before drawing the avatar
    drawBackground(g); //Calling the drawBackground method to draw the background of the stage
    
    //Now we must create the obstacles
    g.setColor(objectColor); //Setting all obstacles/object colors
    
    //Drawing the obstacles
    g.fillRect(300, 0, 150, 500);
    g.fillRect(300, 650, 660, 70);
    g.fillOval(500, 420, 160, 130);
    g.fillRect(450, 470, 70, 20);
    g.fillRect(940, 320, 180, 400);
    g.fillRect(575, 0, 50, 440);
    g.fillRect(625, 320, 125, 20);
    g.fillRect(740, 160, 10, 160);
    g.fillRect(740, 160, 515, 10);
    g.fillRect(950, 320, 330, 10);
    g.fillRect(0, 0, 300, 160);
    
    //Adding the avatar
    imageAvatar.paintIcon(this, g, xPositionLevel2, yPositionLevel2);
    avatarCheckLevel2(); //Calling the check method for level 2 to check if the player loses or not
    
    // Try catch used incase there is a problem with the Thread.sleep
    try {
      
      Thread.sleep(10); //Allows the movement to slow down for us to see
      
    } catch (Exception error) {
      
      BusyAirField.errorEnd(); // Calling errorEnd method from class BusyAirField to display a message and terminate the program
    }
    
  }//drawSecondLevel Method
  
  //This method contains repeated components found in the loseMsg and completion methods
  public void repeatedComponents(JFrame frame) {
    
    BusyAirField.frameSetter(frame, 300, 250); //Calling frameSetter method from class BusyAirField
    
    //Altering font/changing properties of JComponents
    btnPlayAgain.setFont(BusyAirField.fontBtn);
    btnPlayAgain.setBackground(Color.GRAY);
    
    lblInPic2.setFont(new Font("Arial", Font.PLAIN, 11));
    
    //Adding the avatar (x ,y , width, height)
    lblPic.setBounds(40, 130, 88, 49);
    frame.add(lblPic);
    lblPic2.setBounds(90, 60, 100, 100);
    frame.add(lblPic2);
    lblInPic2.setBounds(105, 62, 100, 100);
    frame.add(lblInPic2);
    
    //Adding listener to the button and changing its cursor
    btnPlayAgain.addActionListener(this);
    btnPlayAgain.addMouseListener(this);
    btnPlayAgain.setCursor(BusyAirField.handCursor);
    
  }//repeatedComponents Method
  
  //This method will be responsible for displaying a window whenever the user loses the tutorial
  public void loseMsg() {
    
    JOptionPane.showMessageDialog(null, "CRASH!"); //Displaying a message to the user
    setVisible(false); //Making the graphics window not visible
    
    lblLose.setFont(BusyAirField.fontLabel); //Setting font for the label
    
    repeatedComponents(frameLose); //Calling repeated components method
    
    //Adding/Setting JComponents
    lblLose.setBounds(110, 30, 100, 40);
    frameLose.add(lblLose);
    
    btnPlayAgain.setBounds(0, 180, 170, 40);
    frameLose.add(btnPlayAgain);
    
    BusyAirField.btnExit.setBounds(170, 180, 130, 40);
    frameLose.add(BusyAirField.btnExit);
    
    frameLose.setVisible(true); //Making the lose window visible
    
  }//loseMsg Method
  
  //This method displays a window when the user completes the tutorial
  public void completion() {
    
    setVisible(false); //Making the graphics window not visible
    
    lblInPic2.setText("Great Work!");
    repeatedComponents(frameDone); //Calling the repeatedComponents Method
    
    //Altering the properties of JComponents
    btnRealGame.setFont(BusyAirField.fontBtn);
    btnRealGame.setBackground(Color.GRAY);
    lblCongrat.setFont(BusyAirField.fontLabel);
    
    //Adding/Setting JComponents (x, y, width, height)
    btnRealGame.setBounds(0, 180, 115, 40);
    frameDone.add(btnRealGame);
    btnPlayAgain.setBounds(115, 180, 120, 40);
    frameDone.add(btnPlayAgain);
    BusyAirField.btnExit.setBounds(230, 180, 70, 40);
    frameDone.add(BusyAirField.btnExit);
    lblCongrat.setBounds(50, 30, 200, 40);
    frameDone.add(lblCongrat);
    
    //Adding listener to buttons
    btnRealGame.addActionListener(this);
    btnRealGame.addMouseListener(this);
    
    btnBackToGame.addActionListener(this);
    btnBackToGame.addMouseListener(this);
    
    //Changing btnRealGame cursor
    btnRealGame.setCursor(BusyAirField.handCursor);
    btnBackToGame.setCursor(BusyAirField.handCursor);
    
    frameDone.setVisible(true);
    
  }//competition Method
  
  //This method displays a window when the user presses the escape button
  public void escapeMsg() {
    
    BusyAirField.frameSetter(frameEsc, 300, 200);
    
    //Altering other buttons
    btnBackToGame.setBackground(Color.GRAY);
    btnBackToGame.setFont(BusyAirField.fontBtn);
    
    //Setting/Adding the buttons
    BusyAirField.btnExit.setBounds(50, 50, 100, 50);
    frameEsc.add(BusyAirField.btnExit);
    
    btnBackToGame.setBounds(150, 50, 100, 50);
    frameEsc.add(btnBackToGame);
    
    //Adding listeners to btnBackToGame
    btnBackToGame.addActionListener(this);
    btnBackToGame.addMouseListener(this);
    
    //Changing buttons cursor

    btnBackToGame.setCursor(BusyAirField.handCursor);
    
    frameEsc.setVisible(true);
    
  }//escapeMsg Method
  
  //This method will check the player's position and will determine if the player loses the game
  public void avatarCheckLevel1() {
    
    //If the user's avatar is beyond 250 and less than 5 on its x-axis the following occurs
    if (xPosition > 250 && xPosition < 850) {
      //Check to see if the user's avatar graces the coordinates below (y)
      if (yPosition <= 190 || yPosition >= 450) {
        //Displaying a message to the user
        loseMsg(); //Calling loseMsg method to display a message and scoreboard
      }
    }
    //If the user's avatar is past 850 on the x-axis the following will determine if they lose
    else if (xPosition > 850) {
      
      if (yPosition <= 320 || yPosition >= 450) {
        loseMsg(); //Calling loseMsg method to display a message and scoreboard
      }
    }
  }//avatarCheckLevel1 method
  
  public void avatarCheckLevel2() {
    
    //If the avatar's x coordinate is greater than 150  and less t han 450 the following will determine dif the player loses
    if (xPositionLevel2 > 120 && xPositionLevel2 < 380) {
      //Check to see if the avatar graces the y coordinates below and performs an action if it does
      if (yPositionLevel2 <= 495 || yPositionLevel2 >= 600) {
        loseMsg(); //Calling loseMsg method to display a message and scoreboard
      }
    }
    //If the avatar's x coordinate is greater than 450 and less than 650 the following will determine if the player loses
    else if (xPositionLevel2 > 380 && xPositionLevel2 < 650) {
      //Check to see if the avatar graces the y coordinates below and performs an action if it does
      if (yPositionLevel2 <= 500 || yPositionLevel2 >= 600) {
        loseMsg(); //Calling loseMsg method to display a message and scoreboard
      }
    }
    //If the avatar's x coordinate is greater than 650 and less than 750 the following will determine if the player loses
    else if (xPositionLevel2 > 650 && xPositionLevel2 < 750) {
      //Check to see if the avatar graces the y coordinates below and performs an action if it does
      if (yPositionLevel2 <= 250 || yPositionLevel2 >= 600) {
        loseMsg(); //Calling loseMsg method to display a message and scoreboard
      }
    }
    //If the avatar's x coordinate is greater than 780 and less than 1050 the following will determine if the player loses
    else if (xPositionLevel2 > 780 && xPositionLevel2 < 1050) {
      //Check to see if the avatar graces the y coordinates below and performs an action if it does
      if (yPositionLevel2 >= 220) {
        loseMsg(); //Calling loseMsg method to display a message and scoreboard
      }
    } else if (yPositionLevel2 <= 160) {
      loseMsg(); //Calling loseMsg method to display a message and scoreboard
    }
    //If the user completes the tutorial (tracked the avatar's xPosition being greater or equal to the width)
    else if (xPositionLevel2 >= width) {
      completion(); //Calling completionMsg method to create and display the completion window
    }
  }//avatarCheckLevel2 Method
  
  // This ActionListener method will perform actions when an event occurs on certain components
  public void actionPerformed(ActionEvent e) {
    
    //If the user would like to play the game again by clicking btnPlayAgain the following will occur
    if (e.getSource() == btnPlayAgain) {
      frameLose.dispose(); //Closing the frame  incase it was pressed from there
      frameDone.dispose(); //Closing the frame  incase it was pressed from there
      new Tutorial(); //Re-calling the class to restart the tutorial
    }
    //If the user would like to exit the escape menu (comes up when pressing Esc) by pressing the back button the following will occur
    else if (e.getSource() == btnBackToGame) {
      frameEsc.dispose(); //Closing the frame  incase it was pressed from there
      setVisible(true); //Making the game window visible
    }
    //If the user would like to play the real game after successfully completing the tutorial the following will occur
    else if (e.getSource() == btnRealGame) {
      frameDone.dispose(); //Closing the completion frame
      new RealGame(); //Calling the RealGame class to run and play the actual game
    }
  }// actionPerformed Method
  
  // This method is invoked when the user presses key
  public void keyPressed(KeyEvent e) {
    
    repaint();
    // If the user presses the W key or UP arrow key the following occurs
    if (e.getKeyCode() == KeyEvent.VK_W || e.getKeyCode() == KeyEvent.VK_UP) {
      
      // Checking to see that the avatar is not going to collide with the edge of the
      // window
      if (yPosition > 50 && xPosition <= width) {
        
        yPosition = yPosition - 50;
        
      } else if (yPositionLevel2 >= 160) {
        yPositionLevel2 = yPositionLevel2 - 50;
      }
    }
    // If the user presses the S key or DOWN arrow key the following occurs
    else if (e.getKeyCode() == KeyEvent.VK_S || e.getKeyCode() == KeyEvent.VK_DOWN) {
      
      // Checking to see that the avatar is not going to collide with the edge of the window
      if (yPosition < height - 150 && xPosition <= width) {
        
        yPosition = yPosition + 50; // Adding 50 to its y coordinate if it meets the check requirement
        
      } else if (yPositionLevel2 < height - 150) {
        
        yPositionLevel2 = yPositionLevel2 + 50;
        
      }
    }
    // If the user presses the D key or the RIGHT arrow key the following will occur
    else if (e.getKeyCode() == KeyEvent.VK_D || e.getKeyCode() == KeyEvent.VK_RIGHT) {
      
      if (xPosition <= width) {
        xPosition = xPosition + 50;
      } else {
        xPositionLevel2 = xPositionLevel2 + 50;
      }
    }
    // If the user presses the A key or the LEFT arrow key the following will occur
    else if (e.getKeyCode() == KeyEvent.VK_A || e.getKeyCode() == KeyEvent.VK_LEFT) {
      
      // Checking to see that the avatar is not going to collide with the edge of the
      // window
      if (xPosition > 50 && xPosition <= width) {
        xPosition = xPosition - 50; // Subtracting 50 from its x coordinate if it meets the check requirement
      } else if (xPositionLevel2 > 50) {
        xPositionLevel2 = xPositionLevel2 - 50;
      }
    }
    //If the user presses the Esc key the following will occur
    else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
      setVisible(false); //Making the graphics window not visible 
      escapeMsg(); //Calling the escapeMsg method to run and display the escape window
    }
  }// keyPressed Method
  
  public void keyReleased(KeyEvent e) {
  }// keyReleased Method
  
  public void keyTyped(KeyEvent e) {
  }// keyTyped Method
  
  public void mouseClicked(MouseEvent e) {
    
  }//mouseClicked Method
  
  public void mousePressed(MouseEvent e) {
    
  }//mousePressed Method
  
  public void mouseReleased(MouseEvent e) {
    
  }//mouseReleased Method
  
  public void mouseEntered(MouseEvent e) {
    
    if (e.getSource() == btnPlayAgain) {
      btnPlayAgain.setToolTipText("Click to Play Again!");
    } else if (e.getSource() == btnRealGame) {
      btnRealGame.setToolTipText("Think your ready for the real deal?");
    } else if (e.getSource() == btnBackToGame) {
      btnBackToGame.setToolTipText("Click to return to the game");
    } 
    
  }//mouseEntered Method
  
  public void mouseExited(MouseEvent e) {
    
  }//mouseExited Method
  
  
  public static void main(String[] args) {
    
    new Tutorial(); // Calling the class
    
  }// main Method
  
}// Game Class
