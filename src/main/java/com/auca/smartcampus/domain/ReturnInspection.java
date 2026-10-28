package com.auca.smartcampus.domain;

import java.time.LocalDate;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "return_inspection")
public class ReturnInspection {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "item_name")
    private String itemName;

    @Column(name = "condition")
    private String condition;

    @Column(name = "penalty_rwf")
    private int penaltyRwf;

    @Column(name = "inspected_date")
    private LocalDate inspectedDate;

    @ManyToOne
    @JoinColumn(name = "borrow_request_id")
    private BorrowRequest borrowRequest;

    public UUID getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getPenaltyRwf() {
        return penaltyRwf;
    }

    public void setPenaltyRwf(int penaltyRwf) {
        this.penaltyRwf = penaltyRwf;
    }

    public LocalDate getInspectedDate() {
        return inspectedDate;
    }

    public void setInspectedDate(LocalDate inspectedDate) {
        this.inspectedDate = inspectedDate;
    }

    public BorrowRequest getBorrowRequest() {
        return borrowRequest;
    }

    public void setBorrowRequest(BorrowRequest borrowRequest) {
        this.borrowRequest = borrowRequest;
    }
}
