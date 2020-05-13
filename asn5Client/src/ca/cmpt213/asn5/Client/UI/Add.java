package ca.cmpt213.asn5.Client.UI;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * A class for Add stage to add Tokimon
 */

public class Add {

    public VBox createScene(Stage window){
        window.setMaximized(true);

        Label Name = new Label( "Name:");
        TextField nameTextField = new TextField ();
        HBox hbName = new HBox();
        hbName.getChildren().addAll(Name, nameTextField);
        hbName.setSpacing(39);

        Label Weight = new Label( "Weight:");
        TextField weightTextField = new TextField ();

        HBox hbWeight = new HBox();
        hbWeight.getChildren().addAll(Weight, weightTextField);

        hbWeight.setSpacing(30);
        Label Height = new Label( "Height:");
        TextField heightTextField = new TextField ();

        HBox hbHeight= new HBox();
        hbHeight.getChildren().addAll(Height, heightTextField);
        hbHeight.setSpacing(33);


        Label Strength = new Label( "Strength:");
        TextField strengthTextField = new TextField ();

        HBox hbStrength = new HBox();
        hbStrength.getChildren().addAll(Strength, strengthTextField);
        hbStrength.setSpacing(20);
        Label color = new Label( "Color:");
        TextField colorTextField = new TextField ();
        Label colorExample = new Label( "Ex: Red, White, yellow etc");


        HBox hbColor = new HBox();
        hbColor.getChildren().addAll(color, colorTextField, colorExample);
        hbColor.setSpacing(42);

        Label Fly = new Label( "Fly:");
        TextField flyTextField = new TextField ();

        HBox hbFly = new HBox();
        hbFly.getChildren().addAll(Fly, flyTextField);
        hbFly.setSpacing(51);

        Label Fire = new Label( "Fire:");
        TextField fireTextField = new TextField ();

        HBox hbFire = new HBox();
        hbFire.getChildren().addAll(Fire, fireTextField);
        hbFire.setSpacing(48);

        Label Water = new Label( "Water:");
        TextField waterTextField = new TextField ();

        HBox hbWater = new HBox();
        hbWater.getChildren().addAll(Water, waterTextField);
        hbWater.setSpacing(43);

        Label Electric = new Label( "Electric:");
        TextField electricTextField = new TextField ();

        HBox hbElectric = new HBox();
        hbElectric.getChildren().addAll(Electric, electricTextField);
        hbElectric.setSpacing(31);

        Label Freeze = new Label( "Freeze:");
        TextField freezeTextField = new TextField ();

        HBox hbFreeze = new HBox();
        hbFreeze.getChildren().addAll(Freeze, freezeTextField);
        hbFreeze.setSpacing(42);

        Button save = new Button("Save");
        Button back = new Button("Back");
        save.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    String name = nameTextField.getText();
                    String weight = weightTextField.getText();
                    String height = heightTextField.getText();
                    String strength = strengthTextField.getText();
                    String color = colorTextField.getText();
                    String fly = flyTextField.getText();
                    String fire = fireTextField.getText();
                    String water = waterTextField.getText();
                    String electric = electricTextField.getText();
                    String freeze = freezeTextField.getText();
                    Stage newStage = new Stage();

                    if (Double.parseDouble(fly) > 100 || Double.parseDouble(fly) < 0 || Double.parseDouble(fire) > 100 || Double.parseDouble(fire) < 0
                            || Double.parseDouble(water) > 100 || Double.parseDouble(water) < 0 || Double.parseDouble(electric) > 100 || Double.parseDouble(electric) < 0
                            || Double.parseDouble(freeze) > 100 || Double.parseDouble(freeze) < 0) {
                        Label l = new Label("All the Abilities should be between 0 and 100");
                        Pane vbDisplay = new Pane();
                        vbDisplay.getChildren().addAll(l);
                        Scene stageScene = new Scene(vbDisplay, 300, 300);
                        newStage.setScene(stageScene);
                        newStage.show();
                    }

                    else {
                        URL url = new URL("http://localhost:8080/api/tokimon/add");
                        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                        connection.setRequestMethod("POST");
                        connection.setDoOutput(true);
                        connection.setRequestProperty("Content-Type", "application/json");

                        OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());

                        wr.write("{\"name\":" + "\"" + name + "\"" + ", \"weight\":" + weight + ", \"height\":" + height + ", \"strength\":" + strength + ", \"color\":" + "\"" + color + "\""
                                + ", \"fly\":" + fly + ", \"fire\":" + fire + ", \"water\":" + water + ", \"electric\":" + electric + ", \"freeze\":" + freeze + "}");
                        wr.flush();
                        wr.close();
                        connection.connect();

                        if (connection.getResponseCode() == 201) {
                            Label l = new Label("Tokimon Created");
                            Pane vbDisplay = new Pane();
                            vbDisplay.getChildren().addAll(l);
                            Scene stageScene = new Scene(vbDisplay, 300, 300);
                            newStage.setScene(stageScene);
                            newStage.show();
                            nameTextField.clear();
                            weightTextField.clear();
                            heightTextField.clear();
                            strengthTextField.clear();
                            colorTextField.clear();
                            flyTextField.clear();
                            fireTextField.clear();
                            waterTextField.clear();
                            electricTextField.clear();
                            freezeTextField.clear();

                        } else {
                            Label l = new Label("Tokimon Not Created,  Please fill all the information");
                            Pane vbDisplay = new Pane();
                            vbDisplay.getChildren().addAll(l);
                            Scene stageScene = new Scene(vbDisplay, 300, 300);
                            newStage.setScene(stageScene);
                            newStage.show();
                        }
                        connection.disconnect();

                    }
                }
                catch (IOException e){

                }
                catch (NumberFormatException e){
                    Stage newStage = new Stage();
                    Label l = new Label("Invalid Input");
                    Pane vbDisplay = new Pane();
                    vbDisplay.getChildren().addAll(l);
                    Scene stageScene = new Scene(vbDisplay, 300, 300);
                    newStage.setScene(stageScene);
                    newStage.show();
                }
                catch (IllegalArgumentException e){
                    Stage newStage = new Stage();
                    Label l = new Label("Invalid Color");
                    Pane vbDisplay = new Pane();
                    vbDisplay.getChildren().addAll(l);
                    Scene stageScene = new Scene(vbDisplay, 300, 300);
                    newStage.setScene(stageScene);
                    newStage.show();
                }
            }
        });
        back.setOnAction(e->window.setScene(Main.Main));
        HBox hbButton = new HBox();
        hbButton.getChildren().addAll(save,back);
        hbButton.setSpacing(10);
        VBox vbCreate = new VBox();
        vbCreate.getChildren().addAll(hbName, hbWeight, hbHeight, hbStrength, hbColor, hbFly, hbFire, hbWater, hbElectric, hbFreeze, hbButton);
        vbCreate.setSpacing(30);

        return vbCreate;

    }

}
