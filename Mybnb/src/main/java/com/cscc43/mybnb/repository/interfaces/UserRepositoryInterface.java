package com.cscc43.mybnb.repository.interfaces;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cscc43.mybnb.models.User;

@Repository
public interface UserRepositoryInterface {
  public List<User> getAllUsers();

  public User getUser(int userId);

  public void addUser(User user);

  public void deleteUser(int id);

  public List<User> getUsersWithStatus();
}
