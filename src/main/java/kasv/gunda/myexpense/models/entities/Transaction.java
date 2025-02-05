package kasv.gunda.myexpense.models.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.UUID;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(length = 36, nullable = false)
    private String transactionName;

    @Column(length = 36, nullable = false)
    private String description;
    private float amount;

    @Column(nullable = false)
    private Date transactionDate = new Date();

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
}
