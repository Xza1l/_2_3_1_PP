package web.DAO;

import web.Model.User;
import java.util.List;

public interface UserDAO {

    //C: добавить пользователя в БД
    //R: Получить пользователя из БД
    //U: Обновить пользователя в БД
    //D: Удалить пользователя в БД

    void saveUser(String name, String lastName);
    void saveUser(User user);
    void updateUser(User user);
    void updateUser(long id, String name, String lastName);
    void removeUserById(long id);
    User readUserById(long id);
    List<User> getAllUsers();
}
