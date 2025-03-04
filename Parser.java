
import java.util.StringTokenizer;


/**
 * This parser reads user input and tries to interpret it as an "Adventure"
 * command. Every time it is called it reads a line from the terminal and
 * tries to interpret the line as a two word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 * 
 * @author Pham Jimmy
 * @version 7.43
 */
public class Parser 
{
    private CommandWords aValidCommands;  // (voir la classe CommandWords)

    /**
     * Constructeur par defaut qui cree les 2 objets prevus pour les attributs
     */

    public Parser() 
    {
        this.aValidCommands = new CommandWords();
        // System.in designe le clavier, comme System.out designe l'ecran
    } // Parser()

    /**
     * @return la prochaine commande de l'utilisateur.
     * @param pInputLine correspond à l'utilisateur écrit sur le clavier
     */
    public Command getCommand(final String pInputLine) 
    {
        String vWord1;
        String vWord2;

        StringTokenizer vTokenizer = new StringTokenizer( pInputLine);

        // cherche jusqu'a 2 mots dans la ligne tapee
        if ( vTokenizer.hasMoreTokens() )     // y a-t-il un mot suivant ?
            vWord1 = vTokenizer.nextToken();      // recupere le premier mot
        else
            vWord1 = null;
        if ( vTokenizer.hasMoreTokens() )  // y a-t-il encore un mot suivant ?
            vWord2 = vTokenizer.nextToken();  // recupere le deuxieme mot
        else 
            vWord2 = null;
                // note : on ignore tout le reste de la ligne tapee !

        // Verifie si le premier mot est une commande connue.
        // Si oui, cree un objet Command avec ce mot. (vWord2 peut etre null)
        // Sinon, cree une commande vide avec "null" (pour dire 'commande inconnue').
        if ( this.aValidCommands.isCommand( vWord1 ) ) 
            return new Command( vWord1, vWord2 );
        
        else 
            return new Command( null, vWord2 ); 
        
    } // getCommand()
    /**
     * @return les commandes utilisables sous forme de String.
     */
    public String getCommandString()
    {
        return this.aValidCommands.getCommandList();
    } //getCommandString()
} // Parser
