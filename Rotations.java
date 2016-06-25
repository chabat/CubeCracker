public abstract class Rotations{

    //Copia uma linha do cubo para outra
    private static void copyLine(char[][] orig, char[][] dest, int origLine,  int destLine,
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

    //Roda uma face do cubo
    private static void rotateFace(char[][] face, boolean antiClockWise){
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

    public static void rotate(Cube cube, String direction){
        char[][] tmp = new char[3][3];

        //left
        if(direction == "L "){
            copyLine(cube.front.symbols, tmp, 0, 0, false, false, 0); //ok
            copyLine(cube.up.symbols, cube.front.symbols, 0, 0, false, false, 0); //ok
            copyLine(cube.back.symbols, cube.up.symbols, 2, 0, false, true, 0); //ok
            copyLine(cube.down.symbols, cube.back.symbols, 0, 2, false, true, 0);//ok
            copyLine(tmp, cube.down.symbols, 0, 0, false, false, 0);//ok
            rotateFace(cube.left.symbols, false);// ok
        }
        //Left'
        else if(direction == "L'"){
            copyLine(cube.front.symbols, tmp, 0, 0, false, false, 0); //ok
            copyLine(cube.down.symbols, cube.front.symbols, 0, 0, false, false, 0); //ok
            copyLine(cube.back.symbols, cube.down.symbols, 2, 0, false, true, 0); //ok
            copyLine(cube.up.symbols, cube.back.symbols, 0, 2, false, true, 0); //ok
            copyLine(tmp, cube.up.symbols, 0, 0, false, false, 0);
            rotateFace(cube.left.symbols, true);
        }
        //right
        else if(direction == "R "){
            copyLine(cube.front.symbols, tmp, 2, 2, false, false, 0); //ok
            copyLine(cube.down.symbols, cube.front.symbols, 2, 2, false, false, 0); //ok
            copyLine(cube.back.symbols, cube.down.symbols, 0, 2, false, true, 0); //ok
            copyLine(cube.up.symbols, cube.back.symbols, 2, 0, false, true, 0); //ok
            copyLine(tmp, cube.up.symbols, 2, 2, false, false, 0); //ok
            rotateFace(cube.right.symbols, false);
        }
        //right'
        else if(direction == "R'"){
            copyLine(cube.front.symbols, tmp, 2, 2, false, false, 0); //ok
            copyLine(cube.up.symbols, cube.front.symbols, 2, 2, false, false, 0); //ok
            copyLine(cube.back.symbols, cube.up.symbols, 0, 2, false, true, 0); // ok
            copyLine(cube.down.symbols, cube.back.symbols, 2, 0, false, true, 0); //ok
            copyLine(tmp, cube.down.symbols, 2, 2, false, false, 0); //ok
            rotateFace(cube.right.symbols, true);
        }
        //up
        else if(direction == "U "){
            copyLine(cube.front.symbols, tmp, 0, 0, true, false, 0); //ok
            copyLine(cube.right.symbols, cube.front.symbols, 0, 0, true, false, 0); //ok
            copyLine(cube.back.symbols, cube.right.symbols, 0, 0, true, false, 0); //ok
            copyLine(cube.left.symbols, cube.back.symbols, 0, 0, true, false, 0); //ok
            copyLine(tmp, cube.left.symbols, 0, 0, true, false, 0); //ok
            rotateFace(cube.up.symbols, false);
        }
        //up'
        else if(direction == "U'"){
            copyLine(cube.front.symbols, tmp, 0, 0, true, false, 0); //ok
            copyLine(cube.left.symbols, cube.front.symbols, 0, 0, true, false, 0); //ok
            copyLine(cube.back.symbols, cube.left.symbols, 0, 0, true, false, 0); //ok
            copyLine(cube.right.symbols, cube.back.symbols, 0, 0, true, false, 0); //ok
            copyLine(tmp, cube.right.symbols, 0, 0, true, false, 0); //ok
            rotateFace(cube.up.symbols, true);
        }
        //down
        else if(direction == "D "){
            copyLine(cube.front.symbols, tmp, 2, 2, true, false, 0); //ok
            copyLine(cube.left.symbols, cube.front.symbols, 2, 2, true, false, 0);
            copyLine(cube.back.symbols, cube.left.symbols, 2, 2, true, false, 0);
            copyLine(cube.right.symbols, cube.back.symbols, 2, 2, true, false, 0);
            copyLine(tmp, cube.right.symbols, 2, 2, true, false, 0);
            rotateFace(cube.down.symbols, false);
        }
        //down'
        else if(direction == "D'"){
            copyLine(cube.front.symbols, tmp, 2, 2, true, false, 0); //ok
            copyLine(cube.right.symbols, cube.front.symbols, 2, 2, true, false, 0);
            copyLine(cube.back.symbols, cube.right.symbols, 2, 2, true, false, 0);
            copyLine(cube.left.symbols, cube.back.symbols, 2, 2, true, false, 0);
            copyLine(tmp, cube.left.symbols, 2, 2, true, false, 0);
            rotateFace(cube.down.symbols, true);
        }
        //copyLine(Origem,Destino,Linha da origem, linha do destino, horizontal, inversa, tipo)
        //front
        else if(direction == "F "){
            copyLine(cube.up.symbols, tmp, 2, 2, true, false, 0);   //ok
            copyLine(cube.left.symbols, cube.up.symbols, 2, 2, false, true, 2); //ok
            copyLine(cube.down.symbols, cube.left.symbols, 0, 2, false, false, 1); //ok
            copyLine(cube.right.symbols, cube.down.symbols, 0, 0, false, true, 2); //ok
            copyLine(tmp, cube.right.symbols, 2, 0, false, false, 1); //ok
            rotateFace(cube.front.symbols, false);
        }
        else if(direction == "F'"){
            copyLine(cube.up.symbols, tmp, 2, 2, true, false, 0); // ok
            copyLine(cube.right.symbols, cube.up.symbols, 0, 2, false, false, 2); //ok
            copyLine(cube.down.symbols, cube.right.symbols, 0, 0, false, true, 1); //ok
            copyLine(cube.left.symbols, cube.down.symbols, 2, 0, false, false, 2); //ok
            copyLine(tmp, cube.left.symbols, 2, 2, false, true, 1);
            rotateFace(cube.front.symbols, true);
        }
        else if(direction == "B "){
            copyLine(cube.up.symbols, tmp, 0, 0, true, false, 0); // ok
            copyLine(cube.right.symbols, cube.up.symbols, 2, 0, false, false, 2); //ok
            copyLine(cube.down.symbols, cube.right.symbols, 2, 2, false, true, 1); //ok
            copyLine(cube.left.symbols, cube.down.symbols, 0, 2, false, false, 2); // ok
            copyLine(tmp, cube.left.symbols, 0, 0, false, true, 1);
            rotateFace(cube.back.symbols, false);
        }
        else if(direction == "B'"){
            copyLine(cube.up.symbols, tmp, 0, 0, true, false, 0); // ok
            copyLine(cube.left.symbols, cube.up.symbols, 0, 0, false, true, 2); //ok
            copyLine(cube.down.symbols, cube.left.symbols, 2, 0, false, false, 1); //ok
            copyLine(cube.right.symbols, cube.down.symbols, 2, 2, false, true, 2); // ok
            copyLine(tmp, cube.right.symbols, 0, 2, false, false, 1); //ok
            rotateFace(cube.back.symbols, true);
        }
    }

    //Imprime uma representacao grafica do cubo no terminal
    public static void printCube(Cube cube){
        //Imprime topo
        for(int i = 0; i < 3; i++){
            System.out.print("    ");
            for(int j = 0; j < 3; j++) System.out.print(cube.up.symbols[i][j]);
            System.out.print("\n");
        }
        //Imprime esquerda,frente, direita e tras
        for(int i = 0; i < 3; i++){
            for(int j = 0, k = 0; j < 15; j++){
                if(j == 3 || j == 7 || j == 11){ System.out.print(" "); k = 0;}
                else if(j < 3) System.out.print(cube.left.symbols[i][k++]);
                else if(j < 7) System.out.print(cube.front.symbols[i][k++]);
                else if(j < 11) System.out.print(cube.right.symbols[i][k++]);
                else if(j < 15) System.out.print(cube.back.symbols[i][k++]);
            }
            System.out.print("\n");
        }
        //Imprime topo
        for(int i = 0; i < 3; i++){
            System.out.print("    ");
            for(int j = 0; j < 3; j++) System.out.print(cube.down.symbols[i][j]);
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }

}
