import java.util.List;

public class Solver{
    Cube cube;

    Solver(Cube cube){
        this.cube = cube;
    }

    public boolean solve(List<String> moveList){

        if (!solveCross(moveList)) return false;

System.out.println("CROSS MOVES:");
for(String tmp: moveList) System.out.print("[" + tmp + "]");
System.out.println("\n");

        if (!solveFirstCorners(moveList)) return false;
        return true;
    }

    //Resolve a cruz
    private boolean solveCross(List<String> moveList){
        if(!solveOneZero(moveList)){System.out.println("Erro cruz, peca OneZero"); return false;}
        if(!solveZeroOne(moveList)){System.out.println("Erro cruz, peca ZeroOne"); return false;}
        if(!solveOneTwo(moveList)){System.out.println("Erro cruz, peca OneTwo"); return false;}
        if(!solveTwoOne(moveList)){System.out.println("Erro cruz, peca TwoOne"); return false;}
        return true;
    }
    //Resolve a primeira peca da cruz
    private boolean solveOneZero(List<String> moveList){
        char topColor = cube.up.symbols[1][1];
        char leftColor = cube.left.symbols[1][1];
        //caso estiver na primeira camada e nao estiver invertida
        if(cube.up.symbols[0][1] == topColor && cube.back.symbols[0][1] == leftColor){
            cube.rotate(cube,"U'"); moveList.add("U'");
        }
        else if(cube.up.symbols[2][1] == topColor && cube.front.symbols[0][1] == leftColor){
            cube.rotate(cube, "U "); moveList.add("U ");
        }
        else if(cube.up.symbols[1][2] == topColor && cube.right.symbols[0][1] == leftColor){
            cube.rotate(cube, "U "); moveList.add("U ");
            cube.rotate(cube, "U "); moveList.add("U ");
        }

        //caso estiver na primeira camada e estiver invertido
        else if(cube.up.symbols[1][0] == leftColor && cube.left.symbols[0][1] == topColor){
            cube.rotate(cube, "L "); cube.rotate(cube, "F "); cube.rotate(cube, "U'");
            moveList.add("L "); moveList.add("F "); moveList.add("U'");
        }
        else if(cube.up.symbols[0][1] == leftColor && cube.back.symbols[0][1] == topColor){
            cube.rotate(cube, "B "); cube.rotate(cube, "L ");
            moveList.add("B "); moveList.add("L ");
        }
        else if(cube.up.symbols[1][2] == leftColor && cube.right.symbols[0][1] == topColor){
            cube.rotate(cube, "U "); cube.rotate(cube, "F'"); cube.rotate(cube, "L'");
            moveList.add("U "); moveList.add("F'"); moveList.add("L'");
        }
        else if(cube.up.symbols[2][1] == leftColor && cube.front.symbols[0][1] == topColor){
            cube.rotate(cube,"F'"); cube.rotate(cube, "L'");
            moveList.add("F'"); moveList.add("L'");
        }

        //caso estiver na segunda camada e nao estiver invertido
        else if(cube.back.symbols[1][2] == topColor && cube.left.symbols[1][0] == leftColor){
            cube.rotate(cube, "L "); moveList.add("L ");
        }
        else if(cube.back.symbols[1][0] == topColor && cube.right.symbols[1][2] == leftColor){
            cube.rotate(cube, "B "); cube.rotate(cube, "B "); cube.rotate(cube, "L ");
            moveList.add("B "); moveList.add("B "); moveList.add("L ");
        }
        else if(cube.front.symbols[1][2] == topColor && cube.right.symbols[0][1] == leftColor){
            cube.rotate(cube, "F'"); cube.rotate(cube, "F'"); cube.rotate(cube, "L'");
            moveList.add("F'"); moveList.add("F'"); moveList.add("L'");
        }
        else if(cube.front.symbols[1][0] == topColor && cube.left.symbols[1][2] == leftColor){
            cube.rotate(cube, "L'"); moveList.add("L'");
        }

        //caso estiver na segunda camada e estiver invertido
        else if(cube.back.symbols[1][2] == leftColor && cube.left.symbols[1][0] == topColor){
            cube.rotate(cube, "B'"); cube.rotate(cube, "U'");
            moveList.add("B'"); moveList.add("U'");
        }
        else if(cube.back.symbols[1][0] == leftColor && cube.right.symbols[1][2] == topColor){
            cube.rotate(cube, "B "); cube.rotate(cube, "U'");
            moveList.add("B "); moveList.add("U'");
        }
        else if(cube.front.symbols[1][2] == leftColor && cube.right.symbols[1][0] == topColor){
            cube.rotate(cube, "F'"); cube.rotate(cube, "U ");
            moveList.add("F'"); moveList.add("U ");
        }
        else if(cube.front.symbols[0][1] == leftColor && cube.left.symbols[1][2] == topColor){
            cube.rotate(cube, "F "); cube.rotate(cube, "U ");
            moveList.add("F "); moveList.add("U ");
        }

        //caso estiver na terceira camada e nao invertido
        else if(cube.down.symbols[1][0] == topColor && cube.left.symbols[2][1] == leftColor){
            cube.rotate(cube, "L "); cube.rotate(cube, "L ");
            moveList.add("L "); moveList.add("L ");
        }
        else if(cube.down.symbols[1][2] == topColor && cube.back.symbols[2][1] == leftColor){
            cube.rotate(cube, "D "); cube.rotate(cube, "L "); cube.rotate(cube, "L ");
            moveList.add("D "); moveList.add("L "); moveList.add("L ");
        }
        else if(cube.right.symbols[2][1] == leftColor && cube.down.symbols[1][2] == topColor){
            cube.rotate(cube, "D "); cube.rotate(cube, "D "); cube.rotate(cube, "L "); cube.rotate(cube, "L ");
            moveList.add("D "); moveList.add("D "); moveList.add("L "); moveList.add("L ");
        }
        else if(cube.front.symbols[2][1] == leftColor && cube.down.symbols[0][1] == topColor){
            cube.rotate(cube, "D'"); cube.rotate(cube, "L "); cube.rotate(cube, "L ");
            moveList.add("D'"); moveList.add("L "); moveList.add("L ");
        }

        //caso estiver na terceira camada e invertido
        else if(cube.left.symbols[2][1] == topColor && cube.down.symbols[1][0] == leftColor){
            cube.rotate(cube, "D "); cube.rotate(cube, "F "); cube.rotate(cube, "L'");
            moveList.add("D "); moveList.add("F "); moveList.add("L'");
        }
        else if(cube.back.symbols[2][1] == topColor && cube.down.symbols[2][1] == leftColor){
            cube.rotate(cube, "B'"); cube.rotate(cube, "L ");
            moveList.add("B'"); moveList.add("L ");
        }
        else if(cube.right.symbols[2][1] == topColor && cube.down.symbols[1][2] == leftColor){
            cube.rotate(cube, "D'"); cube.rotate(cube, "F "); cube.rotate(cube, "L'");
            moveList.add("D'"); moveList.add("F "); moveList.add("L'");
        }
        else if(cube.front.symbols[2][1] == topColor && cube.down.symbols[0][1] == leftColor){
            cube.rotate(cube, "F "); cube.rotate(cube, "L'");
            moveList.add("F "); moveList.add("L'");
        }

        //Checa se a peca esta no lugar agora
        if(cube.up.symbols[1][0] == topColor && cube.left.symbols[0][1] == leftColor) return true;
        return false;
    }
    //Resolve a segunda peca da cruz
    private boolean solveZeroOne(List<String> moveList){
        char topColor = cube.up.symbols[1][1];
        char backColor = cube.back.symbols[1][1];

        //Se estiver na primeira camada e nao invertido
        if(cube.up.symbols[1][2] == topColor && cube.right.symbols[0][1] == backColor){
            cube.rotate(cube, "L "); cube.rotate(cube,"U'"); cube.rotate(cube, "L'");
            moveList.add("L "); moveList.add("U'"); moveList.add("L'");
        }
        else if(cube.up.symbols[2][1] == topColor && cube.front.symbols[0][1] == backColor){
            cube.rotate(cube, "L "); cube.rotate(cube, "U "); cube.rotate(cube, "U "); cube.rotate(cube, "L'");
            moveList.add("L "); moveList.add("U "); moveList.add("U "); moveList.add("L'");
        }

        //Se estiver na primeira camada e estiver invertido
        else if(cube.up.symbols[0][1] == backColor && cube.back.symbols[0][1] == topColor){
            cube.rotate(cube, "B'"); cube.rotate(cube, "U "); cube.rotate(cube,"R'"); cube.rotate(cube, "U'");
            moveList.add("B'"); moveList.add("U "); moveList.add("R'"); moveList.add("U'");
        }
        else if(cube.up.symbols[1][2] == backColor && cube.right.symbols[0][1] == topColor){
            cube.rotate(cube, "R "); cube.rotate(cube, "B ");
            moveList.add("R "); moveList.add("B ");
        }
        else if(cube.up.symbols[2][1] == backColor && cube.front.symbols[0][1] == topColor){
            cube.rotate(cube, "F "); cube.rotate(cube, "U "); cube.rotate(cube, "R "); cube.rotate(cube, "U'");
            moveList.add("F "); moveList.add("U "); moveList.add("R "); moveList.add("U'");
        }

        //Se estiver na segunda camada e nao estiver invertido
        else if(cube.left.symbols[1][0] == topColor && cube.back.symbols[1][2] == backColor){
            cube.rotate(cube, "B'"); moveList.add("B'");
        }
        else if(cube.right.symbols[1][2] == topColor && cube.back.symbols[1][0] == backColor){
            cube.rotate(cube, "B "); moveList.add("B ");
        }
        else if(cube.front.symbols[1][2] == backColor && cube.right.symbols[1][0] == topColor){
            cube.rotate(cube,"R "); cube.rotate(cube, "R "); cube.rotate(cube, "B ");
            moveList.add("R "); moveList.add("R "); moveList.add("B ");
        }
        else if(cube.front.symbols[1][0] == backColor && cube.left.symbols[1][2] == topColor){
            cube.rotate(cube, "U "); cube.rotate(cube, "U "); cube.rotate(cube, "F ");
            cube.rotate(cube, "U "); cube.rotate(cube, "U ");
            moveList.add("U "); moveList.add("U "); moveList.add("F "); moveList.add("U "); moveList.add("U ");
        }

        //Se estiver na segunda camada e estiver invertido
        else if(cube.back.symbols[1][2] == topColor && cube.left.symbols[1][0] == backColor){
            cube.rotate(cube, "U'"); cube.rotate(cube, "L "); cube.rotate(cube, "U ");
            moveList.add("U'"); moveList.add("L "); moveList.add("U ");
        }
        else if(cube.back.symbols[1][0] == topColor && cube.right.symbols[1][2] == backColor){
            cube.rotate(cube, "U "); cube.rotate(cube, "R'"); cube.rotate(cube, "U'");
            moveList.add("U "); moveList.add("R'"); moveList.add("U'");
        }
        else if(cube.front.symbols[1][2] == topColor && cube.right.symbols[1][0] == backColor){
            cube.rotate(cube, "U "); cube.rotate(cube, "R "); cube.rotate(cube, "U'");
            moveList.add("U "); moveList.add("R "); moveList.add("U'");
        }
        else if(cube.front.symbols[1][0] == topColor && cube.left.symbols[1][2] == backColor){
            cube.rotate(cube, "U'"); cube.rotate(cube, "L'"); cube.rotate(cube, "U ");
            moveList.add("U'"); moveList.add("L'"); moveList.add("U ");
        }

        //Se estiver na terceira camada e nao estiver invertido
        else if(cube.down.symbols[1][0] == topColor && cube.left.symbols[2][1] == backColor){
            cube.rotate(cube, "D'"); cube.rotate(cube, "B "); cube.rotate(cube, "B ");
            moveList.add("D'"); moveList.add("B "); moveList.add("B ");
        }
        else if(cube.down.symbols[2][1] == topColor && cube.back.symbols[2][1] == backColor){
            cube.rotate(cube, "B "); cube.rotate(cube, "B ");
            moveList.add("B "); moveList.add("B ");
        }
        else if(cube.down.symbols[1][2] == topColor && cube.right.symbols[2][1] == backColor){
            cube.rotate(cube, "D "); cube.rotate(cube, "B "); cube.rotate(cube, "B ");
            moveList.add("D "); moveList.add("B "); moveList.add("B ");
        }
        else if(cube.down.symbols[0][1] == topColor && cube.front.symbols[2][1] == backColor){
            cube.rotate(cube, "D "); cube.rotate(cube, "D "); cube.rotate(cube, "B "); cube.rotate(cube, "B ");
            moveList.add("D "); moveList.add("D "); moveList.add("B "); moveList.add("B ");
        }

        //Se estiver na terceira camada e estiver invertido
        else if(cube.down.symbols[1][0] == backColor && cube.left.symbols[2][1] == topColor){
            cube.rotate(cube, "U'"); cube.rotate(cube, "L "); cube.rotate(cube, "U "); cube.rotate(cube, "B'");
            moveList.add("U'"); moveList.add("L "); moveList.add("U "); moveList.add("B'");
        }
        else if(cube.down.symbols[2][1] == backColor && cube.back.symbols[2][1] == topColor){
            cube.rotate(cube, "B'"); cube.rotate(cube, "U'"); cube.rotate(cube, "L "); cube.rotate(cube, "U ");
            moveList.add("B'"); moveList.add("U'"); moveList.add("L "); moveList.add("U ");
        }
        else if(cube.down.symbols[1][2] == backColor && cube.right.symbols[2][1] == topColor){
            cube.rotate(cube, "R'"); cube.rotate(cube, "B ");
            moveList.add("R'"); moveList.add("B ");
        }
        else if(cube.down.symbols[0][1] == backColor && cube.front.symbols[2][1] == topColor){
            cube.rotate(cube, "F "); cube.rotate(cube, "U'"); cube.rotate(cube, "L'"); cube.rotate(cube, "U ");
            moveList.add("F "); moveList.add("U'"); moveList.add("L'"); moveList.add("U ");
        }

        if(cube.up.symbols[0][1] == topColor && cube.back.symbols[0][1] == backColor) return true;
        return false;
    }
    //Resolve a terceira peca da cruz
    private boolean solveOneTwo(List<String> moveList){
        char topColor = cube.up.symbols[1][1];
        char rightColor = cube.right.symbols[1][1];

        //Se estiver na primeira camada, nao invertida
        if(cube.up.symbols[2][1] == topColor && cube.front.symbols[0][1] == rightColor){
            cube.rotate(cube, "L "); cube.rotate(cube, "B'"); cube.rotate(cube, "U'"); cube.rotate(cube, "B ");
            cube.rotate(cube, "L'");
            moveList.add("L "); moveList.add("B'"); moveList.add("U'"); moveList.add("B "); moveList.add("L'");
        }

        //Se estiver na primeira camada, invertida
        else if(cube.up.symbols[1][2] == rightColor && cube.right.symbols[0][1] == topColor){
            cube.rotate(cube, "R'"); cube.rotate(cube, "U "); cube.rotate(cube, "F'"); cube.rotate(cube, "U'");
            moveList.add("R'"); moveList.add("U "); moveList.add("F'"); moveList.add("U'");
        }
        else if(cube.up.symbols[2][1] == rightColor && cube.front.symbols[0][1] == topColor){
            cube.rotate(cube, "F "); cube.rotate(cube, "R ");
            moveList.add("F "); moveList.add("R ");
        }

        //Se estiver na segunda camada, nao invertida
        else if(cube.front.symbols[1][2] == topColor && cube.right.symbols[1][0] == rightColor){
            cube.rotate(cube, "R "); moveList.add("R ");
        }
        else if(cube.front.symbols[1][0] == topColor && cube.left.symbols[1][2] == rightColor){
            cube.rotate(cube, "F "); cube.rotate(cube, "F "); cube.rotate(cube, "R ");
            moveList.add("F "); moveList.add("F "); moveList.add("R ");
        }
        else if(cube.back.symbols[1][2] == topColor && cube.right.symbols[1][0] == rightColor){
            cube.rotate(cube, "U'"); cube.rotate(cube, "U'"); cube.rotate(cube, "L "); cube.rotate(cube, "U ");
            cube.rotate(cube, "U ");
            moveList.add("U'"); moveList.add("U'"); moveList.add("L "); moveList.add("U "); moveList.add("U ");
        }
        else if(cube.back.symbols[1][0] == topColor && cube.right.symbols[1][2] == rightColor){
            cube.rotate(cube, "R'"); moveList.add("R'");
        }

        //Se estiver na segunda camada, invertida
        else if(cube.front.symbols[1][2] == rightColor && cube.right.symbols[1][0] == topColor){
            cube.rotate(cube, "U "); cube.rotate(cube, "F'"); cube.rotate(cube, "U'");
            moveList.add("U "); moveList.add("F'"); moveList.add("U'");
        }
        else if(cube.front.symbols[1][0] == rightColor && cube.left.symbols[1][2] == topColor){
            cube.rotate(cube, "U "); cube.rotate(cube, "F "); cube.rotate(cube, "U'");
            moveList.add("U "); moveList.add("F "); moveList.add("U'");
        }
        else if(cube.back.symbols[1][2] == rightColor && cube.left.symbols[1][0] == topColor){
            cube.rotate(cube, "U'"); cube.rotate(cube, "B'"); cube.rotate(cube, "U ");
            moveList.add("U'"); moveList.add("B'"); moveList.add("U ");
        }
        else if(cube.back.symbols[1][0] == rightColor && cube.right.symbols[1][2] == topColor){
            cube.rotate(cube, "U'"); cube.rotate(cube, "B "); cube.rotate(cube, "U ");
            moveList.add("U'"); moveList.add("B "); moveList.add("U ");
        }

        //Se estiver na terceira camada, nao invertida
        else if(cube.down.symbols[1][2] == topColor && cube.right.symbols[2][1] == rightColor){
            cube.rotate(cube, "R "); cube.rotate(cube, "R ");
            moveList.add("R "); moveList.add("R ");
        }
        else if(cube.down.symbols[0][1] == topColor && cube.front.symbols[2][1] == rightColor){
            cube.rotate(cube, "D "); cube.rotate(cube, "R "); cube.rotate(cube, "R ");
            moveList.add("D "); moveList.add("R "); moveList.add("R ");
        }
        else if(cube.down.symbols[1][0] == topColor && cube.left.symbols[2][1] == rightColor){
            cube.rotate(cube, "D "); cube.rotate(cube, "D "); cube.rotate(cube, "R "); cube.rotate(cube, "R ");
            moveList.add("D "); moveList.add("D "); moveList.add("R "); moveList.add("R ");
        }
        else if(cube.down.symbols[2][1] == topColor && cube.back.symbols[2][1] == rightColor){
            cube.rotate(cube, "D'"); cube.rotate(cube, "R "); cube.rotate(cube, "R ");
            moveList.add("D'"); moveList.add("R "); moveList.add("R ");
        }

        //Se estiver na terceira camada, invertida
        else if(cube.down.symbols[1][2] == rightColor && cube.right.symbols[2][1] == topColor){
            cube.rotate(cube, "R "); cube.rotate(cube, "U "); cube.rotate(cube, "F'"); cube.rotate(cube, "U'");
            moveList.add("R "); moveList.add("U "); moveList.add("F'"); moveList.add("U'");
        }
        else if(cube.down.symbols[0][1] == rightColor && cube.front.symbols[2][1] == topColor){
            cube.rotate(cube, "F'"); cube.rotate(cube, "R ");
            moveList.add("F'"); moveList.add("R ");
        }
        else if(cube.down.symbols[1][0] == rightColor && cube.left.symbols[2][1] == topColor){
            cube.rotate(cube, "D "); cube.rotate(cube, "F'"); cube.rotate(cube, "R ");
            moveList.add("D "); moveList.add("F'"); moveList.add("R ");
        }
        else if(cube.down.symbols[2][1] == rightColor && cube.back.symbols[2][1] == topColor){
            cube.rotate(cube, "B "); cube.rotate(cube, "R'"); cube.rotate(cube, "B'");
            moveList.add("B "); moveList.add("R'"); moveList.add("B'");
        }

        if(cube.up.symbols[1][2] == topColor && cube.right.symbols[0][1] == rightColor) return true;
        return false;
    }
    //Resolve a quarta peca da cruz
    private boolean solveTwoOne(List<String> moveList){
        char topColor = cube.up.symbols[1][1];
        char frontColor = cube.front.symbols[1][1];

        //Se esta na primeira camada mas inversa
        if(cube.up.symbols[2][1] == frontColor && cube.front.symbols[0][1] == topColor){
            cube.rotate(cube, "F "); cube.rotate(cube, "U'"); cube.rotate(cube, "R "); cube.rotate(cube, "U ");
            moveList.add("F "); moveList.add("U'"); moveList.add("R "); moveList.add("U ");
        }

        //Se esta na segunda camada, nao inversa
        else if(cube.front.symbols[1][0] == frontColor && cube.left.symbols[1][2] == topColor){
            cube.rotate(cube, "F "); moveList.add("F ");
        }
        else if(cube.back.symbols[1][2] == frontColor && cube.left.symbols[1][0] == topColor){
            //U LL U' F
            cube.rotate(cube, "U "); cube.rotate(cube, "L "); cube.rotate(cube, "L "); cube.rotate(cube, "U'");
            cube.rotate(cube, "F ");
            moveList.add("U "); moveList.add("L "); moveList.add("L "); moveList.add("U'"); moveList.add("F ");
        }
        else if(cube.back.symbols[1][0] == frontColor && cube.right.symbols[1][2] == topColor){
            cube.rotate(cube, "U'"); cube.rotate(cube, "R'"); cube.rotate(cube, "R'"); cube.rotate(cube, "U ");
            cube.rotate(cube, "F'");
            moveList.add("U'"); moveList.add("R'"); moveList.add("R'"); moveList.add("U "); moveList.add("F'");
        }
        else if(cube.front.symbols[1][2] == frontColor && cube.right.symbols[1][0] == topColor){
            cube.rotate(cube, "F'"); moveList.add("F'");
        }

        //Se esta na segunda camada inversa
        else if(cube.front.symbols[1][0] == topColor && cube.left.symbols[1][2] == frontColor){
            cube.rotate(cube, "U "); cube.rotate(cube, "L'"); cube.rotate(cube, "U'");
            moveList.add("U "); moveList.add("L'"); moveList.add("U'");
        }
        else if(cube.back.symbols[1][2] == topColor && cube.left.symbols[1][0] == frontColor){
            cube.rotate(cube, "U "); cube.rotate(cube, "L "); cube.rotate(cube, "U'");
            moveList.add("U "); moveList.add("L "); moveList.add("U'");
        }
        else if(cube.back.symbols[1][0] == topColor && cube.right.symbols[1][2] == frontColor){
            cube.rotate(cube, "U'"); cube.rotate(cube, "R'"); cube.rotate(cube, "U ");
            moveList.add("U'"); moveList.add("R'"); moveList.add("U ");
        }
        else if(cube.front.symbols[1][2] == topColor && cube.right.symbols[1][0] == frontColor){
            cube.rotate(cube, "U'"); cube.rotate(cube, "R "); cube.rotate(cube, "U ");
            moveList.add("U'"); moveList.add("R "); moveList.add("U ");
        }

        //Se esta na terceira camada nao inversa
        else if(cube.down.symbols[0][1] == topColor && cube.front.symbols[2][1] == frontColor){
            cube.rotate(cube, "F "); cube.rotate(cube, "F ");
            moveList.add("F "); moveList.add("F ");
        }
        else if(cube.down.symbols[1][0] == topColor && cube.left.symbols[2][1] == frontColor){
            cube.rotate(cube, "D "); cube.rotate(cube, "F "); cube.rotate(cube, "F ");
            moveList.add("D "); moveList.add("F "); moveList.add("F ");
        }
        else if(cube.down.symbols[2][1] == topColor && cube.back.symbols[2][1] == frontColor){
            cube.rotate(cube, "D "); cube.rotate(cube, "D "); cube.rotate(cube, "F "); cube.rotate(cube, "F ");
            moveList.add("D "); moveList.add("D "); moveList.add("F "); moveList.add("F ");
        }
        else if(cube.down.symbols[1][2] == topColor && cube.right.symbols[2][1] == frontColor){
            cube.rotate(cube, "D'"); cube.rotate(cube, "F "); cube.rotate(cube, "F ");
            moveList.add("D'"); cube.rotate(cube, "F "); cube.rotate(cube, "F ");
        }

        //Se esta na terceira camada e inversa
        else if(cube.down.symbols[0][1] == frontColor && cube.front.symbols[2][1] == topColor){
            cube.rotate(cube, "F "); cube.rotate(cube, "U "); cube.rotate(cube, "L'"); cube.rotate(cube, "U'");
            moveList.add("F "); moveList.add("U "); moveList.add("L'"); moveList.add("U'");
        }
        else if(cube.down.symbols[1][0] == frontColor && cube.left.symbols[2][1] == topColor){
            cube.rotate(cube, "U "); cube.rotate(cube, "L'"); cube.rotate(cube, "U'"); cube.rotate(cube, "F ");
            moveList.add("U "); moveList.add("L'"); moveList.add("U'"); moveList.add("F ");
        }
        else if(cube.down.symbols[2][1] == frontColor && cube.back.symbols[2][1] == topColor){
            cube.rotate(cube, "D "); cube.rotate(cube, "U "); cube.rotate(cube, "L'"); cube.rotate(cube, "U'");
            cube.rotate(cube, "F ");
            moveList.add("D "); cube.rotate(cube, "U "); cube.rotate(cube, "L'"); cube.rotate(cube, "U'");
            cube.rotate(cube, "F ");
        }
        else if(cube.down.symbols[1][2] == frontColor && cube.right.symbols[2][1] == topColor){
            cube.rotate(cube, "U'"); cube.rotate(cube, "R "); cube.rotate(cube, "U "); cube.rotate(cube, "F'");
            moveList.add("U'"); moveList.add("R "); moveList.add("U "); moveList.add("F'");
        }

        if(cube.up.symbols[2][1] == topColor && cube.front.symbols[0][1] == frontColor) return true;
        return false;
    }

    //Resolve os cantos da primeira camada
    private boolean solveFirstCorners(List<String> moveList){
        if(!solveZeroZero(moveList)){System.out.println("Erro nos primeiros cantos, peca ZeroZero"); return false;}

        return true;
    }
    //Resolve a primeira peca de canto da primeira camada
    private boolean solveZeroZero(List<String> moveList){
        System.out.println("Resolvendo primeiro canto 0,0");
        char topColor = cube.up.symbols[1][1];
        char leftColor = cube.left.symbols[1][1];
        char backColor = cube.back.symbols[1][1];
        //Primeiro move para baixo de onde tem que ir

        //Se esta na primeira camada
        //Se esta no lugar, 0,0
        if( (cube.up.symbols[0][0] == backColor && cube.left.symbols[0][0] == topColor && cube.back.symbols[0][2] == leftColor) ||
            (cube.up.symbols[0][0] == leftColor && cube.left.symbols[0][0] == backColor && cube.back.symbols[0][2] == topColor)){

            cube.rotate(cube, "L'"); cube.rotate(cube, "D "); cube.rotate(cube, "L ");
            moveList.add("L'"); moveList.add("D "); moveList.add("L ");
        }
        //Se esta no 0,2
        else if((cube.up.symbols[0][2] == leftColor && cube.right.symbols[0][2] == topColor && cube.back.symbols[0][0] == backColor) ||
                 (cube.up.symbols[0][2] == backColor && cube.right.symbols[0][2] == leftColor && cube.back.symbols[0][0] == topColor) ||
                 (cube.up.symbols[0][2] == topColor && cube.right.symbols[0][2] == backColor && cube.back.symbols[0][0] == leftColor)){

            cube.rotate(cube, "R "); cube.rotate(cube, "D "); cube.rotate(cube, "R'");
            moveList.add("R "); moveList.add("D "); moveList.add("R'");
        }
        //Se esta no 2,2
        else if((cube.up.symbols[2][2] == backColor && cube.right.symbols[0][0] == topColor && cube.front.symbols[0][2] == leftColor) ||
                (cube.up.symbols[2][2] == leftColor && cube.right.symbols[0][0] == backColor && cube.front.symbols[0][2] == topColor) ||
                (cube.up.symbols[2][2] == topColor && cube.right.symbols[0][0] == leftColor && cube.front.symbols[0][2] == backColor)){

            cube.rotate(cube, "R'"); cube.rotate(cube, "D'"); cube.rotate(cube, "D'"); cube.rotate(cube, "R ");
            moveList.add("R'"); moveList.add("D'"); moveList.add("D'"); moveList.add("R ");
        }
        //Se esta no 2,0
        else if((cube.up.symbols[2][0] == leftColor && cube.front.symbols[0][0] == backColor && cube.left.symbols[0][2] == topColor) ||
                (cube.up.symbols[2][0] == backColor && cube.front.symbols[0][0] == topColor && cube.left.symbols[0][2] == leftColor) ||
                (cube.up.symbols[2][0] == topColor && cube.front.symbols[0][0] == leftColor && cube.left.symbols[0][2] == backColor)){

                cube.rotate(cube, "L "); cube.rotate(cube, "D "); cube.rotate(cube, "L'"); cube.rotate(cube, "D'"); cube.rotate(cube, "D'");
                moveList.add("L "); moveList.add("D "); moveList.add("L'"); moveList.add("D'"); moveList.add("D'");
        }

        //Se esta na segunda camada
        //Se esta no 0,2
        else if((cube.down.symbols[2][2] == leftColor && cube.right.symbols[2][2] == backColor && cube.back.symbols[0][2] == topColor) ||
                (cube.down.symbols[2][2] == topColor && cube.right.symbols[2][2] == leftColor && cube.back.symbols[0][2] == backColor) ||
                (cube.down.symbols[2][2] == backColor && cube.right.symbols[2][2] == topColor && cube.back.symbols[0][2] == leftColor)){

            cube.rotate(cube, "D "); moveList.add("D ");
        }
        //Se esta no 2,2
        else if((cube.down.symbols[0][2] == backColor && cube.right.symbols[2][0] == leftColor && cube.front.symbols[2][2] == topColor) ||
                (cube.down.symbols[0][2] == topColor && cube.right.symbols[2][0] == backColor && cube.front.symbols[2][2] == leftColor) ||
                (cube.down.symbols[0][2] == leftColor && cube.right.symbols[2][0] == topColor && cube.front.symbols[2][2] == backColor)){

            cube.rotate(cube, "D "); cube.rotate(cube, "D ");
            moveList.add("D "); moveList.add("D ");
        }
        //Se esta no 2,0
        else if((cube.down.symbols[0][0] == leftColor && cube.left.symbols[2][2] == backColor && cube.front.symbols[2][0] == topColor) ||
                (cube.down.symbols[0][0] == backColor && cube.left.symbols[2][2] == topColor && cube.front.symbols[2][0] == leftColor) ||
                (cube.down.symbols[0][0] == topColor && cube.left.symbols[2][2] == leftColor && cube.front.symbols[2][0] == backColor)){

                cube.rotate(cube, "U'"); moveList.add("U'");
        }

        while(!(cube.up.symbols[0][0] == topColor && cube.left.symbols[0][0] == leftColor && cube.back.symbols[0][2] == backColor)){
            cube.rotate(cube, "L'"); cube.rotate(cube, "D'"); cube.rotate(cube, "L "); cube.rotate(cube, "D ");
            moveList.add("L'"); moveList.add("D'"); moveList.add("L "); moveList.add("D ");
        }
        System.out.println("Canto 0,0 resolvido com sucesso");
        return true;
    }
}
