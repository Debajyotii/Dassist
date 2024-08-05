package banquemisr.challenge05.taskmanager.service;


import banquemisr.challenge05.taskmanager.exception.CustomException;

import javax.security.auth.login.FailedLoginException;


public interface AuthService {
    void authenticate(String username, String password) throws CustomException, FailedLoginException;
}
