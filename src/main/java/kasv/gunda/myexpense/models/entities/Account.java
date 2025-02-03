package kasv.gunda.myexpense.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private Integer id;

    @Column(unique = true, length = 36, nullable = false)
    private UUID publicId;

    @Column(length = 36, nullable = false)
    private String accountName;

    private double balance;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_account",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Transaction> transactions = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        if (publicId == null) {
            publicId = UUID.randomUUID();
        }
    }
}
