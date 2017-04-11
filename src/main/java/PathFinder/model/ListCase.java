package PathFinder.model;

import java.util.ArrayList;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ListCase	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 12/04/17 00:02
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ListCase
{
    private String logo;
    private String couleur;
    private int    poids;
    
    private ArrayList<Case> cases;
    
    public ListCase (String Logo, String Couleur, int Poids)
    {
        logo = Logo;
        couleur = Couleur;
        poids = Poids;
        cases = new ArrayList<Case>();
    }
    
    public void Add (Case c)
    {
        cases.add(c);
    }
    
    public ArrayList<Case> get ()
    {
        return cases;
    }
    
    public Case getFirst ()
    {
        return cases.get(0);
    }
    
    public Boolean contains (Case c)
    {
        return cases.contains(c);
    }
    
    public String getLogo ()
    {
        return logo;
    }
    
    public String getCouleur ()
    {
        return couleur;
    }
    
    public int getPoids ()
    {
        return poids;
    }
    
    public void clear ()
    {
        cases.clear();
    }
}
