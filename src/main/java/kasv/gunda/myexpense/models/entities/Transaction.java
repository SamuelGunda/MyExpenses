package kasv.gunda.myexpense.models.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
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

    @Column(length = 24, nullable = false)
    private String transactionName;

    @Column(length = 128, nullable = false)
    private String description;
    @Column(length = 36, nullable = false)
    private String transactionInitiator;
    private BigDecimal amount;

    @Column(nullable = false)
    private Date transactionDate = new Date();

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    @JsonBackReference
    private Account account;
}
