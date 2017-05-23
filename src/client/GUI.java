package client;

import java.util.ArrayList;

import client.utilities.*;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import server.Student;
import server.Subject;

/**
 * Created by teo on 17.04.2017.
 */
public class GUI {

	private BorderPane bp;
	private ChoiceBox cb;
	private VBox menu;
	private MessageProcessor processor;
	private TextField txtUserName;
	private PasswordField pf;
	private Label textNameTeacher;
	private Label textNameSubject;
	private Label textNumberStudents;
	private Label textNameHeadmaster;
	private Label textNumberTeachers;
	private Label textTotalNumberStudents;
	private Label textTotalNumberSubjects;
	private ArrayList<String> students;
	private Label textMessage;
	private ArrayList<Stage> stages;
	private ArrayList<TextField> mark;
	private ArrayList<TextField> data;
	private Label textTitle;
	private TextField addName;
	private PasswordField addPass;
	private TextField addSubject;
	private TextField pathExport;
	private TextField pathImport;
	private TextField studentExport;
	private ArrayList<String> subjects;
	private ArrayList<CheckBox> cbs;
	private TextField deleteMarkAbsence;
	private TextField nameStudent;

	public TextField getNameStudent() {
		return nameStudent;
	}

	public void setNameStudent(String nameStudent) {
		this.nameStudent.setText(nameStudent);
	}

	public TextField getDeleteMarkAbsence() {
		return deleteMarkAbsence;
	}

	public void setDeleteMarkAbsence(TextField deleteMarkAbsence) {
		this.deleteMarkAbsence = deleteMarkAbsence;
	}

	public ArrayList<CheckBox> getCbs() {
		return cbs;
	}

	public void setCbs(ArrayList<CheckBox> cbs) {
		this.cbs = cbs;
	}

	public ArrayList<String> getSubjects() {
		return subjects;
	}

	public void setSubjects(ArrayList<String> subjects) {
		this.subjects = subjects;
		System.out.println(subjects);
		System.out.print("1111111111");
		cbs = new ArrayList<CheckBox>();

		for(int i = 1 ; i < subjects.size(); i++){
			cbs.add(new CheckBox(subjects.get(i)));
		}
	}

	public TextField getPathImport() {
		return pathImport;
	}

	public void setPathImport(String pathImport) {
		this.pathImport.setText(pathImport);
	}

	public TextField getStudentExport() {
		return studentExport;
	}


	public TextField getPathExport() {
		return pathExport;
	}

	public void setPathExport(String pathExport) {
		this.pathExport.setText(pathExport);
	}

	public TextField getAddName() {
		return addName;
	}

	public void setAddName(TextField addName) {
		this.addName = addName;
	}

	public Label getTextTitle() {
		return textTitle;
	}

	public void setTextTitle(String textTitle) {
		this.textTitle.setText(textTitle);
	}

	public Label getTextNameHeadmaster() {
		return textNameHeadmaster;
	}

	public void setTextNameHeadmaster(String textNameHeadmaster) {
		this.textNameHeadmaster.setText(textNameHeadmaster);
	}

	public Label getTextNumberTeachers() {
		return textNumberTeachers;
	}

	public void setTextNumberTeachers(String textNumberTeachers) {
		this.textNumberTeachers.setText(textNumberTeachers);
	}

	public Label getTextTotalNumberStudents() {
		return textTotalNumberStudents;
	}

	public void setTextTotalNumberStudents(String textTotalNumberStudents) {
		this.textTotalNumberStudents.setText(textTotalNumberStudents);
	}

	public PasswordField getAddPass() {
		return addPass;
	}

	public void setAddPass(PasswordField addPass) {
		this.addPass = addPass;
	}

	public TextField getAddSubject() {
		return addSubject;
	}

	public void setAddSubject(TextField addSubject) {
		this.addSubject = addSubject;
	}

	public Label getTextTotalNumberSubjects() {
		return textTotalNumberSubjects;
	}

	public void setTextTotalNumberSubjects(String textTotalNumberSubjects) {
		this.textTotalNumberSubjects.setText(textTotalNumberSubjects);
	}

	public ArrayList<TextField> getData() {
		return data;
	}

	public void setData(ArrayList<TextField> data) {
		this.data = data;
	}

	public ArrayList<TextField> getMark() {
		return mark;
	}

	public void setMark(ArrayList<TextField> mark) {
		this.mark = mark;
	}

	public ArrayList<Stage> getStages() {
		return stages;
	}

	public void setStages(ArrayList<Stage> stages) {
		this.stages = stages;
	}

	public Label getTextMessage() {
		return textMessage;
	}

	public void setTextMessage(String textMessage) {
		this.textMessage.setText(textMessage);
	}

	public TextField getMarkStudent() {
		return markStudent;
	}

	private TextField markStudent;

	public void setStudents(ArrayList<String> students) {
		this.students = students;
		mark = new ArrayList<TextField>();
		data = new ArrayList<TextField>();
		for (int i = 0; i < students.size() - 1; i++) {
			TextField tf = new TextField("");
			TextField tf1 = new TextField("");
			mark.add(tf);
			data.add(tf1);

		}
		addStudents(menu, students, students.get(0));
	}

	public ArrayList<String> getStudents() {
		return students;
	}

	public Label getTextNameTeacher() {
		return textNameTeacher;
	}

	public void setTextNameTeacher(String textNameTeacher) {
		this.textNameTeacher.setText(textNameTeacher);
	}

	public void setTextNameSubject(String textNameSubject) {
		this.textNameSubject.setText(textNameSubject);
	}

	public void setTextNumberStudents(String textNumberStudents) {
		this.textNumberStudents.setText(textNumberStudents);
	}

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

	public void display() {

		Stage primaryStage = new Stage();
		primaryStage.setTitle("JavaFX 2 Login");

		bp = new BorderPane();
		bp.setPrefSize(900, 600);

		loginScreen(bp);

		// Adding BorderPane to the scene and loading CSS
		Scene scene = new Scene(bp);
		scene.getStylesheets()
				.add(getClass().getClassLoader().getResource("client/resources/gui.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Catalog Virtual");
		primaryStage.setResizable(false);
		primaryStage.show();
		primaryStage.setOnHiding(new ButtonCloseWindowListener(this));
	}

	public void loginScreen(BorderPane bp) {

		HBox hbox = new HBox();

		VBox vbox1 = new VBox();
		vbox1.setPrefSize(460, 620);

		VBox imagePane1 = new VBox();
		imagePane1.setPadding(new Insets(100, 0, 0, 30));
		ImageView logo1 = new ImageView(
				new Image(getClass().getResourceAsStream("/client/resources/try.png"), 400, 400, true, true));

		imagePane1.getChildren().add(logo1);

		vbox1.getChildren().add(imagePane1);

		VBox vbox2 = new VBox();
		vbox2.setPrefSize(460, 550);
		vbox2.setPadding(new Insets(0, 0, 0, 110));

		VBox imagePane = new VBox();
		imagePane.setPadding(new Insets(0, 0, 0, -30));
		ImageView logo = new ImageView(
				new Image(getClass().getResourceAsStream("/client/resources/catalog_logo.png"), 300, 300, true, true));

		imagePane.getChildren().add(logo);

		Label textUsername = new Label("Username");
		textUsername.setPadding(new Insets(10, 0, 0, 0));
		textUsername.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		textUsername.setTextFill(Color.web("663F15"));

		txtUserName = new TextField();
		txtUserName.setPadding(new Insets(0, 0, 0, 0));
		txtUserName.setFocusTraversable(false);
		txtUserName.setFont(Font.font("Courier New", FontWeight.NORMAL, 15));

		Label textPassword = new Label("Password");
		textPassword.setPadding(new Insets(10, 0, 0, 0));
		textPassword.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		textPassword.setTextFill(Color.web("663F15"));

		pf = new PasswordField();
		pf.setPadding(new Insets(0, 0, 0, 0));
		pf.setFocusTraversable(false);
		pf.setFont(Font.font("Courier New", FontWeight.NORMAL, 15));

		Label textJob = new Label("Job");
		textJob.setPadding(new Insets(10, 0, 0, 0));
		textJob.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		textJob.setTextFill(Color.web("663F15"));

		cb = new ChoiceBox(FXCollections.observableArrayList("Teacher", "Headmaster"));
		cb.setPadding(new Insets(0, 0, 0, 0));

		HBox btnLoginPane = new HBox();
		btnLoginPane.setPadding(new Insets(100, 0, 0, 0));

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

	public void teacherMode(BorderPane bp, int presentation, String title) {
		stages = new ArrayList<Stage>();
		HBox hbox = new HBox();
		VBox vbox1;
		if (presentation == 1) {
			vbox1 = teacherPresentation();
		} else {
			System.out.print("yuuuuu");
			vbox1 = headmasterPresentation();
		}

		VBox vbox2 = new VBox();
		vbox2.setPrefSize(620, 550);

		textTitle = new Label(title);
		textTitle.setPadding(new Insets(40, 0, 0, 0));
		textTitle.setWrapText(true);
		textTitle.setFont(Font.font("Courier New", FontWeight.BOLD, 35));
		textTitle.setTextFill(Color.web("008FE9"));
		deleteMarkAbsence = new TextField();
		nameStudent = new TextField();

		GridPane tableBar = new GridPane();
		tableBar.setPadding(new Insets(50, 0, 0, 55));
		if (presentation == 1) {
			tableBar.add(rankingTopInitialization("#008FE9", "Name", "", "Mark", "Data", "Options", 515, "Students"), 0,
					0);
		}
		if (presentation == 2) {
			tableBar.add(rankingTopInitialization("#008FE9", "Name", "Subj.", "Name", "Edit", "Delete", 515,
					"HeadmasterTeachers"), 0, 0);
		}
		if (presentation == 3) {
			tableBar.add(rankingTopInitialization("#008FE9", "Name", "", "Name", "Edit", "Delete", 515,
					"HeadmasterSubjects"), 0, 0);
		}
		if (presentation == 4) {
			tableBar.add(
					rankingTopInitialization("#008FE9", "Name", "", "", "Edit", "Delete", 515, "HeadmasterStudents"), 0,
					0);
		}
		if (presentation == 5) {
			tableBar.add(
					rankingTopInitialization("#008FE9", "Name", "", "", " ", "Situation", 515, "HeadmasterStudents"), 0,
					0);
		}
		if (presentation == 6) {
			tableBar.add(
					rankingTopInitialization("#008FE9", "Option", "Path", "", "Load", "Save", 515, "HeadmasterStudents"), 0,
					0);
		}
		if (presentation == 7 || presentation == 8) {
			tableBar.add(
					rankingTopInitialization("#008FE9", "Name", "", "", "Edit", "Delete", 515, "HeadmasterStudents"), 0,
					0);
		}


		GridPane vboxTable = new GridPane();
		if (presentation == 1) {
			vboxTable.setPrefSize(400, 390);
		} else if(presentation == 8){

			vboxTable.setPrefSize(400, 150);

		}
		else if(presentation == 7){

			vboxTable.setPrefSize(400, 150);

		} else {
			vboxTable.setPrefSize(400, 300);
		}

		vboxTable.setPadding(new Insets(0, 0, 0, 55));

		menu = new VBox();
		menu.setSpacing(5);
		ScrollPane menusp = new ScrollPane();
		menusp.setPrefWidth(515);
		menusp.setContent(menu);

		menu.setSpacing(1);

		/*
		 * if(presentation != 1){ for(int i = 1 ; i < 21; i++){
		 * rankInitialization(menu,i,"#C1DCFE",
		 * "Radu Denis","Edit","Delete","LALA"); } }
		 * 
		 */

		vboxTable.add(menusp, 0, 0);

		textMessage = new Label("");
		textMessage.setPadding(new Insets(10, 0, 0, 0));
		textMessage.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		textMessage.setStyle("-fx-text-fill: linear-gradient(orange   , orangered  ) ;");

		vbox2.setAlignment(Pos.CENTER);
		vbox2.setAlignment(Pos.TOP_CENTER);
		vbox2.getChildren().add(textTitle);
		vbox2.getChildren().add(tableBar);

		vbox2.getChildren().add(vboxTable);
		vbox2.getChildren().add(textMessage);

		if (presentation != 1) {
			GridPane vboxAdd = new GridPane();
			vboxAdd.setPrefSize(400, 30);
			vboxAdd.setPadding(new Insets(30, 0, 0, 55));


			GridPane vboxAddGridPane = new GridPane();
			vboxAddGridPane.setPrefSize(400, 60);
			vboxAddGridPane.setPadding(new Insets(0, 0, 0, 55));



			GridPane backButtonPane = new GridPane();
			backButtonPane.setPadding(new Insets(0, 0, 0, 20));

			Button buttonBack = new Button("Back");
			buttonBack.setFont(Font.font("Courier New", FontWeight.BOLD, 15));
			buttonBack.setStyle(
					"-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;-fx-border-color: linear-gradient(orange  , orangered ) ;\n"
							+ "  -fx-border-width: 1.5px ; -fx-border-radius: 5;\n" + "    -fx-padding: 5 5 5 5;\n"
							+ "    -fx-background-radius: 5;");
			buttonBack.setPrefSize(60, 25);
			buttonBack.setOnAction(new ButtonBackListener(this));

			if(presentation == 7 || presentation == 8){
				backButtonPane.setPadding(new Insets(50, 0, 0, 20));
			}

			backButtonPane.add(buttonBack, 0, 0);

			VBox boxAdd = new VBox();
			// boxAdd.setPrefSize(400,30);
			if (title.equals("Students") ) {
				if(presentation == 8){
					vboxAdd.add(rankingTopInitialization("#008FE9", "Edit Student", "Options", "", "Name", "Add", 430, ""), 0, 0);
					boxAdd.setPrefSize(430,300);
					addOption(boxAdd, "#C1DCFE", "Add Student", "8");
				}
				else
				if(presentation == 7){
					vboxAdd.add(rankingTopInitialization("#008FE9", "Add Student", "Options", "", "Name", "Add", 430, ""), 0, 0);
					boxAdd.setPrefSize(430,300);
					addOption(boxAdd, "#C1DCFE", "Add Student", "7");
				}
				else{
					vboxAdd.add(rankingTopInitialization("#008FE9", "Option", "Options", "", "Name", "Add", 430, ""), 0, 0);
					addOption(boxAdd, "#C1DCFE", "Add Student", "Add");
				}
			}
			if (title.equals("Situation of Students")) {
				vboxAdd.add(rankingTopInitialization("#008FE9", "Option", "Options", "Name", "Path", "Options", 430, "StudentSituation"), 0, 0);
				addOption(boxAdd, "#C1DCFE", "Export XML Situation", "");
				System.out.print("lalala");

			}
			if (title.equals("Teachers")) {
				vboxAdd.add(rankingTopInitialization("#008FE9", "Option", "Options", "", "Name/Password/Subject", "Add", 430, "HeadmasterTeachers"), 0, 0);
				addOption(boxAdd, "#C1DCFE", "Add Teacher", "Add");
			}
			if (title.equals("Subjects")) {

				vboxAdd.add(rankingTopInitialization("#008FE9", "Option", "Options", "", "Name", "Add", 430, "HeadmasterSubjects"), 0, 0);
				addOption(boxAdd, "#C1DCFE", "Add Subject", "Add");
			}
			if (title.equals("Import/Export Students")) {
				rankInitialization(menu, 1, "#C1DCFE", "Import", "", "M", "A", "S", "Import/Export Students");
				rankInitialization(menu, 2, "#C1DCFE", "Export", "", "M", "A", "S", "Import/Export Students");
				backButtonPane.setPadding(new Insets(0, 0, 0, 450));
			}



			vboxAddGridPane.add(boxAdd, 0, 0);
			vboxAddGridPane.add(backButtonPane, 1, 0);
			vbox2.getChildren().add(vboxAdd);
			vbox2.getChildren().add(vboxAddGridPane);
			if (presentation == 1) {
				vbox2.getChildren().add(textMessage);
			}

		}

		vbox2.setId("vbox2");

		hbox.getChildren().add(vbox1);
		hbox.getChildren().add(vbox2);

		bp.setCenter(hbox);
	}

	public void headmasterMode(BorderPane bp) {
		HBox hbox = new HBox();
		VBox vbox1 = headmasterPresentation();
		menu = new VBox();
		menu.setSpacing(5);

		VBox vbox2 = new VBox();
		vbox2.setPrefSize(620, 550);

		textTitle = new Label("Menu");
		textTitle.setPadding(new Insets(40, 0, 0, 0));
		textTitle.setWrapText(true);
		textTitle.setFont(Font.font("Courier New", FontWeight.BOLD, 35));
		textTitle.setTextFill(Color.web("008FE9"));

		GridPane menu1Pane = new GridPane();
		menu1Pane.setPadding(new Insets(50, 18, 0, 18));

		GridPane menu1 = new GridPane();
		menu1.setHgap(15);
		menu1.setVgap(5);
		menu1.setPrefSize(600, 245);

		GridPane addSubjectMenu = new GridPane();
		addSubjectMenu.setVgap(5);
		addSubjectMenu.setPadding(new Insets(0, 0, 0, 0));
		addSubjectMenu.setPrefSize(173, 235);

		HBox btnAddSubjectPane = new HBox();
		btnAddSubjectPane.setPadding(new Insets(0, 0, 0, 50));

		Button addSubjectButton = buttonInitialization(80, 20);
		addSubjectButton.setOnAction(new ButtonEditSubjectListener(this));

		btnAddSubjectPane.getChildren().add(addSubjectButton);

		addSubjectMenu.add(titleBarInitialization("Subjects", 173, 25), 0, 0);
		addSubjectMenu.add(messageInitialization(
				"Subjects are the parts into which learning can be divided. At school, each lesson usually covers one subject only.",
				173, 160), 0, 2);
		addSubjectMenu.add(btnAddSubjectPane, 0, 3);

		GridPane addTeacherMenu = new GridPane();
		addTeacherMenu.setVgap(5);
		addTeacherMenu.setPadding(new Insets(0, 0, 0, 0));
		addTeacherMenu.setPrefSize(173, 235);

		HBox btnAddTeacherPane = new HBox();
		btnAddTeacherPane.setPadding(new Insets(0, 0, 0, 50));

		Button addTeacherButton = buttonInitialization(80, 20);
		addTeacherButton.setOnAction(new ButtonEditTeacherListener(this));

		btnAddTeacherPane.getChildren().add(addTeacherButton);

		addTeacherMenu.add(titleBarInitialization("Teachers", 173, 25), 0, 0);
		addTeacherMenu.add(messageInitialization(
				"A teacher is a person who helps others to acquire knowledge, competences or values.", 173, 160), 0, 2);
		addTeacherMenu.add(btnAddTeacherPane, 0, 3);

		GridPane addStudentMenu = new GridPane();
		addStudentMenu.setVgap(5);
		addStudentMenu.setPadding(new Insets(0, 0, 0, 0));
		addStudentMenu.setPrefSize(173, 235);

		HBox btnAddStudentPane = new HBox();
		btnAddStudentPane.setPadding(new Insets(0, 0, 0, 50));

		Button addStudentButton = buttonInitialization(80, 20);
		addStudentButton.setOnAction(new ButtonEditStudentListener(this));

		btnAddStudentPane.getChildren().add(addStudentButton);

		addStudentMenu.add(titleBarInitialization("Students", 173, 25), 0, 0);
		addStudentMenu.add(messageInitialization(
				"A student is a person formally engaged in learning, especially one enrolled in a school or college.",
				173, 160), 0, 2);
		addStudentMenu.add(btnAddStudentPane, 0, 3);

		menu1.add(addSubjectMenu, 1, 1);
		menu1.add(addTeacherMenu, 2, 1);
		menu1.add(addStudentMenu, 3, 1);

		menu1Pane.add(menu1, 0, 0);

		GridPane menu2Pane = new GridPane();
		menu2Pane.setPadding(new Insets(10, 18, 0, 18));

		GridPane menu2 = new GridPane();
		menu2.setHgap(15);
		menu2.setVgap(5);
		menu2.setPrefSize(600, 195);

		GridPane importExportStudentsMenu = new GridPane();
		importExportStudentsMenu.setVgap(5);
		importExportStudentsMenu.setPadding(new Insets(0, 0, 0, 0));
		importExportStudentsMenu.setPrefSize(267, 185);

		HBox btnImportExportStudentsPane = new HBox();
		btnImportExportStudentsPane.setPadding(new Insets(0, 0, 0, 80));

		Button importExportButton = buttonInitialization(100, 20);
		importExportButton.setOnAction(new ButtonImportExportListener(this));

		btnImportExportStudentsPane.getChildren().add(importExportButton);

		importExportStudentsMenu.add(titleBarInitialization("Import/Export Students", 267, 25), 0, 0);
		importExportStudentsMenu.add(messageInitialization(
				"The import and export of students is the automated input and output of data sets between application and your computer.",
				267, 110), 0, 2);
		importExportStudentsMenu.add(btnImportExportStudentsPane, 0, 3);

		GridPane ratioStudentsMenu = new GridPane();
		ratioStudentsMenu.setVgap(5);
		ratioStudentsMenu.setPadding(new Insets(0, 0, 0, 0));
		ratioStudentsMenu.setPrefSize(267, 185);

		HBox btnRatioPane = new HBox();
		btnRatioPane.setPadding(new Insets(0, 0, 0, 80));

		Button ratioButton = buttonInitialization(100, 20);
		ratioButton.setOnAction(new ButtonStudentSituationListener(this));

		btnRatioPane.getChildren().add(ratioButton);

		ratioStudentsMenu.add(titleBarInitialization("Student's Situation", 267, 25), 0, 0);
		ratioStudentsMenu.add(messageInitialization(
				"The student's situation is a document where you can see all of the student's notes and absences on all subjects.",
				267, 110), 0, 2);
		ratioStudentsMenu.add(btnRatioPane, 0, 3);

		menu2.add(importExportStudentsMenu, 1, 1);
		menu2.add(ratioStudentsMenu, 2, 1);

		menu2Pane.add(menu2, 0, 0);

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

	private GridPane titleBarInitialization(String title, double width, double height) {

		GridPane messagePane = new GridPane();
		messagePane.setPrefSize(width, height);

		messagePane.setHgap(10);
		messagePane.setVgap(5);

		Label textRanking = new Label(title);
		textRanking.setFocusTraversable(false);
		textRanking.setStyle("-fx-text-fill:  #ffffff;");
		textRanking.setFont(Font.font("Courier New", FontWeight.BOLD, 19));

		messagePane.add(textRanking, 1, 0);

		messagePane.setId("vbox1");

		return messagePane;

	}

	private GridPane messageInitialization(String message, double width, double height) {

		GridPane messagePane = new GridPane();
		messagePane.setPrefSize(width, height);

		messagePane.setHgap(10);
		messagePane.setVgap(5);

		Label textRanking = new Label(message);
		textRanking.setWrapText(true);
		textRanking.setFocusTraversable(false);
		textRanking.setStyle("-fx-text-fill:  #008FE9;");
		textRanking.setFont(Font.font("Courier New", FontWeight.NORMAL, 15));

		messagePane.add(textRanking, 1, 0);

		return messagePane;

	}

	private Button buttonInitialization(double width, double height) {

		Button buttonAbcecnce = new Button("Continue");
		buttonAbcecnce.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
		buttonAbcecnce.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
		buttonAbcecnce.setPrefSize(width, height);

		return buttonAbcecnce;
	}

	private GridPane rankingTopInitialization(String color, String stringMark, String mat, String stringData,
			String stringOne, String stringTwo, double width, String option) {

		GridPane messagePane = new GridPane();
		messagePane.setPrefSize(width, 30);

		messagePane.setHgap(9);
		messagePane.setVgap(5);
		messagePane.setStyle("-fx-background-color: " + color + ";");


		if(!stringMark.equals("Add Student") && !stringMark.equals("Edit Student")){
			Label textRanking = new Label("Nr");
			textRanking.setFocusTraversable(false);
			textRanking.setStyle("-fx-text-fill:  #ffffff;");
			textRanking.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
			messagePane.add(textRanking, 1, 0);
		}



		Label textMark = new Label(stringMark);
		textMark.setFocusTraversable(false);
		textMark.setStyle("-fx-text-fill:  #ffffff;");
		textMark.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

		Label textName = new Label(stringData);
		Label textLevel = new Label(stringOne);
		Label textWins = new Label(stringTwo);

		textName.setFocusTraversable(false);
		textName.setStyle("-fx-text-fill:  #ffffff;");
		textName.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

		textLevel.setFocusTraversable(false);
		textLevel.setStyle("-fx-text-fill:  #ffffff;");
		textLevel.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

		textWins.setFocusTraversable(false);
		textWins.setStyle("-fx-text-fill:  #ffffff;");
		textWins.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

		if (option.equals("StudentSituation")) {
			if(width == 515){
				messagePane.add(textName, 26, 0);
			}

			if (width == 430) {
				messagePane.add(textName,6,0);
				messagePane.add(textLevel, 13, 0);
				messagePane.add(textWins, 20, 0);

			}
		}
		if (option.equals("Students")) {

			messagePane.add(textName, 26, 0);
			if (width == 515) {
				messagePane.add(textLevel, 28, 0);
				messagePane.add(textWins, 32, 0);

			}
		}
		if (option.equals("HeadmasterTeachers")) {
			Label textMat = new Label("Subj.");
			textMat.setPadding(new Insets(0, 0, 0, 8));
			textMat.setFocusTraversable(false);
			textMat.setStyle("-fx-text-fill:  #ffffff;");
			textMat.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

			textName.setPadding(new Insets(0, 0, 0, -3));
			textLevel.setPadding(new Insets(0, 0, 0, 17));
			textWins.setPadding(new Insets(0, 0, 0, -13));

			if (width == 515) {
				messagePane.add(textMat, 21, 0);
				messagePane.add(textName, 22, 0);
				messagePane.add(textLevel, 23, 0);
				messagePane.add(textWins, 27, 0);
			}
			if (width == 430) {
				messagePane.add(textLevel, 7, 0);
				messagePane.add(textWins, 11, 0);
			}

		}
		if (option.equals("HeadmasterSubjects")) {

			textName.setPadding(new Insets(0, 0, 0, 17));
			textLevel.setPadding(new Insets(0, 0, 0, 10));
			textWins.setPadding(new Insets(0, 0, 0, -15));
			messagePane.add(textName, 26, 0);
			if (width == 515) {
				messagePane.add(textLevel, 28, 0);
				messagePane.add(textWins, 32, 0);
			}
			if (width == 430) {
				messagePane.add(textLevel, 22, 0);
				messagePane.add(textWins, 28, 0);
			}
		}

		if (option.equals("HeadmasterStudents")) {

			textLevel.setPadding(new Insets(0, 0, 0, 5));
			textWins.setPadding(new Insets(0, 0, 0, -7));
			if (width == 515) {
				messagePane.add(textLevel, 35, 0);
				messagePane.add(textWins, 38, 0);
			}
		}


		messagePane.add(textMark, 3, 0);

		return messagePane;

	}

	private void addStudents(VBox vbox, ArrayList<String> students, String option) {
		vbox.getChildren().clear();
		if (option.equals("Students")) {
			for (int i = 1; i < students.size(); i++) {
				rankInitialization(vbox, i, "#C1DCFE", students.get(i), "", "M", "A", "S", option);

			}
		}
		if (option.equals("addStudentInterface")) {
			for (int i = 1; i < students.size(); i++) {
				rankInitialization(vbox, i, "#C1DCFE", students.get(i), "", "M", "A", "S", option);

			}
		}
		if (option.equals("editStudentsInterface")) {
			for (int i = 1; i < students.size(); i++) {
				rankInitialization(vbox, i, "#C1DCFE", students.get(i), "", "M", "A", "S", option);

			}
		}
		if (option.equals("SeeTeachers")) {
			for (int i = 1; i < (students.size() + 1) / 2; i++) {
				rankInitialization(vbox, i, "#C1DCFE", students.get(i), students.get(i + (students.size() - 1) / 2),
						"M", "A", "S", option);
			}
		}
		if (option.equals("SeeSubjects")) {
			for (int i = 1; i < students.size(); i++) {
				rankInitialization(vbox, i, "#C1DCFE", students.get(i), "", "M", "A", "S", option);
			}
		}
		if (option.equals("SeeStudents")) {
			for (int i = 1; i < students.size(); i++) {
				rankInitialization(vbox, i, "#C1DCFE", students.get(i), "", "M", "A", "S", option);
			}
		}
		if (option.equals("SituationOfStudents")) {
			for (int i = 1; i < students.size(); i++) {
				rankInitialization(vbox, i, "#C1DCFE", students.get(i), "", "M", "A", "S", option);
			}
		}
	}

	private void rankInitialization(VBox discution, int number, String color, String name, String subject,
			String buttonOne, String buttonTwo, String buttonThree, String option) {

		GridPane messagePane = new GridPane();
		messagePane.setPrefSize(500, 40);
		messagePane.setHgap(8);
		messagePane.setVgap(5);
		messagePane.setStyle("-fx-background-color: " + color + ";");

		Label textRanking = new Label("" + number);
		textRanking.setPadding(new Insets(5, 0, 0, 0));
		textRanking.setFocusTraversable(false);
		textRanking.setStyle("-fx-text-fill:  #663F15;");
		textRanking.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

		Label textName = new Label(name);
		textName.setPadding(new Insets(5, 0, 0, 0));
		textName.setPrefWidth(250);
		textName.setFocusTraversable(false);
		textName.setStyle("-fx-text-fill:  #663F15;");
		textName.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

		if(option.equals("Import/Export Students")){
			messagePane.setPrefSize(513, 40);
			textName.setPrefWidth(150);

			GridPane XMLSituationPane = new GridPane();
			XMLSituationPane.setHgap(10);
			XMLSituationPane.setPadding(new Insets(8, 0, 0, 0));

			if(number == 1){
				pathImport = new TextField("Path Import");
				pathImport.setEditable(false);
				pathImport.setPrefSize(185,10);

				XMLSituationPane.add(pathImport,0,0);
			}
			if(number == 2){
				pathExport = new TextField("Path Export");
				pathExport.setEditable(false);
				pathExport.setPrefSize(185,10);

				XMLSituationPane.add(pathExport,0,0);
			}



			GridPane buttonPane = new GridPane();
			buttonPane.setHgap(5);
			buttonPane.setPadding(new Insets(8, 0, 0, 0));

			Button buttonLoad = new Button("Load");
			buttonLoad.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonLoad.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonLoad.setPrefSize(50, 15);


			Button buttonSave = new Button("Save");
			buttonSave.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonSave.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonSave.setPrefSize(50, 15);


			if(number == 1){
				buttonLoad.setOnAction(new ButtonLoadImportPath(this));
				buttonSave.setOnAction(new ButtonSaveImport(this,"ImportStudents"));

			}
			if(number == 2){
				buttonLoad.setOnAction(new ButtonLoadExportPath(this));
				buttonSave.setOnAction(new ButtonSaveExport(this,"ExportStudents"));
			}

			buttonPane.add(buttonLoad,0,0);
			buttonPane.add(buttonSave,2,0);

			messagePane.add(XMLSituationPane,5,0);
			messagePane.add(buttonPane,6,0);
		}

		if (option.equals("Students")) {
			GridPane gpData = new GridPane();
			gpData.setPadding(new Insets(8, 0, 0, 0));

			TextField dataStudent = getData().get(number - 1);
			dataStudent.setPrefWidth(70);
			dataStudent.setFocusTraversable(false);
			dataStudent.setFont(Font.font("Courier New", FontWeight.NORMAL, 11));

			gpData.add(dataStudent, 0, 0);

			GridPane gpMark = new GridPane();
			gpMark.setPadding(new Insets(8, 0, 0, 0));

			TextField markStudent = getMark().get(number - 1);
			markStudent.setPrefWidth(29);
			markStudent.setFocusTraversable(false);
			markStudent.setFont(Font.font("Courier New", FontWeight.NORMAL, 11));

			gpMark.add(markStudent, 0, 0);

			GridPane buttonsPane = new GridPane();
			buttonsPane.setHgap(5);
			buttonsPane.setVgap(5);
			buttonsPane.setPadding(new Insets(8, 0, 0, 0));

			Button buttonMark = new Button(buttonOne);
			buttonMark.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonMark.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonMark.setPrefSize(15, 15);
			buttonMark.setOnAction(new ButtonAddMark(this, name, textNameSubject.getText()));

			Button buttonAbcecnce = new Button(buttonTwo);
			buttonAbcecnce.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonAbcecnce
					.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonAbcecnce.setPrefSize(15, 15);
			buttonAbcecnce.setOnAction(new ButtonAddAbsence(this, name, textNameSubject.getText()));

			Button buttonSituation = new Button(buttonThree);
			buttonSituation.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonSituation
					.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonSituation.setPrefSize(15, 15);
			buttonSituation.setOnAction(new ButtonSituationStudent(this, name, textNameSubject.getText()));

			buttonsPane.add(buttonMark, 0, 0);
			buttonsPane.add(buttonAbcecnce, 1, 0);
			buttonsPane.add(buttonSituation, 2, 0);

			messagePane.add(gpMark, 5, 0);
			messagePane.add(gpData, 6, 0);
			messagePane.add(buttonsPane, 7, 0);

		}
		if (option.equals("SeeTeachers")) {
			GridPane gpData = new GridPane();
			gpData.setPadding(new Insets(8, 0, 0, 0));

			TextField dataStudent = getData().get(number - 1);
			dataStudent.setPrefWidth(70);
			dataStudent.setFocusTraversable(false);
			dataStudent.setFont(Font.font("Courier New", FontWeight.NORMAL, 11));

			gpData.add(dataStudent, 0, 0);

			textName.setPrefWidth(205);
			dataStudent.setPrefWidth(50);

			GridPane gpMark = new GridPane();
			gpMark.setPadding(new Insets(2, 0, 0, 0));
			if(subject.length()>=5)
			subject = subject.substring(0, 5);
			Label markStudent = new Label(subject);
			markStudent.setPrefWidth(40);
			markStudent.setPadding(new Insets(8, 0, 0, 0));
			markStudent.setFocusTraversable(false);
			markStudent.setFont(Font.font("Courier New", FontWeight.NORMAL, 13));

			gpMark.add(markStudent, 0, 0);

			gpData.setPadding(new Insets(8, 0, 0, 0));

			GridPane buttonsPane = new GridPane();
			buttonsPane.setHgap(5);
			buttonsPane.setVgap(5);
			buttonsPane.setPadding(new Insets(8, 0, 0, 0));

			Button buttonMark = new Button("Edit");
			buttonMark.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonMark.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonMark.setPrefSize(65, 15);
			buttonMark.setOnAction(new ButtonEditName(this, name, textTitle.getText()));

			Button buttonAbcecnce = new Button("Delete");
			buttonAbcecnce.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonAbcecnce
					.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonAbcecnce.setPrefSize(65, 15);
			buttonAbcecnce.setOnAction(new ButtonDeleteListener(this, name, textTitle.getText()));

			buttonsPane.add(buttonMark, 0, 0);
			buttonsPane.add(buttonAbcecnce, 1, 0);

			messagePane.add(buttonsPane, 7, 0);
			messagePane.add(gpMark, 5, 0);
			messagePane.add(gpData, 6, 0);

		}
		if (option.equals("SeeSubjects")) {
			GridPane gpData = new GridPane();
			gpData.setPadding(new Insets(8, 0, 0, 0));

			TextField dataStudent = getData().get(number - 1);
			dataStudent.setPrefWidth(70);
			dataStudent.setFocusTraversable(false);
			dataStudent.setFont(Font.font("Courier New", FontWeight.NORMAL, 11));

			gpData.add(dataStudent, 0, 0);

			dataStudent.setPrefWidth(50);
			textName.setPrefWidth(245);

			GridPane buttonsPane = new GridPane();
			buttonsPane.setHgap(5);
			buttonsPane.setVgap(5);
			buttonsPane.setPadding(new Insets(8, 0, 0, 0));

			Button buttonMark = new Button("Edit");
			buttonMark.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonMark.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonMark.setPrefSize(65, 15);
			buttonMark.setOnAction(new ButtonEditName(this, name, textTitle.getText()));

			Button buttonAbcecnce = new Button("Delete");
			buttonAbcecnce.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonAbcecnce
					.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonAbcecnce.setPrefSize(65, 15);
			buttonAbcecnce.setOnAction(new ButtonDeleteListener(this, name, textTitle.getText()));

			buttonsPane.add(buttonMark, 0, 0);
			buttonsPane.add(buttonAbcecnce, 1, 0);
			messagePane.add(gpData, 6, 0);

			messagePane.add(buttonsPane, 7, 0);
			// messagePane.add(gpMark,5,0);
		}

		if (option.equals("SeeStudents") || option.equals("addStudentInterface") || option.equals("editStudentsInterface")) {

			textName.setPrefWidth(295);

			GridPane buttonsPane = new GridPane();
			buttonsPane.setHgap(5);
			buttonsPane.setVgap(5);
			buttonsPane.setPadding(new Insets(8, 0, 0, 0));

			Button buttonMark = new Button("Edit");
			buttonMark.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonMark.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonMark.setPrefSize(65, 15);
			buttonMark.setOnAction(new ButtonEditStudent(this, name));

			Button buttonAbcecnce = new Button("Delete");
			buttonAbcecnce.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonAbcecnce
					.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonAbcecnce.setPrefSize(65, 15);
			buttonAbcecnce.setOnAction(new ButtonDeleteListener(this, name, textTitle.getText()));

			buttonsPane.add(buttonMark, 0, 0);
			buttonsPane.add(buttonAbcecnce, 1, 0);

			messagePane.add(buttonsPane, 7, 0);
			// messagePane.add(gpMark,5,0);
		}
		if(option.equals("SituationOfStudents")){
			GridPane buttonsPane = new GridPane();
			buttonsPane.setHgap(5);
			buttonsPane.setVgap(5);
			buttonsPane.setPadding(new Insets(8, 0, 0, 0));

			Button buttonMark = new Button("Situation");
			buttonMark.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonMark.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonMark.setPrefSize(90, 15);
			buttonMark.setOnAction(new ButtonAllSituationStudent(this, name));

			buttonsPane.add(buttonMark, 0, 0);


			messagePane.add(buttonsPane, 18, 0);
		}

		messagePane.add(textRanking, 1, 0);
		messagePane.add(textName, 4, 0);

		discution.getChildren().add(messagePane);

	}

	private void addOption(VBox discution, String color, String option, String buttonOne) {

		GridPane messagePane = new GridPane();
		messagePane.setPrefSize(430, 40);
		messagePane.setHgap(10);
		messagePane.setVgap(5);
		messagePane.setStyle("-fx-background-color: " + color + ";");

		if(buttonOne.equals("8")){
			GridPane studentData = new GridPane();
			studentData.setHgap(10);
			studentData.setPadding(new Insets(8, 0, 0, 0));

			nameStudent.setPrefWidth(200);
			nameStudent.setPromptText("Name");
			nameStudent.setText(addName.getText());


			studentData.add(nameStudent, 0, 0);

			FlowPane flowPane = new FlowPane();
			flowPane.setPadding(new Insets(5,0,0,0));
			flowPane.setVgap(10);
			flowPane.setHgap(10);

			HBox hbox = new HBox();
			hbox.setSpacing(50);


			addSubject.setPrefWidth(200);
			addSubject.setPromptText("Subject");

			GridPane buttonsPane = new GridPane();
			buttonsPane.setHgap(5);


			Button buttonAbcecnce = new Button("Add");
			buttonAbcecnce.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonAbcecnce.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonAbcecnce.setPrefSize(80, 15);
			buttonAbcecnce.setOnAction(new ButtonOptionsStudents(this, nameStudent.getText(),"addSubject"));

			Button buttonDelete = new Button("Delete");
			buttonDelete.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonDelete.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonDelete.setPrefSize(80, 15);
			buttonDelete.setOnAction(new ButtonOptionsStudents(this, nameStudent.getText(),"deleteSubject"));

			buttonsPane.add(buttonAbcecnce,0,0);
			buttonsPane.add(buttonDelete,1,0);

			hbox.getChildren().add(addSubject);
			hbox.getChildren().add(buttonsPane);

			HBox hbox1 = new HBox();
			hbox1.setSpacing(50);

			deleteMarkAbsence.setPrefWidth(200);
			deleteMarkAbsence.setPromptText("Date format dd/mm/yyyy");

			GridPane buttonPane = new GridPane();
			buttonPane.setHgap(5);


			Button buttonMark = new Button("Mark");
			buttonMark.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonMark.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonMark.setPrefSize(80, 15);
			buttonMark.setOnAction(new ButtonOptionsStudents(this, nameStudent.getText(),"deleteMark"));

			Button buttonAbssence = new Button("Absence");
			buttonAbssence.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonAbssence.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonAbssence.setPrefSize(80, 15);
			buttonAbssence.setOnAction(new ButtonOptionsStudents(this, nameStudent.getText(),"deleteAbsence"));

			buttonPane.add(buttonMark,0,0);
			buttonPane.add(buttonAbssence,1,0);

			hbox1.getChildren().add(deleteMarkAbsence);
			hbox1.getChildren().add(buttonPane);


			for(int i = 0 ; i < subjects.size() - 1; i++){
				flowPane.getChildren().add(cbs.get(i));
				cbs.get(i).setOnKeyPressed(new EnterKeyAddSubjectListener(this,i));
				cbs.get(i).setSelected(true);
				cbs.get(i).setDisable(true);
			}

			GridPane finishPane = new GridPane();
			finishPane.setPadding(new Insets(8, 0, 8, 160));

			Button buttonFinish = new Button("Finish");
			buttonFinish.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
			buttonFinish.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonFinish.setPrefSize(90, 15);
			buttonFinish.setOnAction(new ButtonOptionsStudents(this, nameStudent.getText(),"FinishEditStudent"));

			finishPane.add(buttonFinish,0,0);

			ScrollPane scroll = new ScrollPane();
			scroll.setContent(flowPane);
			flowPane.setStyle("-fx-background-color: #C1DCFE;");
			scroll.setFitToWidth(true);
			scroll.setFitToHeight(true);

			messagePane.add(studentData, 1, 0);
			messagePane.add(scroll,1,1);
			messagePane.add(hbox,1,2);
			messagePane.add(hbox1,1,3);
			messagePane.add(finishPane,1,4);

		}
		else
		if(buttonOne.equals("7")){
			GridPane studentData = new GridPane();
			studentData.setHgap(10);
			studentData.setPadding(new Insets(8, 0, 0, 65));


			addName.setPrefSize(130, 10);
			addName.setPromptText("Last Name");
			addName.setOnKeyPressed(new EnterKeyAddStudentListener(this));
			addSubject = new TextField();
			addSubject.setPrefSize(130, 10);
			addSubject.setPromptText("First Name");
			addSubject.setOnKeyPressed(new EnterKeyAddStudentListener(this));

			studentData.add(addName, 0, 0);
			studentData.add(addSubject, 2, 0);




			FlowPane flowPane = new FlowPane();
			flowPane.setPadding(new Insets(5,0,0,0));
			flowPane.setVgap(10);
			flowPane.setHgap(10);



			for(int i = 0 ; i < subjects.size() - 1; i++){
				flowPane.getChildren().add(cbs.get(i));
				cbs.get(i).setOnKeyPressed(new EnterKeyAddSubjectListener(this,i));
			}

			GridPane buttonPane = new GridPane();
			buttonPane.setHgap(10);
			buttonPane.setPadding(new Insets(15, 0, 10, 120));

			Button buttonLoad = new Button("Next");
			buttonLoad.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonLoad.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonLoad.setPrefSize(80, 15);
			buttonLoad.setOnAction(new ButtonNextStudent(this));
			buttonLoad.setOnKeyPressed(new EnterKeyAddStudentListener(this));


			Button buttonSave = new Button("Finish");
			buttonSave.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonSave.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonSave.setPrefSize(80, 15);
			buttonSave.setOnAction(new ButtonEditStudentListener(this));


			buttonPane.add(buttonLoad, 0, 0);
			buttonPane.add(buttonSave, 1, 0);


			messagePane.add(studentData, 1, 0);
			messagePane.add(flowPane,1,1);
			messagePane.add(buttonPane, 1, 2);

		}
		else {

			Label textRanking = new Label("1");
			textRanking.setPadding(new Insets(5, 0, 0, 0));
			textRanking.setFocusTraversable(false);
			textRanking.setStyle("-fx-text-fill:  #663F15;");
			textRanking.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

			Label textOption = new Label(option);
			if (option.equals("Export XML Situation")) textOption.setText("Export");
			textOption.setPadding(new Insets(5, 0, 0, 20));
			textOption.setFocusTraversable(false);
			textOption.setStyle("-fx-text-fill:  #663F15;");
			textOption.setFont(Font.font("Courier New", FontWeight.BOLD, 16));

			GridPane optionsTeacherPane = new GridPane();
			optionsTeacherPane.setHgap(10);
			optionsTeacherPane.setPadding(new Insets(8, 0, 0, 0));

			addName = new TextField();
			addName.setPrefSize(57, 10);
			addPass = new PasswordField();
			addPass.setPrefSize(57, 10);
			addSubject = new TextField();
			addSubject.setPrefSize(57, 10);

			optionsTeacherPane.add(addName, 0, 0);
			optionsTeacherPane.add(addPass, 1, 0);
			optionsTeacherPane.add(addSubject, 2, 0);

			GridPane buttonsPane = new GridPane();
			buttonsPane.setPadding(new Insets(8, 0, 0, 0));

			Button buttonAbcecnce = new Button(buttonOne);
			buttonAbcecnce.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
			buttonAbcecnce.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
			buttonAbcecnce.setPrefSize(60, 15);
			if (option.equals("Export XML Situation")) {
				GridPane XMLSituationPane = new GridPane();
				XMLSituationPane.setHgap(10);
				XMLSituationPane.setPadding(new Insets(8, 0, 0, 0));

				studentExport = new TextField();
				studentExport.setPrefSize(74, 10);

				pathExport = new TextField("Path");
				pathExport.setEditable(false);
				pathExport.setPrefSize(110, 10);

				XMLSituationPane.add(studentExport, 0, 0);
				XMLSituationPane.add(pathExport, 1, 0);

				GridPane buttonPane = new GridPane();
				buttonPane.setHgap(5);
				buttonPane.setPadding(new Insets(8, 0, 0, 0));

				Button buttonLoad = new Button("Load");
				buttonLoad.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
				buttonLoad.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
				buttonLoad.setPrefSize(50, 15);
				buttonLoad.setOnAction(new ButtonLoadExportPath(this));

				Button buttonSave = new Button("Save");
				buttonSave.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
				buttonSave.setStyle("-fx-background-color: linear-gradient(orange  , orangered );-fx-text-fill:  #ffffff;");
				buttonSave.setPrefSize(50, 15);
				buttonSave.setOnAction(new ButtonSaveExport(this, "ExportSituation"));

				buttonPane.add(buttonLoad, 0, 0);
				buttonPane.add(buttonSave, 1, 0);

				messagePane.add(XMLSituationPane, 3, 0);
				messagePane.add(buttonPane, 4, 0);
			}
			if (option.equals("Add Subject")) {
				buttonAbcecnce.setOnAction(new ButtonAddAdmin(this, "addSubject"));

				HBox namePane = new HBox();
				namePane.setPadding(new Insets(8, 0, 0, 90));
				namePane.getChildren().add(addName);
				addName.setPrefSize(100, 10);

				messagePane.add(namePane, 3, 0);
				messagePane.add(buttonsPane, 4, 0);
			} else if (option.equals("Add Teacher")) {
				buttonAbcecnce.setOnAction(new ButtonAddAdmin(this, "addTeacher"));
				messagePane.add(optionsTeacherPane, 3, 0);
				messagePane.add(buttonsPane, 4, 0);
			} else if (option.equals("Add Student")) {
				buttonAbcecnce.setOnAction(new ButtonAddAdmin(this, "addStudent"));
				messagePane.add(buttonsPane, 22, 0);
			}

			buttonsPane.add(buttonAbcecnce, 0, 0);
			messagePane.add(textRanking, 1, 0);
			messagePane.add(textOption, 2, 0);

		}

		discution.getChildren().add(messagePane);


	}

	private VBox teacherPresentation() {
		VBox vbox1 = new VBox();
		vbox1.setPrefSize(300, 620);

		VBox imagePane1 = new VBox();
		imagePane1.setPadding(new Insets(30, 0, 0, 70));
		ImageView logo1 = new ImageView(
				new Image(getClass().getResourceAsStream("/client/resources/teacher.png"), 150, 150, true, true));

		imagePane1.getChildren().add(logo1);

		Label textName = new Label("Teacher:");
		textName.setPadding(new Insets(40, 0, 0, 0));
		textName.setWrapText(true);
		textName.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
		textName.setTextFill(Color.web("FFFFFF"));

		textNameTeacher = new Label();
		textNameTeacher.setPadding(new Insets(0, 0, 0, 0));
		textNameTeacher.setWrapText(true);
		textNameTeacher.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		textNameTeacher.setTextFill(Color.web("FFFFFF"));

		Label textSubject = new Label("Subject:");
		textSubject.setPadding(new Insets(20, 0, 0, 0));
		textSubject.setWrapText(true);
		textSubject.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
		textSubject.setTextFill(Color.web("FFFFFF"));

		textNameSubject = new Label();
		textNameSubject.setPadding(new Insets(0, 0, 0, 0));
		textNameSubject.setWrapText(true);
		textNameSubject.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		textNameSubject.setTextFill(Color.web("FFFFFF"));

		Label textStudents = new Label("Students:");
		textStudents.setPadding(new Insets(20, 0, 0, 0));
		textStudents.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
		textStudents.setTextFill(Color.web("FFFFFF"));

		textNumberStudents = new Label();
		textNumberStudents.setPadding(new Insets(0, 0, 0, 0));
		textNumberStudents.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
		textNumberStudents.setTextFill(Color.web("FFFFFF"));

		HBox btnLogoutPane = new HBox();
		btnLogoutPane.setPadding(new Insets(100, 0, 0, 70));

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

	private VBox headmasterPresentation() {
		VBox vbox1 = new VBox();
		vbox1.setPrefSize(300, 620);

		VBox imagePane1 = new VBox();
		imagePane1.setPadding(new Insets(30, 0, 0, 70));
		ImageView logo1 = new ImageView(
				new Image(getClass().getResourceAsStream("/client/resources/headmaster.png"), 150, 150, true, true));

		imagePane1.getChildren().add(logo1);

		Label textHeadmaster = new Label("Headmaster:");
		textHeadmaster.setPadding(new Insets(40, 0, 0, 0));
		textHeadmaster.setWrapText(true);
		textHeadmaster.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
		textHeadmaster.setTextFill(Color.web("FFFFFF"));

		if (textNameHeadmaster != null) {
			textNameHeadmaster = new Label(textNameHeadmaster.getText());
			textNumberTeachers = new Label(textNumberTeachers.getText());
			textTotalNumberStudents = new Label(textTotalNumberStudents.getText());
			textTotalNumberSubjects = new Label(textTotalNumberSubjects.getText());
		} else {
			textNameHeadmaster = new Label();
			textNumberTeachers = new Label();
			textTotalNumberStudents = new Label();
			textTotalNumberSubjects = new Label();
		}

		textNameHeadmaster.setPadding(new Insets(0, 0, 0, 0));
		textNameHeadmaster.setWrapText(true);
		textNameHeadmaster.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		textNameHeadmaster.setTextFill(Color.web("FFFFFF"));

		Label textTeacher = new Label("Teachers:");
		textTeacher.setPadding(new Insets(20, 0, 0, 0));
		textTeacher.setWrapText(true);
		textTeacher.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
		textTeacher.setTextFill(Color.web("FFFFFF"));

		textNumberTeachers.setPadding(new Insets(0, 0, 0, 0));
		textNumberTeachers.setWrapText(true);
		textNumberTeachers.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
		textNumberTeachers.setTextFill(Color.web("FFFFFF"));

		Label textStudents = new Label("Students:");
		textStudents.setPadding(new Insets(20, 0, 0, 0));
		textStudents.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
		textStudents.setTextFill(Color.web("FFFFFF"));

		textTotalNumberStudents.setPadding(new Insets(0, 0, 0, 0));
		textTotalNumberStudents.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
		textTotalNumberStudents.setTextFill(Color.web("FFFFFF"));

		Label textSubjects = new Label("Subjects:");
		textSubjects.setPadding(new Insets(20, 0, 0, 0));
		textSubjects.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
		textSubjects.setTextFill(Color.web("FFFFFF"));

		textTotalNumberSubjects.setPadding(new Insets(0, 0, 0, 0));
		textTotalNumberSubjects.setFont(Font.font("Courier New", FontWeight.BOLD, 20));
		textTotalNumberSubjects.setTextFill(Color.web("FFFFFF"));

		HBox btnLogoutPane = new HBox();
		btnLogoutPane.setPadding(new Insets(50, 0, 0, 70));

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
		vbox1.getChildren().add(textTotalNumberStudents);
		vbox1.getChildren().add(textSubjects);
		vbox1.getChildren().add(textTotalNumberSubjects);
		vbox1.getChildren().add(btnLogoutPane);

		btnLogout.setId("btnLogout");
		vbox1.setId("vbox1");
		return vbox1;
	}

	public void displayAddMark(Stage primaryStage, String name, Subject subject) {



		VBox vBox = new VBox();
		vBox.setPrefSize(410, 350);
		ScrollPane menusp = new ScrollPane();
		menusp.setPrefSize(420, 350);
		menusp.setContent(vBox);

		Label title = new Label("Student Situation");
		title.setPadding(new Insets(10, 0, 0, 85));
		title.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
		title.setTextFill(Color.web("728C9A"));

		Label student = new Label("Student:");
		student.setPadding(new Insets(50, 0, 0, 20));
		student.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		student.setTextFill(Color.web("728C9A"));

		Label nameStudent = new Label(name);
		nameStudent.setPadding(new Insets(-20, 0, 0, 100));
		nameStudent.setWrapText(true);
		nameStudent.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		nameStudent.setStyle("-fx-text-fill: linear-gradient(orange   , orangered  ) ;");

		Label sbj = new Label("Subject:");
		sbj.setPadding(new Insets(20, 0, 0, 20));
		sbj.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		sbj.setTextFill(Color.web("728C9A"));

		Label nameSbj = new Label(subject.getName());
		nameSbj.setPadding(new Insets(-20, 0, 0, 110));
		nameSbj.setWrapText(true);
		nameSbj.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		nameSbj.setStyle("-fx-text-fill: linear-gradient(orange   , orangered  ) ;");

		Label teacher = new Label("Teacher:");
		teacher.setPadding(new Insets(20, 0, 0, 20));
		teacher.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		teacher.setTextFill(Color.web("728C9A"));

		Label nameTeacher = new Label(subject.getNameTeacher());
		nameTeacher.setPadding(new Insets(-20, 0, 0, 110));
		nameTeacher.setWrapText(true);
		nameTeacher.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		nameTeacher.setStyle("-fx-text-fill: linear-gradient(orange   , orangered  ) ;");

		Label marks = new Label("Marks:");
		marks.setPadding(new Insets(20, 0, 0, 20));
		marks.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		marks.setTextFill(Color.web("728C9A"));

		VBox hbox1 = new VBox();
		hbox1.setPadding(new Insets(-39, 0, 0, 70));

		for (int i = 0; i < subject.getMarks().size(); i++) {
			Label mark = new Label(subject.getMarks().get(i).toString());
			mark.setPadding(new Insets(20, 0, 0, 20));
			mark.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
			mark.setStyle("-fx-text-fill: linear-gradient(orange   , orangered  ) ;");
			hbox1.getChildren().add(mark);

		}

		Label absences = new Label("Absences:");
		absences.setPadding(new Insets(20, 0, 0, 20));
		absences.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		absences.setTextFill(Color.web("728C9A"));

		VBox hbox2 = new VBox();
		hbox2.setPadding(new Insets(-39, 0, 0, 100));
		for (int i = 0; i < subject.getAbsences().size(); i++) {
			Label mark = new Label(subject.getAbsences().get(i).toString());
			mark.setPadding(new Insets(20, 0, 0, 20));
			mark.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
			mark.setStyle("-fx-text-fill: linear-gradient(orange   , orangered  ) ;");
			hbox2.getChildren().add(mark);

		}

		vBox.getChildren().add(title);
		vBox.getChildren().add(student);
		vBox.getChildren().add(nameStudent);
		vBox.getChildren().add(sbj);
		vBox.getChildren().add(nameSbj);
		vBox.getChildren().add(teacher);
		vBox.getChildren().add(nameTeacher);
		vBox.getChildren().add(marks);
		vBox.getChildren().add(hbox1);
		vBox.getChildren().add(absences);
		vBox.getChildren().add(hbox2);

		vBox.setId("vbox2");

		// Adding BorderPane to the scene and loading CSS
		Scene scene = new Scene(menusp);
		scene.getStylesheets()
				.add(getClass().getClassLoader().getResource("client/resources/gui.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Situation");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

	public void displayAddMark(Stage primaryStage, Student stud) {

		VBox vBox = new VBox();
		vBox.setPrefSize(410, 350);
		ScrollPane menusp = new ScrollPane();
		menusp.setPrefSize(420, 350);
		menusp.setContent(vBox);

		Label title = new Label("Student Situation");
		title.setPadding(new Insets(10, 0, 0, 85));
		title.setFont(Font.font("Courier New", FontWeight.BOLD, 24));
		title.setTextFill(Color.web("728C9A"));

		Label student = new Label("Student:");
		student.setPadding(new Insets(50, 0, 0, 20));
		student.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		student.setTextFill(Color.web("728C9A"));

		Label nameStudent = new Label(stud.getName());
		nameStudent.setPadding(new Insets(-20, 0, 0, 100));
		nameStudent.setWrapText(true);
		nameStudent.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
		nameStudent.setStyle("-fx-text-fill: linear-gradient(orange   , orangered  ) ;");

		vBox.getChildren().add(title);
		vBox.getChildren().add(student);
		vBox.getChildren().add(nameStudent);

		for(int i = 0; i <stud.getSubjects().size(); i++){
			Label sbj = new Label("Subject:");
			sbj.setPadding(new Insets(20, 0, 0, 20));
			sbj.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
			sbj.setTextFill(Color.web("728C9A"));

			Label nameSbj = new Label(stud.getSubjects().get(i).getName());
			nameSbj.setPadding(new Insets(-20, 0, 0, 110));
			nameSbj.setWrapText(true);
			nameSbj.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
			nameSbj.setStyle("-fx-text-fill: linear-gradient(orange   , orangered  ) ;");

			Label teacher = new Label("Teacher:");
			teacher.setPadding(new Insets(20, 0, 0, 20));
			teacher.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
			teacher.setTextFill(Color.web("728C9A"));

			Label nameTeacher = new Label(stud.getSubjects().get(i).getNameTeacher());
			nameTeacher.setPadding(new Insets(-20, 0, 0, 110));
			nameTeacher.setWrapText(true);
			nameTeacher.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
			nameTeacher.setStyle("-fx-text-fill: linear-gradient(orange   , orangered  ) ;");

			Label marks = new Label("Marks:");
			marks.setPadding(new Insets(20, 0, 0, 20));
			marks.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
			marks.setTextFill(Color.web("728C9A"));

			VBox hbox1 = new VBox();
			hbox1.setPadding(new Insets(-39, 0, 0, 70));

			for (int j = 0; j < stud.getSubjects().get(i).getMarks().size(); j++) {
				Label mark = new Label(stud.getSubjects().get(i).getMarks().get(j).toString());
				mark.setPadding(new Insets(20, 0, 0, 20));
				mark.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
				mark.setStyle("-fx-text-fill: linear-gradient(orange   , orangered  ) ;");
				hbox1.getChildren().add(mark);

			}

			Label absences = new Label("Absences:");
			absences.setPadding(new Insets(20, 0, 0, 20));
			absences.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
			absences.setTextFill(Color.web("728C9A"));

			VBox hbox2 = new VBox();
			hbox2.setPadding(new Insets(-39, 0, 0, 100));
			for (int j = 0; j < stud.getSubjects().get(i).getAbsences().size(); j++) {
				Label mark = new Label(stud.getSubjects().get(i).getAbsences().get(j).toString());
				mark.setPadding(new Insets(20, 0, 0, 20));
				mark.setFont(Font.font("Courier New", FontWeight.BOLD, 16));
				mark.setStyle("-fx-text-fill: linear-gradient(orange   , orangered  ) ;");
				hbox2.getChildren().add(mark);

			}

			vBox.getChildren().add(sbj);
			vBox.getChildren().add(nameSbj);
			vBox.getChildren().add(teacher);
			vBox.getChildren().add(nameTeacher);
			vBox.getChildren().add(marks);
			vBox.getChildren().add(hbox1);
			vBox.getChildren().add(absences);
			vBox.getChildren().add(hbox2);

		}




		vBox.setId("vbox2");

		// Adding BorderPane to the scene and loading CSS
		Scene scene = new Scene(menusp);
		scene.getStylesheets()
				.add(getClass().getClassLoader().getResource("client/resources/gui.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.setTitle("Situation");
		primaryStage.setResizable(false);
		primaryStage.show();
	}

}
