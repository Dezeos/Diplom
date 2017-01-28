package comindmytroskoryk.linkedin.ua.diplom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dem on 28.01.2017.
 */
public class redactSTATUS {

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
        return "redactSTATUS{" +
                "status='" + status + '\'' +
                '}';
    }
}
