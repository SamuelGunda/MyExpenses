package kasv.gunda.myexpense.models.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.CascadeType;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 36, nullable = false)
    private String accountName;

    private BigDecimal balance;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "user_account",
            joinColumns = { @JoinColumn(name = "account_id") },
            inverseJoinColumns = { @JoinColumn(name = "user_id") }
    )
    @JsonManagedReference
    Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL)
    @JsonManagedReference
    private Set<Transaction> transactions = new HashSet<>();
}
