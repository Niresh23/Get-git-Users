package geekbrains.ru.lesson4retrofit.dagger;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import geekbrains.ru.lesson4retrofit.model.Model;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class DaggerNetModule {

    @Provides
    @Singleton
    Retrofit createRetrofit() {
        return new Retrofit.Builder().baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    Model.RestApi provideRestApi(Retrofit retrofit) {
        return retrofit.create(Model.RestApi.class);
    }

    @Provides
    Model.RestApiRepos provideRestApiRepos(Retrofit retrofit) {
        return retrofit.create(Model.RestApiRepos.class);
    }
}
