package ca.cmpt213.asn5.Client.UI;

import ca.cmpt213.asn5.Client.Info.Tokimon;
import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A class for Search stage to search Tokimon with name
 */

public class Search {
    public VBox searchScene(Stage window) {
        Label reference = new Label("Please enter tokimon Name");
        Label search = new Label("Search");
        TextField name = new TextField();
        HBox hbName = new HBox();
        hbName.getChildren().addAll(search, name);
        hbName.setSpacing(39);
        Button searchBtn = new Button("Search");
        Button back = new Button("Back");

        searchBtn.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String ID = name.getText();
                String idName= ID;
                URL url = null;
                Stage newStage = new Stage();
                try {
                    url = new URL("http://localhost:8080/api/tokimon/all");
                    HttpURLConnection connection1 = (HttpURLConnection) url.openConnection();
                    connection1.setRequestMethod("GET");
                    connection1.connect();
                    InputStreamReader readerAll = new InputStreamReader(url.openStream());
                    Tokimon[] tokimonList = new Gson().fromJson(readerAll, Tokimon[].class);
                    for (Tokimon t : tokimonList) {
                        if (t.getName().equalsIgnoreCase(ID)) {
                            ID = t.getId().toString();
                        }
                    }
                    connection1.disconnect();

                    if (ID.equalsIgnoreCase(idName)) {
                        reference.setText("Tokimon not Found");
                        reference.setFont(new Font(30));
                    }
                    else {
                        reference.setText("Please enter tokimon Name");
                        reference.setFont(new Font(15));

                        url = new URL("http://localhost:8080/api/tokimon/" + ID);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();
                    InputStreamReader reader = new InputStreamReader(url.openStream());
                    Tokimon tokimon = Display.gson.fromJson(reader, Tokimon.class);

                    Label secondLabel = new Label("Name:");
                    Text tokiName = new Text();
                    tokiName.setText(tokimon.getName());
                    HBox hbName = new HBox();
                    hbName.getChildren().addAll(secondLabel, tokiName);
                    hbName.setSpacing(39);

                    Label Weight = new Label("Weight:");
                    Text tokiWeight = new Text();
                    tokiWeight.setText(String.valueOf(tokimon.getWeight()));

                    HBox hbWeight = new HBox();
                    hbWeight.getChildren().addAll(Weight, tokiWeight);

                    hbWeight.setSpacing(30);
                    Label Height = new Label("Height:");
                    Text tokiHeight = new Text();
                    tokiHeight.setText(String.valueOf(tokimon.getHeight()));


                    HBox hbHeight = new HBox();
                    hbHeight.getChildren().addAll(Height, tokiHeight);
                    hbHeight.setSpacing(33);


                    Label Strength = new Label("Strength:");
                    Text tokiStrength = new Text();
                    tokiStrength.setText(String.valueOf(tokimon.getStrength()));


                    HBox hbStrength = new HBox();
                    hbStrength.getChildren().addAll(Strength, tokiStrength);
                    hbStrength.setSpacing(20);

                    Label Color = new Label("Color:");
                    Text tokiColor = new Text();
                    tokiColor.setText(String.valueOf(tokimon.getColor()));


                    HBox hbColor = new HBox();
                    hbColor.getChildren().addAll(Color, tokiColor);
                    hbColor.setSpacing(42);

                    Label Fly = new Label("Fly:");
                    Text tokiFly = new Text();
                    tokiFly.setText(String.valueOf(tokimon.getFly()));


                    HBox hbFly = new HBox();
                    hbFly.getChildren().addAll(Fly, tokiFly);
                    hbFly.setSpacing(51);

                    Label Fire = new Label("Fire:");
                    Text tokiFire = new Text();
                    tokiFire.setText(String.valueOf(tokimon.getFire()));


                    HBox hbFire = new HBox();
                    hbFire.getChildren().addAll(Fire, tokiFire);
                    hbFire.setSpacing(48);

                    Label Water = new Label("Water:");
                    Text tokiWater = new Text();
                    tokiWater.setText(String.valueOf(tokimon.getWater()));


                    HBox hbWater = new HBox();
                    hbWater.getChildren().addAll(Water, tokiWater);
                    hbWater.setSpacing(43);

                    Label Electric = new Label("Electric:");
                    Text tokiElectric = new Text();
                    tokiElectric.setText(String.valueOf(tokimon.getElectric()));

                    HBox hbElectric = new HBox();
                    hbElectric.getChildren().addAll(Electric, tokiElectric);
                    hbElectric.setSpacing(31);

                    Label Freeze = new Label("Freeze:");
                    Text tokiFreeze = new Text();
                    tokiFreeze.setText(String.valueOf(tokimon.getFreeze()));


                    HBox hbFreeze = new HBox();
                    hbFreeze.getChildren().addAll(Freeze, tokiFreeze);
                    hbFreeze.setSpacing(42);


                    VBox vbDisplay = new VBox();
                    vbDisplay.getChildren().addAll(hbName, hbWeight, hbHeight, hbStrength, hbColor, hbFly, hbFire, hbWater, hbElectric, hbFreeze);
                    vbDisplay.setSpacing(30);
                    ScrollPane scrollPane = new ScrollPane(vbDisplay);
                    Scene stageScene = new Scene(scrollPane, 300, 300);
                    newStage.setScene(stageScene);
                    newStage.show();
                    name.clear();
                    connection.disconnect();
                }

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        back.setOnAction(e->window.setScene(Main.Main));
        VBox vbCreate = new VBox();
        vbCreate.getChildren().addAll(reference ,hbName, searchBtn, back);
        vbCreate.setSpacing(30);

        return vbCreate;
    }

}

