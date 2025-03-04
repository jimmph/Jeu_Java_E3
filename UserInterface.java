import java.awt.event.ActionListener;
import javax.swing.JTextField;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.event.WindowAdapter;
import java.awt.event.ActionEvent;
import java.net.URL;
import javax.swing.ImageIcon;
import java.awt.event.WindowEvent;
import javax.swing.JButton;


/**
 * This class implements a simple graphical user interface with a 
 * text entry area, a text output area and an optional image.
 * 
 * @author Pham Jimmy
 * @version 7.34
 */
public class UserInterface implements ActionListener
{
    private GameEngine aEngine;
    private JFrame     aMyFrame;
    private JTextField aEntryField;
    private JTextArea  aLog;
    private JLabel     aImage;
    private JButton aButtonNorth;
    private JButton aButtonSouth;
    private JButton aButtonEast;
    private JButton aButtonWest;
    private JButton aButtonUp;
    private JButton aButtonDown;
    /**
     * Construct a UserInterface. As a parameter, a Game Engine
     * (an object processing and executing the game commands) is
     * needed.
     * 
     * @param pGameEngine  The GameEngine object implementing the game logic.
     */
    public UserInterface( final GameEngine pGameEngine )
    {
        this.aEngine = pGameEngine;
        this.createGUI();
    } // UserInterface(.)

    /**
     * Print out some text into the text area.
     * @param pText correspond au texte que l'on veut afficher
     */
    public void print( final String pText )
    {
        this.aLog.append( pText );
        this.aLog.setCaretPosition( this.aLog.getDocument().getLength() );
    } // print(.)

    /**
     * Print out some text into the text area, followed by a line break.
     * @param pText correspond au texte que l'on veut afficher
     */
    public void println( final String pText )
    {
        this.print( pText + "\n" );
    } // println(.)

    /**
     * Show an image file in the interface.
     * @param pImageName correspond au nom de l'image
     */
    public void showImage( final String pImageName )
    {
        String vImagePath = "Photo/" + pImageName; // to change the directory
        URL vImageURL = this.getClass().getClassLoader().getResource( vImagePath );
        if ( vImageURL == null )
            System.out.println( "Image not found : " + vImagePath );
        else {
            ImageIcon vIcon = new ImageIcon( vImageURL );
            this.aImage.setIcon( new ImageIcon( 
            vIcon.getImage().getScaledInstance(640,400,java.awt.Image.SCALE_SMOOTH)));
            this.aMyFrame.pack();
        }
    } // showImage(.)

    /**
     * Enable or disable input in the entry field.
     * @param pOnOff booléen
     */
    public void enable( final boolean pOnOff )
    {
        this.aEntryField.setEditable( pOnOff ); // enable/disable
        if ( pOnOff ) { // enable
            this.aEntryField.getCaret().setBlinkRate( 500 ); // cursor blink
            this.aEntryField.addActionListener( this ); // reacts to entry
        }
        else { // disable
            this.aEntryField.getCaret().setBlinkRate( 0 ); // cursor won't blink
            this.aEntryField.removeActionListener( this ); // won't react to entry
        
            this.aButtonNorth.setEnabled(false);        
            this.aButtonEast.setEnabled(false);        
            this.aButtonSouth.setEnabled(false);        
            this.aButtonWest.setEnabled(false);        
            this.aButtonUp.setEnabled(false);        
            this.aButtonDown.setEnabled(false);        
        }
    } // enable(.)

    /**
     * Set up graphical user interface.
     */
    private void createGUI()
    {
        this.aButtonNorth = new JButton("North");
        this.aButtonSouth = new JButton("South");
        this.aButtonEast = new JButton("East");
        this.aButtonWest = new JButton("West");
        this.aButtonUp = new JButton("Up");
        this.aButtonDown = new JButton("Down");
        this.aMyFrame = new JFrame( "Samourai on Moon" ); // change the title !
        this.aEntryField = new JTextField( 34 );

        this.aLog = new JTextArea();
        this.aLog.setEditable( false );
        JScrollPane vListScroller = new JScrollPane( this.aLog );
        vListScroller.setPreferredSize( new Dimension(200, 200) );
        vListScroller.setMinimumSize( new Dimension(100,100) );

        this.aImage = new JLabel();
        
        JPanel vPanel = new JPanel();
        vPanel.setLayout( new BorderLayout() );
        
        vPanel.add(this.aImage, BorderLayout.NORTH);
        vPanel.add(vListScroller, BorderLayout.CENTER);
        vPanel.add(this.aEntryField, BorderLayout.SOUTH);
 

        JPanel vPanelButton = new JPanel();
        vPanelButton.setLayout( new BorderLayout() );
        JPanel vPanel2 = new JPanel();
        vPanel2.setLayout( new BorderLayout() ); 
        
        vPanel.add(vPanelButton, BorderLayout.EAST);
        vPanelButton.add( this.aButtonNorth, BorderLayout.NORTH );
        vPanelButton.add( this.aButtonSouth, BorderLayout.SOUTH );
        vPanelButton.add( this.aButtonEast, BorderLayout.EAST );
        vPanelButton.add( this.aButtonWest, BorderLayout.WEST );        
        vPanelButton.add( vPanel2, BorderLayout.CENTER );        
        vPanel2.add( this.aButtonUp, BorderLayout.CENTER );
        vPanel2.add( this.aButtonDown, BorderLayout.SOUTH );

        
        this.aMyFrame.getContentPane().add( vPanel, BorderLayout.CENTER );

        // add some event listeners to some components
        this.aEntryField.addActionListener( this );
        this.aButtonNorth.addActionListener( this ) ;
        this.aButtonSouth.addActionListener( this ) ;
        this.aButtonEast.addActionListener( this ) ;
        this.aButtonWest.addActionListener( this ) ;
        this.aButtonUp.addActionListener( this ) ;
        this.aButtonDown.addActionListener( this ) ;
        // to end program when window is closed
        this.aMyFrame.addWindowListener(
            new WindowAdapter() { // anonymous class
                @Override public void windowClosing(final WindowEvent pE)
                {
                    System.exit(0);
                }
        } );

        this.aMyFrame.pack();
        this.aMyFrame.setVisible( true );
        this.aEntryField.requestFocus();
    } // createGUI()

    /**
     * Actionlistener interface for entry textfield.
     * @param pE action réalisé par l'utilisateur
     */
    @Override public void actionPerformed( final ActionEvent pE ) 
    {
        // no need to check the type of action at the moment
        // because there is only one possible action (text input) :
        if (this.aButtonNorth.equals(pE.getSource()))
            this.aEngine.interpretCommand("go North");
        else if (this.aButtonSouth.equals(pE.getSource()))
            this.aEngine.interpretCommand("go South");
        else if (this.aButtonEast.equals(pE.getSource()))
            this.aEngine.interpretCommand("go East");
        else if (this.aButtonWest.equals(pE.getSource()))
            this.aEngine.interpretCommand("go West");
        else if (this.aButtonUp.equals(pE.getSource()))
            this.aEngine.interpretCommand("go Up");
        else if (this.aButtonDown.equals(pE.getSource()))
            this.aEngine.interpretCommand("go Down");
        else
            this.processCommand(); // never suppress this line
    } // actionPerformed(.)

    /**
     * A command has been entered in the entry field.  
     * Read the command and do whatever is necessary to process it.
     */
    private void processCommand()
    {
        String vInput = this.aEntryField.getText();
        this.aEntryField.setText( "" );

        this.aEngine.interpretCommand( vInput );
    } // processCommand()
} // UserInterface 
