package PathFinder;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/*................................................................................................................................
 . Copyright (c)
 .
 . The GridController	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 06/02/17 11:40
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings("FieldCanBeLocal")
public class GridController
{
    private final String RED       = "FireBrick";
    private final String GREEN     = "LimeGreen";
    private final String BLUE      = "CornflowerBlue";
    private final String DARK_BLUE = "DarkCyan";
    private final String BLACK     = "Black";
    private final String BROWN     = "Sienna";
    
    private final int GridWidth  = 19;
    private final int GridHeight = 14;
    
    private HashMap<CaseType, ListCase> listManager = new HashMap<CaseType, ListCase>();
    
    @FXML
    private GridPane gridPane;
    
    @SuppressWarnings("unused")
    @FXML
    public void initialize ()
    {
        listManager.put(CaseType.DEPART, new ListCase("D", GREEN, 1));
        listManager.put(CaseType.ARRIVEE, new ListCase("A", RED, 1));
        listManager.put(CaseType.MUR, new ListCase("M", BLACK, Integer.MAX_VALUE));
        listManager.put(CaseType.BUISSON, new ListCase("B", BROWN, 2));
        listManager.put(CaseType.EAU, new ListCase("E", DARK_BLUE, 3));
    }
    
    private Node cell (int col, int row)
    {
        for (Node node : gridPane.getChildren())
        {
            if(GridPane.getColumnIndex(node) == col && GridPane.getRowIndex(node) == row)
            {
                return node;
            }
        }
        return null;
    }
    
    public void GenerateWalls_onAction ()
    {
        Reset_onAction();
        
        for (Case caseMur : listManager.get(CaseType.MUR).get())
        {
            Node node = cell(caseMur.getColumn(), caseMur.getRow());
            clearNode(node);
        }
        
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(40, 50); i++)
        {
            int Col = ThreadLocalRandom.current().nextInt(0, GridWidth + 1);
            int Row = ThreadLocalRandom.current().nextInt(0, GridHeight + 1);
            
            Node node = cell(Col, Row);
            
            boolean caseAvailable = true;
            
            for (CaseType type : listManager.keySet())
            {
                if(listManager.get(type).contains(new Case(Col, Row)))
                {
                    caseAvailable = false;
                }
            }
            
            if(caseAvailable)
            {
                ListCase listMurs = listManager.get(CaseType.MUR);
                
                listMurs.Add(new Case(Col, Row));
                colorNode(node, listMurs.getCouleur(), listMurs.getLogo());
            }
        }
    }
    
    public void Reset_onAction ()
    {
        for (Node node : gridPane.getChildren())
        {
            boolean isErasable = true;
            
            if(node instanceof TextField)
            {
                String cellValue = ((TextField) node).getText();
                String cellColor = "";
                
                if(node.getStyle().length() > 22)
                {
                    cellColor = node.getStyle().substring(22, node.getStyle().length() - 1);
                }
                
                for (CaseType type : listManager.keySet())
                {
                    ListCase listCase = listManager.get(type);
                    
                    boolean isSameLogo = listCase.getLogo().equalsIgnoreCase(cellValue);
                    boolean hasText = !cellValue.isEmpty();
                    boolean isSameColor = listCase.getCouleur().equalsIgnoreCase(cellColor);
                    
                    if(isSameLogo || (hasText && isSameColor))
                    {
                        isErasable = false;
                        colorNode(node, listCase.getCouleur(), listCase.getLogo());
                    }
                }
                
                if(isErasable) clearNode(node);
            }
        }
        PopulateListManager();
    }
    
    public void Solve_onAction ()
    {
        PopulateListManager();
        
        Case aa = listManager.get(CaseType.ARRIVEE).getFirst();
        
        System.out.println(aa);
        
        Case arrivee = listManager.get(CaseType.ARRIVEE).getFirst();
        Case depart = listManager.get(CaseType.DEPART).getFirst();
        depart.setArrivee(arrivee);
        
        ArrayList<Case> solutions = new Grid(depart, arrivee, GridWidth, GridHeight, listManager).Solve();
        
        Collections.reverse(solutions);
        int stepCount = 0;
        
        for (Case c : solutions)
        {
            if(c != null)
            {
                Node node = cell(c.getColumn(), c.getRow());
                
                boolean isCaseSpecial = false;
                
                for (CaseType type : listManager.keySet())
                {
                    ListCase listCase = listManager.get(type);
                    
                    if(listCase.contains(c))
                    {
                        isCaseSpecial = true;
                        stepCount += listCase.getPoids();
                        colorNode(node, listCase.getCouleur(), stepCount + "");
                    }
                }
                
                if(!isCaseSpecial)
                {
                    colorNode(node, BLUE, ++stepCount + "");
                }
                
                System.out.println(c);
            }
        }
        
        System.out.println("=== Fini ===");
    }
    
    private void colorNode (Node node, String couleur, String text)
    {
        if(node instanceof TextField)
        {
            node.setStyle("-fx-background-color: " + couleur + ";");
            ((TextField) node).setText(text);
        }
    }
    
    private void clearNode (Node node)
    {
        if(node instanceof TextField)
        {
            node.setStyle(null);
            ((TextField) node).setText("");
        }
    }
    
    private void PopulateListManager ()
    {
        ClearListManager();
        
        for (Node node : gridPane.getChildren())
        {
            if(node instanceof TextField)
            {
                String cellValue = ((TextField) node).getText();
                
                for (CaseType type : listManager.keySet())
                {
                    ListCase listCase = listManager.get(type);
                    
                    if(listCase.getLogo().equalsIgnoreCase(cellValue))
                    {
                        int Col = GridPane.getColumnIndex(node);
                        int Row = GridPane.getRowIndex(node);
                        
                        listCase.Add(new Case(Col, Row));
                        colorNode(node, listCase.getCouleur(), listCase.getLogo());
                    }
                }
            }
        }
    }
    
    private void ClearListManager ()
    {
        for (CaseType type : listManager.keySet())
        {
            listManager.get(type).clear();
        }
    }
}