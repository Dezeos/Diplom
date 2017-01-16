package comindmytroskoryk.linkedin.ua.diplom;

/**
 * Created by Dem on 16.01.2017.
 */
public class User {

    private String auth_key;


    public String getAuth_key() {
        return auth_key;
    }

    public void setAuth_key(String auth_key) {
        this.auth_key = auth_key;
    }

/*
    @Override
    public String toString() {
        return "User{" +
                "auth_key='" + auth_key + '\'' +
                '}';
    }*/

    @Override
    public String toString() {
        return  auth_key ;
    }

}
