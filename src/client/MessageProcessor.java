package client;

import javafx.application.Platform;
import javafx.stage.Stage;
import server.Student;
import server.Subject;

import java.util.ArrayList;

/**
 * Created by teo on 10.05.2017.
 */
public class MessageProcessor {
    private Client client;
    private GUI gui;
    private MessageProcessor currentProcessor;

    public MessageProcessor(Client client) {
        this.client = client;
        currentProcessor = this;
        gui = new GUI(currentProcessor);
        gui.display();
    }

    public void process(Object messageReceived) {

        if (messageReceived instanceof ArrayList<?> && ((ArrayList) messageReceived).get(0) instanceof String)
        {
            processArrayListString((ArrayList<String>) messageReceived);
        }
        if (messageReceived instanceof String)
        {
            String message = messageReceived.toString();
            processString(message);
        }
        if (messageReceived instanceof Student)
        {
            processStudentAllSituation((Student) messageReceived);
            System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
        }

        if (messageReceived instanceof ArrayList<?> && ((ArrayList) messageReceived).get(0) instanceof String && ((ArrayList) messageReceived).get(1) instanceof Subject)
        {
            processStudentSituation((ArrayList<Object>) messageReceived);
        }

    }
    private void processArrayListString(ArrayList<String> message)
    {
        if(message.get(0).equals("Teacher"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.setTextNameTeacher(message.get(1));
                    gui.setTextNameSubject(message.get(2));
                    gui.setTextNumberStudents(message.get(3));
                }
            });
        }
        if(message.get(0).equals("Headmaster"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.setTextNameHeadmaster(message.get(1));
                    gui.setTextNumberTeachers(message.get(2));
                    gui.setTextTotalNumberStudents(message.get(3));
                    gui.setTextTotalNumberSubjects(message.get(4));

                }
            });
        }
        if(message.get(0).equals("Students"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    System.out.println("Afiseaza Students");
                    gui.setStudents(message);
                }
            });
        }
        if(message.get(0).equals("SeeTeachers"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.

                    gui.teacherMode(gui.getBp(),2,"Teachers");
                    gui.setStudents(message);

                }
            });

        }
        if(message.get(0).equals("SeeStudents"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.

                    gui.teacherMode(gui.getBp(),4,"Students");
                    gui.setStudents(message);

                }
            });

        }
        if(message.get(0).equals("addStudentInterface"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.

                    gui.teacherMode(gui.getBp(),7,"Students");
                    gui.setStudents(message);

                }
            });

        }
        if(message.get(0).equals("addSubjectInterface"))
        {
            gui.setSubjects(message);

        }
        if(message.get(0).equals("SituationOfStudents"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    System.out.print("lala");
                    gui.teacherMode(gui.getBp(),5,"Situation of Students");
                    gui.setStudents(message);

                }
            });

        }

        if(message.get(0).equals("SeeSubjects"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.

                    gui.teacherMode(gui.getBp(),3,"Subjects");
                    gui.setStudents(message);
                    System.out.println("buba3");
                }
            });

        }

        if(message.get(0).equals("AddedMark"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    System.out.println("AddedMark");
                    gui.setTextMessage("Note: successfully added mark to " + message.get(1));
                    gui.getMark().get(gui.getStudents().indexOf(message.get(1)) - 1).setText("");
                    gui.getData().get(gui.getStudents().indexOf(message.get(1)) - 1).setText("");

                }
            });
        }
        if(message.get(0).equals("AddedAbsence"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    System.out.println("AddedAbsence");
                    gui.setTextMessage("Note: successfully added absence to " + message.get(1));
                    gui.getData().get(gui.getStudents().indexOf(message.get(1)) - 1).setText("");
                }
            });
        }

    }

    private void processString(String message)
    {
        if(message.equals("DeclineLogin"))
        {
            gui.setPf("");
            gui.setTxtUserName("");
        }
        if(message.equals("Back"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.headmasterMode(gui.getBp());
                }
            });

        }

        if(message.equals("SeeImportExport"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.teacherMode(gui.getBp(),6,"Import/Export Students");
                }
            });

        }

        if(message.equals("Note: wrong date or invalid date format (dd/MM/yyyy)"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.setTextMessage("Note: wrong date or invalid date format (dd/MM/yyyy)");
                }
            });

        }
        if(message.equals("AcceptLoginTeacher"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.teacherMode(gui.getBp(),1,"Students");
                }
            });

        }
        if(message.equals("AcceptLoginHeadmaster"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.headmasterMode(gui.getBp());

                }
            });

        }
        if(message.equals("unsuccessfully added mark"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.setTextMessage("Note: unsuccessfully added mark");
                }
            });

        }

        if(message.equals("Logout"))
        {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    // Update UI here.
                    gui.loginScreen(gui.getBp());
                    if(gui.getStages() != null){

                        while(!gui.getStages().isEmpty()){
                            Stage stage = gui.getStages().remove(gui.getStages().size() - 1);
                            stage.close();
                        }
                    }
                }
            });

        }



    }

    private void processStudentSituation(ArrayList<Object> message)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                Stage stage = new Stage();
                gui.getStages().add(stage);

                gui.displayAddMark(stage,(String) message.get(0),(Subject) message.get(1));
            }
        });

    }

    private void processStudentAllSituation(Student message)
    {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                // Update UI here.
                Stage stage = new Stage();
                gui.getStages().add(stage);
                System.out.println(message.getName());

                for(int i = 0; i < message.getSubjects().size(); i++){
                    System.out.println(message.getSubjects().get(i).getName());
                    System.out.println(message.getSubjects().get(i).getMarks());
                    System.out.println(message.getSubjects().get(i).getAbsences());
                }


               gui.displayAddMark(stage,message);
            }
        });

    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
