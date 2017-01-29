package PathFinder;

import java.util.ArrayList;
import java.util.HashMap;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Grid	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 27/01/17 23:31
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

class Grid
{
    private Case            depart;
    private Case            arrivee;
    private int             width;
    private int             height;
    private ArrayList<Case> listToExplore;
    private ArrayList<Case> listExplored;
    private ArrayList<Case>           listSolution = new ArrayList<Case>();
    private HashMap<String, ListCase> listManager  = new HashMap<String, ListCase>();
    
    
    Grid (Case Depart, Case Arrivee, int Width, int Height, HashMap<String, ListCase> ListManager)
    {
        depart = Depart;
        arrivee = Arrivee;
        width = Width;
        height = Height;
        listToExplore = new ArrayList<Case>();
        listExplored = new ArrayList<Case>();
        listManager = ListManager;
        
        listToExplore.add(depart);
    }
    
    ArrayList<Case> Solve ()
    {
        Case opportunite = getLowerF(listToExplore);
        
        listToExplore.remove(opportunite);
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
                    bas = new Case(col,
                                   row - 1,
                                   valG + (isOfCaseType(col, row - 1, CaseType.Boue) ? 2 : 1),
                                   opportunite,
                                   arrivee);
                    listNewCases.add(bas);
                }
                //endregion
                
                //region ( 0 , +1 )
                if(isValid(col, row + 1))
                {
                    haut = new Case(col,
                                    row + 1,
                                    valG + (isOfCaseType(col, row + 1, CaseType.Boue) ? 2 : 1),
                                    opportunite,
                                    arrivee);
                    listNewCases.add(haut);
                }
                //endregion
                
                //region ( +1 , 0 )
                if(isValid(col + 1, row))
                {
                    droite = new Case(col + 1,
                                      row,
                                      valG + (isOfCaseType(col + 1, row, CaseType.Boue) ? 2 : 1),
                                      opportunite,
                                      arrivee);
                    listNewCases.add(droite);
                }
                //endregion
                
                //region ( -1 , 0 )
                if(isValid(col - 1, row))
                {
                    gauche = new Case(col - 1,
                                      row,
                                      valG + (isOfCaseType(col - 1, row, CaseType.Boue) ? 2 : 1),
                                      opportunite,
                                      arrivee);
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
                    bas = new Case(col,
                                   row - 1,
                                   valG + (isOfCaseType(col, row - 1, CaseType.Boue) ? 2 : 1),
                                   opportunite,
                                   arrivee);
                    
                    listNewCases.add(bas);
                }
                //endregion
                
                //region ( 0 , +1 )
                if(isValid(col, row + 1) && !parent.equals(col, row + 1))
                {
                    haut = new Case(col,
                                    row + 1,
                                    valG + (isOfCaseType(col, row + 1, CaseType.Boue) ? 2 : 1),
                                    opportunite,
                                    arrivee);
                    listNewCases.add(haut);
                }
                //endregion
                
                //region ( +1 , 0 )
                if(isValid(col + 1, row) && !parent.equals(col + 1, row))
                {
                    droite = new Case(col + 1,
                                      row,
                                      valG + (isOfCaseType(col + 1, row, CaseType.Boue) ? 2 : 1),
                                      opportunite,
                                      arrivee);
                    listNewCases.add(droite);
                    
                }
                //endregion
                
                //region ( -1 , 0 )
                if(isValid(col - 1, row) && !parent.equals(col - 1, row))
                {
                    gauche = new Case(col - 1,
                                      row,
                                      valG + (isOfCaseType(col - 1, row, CaseType.Boue) ? 2 : 1),
                                      opportunite,
                                      arrivee);
                    listNewCases.add(gauche);
                }
                //endregion
            }
            
            listToExplore.addAll(listNewCases);
            
            opportunite = getLowerF(listToExplore);
            
            listToExplore.remove(opportunite);
            listExplored.add(opportunite);
        }
        
        getLisSolution(opportunite);
        
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
        String typeMur = CaseType.Mur;
        
        if(Column < 0) return false;
        if(Column > width) return false;
        if(Row < 0) return false;
        if(Row > height) return false;
        if(listExplored.contains(new Case(Column, Row))) return false;
        if(listManager.containsKey(typeMur))
        {
            if(listManager.get(typeMur).Contains(new Case(Column, Row))) return false;
        }
        return true;
    }
    
    private Boolean isOfCaseType (int Column, int Row, String caseType)
    {
        if(!listManager.containsKey(caseType)) return false;
        return listManager.get(caseType).Contains(new Case(Column, Row));
    }
}