import java.util.Set;
import java.util.HashMap;
/**
 * Décrivez votre classe ItemList ici.
 *
 * @author PHAM Jimmy
 * @version 7.43
 */
public class ItemList
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private HashMap <String, Item> aItemsBag;
    private String aLocation;
    /**
     * Constructeur d'objets de classe ItemList
     * @param pLocation l'appartenance de l'objet
     */
    public ItemList(final String pLocation)
    {
        // initialisation des variables d'instance
        this.aItemsBag = new HashMap<String, Item>();
        this.aLocation = pLocation;
    }
    
    /**
     * @return l'item désigné
     * @param pItem l'item sous forme de String
     */
    public Item getItem (final String pItem)
    {
        return this.aItemsBag.get(pItem);
    }
    
    /**
     * @return l'attribut aItemsBag
     */
    public HashMap <String, Item> getItems ()
    {
        return aItemsBag;
    }
    
    /**
     * @return l'ensemble des items de la liste
     */
    public String getBagString ()
    {
        if (this.aItemsBag.isEmpty()){
            return "There is no items in " + this.aLocation;
        }// if ()
        
        StringBuilder vChaine = new StringBuilder ("Items in " + this.aLocation + " :");
        Set <String> vItemsNames = this.aItemsBag.keySet();
        for (String vName : vItemsNames){
            vChaine.append(" " + vName +", ");
        }// for ()
        return vChaine.toString();
    }// getItemString ()
    
    /**
     * Ajoute un item à la HashMap
     * 
     * @param pName Clef de la HashMap (nom de l'item)
     * @param pItem Item à insérer dans la HashMap
     */
    public void addItem(final String pName, final Item pItem){
        this.aItemsBag.put(pName, pItem);
    }// addItem ()
    
    /**
     * Supprime un item de la HashMap
     * @param pName Clef de la HashMap (nom de l'item)
     * @param pItem l'item qu'on veut enlever
     */
    public void removeItem (final String pName, final Item pItem){
        this.aItemsBag.remove(pName);
    }// removeItem ()
    
    public boolean verifItem(final String pItem)
    {
        if (this.getItem(pItem)==null)
            return false;
        else
            return true;
    }
}