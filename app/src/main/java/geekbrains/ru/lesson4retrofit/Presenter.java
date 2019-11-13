package geekbrains.ru.lesson4retrofit;

import geekbrains.ru.lesson4retrofit.DataBase.OrmApp;

public class Presenter implements ReturnResult {
    private MainView mainView;
    public Model model;


    public Presenter(MainView mainView) {
        this.mainView = mainView;
        model = new Model(this, OrmApp.getComponent());
    }

    public void onClickLoadUser(String user) {
        model.downloadUser(user);
    }
    public void onClickRepos(String url) {model.downloadRepos(url);}
    public void onClickLoadFromDB(String name) {model.loadFromDB(name);}


    @Override
    public void onSuccess(String result) {
        mainView.setViewText(result);
    }

    @Override
    public void onError() {
        mainView.setViewText("Error");
    }

    @Override
    public void OnSuccessSaveDB(String user) {

    }

}
