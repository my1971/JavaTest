package copyFile;

import java.io.*;
import java.time.LocalDate;
//import java.util.Objects;
//22222222222222222222222
public class ObjectFileBin {
    public static void main(String[] args) throws IOException {

        String filePath  = "LessonXXToDo/src/copyFile/tasks.bin";
      //  String filePath  = "LessonXXToDo/src/toDo/toDo/copyFile/tasks.bin";
        Task[] task;
        task = new Task[3];
        for (int i = 0; i <= 2; i++) {
            task[i] = new Task(1000 + i, "DoOne" + i, LocalDate.now(), LocalDate.now());
        }

        WriteFile(task, filePath);
        ReadFile(filePath);
    }

    public static void WriteFile(Task[] tasks, String filePath) throws IOException{
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))){
            oos.writeInt(tasks.length);
            oos.writeObject(tasks);
            System.out.println("Список успешно сериализован!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void ReadFile(String filePath) throws IOException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))){
            Task[] task = new Task[ois.readInt()];
            task = (Task[]) ois.readObject();
            System.out.println("Список успешно десериализован!");
            for (Task value : task) {System.out.println(value);}
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }



    public static class Task implements Serializable, Comparable<Task> { // для сортировки нужен implements Comparable<"имя класса">
        private int id; //индефикатор
        private String task;
        private LocalDate dateStart;
        private LocalDate dateFinish;

        public Task(int id, String task, LocalDate dateStart, LocalDate dateFinish) {
            //по имени совпадает с именем класса, ничего не возвращает и не void
            this.id = id;
            this.task = task;
            this.dateStart = dateStart;
            this.dateFinish = dateFinish;
        }

        public String getTask() {
            return task;
        }

        public void setTask(String task) {
            this.task = task;
        }

        @Override
        public String toString() {
            return "Task " + id + ") " + task + ", Date Start = " + dateStart + ", Date Finish = " + dateFinish;
        }

//        @Override
//        public boolean equals(Object o) { // для сравнения полей
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Task task = (Task) o;
//            return id == task.id;
//        }

//        @Override
//        public int hashCode() {
//            return Objects.hash(id);
//        }

        @Override
        public int compareTo(Task o) { // при сортирвки положительный возврат меняет местами
            return this.id - o.id; // естественная сортировка по ID_ от меньшего к большему
        }
    }
}