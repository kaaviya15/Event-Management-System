package com.user.User.User;

import java.util.List;

public interface UserService {

    void createUser(User user);
    List<User> getAllUser();
    User getUserById(Long id);
    boolean updateById(Long id,User user);
    boolean deleteById(Long id);
}
