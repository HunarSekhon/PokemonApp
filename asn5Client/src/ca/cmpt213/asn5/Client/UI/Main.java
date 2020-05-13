package ca.cmpt213.asn5.Client.UI;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * Main class that runs the UI application and handles main window
 */

public class Main extends Application {

    Stage window;
    Scene Welecome;
    static Scene Add;
    static Scene Search;
    static Scene Delete;
    static Scene Main;


    @Override
    public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        window.setMaximized(true);


        primaryStage.setTitle("Tokimon List");

        //--------------------------Welcome-----------------------

        Label myLabel = new Label( "Welocome to Tokimon List Application");
        Label myLabel2 = new Label("Created by Hunar Sekhon");
        myLabel.setFont(new Font(30.0));
        myLabel2.setFont(new Font(30.0));
        Button myButton = new Button("Add");
        myButton.setOnAction(e->window.setScene(Add));
        VBox vBox = new VBox(10, myLabel, myLabel2,  myButton);
        vBox.setAlignment(Pos.CENTER);
        Welecome = new Scene(vBox);

        //--------------------------Add-----------------------

        ca.cmpt213.asn5.Client.UI.Add create = new Add();
        Add = new Scene(create.createScene(window), 500, 1000);
        Add.getStylesheets().add("file:css/style.css");

        //--------------------------Delete-----------------------

        ca.cmpt213.asn5.Client.UI.Delete delete = new Delete();
        Delete =  new Scene(delete.deleteScene(window),500, 1000);
        Delete.getStylesheets().add("file:css/style.css");

        //--------------------------Search-----------------------


        ca.cmpt213.asn5.Client.UI.Search search = new Search();
        Search =  new Scene(search.searchScene(window),1000, 1000);
        Search.getStylesheets().add("file:css/style.css");

        //--------------------------Main-----------------------

        Display mainButtons = new Display();
        Main = new Scene(mainButtons.mainDisplay(window), 1440, 1000);
        Main.getStylesheets().add("file:css/style.css");

        window.setScene(Welecome);
        Welecome.getStylesheets().add("file:css/style.css");
        window.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
