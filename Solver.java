public abstract class Solver{

    //NAO RETORNA VOID NA VDD
    public static void solve(char[][]up,char[][]bottom, char[][]left,char[][]right,
                                            char[][]front, char[][]back){
        printCube(up, bottom, left, right, front, back);

    }

    public static void cubeRotate(char[][]up,char[][]bottom, char[][]left,char[][]right,
                                char[][]front, char[][]back, String direction){
        char[][] tmp = new char[3][3];

        //left
        if(direction == "L "){
            copyLine(front, tmp, 0, 0, false, false, 0); //ok
            copyLine(up, front, 0, 0, false, false, 0); //ok
            copyLine(back, up, 2, 0, false, true, 0); //ok
            copyLine(bottom, back, 0, 2, false, true, 0);//ok
            copyLine(tmp, bottom, 0, 0, false, false, 0);//ok
            rotateFace(left, false);// ok
        }
        //Left'
        else if(direction == "L'"){
            copyLine(front, tmp, 0, 0, false, false, 0); //ok
            copyLine(bottom, front, 0, 0, false, false, 0); //ok
            copyLine(back, bottom, 2, 0, false, true, 0); //ok
            copyLine(up, back, 0, 2, false, true, 0); //ok
            copyLine(tmp, up, 0, 0, false, false, 0);
            rotateFace(left, true);
        }
        //right
        else if(direction == "R "){
            copyLine(front, tmp, 2, 2, false, false, 0); //ok
            copyLine(bottom, front, 2, 2, false, false, 0); //ok
            copyLine(back, bottom, 0, 2, false, true, 0); //ok
            copyLine(up, back, 2, 0, false, true, 0); //ok
            copyLine(tmp, up, 2, 2, false, false, 0); //ok
            rotateFace(right, false);
        }
        //right'
        else if(direction == "R'"){
            copyLine(front, tmp, 2, 2, false, false, 0); //ok
            copyLine(up, front, 2, 2, false, false, 0); //ok
            copyLine(back, up, 0, 2, false, true, 0); // ok
            copyLine(bottom, back, 2, 0, false, true, 0); //ok
            copyLine(tmp, bottom, 2, 2, false, false, 0); //ok
            rotateFace(right, true);
        }
        //up
        else if(direction == "U "){
            copyLine(front, tmp, 0, 0, true, false, 0); //ok
            copyLine(right, front, 0, 0, true, false, 0); //ok
            copyLine(back, right, 0, 0, true, false, 0); //ok
            copyLine(left, back, 0, 0, true, false, 0); //ok
            copyLine(tmp, left, 0, 0, true, false, 0); //ok
            rotateFace(up, false);
        }
        //up'
        else if(direction == "U'"){
            copyLine(front, tmp, 0, 0, true, false, 0); //ok
            copyLine(left, front, 0, 0, true, false, 0); //ok
            copyLine(back, left, 0, 0, true, false, 0); //ok
            copyLine(right, back, 0, 0, true, false, 0); //ok
            copyLine(tmp, right, 0, 0, true, false, 0); //ok
            rotateFace(up, true);
        }
        //down
        else if(direction == "D "){
            copyLine(front, tmp, 2, 2, true, false, 0); //ok
            copyLine(left, front, 2, 2, true, false, 0);
            copyLine(back, left, 2, 2, true, false, 0);
            copyLine(right, back, 2, 2, true, false, 0);
            copyLine(tmp, right, 2, 2, true, false, 0);
            rotateFace(bottom, false);
        }
        //down'
        else if(direction == "D'"){
            copyLine(front, tmp, 2, 2, true, false, 0); //ok
            copyLine(right, front, 2, 2, true, false, 0);
            copyLine(back, right, 2, 2, true, false, 0);
            copyLine(left, back, 2, 2, true, false, 0);
            copyLine(tmp, left, 2, 2, true, false, 0);
            rotateFace(bottom, true);
        }
        //copyLine(Origem,Destino,Linha da origem, linha do destino, horizontal, inversa, tipo)
        //front
        else if(direction == "F "){
            copyLine(up, tmp, 2, 2, true, false, 0);   //ok
            copyLine(left, up, 2, 2, false, true, 2); //ok
            copyLine(bottom, left, 0, 2, false, false, 1); //ok
            copyLine(right, bottom, 0, 0, false, true, 2); //ok
            copyLine(tmp, right, 2, 0, false, false, 1); //ok
            rotateFace(front, false);
        }
        else if(direction == "F'"){
            copyLine(up, tmp, 2, 2, true, false, 0); // ok
            copyLine(right, up, 0, 2, false, false, 2); //ok
            copyLine(bottom, right, 0, 0, false, true, 1); //ok
            copyLine(left, bottom, 2, 0, false, false, 2); //ok
            copyLine(tmp, left, 2, 2, false, true, 1);
            rotateFace(front, true);
        }
        else if(direction == "B "){
            copyLine(up, tmp, 0, 0, true, false, 0); // ok
            copyLine(right, up, 2, 0, false, false, 2); //ok
            copyLine(bottom, right, 2, 2, false, true, 1); //ok
            copyLine(left, bottom, 0, 2, false, false, 2); // ok
            copyLine(tmp, left, 0, 0, false, true, 1);
            rotateFace(back, false);
        }
        else if(direction == "B'"){
            copyLine(up, tmp, 0, 0, true, false, 0); // ok
            copyLine(left, up, 0, 0, false, true, 2); //ok
            copyLine(bottom, left, 2, 0, false, false, 1); //ok
            copyLine(right, bottom, 2, 2, false, true, 2); // ok
            copyLine(tmp, right, 0, 2, false, false, 1); //ok
            rotateFace(back, true);
        }
    }

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

    private static void printCube(char[][]up,char[][]bottom, char[][]left,char[][]right,
                                            char[][]front, char[][]back){
        //Imprime topo
        for(int i = 0; i < 3; i++){
            System.out.print("    ");
            for(int j = 0; j < 3; j++) System.out.print(up[i][j]);
            System.out.print("\n");
        }
        //Imprime esquerda,frente, direita e tras
        for(int i = 0; i < 3; i++){
            for(int j = 0, k = 0; j < 15; j++){
                if(j == 3 || j == 7 || j == 11){ System.out.print(" "); k = 0;}
                else if(j < 3) System.out.print(left[i][k++]);
                else if(j < 7) System.out.print(front[i][k++]);
                else if(j < 11) System.out.print(right[i][k++]);
                else if(j < 15) System.out.print(back[i][k++]);
            }
            System.out.print("\n");
        }
        //Imprime topo
        for(int i = 0; i < 3; i++){
            System.out.print("    ");
            for(int j = 0; j < 3; j++) System.out.print(bottom[i][j]);
            System.out.print("\n");
        }
        System.out.print("\n\n");
    }
}
