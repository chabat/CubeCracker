import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.control.Button;
import javafx.scene.paint.Paint;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.StackPane;

public class ColorMenu{ //Menu de escolha de cores
    private ColorButton[] buttons = new ColorButton[7]; //Vetor de 6 botoes
    private Color[] colors = {Color.PINK, Color.BLUE, Color.WHITE,Color.GREEN, //Vetor com as cores do cubo
                             Color.YELLOW, Color.RED, Color.ORANGE};
    private Paint paint;     //Cor atualmente selecionada
    private StackPane root;     //o stackpane da cena
    private double posX, posY, sizeBoxes;   //posicao e tamanho dos botoes
    private int i;  //Indice

    ColorMenu(StackPane r, double pX, double pY, double sB){    //metodo construtor
        this.root = r;  //inicializa as variaveis com os argumentos
        this.posX = pX;
        this.posY = pY;
        this.sizeBoxes = sB;

        double xBoxes = posX, yBoxes = posY;    //posicoes does botoes
        for(i = 1; i <= 6; i++){    //itera sobre os botoes
            buttons[i] = new ColorButton(sizeBoxes, sizeBoxes, xBoxes, yBoxes, colors[i]); //instancia os botoes
            this.paint = Color.GREY; //a cor inicialmente selecionada eh o cinza
            this.buttons[i].button.setOnAction(new EventHandler<ActionEvent>(){ //Trata os cliques
                @Override
                public void handle(ActionEvent event){
                    Object source = event.getSource(); //Guarda qual botao foi clicado
                    for(int i = 1; i <= 6; i++){    //itera sobre os botoes
                        if(source == buttons[i].button){    //quando achar o que foi clicado
                            buttons[i].shape.setStroke(Color.YELLOW);   //muda a cor da borda
                            paint = buttons[i].shape.getFill(); //atualiza a cor atualmente selecionada
                        }
                        else buttons[i].shape.setStroke(Color.BLACK); //muda de volta a cor da borda dos outros
                    }
                }
            });
            root.getChildren().addAll(this.buttons[i].shape,this.buttons[i].button); //adiciona o botao ao stackpane
            xBoxes += sB;   //Incrimenta o X do proximo botao
            if(i % 3 == 0){xBoxes = posX; yBoxes += sB;} //se tiver desenhado o terceiro, incrimenta o y
        }
    }

    public Paint getPaint(){return this.paint;} //getter da cor atualmente selecionada
    public ColorButton getPainter(Paint paint){ //retorna o botao da cor passada como parametro
        ColorButton painter = new ColorButton(0,0,0,0,Color.RED);
        for(int i = 1; i <= 6; i++){    //itera sobre todos os botoes
            if(this.buttons[i].color == paint) painter =  this.buttons[i]; //se for o da cor passada salva
        }
        return painter; //e retorna
    }
}
