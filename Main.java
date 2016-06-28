/*
Class Main do programa.
Este programa eh um gerado de solucoes para o cubo de rubik atraves de um algoritmo de camadas
o estado atual do cubo eh informado preenchendo o diagrama e o conjunto de instrucoes para resolucao
eh carregado na bairra inferior, para passar para a proxima instrucao deve-se clicar no botao "P"
author: Felipe Chabatura Neto (felipechabat at gmail.com)
*/


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import java.util.List;
import java.util.ArrayList;


public class Main extends Application{
    List<String> moveList = new ArrayList<String>();

    @Override
    public void start(Stage stage) throws Exception{
        double screenWidth = 1366, screenHeight = 768;
        StackPane root = new StackPane();
        Scene input = new Scene(root, screenWidth, screenHeight);
        Rectangle sidebar = initializeSidebar(root, screenWidth, screenHeight);
        ColorMenu cMenu = new ColorMenu(root, sidebar.getTranslateX(), 0, screenWidth/27);
        Cube cube = new Cube(root, screenWidth/27, screenWidth/-3, screenHeight/-4, cMenu);
        Solver solver = new Solver(cube);
        MovePlayer movePlayer = new MovePlayer(100, screenWidth/-3, screenHeight/4  , moveList, root, cube);
        ColorButton solve = initializeSolveButton(root, sidebar, solver, movePlayer);

        root.setStyle("-fx-background-color: #2520ad;"); //Muda cor do background

        //Coloca cena, muda titulo da janela e mostra o palco
        stage.setScene(input);
        stage.setTitle("CubeCracker V. -0.1");
        stage.show();
    }

    private Rectangle initializeSidebar(StackPane root, double screenWidth, double screenHeight){
        Rectangle sidebar = new Rectangle(screenWidth/4, screenHeight); //Instancia sidebar
        sidebar.setFill(Color.web("0050db"));    //Muda cor da sidebar
        sidebar.setTranslateX(screenWidth/2 - screenWidth/8); //Muda Pos da sidebar
        root.getChildren().add(sidebar); //Adiciona sidebar ao Stackpane
        return sidebar;
    }

    private ColorButton initializeSolveButton(StackPane root, Rectangle sidebar, Solver solver, MovePlayer movePlayer){
        ColorButton solve = new ColorButton(200.,100., sidebar.getTranslateX(), 200., Color.GREEN); //Mudar tamanho
        solve.button.setText("RESOLVER");
        root.getChildren().addAll(solve.shape, solve.button);
        solve.button.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event){
                solve.shape.setStroke(Color.RED);
                solver.solve(moveList);
                movePlayer.start();
                for(String tmp: moveList) System.out.print("[" + tmp + "]");
            }
        });
        return solve;
    }

    //Metodo main (apenas roda a aplicacao)
    public static void main(String[] args){
        launch(args);
    }
}
