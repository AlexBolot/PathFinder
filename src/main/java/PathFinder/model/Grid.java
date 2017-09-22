package PathFinder.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Grid	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 22/09/17 13:55
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Grid
{
    private PriorityQueue<Cell> listToExplore = new PriorityQueue<Cell>(new Comparator<Cell>()
    {
        public int compare (Cell c1, Cell c2)
        {
            return c1.compareTo(c2);
        }
    });
    private Cell            depart;
    private Cell            arrivee;
    private int             width;
    private int             height;
    private ArrayList<Cell> listExplored;
    private ArrayList<Cell> listSolution;
    private ListManager     listManager;

    public Grid (Cell depart, Cell arrivee, int width, int height, ListManager listManager)
    {
        setDepart(depart);
        setArrivee(arrivee);
        setWidth(width);
        setHeight(height);
        setListToExplore(new ArrayList<Cell>());
        setListExplored(new ArrayList<Cell>());
        setListManager(listManager);
        listSolution = new ArrayList<Cell>();
        
        listToExplore.add(depart);
    }
    
    //region Getters Setters
    private void setDepart (Cell depart)
    {
        this.depart = depart;
    }

    private void setArrivee (Cell arrivee)
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

    private void setListToExplore (ArrayList<Cell> listToExplore)
    {
        this.listToExplore = new PriorityQueue<Cell>();
        this.listToExplore.addAll(listToExplore);
    }

    private void setListExplored (ArrayList<Cell> listExplored)
    {
        this.listExplored = new ArrayList<Cell>();
        this.listExplored.addAll(listExplored);
    }
    
    private void setListManager (ListManager listManager)
    {
        this.listManager = listManager;
    }
    //endregion

    public ArrayList<Cell> Solve ()
    {
        Cell opportunite = listToExplore.remove();
        listExplored.add(opportunite);
        
        while (!opportunite.equals(arrivee))
        {
            int col = opportunite.getColumn();
            int row = opportunite.getRow();
            int valG = opportunite.getValueG();

            Cell gauche;
            Cell droite;
            Cell bas;
            Cell haut;

            ArrayList<Cell> listNewCells = new ArrayList<Cell>();
            
            if(opportunite.equals(depart))
            {
                //region ( 0 , -1 )
                if(isValid(col, row - 1))
                {
                    bas = new Cell(col, row - 1, valG + poidsDeCase(col, row - 1), opportunite, arrivee);
                    listNewCells.add(bas);
                }
                //endregion
                
                //region ( 0 , +1 )
                if(isValid(col, row + 1))
                {
                    haut = new Cell(col, row + 1, valG + poidsDeCase(col, row + 1), opportunite, arrivee);
                    listNewCells.add(haut);
                }
                //endregion
                
                //region ( +1 , 0 )
                if(isValid(col + 1, row))
                {
                    droite = new Cell(col + 1, row, valG + poidsDeCase(col + 1, row), opportunite, arrivee);
                    listNewCells.add(droite);
                }
                //endregion
                
                //region ( -1 , 0 )
                if(isValid(col - 1, row))
                {
                    gauche = new Cell(col - 1, row, valG + poidsDeCase(col - 1, row), opportunite, arrivee);
                    listNewCells.add(gauche);
                }
                //endregion
            }
            else
            {
                Cell parent = opportunite.getParent();
                
                //region ( 0 , -1 )
                if(isValid(col, row - 1) && !parent.equals(col, row - 1))
                {
                    bas = new Cell(col, row - 1, valG + poidsDeCase(col, row - 1), opportunite, arrivee);

                    listNewCells.add(bas);
                }
                //endregion
                
                //region ( 0 , +1 )
                if(isValid(col, row + 1) && !parent.equals(col, row + 1))
                {
                    haut = new Cell(col, row + 1, valG + poidsDeCase(col, row + 1), opportunite, arrivee);
                    listNewCells.add(haut);
                }
                //endregion
                
                //region ( +1 , 0 )
                if(isValid(col + 1, row) && !parent.equals(col + 1, row))
                {
                    droite = new Cell(col + 1, row, valG + poidsDeCase(col + 1, row), opportunite, arrivee);
                    listNewCells.add(droite);
                    
                }
                //endregion
                
                //region ( -1 , 0 )
                if(isValid(col - 1, row) && !parent.equals(col - 1, row))
                {
                    gauche = new Cell(col - 1, row, valG + poidsDeCase(col - 1, row), opportunite, arrivee);
                    listNewCells.add(gauche);
                }
                //endregion
            }

            listToExplore.addAll(listNewCells);
            
            opportunite = listToExplore.remove();
            listExplored.add(opportunite);
        }
        
        getListSolution(opportunite);
        
        return listSolution;
    }

    private void getListSolution (Cell c)
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
        CellType typeMur = CellType.MUR;
        
        if(Column < 0) return false;
        if(Column > width) return false;
        if(Row < 0) return false;
        if(Row > height) return false;
        if (listExplored.contains(new Cell(Column, Row))) return false;
        if(listManager.containsKey(typeMur))
        {
            if (listManager.get(typeMur).contains(new Cell(Column, Row))) return false;
        }
        return true;
    }
    
    private int poidsDeCase (int Column, int Row)
    {
        for (CellType type : listManager.keySet())
        {
            if (listManager.get(type).contains(new Cell(Column, Row)))
            {
                return listManager.get(type).getPoids();
            }
        }
        
        return 1;
    }
}