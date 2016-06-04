import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;

public class Main extends Application{

    @Override
    public void start(Stage stage) throws Exception{
        double screenWidth = 1366, screenHeight = 768;
        StackPane root = new StackPane();

        //Cena de entrada
        stage.setScene(inputScene(root, screenWidth, screenHeight));


        stage.setTitle("CubeCracker V. -0.1");
        stage.show();
    }

    private Scene inputScene(StackPane root, double screenWidth, double screenHeight){
        Scene input = new Scene(root, screenWidth, screenHeight);
        Rectangle sidebar;
        Cube cube;
        ColorMenu cMenu;

        root.setStyle("-fx-background-color: #2520ad;"); //Muda cor do background
        sidebar = new Rectangle(screenWidth/4, screenHeight); //Instancia sidebar
        sidebar.setFill(Color.web("0050db"));    //Muda cor da sidebar
        sidebar.setTranslateX(screenWidth/2 - screenWidth/8); //Muda Pos da sidebar
        root.getChildren().add(sidebar); //Adiciona sidebar ao Stackpane
        cMenu = new ColorMenu(root, sidebar.getTranslateX(), 0, screenWidth/27); //Instancia menu de cores
        cube  = new Cube(root, screenWidth/27, screenWidth/-3, 0, cMenu);// Instancia o cubo

        return input; //Retorna a cena criada
    }

    //Metodo main (apenas roda a aplicacao)
    public static void main(String[] args){
        launch(args);
    }
}
