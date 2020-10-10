package sample;
//Brandon Godinez
//This program displays a box with a textField for the user to input a string into, then saves it into an arrayList that
//can continue definitely by means of a button system that clears the previous input when the addEvent button is activated.
//When the showEvent button is activated, it displays all of the inputted strings in a gridpane that is also defined infinitely
//This lab was hard!!!

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javafx.geometry.Insets;
import javafx.geometry.Pos;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;

public class NoteTracker extends Application {
    // DECLARE AND CONSTRUCT ARRAYLIST OF LABELS
    private TextField noteTextField = new TextField(); //takes the userinput on the UI
    private GridPane notesGrid = new GridPane(); // the grid
    public List<String> notes = new ArrayList<>(); //array list

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        //this part sets up the formatting and UI for the program
        //and the buttons to interact with the program
        Label noteLabel = new Label("New Note: ");
        HBox noteHBox = new HBox(5, noteLabel, noteTextField);
        noteHBox.setAlignment(Pos.CENTER);
        noteHBox.setPadding(new Insets(10, 0, 0, 0));

        Button addNote = new Button("Add a Note");
        Button showNotes = new Button("Show All Notes");

        notesGrid.setAlignment(Pos.CENTER);
        notesGrid.setHgap(10);
        notesGrid.setVgap(20);
        addNote.setOnAction(new AddEvent());
        showNotes.setOnAction(new ShowEvent());
        VBox root = new VBox(10, noteHBox, addNote, showNotes, notesGrid);
        root.setAlignment(Pos.TOP_CENTER);
        Scene scene = new Scene(root, 500, 500);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Note Tracker");
        primaryStage.show();
    }

    private class AddEvent implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            notesGrid.getChildren().clear();  // clear the grid before adding to it
            Label noteBoxLabel = new Label(noteTextField.getText());
            notesGrid.add(noteBoxLabel, 0, 0); // add to grid, only display value user inputted at 0,0
            notes.add(noteTextField.getText());      // add value user inputted to the listArray
        }
    }

    private class ShowEvent implements EventHandler<ActionEvent> {
        public void handle(ActionEvent e) {
            Integer row = 0; // these two integers are the position indicators for the arraylist
            Integer col = 0;
            notesGrid.getChildren().clear();  // clear the grid before adding to it

            for (Integer i = 0; i < notes.size(); i++) {
                // if i % 3, means we have hit the end of row and we need to create a new row
                // the iterator method is also below
                if (i % 3 == 0) { // modulus is used here to break the row into the next one
                    row++; //moves to the next row
                    col = 0; //resets the position of the column back to 0 on the left to iterate through the row
                } else {
                    col++; //iterates through the row
                }
                // IMPLEMENT SHOWEVENT'S HANDLE METHOD
                Label noteBoxLabel = new Label(notes.get(i)); //retrieves i from arraylist to put into gridpane for display
                notesGrid.add(noteBoxLabel, col, row - 1); // add to grid, only display value user inputted at 0,0
            }
        }
    }
}