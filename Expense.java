package com.example.demo;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "expense")

    public class Expense {
        @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false, precision = 12, scale = 2)
        private BigDecimal amount;

        @Column(nullable = false)
        private LocalDate date;

        private String description;
        private String paymentMethod;

        @ManyToOne(optional = false)
        @JoinColumn(name = "category_id", nullable = false)
        private Category category;

        public Expense() {}
            public Long getId() {return id;}
            public BigDecimal getAmount() {return amount;}
            public void setAmount(BigDecimal amount) {this.amount = amount;}
            public LocalDate getDate() {return date;}
            public void setDate(LocalDate date) {this.date = date;}
            public String getDescription() {return description;}
            public void setDescription(String description) {this.description = description;}
            public String getPaymentMethod() {return paymentMethod;}
            public void setPaymentMethod(String paymentMethod) {this.paymentMethod = paymentMethod;}
            public Category getCategory() {return category;}
            public void setCategory(Category category) {this.category = category;}



    }
