package ca.cmpt213.asn5.Client.UI;

import ca.cmpt213.asn5.Client.Info.Tokimon;
import com.google.gson.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A class for Display stage which display all the
 * Tokimons and display different types of
 * Tokimon based on Tokimon Information with different shapes
 */

public class Display {
    Stage newStage = new Stage();

    Button Create = new Button("Add");
    public static Gson gson = new Gson();

    public BorderPane mainDisplay(Stage window) throws IOException {
        window.setMaximized(true);
        BorderPane mainDisplay = new BorderPane();
        Button Delete = new Button("Delete");
        Button refresh = new Button("Refresh");
        Button search = new Button("Search");
        Circle circle = new Circle();
        Circle circle1 = new Circle();
        Rectangle rectangle = null;


        final ComboBox priorityComboBox = new ComboBox();
        priorityComboBox.getItems().addAll(
                "Strength",
                "Weight",
                "Height",
                "Fire",
                "Fly",
                "Freeze",
                "Water",
                "Electric"
        );

        refresh.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {

                    String selectedPerson = (String) priorityComboBox.getSelectionModel().getSelectedItem();
                    URL url = null;
                    url = new URL("http://localhost:8080/api/tokimon/all");

                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.connect();

                    priorityComboBox.getSelectionModel().select("Strength");
                    connection.disconnect();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        priorityComboBox.setOnAction((event) -> {
            try {

                String selectedPerson = (String) priorityComboBox.getSelectionModel().getSelectedItem();
                URL url = new URL("http://localhost:8080/api/tokimon/all");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(connection.getInputStream())
                );
                String s = null;
                s = br.readLine();
                Tokimon[] t = new Gson().fromJson(s, Tokimon[].class);
                GridPane pane = new GridPane();
                int index = 0;

                if (t.length == 0) {
                    Text text = new Text();
                    text.setX(28);
                    text.setY(10);
                    text.setText("No Tokimon to Display.");
                    text.setFont(new Font(40));
                    for (int k = 0; k <= 9; k++) {
                        pane.addRow(k, new Text(""));
                    }
                    for (int k = 11; k < 19; k++) {
                        pane.addRow(k, new Text(""));
                    }
                    Pane p = new Pane(text);
                    pane.add(p, 25, 10);
                    pane.setHgap(10);
                    pane.setVgap(10);
                    mainDisplay.setCenter(pane);
                } else {

                    for (int i = 0; i < t.length; i++) {

                        Tokimon tokimon = t[i];
                        Rectangle rectangle1 = new Rectangle();
                        Text text = new Text();

                        rectangle1.setX(20);
                        rectangle1.setY(20);
                        text.setX(28);
                        text.setY(10);
                        text.setText(tokimon.getName());
                        rectangle1.setWidth(50);
                        if (selectedPerson.equals("Height")) {
                            rectangle1.setHeight(tokimon.getHeight());
                            Pane p = new Pane(rectangle1, text);
                            Color c = Color.web(tokimon.getColor());
                            rectangle1.setFill(c);
                            for (int k = 0; k <= 9; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            for (int k = 11; k < 19; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            pane.add(p, index, 10);
                            pane.setHgap(10);
                            pane.setVgap(10);
                            index++;
                            rectangle1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
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

                                    Scene stageScene = new Scene(vbDisplay, 300, 700);
                                    newStage.setScene(stageScene);
                                    newStage.show();
                                }
                            });

                        } else if (selectedPerson.equals("Weight")) {
                            rectangle1.setHeight(tokimon.getWeight());
                            Pane p = new Pane(rectangle1, text);
                            Color c = Color.web(tokimon.getColor());
                            rectangle1.setFill(c);

                            for (int k = 0; k <= 9; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            for (int k = 11; k < 19; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            pane.setHgap(10);
                            pane.setVgap(10);

                            pane.add(p, index, 10);
                            index++;
                            rectangle1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
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

                                    Scene stageScene = new Scene(vbDisplay, 300, 700);
                                    newStage.setScene(stageScene);
                                    newStage.show();
                                }
                            });

                        } else if (selectedPerson.equals("Strength")) {
                            rectangle1.setHeight(tokimon.getStrength());
                            Pane p = new Pane(rectangle1, text);
                            Color c = Color.web(tokimon.getColor());
                            rectangle1.setFill(c);
                            for (int k = 0; k <= 9; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            for (int k = 11; k < 19; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            pane.setHgap(10);
                            pane.setVgap(10);

                            pane.add(p, index, 10);
                            index++;
                            rectangle1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
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

                                    Scene stageScene = new Scene(vbDisplay, 300, 700);
                                    newStage.setScene(stageScene);
                                    newStage.show();
                                }
                            });
                        } else if (selectedPerson.equals("Fire")) {
                            rectangle1.setHeight(tokimon.getFire());
                            Pane p = new Pane(rectangle1, text);
                            Color c = Color.web(tokimon.getColor());
                            rectangle1.setFill(c);
                            for (int k = 0; k <= 9; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            for (int k = 11; k < 19; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            pane.setHgap(10);
                            pane.setVgap(10);

                            pane.add(p, index, 10);
                            index++;
                            rectangle1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
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

                                    Scene stageScene = new Scene(vbDisplay, 300, 700);
                                    newStage.setScene(stageScene);
                                    newStage.show();
                                }
                            });
                        } else if (selectedPerson.equals("Fly")) {
                            rectangle1.setHeight(tokimon.getFly());
                            Pane p = new Pane(rectangle1, text);
                            Color c = Color.web(tokimon.getColor());
                            rectangle1.setFill(c);
                            for (int k = 0; k <= 9; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            for (int k = 11; k < 19; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            pane.setHgap(10);
                            pane.setVgap(10);

                            pane.add(p, index, 10);
                            index++;
                            rectangle1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
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

                                    Scene stageScene = new Scene(vbDisplay, 300, 700);
                                    newStage.setScene(stageScene);
                                    newStage.show();
                                }
                            });
                        } else if (selectedPerson.equals("Water")) {
                            rectangle1.setHeight(tokimon.getWater());
                            Pane p = new Pane(rectangle1, text);
                            Color c = Color.web(tokimon.getColor());
                            rectangle1.setFill(c);
                            for (int k = 0; k <= 9; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            for (int k = 11; k < 19; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            pane.setHgap(10);
                            pane.setVgap(10);

                            pane.add(p, index, 10);
                            index++;
                            rectangle1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
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

                                    Scene stageScene = new Scene(vbDisplay, 300, 700);
                                    newStage.setScene(stageScene);
                                    newStage.show();
                                }
                            });
                        } else if (selectedPerson.equals("Electric")) {
                            rectangle1.setHeight(tokimon.getElectric());
                            Pane p = new Pane(rectangle1, text);
                            Color c = Color.web(tokimon.getColor());
                            rectangle1.setFill(c);
                            for (int k = 0; k <= 9; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            for (int k = 11; k < 19; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            pane.setHgap(10);
                            pane.setVgap(10);
                            pane.add(p, index, 10);
                            index++;
                            rectangle1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
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

                                    Scene stageScene = new Scene(vbDisplay, 300, 700);
                                    newStage.setScene(stageScene);
                                    newStage.show();
                                }
                            });

                        } else {
                            rectangle1.setHeight(tokimon.getFreeze());
                            Pane p = new Pane(rectangle1, text);
                            Color c = Color.web(tokimon.getColor());
                            rectangle1.setFill(c);
                            for (int k = 0; k <= 9; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            for (int k = 11; k < 19; k++) {
                                pane.addRow(k, new Text(""));
                            }
                            pane.setHgap(10);
                            pane.setVgap(10);

                            pane.add(p, index, 10);
                            index++;
                            rectangle1.setOnMouseClicked(new EventHandler<MouseEvent>() {
                                @Override
                                public void handle(MouseEvent event) {
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

                                    Scene stageScene = new Scene(vbDisplay, 300, 700);
                                    newStage.setScene(stageScene);
                                    newStage.show();
                                }
                            });

                        }
                        mainDisplay.setCenter(pane);

                    }
                }
                connection.disconnect();
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        Create = new Button("Add");
        Create.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(Main.Add);
                priorityComboBox.getSelectionModel().clearSelection();
            }
        });
        Delete.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(Main.Delete);
                priorityComboBox.getSelectionModel().clearSelection();
            }
        });
        search.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                window.setScene(Main.Search);
                priorityComboBox.getSelectionModel().clearSelection();
            }
        });

        HBox mainButtons = new HBox();
        VBox comboBox = new VBox();
        mainButtons.setAlignment(Pos.TOP_CENTER);
        comboBox.getChildren().add(priorityComboBox);

        Label reference = new Label("Please press refresh button if you don't see any Tokimons.");
        reference.setFont(new Font(20));

        reference.setAlignment(Pos.CENTER);
        mainButtons.getChildren().addAll(Create, Delete, search);
        mainDisplay.setStyle("-fx-border-color : blue; -fx-border-width : 5 5 ");

        mainDisplay.setTop(mainButtons);
        mainDisplay.setLeft(comboBox);
        mainDisplay.setRight(refresh);
        mainDisplay.setBottom(reference);


        mainButtons.setSpacing(30);
        return  mainDisplay;
    }
}
