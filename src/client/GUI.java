package client;


import client.utilities.ButtonLoginListener;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 * Created by teo on 17.04.2017.
 */
public class GUI extends Application{

    @Override
    public void start(Stage primaryStage) throws Exception{

        primaryStage.setTitle("JavaFX 2 Login");

        BorderPane bp = new BorderPane();
        bp.setPrefSize(900,600);




        HBox hbox = teacherMode();
        bp.setCenter(hbox);

        //Adding BorderPane to the scene and loading CSS
        Scene scene = new Scene(bp);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("client/resources/gui.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Catalog Virtual");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private HBox loginScreen(){


        HBox hbox = new HBox();

        VBox vbox1 = new VBox();
        vbox1.setPrefSize(460,620);

        VBox imagePane1 = new VBox();
        imagePane1.setPadding(new Insets(100,0,0,30));
        ImageView logo1 = new ImageView(new Image(getClass().getResourceAsStream("/client/resources/try.png"),400,400,true,true));

        imagePane1.getChildren().add(logo1);

        vbox1.getChildren().add(imagePane1);



        VBox vbox2 = new VBox();
        vbox2.setPrefSize(460,550);
        vbox2.setPadding(new Insets(0,0,0,110));

        VBox imagePane = new VBox();
        imagePane.setPadding(new Insets(0,0,0,-30));
        ImageView logo = new ImageView(new Image(getClass().getResourceAsStream("/client/resources/catalog_logo.png"),300,300,true,true));

        imagePane.getChildren().add(logo);


        Label textUsername = new Label("Username");
        textUsername.setPadding(new Insets(10,0,0,0));
        textUsername.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        textUsername.setTextFill(Color.web("663F15"));

        TextField txtUserName = new TextField();
        txtUserName.setPadding(new Insets(0,0,0,0));
        txtUserName.setFocusTraversable(false);
        txtUserName.setFont(Font.font("Courier New", FontWeight.NORMAL, 15));

        Label textPassword = new Label("Password");
        textPassword.setPadding(new Insets(10,0,0,0));
        textPassword.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        textPassword.setTextFill(Color.web("663F15"));

        PasswordField pf = new PasswordField();
        pf.setPadding(new Insets(0,0,0,0));
        pf.setFocusTraversable(false);
        pf.setFont(Font.font("Courier New", FontWeight.NORMAL, 15));

        Label textJob = new Label("Job");
        textJob.setPadding(new Insets(10,0,0,0));
        textJob.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        textJob.setTextFill(Color.web("663F15"));

        ChoiceBox cb = new ChoiceBox(FXCollections.observableArrayList(
                "Teacher", "Headmaster")
        );
        cb.setPadding(new Insets(0,0,0,0));


        HBox btnLoginPane = new HBox();
        btnLoginPane.setPadding(new Insets(100,0,0,0));

        Button btnLogin = new Button("Login");
        btnLogin.setTextFill(Color.web("ffffff"));
        btnLogin.setPrefWidth(250);
        btnLogin.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        btnLogin.setOnAction(new ButtonLoginListener(hbox));


        btnLoginPane.getChildren().add(btnLogin);




        vbox2.getChildren().add(imagePane);
        vbox2.getChildren().add(textUsername);
        vbox2.getChildren().add(txtUserName);
        vbox2.getChildren().add(textPassword);
        vbox2.getChildren().add(pf);
        vbox2.getChildren().add(textJob);
        vbox2.getChildren().add(cb);
        vbox2.getChildren().add(btnLoginPane);



        hbox.getChildren().add(vbox1);
        hbox.getChildren().add(vbox2);


        vbox1.setId("vbox1");
        vbox2.setId("vbox2");
        txtUserName.setId("textField");
        pf.setId("textField");
        cb.setId("textField");
        textPassword.setId("text");
        textUsername.setId("text");
        textJob.setId("text");
        btnLogin.setId("btnEditProfile");

        return hbox;






    }

    private HBox teacherMode(){
        HBox hbox = new HBox();
        VBox vbox1 = new VBox();
        vbox1.setPrefSize(300,620);


        VBox imagePane1 = new VBox();
        imagePane1.setPadding(new Insets(100,0,0,30));
        ImageView logo1 = new ImageView(new Image(getClass().getResourceAsStream("/client/resources/teacher.png"),150,150,true,true));

        imagePane1.getChildren().add(logo1);



        Label textName = new Label("Teacher:");
        textName.setPadding(new Insets(0,0,0,0));
        textName.setWrapText(true);
        textName.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textName.setTextFill(Color.web("FFFFFF"));

        Label textNameTeacher = new Label("Radu Denis");
        textNameTeacher.setPadding(new Insets(0,0,0,0));
        textNameTeacher.setWrapText(true);
        textNameTeacher.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        textNameTeacher.setTextFill(Color.web("FFFFFF"));

        Label textSubject = new Label("Subject:");
        textSubject.setPadding(new Insets(10,0,0,0));
        textSubject.setWrapText(true);
        textSubject.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textSubject.setTextFill(Color.web("FFFFFF"));

        Label textNameSubject = new Label("C#");
        textNameSubject.setPadding(new Insets(10,0,0,0));
        textNameSubject.setWrapText(true);
        textNameSubject.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        textNameSubject.setTextFill(Color.web("FFFFFF"));

        Label textStudents = new Label("Students:");
        textStudents.setPadding(new Insets(10,0,0,0));
        textStudents.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textStudents.setTextFill(Color.web("FFFFFF"));

        Label textNumberStudents = new Label("24");
        textNumberStudents.setPadding(new Insets(10,0,0,0));
        textNumberStudents.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textNumberStudents.setTextFill(Color.web("FFFFFF"));

        HBox btnLogoutPane = new HBox();
        btnLogoutPane.setPadding(new Insets(0,0,0,0));

        Button btnLogout = new Button("Log Out");
        btnLogout.setTextFill(Color.web("ffffff"));
        btnLogout.setPrefWidth(150);
        btnLogout.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        btnLogout.setOnAction(new ButtonLoginListener(hbox));


        btnLogoutPane.getChildren().add(btnLogout);

        vbox1.setAlignment(Pos.TOP_CENTER);
        vbox1.getChildren().add(imagePane1);
        vbox1.getChildren().add(textName);
        vbox1.getChildren().add(textNameTeacher);
        vbox1.getChildren().add(textSubject);
        vbox1.getChildren().add(textNameSubject);
        vbox1.getChildren().add(textStudents);
        vbox1.getChildren().add(textNumberStudents);
        vbox1.getChildren().add(btnLogoutPane);


        VBox vbox2 = new VBox();
        vbox2.setPrefSize(620,550);



        vbox1.setId("vbox1");
        vbox2.setId("vbox2");
        btnLogout.setId("btnLogout");

        hbox.getChildren().add(vbox1);
        hbox.getChildren().add(vbox2);






        return hbox;
    }


}
