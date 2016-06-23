import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Paint;
import javafx.scene.layout.StackPane;
import java.util.Map;
import java.util.HashMap;

public class Face{ //Faces do cubo
    private StackPane root; //StackPane
    private double posX, posY, cubiesSize;  //posicao da face e tamanho dos cubies
    private ColorButton[][] cubies = new ColorButton[3][3]; //vetor de colorbuttons
    private int i, j; //indice
    protected char symbols[][] = new char[3][3];
    Map<Color, Character> colorToChar;
    Map<Character, Color> charToColor;

    //construtor, toda face recebe referencia do cMenu
    Face(StackPane r, double x, double y, double cS, ColorMenu cMenu){
        this.root = r;      //seta os atributos
        this.posX = x;
        this.posY = y;
        this.cubiesSize = cS;

        //Cria map de char para color e de color para char
        colorToChar = new HashMap<Color, Character>();
        charToColor = new HashMap<Character, Color>();
        this.colorToChar.put(Color.GRAY, 'X'); this.charToColor.put('X', Color.GRAY);
        this.colorToChar.put(Color.BLUE, 'B'); this.charToColor.put('B', Color.BLUE);
        this.colorToChar.put(Color.WHITE, 'W'); this.charToColor.put('W', Color.WHITE);
        this.colorToChar.put(Color.GREEN, 'G'); this.charToColor.put('G', Color.GREEN);
        this.colorToChar.put(Color.RED, 'R'); this.charToColor.put('R', Color.RED);
        this.colorToChar.put(Color.ORANGE, 'O'); this.charToColor.put('O', Color.ORANGE);
        this.colorToChar.put(Color.YELLOW, 'Y'); this.charToColor.put('Y', Color.YELLOW);

        double xCubies = posX, yCubies = posY; //variaveis das posicoes
        for(i = 0; i < 3; i++)
            for(j = 0; j < 3; j++){ //itera sobre os cubies da face
                this.symbols[i][j] = 'X';
                this.cubies[i][j] = new ColorButton(cubiesSize, xCubies, yCubies, Color.GREY); //cria um botao
                this.cubies[i][j].button.setOnAction(new EventHandler<ActionEvent>() { //trata o evento do clique
                    @Override
                    public void handle(ActionEvent event){
                        Object source = event.getSource();  //salva a origem do evento
                        for(int i = 0; i < 3; i++)
                            for(j = 0; j < 3; j++){        //itera sobre os cubies da face
                                if(source == cubies[i][j].button){ //quanda achar o cubie do qual o evento veio
                                    if(cubies[i][j].shape.getFill() == cMenu.getPaint()){ //se a cor atual for a mesma do cubie
                                        cMenu.getPainter(cubies[i][j].shape.getFill()).amount++; //aumenta o numero da cor atual
                                        cubies[i][j].shape.setFill(Color.GREY); //e tira a cor do cubie
                                        symbols[i][j] ='X';
                                    }
                                    else if(cMenu.getPainter(cMenu.getPaint()).amount > 0){ //se nao, se cor disponivel
                                        if(cubies[i][j].shape.getFill() != Color.GREY) //se nao era cinza antes
                                            cMenu.getPainter(cubies[i][j].shape.getFill()).amount++; //encontra qual era para aumentar qtd
                                        cubies[i][j].shape.setFill(cMenu.getPaint());  //Colore
                                        symbols[i][j] = colorToChar.get(cMenu.getPaint());
                                        cMenu.getPainter(cMenu.getPaint()).amount--; //diminui de quem pintou
                                    }
                                }
                            }
                    }
                });
                root.getChildren().addAll(this.cubies[i][j].shape, this.cubies[i][j].button); //adiciona os botoes a raiz
                xCubies += cubiesSize;
                if(j == 2){ yCubies += cubiesSize; xCubies = posX;}
            }
    }

    public void update(){
        for(int i = 0; i < 3; i++)
            for(j = 0; j < 3; j++)
                this.cubies[i][j].shape.setFill(charToColor.get(this.symbols[i][j]));
    }
}
