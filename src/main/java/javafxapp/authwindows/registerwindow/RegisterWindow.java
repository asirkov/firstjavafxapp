package javafxapp.authwindows.registerwindow;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafxapp.authwindows.config.AuthConfig;
import javafxapp.authwindows.loginwindow.LoginWindow;
import javafxapp.authwindows.util.ActionButton;
import javafxapp.authwindows.util.checkboxes.RegularCheckBox;
import javafxapp.authwindows.util.labels.BigLabel;
import javafxapp.authwindows.util.labels.SmallLabel;
import javafxapp.authwindows.util.links.RegularLink;
import javafxapp.authwindows.util.textfields.RegularPasswordField;
import javafxapp.authwindows.util.textfields.RegularTextField;
import javafxapp.config.Config;
import javafxapp.mainwindow.windows.settingswindow.SettingsWindow;

import java.util.Optional;

public class RegisterWindow extends Parent {


    private void lightTextBoxes(Color color, TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setBorder(new Border(new BorderStroke(color,
                    BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1.5d))));
        }
    }

    private void loadLoginWindow(Stage primaryStage) {
        Scene loginWindowScene = new Scene(new LoginWindow(primaryStage));

        primaryStage.setTitle("Login");
        primaryStage.setScene(loginWindowScene);
        primaryStage.setResizable(false);

        primaryStage.show();
    }

    private void showMessage(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText("Registration was successful\n\nLog in to your account, please.");
        alert.setResizable(false);

        alert.setOnCloseRequest(e -> loadLoginWindow(primaryStage));

        Optional<ButtonType> option = alert.showAndWait();
        option.ifPresent(op ->alert.close());

    }

    private void showConfirmation(Stage primaryStage) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Registration?");
        alert.setResizable(false);

        Optional<ButtonType> option = alert.showAndWait();
        option.ifPresent(op -> {
            if (option.get() == ButtonType.OK) {
                showMessage(primaryStage);
                alert.close();
            } else if (option.get() == ButtonType.CANCEL) {
                alert.close();
            }
        });
    }

    private GridPane createRegisterWindowPane(Stage primaryStage, double width, double height) {
        GridPane gp = new GridPane();

        gp.setMinSize(width, height);

        gp.setAlignment(Pos.CENTER);
        gp.setPadding(new Insets(40));
        gp.setHgap(10);
        gp.setVgap(10);
//        gp.setGridLinesVisible(true);

        // Labels
        Label lblHeader = new BigLabel("Register ");
        lblHeader.setAlignment(Pos.CENTER);
        Label lblMessage = new SmallLabel("");
        lblMessage.setAlignment(Pos.CENTER);

        Label lblLogin = new SmallLabel("Login: ");
        Label lblPlayerName = new SmallLabel("Player name: ");
        Label lblPassword = new SmallLabel("Password: ");
        Label lblRptPassword = new SmallLabel("Repeat password: ");


        // Text fields
        TextField txtLogin = new RegularTextField();
        TextField txtPlayerName = new RegularTextField();

        final PasswordField txtPassword = new RegularPasswordField();
        final PasswordField txtRptPassword = new RegularPasswordField();
        TextField txtPasswordVisible = new RegularTextField();
        TextField txtRptPasswordVisible = new RegularTextField();


        // Checkbox for show passwords
        CheckBox chPasswordVisible = new RegularCheckBox("Show passwords.");
        chPasswordVisible.setFocusTraversable(false);
        chPasswordVisible.setAlignment(Pos.CENTER_LEFT);


        // Register button
        Button btnRegister = new ActionButton("Register");

        // Dummy for center the panes
        Label lblAbout = new SmallLabel("");

        // Link for go to Login window
        Hyperlink lnkLogin = new RegularLink("Log in");
        lnkLogin.setOnAction(e -> loadLoginWindow(primaryStage));


        // Checkbox for visible/invisible password fields
        txtPasswordVisible.managedProperty().bind(chPasswordVisible.selectedProperty());
        txtPasswordVisible.visibleProperty().bind(chPasswordVisible.selectedProperty());
        txtPassword.managedProperty().bind(chPasswordVisible.selectedProperty().not());
        txtPassword.visibleProperty().bind(chPasswordVisible.selectedProperty().not());
        txtPasswordVisible.textProperty().bindBidirectional(txtPassword.textProperty());

        txtRptPasswordVisible.managedProperty().bind(chPasswordVisible.selectedProperty());
        txtRptPasswordVisible.visibleProperty().bind(chPasswordVisible.selectedProperty());
        txtRptPassword.managedProperty().bind(chPasswordVisible.selectedProperty().not());
        txtRptPassword.visibleProperty().bind(chPasswordVisible.selectedProperty().not());
        txtRptPasswordVisible.textProperty().bindBidirectional(txtRptPassword.textProperty());


        // Action events
        btnRegister.setOnAction(e -> {
            String password = txtPassword.getText();
            String rptPassword = txtRptPassword.getText();

            if(password.equals(rptPassword)
                    && password.length() > AuthConfig.MIN_PASSWORD_SIZE
                    && password.length() < AuthConfig.MAX_PASSWORD_SIZE){

//                lblMessage.setText("Successfully register!");
//                lblMessage.setTextFill(Color.GREEN);
                lightTextBoxes(Color.TRANSPARENT, txtPassword, txtPasswordVisible, txtRptPassword, txtRptPasswordVisible);

//                loadLoginWindow(primaryStage);
                showConfirmation(primaryStage);
            }
            else {
                lblMessage.setText("Wrong passwords! Try again.");
                lblMessage.setTextFill(Color.RED);

                lightTextBoxes(Color.RED, txtPassword, txtPasswordVisible, txtRptPassword, txtRptPasswordVisible);
                txtRptPassword.setText("");
            }

            String login = txtLogin.getText();
            String playerName = txtPlayerName.getText();

            if (login.length() < AuthConfig.MIN_LOGIN_SIZE ||
                    login.length() > AuthConfig.MAX_LOGIN_SIZE ||
                    playerName.length() < AuthConfig.MIN_LOGIN_SIZE ||
                    playerName.length() > AuthConfig.MAX_LOGIN_SIZE) {

                lblMessage.setText("Wrong login or player name.");
                lblMessage.setTextFill(Color.RED);
                lightTextBoxes(Color.RED, txtLogin);
                lightTextBoxes(Color.RED, txtPlayerName);
            } else {
                lightTextBoxes(Color.TRANSPARENT, txtLogin);
                lightTextBoxes(Color.TRANSPARENT, txtPlayerName);
            }
        });

        gp.setOnMouseClicked(e -> {
            lightTextBoxes(Color.TRANSPARENT, txtLogin, txtPlayerName);
            lightTextBoxes(Color.TRANSPARENT, txtPassword, txtPasswordVisible, txtRptPassword, txtRptPasswordVisible);
            lblMessage.setText("");
        });


        gp.add(lblHeader, 1, 0);
        gp.add(lblMessage, 1, 1);
        gp.add(lblLogin, 0, 2);
        gp.add(txtLogin, 1, 2);
        gp.add(lblPlayerName, 0, 3);
        gp.add(txtPlayerName, 1, 3);
        gp.add(lblPassword, 0, 4);
        gp.add(txtPassword, 1, 4);
        gp.add(txtPasswordVisible, 1, 4);
        gp.add(chPasswordVisible, 1, 5);
        gp.add(lblRptPassword, 0, 6);
        gp.add(txtRptPassword, 1, 6);
        gp.add(txtRptPasswordVisible, 1, 6);
        gp.add(new HBox(btnRegister, lnkLogin), 1, 7);
        gp.add(lblAbout, 2, 7);


        ColumnConstraints leftCol = new ColumnConstraints(AuthConfig.BIG_LABEL_WIDTH);
        leftCol.setHalignment(HPos.RIGHT);
        ColumnConstraints centerCol = new ColumnConstraints(AuthConfig.BIG_LABEL_WIDTH);
        centerCol.setHalignment(HPos.CENTER);
        ColumnConstraints rightCol = new ColumnConstraints(AuthConfig.BIG_LABEL_WIDTH);
        rightCol.setHalignment(HPos.LEFT);

        return gp;
    }

    public RegisterWindow(Stage primaryStage) {
        super();
        this.getChildren().add(createRegisterWindowPane(primaryStage, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
    }
}
