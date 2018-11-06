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
public class Cueva extends Mapa{
    
    public int cuadC [][];
    public int limiteN = 4;
    public int limiteM = 3;
    public static int contadorCombates=0;
    
    public Cueva(int _columnas, int _filas) {
        super(_columnas, _filas);
    }
    
    public int[][] getCuad() {
        return cuad;
    }

    public void setCuad(int[][] cuad) {
        this.cuad = cuad;
    }
    
    public void generarCueva(){
        Random rand = new Random();
        int[][] multi = new int[this.filas][this.columnas];
        int prob1 = 40;
    
        for(int x=0; x<this.filas; x++){
            for(int y=0; y<this.columnas; y++){
                if(rand.nextInt(100) < prob1){
                    multi[x][y] = 2;
                }else{
                    multi[x][y] = 1;
                }
            }
        }
        this.cuad = multi;
    }
    
    public int[][] simularPaso(int[][] vMap){
        int[][] nMap = new int[this.filas][this.columnas];
        for(int x=0; x<vMap.length; x++){
            for(int y=0; y<vMap[0].length; y++){
                int nbs = contarVecinosVivos(vMap, x, y);
                if(vMap[x][y] == 2){
                    if(nbs < limiteM){
                        nMap[x][y] = 1;
                    }
                    else{
                        nMap[x][y] = 2;
                    }
                } 
                else{
                    if(nbs > limiteN){
                        nMap[x][y] = 2;
                    }
                    else{
                        nMap[x][y] = 1;
                    }
                }
            }
        }
        return nMap;
    }
    
    
    
public int contarVecinosVivos(int[][] map, int x, int y){
    int cuenta = 0;
    for(int i=-1; i<2; i++){
        for(int j=-1; j<2; j++){
            int vecino_x = x+i;
            int vecino_y = y+j;
            if(i == 0 && j == 0){
            }            
            else if(vecino_x < 0 || vecino_y < 0 || vecino_x >= map.length || vecino_y >= map[0].length){
                cuenta = cuenta + 1;
            }
            else if(map[vecino_x][vecino_y] == 2){
                cuenta = cuenta + 1;
            }
        }
    }
    return cuenta;
}

    public int contarVecinost(int[][] map, int x, int y){
        int cuenta = 0;
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                int vecino_x = x+i;
                int vecino_y = y+j;
                if(i == 0 && j == 0){
                }
            
                else if(map[vecino_x][vecino_y] == 3){
                    cuenta = cuenta + 1;
                }
            }
        }
        return cuenta;
    }

    public int rt(int[][] map, int x, int y){
        int cuenta = 0;
        for(int i=-1; i<2; i++){
            for(int j=-1; j<2; j++){
                int vecino_x = x+i;
                int vecino_y = y+j;
                if(map[vecino_x][vecino_y] == 3){
                    cuenta++;
                    map[vecino_x][vecino_y] = 6;
                }
            }
        }
        return cuenta;
    }

    public void ponerPolvo(int[][] map){
        Random rand = new Random();
        int piso;
        for (int x=0; x < this.filas; x++){
            for (int y=0; y < this.columnas; y++){
                if(map[x][y] == 1){
                    piso = rand.nextInt(100);  
                    if(piso < 100)
                        this.cuad[x][y] = 1;
                    if(piso < 20)
                        this.cuad[x][y] = 5;
                    if(piso < 10)
                        this.cuad[x][y] = 4;
                }
            }
        }
    }

    public void ponerTesoro(int[][] mapa){
  
    int tesoroescondido = 5;
    for (int x=0; x < this.filas; x++){
        for (int y=0; y < this.columnas; y++){
            if(mapa[x][y] == 1 || mapa[x][y] == 4 || mapa[x][y] == 5){
                int nbs = contarVecinosVivos(mapa, x, y);
                if(nbs >= tesoroescondido){
                    this.cuad[x][y] = 3;
                }
            }
        }
    }
}

    public void generarColisiones(int [][] mapa){
        int[][] multi = new int[this.filas][this.columnas];
        for (int x=0; x < this.filas; x++){
            for (int y=0; y < this.columnas; y++){
                if(mapa[x][y] == 2 || mapa[x][y] == 3){
                    multi[x][y] = 1;
                }else{
                    multi[x][y] = 0;
                }
            }
        }
        this.cuadC = multi;
    }
    
    public void imprimirCuad(int cuad[][],GraphicsContext grafico){
        Image m1 = new Image("mapa/tilesCueva/tile1.png");
        Image m2 = new Image("mapa/tilesCueva/tile2.png");
        Image m3 = new Image("mapa/tilesCueva/tiletesoro.png");
        Image m4 = new Image("mapa/tilesCueva/tile4.png");
        Image m5 = new Image("mapa/tilesCueva/tile4.png");
        Image m6 = new Image("mapa/tilesCueva/tiletesoroa.png");
        Image m7 = new Image("mapa/tilesCueva/tileExit.png");
        for(int x=0;x<this.filas;x++){
            for(int y=0;y<this.columnas;y++){
                if(cuad[x][y] == 1)
                    grafico.drawImage(m1,x*16,y*16);
                if(cuad[x][y] == 2)
                    grafico.drawImage(m2,x*16,y*16);
                if(cuad[x][y] == 3)
                    grafico.drawImage(m3,x*16,y*16);
                if(cuad[x][y] == 4)
                    grafico.drawImage(m4,x*16,y*16);
                if(cuad[x][y] == 5)
                    grafico.drawImage(m5,x*16,y*16);
                if(cuad[x][y] == 6)
                    grafico.drawImage(m6,x*16,y*16);
                if(cuad[x][y] == 9)//Marca la salida
                    grafico.drawImage(m7,x*16,y*16);
            }
        }
        
   }
    public  void combatir(int x, int y){
        Random rand = new Random();
        int propuesta = rand.nextInt(100);
        if (this.cuadC[x][y]==4||this.cuadC[x][y]==5){
            propuesta=propuesta+10;
        }
        if(Cueva.contadorCombates!=0){
            propuesta=propuesta-(Cueva.contadorCombates*5);
        }
        if(propuesta>50&&propuesta <=100){
            Cueva.contadorCombates++;
            int probabilidad = rand.nextInt(100);
            if (probabilidad > 0 && probabilidad <40){
                System.out.println("Monstruo 1 apareció");
            }
            else if (probabilidad > 40 && probabilidad < 70){
                System.out.println("Monstruo 2 apareció");
            }
            else if(probabilidad > 70 ){
                System.out.println("Monstruo 3 apareció");
            }
        }
    }
}
