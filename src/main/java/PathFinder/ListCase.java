package PathFinder;

import java.util.ArrayList;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ListCase	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 29/01/17 01:43
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

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
