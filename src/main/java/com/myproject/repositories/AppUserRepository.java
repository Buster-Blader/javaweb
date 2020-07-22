package com.myproject.repositories;


import com.myproject.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser,Long> {
    @Query(value="select user from AppUser user where user.userName =:name")
    AppUser findByName(@Param("name") String name);
}
