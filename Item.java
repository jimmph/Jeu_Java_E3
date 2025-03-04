
/**
 * La classe Item permet au joueur d'accéder à différents objets dans le jeu,
 * qui lui seront utiles pour avancer et terminer le jeu.
 *
 * @author Pham Jimmy
 * @version  7.43
 */
public class Item
{
    // variables d'instance - remplacez l'exemple qui suit par le vôtre
    private String aName;
    private String aDescription;
    private double aWeight;
    /**
     * Constructeur d'objets de classe Item
     * @param pName correspond au nom de l'item 
     * @param pDescription correspond à la description de l'item
     * @param pWeight correspond au poids de l'item
     */
    
    public Item(final String pName, final String pDescription, final double pWeight)
    {
        this.aName = pName;
        this.aDescription = pDescription;
        this.aWeight = pWeight;
    }
    
    /**
     * @return le nom de l'item
     */
    public String getName()
    {
        // initialisation des variables d'instance
        return this.aName;
    }
    
    /**
     * @return la description de l'item
     */
    public String getDescription()
    {
        // initialisation des variables d'instance
        return this.aDescription;
    }
    
    /**
     * @return le poids de l'item
     */
    public double getWeight()
    {
        // initialisation des variables d'instance
        return this.aWeight;
    }

    /**
     * @return le nom, la description et le poids de l'item.
     */
    public String getLongItemDescription()
    {
        // initialisation des variables d'instance
        return this.aName+ " ("+this.aDescription+", "+this.aWeight+")";
    }
    
    /**
     * @return le nom et le poids de l'item.
     */
    public String getItemDescription()
    {
        // initialisation des variables d'instance
        return this.aName+ " ("+this.aWeight+")";
    }

    
}
