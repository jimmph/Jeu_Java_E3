import java.util.HashMap; 
import java.util.Set;
/**
 * Classe Room - un lieu du jeu d'aventure Zuul.
 *Crée des objets ayant pour attributs 2 chaines de caractères représentant la 
 *description et le nom de l'image de la room, ainsi que 2 HashMap permettant de 
 *déterminer les sorties de la room courante et les items qui y sont
 * @author PHAM Jimmy
*/
public class Room
{
    private String aName;
    private String aDescription;
    private HashMap<String,Room> aExits;
    private String aImageName;
    public ItemList aItems;
    private int aPassage;
    private String aItemNeeded;
    
    /**
     * constructeur naturel de la classe
     * @param pName correspond au nom de la pièce
     * @param pDescription correspond à la  description de la pièce
     * @param pImage correspond au nom de l'image de la pièce
     * @param pItemNeeded l'item nécessaire pour accéder à la pièce
     */
    public Room(final String pName, final String pDescription, final String pImage,
    final String pItemNeeded)
    {
        this.aName = pName;
        this.aDescription= pDescription;
        this.aExits = new HashMap<String,Room>();
        this.aImageName = pImage;
        this.aItems = new ItemList("The room");
        this.aPassage = 0;
        this.aItemNeeded = pItemNeeded;
    }//Room
    
    /**
     * 2e constructeur de la classe 
     * @param pName correspond au nom de la pièce
     * @param pDescription correspond à la  description de la pièce
     * @param pImage correspond au nom de l'image de la pièce
     */
    public Room(final String pName, final String pDescription, final String pImage)
    {
        this(pName, pDescription, pImage, "None");
    }//Room
    
    /**
     * @return l'item qu'on veut récupérer
     * 
     */
    public ItemList getItems()
    {
        return this.aItems;
    }
    
    /**
     * @return qui retourne l'item
     * @param pItem correspond à l'item
     */
    public Item getItem(final String pItem)
    {
        return this.aItems.getItems().get(pItem);
    }
    
    /**
     * Définit les sorties possibles de la Room courante. Les directions
     * envoient soit à une autre Room soit est null (Room inexistante)
     * @param pDirection correspond à la direction de la sortie
     * @param pNeighbor correspond à la Room sortante
     */
     public void setExit(final String pDirection, final Room pNeighbor)
     {
        this.aExits.put(pDirection, pNeighbor);
     }
     
     /**
     * @return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return this.aDescription;
    }
    
    /**
     * @return la room à la sortie de la room courante dans la direction 
     * "pDirection". S'il n'y a pas de Room, on retourne null.
     * @param pDirection correspond à la direction de la sortie voulue
     */     
    public Room getExit(final String pDirection)
    {
        return this.aExits.get(pDirection);
    }
  
    /**
     * Retourne la description des sorties de la Room courante
     * par exemple "Exits: North West".
     * @return La description des sorties possibles.
     */
    
    public String getExitString()
    {    
        StringBuilder vReturnString = new StringBuilder("Exits: ");
        for(String vS : this.aExits.keySet()){
            vReturnString.append(" " + vS);
        }
        return vReturnString.toString();
    }
    
    /**
     * Retourne une longue description de la Room, de la forme :
     *  You are in the kichen.
     *      Exits: north west
     *  @return Une description de la Room, incluant les sorties.
     */
    public String getLongDescription()
    {
        return "You are " + this.aDescription + ",\n" + this.getExitString()+ 
        ",\n" + this.aItems.getBagString();
    }
    
    /**
     * @return une String décrivant le nom de l'image de la room
     * 
     */
    public String getImageName()
    {
        return this.aImageName;
    }
    
    /**
     * @return le nom de la room
     */
    public String getName()
    {
        return this.aName;
    }
    
    /**
     * @return le nombre de passages dans la room courante
     */
    public int getPassage()
    {
        return this.aPassage;
    }
    
    /**
     * @return l'item nécessaire pour l'accès à la room
     */
    public String getItemNeeded()
    {
        return this.aItemNeeded;
    }
    
    /**
     * ajoute de 1 le nombre de passages dans la room
     */
    public void addPassage()
    {
        this.aPassage+=1;
    }
    
    /**
     * @return true si la pièce vers laquelle on souhaite aller n'est pas une trap door, 
     * false sinon
     * @param pRoom la room vers laquelle on souhaite se diriger
     */
    public boolean isExit(final Room pRoom)
    {
        return this.aExits.containsValue(pRoom);
    }
    
} // Room
