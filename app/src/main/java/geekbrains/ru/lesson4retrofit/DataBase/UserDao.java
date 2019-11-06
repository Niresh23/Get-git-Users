package geekbrains.ru.lesson4retrofit.DataBase;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface UserDao {
    @Query("SELECT * FROM User")
    List<User> getAll();

    @Query("SELECT * FROM User WHERE id IN (:userIds)")
    List<User> loadAllById(int[] userIds);

    @Query("SELECT * FROM User WHERE name LIKE :nameRequest")
    User findByName(String nameRequest);

    @Insert
    void insertAll(User... user);

    @Delete
    void deleste(User user);
}
