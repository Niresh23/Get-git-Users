package geekbrains.ru.lesson4retrofit.di;
import dagger.Component;
import geekbrains.ru.lesson4retrofit.model.Model;
import geekbrains.ru.lesson4retrofit.di.module.TestRepoModule;


@Component(modules = TestRepoModule.class)
public interface TestComponent {
    void inject(Model model);
}
