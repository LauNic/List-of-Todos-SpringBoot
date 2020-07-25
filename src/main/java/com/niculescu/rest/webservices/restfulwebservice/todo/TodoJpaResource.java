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
public class TodoJpaResource {

    @Autowired
    private TodoHardCodedService todoService;

    @Autowired
    private TodoJpaRepository todoJpaRepository;

    @GetMapping("/jpa/users/{userName}/todos")
    public List<Todo> getAllTodos(@PathVariable String userName) {
        return todoJpaRepository.findByUserName(userName);
//        return todoService.findAll(userName);
    }

    @GetMapping("/jpa/users/todos/{id}")
    public Todo getTodoById(@PathVariable long id) {
        return todoJpaRepository.findById(id).get();
//        return todoService.findById(id);
    }

    @DeleteMapping("/jpa/users/{userName}/todos/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable String userName, @PathVariable long id) {
//        boolean todosRemoved = todoService.deleteByIdAndUserName(userName, id);
        todoJpaRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/jpa/users/todos/{id}")
    public ResponseEntity<Todo> editTodo(@PathVariable long id, @RequestBody Todo todo) {

        Todo editedTodo = todoJpaRepository.save(todo);
//        Todo editedTodo = todoService.editTodo(id, todo);

        if (editedTodo != null) {
            return new ResponseEntity<Todo>(editedTodo, HttpStatus.OK);
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/jpa/users/{userName}/todos")
    public ResponseEntity<Void> createTodo(@PathVariable String userName, @RequestBody Todo todo) {

//        Todo createdTodo = todoService.createTodo(todo);
        Todo createdTodo = todoJpaRepository.save(todo);

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
