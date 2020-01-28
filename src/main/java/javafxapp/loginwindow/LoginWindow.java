package javafxapp.loginwindow;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafxapp.config.Config;
import javafxapp.loginwindow.config.LoginWindowConfig;
import javafxapp.labels.BigLabel;
import javafxapp.labels.RegularLabel;

public class LoginWindow extends Application {
    private String user = "admin";
    private String pw = "admin";

    private String checkUser;
    private String checkPw;

    private void lightTextBoxes(Color color, TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setBorder(new Border(new BorderStroke(color,
                    BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1.5d))));
        }
    }

    private GridPane createLoginWindowPane(double width) {
        GridPane gp = new GridPane();
        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(40));
        gp.setHgap(10);
        gp.setVgap(10);
//        gp.setGridLinesVisible(true);

        Label lblHeader = new BigLabel("Login ", width / 3);
        lblHeader.setFont(Font.font("Arial", FontWeight.EXTRA_BOLD, FontPosture.ITALIC, 32));
        lblHeader.setAlignment(Pos.CENTER);

        Label lblMessage = new RegularLabel("", width / 3);
        lblMessage.setPrefHeight(40);
        lblMessage.setAlignment(Pos.CENTER);

        Label lblLogin = new RegularLabel("Login: ", width / 6);
        lblLogin.setAlignment(Pos.CENTER_RIGHT);


        TextField txtLogin = new TextField();
        txtLogin.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        txtLogin.setPrefHeight(40);
        txtLogin.setMinWidth(width / 3);

        Label lblPassword = new RegularLabel("Password: ", width / 6);
        lblPassword.setAlignment(Pos.CENTER_RIGHT);

        TextField txtPassword = new PasswordField();
        txtPassword.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        txtPassword.setPrefHeight(40);
        txtPassword.setMinWidth(width / 3);

        Button btnLogin = new Button("Log in");
        btnLogin.setMinSize(width / 6, 40);
        btnLogin.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        btnLogin.setAlignment(Pos.CENTER);
        btnLogin.setCursor(Cursor.HAND);
        btnLogin.setDefaultButton(true);

        Label lblAbout = new RegularLabel("", width / 6);
        lblAbout.setAlignment(Pos.CENTER_LEFT);

        btnLogin.setOnAction(e -> {
            checkUser = txtLogin.getText();
            checkPw = txtPassword.getText();
            if(checkUser.equals(user) && checkPw.equals(pw)){
                lblMessage.setText("Successfully login!");
                lblMessage.setTextFill(Color.GREEN);
            }
            else{
                lblMessage.setText("Incorrect login or password!");
                lblMessage.setTextFill(Color.RED);

                lightTextBoxes(Color.RED, txtLogin, txtPassword);
            }
            txtPassword.setText("");
        });

        gp.setOnMouseClicked(e -> {
            lightTextBoxes(Color.TRANSPARENT, txtLogin, txtPassword);
            lblMessage.setText("");
        });

        Hyperlink lnkRegister = new Hyperlink("Register");
        lnkRegister.setFont(Font.font("Arial", FontWeight.NORMAL, FontPosture.ITALIC, 14));
        lnkRegister.setAlignment(Pos.CENTER);
        lnkRegister.setPrefWidth(width / 6);
        lnkRegister.setOnAction(e ->
                getHostServices().showDocument(LoginWindowConfig.REGISTRATION_URL));


        gp.add(lblHeader, 1, 0);
        gp.add(lblMessage, 1, 1);
        gp.add(lblLogin, 0, 2);
        gp.add(txtLogin, 1, 2);
        gp.add(lblPassword, 0, 3);
        gp.add(txtPassword, 1, 3);
        gp.add(new HBox(btnLogin, lnkRegister), 1, 4);
        gp.add(lblAbout, 2, 4);


        ColumnConstraints leftCol = new ColumnConstraints(width / 3);
        leftCol.setHalignment(HPos.RIGHT);
        ColumnConstraints centerCol = new ColumnConstraints(width / 3);
        centerCol.setHalignment(HPos.CENTER);
        ColumnConstraints rightCol = new ColumnConstraints(width / 3);
        rightCol.setHalignment(HPos.LEFT);

        return gp;
    }


    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Login Window");

        GridPane gp = createLoginWindowPane(Config.WINDOW_WIDTH);
        Scene scene = new Scene(gp, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
