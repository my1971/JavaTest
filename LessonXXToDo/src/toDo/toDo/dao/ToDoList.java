package toDo.toDo.dao;

import toDo.toDo.model.Task;

public interface ToDoList {

    // add Task
    boolean addTask();

    //edit Task
    boolean editTask(int id);

    // remove Task
    Task removeTask(int id);

    // find Task
    Task findTask(int id);

    //Filters tasks
    boolean sort();

    // print list of Tasks
    void printTasks();

    // quantity of tasks
    int quantity();

    //создать массив из файла
    void ReadTasks();

    // записать в файл
    void WriteObject();

}