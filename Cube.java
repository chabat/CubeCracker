import javafx.scene.layout.StackPane;

public class Cube{  //Cubo
    StackPane root; //Stack pane
    double cubiesSize, cubeX, cubeY;    //Tamanho de cada cubie e posicao da face esquerda
    Face left, up, back, front, right, bottom; // as 6 faces

    Cube(StackPane r, double cS, double cX, double cY, ColorMenu cMenu){ //cosntrutor
        this.cubiesSize = cS;   //inicializa parametros
        this.cubeX = cX;
        this.cubeY = cY;
        this.root = r;

        Face left = new Face(root, cubeX, cubeY, cubiesSize, cMenu); //Instancia cada face, uma proxima da outra
        Face up = new Face(root, cubeX + 3 * cubiesSize, cubeY, cubiesSize, cMenu); //Cada face recebe a referencia do menu de cores
        Face back = new Face(root, cubeX + 3 * cubiesSize, cubeY - 3 * cubiesSize, cubiesSize, cMenu);
        Face front = new Face(root, cubeX + 3 * cubiesSize, cubeY + 3 * cubiesSize, cubiesSize, cMenu);
        Face right = new Face(root, cubeX + 6 * cubiesSize, cubeY, cubiesSize, cMenu);
        Face bottom = new Face(root, cubeX + 9 * cubiesSize, cubeY, cubiesSize, cMenu);
    }
}
