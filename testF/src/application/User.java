package application;


public class User {

    private Integer id;
    private String email;
    private String username;
    private String password;

    public User(Integer id, String email, String username, String password) {
        this.id = id;
        this.email = email;
        this.username = username;
        this.password = password;
    }

    public Integer getId() {
        return this.id;
    }

    public String getEmail() {
        return this.email;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }
}
