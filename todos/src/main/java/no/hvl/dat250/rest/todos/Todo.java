package no.hvl.dat250.rest.todos;

import java.util.Objects;
import java.util.UUID;
import java.util.Random;

import com.google.gson.Gson;

public class Todo {
    private final Long id;
    private final String summary;
    private final String description;

    public Todo(Long id, String summary, String description) {
        this.id = id;
        this.summary = summary;
        this.description = description;
    }

    public Todo(String summary, String description) {
        this(null, summary, description);
    }

    public Todo () {
        this.id = new Random().nextLong();
        this.summary = "";
        this.description = "";
    }

    /**
     * ID might be null!
     */
    public Long getId() {
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
