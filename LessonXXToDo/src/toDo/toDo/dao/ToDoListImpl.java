package toDo.toDo.dao;

import toDo.toDo.model.Task;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class ToDoListImpl implements ToDoList {

    // private Task task;
    int quantity = 0; // = size
    private Task[] tasks;

    public ToDoListImpl(int capacity) {
        tasks = new Task[capacity];
    }

    @Override
    public boolean addTask() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input task for add of record : ");
        String taskInput = scanner.nextLine();
        System.out.println(" ");
        if (taskInput != null) {
            Task[] tasksCopy = Arrays.copyOf(tasks, tasks.length + 1);
            // tasksCopy[0] = NULL
            tasksCopy[quantity++] = new Task(quantity + 100, taskInput, inputDate("Start Date for add of record : "),
                    inputDate("Finish Date for add of record : "));
            tasks = tasksCopy;
            return true;
        }
        return false;
    }

    public boolean editTask(int id) {
        for (int i = 0; i < quantity; i++) {
            // find by id
            if (tasks[i].getId() == id) {
                System.out.println("Edit line : " + tasks[i]);
                Scanner scanner = new Scanner(System.in);
                System.out.print("Input task for edit of record : "); // edit Name
                String taskEdit = scanner.nextLine();
                System.out.println(" ");
                if (taskEdit == null || taskEdit.trim() == "") {
                } else {
                    tasks[i].setTask(taskEdit);
                    tasks[i].setDateStart(inputDate("Start Date for edit of record : "));
                    tasks[i].setDateFinish(inputDate("Finish Date for edit of record : "));
                    return true;
                }
            }
        }
        return false;
    }

    public LocalDate inputDate(String title) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input " + title);
        String stringDate = scanner.next();
        System.out.println(stringDate + title);
        System.out.println(" ");
        return (stringDate == null || stringDate.trim() == "") ? (LocalDate.now()) : (LocalDate.parse(stringDate,
                DateTimeFormatter.ofPattern("[yyyy/M/d][yyyy.M.d][yyyy-M-d][yyyy:M:d][d-M-yy][d.M.yy][d/M/yy][d:M:yy]")));
    }

    @Override
    public Task removeTask(int id) {
        // find by id then remove, quantity--
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i].getId() == id) {
                Task remove = tasks[i];
                System.arraycopy(tasks, i + 1, tasks, i, (--quantity) - i); //quantity - i - 1
                //tasks[quantity] = null; неиспользую. потомучто работаем без Capacity
                Task[] tasksCopy = Arrays.copyOf(tasks, tasks.length - 1);
                tasks = tasksCopy;
                return remove;
            }
        }
        return null;
    }

    @Override
    public Task findTask(int id) {
        // find by id
        for (int i = 0; i < quantity; i++) {
            if (tasks[i].getId() == id) {
                return tasks[i];
            }
        }
        return null;
    }

    public boolean sort() {
        clearMetod();
        System.out.println("============================  Menu Sort ==================================");
        System.out.println(" 1 - Sort by name | 2 - Sort by date of start | 3 - Sort by date of Finish");
        System.out.println("=============================================================================");
        Scanner scanner = new Scanner(System.in);
        System.out.print("Input Menu number to execute: ");
        int taskEdit = scanner.nextInt();
        if (taskEdit > 0 & taskEdit < 4) {
            sortTasks(taskEdit);
            return true;
        }
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~  Wrong choice!!!  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        return false;
    }

    public void sortTasks(int field) { //метод сортировки сотрудников
        Comparator<Task> employeeComparator = new Comparator<Task>() {
            @Override
            public int compare(Task o1, Task o2) {
                // сравнение по полю experience, которое имеет тип int, если в поле null, то возвращает 0.
                switch (field) {
                    case 1:
                        return (o1 == null || o2 == null) ? (0) : (o1.getTask().compareTo(o2.getTask())); //name
                    case 2:
                        return (o1 == null || o2 == null) ? (0) : (o1.getDateStart().compareTo(o2.getDateStart())); //Date Start
                    case 3:
                        return (o1 == null || o2 == null) ? (0) : (o1.getDateFinish().compareTo(o2.getDateFinish())); //Date Finish
                }
                return 0;
            }
        };
        Arrays.sort(tasks, employeeComparator);
    }

    @Override
    public void printTasks() {
        clearMetod();
        // for loop, print tasks
        System.out.println("============================== List of Tasks ================================");
        for (int i = 0; i < tasks.length; i++) {
            System.out.println(tasks[i]);
        }
        System.out.println("=============================================================================");

    }

    @Override
    public int quantity() {
        // return quantity;
        return quantity;
    }

    public void clearMetod() {
        for (int i = 0; i < 1000; i++) {
            System.out.println("");
        }
    }


    public void ReadTasks() {
        try {
            FileInputStream fis = new FileInputStream("C:\\Share\\Arbeit\\inteliJ\\AIT\\JAVA\\LessonXXToDo\\src\\toDo\\toDo\\model\\tasks.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Task[] tasksInput = new Task[ois.readInt()];
            for (int i = 0; i < tasksInput.length; i++) {
                tasksInput[i] = (Task) ois.readObject();
            }
            tasks = tasksInput;
            quantity = tasksInput.length;
            ois.close();
        } catch (IOException e) {
            System.out.println("Error mit File");
            //throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            System.out.println("Error mit File");
            //throw new RuntimeException(e);
        }
    }

    public void WriteObject() {
        try {
            //FileOutputStream fos = new FileOutputStream("Lesson39/src/practice/todo_appl/model/tasks.bin");
            FileOutputStream fos = new FileOutputStream("C:\\Share\\Arbeit\\inteliJ\\AIT\\JAVA\\LessonXXToDo\\src\\toDo\\toDo\\model\\tasks.bin");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeInt(tasks.length);
            for (int i = 0; i < tasks.length; i++) {
                oos.writeObject(tasks[i]);
            }
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}