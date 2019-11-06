package geekbrains.ru.lesson4retrofit.DataBase;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

@Entity
public class User {
    @PrimaryKey
    public int id;

    @ColumnInfo
    public String name;

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

}
