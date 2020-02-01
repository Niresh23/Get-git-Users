package geekbrains.ru.lesson4retrofit;

import android.app.Application;
import androidx.room.Room;

import geekbrains.ru.lesson4retrofit.DataBase.AppDatabase;
import geekbrains.ru.lesson4retrofit.dagger.AppComponent;
import geekbrains.ru.lesson4retrofit.dagger.DaggerAppComponent;


public class RoomApp extends Application {
    private static final String DATABASE_NAME = "DATABASE_USERS";
    private static AppDatabase database;
    public static RoomApp INSTANCE;
    public static AppComponent component;
    @Override
    public void onCreate() {
        super.onCreate();
        database = Room.databaseBuilder(getApplicationContext(),
                                        AppDatabase.class, DATABASE_NAME).build();
        INSTANCE = this;
        component = DaggerAppComponent.create();
    }

    public AppDatabase getDatabase() {
        return database;
    }
    public static RoomApp get() {
        return INSTANCE;
    }
    public static AppComponent getComponent() {
        return component;
    }
}
