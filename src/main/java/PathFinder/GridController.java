package PathFinder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GridController
{
    private final String Rouge      = "FireBrick";
    private final String Vert       = "LimeGreen";
    private final String Bleu       = "CornflowerBlue";
    private final String Noir       = "Black";
    private final int    GridWidth  = 19;
    private final int    GridHeight = 14;
    
    @FXML
    private GridPane gridPane;
    
    @FXML
    public void initialize () throws IOException
    {
        
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
    
    public void Reset_onAction (ActionEvent actionEvent)
    {
        for (Node node : gridPane.getChildren())
        {
            if(node instanceof TextField)
            {
                //region Get char c
                char c;
                
                if(((TextField) node).getText().isEmpty())
                {
                    c = ' ';
                }
                else
                {
                    c = ((TextField) node).getText().toLowerCase().charAt(0);
                }
                //endregion
                
                switch (c)
                {
                    case 'd':
                        break;
                    
                    case 'a':
                        break;
                    
                    default:
                        node.setStyle(null);
                        ((TextField) node).setText("");
                        break;
                }
            }
        }
    }
    
    public void Solve_onAction (ActionEvent actionEvent)
    {
        Case depart;
        Case arrivee;
        
        int departCol = 0;
        int departRow = 0;
        
        int arriveeCol = GridHeight;
        int arriveeRow = GridWidth;
        
        ArrayList<Case> listMurs = new ArrayList<Case>();
        
        for (Node node : gridPane.getChildren())
        {
            if(node instanceof TextField)
            {
                //region Get char c
                char c;
                
                if(((TextField) node).getText().isEmpty())
                {
                    c = ' ';
                }
                else
                {
                    c = ((TextField) node).getText().toLowerCase().charAt(0);
                }
                //endregion
                
                switch (c)
                {
                    case 'd':
                        departCol = GridPane.getColumnIndex(node);
                        departRow = GridPane.getRowIndex(node);
                        colorNode(node, Vert);
                        ((TextField) node).setText("D");
                        break;
                    
                    case 'a':
                        arriveeCol = GridPane.getColumnIndex(node);
                        arriveeRow = GridPane.getRowIndex(node);
                        colorNode(node, Rouge);
                        ((TextField) node).setText("A");
                        break;
                    
                    case ' ':
                        node.setStyle(null);
                        ((TextField) node).setText("");
                        break;
                    
                    case 'm':
                        int murCol = GridPane.getColumnIndex(node);
                        int murRow = GridPane.getRowIndex(node);
                        
                        listMurs.add(new Case(murCol, murRow));
                        
                        colorNode(node, Noir);
                        ((TextField) node).setText("M");
                        break;
                    
                    default:
                        node.setStyle(null);
                        ((TextField) node).setText("");
                        break;
                }
            }
        }
        
        arrivee = new Case(arriveeCol, arriveeRow);
        depart = new Case(departCol, departRow, arrivee);
        
        ArrayList<Case> solutions = new Grid(depart, arrivee, GridWidth, GridHeight, listMurs).Solve();
    
        Collections.reverse(solutions);
        int stepCount = 0;
        
        for (Case c : solutions)
        {
            if(c != null)
            {
                Node node = cell(c.getColumn(), c.getRow());
                colorNode(node, Bleu, stepCount++ + "");
                
                System.out.println(c);
            }
        }
        
        System.out.println("=== Fini ===");
    }
    
    private void colorNode (Node node, String couleur)
    {
        if(node instanceof TextField)
        {
            node.setStyle("-fx-background-color: " + couleur + ";");
        }
    }
    
    private void colorNode (Node node, String couleur, String text)
    {
        if(node instanceof TextField)
        {
            node.setStyle("-fx-background-color: " + couleur + ";");
            ((TextField) node).setText(text);
        }
    }
    
}