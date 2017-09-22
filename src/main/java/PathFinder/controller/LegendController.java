package PathFinder.controller;

import PathFinder.model.CellType;
import PathFinder.model.ListCase;
import PathFinder.model.ListManager;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

/*................................................................................................................................
 . Copyright (c)
 .
 . The LegendController	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 22/09/17 13:55
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

public class LegendController
{
    @FXML
    private GridPane gridPaneLegend;
    private ListManager listManager = new ListManager();
    
    @FXML
    public void initialize ()
    {
        int rowIndex = 0;
        for (CellType type : CellType.values())
        {
            ListCase listCase = listManager.get(type);

            String poids = type.equals(CellType.MUR) ? " " : listCase.getPoids() + "";
            
            TextField txtName = new TextField(type.name());
            TextField txtColor = new TextField();
            TextField txtLogo = new TextField(listCase.getLogo());
            TextField txtPoids = new TextField(poids);
            
            txtColor.setStyle("-fx-background-color: " + listCase.getCouleur() + ";");
            
            txtName.setEditable(false);
            txtColor.setEditable(false);
            txtLogo.setEditable(false);
            txtPoids.setEditable(false);
            
            gridPaneLegend.addRow(rowIndex++, txtName, txtColor, txtLogo, txtPoids);
        }
    }
    
}
