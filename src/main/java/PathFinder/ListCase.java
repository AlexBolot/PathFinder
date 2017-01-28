package PathFinder;

import java.util.ArrayList;

public class ListCase
{
    private ArrayList<Case> cases;
    private String          logo;
    private String          couleur;
    
    
    ListCase (String Logo, String Couleur)
    {
        logo = Logo;
        couleur = Couleur;
        cases = new ArrayList<Case>();
    }
    
    void Add (Case c)
    {
        cases.add(c);
    }
    
    public ArrayList<Case> Get ()
    {
        return cases;
    }
    
    public Case GetFirst ()
    {
        return cases.get(0);
    }
    
    Boolean Contains (Case c)
    {
        return cases.contains(c);
    }
    
    String getLogo ()
    {
        return logo;
    }
    
    String getCouleur ()
    {
        return couleur;
    }
    
    void Clear ()
    {
        cases.clear();
    }
}
