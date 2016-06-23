import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

public class Cube{  //Cubo
    StackPane root; //Stack pane
    double cubiesSize, cubeX, cubeY;    //Tamanho de cada cubie e posicao da face esquerda
    Face left, up, back, front, right, bottom; // as 6 faces
    ColorButton solve;

    Cube(StackPane r, double cS, double cX, double cY, ColorMenu cMenu){ //cosntrutor
        this.cubiesSize = cS;   //inicializa parametros
        this.cubeX = cX;
        this.cubeY = cY;
        this.root = r;
        //Instancia cada face, uma proxima da outra,Cada face recebe a referencia do menu de cores
        this.left = new Face(root, cubeX, cubeY, cubiesSize, cMenu);
        this.front = new Face(root, cubeX + 3 * cubiesSize, cubeY, cubiesSize, cMenu);
        this.up = new Face(root, cubeX + 3 * cubiesSize, cubeY - 3 * cubiesSize, cubiesSize, cMenu);
        this.bottom = new Face(root, cubeX + 3 * cubiesSize, cubeY + 3 * cubiesSize, cubiesSize, cMenu);
        this.right = new Face(root, cubeX + 6 * cubiesSize, cubeY, cubiesSize, cMenu);
        this.back = new Face(root, cubeX + 9 * cubiesSize, cubeY, cubiesSize, cMenu);
    }

}
