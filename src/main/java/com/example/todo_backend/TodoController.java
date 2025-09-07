package main.java.com.example.todo_backend;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/todos")
@CrossOrigin(origins = "http://localhost:5173") // allow React dev server
public class TodoController {

    private Map<Long, Todo> store = new ConcurrentHashMap<>();
    private AtomicLong seq = new AtomicLong(1L);

    @GetMapping
    public Collection<Todo> list() {
        return store.values();
    }

    @PostMapping
    public Todo create(@RequestBody Todo todo) {
        Long id = seq.getAndIncrement();
        todo.setId(id);
        store.put(id, todo);
        return todo;
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        store.remove(id);
    }
}
