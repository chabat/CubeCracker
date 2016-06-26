import java.util.List;

public class Solver{
    private Cube cube;
    private static boolean debugPrints = true;

    Solver(Cube cube){
        this.cube = cube;
    }

    public boolean solve(List<String> moveList){

        if (!solveCross(moveList)) return false;
        if (!solveFirstCorners(moveList)) return false;
        if (!solveMiddleLayer(moveList)) return false;
        if (!solveDownCross(moveList)) return false;

        cube.update();
        return true;
    }

    //Resolve a cruz
    private boolean solveCross(List<String> moveList){
        if(!solveOneZero(moveList)){System.out.println("Erro cruz, peca OneZero"); return false;}
        if(!solveZeroOne(moveList)){System.out.println("Erro cruz, peca ZeroOne"); return false;}
        if(!solveOneTwo(moveList)){System.out.println("Erro cruz, peca OneTwo"); return false;}
        if(!solveTwoOne(moveList)){System.out.println("Erro cruz, peca TwoOne"); return false;}

        if(debugPrints){
            System.out.println("MOVIMENTOS ATE A CRUZ:");
            for(String tmp: moveList) System.out.print("[" + tmp + "]");
            System.out.println("\n");
        }
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
        else if(cube.front.symbols[1][2] == topColor && cube.right.symbols[1][0] == leftColor){
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
        else if(cube.down.symbols[2][1] == topColor && cube.back.symbols[2][1] == leftColor){
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
        else if(cube.back.symbols[1][2] == topColor && cube.left.symbols[1][0] == rightColor){
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
            moveList.add("D'"); moveList.add("F "); moveList.add("F ");
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
            moveList.add("D "); moveList.add("U "); moveList.add("L'"); moveList.add("U'"); moveList.add("F ");
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
        solveZeroZero(moveList);

        if(debugPrints){
            System.out.println("MOVIMENTOS ATE O PRIMEIRO CANTO:");
            for(String tmp: moveList) System.out.print("[" + tmp + "]");
            System.out.println("\n");
        }

        solveZeroTwo(moveList);

        if(debugPrints){
            System.out.println("MOVIMENTOS ATE O SEGUNDO CANTO:");
            for(String tmp: moveList) System.out.print("[" + tmp + "]");
            System.out.println("\n");
        }

        solveTwoTwo(moveList);

        if(debugPrints){
            System.out.println("MOVIMENTOS ATE O TERCEIRO CANTO:");
            for(String tmp: moveList) System.out.print("[" + tmp + "]");
            System.out.println("\n");
        }
        solveTwoZero(moveList);

        if(debugPrints){
            System.out.println("MOVIMENTOS ATE O QUARTO CANTO:");
            for(String tmp: moveList) System.out.print("[" + tmp + "]");
            System.out.println("\n");
        }
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
        /*//Se esta no lugar, 0,0
        if( (cube.up.symbols[0][0] == backColor && cube.left.symbols[0][0] == topColor && cube.back.symbols[0][2] == leftColor) ||
            (cube.up.symbols[0][0] == leftColor && cube.left.symbols[0][0] == backColor && cube.back.symbols[0][2] == topColor)){

            cube.rotate(cube, "L'"); cube.rotate(cube, "D "); cube.rotate(cube, "L ");
            moveList.add("L'"); moveList.add("D "); moveList.add("L ");
        }*/
        //Se esta no 0,2
        if((cube.up.symbols[0][2] == leftColor && cube.right.symbols[0][2] == topColor && cube.back.symbols[0][0] == backColor) ||
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
        else if((cube.down.symbols[2][2] == leftColor && cube.right.symbols[2][2] == backColor && cube.back.symbols[2][0] == topColor) ||
                (cube.down.symbols[2][2] == topColor && cube.right.symbols[2][2] == leftColor && cube.back.symbols[2][0] == backColor) ||
                (cube.down.symbols[2][2] == backColor && cube.right.symbols[2][2] == topColor && cube.back.symbols[2][0] == leftColor)){

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

                cube.rotate(cube, "D'"); moveList.add("D'");
        }

        //Aplica o algoritmo ate ficar na posicao correta
        while(!(cube.up.symbols[0][0] == topColor && cube.left.symbols[0][0] == leftColor && cube.back.symbols[0][2] == backColor)){
            cube.rotate(cube, "L'"); cube.rotate(cube, "D'"); cube.rotate(cube, "L "); cube.rotate(cube, "D ");
            moveList.add("L'"); moveList.add("D'"); moveList.add("L "); moveList.add("D ");
        }
        System.out.println("Canto 0,0 resolvido com sucesso");
        return true;
    }
    //Resolve a segunda peca de canto da primeira camada
    private boolean solveZeroTwo(List<String> moveList){
        System.out.println("Resolvendo segundo canto 0,2");
        char topColor = cube.up.symbols[1][1];
        char rightColor = cube.right.symbols[1][1];
        char backColor = cube.back.symbols[1][1];

        //Primeiro move para baixo de onde tem que ir
        /*//Se estiver na primeira camada 0,2
        if((cube.up.symbols[0][2] == rightColor && cube.right.symbols[0][2] == backColor && cube.back.symbols[0][0] == topColor) ||
          (cube.up.symbols[0][2] == backColor && cube.right.symbols[0][2] == topColor && cube.back.symbols[0][0] == rightColor)){
              //R D R' D'
              cube.rotate(cube, "R "); cube.rotate(cube, "D "); cube.rotate(cube, "R'"); cube.rotate(cube, "D'");
              moveList.add("R "); moveList.add("D "); moveList.add("R'"); moveList.add("D'");
        }*/
        //Se estiver na primeira camada 2,2
        if((cube.up.symbols[2][2] == rightColor && cube.front.symbols[0][2] == backColor && cube.right.symbols[0][0] == topColor) ||
           (cube.up.symbols[2][2] == backColor && cube.front.symbols[0][2] == topColor && cube.right.symbols[0][0] == rightColor) ||
           (cube.up.symbols[2][2] == topColor && cube.front.symbols[0][2] == rightColor && cube.right.symbols[0][0] == backColor)){

            cube.rotate(cube, "R'"); cube.rotate(cube, "D "); cube.rotate(cube, "D "); cube.rotate(cube, "R ");
            cube.rotate(cube, "D'");
            moveList.add("R'"); moveList.add("D "); moveList.add("D "); moveList.add("R "); moveList.add("D'");
        }
        //Se estiver na primeira camada 2,0
        else if((cube.up.symbols[2][0] == backColor && cube.front.symbols[0][0] == rightColor && cube.left.symbols[0][2] == topColor) ||
                (cube.up.symbols[2][0] == rightColor && cube.front.symbols[0][0] == topColor && cube.left.symbols[0][2] == backColor) ||
                (cube.up.symbols[2][0] == topColor && cube.front.symbols[0][0] == backColor && cube.left.symbols[0][2] == rightColor)){

            cube.rotate(cube, "L "); cube.rotate(cube, "D "); cube.rotate(cube, "D "); cube.rotate(cube, "L'");
            moveList.add("L "); moveList.add("D "); moveList.add("D "); moveList.add("L'");
        }

        //Se estiver na segunda camada 0,0
        else if((cube.down.symbols[2][0] == backColor && cube.left.symbols[2][0] == topColor && cube.back.symbols[2][2] == rightColor) ||
                (cube.down.symbols[2][0] == topColor && cube.left.symbols[2][0] == rightColor && cube.back.symbols[2][2] == backColor) ||
                (cube.down.symbols[2][0] == rightColor && cube.left.symbols[2][0] == backColor && cube.back.symbols[2][2] == topColor)){

            cube.rotate(cube, "D'"); moveList.add("D'");
        }
        //Se estiver na segunda camada 2,2
        else if((cube.down.symbols[0][2] == rightColor && cube.front.symbols[2][2] == topColor && cube.right.symbols[2][0] == backColor) ||
                (cube.down.symbols[0][2] == backColor && cube.front.symbols[2][2] == rightColor && cube.right.symbols[2][0] == topColor) ||
                (cube.down.symbols[0][2] == topColor && cube.front.symbols[2][2] == backColor && cube.right.symbols[2][0] == rightColor)){

            cube.rotate(cube, "D "); moveList.add("D ");
        }
        //Se estiver na segunda camada 2,0
        else if((cube.down.symbols[0][0] == topColor && cube.front.symbols[2][0] == rightColor && cube.left.symbols[2][2] == backColor) ||
                (cube.down.symbols[0][0] == rightColor && cube.front.symbols[2][0] == backColor && cube.left.symbols[2][2] == topColor) ||
                (cube.down.symbols[0][0] == backColor && cube.front.symbols[2][0] == topColor && cube.left.symbols[2][2] == rightColor)){

                cube.rotate(cube,"D "); cube.rotate(cube,"D "); moveList.add("D "); moveList.add("D ");
        }

        //Entao aplica o algoritmo ate ficar correto
        while(!(cube.up.symbols[0][2] == topColor && cube.right.symbols[0][2] == rightColor && cube.back.symbols[0][0] == backColor)){
            cube.rotate(cube, "B'"); cube.rotate(cube, "D'"); cube.rotate(cube, "B "); cube.rotate(cube, "D ");
            moveList.add("B'"); moveList.add("D'"); moveList.add("B "); moveList.add("D ");
        }

        System.out.println("Canto 0,2 resolvido com sucesso");
        return true;
    }
    //Resolve a terceira peca de canto da primeira camada
    private boolean solveTwoTwo(List<String> moveList){
        System.out.println("Resolvendo terceiro canto 2,2");
        char topColor = cube.up.symbols[1][1];
        char frontColor = cube.front.symbols[1][1];
        char rightColor = cube.right.symbols[1][1];
        //Primeiro, move para baixo de onde deve ir

        /*//Se estiver na primeira camada 2,2
        if((cube.up.symbols[2][2] == frontColor && cube.front.symbols[0][2] == rightColor && cube.right.symbols[0][0] == topColor) ||
           (cube.up.symbols[2][2] == rightColor && cube.front.symbols[0][2] == topColor && cube.right.symbols[0][0] == frontColor)){

               cube.rotate(cube, "R'"); cube.rotate(cube, "D'"); cube.rotate(cube, "R "); cube.rotate(cube, "D ");
               moveList.add("R'"); moveList.add("D'"); moveList.add("R "); moveList.add("D ");
        }*/
        //Se estiver na primeira camada 2,0
        if((cube.up.symbols[2][0] == topColor && cube.front.symbols[0][0] == rightColor && cube.left.symbols[0][2] == frontColor) ||
                (cube.up.symbols[2][0] == rightColor && cube.front.symbols[0][0] == frontColor && cube.left.symbols[0][2] == topColor) ||
                (cube.up.symbols[2][0] == frontColor && cube.front.symbols[0][0] == topColor && cube.left.symbols[0][2] == rightColor)){

            cube.rotate(cube, "L "); cube.rotate(cube, "D "); cube.rotate(cube, "L'");
            moveList.add("L "); moveList.add("D "); moveList.add("L'");
        }

        //Se estiver na terceira camada 0,0
        else if((cube.down.symbols[2][0] == topColor && cube.left.symbols[2][0] == frontColor && cube.back.symbols[2][2] == rightColor) ||
                (cube.down.symbols[2][0] == rightColor && cube.left.symbols[2][0] == topColor && cube.back.symbols[2][2] == frontColor) ||
                (cube.down.symbols[2][0] == frontColor && cube.left.symbols[2][0] == rightColor && cube.back.symbols[2][2] == topColor)){
            cube.rotate(cube, "D "); cube.rotate(cube, "D ");
            moveList.add("D "); moveList.add("D ");
        }
        //Se estiver na terceira camada 0,2
        else if((cube.down.symbols[2][2] == frontColor && cube.right.symbols[2][2] == topColor && cube.back.symbols[2][0] == rightColor) ||
                (cube.down.symbols[2][2] == topColor && cube.right.symbols[2][2] == rightColor && cube.back.symbols[2][0] == frontColor) ||
                (cube.down.symbols[2][2] == rightColor && cube.right.symbols[2][2] == frontColor && cube.back.symbols[2][0] == topColor)){
            cube.rotate(cube, "D'"); moveList.add("D'");
        }
        //Se estiver na terceira camada 2,0
        else if((cube.down.symbols[0][0] == rightColor && cube.front.symbols[2][0] == topColor && cube.left.symbols[2][2] == frontColor) ||
                (cube.down.symbols[0][0] == frontColor && cube.front.symbols[2][0] == rightColor && cube.left.symbols[2][2] == topColor) ||
                (cube.down.symbols[0][0] == topColor && cube.front.symbols[2][0] == frontColor && cube.left.symbols[2][2] == rightColor)){

                cube.rotate(cube, "D "); moveList.add("D ");
        }

        //Entao, aplica o algoritmo ate corrigir
        while(!(cube.up.symbols[2][2] == topColor && cube.front.symbols[0][2] == frontColor && cube.right.symbols[0][0] == rightColor)){
            cube.rotate(cube, "R'"); cube.rotate(cube, "D'"); cube.rotate(cube, "R "); cube.rotate(cube, "D ");
            moveList.add("R'"); moveList.add("D'"); moveList.add("R "); moveList.add("D ");
        }

        System.out.println("Canto 2,2 resolvido com sucesso");
        return true;
    }
    //Resolve a quarta peca do canto da primeira camada
    private boolean solveTwoZero(List<String> moveList){
        System.out.println("Resolvendo quarto canto 2,0");
        char topColor = cube.up.symbols[1][1];
        char frontColor = cube.front.symbols[1][1];
        char leftColor = cube.left.symbols[1][1];
        //Primeiro move para baixo de onde deve ir

        //Se estiver na segunda camada 0,0
        if((cube.down.symbols[2][0] == frontColor && cube.left.symbols[2][0] == topColor && cube.back.symbols[2][2] == leftColor) ||
           (cube.down.symbols[2][0] == leftColor && cube.left.symbols[2][0] == frontColor && cube.back.symbols[2][2] == topColor) ||
           (cube.down.symbols[2][0] == topColor && cube.left.symbols[2][0] == leftColor && cube.back.symbols[2][2] == frontColor)){

               cube.rotate(cube,"D "); moveList.add("D ");
        }
        //Se estiver na segunda camada 0,2
        else if((cube.down.symbols[2][2] == topColor && cube.right.symbols[2][2] == frontColor && cube.back.symbols[2][0] == leftColor) ||
                (cube.down.symbols[2][2] == frontColor && cube.right.symbols[2][2] == leftColor && cube.back.symbols[2][0] == topColor) ||
                (cube.down.symbols[2][2] == leftColor && cube.right.symbols[2][2] == topColor && cube.back.symbols[2][0] == frontColor)){

            cube.rotate(cube, "D "); cube.rotate(cube, "D "); moveList.add("D "); moveList.add("D ");
        }
        //Se estiver na segunda camada 2,2
        else if((cube.down.symbols[0][2] == leftColor && cube.right.symbols[2][0] == frontColor && cube.front.symbols[2][2] == topColor) ||
                (cube.down.symbols[0][2] == topColor && cube.right.symbols[2][0] == leftColor && cube.front.symbols[2][2] == frontColor) ||
                (cube.down.symbols[0][2] == frontColor && cube.right.symbols[2][0] == topColor && cube.front.symbols[2][2] == leftColor)){

            cube.rotate(cube, "D'"); moveList.add("D'");
        }

        while(!(cube.up.symbols[2][0] == topColor && cube.front.symbols[0][0] == frontColor && cube.left.symbols[0][2] == leftColor)){
            cube.rotate(cube, "L "); cube.rotate(cube, "D "); cube.rotate(cube, "L'"); cube.rotate(cube, "D'");
            moveList.add("L "); moveList.add("D "); moveList.add("L'"); moveList.add("D'");
        }
        System.out.println("Canto 2,0 resolvido com sucesso");
        return true;
    }

    //Resolve a camada do meio
    private boolean solveMiddleLayer(List<String> moveList){
        if(!solveMiddleZeroZero(moveList)){System.out.println("Erro ao resolver primeira peca do meio"); return false;}

        if(debugPrints){
            System.out.println("MOVIMENTOS ATE A PRIMEIRA PECA DO MEIO:");
            for(String tmp: moveList) System.out.print("[" + tmp + "]");
            System.out.println("\n");
        }

        if(!solveMiddleZeroTwo(moveList)){System.out.println("Erro ao resolver a segunda peca do meio"); return false;}

        if(debugPrints){
            System.out.println("MOVIMENTOS ATE A SEGUNDA PECA DO MEIO:");
            for(String tmp: moveList) System.out.print("[" + tmp + "]");
            System.out.println("\n");
        }

        if(!solveMiddleTwoTwo(moveList)){System.out.println("Erro ao resolver a terceira peca do meio"); return false;}

        if(debugPrints){
            System.out.println("MOVIMENTOS ATE A TERCEIRA PECA DO MEIO:");
            for(String tmp: moveList) System.out.print("[" + tmp + "]");
            System.out.println("\n");
        }

        if(!solveMiddleTwoZero(moveList)){System.out.println("Erro ao resolver quarta peca do meio"); return false;}

        if(debugPrints){
            System.out.println("MOVIMENTOS ATE A QUARTA PECA DO MEIO:");
            for(String tmp: moveList) System.out.print("[" + tmp + "]");
            System.out.println("\n");
        }

        return true;
    }
    //Algoritmo do meio para face da esquerda
    private void middleAlgorithmLeft(List<String> moveList, String dir){
        if(dir == "right"){
            cube.rotate(cube, "D "); cube.rotate(cube, "B "); cube.rotate(cube, "D'"); cube.rotate(cube, "B'");
            cube.rotate(cube, "D'"); cube.rotate(cube, "L'"); cube.rotate(cube, "D "); cube.rotate(cube, "L ");
            moveList.add("D "); moveList.add("B "); moveList.add("D'"); moveList.add("B'"); moveList.add("D'");
            moveList.add("L'"); moveList.add("D "); moveList.add("L ");
        }
        else if(dir == "left"){
            //D' F'D F D L D' L'
            cube.rotate(cube, "D'"); cube.rotate(cube, "F'"); cube.rotate(cube, "D "); cube.rotate(cube, "F ");
            cube.rotate(cube, "D "); cube.rotate(cube, "L "); cube.rotate(cube, "D'"); cube.rotate(cube, "L'");
            moveList.add("D'"); moveList.add("F'"); moveList.add("D "); moveList.add("F "); moveList.add("D ");
            moveList.add("L "); moveList.add("D'"); moveList.add("L'");
        }
    }
    //Algoritmo do meio para face de tras
    private void middleAlgorithmBack(List<String> moveList, String dir){
        if(dir == "right"){
            //D R D' R' D' B' D B
            cube.rotate(cube, "D "); cube.rotate(cube, "R "); cube.rotate(cube, "D'"); cube.rotate(cube, "R'");
            cube.rotate(cube, "D'"); cube.rotate(cube, "B'"); cube.rotate(cube, "D "); cube.rotate(cube, "B ");
            moveList.add("D "); moveList.add("R "); moveList.add("D'"); moveList.add("R'"); moveList.add("D'");
            moveList.add("B'"); moveList.add("D "); moveList.add("B ");
        }
        else if(dir == "left"){
            //D' L' D  L D B D' B'
            cube.rotate(cube, "D'"); cube.rotate(cube, "L'"); cube.rotate(cube, "D "); cube.rotate(cube, "L ");
            cube.rotate(cube, "D "); cube.rotate(cube, "B "); cube.rotate(cube, "D'"); cube.rotate(cube, "B'");
            moveList.add("D'"); moveList.add("L'"); moveList.add("D "); moveList.add("L "); moveList.add("D ");
            moveList.add("B "); moveList.add("D'"); moveList.add("B'");
        }
    }
    //Algoritmo do meio para face da direita
    private void middleAlgorithmRight(List<String> moveList, String dir){
        if(dir == "right"){
            //D F D' F' D' R' D R
            cube.rotate(cube, "D "); cube.rotate(cube, "F "); cube.rotate(cube, "D'"); cube.rotate(cube, "F'");
            cube.rotate(cube, "D'"); cube.rotate(cube, "R'"); cube.rotate(cube, "D "); cube.rotate(cube, "R ");
            moveList.add("D "); moveList.add("F "); moveList.add("D'"); moveList.add("F'"); moveList.add("D'");
            moveList.add("R'"); moveList.add("D "); moveList.add("R ");
        }
        else if(dir == "left"){
            //D' B' D B D R D' R'
            cube.rotate(cube, "D'"); cube.rotate(cube, "B'"); cube.rotate(cube, "D "); cube.rotate(cube, "B ");
            cube.rotate(cube, "D "); cube.rotate(cube, "R "); cube.rotate(cube, "D'"); cube.rotate(cube, "R'");
            moveList.add("D'"); moveList.add("B'"); moveList.add("D "); moveList.add("B "); moveList.add("D ");
            moveList.add("R "); moveList.add("D'"); moveList.add("R'");
        }
    }
    //Algoritmo do meio para face da frente
    private void middleAlgorithmFront(List<String> moveList, String dir){
        if(dir == "right"){
            //D L D' L' D' F' D F
            cube.rotate(cube, "D "); cube.rotate(cube, "L "); cube.rotate(cube, "D'"); cube.rotate(cube, "L'");
            cube.rotate(cube, "D'"); cube.rotate(cube, "F'"); cube.rotate(cube, "D "); cube.rotate(cube, "F ");
            moveList.add("D "); moveList.add("L "); moveList.add("D'"); moveList.add("L'"); moveList.add("D'");
            moveList.add("F'"); moveList.add("D "); moveList.add("F ");
        }
        else if(dir == "left"){
            //D' R' D R D F D' F'
            cube.rotate(cube, "D'"); cube.rotate(cube, "R'"); cube.rotate(cube, "D "); cube.rotate(cube, "R ");
            cube.rotate(cube, "D "); cube.rotate(cube, "F "); cube.rotate(cube, "D'"); cube.rotate(cube, "F'");
            moveList.add("D'"); moveList.add("R'"); moveList.add("D"); moveList.add("R "); moveList.add("D ");
            moveList.add("F "); moveList.add("D'"); moveList.add("F'");
        }
    }
    //Resolve a primeira peca de canto do meio
    private boolean solveMiddleZeroZero(List<String> moveList){
        System.out.println("Arrumando primeira peca do meio...");
        char backColor = cube.back.symbols[1][1];
        char leftColor = cube.left.symbols[1][1];

        //Primeiro se estiver em algum dos cantos errados, tira do canto
        //canto 0,0
        if(cube.left.symbols[1][0] == backColor && cube.back.symbols[1][2] == leftColor) middleAlgorithmLeft(moveList, "right");
        //canto 0,2
        else if((cube.back.symbols[1][0] == backColor && cube.right.symbols[1][2] == leftColor) ||
                (cube.back.symbols[1][0] == leftColor && cube.right.symbols[1][2] == backColor))
            middleAlgorithmBack(moveList, "right");
        //canto 2,2
        else if((cube.front.symbols[1][2] == backColor && cube.right.symbols[1][0] == leftColor) ||
                (cube.front.symbols[1][2] == leftColor && cube.right.symbols[1][0] == backColor))
            middleAlgorithmRight(moveList, "right");
        //Canto 2,0
        else if((cube.front.symbols[1][0] == backColor && cube.left.symbols[1][2] == leftColor) ||
                (cube.front.symbols[1][0] == leftColor && cube.left.symbols[1][2] == backColor) ){
            middleAlgorithmLeft(moveList, "left");
        }

        while(!((cube.left.symbols[2][1] == leftColor && cube.down.symbols[1][0] == backColor) ||
               (cube.back.symbols[2][1] == backColor && cube.down.symbols[2][1] == leftColor))){
            cube.rotate(cube, "D "); moveList.add("D ");
        }
        if(cube.left.symbols[2][1] == leftColor && cube.down.symbols[1][0] == backColor) middleAlgorithmLeft(moveList, "right");
        else if (cube.back.symbols[2][1] == backColor && cube.down.symbols[2][1] == leftColor) middleAlgorithmBack(moveList, "left");

        if(cube.left.symbols[1][0] == leftColor && cube.back.symbols[1][2] == backColor)
        {System.out.println("Primeira peca do meio resolvida com sucesso"); return true;}
        return false;
    }
    //Resolve a segunda peca de canto do meio
    private boolean solveMiddleZeroTwo(List<String> moveList){
        System.out.println("Arrumando a segunda peca do meio...");
        char rightColor = cube.right.symbols[1][1];
        char backColor = cube.back.symbols[1][1];

        //Se tiver em um dos cantos tira
        //Se tiver no canto 0,2
        if(cube.right.symbols[1][2] == backColor && cube.back.symbols[1][0] == rightColor) middleAlgorithmRight(moveList, "left");
        //Se tiver no canto 2,2
        else if((cube.front.symbols[1][2] == rightColor && cube.right.symbols[1][0] == backColor) ||
            (cube.front.symbols[1][2] == backColor && cube.right.symbols[1][0] == rightColor))
            middleAlgorithmFront(moveList, "left");
        //Se tiver no canto 2,0
        else if((cube.front.symbols[1][0] == backColor && cube.left.symbols[1][2] == rightColor) ||
            (cube.front.symbols[1][0] == rightColor && cube.left.symbols[1][2] == backColor))
            middleAlgorithmFront(moveList, "right");

        while(!((cube.right.symbols[2][1] == rightColor && cube.down.symbols[1][2] == backColor) ||
                (cube.back.symbols[2][1] == backColor && cube.down.symbols[2][1] == rightColor))){
            cube.rotate(cube, "D "); moveList.add("D ");
        }
        if(cube.right.symbols[2][1] == rightColor && cube.down.symbols[1][2] == backColor) middleAlgorithmRight(moveList, "left");
        else if (cube.back.symbols[2][1] == backColor && cube.down.symbols[2][1] == rightColor) middleAlgorithmBack(moveList, "right");

        if(cube.right.symbols[1][2] == rightColor && cube.back.symbols[1][0] == backColor)
        {System.out.println("Segunda peca do meio resolvida com sucesso"); return true;}
        return false;
    }
    //Resolve a terceira peca de canto do meio
    private boolean solveMiddleTwoTwo(List<String> moveList){
        System.out.println("Arrumando a Terceira peca do meio...");
        char frontColor = cube.front.symbols[1][1];
        char rightColor = cube.right.symbols[1][1];

        //Se estiver num canto, tira dele
        //Se estiver no 2,2
        if(cube.front.symbols[1][2] == rightColor && cube.right.symbols[1][0] == frontColor) middleAlgorithmFront(moveList, "left");
        //Se estiver no 2,0
        else if((cube.front.symbols[1][0] == frontColor && cube.left.symbols[1][2] == rightColor) ||
                (cube.front.symbols[1][0] == rightColor && cube.left.symbols[1][2] == frontColor))
            middleAlgorithmFront(moveList, "right");

        while(!((cube.right.symbols[2][1] == rightColor && cube.down.symbols[1][2] == frontColor) ||
                (cube.front.symbols[2][1] == frontColor && cube.down.symbols[0][1] == rightColor))){
            cube.rotate(cube, "D "); moveList.add("D ");
        }

        if (cube.right.symbols[2][1] == rightColor && cube.down.symbols[1][2] == frontColor) middleAlgorithmRight(moveList, "right");
        else if (cube.front.symbols[2][1] == frontColor && cube.down.symbols[0][1] == rightColor) middleAlgorithmFront(moveList,"left");

        if(cube.right.symbols[1][0] == rightColor && cube.front.symbols[1][2] == frontColor)
        {System.out.println("Terceira peca do meio resolvida com sucesso"); return true;}
        return false;
    }
    //Resolve a quarta peca de canto do meio
    private boolean solveMiddleTwoZero(List<String> moveList){
        System.out.println("Arrumando a Quarta peca do meio...");
        char frontColor = cube.front.symbols[1][1];
        char leftColor = cube.left.symbols[1][1];

        //Se tiver no canto, tira
        if(cube.front.symbols[1][0] == leftColor && cube.left.symbols[1][2] == frontColor) middleAlgorithmLeft(moveList, "left");

        while(!((cube.left.symbols[2][1] == leftColor && cube.down.symbols[1][0] == frontColor) ||
                (cube.front.symbols[2][1] == frontColor && cube.down.symbols[0][1] == leftColor))){
            cube.rotate(cube, "D "); moveList.add("D ");
        }
        if(cube.left.symbols[2][1] == leftColor && cube.down.symbols[1][0] == frontColor) middleAlgorithmLeft(moveList, "left");
        else if(cube.front.symbols[2][1] == frontColor && cube.down.symbols[0][1] == leftColor) middleAlgorithmFront(moveList, "right");

        if(cube.front.symbols[1][0] == frontColor && cube.left.symbols[1][2] == leftColor)
        {System.out.println("Quarta peca do meio resolvida com sucesso"); return true;}
        return false;
    }

    //Faz a cruz na camada de baixo
    private boolean solveDownCross(List<String> moveList){
        System.out.println("Resolvendo cruz de baixo...");
        char downColor = cube.down.symbols[1][1];

        //Enquanto nao existir cruz
        while(!(downColor == cube.down.symbols[0][1] && downColor == cube.down.symbols[1][2] &&
                downColor == cube.down.symbols[2][1] && downColor == cube.down.symbols[1][0])){

            //Testa retas
            if(downColor == cube.down.symbols[1][0] && downColor == cube.down.symbols[1][2]){
                //F L D L' D' F'
                cube.rotate(cube, "F "); cube.rotate(cube, "L "); cube.rotate(cube, "D "); cube.rotate(cube, "L'");
                cube.rotate(cube, "D'"); cube.rotate(cube, "F'");
                moveList.add("F "); moveList.add("L "); moveList.add("D "); moveList.add("L'"); moveList.add("D'");
                moveList.add("F'");
            }
            else if(downColor == cube.down.symbols[0][1] && downColor == cube.down.symbols[2][1]){
                //L B D B' D' L'
                cube.rotate(cube, "L "); cube.rotate(cube, "B "); cube.rotate(cube, "D "); cube.rotate(cube, "B'");
                cube.rotate(cube, "D'"); cube.rotate(cube, "L'");
                moveList.add("L "); moveList.add("B "); moveList.add("D "); moveList.add("B'"); moveList.add("D'");
                moveList.add("L'");
            }

            //Testa L's
            //L 21 11 12
            else if(cube.down.symbols[2][1] == downColor && cube.down.symbols[1][2] == downColor){
                //F D L D' L' F'
                cube.rotate(cube, "F "); cube.rotate(cube, "D "); cube.rotate(cube, "L "); cube.rotate(cube, "D'");
                cube.rotate(cube, "L'"); cube.rotate(cube, "F'");
                moveList.add("F "); moveList.add("D "); moveList.add("L "); moveList.add("D'"); moveList.add("L'");
                moveList.add("F'");
            }
            //L 01 11 10
            else if(cube.down.symbols[0][1] == downColor && cube.down.symbols[1][0] == downColor){
                //B D R D' R' B'
                cube.rotate(cube, "B "); cube.rotate(cube, "D "); cube.rotate(cube, "R "); cube.rotate(cube, "D'");
                cube.rotate(cube, "R'"); cube.rotate(cube, "B'");
                moveList.add("B "); moveList.add("D "); moveList.add("R "); moveList.add("D'"); moveList.add("R'");
                moveList.add("B'");
            }
            //L 21 11 10
            else if(cube.down.symbols[2][1] == downColor && cube.down.symbols[1][0] == downColor){
                // R D F D' F' R'
                cube.rotate(cube, "R "); cube.rotate(cube, "D "); cube.rotate(cube, "F "); cube.rotate(cube, "D'");
                cube.rotate(cube, "F'"); cube.rotate(cube, "R'");
                moveList.add("R "); moveList.add("D "); moveList.add("F "); moveList.add("D'");
                moveList.add("F'"); moveList.add("R'");
            }
            //L 01 11 12
            else if(cube.down.symbols[0][1] == downColor && cube.down.symbols[1][2] == downColor){
                //L D B D' B' L'
                cube.rotate(cube, "L "); cube.rotate(cube, "D "); cube.rotate(cube, "B "); cube.rotate(cube, "D'");
                cube.rotate(cube, "B'"); cube.rotate(cube, "L'");
                moveList.add("L "); moveList.add("D "); moveList.add("B "); moveList.add("D'"); moveList.add("B'");
                moveList.add("L'");
            }

            //se nao, aplica o algoritmo do ponto
            else{
                //F D L D' L' F'
                cube.rotate(cube, "F "); cube.rotate(cube, "D "); cube.rotate(cube, "L "); cube.rotate(cube, "D'");
                cube.rotate(cube, "L'"); cube.rotate(cube, "F'");
                moveList.add("F "); moveList.add("D "); moveList.add("L "); moveList.add("D'"); moveList.add("L'");
                moveList.add("F'");
            }
        }

        System.out.println("Cruz de baixo resolvida com sucesso");
        return true;
    }
}
