/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package graficos;
import graficos.mapa;
import graficos.jugador;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
/**
 *
 * @author C0D
 */
public class graficos extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        Button btni = new Button();
        Button btnd = new Button();
        Button btnar = new Button();
        Button btnab = new Button();
        Button btnN = new Button();
        Button btnac = new Button();
        
        btni.setText("←");
        btnd.setText("→");
        btnar.setText("↑");
        btnab.setText("↓");
        btnN.setText("Generar Nuevo Mapa");
        btnac.setText("Abrir cofre");
        
        btni.setLayoutX(500);
        btni.setLayoutY(400);
        btnd.setLayoutX(530);
        btnd.setLayoutY(400);
        btnar.setLayoutX(520);
        btnar.setLayoutY(370);
        btnab.setLayoutX(520);
        btnab.setLayoutY(430);
        btnN.setLayoutX(600);
        btnN.setLayoutY(370);
        btnac.setLayoutX(600);
        btnac.setLayoutY(400);
        
        jugador p1 = new jugador();
        
        Pane root = new Pane();
        Label din = new Label(Integer.toString(p1.din));
        
        din.setLayoutX(500);
        din.setLayoutY(0);
        
        root.getChildren().add(btni);
        root.getChildren().add(btnd);
        root.getChildren().add(btnar);
        root.getChildren().add(btnab);
        root.getChildren().add(btnN);
        root.getChildren().add(btnac);
        root.getChildren().add(din);
        btnac.setVisible(false);
        
        Canvas lienzo = new Canvas(500,500);
        root.getChildren().add(lienzo);
        GraphicsContext grafico = lienzo.getGraphicsContext2D();
        
        Image mb = new Image("graficos/secuencia/fondo1.png");
        grafico.drawImage(mb,0,0);
        mapa map = new mapa();
        
         btni.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(map.cuadC[p1.pos_x-1][p1.pos_y] == 0){
                    map.imprimirCuad(map.cuad, grafico);
                    p1.moverIzq(p1,grafico);
                    p1.verificarC(map,btnac);
                    map.combatir(p1.pos_x, p1.pos_y);
                }
            }
        });
        btnd.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(map.cuadC[p1.pos_x+1][p1.pos_y] == 0){
                    map.imprimirCuad(map.cuad, grafico);
                    p1.moverDer(p1,grafico);
                    p1.verificarC(map,btnac);
                    map.combatir(p1.pos_x, p1.pos_y);
                }
            }
        });
        btnar.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(map.cuadC[p1.pos_x][p1.pos_y-1] == 0){
                    map.imprimirCuad(map.cuad, grafico);
                    p1.moverArriba(p1,grafico);
                    p1.verificarC(map,btnac);
                    map.combatir(p1.pos_x, p1.pos_y);
                }
            }
        });
        btnab.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                if(map.cuadC[p1.pos_x][p1.pos_y+1] == 0){
                    map.imprimirCuad(map.cuad, grafico);
                    p1.moverAbajo(p1,grafico);
                    p1.verificarC(map,btnac);
                    map.combatir(p1.pos_x, p1.pos_y);
                }
            }
        });
        btnN.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                map.generarCueva();
                for(int i=0; i<4; i++){
                    map.cuad = map.simularPaso(map.cuad);
                }
                map.ponerPolvo(map.cuad);
                map.ponerTesoro(map.cuad);
                map.generarColisiones(map.cuad);
                p1.verificarS(map.cuadC);
                map.imprimirCuad(map.cuad, grafico);
                p1.imprimirJugador(grafico);
            }   
        });
        
        btnac.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                p1.abrirC(map);
                map.imprimirCuad(map.cuad, grafico);
                p1.imprimirJugador(grafico);
                btnac.setVisible(false);
                din.setText(Integer.toString(p1.din));
            }   
        });
        
        new AnimationTimer(){
            double tiempo;
            int index;
            int x = 0;
            
            @Override
            public void handle(long now) {
                tiempo = now/100000000;  
            }
        }.start();
        
        Scene scene = new Scene(root, 900, 500);
        
        
        primaryStage.setTitle("CUARTO OBSCURO!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}