package no.hvl.dat250.rest.todos;

import java.util.Objects;
import java.util.Random;

import com.google.gson.Gson;

public class Todo {
    private final Integer id;
    private final String summary;
    private final String description;

    public Todo(Integer id, String summary, String description) {
        this.id = id;
        this.summary = summary;
        this.description = description;
    }

    public Todo(String summary, String description) {
        this(null, summary, description);
    }

    public Todo () {
        this.id = new Random().nextInt(1000000);
        this.summary = "";
        this.description = "";
    }

    /**
     * ID might be null!
     */
    public Integer getId() {
        return id;
    }

    public String getSummary() {
        return summary;
    }

    public String getDescription() {
        return description;
    }

    // Do not change equals and hashcode!

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return Objects.equals(id, todo.id) && Objects.equals(summary, todo.summary) && Objects.equals(description, todo.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, summary, description);
    }

    @Override
    public String toString() {
        return "Todo [summary=" + summary + ", description=" + description
                + "]";
    }
    public Object toJson() {
        Gson gson = new Gson();
        Object jsonObject = gson.toJson(this);
        return jsonObject;
    }

}
