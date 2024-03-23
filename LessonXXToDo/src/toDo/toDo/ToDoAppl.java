package toDo.toDo;

import toDo.toDo.dao.ToDoListImpl;
import toDo.toDo.model.Menu;

import java.io.FileNotFoundException;
import java.util.Scanner;


public class ToDoAppl {
    public static void main(String[] args) throws FileNotFoundException {
        ToDoListImpl toDoList = new ToDoListImpl(0); // вызов конструктора
        toDoList.ReadTasks(); //Input Array from File
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            // greeting
            System.out.println("=============================================================================");
            System.out.println("Welcome to ToDo Application!");
            // print menu
            Menu.printMenu(); // Menu menu;  нет необходимости определять класс "enum"
            System.out.println(" ");
            // ask choice
            System.out.print("Input number of your choice : ");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1: //List
                    toDoList.printTasks();
                    break;
                case 2: //Add
                    toDoList.addTask();
                    break;
                case 3: //Edit
                    toDoList.editTask(inputID("Edit"));
                    break;
                case 4: //Find
                    System.out.println(toDoList.findTask(inputID("Find")));
                    break;
                case 5: //remuve
                    System.out.println("Record : " + toDoList.removeTask(inputID("Remuve")) + " - deleted");
                    break;
                case 6: //Filters
                    if (toDoList.sort()) toDoList.printTasks();
                    break;
                case 7: //Exit
                    loop = false;
                    toDoList.WriteObject();
                    System.out.println("======================= Exit Programm!  Good bay!!!   =======================");
                    break;
                default:
                    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~ Wrong choice!!! Try again ~~~~~~~~~~~~~~~~~~~~~~~~~");
                    break;
            }
        }
    }

    // execute
    public static int inputID(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input number ID for " + title + " of recort : ");
        int id = scanner.nextInt();
        System.out.println(" ");
        return id;
    }

}
