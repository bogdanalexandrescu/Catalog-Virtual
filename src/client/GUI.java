package client;


import client.utilities.*;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


/**
 * Created by teo on 17.04.2017.
 */
public class GUI {

    private BorderPane bp ;
    private ChoiceBox cb;
    private MessageProcessor processor;
    private TextField txtUserName;
    private PasswordField pf;

    public GUI(MessageProcessor messageProcessor) {
        processor = messageProcessor;
    }

    public BorderPane getBp() {
        return bp;
    }


    public ChoiceBox getCb() {
        return cb;
    }

    public MessageProcessor getProcessor() {
        return processor;
    }

    public void setProcessor(MessageProcessor processor) {
        this.processor = processor;
    }

    public TextField getTxtUserName() {
        return txtUserName;
    }

    public void setTxtUserName(String txtUserName) {
        this.txtUserName.setText(txtUserName);
    }

    public PasswordField getPf() {
        return pf;
    }

    public void setPf(String pf) {
        this.pf.setText(pf);
    }

    public void display(){

        Stage primaryStage = new Stage();
        primaryStage.setTitle("JavaFX 2 Login");

        bp = new BorderPane();
        bp.setPrefSize(900,600);




        loginScreen(bp);


        //Adding BorderPane to the scene and loading CSS
        Scene scene = new Scene(bp);
        scene.getStylesheets().add(getClass().getClassLoader().getResource("client/resources/gui.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setTitle("Catalog Virtual");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void loginScreen(BorderPane bp){


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

        txtUserName = new TextField();
        txtUserName.setPadding(new Insets(0,0,0,0));
        txtUserName.setFocusTraversable(false);
        txtUserName.setFont(Font.font("Courier New", FontWeight.NORMAL, 15));

        Label textPassword = new Label("Password");
        textPassword.setPadding(new Insets(10,0,0,0));
        textPassword.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        textPassword.setTextFill(Color.web("663F15"));

        pf = new PasswordField();
        pf.setPadding(new Insets(0,0,0,0));
        pf.setFocusTraversable(false);
        pf.setFont(Font.font("Courier New", FontWeight.NORMAL, 15));

        Label textJob = new Label("Job");
        textJob.setPadding(new Insets(10,0,0,0));
        textJob.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        textJob.setTextFill(Color.web("663F15"));

        cb = new ChoiceBox(FXCollections.observableArrayList(
                "Teacher", "Headmaster")
        );
        cb.setPadding(new Insets(0,0,0,0));


        HBox btnLoginPane = new HBox();
        btnLoginPane.setPadding(new Insets(100,0,0,0));

        Button btnLogin = new Button("Login");
        btnLogin.setTextFill(Color.web("ffffff"));
        btnLogin.setPrefWidth(250);
        btnLogin.setFont(Font.font("Courier New", FontWeight.BOLD, 16));


        btnLogin.setOnAction(new ButtonLoginListener(this));


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

        bp.setCenter(hbox);

    }

    public void teacherMode(BorderPane bp,int presentation, String title){
        HBox hbox = new HBox();
        VBox vbox1;
        if(presentation == 1){
            vbox1 = teacherPresentation();
        }
        else vbox1 = headmasterPresentation();



        VBox vbox2 = new VBox();
        vbox2.setPrefSize(620,550);

        Label textTitle = new Label(title);
        textTitle.setPadding(new Insets(40,0,0,0));
        textTitle.setWrapText(true);
        textTitle.setFont(Font.font("Courier New", FontWeight.BOLD, 35));
        textTitle.setTextFill(Color.web("008FE9"));

        GridPane tableBar = new GridPane();
        tableBar.setPadding(new Insets(50,0,0,55));
        if(presentation == 1){
            tableBar.add(rankingTopInitialization("#008FE9","Name","Mark","Absence",515),0,0);
        }
        if(presentation == 2 || presentation == 3){
            tableBar.add(rankingTopInitialization("#008FE9","Name","Edit","Delete",515),0,0);
        }






        GridPane vboxTable = new GridPane();
        if(presentation == 1){
            vboxTable.setPrefSize(400,390);
        }
        else{
            vboxTable.setPrefSize(400,300);
        }

        vboxTable.setPadding(new Insets(0,0,0,55));




        VBox menu = new VBox();
        menu.setSpacing(5);
        ScrollPane menusp = new ScrollPane();
        menusp.setPrefWidth(515);
        menusp.setContent(menu);






        menu.setSpacing(1);


        for(int i = 0; i < 21; i++){
            if(presentation == 1){
                rankInitialization(menu,"#C1DCFE", "Radu Denis","Add","Add");
            }
            else{
                rankInitialization(menu,"#C1DCFE", "Radu Denis","Edit","Delete");
            }

        }





        vboxTable.add(menusp,0,0);

        vbox2.setAlignment(Pos.CENTER);
        vbox2.setAlignment(Pos.TOP_CENTER);
        vbox2.getChildren().add(textTitle);
        vbox2.getChildren().add(tableBar);

        vbox2.getChildren().add(vboxTable);

        if(presentation != 1){
            GridPane vboxAdd = new GridPane();
            vboxAdd.setPrefSize(400,30);
            vboxAdd.setPadding(new Insets(30,0,0,55));
            vboxAdd.add(rankingTopInitialization("#008FE9","Options","","",365),0,0);

            GridPane vboxAddGridPane = new GridPane();
            vboxAddGridPane.setPrefSize(400,60);
            vboxAddGridPane.setPadding(new Insets(0,0,0,55));

            VBox boxAdd = new VBox();
            //boxAdd.setPrefSize(400,30);
            if(title.equals("Students")){
                addOption(boxAdd,"#C1DCFE", "Add Student","Add");
            }
            if(title.equals("Teachers")){
                addOption(boxAdd,"#C1DCFE", "Add Teacher","Add");
            }
            if(title.equals("Subjects")){
                addOption(boxAdd,"#C1DCFE", "Add Subject","Add");
            }

            GridPane backButtonPane = new GridPane();
            backButtonPane.setPadding(new Insets(0,0,0,50));

            Button buttonBack = new Button("Back");
            buttonBack.setFont(Font.font("Courier New", FontWeight.BOLD, 18));
            buttonBack.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;-fx-border-color: linear-gradient(orange  , orangered ) ;\n" +
                    "  -fx-border-width: 1.5px ; -fx-border-radius: 5;\n" +
                    "    -fx-padding: 5 5 5 5;\n" +
                    "    -fx-background-radius: 5;");
            buttonBack.setPrefSize(100,25);
            buttonBack.setOnAction(new ButtonBackListener(this));

            backButtonPane.add(buttonBack,0,0);




            vboxAddGridPane.add(boxAdd,0,0);
            vboxAddGridPane.add(backButtonPane,1,0);
            vbox2.getChildren().add(vboxAdd);
            vbox2.getChildren().add(vboxAddGridPane);
        }







        vbox2.setId("vbox2");


        hbox.getChildren().add(vbox1);
        hbox.getChildren().add(vbox2);

        bp.setCenter(hbox);
    }

    public void headmasterMode(BorderPane bp){
        HBox hbox = new HBox();
        VBox vbox1 = headmasterPresentation();



        VBox vbox2 = new VBox();
        vbox2.setPrefSize(620,550);

        Label textTitle = new Label("Menu");
        textTitle.setPadding(new Insets(40,0,0,0));
        textTitle.setWrapText(true);
        textTitle.setFont(Font.font("Courier New", FontWeight.BOLD, 35));
        textTitle.setTextFill(Color.web("008FE9"));

        GridPane menu1Pane = new GridPane();
        menu1Pane.setPadding(new Insets(50,18,0,18));


        GridPane menu1 = new GridPane();
        menu1.setHgap(15);
        menu1.setVgap(5);
        menu1.setPrefSize(600,245);


        GridPane addSubjectMenu = new GridPane();
        addSubjectMenu.setVgap(5);
        addSubjectMenu.setPadding(new Insets(0,0,0,0));
        addSubjectMenu.setPrefSize(173,235);

        HBox btnAddSubjectPane = new HBox();
        btnAddSubjectPane.setPadding(new Insets(0,0,0,50));

        Button addSubjectButton = buttonInitialization(80,20);
        addSubjectButton.setOnAction(new ButtonEditSubjectListener(this));

        btnAddSubjectPane.getChildren().add(addSubjectButton);

        addSubjectMenu.add(titleBarInitialization("Subjects",173,25),0,0);
        addSubjectMenu.add(messageInitialization("Subjects are the parts into which learning can be divided. At school, each lesson usually covers one subject only.",173,160),0,2);
        addSubjectMenu.add(btnAddSubjectPane,0,3);

        GridPane addTeacherMenu = new GridPane();
        addTeacherMenu.setVgap(5);
        addTeacherMenu.setPadding(new Insets(0,0,0,0));
        addTeacherMenu.setPrefSize(173,235);

        HBox btnAddTeacherPane = new HBox();
        btnAddTeacherPane.setPadding(new Insets(0,0,0,50));

        Button addTeacherButton = buttonInitialization(80,20);
        addTeacherButton.setOnAction(new ButtonEditTeacherListener(this));

        btnAddTeacherPane.getChildren().add(addTeacherButton);

        addTeacherMenu.add(titleBarInitialization("Teachers",173,25),0,0);
        addTeacherMenu.add(messageInitialization("A teacher is a person who helps others to acquire knowledge, competences or values.",173,160),0,2);
        addTeacherMenu.add(btnAddTeacherPane,0,3);


        GridPane addStudentMenu = new GridPane();
        addStudentMenu.setVgap(5);
        addStudentMenu.setPadding(new Insets(0,0,0,0));
        addStudentMenu.setPrefSize(173,235);

        HBox btnAddStudentPane = new HBox();
        btnAddStudentPane.setPadding(new Insets(0,0,0,50));

        Button addStudentButton = buttonInitialization(80,20);
        addStudentButton.setOnAction(new ButtonEditStudentListener(this));

        btnAddStudentPane.getChildren().add(addStudentButton);

        addStudentMenu.add(titleBarInitialization("Students",173,25),0,0);
        addStudentMenu.add(messageInitialization("A student is a person formally engaged in learning, especially one enrolled in a school or college.",173,160),0,2);
        addStudentMenu.add(btnAddStudentPane,0,3);


        menu1.add(addSubjectMenu,1,1);
        menu1.add(addTeacherMenu,2,1);
        menu1.add(addStudentMenu,3,1);


        menu1Pane.add(menu1,0,0);

        GridPane menu2Pane = new GridPane();
        menu2Pane.setPadding(new Insets(10,18,0,18));


        GridPane menu2 = new GridPane();
        menu2.setHgap(15);
        menu2.setVgap(5);
        menu2.setPrefSize(600,195);

        GridPane importExportStudentsMenu = new GridPane();
        importExportStudentsMenu.setVgap(5);
        importExportStudentsMenu.setPadding(new Insets(0,0,0,0));
        importExportStudentsMenu.setPrefSize(267,185);

        HBox btnImportExportStudentsPane = new HBox();
        btnImportExportStudentsPane.setPadding(new Insets(0,0,0,80));

        Button importExportButton = buttonInitialization(100,20);
        importExportButton.setOnAction(new ButtonImportExportListener(this));

        btnImportExportStudentsPane.getChildren().add(importExportButton);

        importExportStudentsMenu.add(titleBarInitialization("Import/Export Students",267,25),0,0);
        importExportStudentsMenu.add(messageInitialization("The import and export of students is the automated input and output of data sets between application and your computer.",267,110),0,2);
        importExportStudentsMenu.add(btnImportExportStudentsPane,0,3);

        GridPane ratioStudentsMenu = new GridPane();
        ratioStudentsMenu.setVgap(5);
        ratioStudentsMenu.setPadding(new Insets(0,0,0,0));
        ratioStudentsMenu.setPrefSize(267,185);

        HBox btnRatioPane = new HBox();
        btnRatioPane.setPadding(new Insets(0,0,0,80));

        Button ratioButton = buttonInitialization(100,20);
        ratioButton.setOnAction(new ButtonStudentSituationListener(this));

        btnRatioPane.getChildren().add(ratioButton);

        ratioStudentsMenu.add(titleBarInitialization("Student's Situation",267,25),0,0);
        ratioStudentsMenu.add(messageInitialization("The student's situation is a document where you can see all of the student's notes and absences on all subjects.",267,110),0,2);
        ratioStudentsMenu.add(btnRatioPane,0,3);


        menu2.add(importExportStudentsMenu,1,1);
        menu2.add(ratioStudentsMenu,2,1);

        menu2Pane.add(menu2,0,0);




        vbox2.setAlignment(Pos.CENTER);
        vbox2.setAlignment(Pos.TOP_CENTER);
        vbox2.getChildren().add(textTitle);
        vbox2.getChildren().add(menu1Pane);
        vbox2.getChildren().add(menu2Pane);

        vbox1.setId("vbox1");
        vbox2.setId("vbox2");
        addStudentMenu.setId("menu");
        addSubjectMenu.setId("menu");
        addTeacherMenu.setId("menu");
        importExportStudentsMenu.setId("menu");
        ratioStudentsMenu.setId("menu");



        hbox.getChildren().add(vbox1);
        hbox.getChildren().add(vbox2);

        bp.setCenter(hbox);
    }




    private GridPane titleBarInitialization(String title, double width, double height){

        GridPane messagePane = new GridPane();
        messagePane.setPrefSize(width,height);

        messagePane.setHgap(10);
        messagePane.setVgap(5);


        Label textRanking = new Label(title);
        textRanking.setFocusTraversable(false);
        textRanking.setStyle("-fx-text-fill:  #ffffff;");
        textRanking.setFont(Font.font("Courier New", FontWeight.BOLD, 19));

        messagePane.add(textRanking,1,0);

        messagePane.setId("vbox1");

        return messagePane;

    }

    private GridPane messageInitialization(String message, double width, double height){

        GridPane messagePane = new GridPane();
        messagePane.setPrefSize(width,height);

        messagePane.setHgap(10);
        messagePane.setVgap(5);


        Label textRanking = new Label(message);
        textRanking.setWrapText(true);
        textRanking.setFocusTraversable(false);
        textRanking.setStyle("-fx-text-fill:  #008FE9;");
        textRanking.setFont(Font.font("Courier New", FontWeight.NORMAL, 15));


        messagePane.add(textRanking,1,0);

        return messagePane;

    }

    private Button buttonInitialization(double width,double height){

        Button buttonAbcecnce = new Button("Continue");
        buttonAbcecnce.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
        buttonAbcecnce.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
        buttonAbcecnce.setPrefSize(width,height);

        return buttonAbcecnce;
    }

    private GridPane rankingTopInitialization(String color,String string,String stringOne, String stringTwo,double width){

            GridPane messagePane = new GridPane();
            messagePane.setPrefSize(width,30);

            messagePane.setHgap(10);
            messagePane.setVgap(5);
            messagePane.setStyle("-fx-background-color: " + color + ";");

            Label textRanking = new Label("Nr");
            textRanking.setFocusTraversable(false);
            textRanking.setStyle("-fx-text-fill:  #ffffff;");
            textRanking.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

            Label textName = new Label(string);
            textName.setFocusTraversable(false);
            textName.setStyle("-fx-text-fill:  #ffffff;");
            textName.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

            Label textLevel = new Label(stringOne);
            textLevel.setFocusTraversable(false);
            textLevel.setStyle("-fx-text-fill:  #ffffff;");
            textLevel.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

            Label textWins = new Label(stringTwo);
            textWins.setFocusTraversable(false);
            textWins.setStyle("-fx-text-fill:  #ffffff;");
            textWins.setFont(Font.font("Courier New", FontWeight.BOLD, 16));


            messagePane.add(textRanking,1,0);
            messagePane.add(textName,3,0);
            if(width == 515){
                messagePane.add(textLevel,27,0);
                messagePane.add(textWins,32,0);
            }







            return messagePane;

    }

    private void rankInitialization(VBox discution, String color,String name,String buttonOne, String buttonTwo){

        GridPane messagePane = new GridPane();
        messagePane.setPrefSize(500,40);
        messagePane.setHgap(10);
        messagePane.setVgap(5);
        messagePane.setStyle("-fx-background-color: " + color + ";");

        Label textRanking = new Label("1");
        textRanking.setPadding(new Insets(5,0,0,0));
        textRanking.setFocusTraversable(false);
        textRanking.setStyle("-fx-text-fill:  #663F15;");
        textRanking.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

        Label textName = new Label(name);
        textName.setPadding(new Insets(5,0,0,0));
        textName.setPrefWidth(262);
        textName.setFocusTraversable(false);
        textName.setStyle("-fx-text-fill:  #663F15;");
        textName.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

        GridPane buttonsPane = new GridPane();
        buttonsPane.setHgap(36);
        buttonsPane.setVgap(5);
        buttonsPane.setPadding(new Insets(8,0,0,0));


        Button buttonMark = new Button(buttonOne);
        buttonMark.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
        buttonMark.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
        buttonMark.setPrefSize(60,15);

        Button buttonAbcecnce = new Button(buttonTwo);
        buttonAbcecnce.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
        buttonAbcecnce.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
        buttonAbcecnce.setPrefSize(60,15);

        buttonsPane.add(buttonMark,0,0);
        buttonsPane.add(buttonAbcecnce,1,0);




        messagePane.add(textRanking,1,0);
        messagePane.add(textName,4,0);
        messagePane.add(buttonsPane,5,0);



        discution.getChildren().add(messagePane);

    }

    private void addOption(VBox discution, String color,String option,String buttonOne){

        GridPane messagePane = new GridPane();
        messagePane.setPrefSize(365,40);
        messagePane.setHgap(10);
        messagePane.setVgap(5);
        messagePane.setStyle("-fx-background-color: " + color + ";");

        Label textRanking = new Label("1");
        textRanking.setPadding(new Insets(5,0,0,0));
        textRanking.setFocusTraversable(false);
        textRanking.setStyle("-fx-text-fill:  #663F15;");
        textRanking.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

        Label textOption = new Label(option);
        textOption.setPadding(new Insets(5,0,0,20));
        textOption.setFocusTraversable(false);
        textOption.setStyle("-fx-text-fill:  #663F15;");
        textOption.setFont(Font.font("Courier New", FontWeight.BOLD, 16));



        GridPane buttonsPane = new GridPane();
        buttonsPane.setPadding(new Insets(8,0,0,105));



        Button buttonAbcecnce = new Button(buttonOne);
        buttonAbcecnce.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
        buttonAbcecnce.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
        buttonAbcecnce.setPrefSize(60,15);

        buttonsPane.add(buttonAbcecnce,0,0);




        messagePane.add(textRanking,1,0);
        messagePane.add(textOption,2,0);
        messagePane.add(buttonsPane,3,0);



        discution.getChildren().add(messagePane);

    }

    private VBox teacherPresentation(){
        VBox vbox1 = new VBox();
        vbox1.setPrefSize(300,620);


        VBox imagePane1 = new VBox();
        imagePane1.setPadding(new Insets(30,0,0,70));
        ImageView logo1 = new ImageView(new Image(getClass().getResourceAsStream("/client/resources/teacher.png"),150,150,true,true));

        imagePane1.getChildren().add(logo1);



        Label textName = new Label("Teacher:");
        textName.setPadding(new Insets(40,0,0,0));
        textName.setWrapText(true);
        textName.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textName.setTextFill(Color.web("FFFFFF"));

        Label textNameTeacher = new Label("Radu Denis");
        textNameTeacher.setPadding(new Insets(0,0,0,0));
        textNameTeacher.setWrapText(true);
        textNameTeacher.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        textNameTeacher.setTextFill(Color.web("FFFFFF"));

        Label textSubject = new Label("Subject:");
        textSubject.setPadding(new Insets(20,0,0,0));
        textSubject.setWrapText(true);
        textSubject.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textSubject.setTextFill(Color.web("FFFFFF"));

        Label textNameSubject = new Label("C#");
        textNameSubject.setPadding(new Insets(0,0,0,0));
        textNameSubject.setWrapText(true);
        textNameSubject.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        textNameSubject.setTextFill(Color.web("FFFFFF"));

        Label textStudents = new Label("Students:");
        textStudents.setPadding(new Insets(20,0,0,0));
        textStudents.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textStudents.setTextFill(Color.web("FFFFFF"));

        Label textNumberStudents = new Label("24");
        textNumberStudents.setPadding(new Insets(0,0,0,0));
        textNumberStudents.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textNumberStudents.setTextFill(Color.web("FFFFFF"));

        HBox btnLogoutPane = new HBox();
        btnLogoutPane.setPadding(new Insets(100,0,0,70));

        Button btnLogout = new Button("Log Out");
        btnLogout.setTextFill(Color.web("ffffff"));
        btnLogout.setPrefWidth(150);
        btnLogout.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        btnLogout.setOnAction(new ButtonLogoutListener(this));


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

        btnLogout.setId("btnLogout");
        vbox1.setId("vbox1");

        return vbox1;
    }

    private VBox headmasterPresentation(){
        VBox vbox1 = new VBox();
        vbox1.setPrefSize(300,620);


        VBox imagePane1 = new VBox();
        imagePane1.setPadding(new Insets(30,0,0,70));
        ImageView logo1 = new ImageView(new Image(getClass().getResourceAsStream("/client/resources/headmaster.png"),150,150,true,true));

        imagePane1.getChildren().add(logo1);



        Label textHeadmaster = new Label("Headmaster:");
        textHeadmaster.setPadding(new Insets(40,0,0,0));
        textHeadmaster.setWrapText(true);
        textHeadmaster.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textHeadmaster.setTextFill(Color.web("FFFFFF"));

        Label textNameHeadmaster = new Label("Stefanoiu Alexandru-Petru");
        textNameHeadmaster.setPadding(new Insets(0,0,0,0));
        textNameHeadmaster.setWrapText(true);
        textNameHeadmaster.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        textNameHeadmaster.setTextFill(Color.web("FFFFFF"));

        Label textTeacher = new Label("Teachers:");
        textTeacher.setPadding(new Insets(20,0,0,0));
        textTeacher.setWrapText(true);
        textTeacher.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textTeacher.setTextFill(Color.web("FFFFFF"));

        Label textNumberTeachers = new Label("50");
        textNumberTeachers.setPadding(new Insets(0,0,0,0));
        textNumberTeachers.setWrapText(true);
        textNumberTeachers.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textNumberTeachers.setTextFill(Color.web("FFFFFF"));

        Label textStudents = new Label("Students:");
        textStudents.setPadding(new Insets(20,0,0,0));
        textStudents.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textStudents.setTextFill(Color.web("FFFFFF"));

        Label textNumberStudents = new Label("324");
        textNumberStudents.setPadding(new Insets(0,0,0,0));
        textNumberStudents.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textNumberStudents.setTextFill(Color.web("FFFFFF"));

        Label textSubjects = new Label("Subjects:");
        textSubjects.setPadding(new Insets(20,0,0,0));
        textSubjects.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textSubjects.setTextFill(Color.web("FFFFFF"));

        Label textNumberSubjects = new Label("16");
        textNumberSubjects.setPadding(new Insets(0,0,0,0));
        textNumberSubjects.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
        textNumberSubjects.setTextFill(Color.web("FFFFFF"));

        HBox btnLogoutPane = new HBox();
        btnLogoutPane.setPadding(new Insets(50,0,0,70));

        Button btnLogout = new Button("Log Out");
        btnLogout.setTextFill(Color.web("ffffff"));
        btnLogout.setPrefWidth(150);
        btnLogout.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
        btnLogout.setOnAction(new ButtonLogoutListener(this));


        btnLogoutPane.getChildren().add(btnLogout);

        vbox1.setAlignment(Pos.TOP_CENTER);
        vbox1.getChildren().add(imagePane1);
        vbox1.getChildren().add(textHeadmaster);
        vbox1.getChildren().add(textNameHeadmaster);
        vbox1.getChildren().add(textTeacher);
        vbox1.getChildren().add(textNumberTeachers);
        vbox1.getChildren().add(textStudents);
        vbox1.getChildren().add(textNumberStudents);
        vbox1.getChildren().add(textSubjects);
        vbox1.getChildren().add(textNumberSubjects);
        vbox1.getChildren().add(btnLogoutPane);

        btnLogout.setId("btnLogout");
        vbox1.setId("vbox1");
        return vbox1;
    }





}
