package javafxapp.loginwindow;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginWindow extends Application {
    private String user = "admin";
    private String pw = "admin";

    private String checkUser;
    private String checkPw;


    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Login");

        BorderPane bp = new BorderPane();
        //bp.setPadding(new Insets(10, 50, 50, 50));

        HBox hb = new HBox();
        //

        GridPane gp = new GridPane();
        //
        gp.setHgap(5);
        gp.setVgap(5);

        Label lblUserName = new Label("User");
        final TextField txtUserName = new TextField();
        Label lblPassWord = new Label("Password");
        final TextField txtPassWord = new PasswordField();

        Button btnLogin = new Button("Login");
        final Label lblMessage = new Label();

        gp.add(lblUserName, 0, 0);
        gp.add(txtUserName, 1, 0);
        gp.add(lblPassWord, 0, 1);
        gp.add(txtPassWord, 1, 1);
        gp.add(btnLogin, 2, 1);
        gp.add(lblMessage, 1, 2);

        Reflection r = new Reflection();
        r.setFraction(0.7f);
        gp.setEffect(r);

        Text title = new Text("Login");
        title.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        hb.getChildren().add(title);

        btnLogin.setOnAction(e -> {
            checkUser = txtUserName.getText();
            checkPw = txtPassWord.getText();

            if (checkUser.equals(user) && checkPw.equals(pw)) {
                lblMessage.setText("Success!");
                lblMessage.setTextFill(Color.GREEN);
            } else {
                lblMessage.setText("Access denied!");
                lblMessage.setTextFill(Color.RED);
            }
            txtUserName.setText("");
            txtPassWord.setText("");
        });

        bp.setTop(hb);
        bp.setCenter(gp);


        Scene scene = new Scene(bp);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
