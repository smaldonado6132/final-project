/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;

/**
 *
 * @author smald
 */
public class shotChartWindow {
    private Stage stage;
    private Label totalLabel;
    private Label makeLabel;
    private Label missLabel;
    private App app;
    private Stage mainStage;
    private Pane courtPane;
    double x;
    double y;
    boolean made;
    
    public shotChartWindow(App app, Stage stage, Stage mainStage){
      this.app = app; 
      this.stage = stage;
      this.mainStage = mainStage;
    
    }
    
  
    public BorderPane getLayout(){
        
    // Create Radio Buttons for make and miss
    RadioButton make = new RadioButton("Make");
    RadioButton miss = new RadioButton("Miss");
    make.getStyleClass().add("make-btn");
    miss.getStyleClass().add("miss-btn");
    
    //Toggle group for buttons 
    ToggleGroup radioGroup = new ToggleGroup();
    make.setToggleGroup(radioGroup);
    miss.setToggleGroup(radioGroup);
    
    //Labels on right of borderpane
    totalLabel = new Label("Total: " + app.getTotalShots());
    totalLabel.getStyleClass().add("stats-number");
    makeLabel = new Label("Makes: "+ app.getTotalMakes());
    makeLabel.getStyleClass().add("stats-number");
    missLabel = new Label("Misses: " + app.getTotalMiss());
    missLabel.getStyleClass().add("stats-number");
    
    // top label
    Label shotLabel = new Label("Shot Chart");
    shotLabel.getStyleClass().add("title-text");
    
    // Creat the buttons
    Button heatMapButton = new Button("Heat Map");
    heatMapButton.setPrefSize(165,50);
    Button homeButton = new Button("Home");
    homeButton.setPrefSize(165,50);
    
    //HBox container for buttons
    HBox hBox = new HBox(heatMapButton, homeButton);
    hBox.setAlignment(Pos.CENTER);
    hBox.setSpacing(10);
    hBox.setMargin(heatMapButton, new Insets(0,0,50,0));
    hBox.setMargin(homeButton, new Insets(0,0,50,0));
   
        
    // Containers for radio buttons and labels 
    VBox vBoxLeft = new VBox(make,miss);
    VBox vBoxRight = new VBox(makeLabel,missLabel,totalLabel);
    vBoxLeft.setAlignment(Pos.CENTER_LEFT);
    vBoxRight.setAlignment(Pos.CENTER_LEFT);
    vBoxRight.getStyleClass().add("stats-box");
    vBoxLeft.setSpacing(10);
    vBoxLeft.setPrefWidth(150);
    vBoxRight.setSpacing(10);
    vBoxLeft.setPadding(new Insets(20));
    vBoxRight.setPadding(new Insets(20));
    
   
    // create image
    Image bballCourtImage = new Image("bballWooden.png");
    ImageView courtIView = new ImageView(bballCourtImage);
    courtIView.setFitWidth(600);
    courtIView.setFitHeight(600);
    
    courtIView.setOnMouseClicked(event -> {
    System.out.println("X: " + event.getX() + " Y: " + event.getY());
});
    
    courtPane = new Pane();
    courtPane.setPrefSize(600, 600);
    courtPane.getChildren().add(courtIView);
   
    //borderpane
    BorderPane layout = new BorderPane();
    layout.setRight(vBoxRight);
    layout.setLeft(vBoxLeft);
    layout.setTop(shotLabel);
    layout.setBottom(hBox);
    layout.setCenter(courtPane); 
    layout.setAlignment(shotLabel,Pos.CENTER);
    layout.getStyleClass().add("screen-root");
    
      //Event Handler for Shot Chart button
    heatMapButton.setOnAction(event ->
        {
        stage.close();
        app.showHeatMapWindow();
    });
    
    //event handler for home button
     homeButton.setOnAction(event ->
    {
        stage.close();
        mainStage.show();
    });
   
     //event handler for clicking on screen
    courtPane.setOnMouseClicked(event ->
     {
        x = event.getX();
        y = event.getY();
       
        
        if (make.isSelected()){
            made = true;
            
            //create circle for made shots
             Circle circle = new Circle(x,y,6,Color.GREEN);
 
            courtPane.getChildren().add(circle);
            app.addMake();
            getPlayerShot(x,y);
            updateZoneStats(x, y, true);
            
            updateLabel();
        }
       if (miss.isSelected()){
           made = false;
           double size = 5; // controls X size

           Line line1 = new Line(x - size, y - size, x + size, y + size);
           Line line2 = new Line(x + size, y - size, x - size, y + size);
           line1.setStroke(Color.RED);
           line2.setStroke(Color.RED);
           line1.setStrokeWidth(5);
           line2.setStrokeWidth(5);
           courtPane.getChildren().add(line1);
           courtPane.getChildren().add(line2);
           app.addMiss();
           getPlayerShot(x,y);
           updateZoneStats(x, y, false);
           
           updateLabel();
       }
    });
    
    return layout;
    }
    
    public void updateLabel(){
        totalLabel.setText("Total: " + app.getTotalShots());
        makeLabel.setText("Makes: " + app.getTotalMakes());
        missLabel.setText("Misses: " + app.getTotalMiss());
    }
   
    public void getPlayerShot(double clickX, double clickY){
       app.getShots().add(new PlayerShot(clickX,clickY,made)); 
    }
    
    private void updateZoneStats(double x, double y, boolean made) {

    // Paint
    if (x >= 235 && x <= 365 &&
        y >= 97 && y <= 222) {

        app.paintAttempts++;
        if (made) app.paintMakes++;
        return;
    }

    // Left corner
    if (x >= 38 && x <= 83 &&
             y >= 31 && y <= 96) {

        app.leftCornerAttempts++;
        if (made) app.leftCornerMakes++;
        return;
    }

    // Right corner
    if (x >= 518 && x <= 563 &&
             y >= 31 && y <= 96) {

        app.rightCornerAttempts++;
        if (made) app.rightCornerMakes++;
        return;
    }
    // Left wing 
    if (x >= 37.5 && x <= 162.5 &&
        y >= 94.5 && y <= 312.0) {

        app.leftWingAttempts++;
        if (made) app.leftWingMakes++;
        return;
    }

    // RIGHT WING
    if (x >= 444.5 && x <= 563.5 &&
        y >= 94.5  && y <= 312.0) {

        app.rightWingAttempts++;
        if (made) app.rightWingMakes++;
        return;
    }

    // TOP ARC
    if (x >= 39.6 && x <= 560.0 &&
        y >= 256.0 && y <= 570.0) {

        app.topArcAttempts++;
        if (made) app.topArcMakes++;
        return;
    }
    //Mid Range
    if(x >= 82.5 && x <= 517.5 &&
    y >= 95.0 && y <= 223.5) {

    app.midRangeAttempts++;
    if (made) app.midRangeMakes++;
    return;
}
}
}
