package PathFinder.model;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Case	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 12/04/17 00:02
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Case implements Comparable
{
    private int valueG;
    private int valueH;
    private int valueF;
    
    private Case parent;
    
    private int row;
    private int column;
    
    //region Constructors
    
    public Case (int Column, int Row)
    {
        column = Column;
        row = Row;
    }
    
    Case (int Column, int Row, int ValueG, Case Parent, Case Arrivee)
    {
        column = Column;
        row = Row;
        
        valueG = ValueG;
        valueH = getManathanDistance(Arrivee);
        valueF = valueG + valueH;
        parent = Parent;
    }
    
    private Case (int Column, int Row, int ValueG, int ValueH, Case Parent)
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
    
    public Case getParent ()
    {
        return parent;
    }
    
    public void setArrivee (Case c)
    {
        valueF = getManathanDistance(c);
        valueH = valueF + valueG;
    }
    //endregion
    
    private int getManathanDistance (Case c)
    {
        int rowDistance = Math.abs(this.getRow() - c.getRow());
        int columnDistance = Math.abs(this.getColumn() - c.getColumn());
        
        return rowDistance + columnDistance;
    }
    
    @Override
    public boolean equals (Object o)
    {
        if(this == o) return true;
        if(!(o instanceof Case)) return false;
        
        Case aCase = (Case) o;
        
        return getRow() == aCase.getRow() && getColumn() == aCase.getColumn();
    }
    
    public boolean equals (int col, int row)
    {
        return (getColumn() == col) && (getRow() == row);
    }
    
    @SuppressWarnings("MethodDoesntCallSuperMethod")
    @Override
    public Case clone ()
    {
        return new Case(column, row, valueG, valueH, parent);
    }
    
    @Override
    public String toString ()
    {
        return "Case (" + column + "," + row + ")\n\tG = " + valueG + "\n\tH = " + valueH + "\n\tF = " + valueF;//+ "\n\tParent = " + parent;
    }
    
    public int compareTo (Object o)
    {
        Case c1 = this;
        Case c2 = (Case) o;
        
        if(c1.getValueF() < c2.getValueF()) return -1;
        if(c1.getValueF() > c2.getValueF()) return 1;
        return 0;
    }
}