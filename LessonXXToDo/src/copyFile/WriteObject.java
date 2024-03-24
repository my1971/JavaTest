package copyFile;

import toDo.toDo.model.Task;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
//4444444444444444444444444444444444444444444
public class WriteObject {
    public static void main(String[] args) {
        Task[] task;
        task = new Task[4];
        task[0] = new Task(101, "Do one1", LocalDate.now(), LocalDate.now());
        task[1] = new Task(102, "Do one2", LocalDate.now(), LocalDate.now());
        task[2] = new Task(103, "Do one3", LocalDate.now(), LocalDate.now());
        task[3] = new Task(104, "Do one4", LocalDate.now(), LocalDate.now());
        try {
            //FileOutputStream fos = new FileOutputStream("Lesson39/src/practice/todo_appl/model/tasks.bin");
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("tasksNew.txt"));
            oos.writeInt(task.length);
            for (int i = 0; i < task.length; i++) {
                oos.writeObject(task[i]);
            }
            oos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
