package com.model;
import java.util.ArrayList;

public class UserList {
  private ArrayList<User> users;
  private static UserList userList;

  /**
   * Default constructor for UserList
   */
  public UserList() {
    users = DataReadWriter.getUsers();
    if (users == null) {
        users = new ArrayList<>();  // Initialize with an empty list if null
    }
}

  /**
   * Gets current userlist, only 1 can exist
   * @return Current UserList
   */
  public static UserList getInstance(){
    if(userList==null)
      userList=new UserList();
    return userList;
  }

  /**
   * Adds a user to the list with minimum info
   * @param username String of username
   * @param email String of email
   * @param password String of password
   */
  public void addUser(String username, String email, String password) {
    for (User tempUser : users) {
        if (tempUser.getUsername().equals(username)) {
            System.out.println("Username already exists.");
            return;
        }
        if (tempUser.getEmailAddress().equals(email)) {
            System.out.println("Email already exists.");
            return;
        }
    }
    User newUser = new User(username, password, email);
    users.add(newUser);
    System.out.println("User added successfully.");
}

  /**
   * Changes an existing user based on email and password
   * @param firstName String of user first name
   * @param lastName String of user last name
   * @param email String of user email
   * @param password String of user password
   */
  public void editUser(String firstName, String lastName, String email, String password) {
    for(int i=0;i<users.size();i++){
      User tempUser=users.get(i);
      if(tempUser.getEmailAddress().equals(email) && tempUser.getPassword().equals(password)){
              tempUser.setName(firstName, lastName);
              tempUser.setEmailAddress(email);
              tempUser.setPassword(password);
              users.remove(i);
              users.add(i,tempUser);   
      }
    }
  }

  /**
   * Gets a user from the UserList
   * @param username String username 
   * @param password String password
   * @return User with username and password
   */
  public User getUser(String username, String password) {
    users = DataReadWriter.getUsers();
    for (int i = 0; i < users.size(); i++) {  // Fix: iterate through the entire list
        User tempUser = users.get(i);
        if (tempUser.getUsername().equals(username) && tempUser.getPassword().equals(password)) {
            return users.get(i);  // Return the matching user
        }
    }
    return null;  // Return the "NotFound" user if no match is found
}

  /**
   * Gets the current UserList
   * @return Arraylist of users
   */
  public ArrayList<User> getUsers(){
    return users;
  }

  /**
   * Removes a user
   * @param username String username of user to be removed
   * @param password String password of user to be removed
   */
  public void removeUser(String username, String password) {
    for(int i=0;i<users.size();i++){
      User tempUser=users.get(i);
      if(tempUser.getUsername().equals(username) && tempUser.getPassword().equals(password)){
              users.remove(i);   
      }
    }
  }

}   
