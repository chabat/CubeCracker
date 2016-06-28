/*
Classe da barra que exibe as instrucoes

author: Felipe Chabatura Neto (felipechabat at gmail.com)
*/

import javafx.scene.shape.Rectangle;
import javafx.scene.paint.Color;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.StrokeType;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import java.util.List;
import java.util.ArrayList;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;

public class MovePlayer{
    Cube cube;
    Rectangle[] slot;
    ColorButton playButton;
    Text[] texts;
    double slotSize, posX, posY;
    String moveArray[];
    List<String> moveList;
    StackPane root;
    int index;

    MovePlayer(double sS, double pX, double pY, List<String> mL, StackPane rt, Cube cb){
        this.cube = cb;
        this.index = 0;
        this.slotSize = sS;
        this.posX = pX;
        this.posY = pY;
        this.moveList = mL;
        this.root = rt;
        double currentPos = pX;
        texts = new Text[5];
        slot = new Rectangle[5];
        for(int i = 0; i < 5; i++){
            slot[i] = new Rectangle(this.slotSize, this.slotSize);
            slot[i].setFill(Color.web("2520AD"));
            slot[i].setStroke(Color.BLACK);
            slot[i].setStrokeWidth(6.0);
            slot[i].setStrokeType(StrokeType.CENTERED);
            slot[i].setTranslateX(currentPos);
            slot[i].setTranslateY(pY);

            texts[i] = new Text();
            texts[i].setTranslateY(pY);
            texts[i].setTranslateX(currentPos);
            texts[i].setFont(Font.font("Verdana", 60));
            if(i == 2) texts[i].setFill(Color.WHITE);
            else texts[i].setFill(Color.GRAY);

            currentPos += slotSize;

            //logica do botao
            if(i == 4){
                playButton = new ColorButton(this.slotSize/4, this.slotSize/4, currentPos - this.slotSize/4, pY - this.slotSize/4, Color.GRAY);
                playButton.button.setText("P");
                playButton.button.setOnAction(new EventHandler<ActionEvent>(){
                    @Override
                    public void handle(ActionEvent event){
                        if(playButton.shape.getFill() == Color.GRAY){
                            playButton.shape.setFill(Color.GREEN);
                        }
                        next();
                    }
                });
                root.getChildren().addAll(playButton.shape, playButton.button);
            }

            root.getChildren().addAll(slot[i], texts[i]);
        }
    }



    public void start(){
        moveArray = new String[moveList.size()];
        moveList.toArray(moveArray);
        update();
    }

    public void next(){
        if(index <= moveArray.length){
            if(index < moveArray.length) this.cube.rotate(cube, moveArray[index]);
            index++;
            this.cube.update();
            update();
        }
    }
    private void update(){
        if(index > 1) texts[0].setText(moveArray[index - 2]);
        else texts[0].setText("");
        if(index > 0) texts[1].setText(moveArray[index - 1]);
        else texts[1].setText("");
        if(index < moveArray.length) texts[2].setText(moveArray[index]);
        else texts[2].setText("");
        if(index < moveArray.length - 1) texts[3].setText(moveArray[index + 1]);
        else texts[3].setText("");
        if(index < moveArray.length - 2) texts[4].setText(moveArray[index + 2]);
        else texts[4].setText("");
    }
}
