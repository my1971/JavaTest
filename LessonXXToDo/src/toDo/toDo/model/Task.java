package toDo.toDo.model;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

public class Task implements Serializable, Comparable<Task> { // для сортировки нужен implements Comparable<"имя класса">
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

    public LocalDate getDateStart() {
        return dateStart;
    }

    public void setDateStart(LocalDate dateStart) {
        this.dateStart = dateStart;
    }

    public LocalDate getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(LocalDate dateFinish) {
        this.dateFinish = dateFinish;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public boolean equals(Object o) { // для сравнения полей
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int compareTo(Task o) { // при сортирвки положительный возврат меняет местами
        return this.id - o.id; // естественная сортировка по ID_ от меньшего к большему
    }
}