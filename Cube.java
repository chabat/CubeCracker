import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import java.util.Map;

public class Cube extends Rotations{  //Cubo
    StackPane root; //Stack pane
    double cubiesSize, cubeX, cubeY;    //Tamanho de cada cubie e posicao da face esquerda
    Face left, up, back, front, right, bottom; // as 6 faces
    Map<Color, Character> colorToChar;
    Map<Character, Color> charToColor;

    Cube(StackPane r, double cS, double cX, double cY, ColorMenu cMenu){ //cosntrutor
        this.cubiesSize = cS;   //inicializa parametros
        this.cubeX = cX;
        this.cubeY = cY;
        this.root = r;
        //Instancia cada face, uma proxima da outra,Cada face recebe a referencia do menu de cores
        this.left = new Face(root, cubeX, cubeY, cubiesSize, cMenu, colorToChar, charToColor);
        this.front = new Face(root, cubeX + 3 * cubiesSize, cubeY, cubiesSize, cMenu, colorToChar, charToColor);
        this.bottom = new Face(root, cubeX + 3 * cubiesSize, cubeY - 3 * cubiesSize, cubiesSize, cMenu, colorToChar, charToColor);
        this.up = new Face(root, cubeX + 3 * cubiesSize, cubeY + 3 * cubiesSize, cubiesSize, cMenu, colorToChar, charToColor);
        this.right = new Face(root, cubeX + 6 * cubiesSize, cubeY, cubiesSize, cMenu, colorToChar, charToColor);
        this.back = new Face(root, cubeX + 9 * cubiesSize, cubeY, cubiesSize, cMenu, colorToChar, charToColor);

        this.colorToChar.put(Color.BLUE, 'B'); this.charToColor.put('B', Color.BLUE);
        this.colorToChar.put(Color.WHITE, 'W'); this.charToColor.put('W', Color.WHITE);
        this.colorToChar.put(Color.GREEN, 'G'); this.charToColor.put('G', Color.GREEN);
        this.colorToChar.put(Color.RED, 'R'); this.charToColor.put('R', Color.RED);
        this.colorToChar.put(Color.ORANGE, 'O'); this.charToColor.put('O', Color.ORANGE);
        this.colorToChar.put(Color.YELLOW, 'Y'); this.charToColor.put('Y', Color.YELLOW);
    }

    private void rotateFace(char[][] face, boolean antiClockWise){
        char[][] tmp = new char[3][3];
        int i, j;

        //salva a face
        for(i = 0; i < 3; i++)
            for(j = 0; j < 3; j++) tmp[i][j] = face[i][j];

        if(!antiClockWise){
            //Primeira e ultima linha
            for(j = 0, i = 2; j < 3; j++, i--)
                {face[0][j] = tmp[i][0]; face[2][j] = tmp[i][2];}
            //Arruma os "meios"
            face[1][0] = tmp[2][1]; face[1][2] = tmp[0][1];
        }
        else{
            //Primeira e ultima linha
            for(j = 0, i = 0; j < 3; j++, i++)
                {face[0][j] = tmp[i][2]; face[2][j] = tmp[i][0];}
            //Arruma os "meios"
            face[1][0] = tmp[0][1]; face[1][2] = tmp[2][1];
        }
    }

    private void copyLine(char[][] orig, char[][] dest, int origLine,  int destLine,
                                    boolean horizontal, boolean reverse, int type){
        int i, j;
        //Se for linha para linha ou coluna para coluna
        if(type == 0){
            // se a linha for horizontal
            if(horizontal){
                if(reverse) for(j = 2, i = 0; j > 0; j--, i++) dest[destLine][i] = orig[origLine][j];
                else for(j = 0; j < 3; j++) dest[destLine][j] = orig[origLine][j];
            }
            //se a linha for vertical
            else if(reverse) for(i = 2, j = 0; i >= 0 ; i--, j++) dest[j][destLine] = orig[i][origLine];
            else for(i = 0; i < 3; i++) dest[i][destLine] = orig[i][origLine];
        }
        //Se for linha para coluna
        else if(type == 1){
            if(reverse) for(j = 2, i = 0; j >= 0; j--, i++) dest[i][destLine] = orig[origLine][j];
            else for(i = 0; i < 3; i++) dest[i][destLine] = orig[origLine][i];
        }
        //Se for coluna para linha
        else if(type == 2){
            if(reverse) for(i = 2, j = 0; i >= 0; i--, j++) dest[destLine][j] = orig[i][origLine];
            else for(j = 0; j < 3; j++) dest[destLine][j] = orig[j][origLine];
        }

    }

    private void cubeRotate(String direction){
        char[][] tmp = new char[3][3];

        //left
        if(direction == "L "){
            copyLine(this.front.symbols, tmp, 0, 0, false, false, 0); //ok
            copyLine(this.up.symbols, this.front.symbols, 0, 0, false, false, 0); //ok
            copyLine(this.back.symbols, this.up.symbols, 2, 0, false, true, 0); //ok
            copyLine(this.bottom.symbols, this.back.symbols, 0, 2, false, true, 0);//ok
            copyLine(tmp, this.bottom.symbols, 0, 0, false, false, 0);//ok
            rotateFace(this.left.symbols, false);// ok
        }
        //Left'
        else if(direction == "L'"){
            copyLine(this.front.symbols, tmp, 0, 0, false, false, 0); //ok
            copyLine(this.bottom.symbols, this.front.symbols, 0, 0, false, false, 0); //ok
            copyLine(this.back.symbols, this.bottom.symbols, 2, 0, false, true, 0); //ok
            copyLine(this.up.symbols, this.back.symbols, 0, 2, false, true, 0); //ok
            copyLine(tmp, this.up.symbols, 0, 0, false, false, 0);
            rotateFace(this.left.symbols, true);
        }
        //right
        else if(direction == "R "){
            copyLine(this.front.symbols, tmp, 2, 2, false, false, 0); //ok
            copyLine(this.bottom.symbols, this.front.symbols, 2, 2, false, false, 0); //ok
            copyLine(this.back.symbols, this.bottom.symbols, 0, 2, false, true, 0); //ok
            copyLine(this.up.symbols, this.back.symbols, 2, 0, false, true, 0); //ok
            copyLine(tmp, this.up.symbols, 2, 2, false, false, 0); //ok
            rotateFace(this.right.symbols, false);
        }
        //right'
        else if(direction == "R'"){
            copyLine(this.front.symbols, tmp, 2, 2, false, false, 0); //ok
            copyLine(this.up.symbols, this.front.symbols, 2, 2, false, false, 0); //ok
            copyLine(this.back.symbols, this.up.symbols, 0, 2, false, true, 0); // ok
            copyLine(this.bottom.symbols, this.back.symbols, 2, 0, false, true, 0); //ok
            copyLine(tmp, this.bottom.symbols, 2, 2, false, false, 0); //ok
            rotateFace(this.right.symbols, true);
        }
        //up
        else if(direction == "U "){
            copyLine(this.front.symbols, tmp, 0, 0, true, false, 0); //ok
            copyLine(this.right.symbols, this.front.symbols, 0, 0, true, false, 0); //ok
            copyLine(this.back.symbols, this.right.symbols, 0, 0, true, false, 0); //ok
            copyLine(this.left.symbols, this.back.symbols, 0, 0, true, false, 0); //ok
            copyLine(tmp, this.left.symbols, 0, 0, true, false, 0); //ok
            rotateFace(this.up.symbols, false);
        }
        //up'
        else if(direction == "U'"){
            copyLine(this.front.symbols, tmp, 0, 0, true, false, 0); //ok
            copyLine(this.left.symbols, this.front.symbols, 0, 0, true, false, 0); //ok
            copyLine(this.back.symbols, this.left.symbols, 0, 0, true, false, 0); //ok
            copyLine(this.right.symbols, this.back.symbols, 0, 0, true, false, 0); //ok
            copyLine(tmp, this.right.symbols, 0, 0, true, false, 0); //ok
            rotateFace(this.up.symbols, true);
        }
        //down
        else if(direction == "D "){
            copyLine(this.front.symbols, tmp, 2, 2, true, false, 0); //ok
            copyLine(this.left.symbols, this.front.symbols, 2, 2, true, false, 0);
            copyLine(this.back.symbols, this.left.symbols, 2, 2, true, false, 0);
            copyLine(this.right.symbols, this.back.symbols, 2, 2, true, false, 0);
            copyLine(tmp, this.right.symbols, 2, 2, true, false, 0);
            rotateFace(this.bottom.symbols, false);
        }
        //down'
        else if(direction == "D'"){
            copyLine(this.front.symbols, tmp, 2, 2, true, false, 0); //ok
            copyLine(this.right.symbols, this.front.symbols, 2, 2, true, false, 0);
            copyLine(this.back.symbols, this.right.symbols, 2, 2, true, false, 0);
            copyLine(this.left.symbols, this.back.symbols, 2, 2, true, false, 0);
            copyLine(tmp, this.left.symbols, 2, 2, true, false, 0);
            rotateFace(this.bottom.symbols, true);
        }
        //copyLine(Origem,Destino,Linha da origem, linha do destino, horizontal, inversa, tipo)
        //front
        else if(direction == "F "){
            copyLine(this.up.symbols, tmp, 2, 2, true, false, 0);   //ok
            copyLine(this.left.symbols, this.up.symbols, 2, 2, false, true, 2); //ok
            copyLine(this.bottom.symbols, this.left.symbols, 0, 2, false, false, 1); //ok
            copyLine(this.right.symbols, this.bottom.symbols, 0, 0, false, true, 2); //ok
            copyLine(tmp, this.right.symbols, 2, 0, false, false, 1); //ok
            rotateFace(this.front.symbols, false);
        }
        else if(direction == "F'"){
            copyLine(this.up.symbols, tmp, 2, 2, true, false, 0); // ok
            copyLine(this.right.symbols, this.up.symbols, 0, 2, false, false, 2); //ok
            copyLine(this.bottom.symbols, this.right.symbols, 0, 0, false, true, 1); //ok
            copyLine(this.left.symbols, this.bottom.symbols, 2, 0, false, false, 2); //ok
            copyLine(tmp, this.left.symbols, 2, 2, false, true, 1);
            rotateFace(this.front.symbols, true);
        }
        else if(direction == "B "){
            copyLine(this.up.symbols, tmp, 0, 0, true, false, 0); // ok
            copyLine(this.right.symbols, this.up.symbols, 2, 0, false, false, 2); //ok
            copyLine(this.bottom.symbols, this.right.symbols, 2, 2, false, true, 1); //ok
            copyLine(this.left.symbols, this.bottom.symbols, 0, 2, false, false, 2); // ok
            copyLine(tmp, this.left.symbols, 0, 0, false, true, 1);
            rotateFace(this.back.symbols, false);
        }
        else if(direction == "B'"){
            copyLine(this.up.symbols, tmp, 0, 0, true, false, 0); // ok
            copyLine(this.left.symbols, this.up.symbols, 0, 0, false, true, 2); //ok
            copyLine(this.bottom.symbols, this.left.symbols, 2, 0, false, false, 1); //ok
            copyLine(this.right.symbols, this.bottom.symbols, 2, 2, false, true, 2); // ok
            copyLine(tmp, this.right.symbols, 0, 2, false, false, 1); //ok
            rotateFace(this.back.symbols, true);
        }
    }
}
