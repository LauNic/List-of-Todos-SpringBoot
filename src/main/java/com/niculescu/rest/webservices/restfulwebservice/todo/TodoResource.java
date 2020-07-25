package com.niculescu.rest.webservices.restfulwebservice.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class TodoResource {

    @Autowired
    private TodoHardCodedService todoService;

    @GetMapping("/users/{userName}/todos")
    public List<Todo> getAllTodos(@PathVariable String userName) {
        return todoService.findAll(userName);
    }

    @GetMapping("/users/todos/{id}")
    public Todo getTodoById(@PathVariable long id) {
        return todoService.findById(id);
    }

    @DeleteMapping("/users/{userName}/todos/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String userName, @PathVariable long id) {
        boolean todosRemoved = todoService.deleteByIdAndUserName(userName, id);

        if (todosRemoved) {
            return ResponseEntity.noContent().build();
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/users/todos/{id}")
    public ResponseEntity<Todo> editTodo(@PathVariable long id, @RequestBody Todo todo) {

        Todo editedTodo = todoService.editTodo(id, todo);

        if (editedTodo != null) {
            return new ResponseEntity<Todo>(editedTodo, HttpStatus.OK);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/users/{userName}/todos")
    public ResponseEntity<Void> createTodo(@PathVariable String userName, @RequestBody Todo todo) {

        Todo createdTodo = todoService.createTodo(todo);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                                            .buildAndExpand(createdTodo.getId()).toUri();

        if (createdTodo != null) {
            return ResponseEntity.created(uri).build();
        }
        else {
            return ResponseEntity.badRequest().build();
        }
    }

}
