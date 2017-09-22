package PathFinder.model;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Cell	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 22/09/17 13:55
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Cell implements Comparable
{
    private int valueG;
    private int valueH;
    private int valueF;

    private Cell parent;
    
    private int row;
    private int column;
    
    //region Constructors

    public Cell (int Column, int Row)
    {
        column = Column;
        row = Row;
    }

    Cell (int Column, int Row, int ValueG, Cell Parent, Cell Arrivee)
    {
        column = Column;
        row = Row;
        
        valueG = ValueG;
        valueH = getManathanDistance(Arrivee);
        valueF = valueG + valueH;
        parent = Parent;
    }

    private Cell (int Column, int Row, int ValueG, int ValueH, Cell Parent)
    {
        column = Column;
        row = Row;
        
        valueG = ValueG;
        valueH = ValueH;
        valueF = valueG + valueH;
        parent = Parent;
    }
    
    //endregion
    
    //region Getters Setters
    public int getValueG ()
    {
        return valueG;
    }
    
    public int getValueF ()
    {
        return valueF;
    }
    
    public int getColumn ()
    {
        return column;
    }
    
    public int getRow ()
    {
        return row;
    }

    public Cell getParent ()
    {
        return parent;
    }

    public void setArrivee (Cell c)
    {
        valueF = getManathanDistance(c);
        valueH = valueF + valueG;
    }
    //endregion

    private int getManathanDistance (Cell c)
    {
        int rowDistance = Math.abs(this.getRow() - c.getRow());
        int columnDistance = Math.abs(this.getColumn() - c.getColumn());
        
        return rowDistance + columnDistance;
    }
    
    @Override
    public boolean equals (Object o)
    {
        if(this == o) return true;
        if (!(o instanceof Cell)) return false;

        Cell aCell = (Cell) o;

        return getRow() == aCell.getRow() && getColumn() == aCell.getColumn();
    }
    
    public boolean equals (int col, int row)
    {
        return (getColumn() == col) && (getRow() == row);
    }
    
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Cell clone ()
    {
        return new Cell(column, row, valueG, valueH, parent);
    }
    
    @Override
    public String toString ()
    {
        return "Cell (" + column + "," + row + ")\n\tG = " + valueG + "\n\tH = " + valueH + "\n\tF = " + valueF;//+ "\n\tParent = " + parent;
    }
    
    public int compareTo (Object o)
    {
        Cell c1 = this;
        Cell c2 = (Cell) o;
        
        if(c1.getValueF() < c2.getValueF()) return -1;
        if(c1.getValueF() > c2.getValueF()) return 1;
        return 0;
    }
}