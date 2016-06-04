import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.paint.Paint;
import javafx.scene.layout.StackPane;

public class Face{ //Faces do cubo
    private StackPane root; //StackPane
    private double posX, posY, cubiesSize;  //posicao da face e tamanho dos cubies
    private ColorButton[] cubies = new ColorButton[10]; //vetor de colorbuttons
    private int i; //indice

    Face(StackPane r, double x, double y, double cS, ColorMenu cMenu){ //construtor, toda face recebe referencia do cMenu
        this.root = r;      //seta os atributos
        this.posX = x;
        this.posY = y;
        this.cubiesSize = cS;

        double xCubies = posX, yCubies = posY; //variaveis das posicoes
        for(i = 1; i <= 9; i++){ //itera sobre os cubies da face
            this.cubies[i] = new ColorButton(cubiesSize, xCubies, yCubies, Color.GREY); //cria um botao
            this.cubies[i].button.setOnAction(new EventHandler<ActionEvent>() { //trata o evento do clique
                @Override
                public void handle(ActionEvent event){
                    Object source = event.getSource();  //salva a origem do evento
                    for(int i = 1; i <= 9; i++){        //itera sobre os cubies da face
                        if(source == cubies[i].button){ //quanda achar o cubie do qual o evento veio
                            if(cubies[i].shape.getFill() == cMenu.getPaint()){ //se a cor atual for a mesma do cubie
                                cMenu.getPainter(cubies[i].shape.getFill()).amount++; //aumenta o numero da cor atual
                                cubies[i].shape.setFill(Color.GREY); //e tira a cor do cubie
                            }
                            else if(cMenu.getPainter(cMenu.getPaint()).amount > 0){ //se nao, se cor disponivel
                                if(cubies[i].shape.getFill() != Color.GREY) //se nao era cinza antes
                                    cMenu.getPainter(cubies[i].shape.getFill()).amount++; //encontra qual era para aumentar qtd
                                cubies[i].shape.setFill(cMenu.getPaint());  //Colore
                                cMenu.getPainter(cMenu.getPaint()).amount--; //diminui de quem pintou
                            }
                        }
                    }
                }
            });
            root.getChildren().addAll(this.cubies[i].shape, this.cubies[i].button); //adiciona os botoes a raiz
            xCubies += cubiesSize;
            if(i % 3 == 0){ yCubies += cubiesSize; xCubies = posX;}
        }
    }
}
