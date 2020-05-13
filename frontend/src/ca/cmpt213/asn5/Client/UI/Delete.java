package ca.cmpt213.asn5.Client.UI;

import ca.cmpt213.asn5.Client.Info.Tokimon;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * A class for Delete stage to delete Tokimon by selecting
 * Tokimon from list
 */

public class Delete {
    public VBox deleteScene(Stage window) {
        window.setMaximized(true);
        Label Name = new Label("Delete");
        Name.setFont(new Font(30));
        TextField id = new TextField();
        HBox hbName = new HBox();
        hbName.getChildren().addAll(Name, id);
        hbName.setSpacing(39);
        Label selectedNameLabel = new Label("Select a Name");
        selectedNameLabel.setFont(new Font(30));
        final Integer[] idNum = {null};
        ListView<String> list = new ListView<>() ;
        Button refresh = new Button("Refresh");

        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                URL url = null;
                list.getItems().clear();
                try {
                    url = new URL("http://localhost:8080/api/tokimon/all");
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    final GsonBuilder gsonBuilder = new GsonBuilder();
                    BufferedReader br = new BufferedReader(
                            new InputStreamReader(connection.getInputStream())
                    );
                    String s = null;
                    s = br.readLine();
                    Tokimon[] t = new Gson().fromJson(s, Tokimon[].class);

                    for (Tokimon to : t){
                        list.getItems().addAll(to.getName());
                    }
                    list.setPrefWidth(100);
                    list.setPrefHeight(70);
                    list.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
                    list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
                        @Override
                        public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                            String select = list.getSelectionModel().getSelectedItem();
                            for (Tokimon to : t){
                                if (select.equals(to.getName())){
                                    idNum[0] = to.getId();
                                }
                            }
                            selectedNameLabel.setText(idNum[0].toString());
                        }
                    });
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        Button delete = new Button("Delete");
        Button back = new Button("Back");

        delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String ID = id.getText();
                try {
                    URL url = new URL("http://localhost:8080/api/tokimon/" + idNum[0]  );
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("DELETE");
                    connection.connect();
                    connection.disconnect();
                    Stage newStage = new Stage();
                    if (connection.getResponseCode() == 200){
                        Label tokimonDeleted = new Label("Tokimon Deleted");
                        tokimonDeleted.setAlignment(Pos.CENTER);
                        Pane vbDisplay = new Pane(tokimonDeleted);
                        Scene stageScene = new Scene(vbDisplay, 300, 300);
                        newStage.setScene(stageScene);
                        newStage.show();
                    }
                    else if(idNum[0] == null){
                        Label l = new Label("Please select a Tokimon");
                        Pane vbDisplay = new Pane();
                        l.setAlignment(Pos.CENTER);
                        vbDisplay.getChildren().addAll(l);
                        Scene stageScene = new Scene(vbDisplay, 300, 300);
                        newStage.setScene(stageScene);
                        newStage.show();
                    }
                    else{
                        Label l = new Label("Tokimon not Found, Please refresh the list.");
                        Pane vbDisplay = new Pane();
                        l.setAlignment(Pos.CENTER);
                        vbDisplay.getChildren().addAll(l);
                        Scene stageScene = new Scene(vbDisplay, 300, 300);
                        newStage.setScene(stageScene);
                        newStage.show();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        back.setOnAction(e->window.setScene(Main.Main));

        HBox buttons = new HBox();
        buttons.getChildren().addAll(delete,refresh,back);
        buttons.setSpacing(20);

        Label reference =  new Label("Please press refresh to update the list");
        reference.setFont(new Font(20));

        VBox vbAdd = new VBox();
        vbAdd.getChildren().addAll(reference,list, buttons);
        vbAdd.setSpacing(30);

        return vbAdd;
    }
}
