package com.mycompany.finalproject;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.geometry.*;
import java.util.ArrayList;
import javafx.animation.Interpolator;
import javafx.animation.*;
import javafx.scene.effect.GaussianBlur;
import javafx.util.Duration;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.effect.ColorAdjust;
/****************************************************************************************** 
* Final Project
* Author: Sophia Maldonado 
* Project Purpose: Interactive basketball shot chart and heat map                   
* Input: User clicks on desired location of court and determines whether shot was a make or not using radio buttons
* Desired Output: In the shot chart window the total shots, misses, and makes will update as a circle or red x will appear where the user clicked.
*                 In the heat map window the zone of the court will change colors depending on how many shot were taken and display fg% in that area
* Variables and Classes: There is one class, with the main method. It uses the System.out.println method. 
*             Variables: Stage shotChartStage, heatMapStage, mainStage
*                        int for Attempts and Makes of each zone, Button heatButton, Button shotChartButton,ImageView background, Hbox hbox
*                        ImageView ball, TranslateTransition roll,GaussianBlur blur, Scene scene, StackPane root
*          shotChartWindow class - lets user record shots
*          heatMapWindow class - shows users shooting performance visually 
*          shot class - storing shots
*          PlayerShot class - determine if shot was made or missed
*          zone class - updates label and color of zone depending on number of shots
* Formulas: The values are stored as literals
* Testing: I tested the program to make sure the event handlers worked properly, the calculations were accurate, and the zones changed colors correctly
* April 28, 2026 
*************************************************************************************/
public class App extends Application {

    private Stage shotChartStage;
    private Stage heatMapStage;
    private Stage mainStage;
    private int totalMakes = 0;
    private int totalShots = 0;
    private int totalMiss = 0;
    
    // Zone stats
    public int paintAttempts = 0;
    public int paintMakes = 0;

    public int leftCornerAttempts = 0;
    public int leftCornerMakes = 0;

    public int rightCornerAttempts = 0;
    public int rightCornerMakes = 0;
    
    public int leftWingAttempts = 0;
    public int leftWingMakes = 0;

    public int rightWingAttempts = 0;
    public int rightWingMakes = 0;

    public int topArcAttempts = 0;
    public int topArcMakes = 0;
    
    public int midRangeAttempts = 0;
    public int midRangeMakes = 0;
    

    // Zone getters
    public int getPaintAttempts() { return paintAttempts; }
    public int getPaintMakes() { return paintMakes; }

    public int getLeftCornerAttempts() { return leftCornerAttempts; }
    public int getLeftCornerMakes() { return leftCornerMakes; }

    public int getRightCornerAttempts() { return rightCornerAttempts; }
    public int getRightCornerMakes() { return rightCornerMakes; }
    
    public int getLeftWingAttempts() { return leftWingAttempts; }
    public int getLeftWingMakes() { return leftWingMakes; }   
    
    public int getRightWingAttempts() { return rightWingAttempts; }
    public int getRightWingMakes() { return rightWingMakes; }

    public int getTopArcAttempts() { return topArcAttempts; }
    public int getTopArcMakes() { return topArcMakes; }
    
    public int getMidRangeAttempts() { return midRangeAttempts; }
    public int getMidRangeMakes() { return midRangeMakes; }
    
    
    //Total shot methods
    public void addMake() {
    totalMakes++;
    totalShots++;
    }

    public void addMiss() {
    totalMiss++;
    totalShots++;
    }

    public int getTotalMakes() {
    return totalMakes;
    }

    public int getTotalShots() {
    return totalShots;
    }

    public int getTotalMiss() {
    return totalMiss;
    }
    
     //shot list 
    private ArrayList<PlayerShot> shots = new ArrayList<>();
    
    public ArrayList<PlayerShot> getShots(){
        return shots;
    }
    
    @Override
    public void start(Stage stage) {
        //create button for Shot Chart and Heat Map
        Button shotChartButton = new Button("Shot Chart");
        Button heatButton = new Button("Heat Map");
        //event handler  for buttons
        shotChartButton.setOnAction( event ->showShotChartWindow());
        heatButton.setOnAction(event -> showHeatMapWindow());
        // container 
        HBox hBox = new HBox(shotChartButton, heatButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(10);
        
        // Background image
        ImageView background = new ImageView(new Image("bballWooden.png"));
        background.setFitWidth(900);
        background.setFitHeight(800);
        Rectangle overlay = new Rectangle(900, 800);
        overlay.setFill(Color.rgb(0, 0, 0, 0.35)); 
         
        // Blur effect
        GaussianBlur blur = new GaussianBlur(12);
        background.setEffect(blur);
         // Slide-in animation
         TranslateTransition slide = new TranslateTransition(Duration.seconds(3), hBox);
        slide.setFromY(120);  
        slide.setToY(0);       
        slide.setInterpolator(Interpolator.EASE_OUT);
        slide.play();
     
        
        // Basketball icon
        ImageView ball = new ImageView(new Image("basketball.png"));
        ball.setFitWidth(60);
        ball.setFitHeight(60);
        ColorAdjust darkenBall = new ColorAdjust();
        darkenBall.setBrightness(-0.4); // negative = darker
        ball.setEffect(darkenBall);
        ball.setFitWidth(100);
        ball.setFitHeight(100);

        // Start the ball off-screen to the left
        ball.setTranslateX(-400);
        ball.setTranslateY(200); // adjust to place it lower or higher

    // Rolling animation
    TranslateTransition roll = new TranslateTransition(Duration.seconds(3), ball);
    roll.setFromX(-400);
    roll.setToX(0);
    roll.setInterpolator(Interpolator.EASE_OUT);
  
    //Spin 
    RotateTransition spin = new RotateTransition(Duration.seconds(5.0), ball);
    spin.setByAngle(360);             
    spin.setInterpolator(Interpolator.LINEAR);
    spin.setCycleCount(Animation.INDEFINITE);
    
    TranslateTransition bounce = new TranslateTransition(Duration.seconds(0.3), ball);
    bounce.setFromY(ball.getTranslateY());
    bounce.setToY(ball.getTranslateY() - 25);  // small upward bounce
    bounce.setAutoReverse(true);
    bounce.setCycleCount(Animation.INDEFINITE);                   // up then down
    
    roll.setOnFinished(e -> {
    spin.play();
    bounce.play();
});
    roll.play();
    // Root layout
    StackPane root = new StackPane(background,overlay, hBox, ball);


    this.mainStage = stage;
       
        this.mainStage = stage;
        Scene scene = new Scene(root, 900, 800);
        scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }
    
    public void showShotChartWindow(){
    shotChartStage = new Stage();
    shotChartWindow window = new shotChartWindow(this, shotChartStage,mainStage);
    Scene scene = new Scene(window.getLayout(), 900, 800);
    scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    
    shotChartStage.setScene(scene);
    shotChartStage.setTitle("Shot Chart");
    shotChartStage.show();
    }
    
    public void showHeatMapWindow(){
    
    heatMapStage = new Stage();
     heatMapWindow heatWindow = new heatMapWindow(this, heatMapStage, mainStage);
    Scene scene = new Scene(heatWindow.getHeatLayout(), 900, 800);
    scene.getStylesheets().add(getClass().getResource("/styles.css").toExternalForm());
    
    heatMapStage.setScene(scene);
    heatMapStage.setTitle("Heat Map");
    heatMapStage.show();  
    }

    public static void main(String[] args) {
        launch();
    }

}