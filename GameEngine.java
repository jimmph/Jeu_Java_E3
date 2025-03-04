import java.util.Stack;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
/**
 * 
 *  This class creates all rooms, creates the parser and starts
 *  the game.  It also evaluates and executes the commands that 
 *  the parser returns.
 * 
 * @author  Pham Jimmy
 * @version 7.26
 */
public class GameEngine
{
    private Parser        aParser;
    private UserInterface aGui;
    private Player aPlayer;
    /**
     * Constructor for objects of class GameEngine
     */
    public GameEngine()
    {
        this.aParser = new Parser();
        this.createRooms();
    }

    /**
     * procédure qui affiche l'image et lea description de la pièce
     * @param pUserInterface initialise et ouvre l'interface
     */
    public void setGUI( final UserInterface pUserInterface )
    {
        this.aGui = pUserInterface;
        this.printWelcome();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        this.aGui.print( "\n" );
        this.aGui.println( "Welcome in Samourai on Moon !" );
        this.aGui.println( "You are Gintoki Hanma, a samourai on Titan missionning for"+
        " science purposes" );
        this.aGui.println( "The spaceship is broken, you need to change the 2 broken"+
        " motors to get back on Earth" );
        this.aGui.println( "Type 'help' if you need help." );
        this.aGui.print( "\n" );
        this.printLocationInfo();
    }
    
    /**
     * Affiche la description et l'image de la room
     */
    private void printLocationInfo()
    {
        if (this.aPlayer.getCurrentRoom().getName().equals("CerbMort")){
            this.aGui.println("\n");
            this.aGui.println("Well done ! You have defeated Cerberus");
            this.aGui.println("You can continue your journey.\n");
        }
        else if (this.aPlayer.getCurrentRoom().getName().equals("Titan")){
            this.aGui.println("\n");
            this.aGui.println("Well done ! You have defeated Titan");
            this.aGui.println("You can continue your journey.\n");
            
        }
        this.aGui.println( this.aPlayer.getCurrentRoom().getLongDescription() );
        if (this.aPlayer.getCurrentRoom().getName().equals("Spaceship"))
            this.aGui.println("You have 10 moves left before dying of asphyxiation");
        else
            this.aGui.println("You have " + this.aPlayer.getMovesLeft() + " moves left before "+
            "dying of asphyxiation");
        if ( this.aPlayer.getCurrentRoom().getImageName() != null )
            this.aGui.showImage( this.aPlayer.getCurrentRoom().getImageName() );
        
        
        
        
    }
    
    /**
     * procédure qui affiche la description de la pièce ou d'un item si l'on 
     * renseigne un 2e mot
     */
   public void look()
   {
       this.aGui.println(this.aPlayer.getCurrentRoom().getLongDescription());
   }
   
   /**
    * procédure qui permet au joueur de manger
    * @param pCommand la commande entrée par l'utilisateur
    */
   public void eat(final Command pCommand)
   {
       if (!pCommand.hasSecondWord()){
           this.aGui.println("What do you want to eat ?");
           return;
       }
       String vFood = pCommand.getSecondWord();
       
       if (!vFood.equals("MagicCookie")){
           this.aGui.println("You can't eat that !");
           return;
       }
       
       if (this.aPlayer.getBagItems().getItem("MagicCookie")==null){
           this.aGui.println("You don't have cookie in your bag");
           return;
       }
       
       this.aPlayer.removeInventWeight(this.aPlayer.getBagItems().getItem("MagicCookie").getWeight());
       this.aPlayer.setMaxWeight(10);
       this.aPlayer.getBagItems().removeItem("MagicCookie",this.aPlayer.getBagItems().getItem("MagicCookie"));
       this.aGui.println("You have eaten now and you are not hungry anymore");
       this.aGui.println("Your inventory is bigger !");
   }
   
    /**
     * Crée les rooms du jeu, désigne chacune de leurs sorties et leurs assignent
     * les items. Initialise également la pièce de départ et "la pièce  d'avant".
     * 
     */
    private void createRooms()
    {
        Room vVaisseau = new Room("Spaceship","in the spaceship", "vaisseau.jpg");
        Room vDehors1 = new Room("FSpaceship","in front of the spaceship, you stand on the frozen land.", 
        "dehors1.jpg");
        Room vDehors2 = new Room("FDwarfShelter","in front of the dwarf's shelter", "maisonNain.png");
        Room vMaisonNain = new Room("DwarfShelter", "in the dwarf's shelter","dwarfhouse.jpg");
        Room vBasMontagne = new Room("BotMountain","at the foot of the mountain","botmountain.jpg"); 
        Room vHautMontagne = new Room("TopMountain","in the valley's mountain; you may face a big monster.. a blunt sword won't be enough","topmountain.jpg", "BoostedShoes");        
        Room vDehors4 = new Room("Dehors4","in the winding mud path","mud.jpg"); 
        Room vPiece1 = new Room("HotelMonster","in the Hotel Monster","hotel.jpg");
        Room vChambre = new Room("Room29","in room 29","room.jpeg");  
        Room vSousSol = new Room("SousSol","in the basement","basement.png"); 
        Room vDehors7 = new Room("Grave","in front of a samourai's grave","grave.jpg");   
        Room vDehors5 = new Room("Dehors5","in a sinister path, you need a blunt sword to be ready..","sinister.jpg");   
        Room vMonstre1 = new Room("Cerberus", "in the Cerberus' lair","cerberus.jpg", "BluntSword");   
        Room vMonstreMort = new Room("CerbMort","in the treasure room", "treasure.jpg");
        Room vMonstre2 = new Room("Titan","in the original Titan's lair", "titan.jpg", "TitanicSword");
        Room vMonstreMort2 = new Room("TitanMort","in the treasure room", "treasure2.jpg");



        
        vVaisseau.setExit("East", vDehors1);
        
        vDehors1.setExit("North", vDehors2);
        vDehors1.setExit("East", vDehors7);
        vDehors1.setExit("South", vDehors5);
        vDehors1.setExit("West", vVaisseau);

        vDehors2.setExit("North", vDehors4);
        vDehors2.setExit("East", vBasMontagne);
        vDehors2.setExit("South",vDehors1);
        vDehors2.setExit("West", vMaisonNain);
        
        vMaisonNain.setExit("East", vDehors2);
        
        vBasMontagne.setExit("West", vDehors2); 
        vBasMontagne.setExit("Up",vHautMontagne);
        
        vHautMontagne.setExit("Down", vBasMontagne);
        vHautMontagne.setExit("East", vMonstre2);
        
        vMonstre2.setExit("North", vMonstreMort2);
        
        vMonstreMort2.setExit("South", vMonstre2);
        vMonstreMort2.setExit("Down", vVaisseau);
        
        vDehors4.setExit("North", vPiece1);
        vDehors4.setExit("South", vDehors2);
        
        vPiece1.setExit("East", vSousSol);
        vPiece1.setExit("South", vDehors4);
        vPiece1.setExit("West", vChambre);
        
        vChambre.setExit("East", vPiece1);
        
        vSousSol.setExit("West", vPiece1);
        
        vDehors7.setExit("West", vDehors1);
        
        vDehors5.setExit("North", vDehors1);
        vDehors5.setExit("South", vMonstre1);        

        vMonstre1.setExit("North", vDehors5);
        vMonstre1.setExit("South", vMonstreMort);
        
        vMonstreMort.setExit("North", vMonstre1);
        
        this.aPlayer = new Player("Gintoki Hanma", vVaisseau,3, 10);
        
        Beamer vBeamer = new Beamer("Beamer", "to teleport you somewhere",1);
        Item vChaussures = new Item("BoostedShoes", "allows you to climb mountain", 1);
        Item vSabreNul = new Item("BluntSword", "only allows you to kill Cerberus", 1);
        Item vSabreFort = new Item("TitanicSword", "allows you to kill the original titan", 2);
        Item vLivre = new Item("IncantationBook", "gives a second wind to a broken propulsion motor..", 2);
        Item vMoteur1 = new Item("BrokenPropulsionMotor", "It would have allowed Gintoki to leave this moon, but it's broken ! Maybe is there "+ 
        "an item that would make it work again..", 2);
        Item vMoteur2 = new Item("PropulsionMotor", "Bravo ! the first propulsion motor has been found ! find the second one to leave this moon", 2);
        Item vMagicCookie = new Item("MagicCookie", "allows you to expand your inventory", 2);
        
        vVaisseau.getItems().addItem("Beamer", vBeamer);
        vDehors7.getItems().addItem("BluntSword", vSabreNul);
        vMaisonNain.getItems().addItem("IncantationBook", vLivre);
        vMonstreMort.getItems().addItem("BoostedShoes", vChaussures);
        vChambre.getItems().addItem("MagicCookie", vMagicCookie);
        vSousSol.getItems().addItem("TitanicSword", vSabreFort);
        vSousSol.getItems().addItem("PropulsionMotor", vMoteur2);
        vMonstreMort2.getItems().addItem("BrokenPropulsionMotor", vMoteur1);
    }

    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     * @param pCommandLine correspond à la commande entrée par l'utilisateur
     */
    public void interpretCommand( final String pCommandLine ) 
    {
        this.aGui.println( "> " + pCommandLine );
        Command vCommand = this.aParser.getCommand( pCommandLine );

        if ( vCommand.isUnknown() ) {
            this.aGui.println( "I don't know what you mean..." );
            return;
        }

        String vCommandWord = vCommand.getCommandWord();
        if ( vCommandWord.equals( "help" ) )
            this.printHelp();
        else if ( vCommandWord.equals( "go" ) )
            this.goRoom( vCommand );
        else if(vCommandWord.equals("look"))
            this.look();
        else if(vCommandWord.equals("eat"))
            this.eat(vCommand);
        else if( vCommandWord.equals("back"))
            this.back(vCommand);
        else if(vCommandWord.equals("test"))
            this.test(vCommand);       
        else if(vCommandWord.equals("take"))
            this.take(vCommand);
        else if(vCommandWord.equals("drop"))
            this.drop(vCommand);
        else if(vCommandWord.equals("inventory"))
            this.inventory(vCommand);
        else if(vCommandWord.equals("charge"))
            this.charge(vCommand);
        else if(vCommandWord.equals("fire"))
            this.fire(vCommand);
        else if(vCommandWord.equals("inventory"))
            this.inventory(vCommand);
        else if ( vCommandWord.equals( "quit" ) ) {
            if ( vCommand.hasSecondWord() )
                this.aGui.println( "Quit what?" );
            else
                this.endGame();
        }
        this.oxyMoves();
        this.timerEnd();
        this.winSituation();
        if (!this.aPlayer.getStatus()){
            this.aGui.println("You died");
            this.endGame();
        }
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     */
    private void printHelp() 
    {
        this.aGui.println( "You are lost. You are alone. You wander" );
        this.printLocationInfo();
        this.aGui.println( "Your command words are: " + this.aParser.getCommandString() );
        this.aGui.println("You have " + this.aPlayer.getMovesLeft() + " moves left before "+
        "dying of asphyxiation");
        this.aGui.println("Don't forget to write the initials of the second" +
        "word in capital letters !");
    }

    /** 
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param pCommand correspond au 2e mot entré par l'utilisateur après un "go"
     */
    private void goRoom( final Command pCommand ) 
    {
        
        String vDirection = pCommand.getSecondWord();

        if ( ! pCommand.hasSecondWord() ) {
            // if there is no second word, we don't know where to go...
            this.aGui.println( "Go where?" );
            return;
        }

        Room vNextRoom = this.aPlayer.getCurrentRoom().getExit( vDirection );
        
        
        if ( vNextRoom == null ){
            this.aGui.println( "There is no door!" );
            return;
        }
        this.aPlayer.executeGoRoom(vNextRoom);

        if (!this.aPlayer.canGoMonster()){
            this.aGui.println("You didn't have the sword required in your" +
            " inventory..");
        }
        else if (!this.aPlayer.canGoMountain()){
            this.aGui.println("You tried to  climb the mountain barefoot and..");
        }
        else {
            this.aPlayer.getCurrentRoom().addPassage();
            this.printLocationInfo();
        }
    }

    /**
     * procédure qui permet de revenir dans la pièce précédente
     * @param pCommand correspond à la commande entrée par l'utilisateur
     */
    private void back(final Command pCommand)
    {
        if (pCommand.hasSecondWord()){
            this.aGui.println("You can't back in a particular room !");
            return;
        }
        
        if(this.aPlayer.getPreviousRoom().empty()){
            this.aGui.println("There is no previous room");
            return;
        }
        if (!this.aPlayer.possibleGoBack()){
            this.aGui.println("this was a trap door !");
            return;
        }
        this.aPlayer.executeGoBack();
        this.aPlayer.getCurrentRoom().addPassage();
        this.printLocationInfo();
    }
    
    /**
     * procédure qui teste une succession de commandes enregistrées dans un fichier.
     * @param pCommand correspond à la commande entrée par l'utilisateur
     */
    private void test(final Command pCommand){
        if (!pCommand.hasSecondWord()){
            this.aGui.println("What do you want to test ?");
            return;
        }
        String vFile = pCommand.getSecondWord();
        try{
            Scanner vScan = new Scanner(new File(""+vFile+".txt"));
            this.aGui.println("Testing "+vFile+" ...");
            while (vScan.hasNextLine())
                this.interpretCommand(vScan.nextLine());
        }
        catch(final FileNotFoundException pE){
            this.aGui.println("There is no such file of testing");
        }
    }
    
    /**
     * procédure qui permet de récupérer l'item 
     * @param pCommand la commande entrée par l'utilisateur
     */
    private void take(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("What do you want to take ?");
        }
        else if (pCommand.hasSecondWord())
        {
            String vTake=pCommand.getSecondWord();
            Item vItem=this.aPlayer.getCurrentRoom().getItem(vTake);
            if (vItem==null)
            {
                this.aGui.println("This item is not in this room or does not exist");
                return;
            }
            else if (vItem.getWeight()+this.aPlayer.getInventoryWeight()>this.aPlayer.getMaxWeight()){
                this.aGui.println("this item is too heavy for the bag");
            }
            else
            {
                this.aPlayer.executeTake(vItem);
                this.aGui.println("You have taken "+vTake);
            }
            this.aGui.println(this.aPlayer.getBagItems().getBagString());
        }       
    }
    
    /**
     * procédure qui permet de poser l'item 
     * @param pCommand la commande entrée par l'utilisateur
     */
    private void drop(final Command pCommand)
    {
        if(!pCommand.hasSecondWord())
        {
            this.aGui.println("What do you want to drop ?");
        }
        else if (pCommand.hasSecondWord())
        {
            String vDrop=pCommand.getSecondWord();
            Item vItem=this.aPlayer.getBagItems().getItem(vDrop);
            if (vItem==null)
            {
                this.aGui.println("This item is not in your inventory or does not exist");
                return;
            }
            else
            {
                this.aPlayer.executeDrop(this.aPlayer.getBagItems().getItem(vDrop));
                this.aGui.println("You have dropped "+vDrop);
            }
            this.aGui.println(this.aPlayer.getBagItems().getBagString());
        }
    }
    
    /**
     * affiche l'inventaire du joueur
     * @param pCommand la commande entrée par l'utilisateur
     */
     private void inventory(final Command pCommand)
     {
         if (pCommand.hasSecondWord()){
             this.aGui.println("if you want to see your inventory write 'inventory'");
             return;
         }
         this.aGui.println(this.aPlayer.getBagItems().getBagString());
         this.aGui.println("Total weight: " + this.aPlayer.getInventoryWeight());
         this.aGui.println("Max weight: " + this.aPlayer.getMaxWeight());
     }
    
    /**
     * procédure qui permet de terminer la partie
     */
    private void endGame()
    {
        this.aGui.enable( false );
    }

    /**
     * détermine si le joueur n'a plus de tours restant à jouer
     */
    
    private void timerEnd()
    {
        if(this.aPlayer.getMovesLeft()==0){
            this.aGui.println("You have no more oxygen");
            this.aPlayer.setDead();
            this.endGame();
        }
    }
    
    /**
     * vérifie si le joueur est retourné au vaisseau, si oui le temps est réinitialisé à 15 moves
     */
    private void oxyMoves()
    {
        if(this.aPlayer.getCurrentRoom().getName().equals("Spaceship"))
            this.aPlayer.setMovesLeft(10);
    }
    
    /**
     * permet au joueur de charger son Beamer censé être dans son inventaire
     * @param pCommand la commande entrée par le joueur
     */
    private void charge(final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){
            this.aGui.println("What do you want to charge ?");
            return;
        }
        String vSecondWord= pCommand.getSecondWord();
        if (!vSecondWord.equals("Beamer")){
            this.aGui.println("I don't know how to charge this..");
            return;
        }
        Item vItem = this.aPlayer.getBagItems().getItem(vSecondWord);
        Beamer vBeamer = (Beamer)vItem; 
        if (vItem==null){
            this.aGui.println("You don't have the beamer in your inventory");
            return;
        }
        if (vBeamer.isCharged()){
            this.aGui.println("Your beamer is already charged");
            return;
        }
        this.aPlayer.chargeBeamer(vBeamer);
        this.aGui.println("Your beamer has been charged");
    }
    
    /**
     * permet au joueur de se téléporter à la room chargée dans son Beamer,
     * censé être placé dans son inventaire
     * @param pCommand la commande entrée par le joueur
     */
    private void fire(final Command pCommand)
    {
        if (!pCommand.hasSecondWord()){
            this.aGui.println("What do you want to fire ?");
            return;
        }
        String vSecondWord= pCommand.getSecondWord();
        if (!vSecondWord.equals("Beamer")){
            this.aGui.println("I don't know how to fire this..");
            return;
        }
        Item vItem = this.aPlayer.getBagItems().getItem(vSecondWord);
        Beamer vBeamer = (Beamer) vItem; 
        if (vItem==null){
            this.aGui.println("You don't have the beamer in your inventory");
            return;
        }
        if (!vBeamer.isCharged()){
            this.aGui.println("Your beamer isn't charged yet !");
            return;
        }
        this.aPlayer.fireBeamer(vBeamer);
        this.aGui.println("Your beamer has been fired");
        this.printLocationInfo();
        
    }
    
    /**
     * Termine le jeu dans le cas où les 2 moteurs et le livre d'incantation sont posés dans le vaisseau.
     * Renvoie un message de victoire dans ce cas, et renvoie un message rappelant la mission du jeu sinon.
     */
    private void winSituation()
    {
        if (this.aPlayer.getCurrentRoom().getName().equals("Spaceship")){
            Room vRoom = this.aPlayer.getCurrentRoom();
            if (vRoom.getItems().verifItem("IncantationBook")){
                if (vRoom.getItems().verifItem("PropulsionMotor")){
                    if (vRoom.getItems().verifItem("BrokenPropulsionMotor")){
                        this.printWin();
                    }
                }
            }
            else{
                this.printMiss();
            }
        }
    }
    
    /**
     * affiche le message de victoire et termine le jeu
     */
    private void printWin()
    {
        this.aGui.println("\n Congratulations ! You gathered all the items needed"+
        " to get back to Earth \n");
        this.aGui.println("Your adventure ends here. Thank you for playing !");
        this.endGame();
    }
    
    /**
     * affiche le message qui rappelle les missions du jeu
     */
    private void printMiss()
    {
        this.aGui.println("\n Hey ! You need to DROP two propulsion motors"+
        " to get back to Earth. \n");
        this.aGui.println("Maybe there is an item to DROP to repair a broken motor..");
    }
}
