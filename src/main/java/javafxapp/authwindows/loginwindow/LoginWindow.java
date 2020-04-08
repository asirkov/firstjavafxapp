package javafxapp.authwindows.loginwindow;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafxapp.api.dao.AuthDataApiDao;
import javafxapp.api.dto.AuthRequestDto;
import javafxapp.api.dto.AuthResponseDto;
import javafxapp.api.security.ApiPasswordEncoder;
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
import lombok.extern.slf4j.Slf4j;

import java.io.FileNotFoundException;

@Slf4j
public class LoginWindow extends Parent {
    private final AuthDataApiDao authDataApiDao = new AuthDataApiDao();
    private final ApiPasswordEncoder apiPasswordEncoder = new ApiPasswordEncoder();

    private void lightTextBoxes(Color color, TextField... textFields) {
        for (TextField textField : textFields) {
            textField.setBorder(new Border(new BorderStroke(color,
                    BorderStrokeStyle.SOLID, new CornerRadii(2), new BorderWidths(1.5d))));
        }
    }

    private void loadRegisterWindow(Stage primaryStage) {

        try {
            final RegisterWindow registerWindow = new RegisterWindow(primaryStage);
            Scene registerWindowScene = new Scene(registerWindow);

            primaryStage.setTitle("Register");
            primaryStage.setScene(registerWindowScene);
            primaryStage.setResizable(false);

            primaryStage.show();
        } catch (FileNotFoundException ex) {
            log.error("Can`t load register window, can`t get default avatar", ex);
        }
    }

    private void loadMainWindow(Stage primaryStage, AuthResponseDto authResponseDto) {
        Scene registerWindowScene = new Scene(new MainWindow(primaryStage, authResponseDto));

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
        txtLogin.setText("admin");
        final PasswordField txtPassword = new RegularPasswordField();
        txtPassword.setText("admin");
        final TextField txtPasswordVisible = new RegularTextField();
        txtPasswordVisible.setText("admin");

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
            String checkUser = txtLogin.getText().strip();
            String checkPw = txtPassword.getText().strip();

            AuthRequestDto authRequestDto = new AuthRequestDto(checkUser, apiPasswordEncoder.sha256(checkPw));
            System.out.println(String.format("IN loginWindow - try to auth with request %s", authRequestDto));

            AuthResponseDto authResponseDto = authDataApiDao.login(authRequestDto);

            if (authResponseDto.getAuthorized()) {
                loadMainWindow(primaryStage, authResponseDto);

            } else {
                lblMessage.setText(authResponseDto.getMessage());
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
