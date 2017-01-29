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
 . Last Modified : 28/01/17 18:11
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class GridController
{
    private final String                    Rouge       = "FireBrick";
    private final String                    Vert        = "LimeGreen";
    private final String                    Bleu        = "CornflowerBlue";
    private final String                    Noir        = "Black";
    private final String                    Marron      = "Brown";
    private final int                       GridWidth   = 19;
    private final int                       GridHeight  = 14;
    private       HashMap<String, ListCase> listManager = new HashMap<String, ListCase>();
    
    @FXML
    private GridPane gridPane;
    
    @FXML
    public void initialize ()
    {
        listManager.put(CaseType.Depart, new ListCase("D", Vert));
        listManager.put(CaseType.Arrivee, new ListCase("A", Rouge));
        listManager.put(CaseType.Mur, new ListCase("M", Noir));
        listManager.put(CaseType.Boue, new ListCase("B", Marron));
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
        
        for (int i = 0; i < ThreadLocalRandom.current().nextInt(40, 50); i++)
        {
            int Col = ThreadLocalRandom.current().nextInt(0, GridWidth + 1);
            int Row = ThreadLocalRandom.current().nextInt(0, GridHeight + 1);
            
            Node node = cell(Col, Row);
            
            for (String listName : listManager.keySet())
            {
                if(!listManager.get(listName).Contains(new Case(Col, Row)))
                {
                    ListCase listMurs = listManager.get(CaseType.Mur);
                    
                    listMurs.Add(new Case(Col, Row));
                    colorNode(node, listMurs.getCouleur(), listMurs.getLogo());
                }
            }
        }
    }
    
    public void Reset_onAction ()
    {
        PopulateListManager();
    }
    
    public void Solve_onAction ()
    {
        PopulateListManager();
        
        Case aa = listManager.get(CaseType.Arrivee).GetFirst();
        
        System.out.println(aa);
        
        Case arrivee = listManager.get(CaseType.Arrivee).GetFirst();
        Case depart = listManager.get(CaseType.Depart).GetFirst();
        depart.setArrivee(arrivee);
        
        ArrayList<Case> solutions = new Grid(depart, arrivee, GridWidth, GridHeight, listManager).Solve();
        
        Collections.reverse(solutions);
        int stepCount = 0;
        
        for (Case c : solutions)
        {
            if(c != null)
            {
                Node node = cell(c.getColumn(), c.getRow());
    
                if(listManager.get(CaseType.Boue).Contains(c))
                {
                    colorNode(node, Marron, stepCount++ + "");
                }
                else
                {
                    colorNode(node, Bleu, stepCount++ + "");
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
    
    private void PopulateListManager ()
    {
        ClearListManager();
        
        for (Node node : gridPane.getChildren())
        {
            if(node instanceof TextField)
            {
                String cellValue = ((TextField) node).getText();
                
                for (String listName : listManager.keySet())
                {
                    ListCase listCase = listManager.get(listName);
                    
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
        for (String listName : listManager.keySet())
        {
            listManager.get(listName).Clear();
        }
    }
    
}