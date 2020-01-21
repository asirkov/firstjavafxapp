package javafxapp.loginwindow;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.MotionBlur;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafxapp.loginwindow.config.LoginConfig;

public class LoginWindow extends Application {
    private String user = "admin";
    private String pw = "admin";

    private String checkUser;
    private String checkPw;

    private BorderPane createLoginPanel() {
        BorderPane bp = new BorderPane();
        bp.setPadding(new Insets(10, 50, 50, 50));

        HBox hb = new HBox();
        hb.setPadding(new Insets(20, 20, 20, 30));

        GridPane gp = new GridPane();
        gp.setPadding(new Insets(20, 20, 20, 20));
        gp.setHgap(5);
        gp.setVgap(5);

        Label lblUserName = new Label("User");
        final TextField txtUserName = new TextField();
        Label lblPassWord = new Label("Password");
        final TextField txtPassWord = new PasswordField();

        Button btnLogin = new Button("Login");
        Label lblMessage = new Label();

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

        return bp;
    }

//    @Override
//    public void start(Stage primaryStage) throws Exception {
//        primaryStage.setTitle("Login");
//
//        BorderPane bp = createLoginPanel();
//
////        Scene scene = new Scene(bp);
//
//        primaryStage.setScene(new Scene(bp));
//        primaryStage.setResizable(false);
//        primaryStage.setWidth(600);
//        primaryStage.setHeight(600);
//        primaryStage.show();
//    }

    private GridPane createRegistrationFormPane() {
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(40));
        gp.setHgap(10);
        gp.setVgap(10);

        addUIControls(gp);

        return gp;
    }

    private void addUIControls(GridPane gp) {
        Label lblHeader = new Label("Login ");
        lblHeader.setFont(Font.font("Arial", FontWeight.BOLD, 36));
        GridPane.setHalignment(lblHeader, HPos.CENTER);
        GridPane.setMargin(lblHeader, new Insets(20, 0,20,0));

        Label lblMessage = new Label();
        lblMessage.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.ITALIC, 14));
        lblMessage.setPrefWidth(120);

        Label lblLogin = new Label("Login: ");
        lblLogin.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        TextField txtLogin = new TextField();
        txtLogin.setPrefHeight(40);

        Label lblPassword = new Label("Password: ");
        lblPassword.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        TextField txtPassword = new PasswordField();
        txtPassword.setPrefHeight(40);

        Button btnLogin = new Button("Log in");

        btnLogin.setOnAction(e -> {
            checkUser = txtLogin.getText();
            checkPw = txtPassword.getText();
            if(checkUser.equals(user) && checkPw.equals(pw)){
                lblMessage.setText("Log in successfull!");
                lblMessage.setTextFill(Color.GREEN);
            }
            else{
                lblMessage.setText("Incorrect user or pw.");
                lblMessage.setTextFill(Color.RED);
            }
            txtLogin.setText("");
            txtPassword.setText("");
        });

        btnLogin.setPrefHeight(40);
        btnLogin.setPrefWidth(100);
        btnLogin.setDefaultButton(true);
        btnLogin.setCursor(Cursor.HAND);

        Hyperlink lnkRegister = new Hyperlink("Register");
        lnkRegister.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.ITALIC, 14));
        lnkRegister.setOnAction(e -> {
            getHostServices().showDocument(LoginConfig.REGISTRATION_URL);
        });

        gp.add(lblHeader, 0,0, 2, 1);
        gp.add(lblLogin, 0, 1);
        gp.add(lblPassword, 0, 2);
        gp.add(txtLogin, 1, 1);
        gp.add(txtPassword, 1, 2);
        gp.add(btnLogin, 1, 3);
        gp.add(lblMessage, 2, 3);
        gp.add(lnkRegister, 0, 3);


//        gp.setCache(true);
//        MotionBlur motionBlur = new MotionBlur();
//        motionBlur.setRadius(10.0f);
//        gp.setEffect(motionBlur);

        GridPane.setHalignment(btnLogin, HPos.CENTER);
        GridPane.setMargin(btnLogin, new Insets(20, 0, 20, 0));
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Form JavaFX Application");

        GridPane gp = createRegistrationFormPane();
        Scene scene = new Scene(gp, 800, 480);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
