  


/**
 * la classe CommandWords recense les mots commandes valides, et possède une méthode qui retourne les
 * la liste des mots commandes.
 *
 * @author Pham Jimmy
 * @version 7.43
 */
public class CommandWords
{
    // a constant array that will hold all valid command words
    private static final String aValidCommands[] = { "go","quit","help","look",
        "eat", "back", "test", "take", "drop", "inventory", "fire", "charge"};
    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        //rien a faire ici
    } // CommandWords()

    /**
     * Vérifie si le String est une commande valide.
     * @return true si le string en paramètre est bien une commande valide, false sinon.
     * @param pString correspond à la commande entrée par l'utilisateur
     */
    public boolean isCommand( final String pString )
    {
        for ( int vI=0; vI<this.aValidCommands.length; vI++ ) {
            if ( this.aValidCommands[vI].equals( pString ) )
                return true;
        } // for
        // if we get here, the string was not found in the commands :
        return false;
    } // isCommand()
    /**
     * @return un mot contenant toute les commandes
     */
    public String getCommandList()
    {
        StringBuilder vCommand= new StringBuilder();
        for(int vI = 0; vI < this.aValidCommands.length; vI++) {            
           vCommand.append( this.aValidCommands[vI] + "  " );        
        }        
       return vCommand.toString();    
    }
}