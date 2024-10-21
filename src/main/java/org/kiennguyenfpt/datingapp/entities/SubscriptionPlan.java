package org.kiennguyenfpt.datingapp.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "SubscriptionPlans")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SubscriptionPlan {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long planId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private int duration; // Duration in days

    @Column(nullable = false)
    private double price;

    @Column(columnDefinition = "TEXT")
    private String features;
}
