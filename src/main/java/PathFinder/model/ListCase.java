package PathFinder.model;

import java.util.ArrayList;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ListCase	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 22/09/17 13:55
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ListCase
{
    private String logo;
    private String couleur;
    private int    poids;

    private ArrayList<Cell> cells;
    
    public ListCase (String Logo, String Couleur, int Poids)
    {
        logo = Logo;
        couleur = Couleur;
        poids = Poids;
        cells = new ArrayList<Cell>();
    }

    public void Add (Cell c)
    {
        cells.add(c);
    }

    public ArrayList<Cell> get ()
    {
        return cells;
    }

    public Cell getFirst ()
    {
        return cells.get(0);
    }

    public Boolean contains (Cell c)
    {
        return cells.contains(c);
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
        cells.clear();
    }
}
