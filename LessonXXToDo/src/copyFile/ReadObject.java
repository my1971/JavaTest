package copyFile;

import toDo.toDo.model.Task;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ReadObject {
    public static void main(String[] args) {

        try {
            //FileOutputStream fos = new FileOutputStream("Lesson39/src/practice/todo_appl/model/tasks.bin");
            FileInputStream fis = new FileInputStream("tasks.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);

            Task[] task = new Task[ois.readInt()];
            for (int i = 0; i < task.length; i++) {
                task[i] = (Task) ois.readObject();
                System.out.println(task[i]);
            }
            ois.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
