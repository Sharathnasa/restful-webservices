package com.fxpi.rest.webservices.restfulwebservices.Controllers;

import com.fxpi.rest.webservices.restfulwebservices.Beans.Users;
import com.fxpi.rest.webservices.restfulwebservices.Dao.UserDaoService;
import com.fxpi.rest.webservices.restfulwebservices.Exceptions.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserDaoService userDaoService;

    @GetMapping("/users")
    public List<Users> retrieveAllUsers() {
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public Users getUserById(@PathVariable int id) throws NoSuchFieldException {
        Users users = userDaoService.findById(id);
        if (users == null) {
            throw new UserNotFoundException("id " + id);
        }
        return users;
    }

    @PostMapping("/users/save")
    public ResponseEntity<Object> createUser(@RequestBody Users users) {
        Users savedUser = userDaoService.saveUser(users);

        // this is the how we can form the new URI from the current request
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id) throws NoSuchFieldException {
        Users users = userDaoService.deleteById(id);
        if (users == null) {
            throw new UserNotFoundException("id " + id);
        }
    }
}
