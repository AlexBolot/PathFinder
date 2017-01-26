package PathFinder;

import java.util.ArrayList;

public class Grid
{
    private Case            depart;
    private Case            arrivee;
    private int             width;
    private int             height;
    private ArrayList<Case> listToExplore;
    private ArrayList<Case> listExplored;
    private ArrayList<Case> listMurs;
    private ArrayList<Case> listSolution = new ArrayList<Case>();
    
    Grid (Case Depart, Case Arrivee, int Width, int Height, ArrayList<Case> ListMurs)
    {
        depart = Depart;
        arrivee = Arrivee;
        width = Width;
        height = Height;
        listToExplore = new ArrayList<Case>();
        listExplored = new ArrayList<Case>();
        listMurs = ListMurs;
        
        listToExplore.add(depart);
    }
    
    //region Getters
    public Case getDepart ()
    {
        return depart;
    }
    
    //region Setters
    public void setDepart (Case Depart)
    {
        depart = Depart;
    }
    
    public Case getArrivee ()
    {
        return arrivee;
    }
    
    public void setArrivee (Case Arrivee)
    {
        arrivee = Arrivee;
    }
    //endregion
    
    public ArrayList<Case> getListToExplore ()
    {
        return listToExplore;
    }
    
    public void setListToExplore (ArrayList<Case> ListToExplore)
    {
        listToExplore = ListToExplore;
    }
    
    public ArrayList<Case> getListExplored ()
    {
        return listExplored;
    }
    
    public void setListExplored (ArrayList<Case> ListExplored)
    {
        listExplored = ListExplored;
    }
    //endregion
    
    ArrayList<Case> Solve ()
    {
        Case opportunite = getLowerF(listToExplore);
        
        listToExplore.remove(opportunite);
        listExplored.add(opportunite);
        
        if(!opportunite.equals(arrivee))
        {
            int col = opportunite.getColumn();
            int row = opportunite.getRow();
            int valG = opportunite.getValueG();
            
            Case gauche;
            Case droite;
            Case bas;
            Case haut;
            
            ArrayList<Case> listNewCases = new ArrayList<Case>();
            
            if(opportunite.equals(depart))
            {
                //region ...
                if(isValid(col - 1, row))
                {
                    gauche = new Case(col - 1, row, valG + 1, opportunite, arrivee);
                    listNewCases.add(gauche);
                }
                if(isValid(col + 1, row))
                {
                    droite = new Case(col + 1, row, valG + 1, opportunite, arrivee);
                    listNewCases.add(droite);
                }
                if(isValid(col, row - 1))
                {
                    bas = new Case(col, row - 1, valG + 1, opportunite, arrivee);
                    listNewCases.add(bas);
                }
                if(isValid(col, row + 1))
                {
                    haut = new Case(col, row + 1, valG + 1, opportunite, arrivee);
                    listNewCases.add(haut);
                }
                //endregion
            }
            else
            {
                //region ...
                Case parent = opportunite.getParent();
                
                //( 0 , -1 )
                if(isValid(col, row - 1) && !parent.equals(col, row - 1))
                {
                    bas = new Case(col, row - 1, valG + 1, opportunite, arrivee);
                    listNewCases.add(bas);
                }
                
                //( 0 , +1 )
                if(isValid(col, row + 1) && !parent.equals(col, row + 1))
                {
                    haut = new Case(col, row + 1, valG + 1, opportunite, arrivee);
                    listNewCases.add(haut);
                }
                
                //( +1 , 0 )
                if(isValid(col + 1, row) && !parent.equals(col + 1, row))
                {
                    droite = new Case(col + 1, row, valG + 1, opportunite, arrivee);
                    listNewCases.add(droite);
                    
                }
                
                //( -1 , 0 )
                if(isValid(col - 1, row) && !parent.equals(col - 1, row))
                {
                    gauche = new Case(col - 1, row, valG + 1, opportunite, arrivee);
                    listNewCases.add(gauche);
                }
                //endregion
            }
            
            listToExplore.addAll(listNewCases);
            
            Solve();
        }
        else
        {
            getLisSolution(opportunite);
        }
        return listSolution;
    }
    
    private void getLisSolution (Case c)
    {
        if(!c.getParent().equals(depart))
        {
            if(!c.equals(arrivee))
            {
                listSolution.add(c);
            }
            getLisSolution(c.getParent());
        }
        else
        {
            listSolution.add(c);
        }
    }
    
    private Case getLowerF (ArrayList<Case> listOfCases)
    {
        Case reference = listOfCases.get(0);
        
        for (Case c : listOfCases)
        {
            if(c.getValueF() < reference.getValueF())
            {
                reference = c.clone();
            }
        }
        
        return reference;
    }
    
    private Boolean isValid (int Column, int Row)
    {
        if(Column < 0) return false;
        if(Column > width) return false;
        if(Row < 0) return false;
        if(Row > height) return false;
        if(listMurs.contains(new Case(Column, Row))) return false;
        if(listExplored.contains(new Case(Column, Row))) return false;
        return true;
    }
}