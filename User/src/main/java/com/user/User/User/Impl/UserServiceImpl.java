package com.user.User.User.Impl;

import com.user.User.User.User;
import com.user.User.User.UserRepo;
import com.user.User.User.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

    private UserRepo userRepo;

    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public void createUser(User user) {
        userRepo.save(user);
    }

    @Override
    public List<User> getAllUser() {
        List<User> users=userRepo.findAll();
        return users;
    }



    @Override
    public User getUserById(Long id) {
        return userRepo.findById(id).orElse(null);
    }

    @Override
    public boolean updateById(Long id, User user) {
        Optional<User> update=userRepo.findById(id);
        if(update.isPresent()){
            User updated=update.get();
            updated.setEmail(user.getEmail());
            updated.setName(user.getName());
            updated.setEventId(user.getEventId());

            userRepo.save(updated);
          return true;
        }
        return false;
    }

    @Override
    public boolean deleteById(Long id) {
     if(userRepo.existsById(id)){
         userRepo.deleteById(id);
         return true;
     }
     return false;
    }


}
