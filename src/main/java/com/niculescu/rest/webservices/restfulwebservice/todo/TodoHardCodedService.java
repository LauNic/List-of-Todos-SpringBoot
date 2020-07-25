package com.niculescu.rest.webservices.restfulwebservice.todo;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class TodoHardCodedService {

    private static List<Todo> todos = new ArrayList<>();

    static {
        todos.add(new Todo(0,"teamlead", "Learn French Fast", new Date(), false));
        todos.add(new Todo(1,"teamlead", "Learn Spring Boot", new Date(), false));
        todos.add(new Todo(2,"teamlead", "Become Angular Expert", new Date(), false));
        todos.add(new Todo(3,"teamlead", "Learn Docker Kubernetes", new Date(), false));
        todos.add(new Todo(4,"teamlead", "Learn Microservices Best Architecture", new Date(), false));
        todos.add(new Todo(5,"teamlead", "Learn Cloud tool", new Date(), false));
        todos.add(new Todo(6,"teamlead", "Learn last Java version", new Date(), false));
        todos.add(new Todo(7,"teamlead", "Visit India", new Date(), true));

        todos.add(new Todo(8,"analyst", "Learn Spring Boot", new Date(), false));
        todos.add(new Todo(9,"analyst", "Become Angular Expert", new Date(), false));
        todos.add(new Todo(10,"analyst", "Learn Docker Kubernetes", new Date(), false));

        todos.add(new Todo(11,"lau", "Learn Spring Boot", new Date(), false));
        todos.add(new Todo(12,"lau", "Become Angular Expert", new Date(), false));
        todos.add(new Todo(13,"lau", "Learn Docker Kubernetes", new Date(), false));

    }

    public List<Todo> findAll(String userName) {

        Predicate<Todo> byUserName = todo -> todo.getUserName().equals(userName);
        return todos.stream().filter(byUserName).collect(Collectors.toList());
    }

    public Todo findById(long id) {

        Predicate<Todo> byId = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(byId).findAny().orElse(null);
        return todo;
    }

    public Todo editTodo(long id, Todo todoIn) {

        todoIn.setId(id);
        Todo todo = findById(id);
        int index = todos.indexOf(todo);

        if(index > -1) {
            todos.set(index, todoIn);
            return findById(id);
        }
        else {
            return null;
        }
    }

    public Todo createTodo(Todo todoIn) {

        long newId = getNewId();
        todoIn.setId(newId);
        boolean created = todos.add(todoIn);

        if(created) {
            return findById(newId);
        }
        else {
            return null;
        }
    }

    private long getNewId(){

        Todo maxIdTodo = todos.stream().max(Comparator.comparing(Todo::getId)).orElseThrow(NoSuchElementException::new);
        return maxIdTodo.getId()+1;
    }

    public boolean deleteByIdAndUserName(String userName, long id) {

        List<Todo> todosToRem = findByIdAndUserName(userName, id);

        boolean isRemoved = todos.removeAll(todosToRem);
//        System.out.println("isRemoved:" + isRemoved);
        return isRemoved;
    }

    private List<Todo> findByIdAndUserName(String userName, long id) {

        Predicate<Todo> byId = todo -> todo.getId() == id;
        Predicate<Todo> byUserName = todo -> todo.getUserName().equals(userName);

        List<Todo> filteredTodos = todos.stream().filter(byId.and(byUserName)).collect(Collectors.toList());

//        System.out.println("filteredTodos:" + filteredTodos);

        return filteredTodos;
    }

}
