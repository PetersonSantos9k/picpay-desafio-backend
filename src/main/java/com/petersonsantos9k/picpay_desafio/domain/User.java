package com.petersonsantos9k.picpay_desafio.domain;

import com.petersonsantos9k.picpay_desafio.exception.UserException;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;


@Entity
@Table(name = "tb_user")
@EqualsAndHashCode(of = "id")
@Getter
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, length = 150)
    @Setter
    private String fullName;

    @Column(unique = true, nullable = false, length = 14)
    @Setter
    private String taxId;
    @Column(unique = true, nullable = false, length = 254)
    @Setter
    private String email;
    @Column(nullable = false, length = 255)
    @Setter
    private String password;

    @Column(nullable = false, precision = 19, scale = 2)
    private BigDecimal balance = BigDecimal.ZERO;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    @Setter
    private UserType userType;

    public User(String fullName, String taxId, String email, String password, UserType userType) {
        this.fullName = fullName;
        this.taxId = taxId;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public void deposit(BigDecimal amount){

        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new UserException("Amount must be positive");
        }
        this.balance = this.balance.add(amount);
    }

    public void withdraw(BigDecimal amount){

        if (this.userType == UserType.MERCHANT) {
            throw new UserException("Merchant accounts cannot withdraw funds");
        }

        if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0){
            throw new UserException("Amount must be positive");
        }

        if(this.balance.compareTo(amount) < 0 ){
            throw new UserException("Insufficient balance");
        }

        this.balance = this.balance.subtract(amount);

    }
}
