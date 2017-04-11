package PathFinder.model;

import java.util.HashMap;
import java.util.Set;

/*................................................................................................................................
 . Copyright (c)
 .
 . The ListManager	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 12/04/17 00:01
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class ListManager
{
    private HashMap<CaseType, ListCase> listManager = new HashMap<CaseType, ListCase>();
    
    public ListManager ()
    {
        listManager.put(CaseType.DEPART, new ListCase("D", "LimeGreen", 1));
        listManager.put(CaseType.ARRIVEE, new ListCase("A", "FireBrick", 1));
        listManager.put(CaseType.MUR, new ListCase("M", "Black", Integer.MAX_VALUE));
        listManager.put(CaseType.BUISSON, new ListCase("B", "Sienna", 2));
        listManager.put(CaseType.EAU, new ListCase("E", "DarkCyan", 3));
    }
    
    public void put (CaseType caseType, ListCase listCase)
    {
        if(listManager.containsKey(caseType)) return;
        if(listManager.containsValue(listCase)) return;
        
        listManager.put(caseType, listCase);
    }
    
    public ListCase get (CaseType caseType)
    {
        return listManager.get(caseType);
    }
    
    public Set<CaseType> keySet ()
    {
        return listManager.keySet();
    }
    
    public boolean containsKey (CaseType typeMur)
    {
        return listManager.containsKey(typeMur);
    }
}
