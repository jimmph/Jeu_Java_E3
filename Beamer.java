
/**
 * La classe Beamer est une sous-classe de Item. Un objet Beamer présente en
 * plus un attribut Room aChargedRoom. Il permet au joueur de se téléporter
 * à l'endroit où il a chargé l'objet.
 *
 * @author PHAM Jimmy
 * @version 7.45
 */

public class Beamer extends Item
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private Room aChargedRoom;

    /**
     * Constructeur d'objets de classe Beamer
     */
    public Beamer(final String pName, final String pDescription, final double pWeight)
    {
        // initialisation des variables d'instance
        super(pName, pDescription, pWeight);
    }

    /**
     * Accesseur de l'attribut aChargedRoom
     *
     * @return l'attribut aChargedRoom
     */
    public Room getChargedRoom()
    {
        // Insérez votre code ici
        return this.aChargedRoom;
    }
    
    /**
     * Mutateur de l'attribut aChargedRoom
     * 
     * @param pRoom l'objet Room qu'on attribue à aChargedRoom 
     */
    public void setChargedRoom(final Room pRoom)
    {
        this.aChargedRoom = pRoom;
    }
    
    /**
     * méthode qui vérifie si aChargedRoom a été instancié
     */
    public boolean isCharged()
    {
        return this.aChargedRoom!=null;
    }
    
    
}
