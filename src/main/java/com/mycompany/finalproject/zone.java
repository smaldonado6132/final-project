package com.mycompany.finalproject;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import javafx.geometry.*;
import javafx.scene.shape.*;
import javafx.scene.paint.*;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.control.*;
/**
 *
 * @author smald
 */
public class zone {
    Shape area;
    int attempts = 0;
    int makes = 0;
    Label label;

    public zone(Shape area, Label label) {
        this.area = area;
        this.label = label;
        
        area.setStroke(Color.TRANSPARENT);
        area.setStrokeWidth(1);
        area.setManaged(false);
        label.getStyleClass().add("zone-label");
    }
    
     // Stats from App
    void setStats(int makes, int attempts) {
        this.makes = makes;
        this.attempts = attempts;
        updateLabel();
        updateColor();
    }
   
    void recordShot(boolean isMake){
        attempts++;
        if(isMake) makes++;
        updateLabel();
    }
  
    void updateLabel(){
        double fg;
        if(attempts ==0){
           fg = 0;
        }else{
            fg = (double)makes/ attempts *100;
        }
        fg = Math.round(fg *10)/10.0;
        label.setText("FG%: "+ fg +"%\n\n "+ makes + "/" + attempts);
    }
    void updateColor() {
    area.setFill(getColorForAttempts(attempts));
}
    private Color getColorForAttempts(int attempts) {

        if (attempts == 0)
            return Color.rgb(0,137,203, 0.4);   // None

        if (attempts <= 5)
            return Color.rgb(0,102,204,0.4);       // Cold

        if (attempts <= 10)
            return Color.rgb(255,215,0,0.4);     // Medium

        if (attempts <= 20)
            return Color.rgb(255,140,0,0.4);     // Warm

        return Color.rgb(255,0,0,0.4);            // Hottest
    }
    
}
