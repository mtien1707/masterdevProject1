package com.tien.masterdevspring.service;


import com.tien.masterdevspring.model.User;
import com.tien.masterdevspring.repository.UserRepository;
import exception.DuplicateRecordException;
import exception.InternalServerException;
import exception.NotFoundException;
import exception.ValidationRunTimeException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
@Service
public class UserServiceImpl implements IUserService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserServiceImpl(UserRepository userRespository) {
        this.userRepository = userRespository;
    }

    @Override
    public Optional<User> findById(int id) {
        return this.userRepository.findById(id);
    }

   @Override
    public Optional<User> findByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }






    @Override
    public User addUser(User user)  {

        if(this.findById(user.getId()).isPresent()) {
            throw new DuplicateRecordException("Id đã tồn tại");
        }


        if(this.findByEmail(user.getEmail()).isPresent()) {
            throw new DuplicateRecordException("Email đã tốn tại");

        }
        if(user != null) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return this.userRepository.save(user);
        }

        return null;
    }

    @Override
    public User updateUser(int id, User user) throws ValidationRunTimeException {
        if(user !=null) {
            User user1 = userRepository.getById(id);
            if (user1 != null) {
                user1.setEmail(user.getEmail());
                user1.setPassword(user.getPassword());
                return userRepository.save(user1);

            }
        }
        throw new ValidationRunTimeException("Không tìm thấy user");


    }


    @Override
    public boolean deleteUser(int id) {
        if (id > 0) {
            User user = userRepository.findById(id).get();
            if (user != null) {
                userRepository.delete(user);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUser(int id) {
        return userRepository.findById(id).get();
    }

}


