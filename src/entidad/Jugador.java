/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidad;

import mapa.Cueva;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;
import javafx.scene.control.Button;

/**
 *
 * @author Gerònimo
 */
public class Jugador {
    
    public int x, y;
    public int saveX, saveY;
    public int ent;
    public static int din=0;

    public Jugador(){
        this.x = 31;
        this.y = 31;
        this.ent =0;
    }
    
    public void moveLeft(Jugador player, GraphicsContext grafico){
        this.x = this.x - 1;
        player.imprimirJugador(grafico);
    }
    
    public void moveRight(Jugador player, GraphicsContext grafico){
        this.x = this.x + 1;
        player.imprimirJugador(grafico);
    }
    
    public void moveUp(Jugador player, GraphicsContext grafico){
        this.y = this.y - 1;
        player.imprimirJugador(grafico);
    }
    
    public void moveDown(Jugador player, GraphicsContext grafico){
        this.y = this.y + 1;
        player.imprimirJugador(grafico);
    }
    
    public void imprimirJugador(GraphicsContext grafico){
        int x = this.x;
        int y = this.y;
        Image mp = new Image("entidad/jugador1.png");
        grafico.drawImage(mp,x*16,y*16);
    }

    public void verificarS(int[][] collisionador){
        Random rand = new Random();
        int  x = rand.nextInt(30);
        int  y = rand.nextInt(30);
          
        while(collisionador[x][y] == 1){
            x = rand.nextInt(30);
            y = rand.nextInt(30);
        }
        this.x = x;
        this.x = y;
    }

    public void saveCoord(){
        saveX = this.x;
        saveY = this.y;
    }
    public void mdin(int mas){
        this.din = this.din+mas;
    }
    
    public void abrirC(Cueva a){
        Random rand = new Random();
        int  x = a.rt(a.cuad, this.x, this.y);
        for (int i = 0; i<=x;i++){
        int mon = rand.nextInt(20);
        this.mdin(mon);
        }
    }
    
    public void verificarC(Cueva a,Button btnac){
       int x = a.contarVecinost(a.cuad, this.x, this.y);
        if (x == 0){
            btnac.setVisible(false);
        }else{
            btnac.setVisible(true);
        }
    }
}
