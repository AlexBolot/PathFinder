package PathFinder.service;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*................................................................................................................................
 . Copyright (c)
 .
 . The App	 Class was Coded by : Alexandre BOLOT
 .
 . Last Modified : 12/04/17 00:01
 .
 . Contact : bolotalex06@gmail.com
 ...............................................................................................................................*/

@SuppressWarnings("ConstantConditions")
public class App extends Application
{
    public static void main (String[] args) { launch(args); }
    
    @Override
    public void start (Stage primaryStage)
    {
        
        Stage anotherStage = new Stage();
        
        try
        {
            FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("Grid.fxml"));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.show();
            
            FXMLLoader anotherLoader = new FXMLLoader(getClass().getClassLoader().getResource("Legend.fxml"));
            Parent anotherRoot = anotherLoader.load();
            Scene anotherScene = new Scene(anotherRoot);
            anotherStage.setScene(anotherScene);
            anotherStage.show();
            
        }
        catch (Exception exc)
        {
            exc.printStackTrace();
        }
    }
}
