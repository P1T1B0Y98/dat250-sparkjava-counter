package no.hvl.dat250.rest.todos;

import static spark.Spark.*;
import java.util.HashMap;
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
import spark.Spark;

import static spark.Spark.*;

public class TodoAPI {

    static TodoList todolist = new TodoList();
    public static void main(String[] args) {

        if (args.length > 0)
            port(Integer.parseInt(args[0]));
        else
            port(8080);

        options("/*",
                (request, response) -> {

                    String accessControlRequestHeaders = request
                            .headers("Access-Control-Request-Headers");
                    if (accessControlRequestHeaders != null) {
                        response.header("Access-Control-Allow-Headers",
                                accessControlRequestHeaders);
                    }

                    String accessControlRequestMethod = request
                            .headers("Access-Control-Request-Method");
                    if (accessControlRequestMethod != null) {
                        response.header("Access-Control-Allow-Methods",
                                accessControlRequestMethod);
                    }

                    return "OK";
                });

        before((req, res) -> res.header("Access-Control-Allow-Origin", "*"));

        after((req, res) -> {
            res.type("application/json");
            res.header("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS");
            res.header("Access-Control-Allow-Headers", "Content-Type,Authorization,X-Requested-With,Content-Length,Accept,Origin,");
            res.header("Access-Control-Allow-Credentials", "true");
        });

        get("/todos", (req, res) -> new Gson().toJsonTree(todolist.getTodos()));
        get("/todos/:id", (req, res) -> {
            try {
                Integer.parseInt((req.params(":id")));
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
                Integer.parseInt((req.params(":id")));
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
                Integer.parseInt((req.params(":id")));
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
