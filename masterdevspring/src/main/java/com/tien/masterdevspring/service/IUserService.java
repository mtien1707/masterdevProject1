package com.tien.masterdevspring.service;


import com.tien.masterdevspring.model.User;
import exception.ValidationRunTimeException;
import org.hibernate.service.Service;

import java.util.List;
import java.util.Optional;

public interface IUserService extends Service {

    Optional<User> findById(int id);

    Optional<User> findByEmail(String email);

//    User save(User user);

    // ham them user
    public User addUser(User user) ;

    // ham chinh sua thong tin user
    public User updateUser(int id, User user) throws ValidationRunTimeException;;

    //ham xoa user
    public boolean deleteUser(int id);

    // ham lay ra danh sach user
    public List<User> getAllUser();
    // ham lay ra 1 user
    public User getUser(int id);


}
