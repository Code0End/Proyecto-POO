/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darkroom;

import entidad.Jugador;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import mapa.Cueva;
import mapa.Mapa;

/**
 *
 * @author Gerònimo
 */
public class DarkRoom extends Application {
    
    public Mapa overworld;
    public Jugador player;
    public Pane root;
    public GraphicsContext grafico;
    public Scene scene;
    public Cueva cave;
    
    @Override
    public void start(Stage primaryStage) {
        root = new Pane();
        scene = new Scene(root, 976, 976);
        Canvas lienzo = new Canvas(976, 976);
        root.getChildren().add(lienzo);
        grafico = lienzo.getGraphicsContext2D();
        cave = new Cueva(1, 2);
       
        Button btnInicio = new Button();
        Button btnContinuar = new Button();
        Button btnSalir = new Button();
       
        btnInicio.setText("Iniciar Partida");
        btnContinuar.setText("Continuar Partida");
        btnSalir.setText("Salir");
       
        btnInicio.setLayoutX(350);
        btnContinuar.setLayoutX(350);
        btnSalir.setLayoutX(350);
       
        btnInicio.setLayoutY(150);
        btnContinuar.setLayoutY(250);
        btnSalir.setLayoutY(350);
        
        root.getChildren().add(btnInicio);
        root.getChildren().add(btnContinuar);
        root.getChildren().add(btnSalir);
       
        btnInicio.setOnAction((ActionEvent event) -> {
            menuOff(btnInicio, btnContinuar, btnSalir);
            player = new Jugador();
            overworld = new Mapa(61, 61);
            overworld.generarMapa();
            jugar(primaryStage);
        });
        
        btnContinuar.setOnAction((ActionEvent event) -> {
            menuOff(btnInicio, btnContinuar, btnSalir);
            System.out.println("Conitnar partida");
        });
        
        btnSalir.setOnAction((ActionEvent event) -> {
            System.out.println("Salir");
        });
        
        primaryStage.setTitle("Dark Room");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public void jugar(Stage primaryStage){
        Button btnIzq = new Button();
        Button btnDer = new Button();
        Button btnArriba = new Button();
        Button btnAbajo = new Button();

        Button btnDerC = new Button();
        Button btnIzqC = new Button();
        Button btnArrC = new Button();
        Button btnAbaC = new Button();
        Button btnac = new Button();
        
        btnIzq.setText("←");
        btnDer.setText("→");
        btnArriba.setText("↑");
        btnAbajo.setText("↓");
        
        btnIzq.setLayoutX(900);
        btnDer.setLayoutX(950);
        btnArriba.setLayoutX(925);
        btnAbajo.setLayoutX(925);
        
        btnIzq.setLayoutY(300);
        btnDer.setLayoutY(300);
        btnArriba.setLayoutY(275);
        btnAbajo.setLayoutY(325);
        
        root.getChildren().add(btnIzq);
        root.getChildren().add(btnDer);
        root.getChildren().add(btnAbajo);
        root.getChildren().add(btnArriba);
        
        overworld.imprimirMapa(overworld.cuad, grafico);
        player.imprimirJugador(grafico);
        
        btnDer.setOnAction((ActionEvent event) -> {
            player.moveRight(player, grafico);
            if(verificarCueva() == true){
                grafico.clearRect(0, 0, 976, 976);
                buttonOff(btnIzq, btnDer, btnAbajo, btnArriba);
                player.saveCoord();
                cave = jugarCueva();
                buttonOn(btnIzqC, btnDerC, btnAbaC, btnArrC);
            }
            overworld.imprimirMapa(overworld.cuad, grafico);
            player.imprimirJugador(grafico);
        });
        
        btnIzq.setOnAction((ActionEvent event) -> {
            player.moveLeft(player, grafico);
            if(verificarCueva() == true){
                grafico.clearRect(0, 0, 976, 976);
                buttonOff(btnIzq, btnDer, btnAbajo, btnArriba);
                player.saveCoord();
                cave = jugarCueva();
                buttonOn(btnIzqC, btnDerC, btnAbaC, btnArrC);
            }
            overworld.imprimirMapa(overworld.cuad, grafico);
            player.imprimirJugador(grafico);
        });
        
        btnAbajo.setOnAction((ActionEvent event) -> {
            player.moveDown(player, grafico);
            if(verificarCueva() == true){
                grafico.clearRect(0, 0, 976, 976);
                buttonOff(btnIzq, btnDer, btnAbajo, btnArriba);
                player.saveCoord();
                cave = jugarCueva();
                buttonOn(btnIzqC, btnDerC, btnAbaC, btnArrC);
            }
            overworld.imprimirMapa(overworld.cuad, grafico);
            player.imprimirJugador(grafico);
        });
        
        btnArriba.setOnAction((ActionEvent event) -> {
            player.moveUp(player, grafico);
            if(verificarCueva() == true){
                grafico.clearRect(0, 0, 976, 976);
                buttonOff(btnIzq, btnDer, btnAbajo, btnArriba);
                player.saveCoord();
                cave = jugarCueva();
                buttonOn(btnIzqC, btnDerC, btnAbaC, btnArrC);
            }
            overworld.imprimirMapa(overworld.cuad, grafico);
            player.imprimirJugador(grafico);
        });
        
        ///Botones para Cueva
        
        btnDerC.setText("d");
        btnIzqC.setText("i");
        btnArrC.setText("ar");
        btnAbaC.setText("ab");
        btnac.setText("Abrir cofre");
        Label din = new Label(Integer.toString(player.din));

        btnIzqC.setLayoutX(900);
        btnDerC.setLayoutX(950);
        btnArrC.setLayoutX(925);
        btnAbaC.setLayoutX(925);
        din.setLayoutX(500);
        din.setLayoutY(0);
        btnIzqC.setLayoutY(300);
        btnDerC.setLayoutY(300);
        btnArrC.setLayoutY(275);
        btnAbaC.setLayoutY(325);
        btnac.setLayoutX(900);
        btnac.setLayoutY(350);

        root.getChildren().add(btnIzqC);
        root.getChildren().add(btnDerC);
        root.getChildren().add(btnAbaC);
        root.getChildren().add(btnArrC);
        root.getChildren().add(btnac);
        root.getChildren().add(din);
        btnac.setVisible(false);

        btnDerC.setOnAction((ActionEvent event) -> {
            if(cave.cuadC[player.x+1][player.y] == 0){
                grafico.clearRect(0, 0, 976, 976);
                cave.imprimirCuad(cave.cuad, grafico);
                player.moveRight(player,grafico);
                player.verificarC(cave,btnac);
                cave.combatir(player.x, player.y);
                if(cave.cuad[player.x][player.y] == 9){
                    buttonOn(btnIzq, btnDer, btnAbajo, btnArriba);
                    buttonOff(btnIzqC, btnDerC, btnAbaC, btnArrC);
                    player.x = player.saveX;
                    player.y = player.saveY;
                }
            }
        });

        btnIzqC.setOnAction((ActionEvent event) -> {
            if(cave.cuadC[player.x-1][player.y] == 0){
                grafico.clearRect(0, 0, 976, 976);
                cave.imprimirCuad(cave.cuad, grafico);
                player.moveLeft(player,grafico);
                player.verificarC(cave,btnac);
                cave.combatir(player.x, player.y);
                if(cave.cuad[player.x][player.y] == 9){
                    buttonOn(btnIzq, btnDer, btnAbajo, btnArriba);
                    buttonOff(btnIzqC, btnDerC, btnAbaC, btnArrC);
                    player.x = player.saveX;
                    player.y = player.saveY;
                }
            }
        });

        btnArrC.setOnAction((ActionEvent event) -> {
            if(cave.cuadC[player.x][player.y-1] == 0){
                grafico.clearRect(0, 0, 976, 976);
                cave.imprimirCuad(cave.cuad, grafico);
                player.moveUp(player,grafico);
                player.verificarC(cave,btnac);
                cave.combatir(player.x, player.y);
                if(cave.cuad[player.x][player.y] == 9){
                    buttonOn(btnIzq, btnDer, btnAbajo, btnArriba);
                    buttonOff(btnIzqC, btnDerC, btnAbaC, btnArrC);
                    player.x = player.saveX;
                    player.y = player.saveY;
                }
            }
        });
        
        btnAbaC.setOnAction((ActionEvent event) -> {
            if(cave.cuadC[player.x][player.y+1] == 0){
                grafico.clearRect(0, 0, 976, 976);
                cave.imprimirCuad(cave.cuad, grafico);
                player.moveDown(player,grafico);
                player.verificarC(cave,btnac);
                cave.combatir(player.x, player.y);
                if(cave.cuad[player.x][player.y] == 9){
                    buttonOn(btnIzq, btnDer, btnAbajo, btnArriba);
                    buttonOff(btnIzqC, btnDerC, btnAbaC, btnArrC);
                    player.x = player.saveX;
                    player.y = player.saveY;
                }
            }
        });
        
        btnac.setOnAction((ActionEvent event) -> {
            player.abrirC(cave);
            cave.imprimirCuad(cave.cuad, grafico);
                player.imprimirJugador(grafico);
                btnac.setVisible(false);
                din.setText(Integer.toString(player.din));
                
            
        });
       
            
           

        buttonOff(btnIzqC, btnDerC, btnAbaC, btnArrC);
        buttonOff(btnIzqC, btnDerC, btnAbaC, btnArrC);
        buttonOff(btnIzqC, btnDerC, btnAbaC, btnArrC);
        buttonOff(btnIzqC, btnDerC, btnAbaC, btnArrC);
    }
    
    public void menuOn(Button a, Button b, Button c){
        a.setVisible(true);
        b.setVisible(true);
        c.setVisible(true);
    }
    
    public void menuOff(Button a, Button b, Button c){
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
    }
    
    public void buttonOn(Button a, Button b, Button c, Button d){
        a.setVisible(true);
        b.setVisible(true);
        c.setVisible(true);
        d.setVisible(true);
    }

    public void buttonOff(Button a, Button b, Button c, Button d){
        a.setVisible(false);
        b.setVisible(false);
        c.setVisible(false);
        d.setVisible(false);
    }
    
    public boolean verificarCueva(){
        if(overworld.cuad[player.x][player.y] == 5){ // 5 representa que hay una cueva
            return true;
        }
        return false;
    }
    
    public Cueva jugarCueva(){
        player.x = 13;
        player.y = 13;
        Cueva cave = new Cueva(31, 31);
        cave.generarCueva();
        for(int i = 0; i < 4; i++){
            cave.cuad = cave.simularPaso(cave.cuad);
        }
        cave.ponerPolvo(cave.cuad);
        cave.ponerTesoro(cave.cuad);
        cave.generarColisiones(cave.cuad);
        player.verificarS(cave.cuadC);
        cave.cuad[player.x][player.y + 1] = 9;
        cave.imprimirCuad(cave.cuad, grafico);
        
        player.imprimirJugador(grafico);
        return cave;        
    }
        
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}