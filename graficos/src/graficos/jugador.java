/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graficos;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import java.util.Random;
import javafx.scene.control.Button;

/**
 *
 * @author LOLO
 */
public class jugador {
   public int pos_x;
   public int pos_y;
   public int din;
   public int ent;
    
    jugador(){
        this.pos_x = 12;
        this.pos_y = 12;
        this.din = 0;
        this.ent = 0;
    }
    
    public void moverIzq(jugador p1,GraphicsContext grafico){
        this.pos_x = this.pos_x-1;
        p1.imprimirJugador(grafico);
        
    }
    
    public void moverDer(jugador p1,GraphicsContext grafico){
        this.pos_x = this.pos_x+1;
        p1.imprimirJugador(grafico);
        
    }
    
    public void moverArriba(jugador p1,GraphicsContext grafico){
        this.pos_y = this.pos_y-1;
        p1.imprimirJugador(grafico);
        
    }
    
    public void moverAbajo(jugador p1,GraphicsContext grafico){
        this.pos_y = this.pos_y+1;
        p1.imprimirJugador(grafico);
        
    }
    
    public void verificarS(int[][] collisionador){
        if(this.ent == 0){
            while(collisionador[this.pos_x][this.pos_y] == 1 ){
                this.pos_x = this.pos_x-1;
                if(this.pos_x <= 0){
                    this.ent = 1;
                    break;
                }
            }
        }
        if(this.ent == 1){
            while(collisionador[this.pos_x][this.pos_y] == 1 ){
                this.pos_x = this.pos_x+1;
                if(this.pos_x >= 30){
                    while(collisionador[this.pos_x][this.pos_y] == 1){
                        this.pos_x = this.pos_x-1;
                    }
                    this.ent = 0;
                    break;
                }
            }  
        }
    }
    
    public void imprimirJugador(GraphicsContext grafico){
        int x = this.pos_x;
        int y = this.pos_y;
        
        Image mp = new Image("graficos/secuencia/jugador1.png");
        grafico.drawImage(mp,x*16,y*16);
    }
    
    public void mdin(int mas){
        this.din = this.din+mas;
    }
    
    public void abrirC(mapa a){
        Random rand = new Random();
        int  x = a.rt(a.cuad, this.pos_x, this.pos_y);
        for (int i = 0; i<=x;i++){
        int mon = rand.nextInt(20);
        this.mdin(mon);
        }
    }
    
    public void verificarC(mapa a,Button btnac){
       int x = a.contarVecinost(a.cuad, this.pos_x, this.pos_y);
        if (x == 0){
            btnac.setVisible(false);
        }else{
            btnac.setVisible(true);
        }
    }
    
    
    
}
