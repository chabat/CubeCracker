import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;
import javafx.scene.control.Button;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class ColorButton{ // Botao de cor
    Rectangle shape; // forma do botao
    Button button; // o botao em si
    Color color;    //sua cor
    int amount; //quantia disponivel

    ColorButton(double size, double x, double y, Color c){ //Construtor da classe
        this.shape = new Rectangle(size, size); //instancia forma e botao
        this.button = new Button();
        this.button.setMinWidth(size);          //seta tamanho do botao
        this.button.setMinHeight(size);
        this.shape.setTranslateX(x);            //seta posicao da forma e botao
        this.shape.setTranslateY(y);
        this.button.setTranslateX(x);
        this.button.setTranslateY(y);
        this.shape.setStroke(Color.BLACK);  //seta borda para preto
        this.shape.setStrokeWidth(4.0);     //com espessura 4.0
        this.shape.setStrokeType(StrokeType.INSIDE); //tipo de borda
        this.color = c;     //seta cor do botao
        this.shape.setFill(c);  //colore a forma
        this.button.setShape(new Rectangle()); //seta forma do botao
        this.amount = 9;    //inicialmente 9 botoes
    }

}
