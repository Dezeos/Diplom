package comindmytroskoryk.linkedin.ua.diplom;

import com.google.gson.annotations.SerializedName;

/*
Класс для обработки сообщения о статусе сохранения редактированного контента на сервере
 */
public class StatusResult {

    @SerializedName("status")
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "StatusResult{" +
                "status='" + status + '\'' +
                '}';
    }
}
