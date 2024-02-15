package org.example.exceptions;

public class UserDuplicateNicknameException extends Exception {
    public UserDuplicateNicknameException(String message) {
        super(message);
    }
}
