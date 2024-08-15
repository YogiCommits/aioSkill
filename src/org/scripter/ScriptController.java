package org.scripter;

import org.data.Methods;
import simple.robot.api.ClientContext;

import java.util.ArrayList;
import java.util.HashMap;

public class ScriptController implements Runnable {
    private static HashMap<String, Task> tasks;
    private static ArrayList<Task> backgroundTasks;
    private static Task currentTask;
    private ClientContext c;
    private String n;
    Methods m;

    public ScriptController() {
        c = ClientContext.instance();
        n = c.user.forumsName();
        System.out.println("Loaded the Script Controller... Welcome " + n + "!");
        tasks = new HashMap<>();
        backgroundTasks = new ArrayList<>();
        m = new Methods();
    }

    public void addTask(String name, Task task) {
        tasks.put(name.toLowerCase(), task);
        System.out.println("{ScriptController} - Added Task: " + name);
    }

    public void setTask(String name) {
        String curr = "";
        try {
            curr = currentTask.DebugTaskDescription();
        } catch (NullPointerException e) {
            curr = "Null";
        }
        System.out.println("{ScriptController} - Setting Task  [" + curr + "] --> [" + name + "]");
        currentTask = tasks.get(name.toLowerCase());

    }

    public void runTask() {
        String curr = "";
        try {
            curr = currentTask.DebugTaskDescription();
        } catch (NullPointerException e) {
            curr = "Null";
        }
        System.out.println("{ScriptController} - Running Task [" + curr + "]");
        currentTask.runtime();
    }

    public Task getTask() {
        return currentTask;
    }

    public Task getTask(String name) {
        return tasks.get(name);
    }

    public InterruptableTask getTask(int r, String name) {
        return (InterruptableTask) tasks.get(name);
    }

    public void addBGTask(Task task) {
        backgroundTasks.add(task);
    }

    @Override
    public void run() {
        String curr = "";
        try {
            curr = currentTask.DebugTaskDescription();
        } catch (NullPointerException e) {
            curr = "Null";
        }
        System.out.println("{ScriptController} - Running Task [" + curr + "]");
        currentTask.runtime();
        for (Task t : backgroundTasks) {
            System.out.println("{ScriptController} - Running Background Task [" + t.DebugTaskDescription() + "]");
            t.run();
        }
    }

    @SuppressWarnings("unlikely-arg-type")
    public void clean() {
        currentTask = null;
        for (Task t : tasks.values()) {
            tasks.remove(t);
        }
        tasks = null;

    }
}
