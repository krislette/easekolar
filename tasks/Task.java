package easekolar.tasks;

public class Task {

    private String strTaskName;
    private String strTaskDescription;

    public Task(String strTaskName, String strTaskDescription) {
        this.strTaskName = strTaskName;
        this.strTaskDescription = strTaskDescription;
    }

    public String getTaskName() {
        return strTaskName;
    }

    public String getTaskDescription() {
        return strTaskDescription;
    }

}