import java.util.Stack;
import java.util.HashMap;
import java.util.Set;
/**
 * La classe Player contient toutes les méthodes concernant le joueur, ses 
 * déplacements et ses différentes interactions.
 * @author Pham Jimmy
 * @version 7.29
 */
public class Player
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private String aPseudo;
    private Room aCurrentRoom;
    private Stack<Room> aPreviousRoom;
    private ItemList aInventItems;
    private double aMaxWeight;
    private double aInventoryWeight;
    private int aMovesLeft;
    private boolean aStatus;
    /**
     * Constructeur d'objets de classe Player
     * @param pPseudo le pseudo du joueur
     * @param pCurrentRoom la room dans laquelle se trouve le joueur
     * @param pMaxWeight le poids max de l'inventaire
     * @param pMovesLeft le nombre de mouvement restant
     */ 
    public Player(final String pPseudo, final Room pCurrentRoom, final double pMaxWeight, final int pMovesLeft)
    {
        // initialisation des variables d'instance
        this.aPseudo = pPseudo;
        this.aCurrentRoom = pCurrentRoom;
        this.aPreviousRoom = new Stack<Room>();
        this.aInventItems = new ItemList("the inventory");
        this.aMaxWeight = pMaxWeight;
        this.aInventoryWeight= 0;
        this.aMovesLeft = pMovesLeft;
        this.aStatus = true;
    }

    /**
     * @return le pseudo du joueur
     */
    public String getPseudo()
    {
        // Insérez votre code ici
        return this.aPseudo;
    }
    
    /**
     * @return la room dans laquelle se trouve le joueur
     */
    public Room getCurrentRoom()
    {
        // Insérez votre code ici
        return this.aCurrentRoom;
    }
    
    /**
     * @return la pièce dans laquelle se trouvait le joueur au tour d'avant
     */
    public Stack<Room> getPreviousRoom()
    {
        return this.aPreviousRoom;
    }

    /**
     * @return l'inventaire du joueur
     */
    public ItemList getBagItems ()
    {
        return this.aInventItems;
    }
    
    /**
     * @return true si le joueur est vivant, false s'il est mort
     */
    public boolean getStatus()
    {
        return this.aStatus;
    }
    
    /**
     * change le statut du joueur à "mort"
     */
    public void setDead()
    {
        this.aStatus = false;
    }
    
    /**
     * procédure qui donne la prochaine pièce dans laquelle le joueur se déplace
     * @param pRoom correspond à la prochaine pièce
     */
    public void executeGoRoom(final Room pRoom)
    {
        this.aPreviousRoom.push(this.aCurrentRoom);
        this.aCurrentRoom = pRoom;
        this.aMovesLeft-=1;
        
        
    }
    
    /**
     * procédure qui déplace le joueur en arrière
     */
    public void executeGoBack()
    {
        Room vBackRoom = this.aPreviousRoom.pop();
        this.aCurrentRoom = vBackRoom;
        this.aMovesLeft-=1;
    }
    
    /**
     *permet au joueur de prendre un item
     *@param pItem l'item qu'on veut prendre
     *
     */
    public void executeTake(final Item pItem)
    {
        this.aInventItems.getItems().put(pItem.getName(),pItem);
        this.aCurrentRoom.aItems.removeItem(pItem.getName(),pItem);
        this.removeInventWeight(-pItem.getWeight());
    }
    
    /**
     *permet au joueur de poser un item
     *@param pItem l'item qu'on veut poser
     *
     */
    public void executeDrop(final Item pItem)
    {
        this.aCurrentRoom.aItems.addItem(pItem.getName(), pItem);
        this.aInventItems.getItems().remove(pItem.getName(),pItem);
        this.removeInventWeight(pItem.getWeight());
    }
    
    /**
     * @return le poids max des items qu'on peut porter dans l'inventaire
     */
    public double getMaxWeight()
    {
        return this.aMaxWeight;
    }
    
    /**
     * @return le poids de l'inventaire
     */
    public double getInventoryWeight()
    {
        return this.aInventoryWeight;
    }
    
    /**
     * change le poids max des items qu'on peut porter dans l'inventaire
     * @param pMaxWeight le nouveau poids max de l'inventaire
     */
    public void setMaxWeight(final double pMaxWeight)
    {
        this.aMaxWeight = pMaxWeight;
    }
    
    /**
     * enlève du poids de l'inventaire
     * @param pWeight le poids qu'on enlève de l'inventaire
     */
    public void removeInventWeight(final double pWeight)
    {
        this.aInventoryWeight-=pWeight;
    }
    
    /**
     * @return le nombre de mouvement restant
     */
    public int getMovesLeft()
    {
        return this.aMovesLeft;
    }
    
    /**
     * procédure qui ajoute un nombre de déplacements
     * @param pMovesLeft le nombre de déplacements initialisé
     */
    
    public void setMovesLeft( final int pMovesLeft)
    {
        this.aMovesLeft=pMovesLeft;
    }
    
    /**
     * @return True si le joueur est passé par une trap door
     */
    public boolean possibleGoBack()
    {
        return this.aCurrentRoom.isExit(this.aPreviousRoom.peek());
    }
    
    /**
     * permet à l'utilisateur de charger le Beamer
     */
    public void chargeBeamer(final Beamer pBeamer)
    {
        pBeamer.setChargedRoom(this.aCurrentRoom);
    }
    
    /**
     * permet à l'utilisateur de se téléporter grâce au Beamer
     */
    public void fireBeamer(final Beamer pBeamer)
    {
        this.aPreviousRoom.push(this.aCurrentRoom);
        this.aCurrentRoom = pBeamer.getChargedRoom();
        this.aInventItems.removeItem(pBeamer.getName(),pBeamer);
        this.aInventoryWeight-=pBeamer.getWeight();
    } 
    
    /**
     * indique si le joueur est autorisé à être dans la room
     * @return true si oui, sinon false
     */
    public boolean canGoMonster()
    {
        if (this.aCurrentRoom.getPassage() ==0){
            if (this.aCurrentRoom.getItemNeeded().equals("None")){
                return true;
            }
            else if (this.aCurrentRoom.getName().equals("TopMountain")){
                return true;
            }
            else if(this.aInventItems.getItem(this.aCurrentRoom.getItemNeeded())==null){
                this.setDead();
                return false;
            }
        }
        return true;
    }
    
    /**
     * indique si le joueur est autorisé à être dans la room
     * @return true si oui, sinon false
     */
    public boolean canGoMountain()
    {
        if (this.aCurrentRoom.getItemNeeded().equals("None")){
                return true;
        }
        else if (this.aCurrentRoom.getName().equals("TopMountain")){
            if (this.aInventItems.getItem(this.aCurrentRoom.getItemNeeded())==null){
                this.setDead();
                return false;
            }
        }
        return true;
    }
}
