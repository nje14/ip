package nyonbot.model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * TaskList
 */
public class TaskList implements Iterable<Task> {
    private final List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public boolean remove(Task task) {
        return tasks.remove(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public void clear() {
        tasks.clear();
    }

    public int size() {
        return tasks.size();
    }

    @Override
    public Iterator<Task> iterator() {
        return tasks.iterator();
    }
}
