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
public class heatMapWindow {
    private App app;
    private Stage stage;
    private Stage mainStage;
    private shotChartWindow shotData;
    private int makes;
    private int total;
    
    //create arg-constructor 
    public heatMapWindow(App app, Stage stage, Stage mainStage){
    this.app =app;
    this.stage =stage;
    this.mainStage = mainStage; 
    }
    
    
    //Heat map layout
    public BorderPane getHeatLayout(){
        
     int makes = app.getTotalMakes();
     int total = app.getTotalShots();

     
     // store fg percentage
     String fgText;
     if(total == 0){
         fgText = "N/A";
     }else{
         double fg = (double)makes / total *100;
         fgText = String.format("%.1f", fg);
     }
     
     
    
    //Labels on right of borderpane
    Label statsLabel = new Label("Overall FG%:\n " + fgText);
    statsLabel.getStyleClass().add("stats-label");
    
    Label legendLabel = new Label("Legend:\n"
            + "Red = Hottest\n"
            + "Orange = Warm\n"
            + "Yellow = Medium\n"
            + "Blue = Cold\n"
            + "Aqua = None");
    legendLabel.getStyleClass().add("stats-label");
    
 
    
    // toplabel 
    Label heatLabel = new Label("Heat Map Chart");
    heatLabel.getStyleClass().add("title-text");
    
    //Bottom buttons
    Button shotChartButton = new Button("Shot Chart");
    shotChartButton.setPrefSize(165,50);
    Button homeButton = new Button("Home");
    homeButton.setPrefSize(165,50);
    
    
    HBox hBox = new HBox(shotChartButton,homeButton);
    hBox.setAlignment(Pos.CENTER);
    hBox.setSpacing(10);
    hBox.setMargin(shotChartButton, new Insets(0,0,50,0));
    hBox.setMargin(homeButton, new Insets(0,0,50,0));
    
    // create image
    Image bballCourtImage = new Image("bballWooden.png");
    ImageView courtIView = new ImageView(bballCourtImage);
    courtIView.setFitWidth(600);
    courtIView.setFitHeight(600);
 
  
    //Shapes going over the court
    Rectangle paint = new Rectangle(234.9,97.3,130,125.1);
    
    Path leftWing = new Path(
    new MoveTo(37.5, 94.5),
    new LineTo(82.5, 95.5),
  
    // Curved arc along the real 3PT line
    new ArcTo(
        230.0, 230.0,   
        0,             
        162.5, 262.0,     
        false,          
        false           
    ),
    
    new LineTo(97.0,306.5), 
    new LineTo(41.0,312.0),
    new LineTo(39.5,223.0),

    new ClosePath()
    );
    
    Path rightWing = new Path(
    new MoveTo(517.5, 95.5),   
      
    // Curved arc along the real 3PT line 
    new ArcTo(
        230.0, 230.0,         
        0,                     
        444.5, 256.0,         
        false,                 
        true                  
    ),

    new LineTo(504.0, 306.5), 
    new LineTo(560.0, 312.0),  
    new LineTo(563.5, 94.5),

    new ClosePath()
    );
    
    Path topArc = new Path(
    // Start at left wing’s top-right
    new MoveTo(165.5, 262.0),

    // Arc across the top of the 3PT line
    new ArcTo(
        230.0, 230.0,
        0,
        444.5, 256.0,   // right arc endpoint
        false,
        false
    ),

    // Connect to right wing 
    new LineTo(504.0, 306.5), 
    new LineTo(560.0, 312.0), 
    new ArcTo(
        25.0, 25.0,
        0,
        39.6, 312.0, 
        false,
        true
    ),
            
     new LineTo(97,306),
             
    // Close back to the start
    new ClosePath()
    );
    
    // Right corner
    Rectangle rightCorner = new Rectangle(518,31,45,65);
    
    //Left Corner
    Rectangle leftCorner = new Rectangle(38,31,45,65);
    
    Path midRange = new Path(

    // Start left side of arc
    new MoveTo(82.5, 95.5),

    // Full 3PT arc across the top
    new ArcTo(
        100.0, 100.0,
        0,
        517.5, 95.5,
        false,
        false
    ),

    // Straight baseline
    new LineTo(367.0, 95.0),

    // INNER BOUNDARY 
    new LineTo(366.5,95.5),
    new LineTo(366.0, 222.5),
    new LineTo(236, 223.5),
    new LineTo(233.5,96.5),
  
    new ClosePath()
);

    
    // Stats labels 
    Label paintStats = new Label();
    paintStats.setLayoutX(275);
    paintStats.setLayoutY(130);
    paintStats.getStyleClass().add("zone-label"); 

    Label leftCornerStats = new Label();
    leftCornerStats.setLayoutX(35);
    leftCornerStats.setLayoutY(40);
    leftCornerStats.getStyleClass().add("zone-label"); 

    Label rightCornerStats = new Label();
    rightCornerStats.setLayoutX(510);
    rightCornerStats.setLayoutY(40);
    rightCornerStats.getStyleClass().add("zone-label"); 
    
    Label leftWingStats = new Label();
    leftWingStats.setLayoutX(53);  
    leftWingStats.setLayoutY(200);  
    leftWingStats.getStyleClass().add("zone-label");
    
    Label rightWingStats = new Label();
    rightWingStats.setLayoutX(495); 
    rightWingStats.setLayoutY(200);  
    rightWingStats.getStyleClass().add("zone-label");
    
    Label topArcStats = new Label();
    topArcStats.setLayoutX(275);   
    topArcStats.setLayoutY(400);   
    topArcStats.getStyleClass().add("zone-label");
    
    Label midRangeStats = new Label();
    midRangeStats.setLayoutX(275);
    midRangeStats.setLayoutY(235.5);
    midRangeStats.getStyleClass().add("zone-label");    
    
    //Create zone objects
    zone paintZone = new zone(paint, paintStats);
    zone leftCornerZone = new zone(leftCorner, leftCornerStats);
    zone rightCornerZone = new zone(rightCorner, rightCornerStats);
    zone leftWingZone = new zone(leftWing, leftWingStats);
    zone rightWingZone = new zone(rightWing, rightWingStats);
    zone topZone = new zone(topArc, topArcStats);
    zone midRangeZone = new zone (midRange, midRangeStats);
    
    //Load stats from App into zones
    paintZone.setStats(app.paintMakes, app.paintAttempts);
    leftCornerZone.setStats(app.leftCornerMakes, app.leftCornerAttempts);
    rightCornerZone.setStats(app.rightCornerMakes, app.rightCornerAttempts);
    leftWingZone.setStats(app.leftWingMakes, app.leftWingAttempts);
    rightWingZone.setStats(app.rightWingMakes, app.rightWingAttempts);
    topZone.setStats(app.topArcMakes, app.topArcAttempts);
    midRangeZone.setStats(app.midRangeMakes,app.midRangeAttempts);
 
    //Pane
    Pane heatMapLayer = new Pane();
    heatMapLayer.setPrefSize(600,600);
    heatMapLayer.getChildren().add(courtIView);
    heatMapLayer.getChildren().addAll(
            paint, leftCorner, rightCorner,
            paintStats, leftCornerStats, rightCornerStats,
            leftWing,rightWing, topArc, leftWingStats, rightWingStats,
            topArcStats, midRange, midRangeStats
    );
   
    

    
    // Containers for borderpane   
    VBox vBoxRight = new VBox(statsLabel,legendLabel);
    vBoxRight.getStyleClass().add("stats-box");
    vBoxRight.setAlignment(Pos.CENTER_LEFT);
    vBoxRight.setSpacing(10);
    vBoxRight.setPadding(new Insets(20));
    VBox emptyLeft = new VBox();
    emptyLeft.setPrefWidth(150); 


    
    
    //borderpane
    BorderPane layout = new BorderPane();
    layout.setRight(vBoxRight);
    layout.setTop(heatLabel);
    layout.setBottom(hBox);
    layout.setCenter(heatMapLayer); 
    layout.setAlignment(heatLabel,Pos.CENTER);
    layout.setLeft(emptyLeft);
    layout.getStyleClass().add("screen-root");
    
    //Event Handler for Shot Chart button
    shotChartButton.setOnAction(event ->
        {
        stage.close();
        app.showShotChartWindow();   
    });
    
    //event handler for home button
    homeButton.setOnAction(event ->
    {
        stage.close();
        mainStage.show();
    }); 
    
    return layout;
    }
    
     private String getZoneText(int makes, int attempts) {
        if (attempts == 0) return "0%\n0/0";

        double fg = (double) makes / attempts * 100;
        fg = Math.round(fg * 10) / 10.0;

        return fg + "%\n" + makes + "/" + attempts;
    }
}
