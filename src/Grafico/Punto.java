/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafico;

import covid.tracker.Nodo;

/**
 * Punto en el espacio bidimensional.
 * @author JPPM
 */
public class Punto extends Nodo{
    int x , y;

    public Punto(int id, int x , int y) {
        super(id);
        this.x=x;
        this.y=y;
    }

    public void setCordenada(int x , int y){
        this.x=  x ;
        this.y = y;
    }
    public void setX(int x){
        this.x=x;
    }
    public void setY(int y){
        this.y=y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    
    
}
