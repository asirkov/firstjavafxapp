package javafxapp.authwindows.loginwindow;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxapp.authwindows.config.AuthConfig;
import javafxapp.authwindows.registerwindow.RegisterWindow;
import javafxapp.authwindows.util.ActionButton;
import javafxapp.authwindows.util.checkboxes.RegularCheckBox;
import javafxapp.authwindows.util.labels.BigLabel;
import javafxapp.authwindows.util.labels.SmallLabel;
import javafxapp.authwindows.util.links.RegularLink;
import javafxapp.authwindows.util.textfields.RegularPasswordField;
import javafxapp.authwindows.util.textfields.RegularTextField;
import javafxapp.config.Config;
import javafxapp.mainwindow.MainWindow;
import javafxapp.util.labels.RegularLabel;

public class LoginWindow extends Parent {
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

    private void loadRegisterWindow(Stage primaryStage) {
        Scene registerWindowScene = new Scene(new RegisterWindow(primaryStage));

        primaryStage.setTitle("Register");
        primaryStage.setScene(registerWindowScene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    private void loadMainWindow(Stage primaryStage) {
        Scene registerWindowScene = new Scene(new MainWindow(primaryStage, null));

        primaryStage.setTitle("MainWindow");
        primaryStage.setScene(registerWindowScene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    private GridPane createLoginWindowPane(Stage primaryStage, double width, double height) {
        GridPane gp = new GridPane();

        gp.setMinSize(width, height);

        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(40));
        gp.setHgap(10);
        gp.setVgap(10);
//        gp.setGridLinesVisible(true);

        // Labels
        Label lblHeader = new BigLabel("Login ");
        lblHeader.setAlignment(Pos.CENTER);
        Label lblMessage = new SmallLabel("");
        lblMessage.setAlignment(Pos.CENTER);

        Label lblLogin = new SmallLabel("Login: ");
        Label lblPassword = new SmallLabel("Password: ");

        // Text fields
        TextField txtLogin = new RegularTextField();
        final PasswordField txtPassword = new RegularPasswordField();
        TextField txtPasswordVisible = new RegularTextField();

        // Checkbox for show passwords
        CheckBox chPasswordVisible = new RegularCheckBox("Show password.");
        chPasswordVisible.setFocusTraversable(false);
        chPasswordVisible.setAlignment(Pos.CENTER_LEFT);


        // Register button
        Button btnLogin = new ActionButton("Login");

        // Dummy for center the panes
        Label lblAbout = new SmallLabel("");

        // Link for go to Login window
        Hyperlink lnkRegister = new RegularLink("Register");
        lnkRegister.setOnAction(e -> loadRegisterWindow(primaryStage));


        // Checkbox for visible/invisible password fields
        txtPasswordVisible.managedProperty().bind(chPasswordVisible.selectedProperty());
        txtPasswordVisible.visibleProperty().bind(chPasswordVisible.selectedProperty());
        txtPassword.managedProperty().bind(chPasswordVisible.selectedProperty().not());
        txtPassword.visibleProperty().bind(chPasswordVisible.selectedProperty().not());
        txtPasswordVisible.textProperty().bindBidirectional(txtPassword.textProperty());


        // Action events
        btnLogin.setOnAction(e -> {
            checkUser = txtLogin.getText();
            checkPw = txtPassword.getText();
            if(checkUser.equals(user) && checkPw.equals(pw)){
//                lblMessage.setText("Successfully login!");
//                lblMessage.setTextFill(Color.GREEN);
                loadMainWindow(primaryStage);
            }
            else{
                lblMessage.setText("Incorrect login or password!");
                lblMessage.setTextFill(Color.RED);

                lightTextBoxes(Color.RED, txtLogin, txtPassword, txtPasswordVisible);
            }
            txtPassword.setText("");
        });

        gp.setOnMouseClicked(e -> {
            lightTextBoxes(Color.TRANSPARENT, txtLogin, txtPassword, txtPasswordVisible);
            lblMessage.setText("");
        });

        gp.add(lblHeader, 1, 0);
        gp.add(lblMessage, 1, 1);
        gp.add(lblLogin, 0, 2);
        gp.add(txtLogin, 1, 2);
        gp.add(lblPassword, 0, 3);
        gp.add(txtPassword, 1, 3);
        gp.add(txtPasswordVisible, 1, 3);
        gp.add(chPasswordVisible, 1, 4);
        gp.add(new HBox(btnLogin, lnkRegister), 1, 5);
        gp.add(lblAbout, 2, 5);


        ColumnConstraints leftCol = new ColumnConstraints(AuthConfig.BIG_LABEL_WIDTH);
        leftCol.setHalignment(HPos.RIGHT);
        ColumnConstraints centerCol = new ColumnConstraints(AuthConfig.BIG_LABEL_WIDTH);
        centerCol.setHalignment(HPos.CENTER);
        ColumnConstraints rightCol = new ColumnConstraints(AuthConfig.BIG_LABEL_WIDTH);
        rightCol.setHalignment(HPos.LEFT);

        return gp;
    }

    public LoginWindow(Stage primaryStage) {
        super();
        this.getChildren().add(createLoginWindowPane(primaryStage, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
    }
}
