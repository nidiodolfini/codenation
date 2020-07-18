package com.challenge.service;

import com.challenge.entity.User;
import com.challenge.repository.UserRepository;
import com.challenge.service.interfaces.UserServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserServiceInterface {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<User> findById(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public List<User> findByAccelerationName(String name) {
        return userRepository.findByAccelerationName(name);
    }

    @Override
    public List<User> findByCompanyId(Long companyId) {
        return userRepository.findByCompanyId(companyId);
    }

    @Override
    public User save(User user) {
        User userDb;
        String fullName = user.getFullName();
        String email = user.getEmail();
        String nickname = user.getNickname();
        String password = user.getPassword();
        userDb = userRepository.findUserByFullNameAndEmailAndNicknameAndPassword(fullName, email, nickname, password);
        if (userDb != null)
            user = userDb;
        return userRepository.save(user);
    }
}
