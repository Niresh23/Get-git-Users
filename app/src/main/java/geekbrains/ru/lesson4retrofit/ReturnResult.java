package geekbrains.ru.lesson4retrofit;

public interface ReturnResult {
    void onSuccess(String result);
    void onError();
    void OnSuccessSaveDB(String user);
}
