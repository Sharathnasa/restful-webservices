package com.fxpi.rest.webservices.restfulwebservices.Repository;

import com.fxpi.rest.webservices.restfulwebservices.Beans.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
}
