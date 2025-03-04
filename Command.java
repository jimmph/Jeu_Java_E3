 
/**
 * Classe Command - une commande du jeu d'aventure Zuul.
 *
 * @author Pham Jimmy
 * @version 7.43
 */
public class Command
{
    private String aCommandWord;
    private String aSecondWord;
    
    /**
     * Crée un objet commande. Les deux mots doivent fournis, mais ils 
     * peuvent très bien valoir null. Le mot commande doit valoir null pour
     * indiquer que la commande n'est pas reconnue par le jeu.
     * 
     * @param pCommandWord correspond à la première commande entrée par l'utilisateur
     * @param pSecondWord correspond à la seconde commande entrée par l'utilisateur
     */
    public Command(final String pCommandWord, final String pSecondWord)
    {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }//Command
    
    /**
     * @return le mot commande de la commande. Si la commande n'est pas comprise, le résultat sera null.
     */
    public String getCommandWord()
    {
        return this.aCommandWord;
    }
    
    /**
     * @return le deuxième mot de la commande. Retourne null s'il n'y a pas de deuxième mot.
     */
    public String getSecondWord()
    {
        return this.aSecondWord;
    }
    
    /**
     * @return true si la commande possède un deuxième mot sinon false
     */
    public boolean hasSecondWord()
    {
        return this.aSecondWord != null;
    }
    
    /**
     * @return true si la commande n'a pas été comprise sinon false
     */
    public boolean isUnknown()
    {
        return this.aCommandWord == null;
    }
    
} // Command
