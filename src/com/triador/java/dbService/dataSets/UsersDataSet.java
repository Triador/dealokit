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

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "password")
    private String password;

    @Column(name = "party")
    private String party;

    public UsersDataSet() {} //important for hibernate...

    public UsersDataSet(String name, String password, String party) {
        this.name = name;
        this.password = password;
        this.party = party;
    }

    public UsersDataSet(String name, String password) {
        this.name = name;
        this.password = password;
        this.party = null;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getParty() {
        return party;
    }
}
