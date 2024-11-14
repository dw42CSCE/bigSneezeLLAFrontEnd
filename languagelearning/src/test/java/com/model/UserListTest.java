package com.model;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UserListTest {

    private UserList users;
    private User user;
    private User testUser;

    @BeforeEach
    public void setUp(){
        users = new UserList();
        user = new User("Test", "Test", "Test");
        users.addUser("Test", "Test", "Test");
    }
    

    //AddWord Tests
    @Test
    public void testAddUser(){
        assertEquals("Test", user.getUsername());
    }

    @Test
    public void testAddDuplicateUser() {
        users.addUser(user.getUsername(),user.getEmailAddress(),user.getPassword());
        users.addUser("Test", "Test", "User");
        int count = 0;
        for (User testUser : users.getUsers()) {
            if (testUser.getUsername().equals("Test")) {
                count++;
            }
        }
        assertEquals(1, count);
    }
    

    @Test
    public void testNullUsername(){
        users.addUser(null, "Test", "Test");
        User test = users.getUser(null, "Test");
        assertNull(test);
    }

    @Test
    public void testNullPassword(){
        users.addUser("Test", "Test", null);
        User test = users.getUser("Test", null);
        assertNull(test);
    }

    @Test
    public void testRemoveUser(){
        users.removeUser("Test","Test");
        assertNull(users.getUser("Test", "Test"));;
    }

    @Test
    public void testCaseSensitiveRemove(){
        users.removeUser("test","test");
        assertNotNull(users.getUser("Test", "Test").getUsername());;
    }

    @Test
    public void testEditUser(){
        users.addUser("Test","Test","Test");
        users.editUser("New", "New", "Test", "Test");
        assertEquals(users.getUser("New", "New").getPassword(),"Test");
    }

    @Test
    public void testEditNonExist(){
        users.addUser("Test", "Test", "Test");;
        users.editUser("New","New","NoExist","NoExist");
        int count =0;
        if(users.getUser("Test","NoExist").equals("NoExist"))
            count++;
        assertEquals(0,count);
    }
    
}
