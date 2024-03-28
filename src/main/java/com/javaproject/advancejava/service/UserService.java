package com.javaproject.advancejava.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.javaproject.advancejava.model.User;

@Service
public class UserService {
 private List<User> store=new ArrayList<>();

 public UserService() {
    store.add(new User(UUID.randomUUID().toString(), "Raj Das", "raj1@gmail.com"));
    store.add(new User(UUID.randomUUID().toString(), "Ram Das", "ram1@gmail.com"));
    store.add(new User(UUID.randomUUID().toString(), "Deepak Das", "deepak1@gmail.com"));
    store.add(new User(UUID.randomUUID().toString(), "Lal Das", "lal1@gmail.com"));
}
public List<User>getUsers(){
    return this.store;
}
}
