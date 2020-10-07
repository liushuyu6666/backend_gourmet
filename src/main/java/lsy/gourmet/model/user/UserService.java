package lsy.gourmet.model.user;


import lsy.gourmet.core.JWT;
import lsy.gourmet.model.loginrequest.LoginRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWT jwt;

    public User createUser(User userBody) throws Exception{
        if(userRepository.findUserByUsername(userBody.getUsername()) == null){
            if(userBody.getPassword() != null){
                User savingUser = new User();
                savingUser.setAddress(userBody.getAddress());
                savingUser.setEmail(userBody.getEmail());
                savingUser.setFirst_name(userBody.getFirst_name());
                savingUser.setLast_name(userBody.getLast_name());
                savingUser.setPassword(userBody.getPassword());
                savingUser.setStatus(userBody.getStatus());
                savingUser.setUsername(userBody.getUsername());

                return userRepository.save(savingUser);
            }
            else{
                throw new Exception("password can't be empty");
            }

        }
        else{
            throw new Exception("the username has already existed");
        }
    }

    public User retrieveUser(String username) throws Exception{

        User foundUser = userRepository
                .findUserByUsername(username);
        if(foundUser != null){
            return foundUser;
        }

        else{
            throw new Exception("can't retrieve, no username");
        }
    }

    public List<User> listUser(){
        return userRepository.findAll();
    }

    public User updateUser(String username, User updatingUser) throws Exception{
        User updatedUser = userRepository
                .findUserByUsername(username);
        if(updatedUser != null){
            updatedUser.setAddress(updatingUser.getAddress());
            updatedUser.setEmail(updatingUser.getEmail());
            updatedUser.setFirst_name(updatingUser.getFirst_name());
            updatedUser.setLast_name(updatingUser.getLast_name());
            updatedUser.setPassword(updatingUser.getPassword() == null?
                    updatedUser.getPassword(): updatingUser.getPassword());
            updatedUser.setStatus(updatingUser.getStatus());

            return userRepository.save(updatedUser);
        }
        else{
            throw new Exception("can't update, no username");
        }
    }

    public String createToken (LoginRequestBody loginRequestBody) throws Exception{
        String loginUsername = loginRequestBody.getUsername();
        String loginPassword = loginRequestBody.getPassword();
        User foundUser = userRepository
                .findUserByUsername(loginUsername);
        if(foundUser != null){
            if(loginPassword.equals(foundUser.getPassword())){
                return jwt.createToken(loginUsername);
            }
            else{
                throw new Exception("password and username is not match");
            }
        }
        else{
            throw new Exception("no such user");
        }
    }

}
