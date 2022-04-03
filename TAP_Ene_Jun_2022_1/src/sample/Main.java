package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import javafx.event.Event;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import sample.events.EventoLoteria;
import sample.views.Loteria;
import sample.views.Parseador;

public class Main extends Application implements EventHandler<Event> {

    private MenuBar mnbPrincipal;
    private Menu menCompetencia1, menCompetencia2;
    private MenuItem mitLoteria, mitParseador;
    private VBox vBox;

    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        vBox = new VBox();
        mnbPrincipal = new MenuBar();
        menCompetencia1 = new Menu("Competencia 1");
        menCompetencia2 = new Menu("Competencia 2");
        mnbPrincipal.getMenus().addAll(menCompetencia1,menCompetencia2);

        mitLoteria = new MenuItem("Loteria");
        mitLoteria.setOnAction(event -> Eventos(1));
        mitParseador = new MenuItem("Código Morse");
        mitParseador.setOnAction(event -> Eventos(2));
        menCompetencia1.getItems().addAll(mitLoteria,mitParseador);

        vBox.getChildren().add(mnbPrincipal);

        primaryStage.addEventHandler(WindowEvent.WINDOW_SHOWING,this);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(vBox, 300, 275));
        primaryStage.show();
        primaryStage.setMaximized(true);
    }

    private void Eventos(int i) {
        switch(i){
            case 1: new Loteria(); break;
            case 2: new Parseador(); break;
        }
    }


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void handle(Event event) {
        Alert alerta = new Alert(Alert.AlertType.INFORMATION);
        alerta.setTitle("Bienvenidos");
        alerta.setHeaderText("Mensaje del dia:");
        alerta.setContentText("Insertando ebola en el sistema no se desconecte o morira");
        alerta.showAndWait();
    }

}
