package no.hvl.dat250.rest.todos;

import static spark.Spark.*;

import com.google.gson.Gson;
/**
 * Rest-Endpoint.
 */
import static spark.Spark.after;
import static spark.Spark.get;
import static spark.Spark.port;
import static spark.Spark.put;
import static spark.Spark.post;
import static spark.Spark.delete;


/**
 * Hello world!
 */
import com.google.gson.Gson;
import static spark.Spark.*;

public class TodoAPI {

    static TodoList todolist = new TodoList();
    public static void main(String[] args) {

        if (args.length > 0)
            port(Integer.parseInt(args[0]));
        else
            port(8080);

        after((req, res) -> {
            res.type("application/json");
        });

        get("/todos", (req, res) -> new Gson().toJsonTree(todolist.getTodos()));
        get("/todos/:id", (req, res) -> {
            try {
                Long.parseLong((req.params(":id")));
                Todo todo = todolist.getTodo(req.params(":id"));
                if (todo != null)
                    return new Gson().toJson(todo);
                else
                    return "Todo with the id  \"9999\" not found!";
            }
            catch(NumberFormatException e) {
                return "The id \"notANumber\" is not a number!";
            }
        });
                ;
        delete("/todos/:id", (req, res) -> {
            try {
                Long.parseLong((req.params(":id")));
                Todo todo = todolist.getTodo(req.params(":id"));
                todolist.deleteTodo(todo.getId());
                if (todo != null)
                    return new Gson().toJson(todo);
                else
                    return "Todo with the id  \"9999\" not found!";
            }
            catch(NumberFormatException e) {
                return "The id \"notANumber\" is not a number!";
            }
        });
        post("/todos", (req, res) -> {
            Todo todo = new Gson().fromJson(req.body(), Todo.class);
            todolist.addTodo(todo);
            return todo.toJson();
        });
        put("/todos/:id", (req, res) -> {
            try {
                Long.parseLong((req.params(":id")));
                Todo oldTodo = todolist.getTodo(req.params(":id"));
                Todo newTodo = new Gson().fromJson(req.body(), Todo.class);
                todolist.deleteTodo(oldTodo.getId());
                todolist.addTodo(newTodo);
                if (oldTodo != null)
                    return new Gson().toJson(newTodo);
                else
                    return "Todo with the id  \"9999\" not found!";
            }
            catch(NumberFormatException e) {
                return "The id \"notANumber\" is not a number!";
            }
        });
    }

}
