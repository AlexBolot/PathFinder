package PathFinder.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Grid	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 12/04/17 00:01
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Grid
{
    PriorityQueue<Case> listToExplore = new PriorityQueue<Case>(new Comparator<Case>()
    {
        public int compare (Case c1, Case c2)
        {
            return c1.compareTo(c2);
        }
    });
    private Case            depart;
    private Case            arrivee;
    private int             width;
    private int             height;
    private ArrayList<Case> listExplored;
    private ArrayList<Case> listSolution;
    private ListManager     listManager;
    
    public Grid (Case depart, Case arrivee, int width, int height, ListManager listManager)
    {
        setDepart(depart);
        setArrivee(arrivee);
        setWidth(width);
        setHeight(height);
        setListToExplore(new ArrayList<Case>());
        setListExplored(new ArrayList<Case>());
        setListManager(listManager);
        listSolution = new ArrayList<Case>();
        
        listToExplore.add(depart);
    }
    
    //region Getters Setters
    private void setDepart (Case depart)
    {
        this.depart = depart;
    }
    
    private void setArrivee (Case arrivee)
    {
        this.arrivee = arrivee;
    }
    
    private void setWidth (int width)
    {
        this.width = width;
    }
    
    private void setHeight (int height)
    {
        this.height = height;
    }
    
    private void setListToExplore (ArrayList<Case> listToExplore)
    {
        this.listToExplore = new PriorityQueue<Case>();
        this.listToExplore.addAll(listToExplore);
    }
    
    private void setListExplored (ArrayList<Case> listExplored)
    {
        this.listExplored = new ArrayList<Case>();
        this.listExplored.addAll(listExplored);
    }
    
    private void setListManager (ListManager listManager)
    {
        this.listManager = listManager;
    }
    //endregion
    
    public ArrayList<Case> Solve ()
    {
        Case opportunite = listToExplore.remove();
        listExplored.add(opportunite);
        
        while (!opportunite.equals(arrivee))
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
                //region ( 0 , -1 )
                if(isValid(col, row - 1))
                {
                    bas = new Case(col, row - 1, valG + poidsDeCase(col, row - 1), opportunite, arrivee);
                    listNewCases.add(bas);
                }
                //endregion
                
                //region ( 0 , +1 )
                if(isValid(col, row + 1))
                {
                    haut = new Case(col, row + 1, valG + poidsDeCase(col, row + 1), opportunite, arrivee);
                    listNewCases.add(haut);
                }
                //endregion
                
                //region ( +1 , 0 )
                if(isValid(col + 1, row))
                {
                    droite = new Case(col + 1, row, valG + poidsDeCase(col + 1, row), opportunite, arrivee);
                    listNewCases.add(droite);
                }
                //endregion
                
                //region ( -1 , 0 )
                if(isValid(col - 1, row))
                {
                    gauche = new Case(col - 1, row, valG + poidsDeCase(col - 1, row), opportunite, arrivee);
                    listNewCases.add(gauche);
                }
                //endregion
            }
            else
            {
                Case parent = opportunite.getParent();
                
                //region ( 0 , -1 )
                if(isValid(col, row - 1) && !parent.equals(col, row - 1))
                {
                    bas = new Case(col, row - 1, valG + poidsDeCase(col, row - 1), opportunite, arrivee);
                    
                    listNewCases.add(bas);
                }
                //endregion
                
                //region ( 0 , +1 )
                if(isValid(col, row + 1) && !parent.equals(col, row + 1))
                {
                    haut = new Case(col, row + 1, valG + poidsDeCase(col, row + 1), opportunite, arrivee);
                    listNewCases.add(haut);
                }
                //endregion
                
                //region ( +1 , 0 )
                if(isValid(col + 1, row) && !parent.equals(col + 1, row))
                {
                    droite = new Case(col + 1, row, valG + poidsDeCase(col + 1, row), opportunite, arrivee);
                    listNewCases.add(droite);
                    
                }
                //endregion
                
                //region ( -1 , 0 )
                if(isValid(col - 1, row) && !parent.equals(col - 1, row))
                {
                    gauche = new Case(col - 1, row, valG + poidsDeCase(col - 1, row), opportunite, arrivee);
                    listNewCases.add(gauche);
                }
                //endregion
            }
            
            listToExplore.addAll(listNewCases);
            
            opportunite = listToExplore.remove();
            listExplored.add(opportunite);
        }
        
        getListSolution(opportunite);
        
        return listSolution;
    }
    
    private void getListSolution (Case c)
    {
        if(!c.getParent().equals(depart))
        {
            if(!c.equals(arrivee))
            {
                listSolution.add(c);
            }
            getListSolution(c.getParent());
        }
        else
        {
            listSolution.add(c);
        }
    }
    
    private Boolean isValid (int Column, int Row)
    {
        CaseType typeMur = CaseType.MUR;
        
        if(Column < 0) return false;
        if(Column > width) return false;
        if(Row < 0) return false;
        if(Row > height) return false;
        if(listExplored.contains(new Case(Column, Row))) return false;
        if(listManager.containsKey(typeMur))
        {
            if(listManager.get(typeMur).contains(new Case(Column, Row))) return false;
        }
        return true;
    }
    
    private int poidsDeCase (int Column, int Row)
    {
        for (CaseType type : listManager.keySet())
        {
            if(listManager.get(type).contains(new Case(Column, Row)))
            {
                return listManager.get(type).getPoids();
            }
        }
        
        return 1;
    }
}