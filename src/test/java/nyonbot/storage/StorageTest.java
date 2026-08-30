package nyonbot.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import nyonbot.model.Deadline;
import nyonbot.model.Event;
import nyonbot.model.Task;
import nyonbot.model.TaskList;
import nyonbot.model.ToDo;

class StorageTest {
    @TempDir
    Path tempDir;

    @Test
    void load_missingFile_returnsEmptyTaskList() throws IOException {
        Storage storage = new Storage(tempDir.resolve("missing.txt").toString());

        assertEquals(0, storage.load().size());
    }

    @Test
    void saveAndLoad_allTaskTypes_preservesTaskData() throws IOException {
        Path saveFile = tempDir.resolve("nested").resolve("tasks.txt");
        Storage storage = new Storage(saveFile.toString());
        TaskList original = new TaskList();
        Task task = new Task("plain");
        ToDo todo = new ToDo("todo");
        todo.completeTask();
        Deadline deadline = new Deadline(
                "deadline", LocalDateTime.of(2026, 9, 5, 9, 0));
        Event event = new Event(
                "event",
                LocalDateTime.of(2026, 9, 5, 10, 0),
                LocalDateTime.of(2026, 9, 5, 11, 0));
        original.add(task);
        original.add(todo);
        original.add(deadline);
        original.add(event);

        storage.save(original);
        TaskList loaded = storage.load();

        assertTrue(Files.exists(saveFile));
        assertEquals(4, loaded.size());
        assertEquals("plain", loaded.get(0).getName());
        assertInstanceOf(ToDo.class, loaded.get(1));
        assertTrue(loaded.get(1).isDone());

        Deadline loadedDeadline = assertInstanceOf(Deadline.class, loaded.get(2));
        assertEquals(deadline.getDeadline(), loadedDeadline.getDeadline());

        Event loadedEvent = assertInstanceOf(Event.class, loaded.get(3));
        assertEquals(event.getEventTimes()[0], loadedEvent.getEventTimes()[0]);
        assertEquals(event.getEventTimes()[1], loadedEvent.getEventTimes()[1]);
    }

    @Test
    void save_existingFile_overwritesPreviousContents() throws IOException {
        Path saveFile = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(saveFile.toString());
        TaskList first = new TaskList();
        first.add(new Task("old"));
        storage.save(first);

        TaskList replacement = new TaskList();
        replacement.add(new Task("new"));
        storage.save(replacement);

        TaskList loaded = storage.load();
        assertEquals(1, loaded.size());
        assertEquals("new", loaded.get(0).getName());
    }

    @Test
    void wipe_existingFile_removesAllStoredTasks() throws IOException {
        Path saveFile = tempDir.resolve("tasks.txt");
        Storage storage = new Storage(saveFile.toString());
        TaskList tasks = new TaskList();
        tasks.add(new Task("task"));
        storage.save(tasks);

        storage.wipe();

        assertEquals(0, storage.load().size());
        assertFalse(Files.readString(saveFile).contains("task"));
    }
}
