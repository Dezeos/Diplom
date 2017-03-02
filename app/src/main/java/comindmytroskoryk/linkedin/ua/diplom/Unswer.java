package comindmytroskoryk.linkedin.ua.diplom;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Dem on 16.01.2017.
 */
public class Unswer implements Serializable{


    @SerializedName("id")
    private String Id;
    @SerializedName("name")
    private String firstName;
    @SerializedName("title")
    private String title;
    @SerializedName("groupName")
    private String groupName;
    @SerializedName("description")
    private String description;


    @Override
    public String toString() {
        return "Unswer{" +
                "id='" + Id + '\'' +
                ", firstName='" + firstName + '\'' +
                ", title='" + title + '\'' +
                ", groupName='" + groupName + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }




    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }
}
