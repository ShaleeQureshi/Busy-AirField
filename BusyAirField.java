import javax.swing.JFrame; //For the window
import javax.swing.JButton; //For the buttons
import javax.swing.JLabel; //For the labels
import javax.swing.JTextField; //Areas the user can input information
import javax.swing.JOptionPane; //For the use of dialog boxes
import java.awt.Color; //To change color of certain components such as the frame
import java.awt.Font; //To change the font
import java.awt.event.ActionListener; //To listen to the buttons
import java.awt.event.ActionEvent; //To perform certain events if a button is pressed
import java.awt.event.MouseListener; //To listen to the mouse of the user
import java.awt.event.MouseEvent; //To perform certain events based off the users mouse movements
import javax.swing.border.LineBorder; //To create line borders 
import javax.swing.plaf.basic.BasicArrowButton; //To draw arrow buttons
import java.awt.Cursor; //To change the cursor
import javax.swing.JPasswordField; //To  make the password the user enters hidden
import javax.swing.ImageIcon; //To add visuals
import javax.swing.BorderFactory; //To create a border
import java.io.FileReader; //To create a file stream to a file
import java.io.BufferedReader; //To read from a file line by line
import java.io.IOException; //Incase an error occurs with the file
import java.io.FileNotFoundException; //Incase the file cannot be found in the folder
import java.io.PrintWriter; //To open a file stream so we can write to a file
import java.io.FileWriter; //To write to a file
import java.io.File; //To open a directory
import javax.sound.sampled.AudioSystem; //Entry point to the stream
import javax.sound.sampled.Clip; //Way to open the file
import javax.swing.JSlider; //To allow the user to easily alter numerical values
import javax.swing.event.ChangeListener; //To listen to changes (JSlider)
import javax.swing.event.ChangeEvent; //To perform certain events if a change occurs
import javax.sound.sampled.FloatControl; //To get control over a  range of floating values
import javax.sound.sampled.LineUnavailableException; //Incase the line being played is not approachable
import javax.sound.sampled.UnsupportedAudioFileException; //Incase the type of audio-file is not supported
import javax.swing.JTextArea; //Method of getting user input/displaying information
import java.awt.Desktop; //To get access the user's desktop
import java.net.URI; //Allows us to create instances that can be nams, locations, or both
import java.net.URISyntaxException; //Incase an error occurs with the URI 
/**
 * Date: December 26, 2019
 * Author: Shalee Qureshi
 * Description: This is the class that the entire game MUST be run through in order for it to run properly. This is because this class is responsbile for creating-
 * the GUIs and is how the user can access the game along with viewing luxuries games commonly have such as music, volume controls, game controls, etc. This class-
 * will then run one of the two other classes based on user decision and can run all 3 if the user selects -> tutorial -> real game or two if the user selects -
 * to play the real game right away.
 * 
 * Method List:
 * 
 * frameSetter (JFrame a, int x, int y) = Alters properties of a JFrame such as background, size, location, etc
 * introFrame () = Creates the first window
 * helpFrame () = Creates a help window
 * repeatedComponents (JFrame a) = This method creates/adds JComponents that are repeated in other methods (just used to avoid repetition)
 * loginFrame () = Creates the login window
 * createAccountFrame() = Creates the create an account window
 * mainFrame () = Creates the main window that has access to settings and the actual game
 * settingsFrame () = Creates the settings window which is only accessible through the main window
 * controlsFrame () = Creates the controls window which  is only accessible through the settings window
 * optionFrame() = Creates a window asking the user if they want to play the tutorial or not after the play button is pressed from the mainFrame
 * errorEnd () = Displays an error message terminates the program
 * arraySize (String fileName) = Initializes the size of arrays without the use of ArrayList
 * readFile(String readType, String array[], String array2[], int array3[], String fileName) = Reads from a file that contains two string arrays or 1 string and 1 int array
 * checkLogin (String array[], String array2[], String login, String pass) = Checks arrays to see if user  input matches their data and returns an int
 * outputData (String outputType, int output1, String output2, String file) = Writes max 2 strings (min 1 string) to a specified file
 * clear () = Clears all textfields and passwordfields
 * musicPlayer (File filePath, float userInput, String loopYesNo, long timeStamp) = Plays audio files (preferably .wav)
 * boolean charCounter (String text, int size) = Counts number of characters in a string and compares it with the specified size
 * playVideo () = Opens the user's default browser and goes to a specified URL
 * actionPerformed (ActionEvent e) = Invoked when an action occurs towards a JComponent - used for all action
 * mouseClicked (MouseEvent e) = Invoked when the mouse button has been clicked - not used
 * mouseExited(MouseEvent e) = Invoked when the mouse exits a component - not used
 * mousePressed(MouseEvent e) = Invoked when a mouse button has been pressed on a component - not used
 * mouseEntered(MouseEvent e) = Invoked when a mouse button hovers on a component (used to display a small blurb when the user hovers a button)
 * mouseReleased(MouseEvent e) = Invoked when a mouse button has been released on a component - not used
 * stateChanged (ChangeEvent e) = Invoked when a component has changed state - used for adjusting music volume
 */
public class BusyAirField extends JFrame implements ActionListener, MouseListener, ChangeListener {
  
  // Creating frameName found in all JFrames
  static String frameName = "Busy AirSpace"; //Static so it can be used in other classes
  static String loginName; //Static so it can be used in other classes
  static int elements = 0; // Declaring an int to get the number of elements each array holds (static so it can be used in multiple classes)
  
  // Declaring variables used throughout the program
  // The values for these two variables is received from userInfo.txt
  // These variables are static as they are used in multiple classes
  static String playerID[];
  static String playerPassword[]; 
  
  File mainFrameMusic = new File("mainMusic2.wav"); // Audio file that is played when the program is turned on
  float programVolume = 0.05f; // Declaring the programs volume here as it will be used in other methods(this is equal to 5% volume)
  Clip clip; // Declaring clip variable here as it will be used in other methods
  
  // Declaring/setting JComponents
  
  // Frames
  JFrame frameIntro = new JFrame(frameName); // Creating the first frame and adding the frameName
  JFrame frameHelp = new JFrame(frameName); // Creating a help frame and adding the frame name to it
  JFrame frameLogin = new JFrame(frameName); // Creating the login frame and adding the frameName
  JFrame frameCreateAccount = new JFrame(frameName); // Creating the create an account frame and adding the frame name
  static JFrame frameMainGUI = new JFrame(frameName); // Creating the main frame and adding the frame name to it (static as it is used in other classes)
  JFrame frameSettings = new JFrame(frameName); // Creating settings GUI and adding the frame name to it
  JFrame frameControls = new JFrame(frameName); // Creating control GUI and adding the frame name to it
  JFrame frameTutorial = new JFrame(frameName); // Creating a tutorial GUI and adding the frame name to it
  
  // JComponents found in frameIntro
  JLabel lblWelcome = new JLabel("<html><u>Welcome to Busy AirSpace!"); //html part allows the text to be underlined
  JButton btnLogin = new JButton("Login");
  JButton btnCreateAccount = new JButton("Create an Account");
  static JButton btnExit = new JButton("Exit"); // Making this static so it can be used in other classes
  JLabel lblIntroGif = new JLabel(new ImageIcon("intro.gif")); // Adding a gif
  JButton btnHelp = new JButton("Help");
  
  // JComponents found in frameHelp
  JButton btnProblem = new JButton("Report a problem");
  JButton btnBackToIntro = new JButton("Back");
  
  // JComponents found in frameLogin
  JTextField txtLogin = new JTextField();
  JPasswordField txtPassLogin = new JPasswordField(); // PasswordField to make it so the text cannot be seen from the user
  JButton btnContinueLogin = new JButton("Continue");
  BasicArrowButton btnBack = new BasicArrowButton(BasicArrowButton.WEST);
  JLabel lblLogin = new JLabel("<html><u>Login Below"); // html text underlines the text in the label
  JLabel lblLoginPlaceTxt = new JLabel("Enter UserID"); // PlaceHolder text and is removed upon key entry
  JLabel lblPassPlaceTxt = new JLabel("Enter Password"); // PlaceHolder text and is removed upon key entry
  
  // JComponents founds in frameCreateAccount
  JPasswordField txtPassConfirm = new JPasswordField(); // PasswordField to make it so the text cannot be seen from the user
  JPasswordField txtPassCreate = new JPasswordField(); //Second PasswordField used so the user can confirm their password
  JTextField txtUserID = new JTextField(); //This is where the user enters their userID
  JLabel lblPassConfirmPlacetxt = new JLabel("Confirm Password");
  JLabel lblCreate = new JLabel("<html><u>Create your new Account"); // html text underlines the text in the label
  JButton btnContinueCreate = new JButton("Continue");
  JLabel lblLimit = new JLabel("<html><u>10 characters max per field"); // Note for user
  JLabel lblError = new JLabel(); //Text will be added when the user enters invalid credentials
  
  // JComponents found in frameMainGUI 
  // They are all static so they can be used in static methods in this class (private static = only static in this class and the static makes them -
  // usable in other classes) 
  private static JLabel lblMain = new JLabel("<html><u>Busy AirSpace"); // html text underlines the text in the label
  private static JButton btnSettings = new JButton("Settings"); //Making it static so it can be used in other classes
  private static JButton btnPlay = new JButton("Play!");
  private static JButton btnHow = new JButton("How to Play");
  private static JLabel lblMainGif = new JLabel(new ImageIcon("main.gif")); // Adding a gif
  private static JLabel lblMain2Gif = new JLabel(new ImageIcon("main2.gif")); // Adding a gif
  
  // JComponents found in frameSettings
  JButton btnControls = new JButton("Controls");
  // Adding a JSlider and setting its max/min and initial point
  // (int) forces it to be an integer but the time is first multiplied by 100 then converted to an int
  JSlider volume = new JSlider(JSlider.VERTICAL, 0, 100, (int) (programVolume * 100));
  JLabel lblVol = new JLabel("Volume = 5%"); //This label shows the initial volume of the audio 
  JButton btnBackToMain = new JButton("Back");
  
  // JComponents found in frameControls
  JLabel lblWBorder = new JLabel();
  JLabel lblABorder = new JLabel();
  JLabel lblSBorder = new JLabel();
  JLabel lblDBorder = new JLabel();
  JLabel lblW = new JLabel("W");
  JLabel lblA = new JLabel("A");
  JLabel lblS = new JLabel("S");
  JLabel lblD = new JLabel("D");
  BasicArrowButton btnWUpArrow = new BasicArrowButton(BasicArrowButton.NORTH);
  BasicArrowButton btnALeftArrow = new BasicArrowButton(BasicArrowButton.WEST);
  BasicArrowButton btnDRightArrow = new BasicArrowButton(BasicArrowButton.EAST);
  BasicArrowButton btnSDownArrow = new BasicArrowButton(BasicArrowButton.SOUTH);
  JLabel lblOr = new JLabel("Or");
  JButton btnBackToSettings = new JButton("Back");
  
  //JComponents found in frameTutorial
  JLabel lblTutorial = new JLabel("<html><u>Would you like to play the tutorial?");
  static JButton btnTutorial = new JButton("Yes"); //Making this variable static so it can be used in other classes 
  JButton btnRealGame = new JButton("No");
  
  // Fonts
  static Font fontLabel = new Font("Arial", Font.BOLD, 17); // Making this font static so it can be used in another class
  static Font fontBtn = new Font("Arial", Font.BOLD, 14); // Making this font static so it can be used in another class
  Font fontPlaceHolder = new Font("Arial", Font.ITALIC, 10);
  Font fontControlText = new Font("Arial", Font.BOLD, 30);
  
  // Creating a cursor for when the user hovers components with their mouse
  static Cursor handCursor = new Cursor(Cursor.HAND_CURSOR); // Making this static so it can be used in other classes
  
  // Constructor (super not needed as we set the frame text when declaring it)
  public BusyAirField() {
    
    //Adding the listeners here as they are found in static methods and the listener cannot be added there
    // Adding ActionListener to the buttons   
    btnSettings.addActionListener(this);
    btnPlay.addActionListener(this);
    btnHow.addActionListener(this);
    // Adding MouseListener to the buttons
    btnSettings.addMouseListener(this);
    btnPlay.addMouseListener(this);
    btnHow.addMouseListener(this);
    
    // Calling the musicPlayer method to play background music
    // Putting the file, volume level, yes to make it loop continuously, and 0 to make sure it starts at the beginning of the audiofile
    musicPlayer(mainFrameMusic, programVolume, 0);
    
    introFrame(); // Calling the first GUI method to run
    
  }// constructor
  
  // This method alters things for a JFrame such as background color, size, layout, etc
  public static void frameSetter(JFrame a, int x, int y) {
    
    a.getContentPane().setBackground(new Color(50, 197, 216)); // Changing the color of the Frame to a custom color based off of RGB
    a.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Setting its close operation
    a.setSize(x, y); // Setting window size
    a.setLayout(null); // Setting layout to null so we can decide the JComponents location
    a.setLocationRelativeTo(null); // Setting the windows location to the center of the display
    a.setResizable(false); // Making it so the user cannot resize the JFrame windows
    
  }// frameSetter
  
  // This method creates the first GUI the user will encounter
  public void introFrame() {
    
    frameSetter(frameIntro, 400, 480); // Calling frameSetter method to set the size and do other things with the frame
    
    // Adding the fonts to the JComponents
    lblWelcome.setFont(fontLabel);
    btnLogin.setFont(fontBtn);
    btnCreateAccount.setFont(fontBtn);
    btnExit.setFont(fontBtn);
    btnHelp.setFont(fontBtn);
    
    // Adding the cursor to the buttons
    btnLogin.setCursor(handCursor);
    btnCreateAccount.setCursor(handCursor);
    btnExit.setCursor(handCursor);
    btnHelp.setCursor(handCursor);
    
    // Changing the background for the buttons
    btnLogin.setBackground(Color.GRAY);
    btnCreateAccount.setBackground(Color.GRAY);
    btnExit.setBackground(Color.GRAY);
    btnHelp.setBackground(Color.GRAY);
    
    // Location set with (x, y, width, height)
    
    // Adding/Setting location for label
    lblWelcome.setBounds(30, 60, 300, 50);
    frameIntro.add(lblWelcome);
    
    // Adding/Setting the GIF
    lblIntroGif.setBounds(250, -40, 150, 200);
    frameIntro.add(lblIntroGif);
    
    // Adding/Setting location for buttons
    btnLogin.setBounds(90, 150, 200, 40);
    frameIntro.add(btnLogin);
    
    btnCreateAccount.setBounds(90, 230, 200, 40);
    frameIntro.add(btnCreateAccount);
    
    btnExit.setBounds(20, 390, 100, 40);
    frameIntro.add(btnExit);
    
    btnHelp.setBounds(280, 390, 100, 40);
    frameIntro.add(btnHelp);
    
    // Adding ActionListener to the buttons
    btnLogin.addActionListener(this);
    btnExit.addActionListener(this);
    btnCreateAccount.addActionListener(this);
    btnHelp.addActionListener(this);
    
    // Adding MouseListener to the buttons
    btnLogin.addMouseListener(this);
    btnExit.addMouseListener(this);
    btnCreateAccount.addMouseListener(this);
    btnHelp.addMouseListener(this);
    
    frameIntro.setVisible(true);
  }// introFrame method
  
  public void helpFrame() {
    
    frameSetter(frameHelp, 300, 250); // Calling frameSetter method to set the size and do other things with the frame
    
    // Setting font to buttons
    btnProblem.setFont(fontBtn);
    btnBackToIntro.setFont(fontBtn);
    // Changing the background of the buttons
    btnProblem.setBackground(Color.GRAY);
    btnBackToIntro.setBackground(Color.GRAY);
    
    // Adding/Setting JComponents (x, y, width, height)
    
    btnProblem.setBounds(50, 50, 200, 50);
    frameHelp.add(btnProblem);
    btnBackToIntro.setBounds(50, 120, 200, 50);
    frameHelp.add(btnBackToIntro);
    
    // Adding listeners
    btnProblem.addActionListener(this);
    btnProblem.addMouseListener(this);
    
    btnBackToIntro.addActionListener(this);
    btnBackToIntro.addMouseListener(this);
    
    // Setting the cursor to the buttons
    btnProblem.setCursor(handCursor);
    btnBackToIntro.setCursor(handCursor);
    
    frameHelp.setVisible(true);
    
  }// helpFrame method
  
  // This method simply sets/adds components to the JComponents that are used repeatedly throughout the program (used to avoid repetition)
  public void repeatedComponents(JFrame a) {
    
    // Adding handCursor to btnBack
    btnBack.setCursor(handCursor);
    
    // Adding the fonts to the other JComponents
    lblLoginPlaceTxt.setFont(fontPlaceHolder);
    lblPassPlaceTxt.setFont(fontPlaceHolder);
    //Changing the color of lblError's text
    lblError.setForeground(Color.RED);
    
    // Setting the location and adding the JComponents (x, y, width, height)
    
    // Adding/setting location for labels
    lblLoginPlaceTxt.setBounds(55, 80, 190, 40);
    a.add(lblLoginPlaceTxt);
    lblPassPlaceTxt.setBounds(55, 140, 190, 40);
    a.add(lblPassPlaceTxt);
    lblError.setBounds(60, 10, 150, 40);
    a.add(lblError);
    lblLimit.setBounds(47, 70, 200, 20);
    a.add(lblLimit);
    
    // Adding buttons
    btnExit.setBounds(5, 300, 130, 50);
    a.add(btnExit);
    btnBack.setBounds(0, 0, 50, 50);
    a.add(btnBack);
    
    // Adding listeners to buttons (dont need to add for btnExit as it was added in previous method)
    btnBack.addActionListener(this);
    btnBack.addMouseListener(this);
    
  }// repeatedComponents method
  
  // This method creates the login frame GUI
  public void loginFrame() {
    
    frameSetter(frameLogin, 300, 400); // Calling frameSetter method to set the size and do other things with the frame
    
    // Altering btnContinueLogin
    btnContinueLogin.setBackground(Color.GRAY);
    btnContinueLogin.setFont(fontBtn);
    btnContinueLogin.setCursor(handCursor);
    
    lblLogin.setFont(fontLabel);
    
    
    // Setting/adding the JComponents (x, y, width, height)
    
    lblLogin.setBounds(90, 30, 200, 40);
    frameLogin.add(lblLogin);
    btnContinueLogin.setBounds(150, 300, 130, 50);
    frameLogin.add(btnContinueLogin);
    
    txtLogin.setBounds(50, 110, 190, 40);
    frameLogin.add(txtLogin);
    
    txtPassLogin.setBounds(50, 170, 190, 40);
    frameLogin.add(txtPassLogin);
    
    // Adding listeners to button
    btnContinueLogin.addActionListener(this);
    btnContinueLogin.addMouseListener(this);
    
    repeatedComponents(frameLogin); // Calling repeatedComponents method to add/set the other JComponents
    
    frameLogin.setVisible(true); //Making this frame visible 
    
  }// loginFrame method
  
  // This method creates the create the create an account GUI which the user will encounter if they need to make an account
  public void createAccountFrame() {
    
    frameSetter(frameCreateAccount, 300, 400); // Calling frameSetter method to alter the JFrame (background, etc)
    
    // Adding the fonts to the JComponents
    lblPassConfirmPlacetxt.setFont(fontPlaceHolder);
    lblCreate.setFont(fontLabel);
    
    // Altering btnContinueCreate
    btnContinueCreate.setBackground(Color.GRAY);
    btnContinueCreate.setFont(fontBtn);
    btnContinueCreate.setCursor(handCursor);
    
    // Adding labels
    lblPassConfirmPlacetxt.setBounds(55, 200, 190, 40);
    frameCreateAccount.add(lblPassConfirmPlacetxt);
    
    lblCreate.setBounds(47, 40, 300, 40);
    frameCreateAccount.add(lblCreate);
    
    
    // Adding button
    btnContinueCreate.setBounds(150, 300, 130, 50);
    frameCreateAccount.add(btnContinueCreate);
    repeatedComponents(frameCreateAccount); // Calling the repeatedComponents method to add/set components used in other methods
    
    // Adding textfields/password fields
    txtPassConfirm.setBounds(50, 230, 190, 40);
    frameCreateAccount.add(txtPassConfirm);
    txtUserID.setBounds(50, 110, 190, 40);
    frameCreateAccount.add(txtUserID);
    txtPassCreate.setBounds(50, 170, 190, 40);
    frameCreateAccount.add(txtPassCreate);
    
    // Adding listeners to btnContinueCreate
    btnContinueCreate.addActionListener(this);
    btnContinueCreate.addMouseListener(this);
    
    
  }// createAccountFrame method
  
  // This method creates the main GUI
  public static void mainFrame() {
    
    
    frameSetter(frameMainGUI, 500, 400); // Calling frameSetter method to alter the JFrame (background, etc)
    
    // Adding the fonts to the JComponents
    lblMain.setFont(new Font("Arial", Font.BOLD, 60)); // Creating a new font for the title
    btnSettings.setFont(fontBtn);
    btnPlay.setFont(fontBtn);
    btnHow.setFont(fontBtn);
    
    // Changing the background color the buttons
    btnSettings.setBackground(Color.GRAY);
    btnPlay.setBackground(Color.GRAY);
    btnHow.setBackground(Color.GRAY);
    
    // Setting the location/adding the JComponents (x ,y ,width, height)
    // Adding the label
    lblMain.setBounds(100, -20, 400, 200);
    frameMainGUI.add(lblMain);
    // Adding the gifs
    lblMainGif.setBounds(-155, 130, 550, 350);
    frameMainGUI.add(lblMainGif);
    lblMain2Gif.setBounds(200, 210, 300, 150);
    frameMainGUI.add(lblMain2Gif);
    // Adding the buttons
    btnSettings.setBounds(0, 330, 100, 45);
    frameMainGUI.add(btnSettings);
    btnPlay.setBounds(90, 180, 130, 40);
    frameMainGUI.add(btnPlay);
    btnHow.setBounds(250, 180, 130, 40);
    frameMainGUI.add(btnHow);
    
  }// mainFrame Method
  
  // This method creates the settings GUI when the Settings button is clicked in the main GUI
  public void settingsFrame() {
    
    frameSetter(frameSettings, 350, 400); // Calling frameSetter method to alter the JFrame (background, etc)
    // Adding fonts to JComponents
    btnControls.setFont(fontBtn);
    btnBackToMain.setFont(fontBtn);
    lblVol.setFont(fontLabel);
    // Changing background color of buttons
    btnControls.setBackground(Color.GRAY);
    btnBackToMain.setBackground(Color.GRAY);
    
    // Adding/Setting buttons (x, y, width, height)
    btnControls.setBounds(80, 50, 120, 50);
    frameSettings.add(btnControls);
    btnBackToMain.setBounds(0, 315, 100, 50);
    frameSettings.add(btnBackToMain);
    btnExit.setBounds(250, 315, 100, 50);
    frameSettings.add(btnExit);
    // Adding/Setting JSlider
    volume.setBounds(250, 50, 50, 250);
    frameSettings.add(volume);
    // Adding/Setting label
    lblVol.setBounds(110, 270, 200, 40);
    frameSettings.add(lblVol);
    // Adding labels for JSlider tick marks
    volume.setMajorTickSpacing(10);
    volume.setMinorTickSpacing(1);
    volume.setPaintTicks(true);
    volume.setPaintLabels(true);
    // Adding listeners to buttons
    btnControls.addActionListener(this);
    btnControls.addMouseListener(this);

    btnBackToMain.addActionListener(this);
    btnBackToMain.addMouseListener(this);
    // Adding listener to JSlider
    volume.addChangeListener(this);
    // Changing cursor of the JComponents
    btnControls.setCursor(handCursor);
    volume.setCursor(handCursor);
    
    frameSettings.setVisible(true);
    
  }// settingsFrame Method
  
  // This method creates the controls GUI when the Controls button is clicked in the settings GUI
  public void controlsFrame() {
    
    frameSetter(frameControls, 300, 450); // Calling frameSetter method to alter the JFrame (background, etc)
    // Adding a border around labels
    lblWBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); //Making a border with a thickness of 5
    lblABorder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); //Making a border with a thickness of 5
    lblSBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); //Making a border with a thickness of 5
    lblDBorder.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5)); //Making a border with a thickness of 5
    lblOr.setBorder(new LineBorder(Color.BLACK));
    // Adding fonts to JComponents
    lblW.setFont(fontControlText);
    lblA.setFont(fontControlText);
    lblS.setFont(fontControlText);
    lblD.setFont(fontControlText);
    lblOr.setFont(fontLabel);
    btnBackToSettings.setFont(fontBtn);
    // Changing background of button
    btnBackToSettings.setBackground(Color.GRAY);
    
    // Setting/Adding JComponents, done by (x, y, width, height)
    
    // Adding the borders
    lblWBorder.setBounds(120, 50, 60, 60);
    frameControls.add(lblWBorder);
    lblABorder.setBounds(60, 110, 60, 60);
    frameControls.add(lblABorder);
    lblSBorder.setBounds(120, 110, 60, 60);
    frameControls.add(lblSBorder);
    lblDBorder.setBounds(180, 110, 60, 60);
    frameControls.add(lblDBorder);
    
    // Adding the labels
    lblW.setBounds(135, 55, 60, 60); // Setting its location on the frame
    frameControls.add(lblW); // Adding it to this JFrame
    lblA.setBounds(77, 112, 60, 60);
    frameControls.add(lblA);
    lblS.setBounds(137, 112, 60, 60);
    frameControls.add(lblS);
    lblD.setBounds(197, 110, 60, 60);
    frameControls.add(lblD);
    
    // Adding buttons
    btnWUpArrow.setBounds(120, 210, 60, 60);
    frameControls.add(btnWUpArrow);
    btnALeftArrow.setBounds(60, 270, 60, 60);
    frameControls.add(btnALeftArrow);
    btnSDownArrow.setBounds(120, 270, 60, 60);
    frameControls.add(btnSDownArrow);
    btnDRightArrow.setBounds(180, 270, 60, 60);
    frameControls.add(btnDRightArrow);
    btnBackToSettings.setBounds(0, 365, 100, 50);
    frameControls.add(btnBackToSettings); // Don't need to set bounds as it was done in previous method
    
    // Adding text labels
    lblOr.setBounds(135, 180, 30, 20);
    frameControls.add(lblOr);
    
    // The following makes it so the buttons cannot be clicked and are just there for display
    btnWUpArrow.setEnabled(false);
    btnALeftArrow.setEnabled(false);
    btnSDownArrow.setEnabled(false);
    btnDRightArrow.setEnabled(false);
    // Adding listener to btnBackToSettings
    btnBackToSettings.addActionListener(this);
    btnBackToSettings.addMouseListener(this);
    // Changing cursor of btnBackToSetting
    btnBackToSettings.setCursor(handCursor);
    
    frameControls.setVisible(true);
    
  }// controlsFrame Method
  
  //This method open a window that asks the user if they would like to play the tutorial first or not and open a new class based on user decision
  public void optionFrame() {
    
    frameSetter(frameTutorial, 350, 200); //Calling frameSetter method to create/alter JFrame properties
    
    //Adding fonts to JComponents
    lblTutorial.setFont(fontLabel);
    btnTutorial.setFont(fontBtn);
    btnRealGame.setFont(fontBtn);
    
    //Changing background color of buttons
    btnTutorial.setBackground(Color.GRAY);
    btnRealGame.setBackground(Color.GRAY);
    
    //Adding/Setting JComponents (x, y, width, height)
    lblTutorial.setBounds(30, 50, 300, 50);
    frameTutorial.add(lblTutorial);
    btnRealGame.setBounds(175, 100, 100, 50);
    frameTutorial.add(btnRealGame);
    btnTutorial.setBounds(60, 100, 100, 50);
    frameTutorial.add(btnTutorial);
    
    //Adding listeners to buttons
    btnTutorial.addActionListener(this);
    btnTutorial.addMouseListener(this);
    btnRealGame.addActionListener(this);
    btnRealGame.addMouseListener(this);
    
    //Adding cursor to buttons
    btnRealGame.setCursor(handCursor);
    btnTutorial.setCursor(handCursor);
    
    frameTutorial.setVisible(true); //Making this frame visible 
    
  }//optionFrame Method
  
  // This method displays an error message and tells the user that the program will terminate
  public static void errorEnd() {
    
    JOptionPane.showMessageDialog(null, "An unexpected error has occured, program will now terminate"); // Displaying a message
    System.exit(0); // Terminating program
    
  }// errorRedo Method
  
  // This method initializes the size of the array by assigning its elements without the use of arrayLists
  public static int arraySize(String fileName) {
    
    // Try catch is used incase an error occurs with the file
    try {
      FileReader fileR = new FileReader(fileName); // Creating a file stream
      BufferedReader readFile = new BufferedReader(fileR); // Reading from the file stream
      
      String line = ""; // Declaring/Initializing an empty string to read from the file
      int counter = 0; // Declaring/Initializing the counter which will count the number of lines in
      // the file
      int total = 0; // Declaring/Initializing the total which represents the number of lines each array has information on
      // The following loop will continue to read from the file as long as the lines have information on it
      while ((line = readFile.readLine()) != null) {
        counter++; // Adding 1 to counter each time
      }
      total = counter / 2; // Dividing the counter by 2 as there are 2 arrays of information in the file
      // Initializing the arrays with their elements
      readFile.close(); // Closing the bufferedReader
      return total;
      
      // If the file cannot be found then the following occurs
    } catch (FileNotFoundException error) {
      errorEnd(); // Calling errorEnd method to display a message and terminate program
      // If an error occurs when reading from the file the following will occur
    } catch (IOException error) {
      errorEnd(); // Calling errorEnd method to display a message and terminate program
    }
    return -1; // This return value will never be given because if any problem occurs with the return set earlier the program will end
    
  }// arraySize method
  
  // This method reads from a file that has 2 string arrays and it reads from any file that is in the folder
  public static void readFile(String readType, String array[], String array2[], int array3[], String fileName) {
    
    try {
      FileReader fileRNew = new FileReader(fileName); // Creating a new file stream
      BufferedReader readFileNew = new BufferedReader(fileRNew); // Reading from the file stream with a new reader
      
      if (readType.equals("2String")) {
        // The following loop reads from the file and stores the information to the arrays
        for (int i = 0; i < array.length; i++) {
          array[i] = readFileNew.readLine(); // Storing the string elements to the array
          array2[i] = readFileNew.readLine(); // Storing the string elements to the array
        }
        readFileNew.close(); // Closing the bufferedReader
      }
      else {
        for (int i = 0; i < array.length; i++) {
          array[i] = readFileNew.readLine(); // Storing the string elements to the array
          array3[i] = Integer.parseInt(readFileNew.readLine()); // Storing the integer elements  to the array
        }
        readFileNew.close(); // Closing the bufferedReader
      }
      // If an error occurs when reading from the file the following will occur
    } catch (IOException error) {
      errorEnd(); // Calling errorEnd method to display a message and terminate program
    }
  }// readFile method
  
  // This method runs a check through our database to see if the login credentials match our database and returns -
  // an int as there are 3 possible outcomes
  public static int checkLogin(String array[], String array2[], String login, String passW) {
    
    // Loop to read arrays
    for (int i = 0; i < array.length; i++) {
      // Checking to see if the userID and password match the ones in our database
      if (array[i].equals(login) && array2[i].equals(passW)) {
        return 1; // Returning 1 if the loginID and password match the ones in our database
      }
      // Checking to see if the loginID matches the username in our database
      else if (array[i].equals(login)) {
        return 2; // Returning 2 if the loginID matches the one in our database
      }
    }
    return 3; // Returning 3 if none of the information matches the info in our database
  }// checkLogin method
  
  // This method outputs strings to a specified filed
  public static void outputData(String outputType, int output1, String output2, String file) {
    
    // Try catch used incase an error occurs with the file
    try {
      FileWriter fileW = new FileWriter(file, true); // Opening a file writing stream (true is used to prevent the
      // overwriting of previous data
      PrintWriter output = new PrintWriter(fileW); // Method to write to the stream
      
      if (outputType.equals("String")) {
        output.println(output2);
      }
      else {
        output.println(output1); 
      }
      
      output.close(); // Closing printwriter
      // If an error occurs with the file the following will occur
    } catch (IOException error) {
      errorEnd(); // Calling errorEnd method to display a message and terminate program
    }
    
  }// outputData method
  
  // This method clears the login/password fields
  public void clear() {
    
    txtLogin.setText(""); // Clearing the text from the login textfield
    txtPassLogin.setText(""); // Clearing the text from the password textfield
    txtPassConfirm.setText(""); // Clearing the text from the password confirmation textfield
    txtUserID.setText(""); //Clearing the text from the userID textfield in create account frame
    txtPassCreate.setText(""); //Clearing the password field from the password field in create account
    
  }// clear method
  
  // This method loops an audiofile that is a .wav and plays it a specified volume level from 0 - 100
  public void musicPlayer(File filePath, float userInput, long timeStamp) {
    
    // Try catch used incase any problems occur with the file
    try {
      clip = AudioSystem.getClip();
      clip.open(AudioSystem.getAudioInputStream(filePath));
      clip.setMicrosecondPosition(timeStamp);
      
      FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN); // Getting control over the clip
      float vol = (float) (Math.log(userInput) / Math.log(10) * 20); // Determining the volume by taking user
      
      // input and converting it from decibels to the volume system we know of
      gainControl.setValue(vol); // Setting the new decibel (volume) to the clip
      clip.start(); // Playing the stream
      clip.loop(Clip.LOOP_CONTINUOUSLY); // Setting the clip to loop infinitely until the program terminates
      
      // Catch incase an error occurs
    } catch (LineUnavailableException error) // Incase the line cannot be opened or is missing
    {
      errorEnd(); // Calling errorEnd message to display a message and terminate the program
    } catch (IOException error) // Incase an error occurs with the file
    {
      errorEnd(); // Calling errorEnd message to display a message and terminate the program
    } catch (UnsupportedAudioFileException error) // Incase the type of file is not supported
    {
      errorEnd(); // Calling errorEnd message to display a message and terminate the program
    }
    
  }// musicPlayer method
  
  // This method counts the number of characters and compares it with specified size
  public static boolean charCounter(String text, int size) {
    
    // Declaring int below and initializing it to 0
    int characters = 0;
    
    // For loop to run as long as the length of the string
    for (int i = 0; i < text.length(); i++) {
      
      characters++; // Adding 1 to characters each time
      
    }
    // If the number of characters in the string is greater than the specified size it returns false
    if (characters > size) {
      return false; // Return statement
    }
    // If the number of characters is not greater than the specified size it returns true
    return true; // Return statement
    
  }// charCounter method
  
  //This method plays a video from YouTube which tells the user how to play the game 
  public void playVideo (){
    
    try{
      Desktop openDesktop = Desktop.getDesktop();
      openDesktop.browse(new URI("https://youtu.be/tTibC6bNhUg?t=24"));
    } catch (IOException error) {
      errorEnd();
    } catch (URISyntaxException error2) {
      errorEnd();
    }
    
  }//playVideo Method
  
  // This ActionListener method will perform actions when an event occurs
  public void actionPerformed(ActionEvent e) {
    
    // The following lines of actual code is done so the information can be used throughout the method and this avoids repitition
    elements = arraySize("userInfo.txt"); // Calling the arraySize method to initialize the arrays
    // Initializing the arrays
    playerID = new String[elements];
    playerPassword = new String[elements];
    readFile("2String", playerID, playerPassword, RealGame.scores, "userInfo.txt"); // Calling the readFile method to read from the arrays
    boolean textSizeChecker; // This variable is used to make sure the user does not enter too many characters in fields
    
    // If the user decides to login to his/her account the following will occur
    if (e.getSource() == btnLogin) {
      frameIntro.dispose(); //Closing the intro frame 
      loginFrame(); //Calling loginFrame method to run
    }
    // If the user clicks the help button the following occurs
    else if (e.getSource() == btnHelp) {
      frameIntro.dispose(); //Closing the frame
      helpFrame(); //Calling helpFrame method to run
    }
    // If the user wishes to report an issue by clicking the report a problem button in help frame the following will occur
    else if (e.getSource() == btnProblem) {
      // Creating a JTextArea, putting text on it, and setting its dimensions
      JTextArea issue = new JTextArea("Please let us know what we can do better (limit to 100 characters) ", 10, 30);
      issue.setLineWrap(true); // Making sure that if the text exceeds the length it'll wrap back
      issue.setWrapStyleWord(true); // Making sure if a word cannot fit in a line it'll wrap back
      JOptionPane.showMessageDialog(null, issue); // Outputting the JTextArea
      // Calling the charCounter method to see if the user went over the char limit of 100(excluding the 67 I set as default text)
      textSizeChecker = charCounter(issue.getText(), 167); // Getting the user entered text and setting the limit of chars
      // If the user did go over the limit the following will occur
      if (textSizeChecker != true) {
        JOptionPane.showMessageDialog(null, "You have exceeded the 100 character limit!"); // Letting the user know the problem
        JOptionPane.showMessageDialog(null, issue); // Re-prompting the user with JTextArea
        textSizeChecker = charCounter(issue.getText(), 167); // Re-Checking by calling the method again
      }
      JOptionPane.showMessageDialog(null, "Thank you for help making our game better!"); // Displaying a thank you message after the JTextArea
      outputData("String", 0, issue.getText(), "problems.txt"); // Writing the information the user entered to the problems.txtFile
      
    }
    // If the user wishes to go back to the intro frame from the help frame the following will occur
    else if (e.getSource() == btnBackToIntro) {
      frameHelp.dispose(); //Closing the frame 
      frameIntro.setVisible(true);
    }
    // If the user presses the exit button the following will occur
    else if (e.getSource() == btnExit) {
      JOptionPane.showMessageDialog(null, "Thank you for playing Captain!"); // Displaying a thank you message
      System.exit(0); // Terminating program
    }
    // If the user clicks the back arrow button the following will occur (used in login and create account GUI so below has-
    // components from both GUIs)
    else if (e.getSource() == btnBack) {
      frameLogin.dispose(); //Closing the frame 
      frameCreateAccount.dispose(); //Closing the frame 
      clear(); // Calling the clear method to clear all fields incase they were used
      lblError.setText("");
      introFrame(); //Calling introFrame method
    }
    // If the user wants to create an account the following occurs
    else if (e.getSource() == btnCreateAccount) {
      frameIntro.dispose(); //Closing the frame 
      createAccountFrame(); //Calling createAccountFrame method to run
      frameCreateAccount.setVisible(true);
    }
    // If the user clicks the continue button from the create account GUI the following will occur
    else if (e.getSource() == btnContinueCreate) {
      // Declaring string variables and initializing them to user input from the fields
      loginName = txtUserID.getText();
      String password = String.valueOf(txtPassCreate.getPassword());
      String passwordConfirm = String.valueOf(txtPassConfirm.getPassword());
      textSizeChecker = charCounter(loginName, 10); // Calling charCounter method to see if the userName is longer than 10 chars
      // Calling charCounter method to see if the password is longer than 10 chars
      boolean textSizeCheckerPass = charCounter(password, 10); // Only need to check 1 password as I have other checks to verify that passwords match
      // Declaring and initializing integer variable to the return of the checkLogin method
      int check = checkLogin(playerID, playerPassword, loginName, password);
      
      //If the account name has not been used and the characters are 10 or below and the length of the userID and password is not 0 then the following will occur
      if (check == 3 && password.equals(passwordConfirm) && textSizeChecker == true && textSizeCheckerPass == true && passwordConfirm.length() > 0 && loginName.length() > 0) {
        outputData("String", 0, loginName, "userInfo.txt"); // Calling outputData method to write the new userID to the userInfo text file
        outputData("String", 0, password, "userInfo.txt"); // Calling outputData method to write the new password to the userInfo text file
        outputData("String", 0, loginName, "scores.txt");
        outputData("", 0, loginName, "scores.txt");

        frameCreateAccount.dispose(); //Closing the frame 
        new Buffering(); //Calling Buffering class to run 
      }
      // If the account the user is trying to make or if they do not fill all credentials the following will occur
      else {
        lblError.setText("<html><u>**Invalid Credentials**");
        clear(); // Calling clear method to clear all fields (text and password)
      }
    }
    // If the user clicks continue on the login GUI the following occurs
    if (e.getSource() == btnContinueLogin) {
      
      int checkB; // A check is first done to see if the login information matches our database
      
      // The two string variables are declared and initialized
      loginName = txtLogin.getText(); // Getting the loginID from the textbox which the user entered
      String pass = String.valueOf(txtPassLogin.getPassword()); // Getting the password the user entered (has String.valueOf because getPassword() returns an array of chars
      
      checkB = checkLogin(playerID, playerPassword, loginName, pass); // Calling the check method to search our database and see if the credentials match
      
      // If the login credentials match the following occurs
      if (checkB == 1) {
        frameLogin.dispose(); //Closing the frame 
        new Buffering(); //Calling Buffering class to run 
      }
      // If the login credentials do not match the following occurs
      else {
        lblError.setText("<html><u>**Invalid Credentials**");  //Letting the user know the problem
        clear(); // Calling clear method to clear all fields (text and passwords)
      }
    }
    // If the user decides to view the settings the following will occur
    else if (e.getSource() == btnSettings) {
      frameMainGUI.dispose(); //Closing the frame 
      settingsFrame(); //Calling settingsFrame to run
    }
    // If the user decides to view the controls the following will occur
    else if (e.getSource() == btnControls) {
      frameSettings.dispose(); //Closing the frame 
      controlsFrame(); //Calling controlsFrame method to run
    }
    // If the user wants to go back to the mainFrame by clicking the back button the following will occur
    else if (e.getSource() == btnBackToMain) {
      frameSettings.dispose(); //Closing the frame 
      frameMainGUI.setVisible(true); // Making the main frame GUI visible
    }
    // If the user wants to go back to the settings GUI by clicking the other back button the following will occur
    else if (e.getSource() == btnBackToSettings) {
      frameControls.dispose(); //Closing the frame 
      frameSettings.setVisible(true); // Making the settings window visible
    }
    // If the user wants to play the game the following will occur
    else if (e.getSource() == btnPlay) {     
      frameMainGUI.dispose(); //Closing the frame 
      optionFrame(); //Calling the optionsFrame method to run
    }
    //If the user clicks the how to play button then the following will occur
    else if (e.getSource() == btnHow) {
      playVideo(); //Calling the playVideo Method to open up a youtube video on how to play using their default browser
    }
    //If the user presses btnTutorial from the optionFrame method then the following will occur
    else if (e.getSource() == btnTutorial) {
      frameTutorial.dispose(); //Closing the frame 
      new Tutorial(); //Calling the tutorial class to run
    }
    //If the user presses btnRealGame from the optionFrame method then the following will occur
    else if (e.getSource() == btnRealGame) {
      frameTutorial.dispose(); //Closing the frame 
      new RealGame(); //Calling RealGame class to run
    }
    
  }// actionPerformed method
  
  // The following mouse methods may not perform a task
  // These are here to satisfy the requirement of having the methods with MouseListener being implemented
  public void mouseClicked(MouseEvent e) {
  }// mouseClicked method
  
  public void mousePressed(MouseEvent e) {
  }// mousePressed method
  
  public void mouseReleased(MouseEvent e) {
  }// mouseReleased method
  
  public void mouseEntered(MouseEvent e) {
    // When the mouse hovers the buttons, a certain message will be displayed around the button depending on the button itself
    if (e.getSource() == btnLogin) {
      btnLogin.setToolTipText("Click to login using an existing account"); // The message displayed to the user
    } else if (e.getSource() == btnCreateAccount) {
      btnCreateAccount.setToolTipText("Click to create a new UserID and Password"); // The message displayed to the user
    } else if (e.getSource() == btnExit) {
      btnExit.setToolTipText("Click to exit Busy AirSpace!"); // The message displayed to the user
    } else if (e.getSource() == btnHelp) {
      btnHelp.setToolTipText("Click to seek assistance"); // The message displayed to the user
    } else if (e.getSource() == btnProblem) {
      btnProblem.setToolTipText("Click to report a problem"); // The message displayed to the user
    } else if (e.getSource() == btnBackToIntro) {
      btnBackToIntro.setToolTipText("Click to go back");
    } else if (e.getSource() == btnBack) {
      btnBack.setToolTipText("Click to go back"); // The message displayed to the user
    } else if (e.getSource() == btnContinueLogin) {
      btnContinueLogin.setToolTipText("Click to complete logging in"); // The message displayed to the user
    } else if (e.getSource() == btnContinueCreate) {
      btnContinueCreate.setToolTipText("Click to complete your registration!"); // The message displayed to the user
    } else if (e.getSource() == btnSettings) {
      btnSettings.setToolTipText("Click to view settings"); // The message displayed to the user
    } else if (e.getSource() == btnPlay) {
      btnPlay.setToolTipText("Click to play!"); // The message displayed to the user
    } else if (e.getSource() == btnHow) {
      btnHow.setToolTipText("Click to view a small tutorial"); // The message displayed to the user
    } else if (e.getSource() == btnBackToMain) {
      btnBackToMain.setToolTipText("Click to go back"); // The message displayed to the user
    } else if (e.getSource() == btnControls) {
      btnControls.setToolTipText("Click to view controls"); // The message displayed to the user
    } else if (e.getSource() == btnBackToSettings) {
      btnBackToSettings.setToolTipText("Click to go back"); // The message displayed to the user
    } else if (e.getSource() == btnTutorial) {
      btnTutorial.setToolTipText("Click to play the tutorial!"); // The message displayed to the user
    } else if (e.getSource() == btnRealGame) {
      btnRealGame.setToolTipText("Click to play the real thing!"); // The message displayed to the user
    }
  }// mouseEntered method
  
  public void mouseExited(MouseEvent e) {
  }// mouseExited method
  
  // This method is invoked when the state of the listener has been changed
  public void stateChanged(ChangeEvent e) {
    
    // If the user clicks the volume slider the following will occur
    if (e.getSource() == volume) {
      
      long clipTimePosition = clip.getMicrosecondPosition(); // Getting the exact timestamp at which the audio-file is at
      clip.stop(); // Stopping the clip (stop does not get rid of its place)
      // Converting the information from the slider to float and then diving by 100 to make it in the range (0.0 - 1.0)
      programVolume = (float) volume.getValue() / 100; // Getting the user selected volume, forcing it to be a float, dividing it by 100 to convert it to the decebal scale
      lblVol.setText("Volume = " + volume.getValue() + "%"); // Displaying the new user selected volume on the label
      
      // Calling musicPlayer method and putting the exact timestamp at which we stopped at for the time the new clip being played-
      // starts at -> instead of changing the volume midway of the audio playing we stop -> change volume -> resume
      musicPlayer(mainFrameMusic, programVolume, clipTimePosition);
      
    }
    
  }// stateChanged method
  
  public static void main(String[] args) {
    
    new BusyAirField();
    
  }// main method
}// BusyAirField class