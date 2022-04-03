package sample.views;

import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import sample.events.EventoLoteria;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import javax.swing.*;
import java.util.Timer;
import java.util.TimerTask;

public class Loteria extends Stage implements EventHandler {

    private VBox vBox;
    private HBox hBox1, hBox2;
    private Button btnAtras, btnSiguiente, btnJugar;
    private Label lblTiempo;
    private GridPane gdpPlantilla;
    private Image imgCarta;
    private ImageView imvCarta;
    private Scene escena;
    private String[] arImagenes = {"barril.jpeg","botella.jpeg","catrin.jpeg","chavorruco.jpeg","concha.jpeg","graduada.jpeg","luchador.jpeg","maceta.jpeg","campana.jpg"
    ,"colcha.jpg","exprimidor.jpg","Godin.jpg","homeoffice.PNG","horchata.jpg","mango.jpg","covid.PNG","twitter.jpg","rosa.jpeg","tacos.jpeg","venado.jpeg"};
    private Button[][] arBtnPlantilla = new Button[4][4];
    public static String[][] ide = new String[4][4];
    private String[] plants={"6 11 12 5 6 7 0 19 1 6 2 4 16 15 13 18","3 2 5 16 16 12 2 0 9 6 4 7 17 12 0 19","4 15 19 0 13 14 3 5 17 9 10 3 7 11 8 12",
            "4 10 9 7 15 13 1 4 15 19 0 3 11 7 12 6","8 19 19 0 2 17 5 16 11 2 13 4 4 18 2 10"};
    private int[] indexe = new int[16];
    private TimerTask task;
    private String holder;
    public static int b=0;
    private int t=0;
    int count=4;
    Timer timer = new Timer();
    public Loteria(){
        CrearUI();
        this.setTitle("Loteria :)");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {


        //EventoLoteria objEvento = new EventoLoteria();
        btnAtras     = new Button("Anterior");
        btnAtras.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventoLoteria(1,plants,b,arImagenes,arBtnPlantilla));
        btnAtras.setPrefWidth(100);
        btnSiguiente = new Button("Siguiente");
        //btnSiguiente.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventoLoteria(2));
        btnSiguiente.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.out.println("Hola evento 3");
                b++;
                if(b>4){
                    b=0;
                }
                cambiarplant();
            }
        });
        btnSiguiente.setPrefWidth(100);
        lblTiempo    = new Label("00:00");
        hBox1        = new HBox();
        hBox1.setSpacing(5);
        hBox1.getChildren().addAll(btnAtras,btnSiguiente,lblTiempo);

        gdpPlantilla = new GridPane();
        CrearPlantillas();
        imgCarta     = new Image("sample/images/reverse.PNG");
        imvCarta     = new ImageView(imgCarta);
        imvCarta.setFitHeight(200);
        imvCarta.setFitWidth(150);

        hBox2        = new HBox();
        hBox2.setSpacing(5);
        hBox2.getChildren().addAll(gdpPlantilla, imvCarta);

        btnJugar     = new Button("Jugar");
        btnJugar.setPrefWidth(290);
        btnJugar.addEventHandler(MouseEvent.MOUSE_CLICKED,this);

        vBox         = new VBox();
        vBox.setPadding(new Insets(5));
        vBox.setSpacing(5);
        vBox.getChildren().addAll(hBox1,hBox2,btnJugar);

        escena = new Scene(vBox,450,360);
    }

    private void CrearPlantillas() {
        int a=0;
        String[] strArray = plants[b].split(" ");
        int[] intArray = new int[strArray.length];
        for(int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Image imgCartP = new Image("sample/images/" + arImagenes[intArray[a]]);
                    a++;
                    ImageView imv = new ImageView(imgCartP);
                    imv.setFitHeight(60);
                    imv.setFitWidth(50);
                    arBtnPlantilla[j][i] = new Button();
                    arBtnPlantilla[j][i].setGraphic(imv);
                    //arBtnPlantilla[j][i].setId(arImagenes[intArray[a]]);
                    //holder = arImagenes[intArray[a]];
                    ide[j][i] = arImagenes[intArray[a-1]];
                    //a=j;
                    //b=i;
                    arBtnPlantilla[j][i].addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
                        @Override
                        public void handle(MouseEvent event) {
                            for (int r = 0; r < 4; r++) {
                                for (int c = 0; c < 4; c++) {
                                    if (event.getSource() == arBtnPlantilla[c][r]) {
                                        if(ide[c][r]==holder) {
                                            arBtnPlantilla[c][r].setDisable(true);
                                        }
                                    }
                                }
                            }
                        }
                    });
                    gdpPlantilla.add(arBtnPlantilla[j][i], i, j);
                }
            }
        }
    }
    private void cambiarplant(){
        int a=0;
        String[] strArray = plants[b].split(" ");
        int[] intArray = new int[strArray.length];
        for(int i = 0; i < strArray.length; i++) {
            intArray[i] = Integer.parseInt(strArray[i]);
        }
        {
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    Image imgCartP = new Image("sample/images/" + arImagenes[intArray[a]]);
                    a++;
                    ImageView imv = new ImageView(imgCartP);
                    imv.setFitHeight(60);
                    imv.setFitWidth(50);
                    arBtnPlantilla[j][i].setGraphic(imv);
                    ide[j][i] = arImagenes[intArray[a-1]];
                }
            }
        }
    }
    @Override
    public void handle(Event event) {
        /*TimerTask task = new TimerTask() {
            int tic = 0;
            String g;
            public void run() {
                count--;
                if(count < 0){
                    count = 3;
                }
                g=String.valueOf(count);
                lblTiempo.setText("00:0"+g);
                //System.out.println("00:0"+count);
            }
        };*/
        Integer[] var = shuffledeck();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run(){
                Platform.runLater(new Runnable() {

                    String g;
                    public void run() {
                        count--;

                        if (count < 0) {
                            count = 3;
                            if(t>=20||alloff()){
                                timer.cancel();
                                if(alloff()){
                                    JOptionPane.showMessageDialog(null,"Juego Terminado: Felicidades!");
                                }else if(t>=20){
                                    JOptionPane.showMessageDialog(null,"Juego Perdido");
                                }

                                System.exit(0);
                            }
                            cambiocarta(var,t);
                            t++;
                        }
                        g = String.valueOf(count);
                        lblTiempo.setText("00:0" + count);

                    }
                });
            }
        },0,1000);
        //shuffledeck();
        btnJugar.setDisable(true);
        btnSiguiente.setDisable(true);
        btnAtras.setDisable(true);
        System.out.println("Mi primer evento Fovifest :)");
        //timer.schedule(task,10,1000);
    }
    public void cambiocarta(Integer[] var,int co){
        String name = arImagenes[var[co]];
        Image urll = new Image("sample/images/"+name);
        //imvCarta     = new ImageView(imgCarta);
        imvCarta.setImage(urll);
        holder = name;
    }
    public Integer[] shuffledeck(){
        Integer[] intArray = { 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19 };

        List<Integer> intList = Arrays.asList(intArray);

        Collections.shuffle(intList);

        intList.toArray(intArray);

        System.out.println(Arrays.toString(intArray));
        return intArray;
    }
    public boolean alloff(){
        int a = 0;
        boolean flag = false;
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {

                if(arBtnPlantilla[j][i].isDisabled()){
                    a++;
                }
            }
        }
        if(a==16){
            flag = true;
        }
        return flag;
    }

}