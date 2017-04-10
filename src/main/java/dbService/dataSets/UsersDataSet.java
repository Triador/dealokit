package dbService.dataSets;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by antonandreev on 08/04/2017.
 */
@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable {
    @Id
    @Column()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "login", unique = true)
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    public UsersDataSet() {} //important for hibernate...

    public UsersDataSet(String login, String password, String email) {
        this.login = login;
        this.password = password;
        this.email = email;
    }

    public long getId() {
        return id;
    }
    public String getLogin() {
        return login;
    }
    public String getPassword() {
        return password;
    }
    public String getEmail() {
        return email;
    }
}
