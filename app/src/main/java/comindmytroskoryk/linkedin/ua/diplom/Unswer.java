package comindmytroskoryk.linkedin.ua.diplom;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Dem on 16.01.2017.
 */
public class Unswer {
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String firstName;
    @SerializedName("uuid")
    private String uuid;


    @Override
    public String toString() {
        return "Unswer{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", uuid='" + uuid + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
}
