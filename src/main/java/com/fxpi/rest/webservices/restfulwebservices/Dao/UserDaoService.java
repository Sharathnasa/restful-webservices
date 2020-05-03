package com.fxpi.rest.webservices.restfulwebservices.Dao;

import com.fxpi.rest.webservices.restfulwebservices.Beans.Users;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserDaoService {
    private static List<Users> userList = new ArrayList<>();
    private static int usersCount = 3;

    static {
        userList.add(new Users(1, "Adam", new Date()));
        userList.add(new Users(2, "Eve", new Date()));
        userList.add(new Users(3, "Jack", new Date()));
    }

    public List<Users> findAll() {
        return userList;
    }

    public Users saveUser(Users users) {
        if (users.getId() == null) {
            users.setId(++usersCount);
        }
        userList.add(users);
        return users;
    }

    public Users findById(int id) throws NoSuchFieldException {
        return userList.stream().filter(user -> (user.getId() == id)).findAny().orElse(null);

    }

    public Users deleteById(int id) {
//        boolean newUserList = userList.stream().filter(user -> (user.getId() == id)).collect(Collectors.toList()).removeIf(users -> (users.getId() == id));
        Iterator<Users> usersIterator = userList.iterator();
        while (usersIterator.hasNext()) {
            Users user = usersIterator.next();
            if (user.getId() == id) {
                usersIterator.remove();
                return user;
            }
        }
        return null;
    }

}
