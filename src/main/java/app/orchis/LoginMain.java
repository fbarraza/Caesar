package app.orchis;


import app.orchis.controladors.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;

public class LoginMain extends Application{

    @Override
    public void start(Stage stage) throws IOException {
        Parent root = null;
        FXMLLoader loader = new FXMLLoader();
        //root = FXMLLoader.load(getClass().getClassLoader().getResource("vistes/FXMLLogin.fxml"));
        root = FXMLLoader.load(getClass().getResource("/vistes/FXMLSplash.fxml"));
        //LoginController appCtrl = (LoginController) loader.getController();
        Scene scene = new Scene(root);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();   
    }
    
    public static void main(String[] args) {
        launch(args);
        
    }   
}
