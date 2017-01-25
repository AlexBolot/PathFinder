package PathFinder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class GridController implements Observer
{
    private final String CouleurRouge = "FireBrick";
    private final String CouleurVert  = "LimeGreen";
    private final String CouleurBleu  = "CornflowerBlue";
    private final int    GridWidth    = 19;
    private final int    GridHeight   = 14;
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
    
    public void Solve_onAction (ActionEvent actionEvent)
    {
        Case depart;
        Case arrivee;
        
        int departRow = 0;
        int departCol = 0;
        
        int arriveeRow = GridWidth;
        int arriveeCol = GridHeight;
        
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
                        departRow = GridPane.getRowIndex(node);
                        departCol = GridPane.getColumnIndex(node);
                        node.setStyle("-fx-background-color: " + CouleurVert + ";");
                        ((TextField) node).setText("D");
                        break;
                    
                    case 'a':
                        arriveeRow = GridPane.getRowIndex(node);
                        arriveeCol = GridPane.getColumnIndex(node);
                        node.setStyle("-fx-background-color: " + CouleurRouge + ";");
                        ((TextField) node).setText("A");
                        break;
                    
                    case ' ':
                        node.setStyle(null);
                        ((TextField) node).setText("");
                        break;
                    
                    case 'm':
                        break;
                    
                    default:
                        break;
                }
            }
        }
        
        arrivee = new Case(arriveeCol, arriveeRow);
        depart = new Case(departCol, departRow, arrivee);
        
        new Grid(depart, arrivee, GridWidth, GridHeight, this).Solve();
        
        System.out.println("=== Fini ===");
    }
    
    public void update (Observable observable, Object arrivee)
    {
        if(observable instanceof Grid && arrivee instanceof Case)
        {
            Grid grid = (Grid) observable;
            
            for (Case c : grid.getListToExplore())
            {
                if(!c.equals(arrivee))
                {
                    Node node = cell(c.getColumn(), c.getRow());
                    if(node instanceof TextField) node.setStyle("-fx-background-color: " + CouleurBleu + ";");
                }
            }
        }
    }
}