package geekbrains.ru.lesson4retrofit.model;

public interface ReturnResult {
    void onSuccess(String result);
    void onError();
    void OnSuccessSaveDB(String user);
}
