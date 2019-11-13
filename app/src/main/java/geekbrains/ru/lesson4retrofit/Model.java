package geekbrains.ru.lesson4retrofit;

import android.annotation.SuppressLint;


import javax.inject.Inject;

import geekbrains.ru.lesson4retrofit.DataBase.OrmApp;
import geekbrains.ru.lesson4retrofit.DataBase.User;

import geekbrains.ru.lesson4retrofit.dagger.AppComponent;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class Model {

    @Inject
    RestApi api;
    @Inject
    RestApiRepos apiRepos;

    private ReturnResult returnResult;
    private String result = "";

    Model(ReturnResult returnResult, AppComponent component) {
        this.returnResult = returnResult;
        component.injectToModel(this);
    }



    public interface RestApi{
        @GET("users/{path}")
        Single<RetrofitModel> getUser(@Path("path") String user);
    }
    public interface RestApiRepos{
        @GET("users/{path}/repos")
        Single<RetrofitRepos[]> getRepos(@Path("path") String user);
    }

    public void downloadUser(String user) {
        api.getUser(user).retry(2).subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<RetrofitModel>() {

                    @Override
                    public void onSuccess(RetrofitModel retrofitModel) {
                        saveToDB(retrofitModel);
                        result = retrofitModel.getAvatarUrl();
                        returnResult.onSuccess(result);
                    }

                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                        returnResult.onError();
                    }
                });
    }

    public void downloadRepos(String request) {
        apiRepos.getRepos(request).retry(2).subscribeOn(Schedulers.newThread())
                .subscribeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<RetrofitRepos[]>() {
                    @SuppressLint("CheckResult")
                    @Override
                    public void onSuccess(RetrofitRepos[] retrofitRepos) {
                        StringBuilder builder = new StringBuilder();
                        for(RetrofitRepos r : retrofitRepos) {
                            builder.append(r.getName());
                            builder.append("; ");
                        }
                        returnResult.onSuccess(builder.toString());
                    }

                    @Override
                    public void onError(Throwable error) {
                        error.printStackTrace();
                        returnResult.onError();
                    }
                });
    }

    public void loadFromDB(String request) {
        Single<Object> single = Single.create(emitter -> {
            User user = OrmApp.get().getDatabase().userDao().findByName(request);
            emitter.onSuccess(user);
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Disposable disposable = new DisposableSingleObserver<User>() {

            @Override
            public void onSuccess(User user) {
                StringBuilder builder = new StringBuilder();
                builder.append(user.getName());
                builder.append("; ");
                builder.append(user.getId());
                returnResult.onSuccess(builder.toString());
            }

            @Override
            public void onError(Throwable e) {

            }
        };
        single.subscribe((SingleObserver<? super Object>) disposable);
    }

    public void saveToDB(RetrofitModel model) {
        Single<Object> single = Single.create(emitter -> {
            User user = new User();
            user.setName(model.getLogin());
            user.setId(model.getId());
            OrmApp.get().getDatabase().userDao().insertAll(user);
            emitter.onSuccess(user);
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        Disposable disposable = new DisposableSingleObserver<User>() {

            @Override
            public void onSuccess(User user) {
                returnResult.OnSuccessSaveDB(user.getName());
            }

            @Override
            public void onError(Throwable e) {

            }
        };
        single.subscribe((SingleObserver<? super Object>) disposable);
    }
}
