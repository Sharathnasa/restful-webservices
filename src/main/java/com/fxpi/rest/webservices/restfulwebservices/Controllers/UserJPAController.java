package com.fxpi.rest.webservices.restfulwebservices.Controllers;

import com.fxpi.rest.webservices.restfulwebservices.Beans.Posts;
import com.fxpi.rest.webservices.restfulwebservices.Beans.Users;
import com.fxpi.rest.webservices.restfulwebservices.Exceptions.UserNotFoundException;
import com.fxpi.rest.webservices.restfulwebservices.Repository.PostsRepository;
import com.fxpi.rest.webservices.restfulwebservices.Repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAController {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private PostsRepository postsRepository;

    @GetMapping("/jpa/users")
    public List<Users> retrieveAllUsers() {
        return usersRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<Users> getUserById(@PathVariable int id) throws NoSuchFieldException {
        Optional<Users> users = usersRepository.findById(id);
        if (!users.isPresent()) {
            throw new UserNotFoundException("id " + id);
        }
        // this is the implementation of hateoas to give link
        EntityModel<Users> usersEntityModel = new EntityModel<Users>(users.get());
        WebMvcLinkBuilder linkTo = WebMvcLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());
        usersEntityModel.add(linkTo.withRel("all-users"));
        return usersEntityModel;
    }

    @PostMapping("/jpa/users/save")
    public ResponseEntity<Object> createUser(@Valid @RequestBody Users users) {
        Users savedUser = usersRepository.save(users);

        // this is the how we can form the new URI from the current request
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) throws NoSuchFieldException {
        usersRepository.deleteById(id);
    }

    @GetMapping("/jpa/users/{id}/post")
    public List<Posts> retrieveAllUsersPost(@PathVariable int id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id " + id);
        }

        return userOptional.get().getPosts();
    }

    @PostMapping("/jpa/users/{id}/post")
    public ResponseEntity<Object> createPost(@PathVariable int id, @Valid @RequestBody Posts posts) {
        Optional<Users> userOptional = usersRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new UserNotFoundException("id " + id);
        }

        Users user= userOptional.get();
        posts.setUsers(user);

        postsRepository.save(posts);

        // this is the how we can form the new URI from the current request
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(posts.getId()).toUri();

        return ResponseEntity.created(location).build();

    }
}
