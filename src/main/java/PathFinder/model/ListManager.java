package PathFinder.model;

import java.util.HashMap;
import java.util.Set;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ListManager	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 22/09/17 13:55
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ListManager
{
    private HashMap<CellType, ListCase> listManager = new HashMap<CellType, ListCase>();
    
    public ListManager ()
    {
        listManager.put(CellType.DEPART, new ListCase("D", "LimeGreen", 1));
        listManager.put(CellType.ARRIVEE, new ListCase("A", "FireBrick", 1));
        listManager.put(CellType.MUR, new ListCase("M", "Black", Integer.MAX_VALUE));
        listManager.put(CellType.BUISSON, new ListCase("B", "Sienna", 2));
        listManager.put(CellType.EAU, new ListCase("E", "DarkCyan", 3));
    }

    public void put (CellType cellType, ListCase listCase)
    {
        if (listManager.containsKey(cellType)) return;
        if(listManager.containsValue(listCase)) return;

        listManager.put(cellType, listCase);
    }

    public ListCase get (CellType cellType)
    {
        return listManager.get(cellType);
    }

    public Set<CellType> keySet ()
    {
        return listManager.keySet();
    }

    public boolean containsKey (CellType typeMur)
    {
        return listManager.containsKey(typeMur);
    }
}
