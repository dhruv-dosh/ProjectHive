package com.ikshvaku.ProjectHive.Services;

import com.ikshvaku.ProjectHive.modal.User;
import com.ikshvaku.ProjectHive.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;
    @Override
    public User findUserProfileByJwt(String jwt) throws Exception {
       return null;
    }

    @Override
    public User findUserByEmail(String email) throws Exception {
        return null;
    }

    @Override
    public User findUserById(Long userId) throws Exception {
        return null;
    }

    @Override
    public User updateUsersProjectSize(User user, int number) {
        return null;
    }
}
