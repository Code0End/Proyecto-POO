/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mapa;

import java.util.Random;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 *
 * @author Gerònimo
 */
public class Mapa {
    public int cuad[][];
    public int filas;
    public int columnas;
    
    public Mapa(int _filas, int _columnas){
        this.columnas = _columnas;
        this.filas = _filas;
    }
    
    public void generarMapa(){
        Random rand = new Random();
        int probZonas = 30;
        int [][] matriz = new int [this.filas][this.columnas];
        
        //Generacion Overworld
        
        for(int i = 0; i < this.filas; i++){
            for(int j = 0; j < this.columnas; j++){
                if(i >= 0 && i <= 60 && j >= 0 && j <= 60){
                    matriz[i][j] = 3;
                }

                if(i >= 15 && i <= 45 && j >= 15 && j <= 45 ){
                	matriz[i][j] =2;
                }
                if(i >=  25 && i <= 35 && j >= 25 && j <= 35){
                	matriz[i][j] = 1;
                }
            }
        }
        
        //Reduccion de limites bruscos
        
        for(int i = 0; i < this.filas; i++){
            for(int j = 0; j < this.columnas; j++){
                if(((i >= 0 && i <= 3) || (i >= 57 && i <= 60))){
                    if(rand.nextInt(100) < probZonas){
                        matriz[i][j] = 4;
                    }
                }
                if(((j >= 0 && j <= 3) || (j >= 57 && j <= 60))){
                    if(rand.nextInt(100) < probZonas){
                        matriz[i][j] = 4;
                    }
                }
                
                if(((i >= 13 && i <= 17) || (i >= 43 && i <= 47)) && ((j >= 13) && (j <= 47))){
                    if(rand.nextInt(100) < probZonas){
                        matriz[i][j] = 3;
                    }else {
                        matriz[i][j] = 2;
                    }
                }
                if(((j >= 13 && j <= 17) || (j >= 43 && j <= 47)) && ((i >= 13) && (i <= 47))){
                    if(rand.nextInt(100) < probZonas){
                        matriz[i][j] = 3;
                    }else {
                        matriz[i][j] = 2;
                    }
                }
                
                if(((i >= 23 && i <= 28) || (i >= 33 && i <= 38)) && ((j >= 23) && (j <= 38))){
                    if(rand.nextInt(100) < probZonas){
                        matriz[i][j] = 2;
                    }else {
                        matriz[i][j] = 1;
                    }
                }
                if(((j >= 23 && j <= 28) || (j >= 33 && j <= 38)) && ((i >= 23) && (i <= 38))){
                    if(rand.nextInt(100) < probZonas - 5){
                        matriz[i][j] = 2;
                    }else {
                        matriz[i][j] = 1;
                    }
                }
            }
        }
        
        //Generacion Cuevas en Overworld
        double probCueva = 100.0;
        
        for(int i = 0; i < this.filas; i++){
            for(int j = 0; j < this.columnas; j++){
                if(rand.nextInt(100) < probCueva){
                    matriz[i][j] = 5;
                    probCueva = 0;
                }else{
                    probCueva = probCueva + .025;
                }
            }
        }
        
        this.cuad = matriz;
    }
    
    public void imprimirMapa(int cuad[][], GraphicsContext grafico){
        Image m1 = new Image("mapa/tiles/tile1.png");
        Image m2 = new Image("mapa/tiles/tile2.png");
        Image m3 = new Image("mapa/tiles/tile3.png");
        Image m4 = new Image("mapa/tiles/tile4.png");
        Image m5 = new Image("mapa/tiles/tile5.png");
        for(int x = 0; x < this.filas; x++){
            for(int y = 0; y < this.columnas; y++){
                if(cuad[x][y] == 1)
                    grafico.drawImage(m1, x*16, y*16);
                if(cuad[x][y] == 2)
                    grafico.drawImage(m2, x*16, y*16);
                if(cuad[x][y] == 3)
                    grafico.drawImage(m3, x*16, y*16);
                if(cuad[x][y] == 4)
                    grafico.drawImage(m4, x*16, y*16);
                if(cuad[x][y] == 5)
                    grafico.drawImage(m5, x*16, y*16);
            }
        }
    }
    
    
}
