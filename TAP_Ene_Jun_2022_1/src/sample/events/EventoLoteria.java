package sample.events;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.views.Loteria;
public class EventoLoteria implements EventHandler{
    Loteria refer;
    int opc;
    int a,b;
    String[] arImagenes;
    String[] plants;
    Button[][] arBtnPlantilla;
    public EventoLoteria(int opc, String[] pla, int c, String[] arIm, Button[][] arBtnPlan){
        this.opc = opc;
        this.plants=pla;
        this.b=c;
        this.arImagenes=arIm;
        this.arBtnPlantilla=arBtnPlan;
    }

    @Override
    public void handle(Event event) {
        b=Loteria.b;
        if( opc == 1){
            b--;
            if(b<0){
                b=4;
            }
             a =0;
        String[] strArray = plants[b].split(" ");
        int[] intArray = new int[strArray.length];
        for(int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }

            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Image imgCartP = new Image("sample/images/" + arImagenes[intArray[a]]);
                    a++;
                    ImageView imv = new ImageView(imgCartP);
                    imv.setFitHeight(60);
                    imv.setFitWidth(50);
                    arBtnPlantilla[j][i].setGraphic(imv);
                    Loteria.ide[j][i] = arImagenes[intArray[a-1]];
                }
            }
            Loteria.b=this.b;

        }
        else
            System.out.println("Hola :)");
    }
}
