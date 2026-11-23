/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.finalproject;

/**
 *
 * @author smald
 */
public class PlayerShot extends shot {
    private boolean made;

    public PlayerShot( double x, double y, boolean made) {
        super(x, y);
        this.made = made;
    }

    

    public boolean isMade() {
        return made;
    }
    
    
}
