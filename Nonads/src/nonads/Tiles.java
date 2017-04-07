/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
//package nonads;
package Nonads.src.nonads;

/**
 *
 * @author Lily
 */

public class Tiles {
    private String[] rotations;
    private int orientation;

    Tiles(String first, String second, String third, String fourth){
        rotations = new String[]{first,second,third,fourth};
        orientation = 0;
    }

    public void rotate(){
        orientation++;
    }

    public String getTile(){
        return rotations[orientation % 4];
    }
}
