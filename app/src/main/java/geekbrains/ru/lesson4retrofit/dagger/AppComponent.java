package geekbrains.ru.lesson4retrofit.dagger;

import javax.inject.Singleton;

import dagger.Component;
import geekbrains.ru.lesson4retrofit.Model;

@Component(modules = DaggerNetModule.class)
@Singleton
public interface AppComponent {
    void injectToModel(Model model);
}
