package be.ipam.language.bll.services.Iservices;

import be.ipam.language.bll.exceptions.UserException;
import be.ipam.language.dao.entities.UserEntity;

import java.util.List;

public interface IUserService {

    List<UserEntity> findAllUsers();

    UserEntity findUserById(Long id) throws UserException;

    UserEntity saveUser(UserEntity user);

    void deleteUser(Long id) throws UserException;

    UserEntity findByEmail(String email) throws UserException;
}