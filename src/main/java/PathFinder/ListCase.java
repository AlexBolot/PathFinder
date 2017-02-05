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

class ListCase
{
    private ArrayList<Case> cases;
    private String          logo;
    private String          couleur;
    private int             poids;
    
    ListCase (String Logo, String Couleur, int Poids)
    {
        logo = Logo;
        couleur = Couleur;
        poids = Poids;
        cases = new ArrayList<Case>();
    }
    
    void Add (Case c)
    {
        cases.add(c);
    }
    
    ArrayList<Case> get ()
    {
        return cases;
    }
    
    Case getFirst ()
    {
        return cases.get(0);
    }
    
    Boolean contains (Case c)
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
    
    int getPoids ()
    {
        return poids;
    }
    
    void clear ()
    {
        cases.clear();
    }
}
