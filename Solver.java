import java.util.List;

public class Solver{
    Cube cube;

    Solver(Cube cube){
        this.cube = cube;
    }

    public boolean solve(List<String> moveList){

        if (!solveCross(moveList)) return false;

        return true;
    }

    //Resolve a cruz
    private boolean solveCross(List<String> moveList){
        if(!solveOneZero(moveList)) return false;

        return true;
    }

    //Resolve a primeira peca da cruz
    private boolean solveOneZero(List<String> moveList){
        //caso estiver na primeira camada e nao estiver invertida
        if(cube.up.symbols[0][1] == cube.up.symbols[1][1] && cube.back.symbols[0][1] == cube.left.symbols[1][1]){
            cube.rotate(cube,"U'"); moveList.add("U'");
        }
        if(cube.up.symbols[2][1] == cube.up.symbols[1][1] && cube.front.symbols[0][1] == cube.left.symbols[1][1]){
            cube.rotate(cube, "U "); moveList.add("U ");
        }
        if(cube.up.symbols[1][2] == cube.up.symbols[1][1] && cube.right.symbols[0][1] == cube.left.symbols[1][1]){
            cube.rotate(cube, "U "); moveList.add("U ");
            cube.rotate(cube, "U "); moveList.add("U ");
        }

        //caso estiver na primeira camada e estiver invertido
        if(cube.up.symbols[1][0] == cube.left.symbols[1][1] && cube.left.symbols[0][1] == cube.up.symbols[1][1]){
            cube.rotate(cube, "L "); cube.rotate(cube, "F "); cube.rotate(cube, "U'");
            moveList.add("L "); moveList.add("F "); moveList.add("U'");
        }
        
        //Checa se a peca esta no lugar agora
        if(cube.up.symbols[1][0] == cube.up.symbols[1][1] && cube.left.symbols[0][1] == cube.left.symbols[1][1])
            return true;
        return false;
    }
}
