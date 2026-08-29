package nyonbot.storage;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.Scanner;

import nyonbot.model.Deadline;
import nyonbot.model.Event;
import nyonbot.model.Task;
import nyonbot.model.TaskList;
import nyonbot.model.ToDo;

/**
 * Loads and saves tasks to a file using a delimiter-based text format
 */
public class Storage {
    private final String filePath;

    /**
     * Creates a storage object for the specified file path
     * 
     * @param filePath filepath of the save file
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Loads the tasks from the savefile
     * 
     * @return A <code>TaskList</code> of the Tasks stored in the file
     * @throws IOException if the file cannot be read
     */
    public TaskList load() throws IOException {
        File file = new File(filePath);
        TaskList list = new TaskList();
        if (!file.exists()) {
            return list;
        }
        try (Scanner fileReader = new Scanner(file)) {
            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                String[] params = line.split("\\|", -1);
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
                        LocalDateTime deadlineBy = LocalDateTime.parse(params[3]);
                        Deadline deadline = new Deadline(params[1], deadlineBy);
                        if (params[2].equals("true")) {
                            deadline.completeTask();
                        }
                        list.add(deadline);
                        break;
                    case ("EVENT"):
                        Event event = new Event(params[1],
                                LocalDateTime.parse(params[3]),
                                LocalDateTime.parse(params[4]));
                        if (params[2].equals("true")) {
                            event.completeTask();
                        }
                        list.add(event);
                        break;
                }
            }
            fileReader.close();
        }
        return list;
    }

    /**
     * Returns the storage representation of the task to be stored
     * 
     * @param task the <code>Task</code> to be stored
     * @return the storage representation of the task
     */
    private String taskParser(Task task) {
        if (task instanceof ToDo todo) {
            return String.format("TODO|%s|%s", todo.getName(), todo.isDone());
        }
        if (task instanceof Deadline deadline) {
            return String.format("DEADLINE|%s|%s|%s",
                    deadline.getName(),
                    deadline.isDone(),
                    formatDate(deadline.getDeadline()));
        }
        if (task instanceof Event event) {
            LocalDateTime[] eventTime = event.getEventTimes();
            return String.format("EVENT|%s|%s|%s|%s",
                    event.getName(),
                    event.isDone(),
                    formatDate(eventTime[0]),
                    formatDate(eventTime[1]));
        }
        if (task instanceof Task) {
            return String.format("TASK|%s|%s", task.getName(), task.isDone());
        }
        return "";
    }

    /**
     * Saves a list to the filepath of the Storage object.
     * Will override contents.
     * 
     * @param list the list to be saved
     * @throws IOException if file cannot be created, read or found
     */
    public void save(TaskList list) throws IOException {
        File saveFile = new File(filePath);

        if (!saveFile.exists()) {
            Path path = Path.of(filePath);
            if (path.getParent() != null) {
                Files.createDirectories(path.getParent());
            }
            saveFile.createNewFile();
        }
        try (FileWriter fileWriter = new FileWriter(saveFile);) {
            StringBuilder sb = new StringBuilder();
            for (Task task : list) {
                sb.append(taskParser(task));
                sb.append("\n");
            }
            fileWriter.write(sb.toString());
            fileWriter.close();
        }
    }

    private String formatDate(LocalDateTime dateTime) {
        return dateTime.toString();
    }

    /**
     * Removes all tasks from the file
     * 
     * @throws IOException if the file cannot be read or written to
     */
    public void wipe() throws IOException {
        save(new TaskList());
    }
}
