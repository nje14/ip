package nyonbot.storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import nyonbot.model.Deadline;
import nyonbot.model.Event;
import nyonbot.model.Task;
import nyonbot.model.ToDo;

public class Storage {
    private final Path filePath;

    public Storage(Path filePath) {
        this.filePath = filePath;
    }

    public List<Task> load() {
        File file = new File(filePath.toString());
        List<Task> list = new ArrayList<>();
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] params = line.split("|");
                switch (params[0]) {
                    case ("TASK"):
                        Task task = new Task(params[1]);
                        if (params[2].equals("true")) {
                            task.completeTask();
                        }
                        list.add(task);
                        break;
                    case ("TODO"):
                        ToDo todo = new ToDo(params[1]);
                        if (params[2].equals("true")) {
                            todo.completeTask();
                        }
                        list.add(todo);
                        break;
                    case ("DEADLINE"):
                        Deadline deadline = new Deadline(params[1], params[3]);
                        if (params[2].equals("true")) {
                            deadline.completeTask();
                        }
                        list.add(deadline);
                        break;
                    case ("EVENT"):
                        Event event = new Event(params[1], params[3], params[4]);
                        if (params[2].equals("true")) {
                            event.completeTask();
                        }
                        list.addLast(event);
                        break;
                }
            }
            fileReader.close();
        } catch (IOException e) {

        }
        return null;
    }

    private String taskParser(Task task) {
        if (task instanceof ToDo todo) {
            return String.format("TODO|%s|%s", todo.getName(), todo.isDone());
        }
        if (task instanceof Deadline deadline) {            
            return String.format("DEADLINE|%s|%s|%s", deadline.getName(), deadline.isDone(), deadline.getDeadline());
        }
        if (task instanceof Event event) {
            String[] eventTime = event.getEventTime();
            return String.format("EVENT|%s|%s|%s|%s", event.getName(), event.isDone(), eventTime[0], eventTime[1]);
        }
        if (task instanceof Task) {
            return String.format("TASK|%s|%s", task.getName(), task.isDone());
        }
        return "";
    }

    public void save(List<Task> list) {
        File saveFile = new File(filePath.toString());
        try (FileWriter fileWriter = new FileWriter(saveFile)) {
            StringBuilder sb = new StringBuilder();
            for (Task task: list) {
                sb.append(taskParser(task));
                sb.append("\n");
            }
            fileWriter.write(sb.toString());
            fileWriter.close();
        } catch (IOException e) {
                
        } 
    }
}
