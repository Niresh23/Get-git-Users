package geekbrains.ru.lesson4retrofit.di.module;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import geekbrains.ru.lesson4retrofit.Model;

@Module
public class TestRepoModule {
    @Provides
    public Model.RestApi provideRestApi() {
        return Mockito.mock(Model.RestApi.class);
    }

    @Provides
    public Model.RestApiRepos povideRestApiRepo() {
        return Mockito.mock(Model.RestApiRepos.class);
    }

}
