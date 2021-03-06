package javafxapp.authwindows.registerwindow;

import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafxapp.api.dao.AuthDataApiDao;
import javafxapp.api.dto.RegisterRequestDto;
import javafxapp.api.dto.RegisterResponseDto;
import javafxapp.api.security.ApiPasswordEncoder;
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

import java.io.*;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.requireNonNull;

public class RegisterWindow extends Parent {
    private final AuthDataApiDao authDataApiDao = new AuthDataApiDao();
    private final ApiPasswordEncoder apiPasswordEncoder = new ApiPasswordEncoder();

    private final String defaultAvatarUrl = "file://" +
            requireNonNull(this.getClass().getClassLoader().getResource("images")).getPath() +
            "/" + AuthConfig.DEFAULT_AVATAR_NAME;

    private final String defaultAvatarPath = requireNonNull(this.getClass().getClassLoader().getResource("images")).getPath() +
            "/" + AuthConfig.DEFAULT_AVATAR_NAME;


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

    private void showMessage(Stage primaryStage, RegisterRequestDto registerRequestDto) {
        RegisterResponseDto registerResponseDto = authDataApiDao.register(registerRequestDto);

        if (registerResponseDto.isRegistered()) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Success");
            alert.setHeaderText(String.format("%s\n\nLog in to your account, please.", registerResponseDto.getMessage()));
            alert.setResizable(false);

            alert.setOnCloseRequest(e -> loadLoginWindow(primaryStage));

            Optional<ButtonType> option = alert.showAndWait();
            option.ifPresent(op ->alert.close());
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(String.format("Failed registration, try again.\n\nMessage: %s", registerResponseDto.getMessage()));
            alert.setResizable(false);

//            alert.setOnCloseRequest(e -> loadLoginWindow(primaryStage));

            Optional<ButtonType> option = alert.showAndWait();
            option.ifPresent(op -> alert.close());

        }

    }

    private void showConfirmation(Stage primaryStage, RegisterRequestDto registerRequestDto) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Confirm Registration?");
        alert.setResizable(false);

        Optional<ButtonType> option = alert.showAndWait();
        option.ifPresent(op -> {
            if (option.get() == ButtonType.OK) {
                showMessage(primaryStage, registerRequestDto);
                alert.close();
            } else if (option.get() == ButtonType.CANCEL) {
                alert.close();
            }
        });
    }

    private File loadImage(File oldImageFile, Stage primaryStage) throws IllegalArgumentException, FileNotFoundException {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open an avatar image.");

        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));

        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("All Images", "*.jpg", "*.jpeg", "*.png"),
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("JPEG", "*.jpeg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );

        File newImageFile = fileChooser.showOpenDialog(primaryStage);
        if (newImageFile != null) {
            return newImageFile;
        } else {
            return oldImageFile;
        }
    }

    private GridPane createRegisterWindowPane(Stage primaryStage, double width, double height) throws IOException {
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


        // Message for avatar image info
        Label lblMessageImg = new SmallLabel("");
        lblMessageImg.setMinWidth(AuthConfig.BIG_LABEL_WIDTH);
        lblMessageImg.setAlignment(Pos.CENTER);


        // Url for load default avatar image
        System.out.println("default avatar path: " + defaultAvatarPath);

        // Avatar image
        AtomicReference<File> currentAvatarFile = new AtomicReference<>(new File(defaultAvatarPath));
        final Image imgAvatar = new Image(new FileInputStream(currentAvatarFile.get()));


        // View for avatar image
        final ImageView imgAvatarRoot = new ImageView();
        imgAvatarRoot.setFitWidth(AuthConfig.BIG_IMAGE_WIDTH);
        imgAvatarRoot.setFitHeight(AuthConfig.BIG_IMAGE_WIDTH);
        imgAvatarRoot.setClip(new Circle((AuthConfig.BIG_IMAGE_WIDTH) / 2,
                (AuthConfig.BIG_IMAGE_WIDTH) / 2,
                (AuthConfig.BIG_IMAGE_WIDTH) / 2));
        imgAvatarRoot.setImage(imgAvatar);

        imgAvatarRoot.setOnMouseClicked(mouseEvent -> {
            try {
                File newAvatarFile = loadImage(currentAvatarFile.get(), primaryStage);

                imgAvatarRoot.setImage(new Image(new FileInputStream(newAvatarFile)));
                currentAvatarFile.set(newAvatarFile);

                lblMessageImg.setText("Image successfully loaded.");
                lblMessageImg.setTextFill(Color.GREEN);
            } catch (IllegalArgumentException | FileNotFoundException ex) {
                try {
                    imgAvatarRoot.setImage(new Image(new FileInputStream(new File(defaultAvatarPath))));
                } catch (FileNotFoundException ignore) {}
                lblMessageImg.setText("Can`t load image.");
                lblMessageImg.setTextFill(Color.RED);
            }
        });

        VBox vbAvatar = new VBox(imgAvatarRoot);
        vbAvatar.setAlignment(Pos.CENTER);

        // Message for login/password info
        Label lblMessageText = new SmallLabel("");
        lblMessageText.setAlignment(Pos.CENTER);

        // Labels for login/password view
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

                lightTextBoxes(Color.TRANSPARENT, txtPassword, txtPasswordVisible, txtRptPassword, txtRptPasswordVisible);

                byte[] avatarData = null;
                try {
                    avatarData = new FileInputStream(currentAvatarFile.get()).readAllBytes();
                } catch (IOException ignore) {}

                String userName = txtLogin.getText().strip();
                String playerName = txtPlayerName.getText().strip();
                String passwordHash = apiPasswordEncoder.sha256(txtPassword.getText().strip());

                RegisterRequestDto requestDto = new RegisterRequestDto(userName, playerName, passwordHash, avatarData);
                showConfirmation(primaryStage, requestDto);
            }
            else {
                lblMessageText.setText("Wrong passwords! Try again.");
                lblMessageText.setTextFill(Color.RED);

                lightTextBoxes(Color.RED, txtPassword, txtPasswordVisible, txtRptPassword, txtRptPasswordVisible);
                txtRptPassword.setText("");
            }

            String login = txtLogin.getText();
            String playerName = txtPlayerName.getText();

            if (login.length() < AuthConfig.MIN_LOGIN_SIZE ||
                    login.length() > AuthConfig.MAX_LOGIN_SIZE ||
                    playerName.length() < AuthConfig.MIN_LOGIN_SIZE ||
                    playerName.length() > AuthConfig.MAX_LOGIN_SIZE) {

                lblMessageText.setText("Wrong login or player name.");
                lblMessageText.setTextFill(Color.RED);
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
            lblMessageText.setText("");
            lblMessageImg.setText("");
        });


        gp.add(lblHeader, 1, 0);
        gp.add(lblMessageImg, 1, 1);
        gp.add(vbAvatar, 1, 2);

        gp.add(lblMessageText, 1, 3);
        gp.add(lblLogin, 0, 4);
        gp.add(txtLogin, 1, 4);
        gp.add(lblPlayerName, 0, 5);
        gp.add(txtPlayerName, 1, 5);
        gp.add(lblPassword, 0, 6);
        gp.add(txtPassword, 1, 6);
        gp.add(txtPasswordVisible, 1, 6);
        gp.add(lblRptPassword, 0, 7);
        gp.add(txtRptPassword, 1, 7);
        gp.add(txtRptPasswordVisible, 1, 7);
        gp.add(chPasswordVisible, 1, 8);
        gp.add(new HBox(btnRegister, lnkLogin), 1, 9);
        gp.add(lblAbout, 2, 9);


        ColumnConstraints leftCol = new ColumnConstraints(AuthConfig.BIG_LABEL_WIDTH);
        leftCol.setHalignment(HPos.RIGHT);
        ColumnConstraints centerCol = new ColumnConstraints(AuthConfig.BIG_LABEL_WIDTH);
        centerCol.setHalignment(HPos.CENTER);
        ColumnConstraints rightCol = new ColumnConstraints(AuthConfig.BIG_LABEL_WIDTH);
        rightCol.setHalignment(HPos.LEFT);

        return gp;
    }

    public RegisterWindow(Stage primaryStage) throws FileNotFoundException {
        super();
        try {
            this.getChildren().add(createRegisterWindowPane(primaryStage, Config.WINDOW_WIDTH, Config.WINDOW_HEIGHT));
        } catch (IOException e) {
            System.out.println(String.format("Can`t create register window, %s", e));
        }
    }
}
