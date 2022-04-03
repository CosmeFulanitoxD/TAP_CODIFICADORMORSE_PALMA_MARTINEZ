package sample.views;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Parseador extends Stage  implements EventHandler <KeyEvent>{

    private VBox vBox;
    private ToolBar tlbMenu;
    private TextArea txtEntrada, txtSalida;
    private FileChooser flcArchivo;
    private Button btnAbrir,btnTrans;
    private Scene escena;
    private Image imgAbrir;
    private StringBuilder fielContent = new StringBuilder("");
    private StringBuilder transContent = new StringBuilder("");
    private ImageView imvAbrir;
    List<String> content = new ArrayList<>();
    char[] english = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x',
            'y', 'z', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0',
            ',', '.', '?' };
    String[] newer = {"A","B","C","D","E","F","G","H","I","J","K","L",
            "M","N","O","P","Q","R","S","T","U","V","W","X",
            "Y","Z","DIGIT1","DIGIT2","DIGIT3","DIGIT4","DIGIT5","DIGIT6","DIGIT7","DIGIT8","DIGIT9","DIGIT0",
            "COMMA","PERIOD","QUOTE"
    ,"SPACE","ENTER"};

    String[] deletion = {"A","B","C","D","E","F","G","H","I","J","K","L",
            "M","N","O","P","Q","R","S","T","U","V","W","X",
            "Y","Z","1","2","3","4","5","6","7","8","9","0",
            ",",".","?"};

    String[] morse = { ".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..",
            ".---", "-.-", ".-..", "--", "-.", "---", ".---.", "--.-", ".-.",
            "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--..", ".----",
            "..---", "...--", "....-", ".....", "-....", "--...", "---..", "----.",
            "-----", "--..--", ".-.-.-", "..--.."
    ," ","\n"};

    public Parseador(){
        CrearUI();
        this.setTitle("Traductor de Código Morse");
        this.setScene(escena);
        this.show();
    }

    private void CrearUI() {
        vBox = new VBox();
        tlbMenu = new ToolBar();
        imgAbrir = new Image("sample/images/7265_mail_open_icon.png");
        imvAbrir = new ImageView(imgAbrir);
        imvAbrir.setFitHeight(25);
        imvAbrir.setFitWidth(25);
        btnTrans = new Button("Traducir");
        btnTrans.setOnAction(event -> {
            try{
                String str = "";
                for(int i = 0; i<=content.size()-1; i++){
                    str = "";
                    char[] chars = content.get(i).toCharArray();
                    for (int k = 0; k < chars.length; k++){
                        for (int j = 0; j < english.length; j++){
                            if (english[j] == chars[k]){
                                str = str + morse[j] + " ";
                            }
                        }
                    }
                    content.set(i,str);
                }
                for (Object o : content){
                    transContent.append(o + "\n");
                }
                txtSalida.setText(transContent.toString());
            }catch (Exception e){

            }
        });

        btnAbrir = new Button();
        btnAbrir.setGraphic(imvAbrir);
        btnAbrir.setOnAction(event -> {
           // aqui va el código
            flcArchivo = new FileChooser();
            flcArchivo.setTitle("Buscar archivo...");
            File archivo = flcArchivo.showOpenDialog(this);
            try {
                content = Files.readAllLines(Paths.get(String.valueOf(archivo)), StandardCharsets.UTF_8);
                /*for (int i = 0; i<=content.size();i++){
                    fielContent.append(content);
                }*/
                for (Object o : content){
                    fielContent.append(o + "\n");
                }
               // fielContent.append(content);
                txtEntrada.setText(fielContent.toString());
                //System.out.println(content.get(2));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.print("");
        });
        tlbMenu.getItems().addAll(btnAbrir);
        //....
        txtEntrada = new TextArea();
        txtEntrada.setPromptText("Introduce el texto a parsear");
        txtEntrada.setOnKeyPressed(this);
        txtSalida = new TextArea();
        txtSalida.setEditable(false);
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(5));
        vBox.getChildren().addAll(tlbMenu,txtEntrada,txtSalida,btnTrans);
        escena = new Scene(vBox,500,300);
    }



    @Override
    public void handle(KeyEvent event) {
        String streng="",com;
        Boolean already= false;
        com = event.getCode().toString();
            for (int j = 0; j < english.length+2; j++){

                if (newer[j].equals(com)){
                    //streng = streng + morse[j] + " ";
                    txtSalida.appendText(morse[j]);
                }
            }
        try {
            if (com.equals("BACK_SPACE")){
                String deleter = txtSalida.getText().toString();
                String last = txtEntrada.getText();
                last = last.substring(last.length()-1);
                if (last.equals("\n")){
                    deleter = deleter.substring(0,deleter.length()-1);
                    txtSalida.setText(deleter);
                }else {
                    for (int m = 0; m < english.length; m++) {
                        if (deletion[m].toLowerCase().equals(last.toLowerCase())) {
                            for (int k = 0; k <= morse[m].length() - 1; k++) {
                                deleter = deleter.substring(0, deleter.length() - 1);
                                txtSalida.setText(deleter);
                                already = true;
                            }
                        }
                    }
                    if (already = false) {
                        deleter = deleter.substring(0, deleter.length() - 1);
                        txtSalida.setText(deleter);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println(event.getCode().toString());
    }
}
