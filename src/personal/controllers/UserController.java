package personal.controllers;

import personal.model.Repository;
import personal.model.User;

import java.util.List;

public class UserController {
    private final Repository repository;
    private final Validation valid = new Validation();

    public UserController(Repository repository) {
        this.repository = repository;
    }
    /**
     * Перед созданием проверит пустой ли подсунули user
     * @param user
     */
    public void saveUser(User user) {
        valid.Validate(user);
        repository.CreateUser(user);
    }

    public User readUser(String userId) throws Exception {
        List<User> users = repository.getAllUsers();
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }

        throw new Exception("User not found");
    }
    public List<User> getAllUser(){
        return repository.getAllUsers();
    }

    /**
     * Перед обновлением проверит пустой ли подсунули user
     * @param user
     */
    public void UpdUser(User user){
        valid.Validate(user);
        repository.UpdateUser(user);
    }
    public void DeleteUser(User user){
        repository.Delete(user);
    }
}
