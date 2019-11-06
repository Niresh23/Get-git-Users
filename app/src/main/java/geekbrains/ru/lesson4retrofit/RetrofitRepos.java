package geekbrains.ru.lesson4retrofit;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RetrofitRepos {
    @Expose
    private String name;

    @Expose
    @SerializedName("full_name")
    private String fullName;

    public String getName(){
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
}
