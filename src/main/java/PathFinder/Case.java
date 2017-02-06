package PathFinder;

/*................................................................................................................................
 . Copyright (c)
 .
 . The Case	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 27/01/17 14:56
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class Case
{
    private int valueG;
    private int valueH;
    private int valueF;
    
    private Case parent;
    
    private int row;
    private int column;
    
    //region Constructors
    
    Case (int Column, int Row)
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
    int getValueG ()
    {
        return valueG;
    }
    
    int getValueF ()
    {
        return valueF;
    }
    
    int getColumn ()
    {
        return column;
    }
    
    int getRow ()
    {
        return row;
    }
    
    Case getParent ()
    {
        return parent;
    }
    
    void setArrivee (Case c)
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
    
    boolean equals (int col, int row)
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
}