package com.example.dead_mfcs_widget;


public class DeadRussians {
    private final String type;
    private final String quantity;
    private final String income;

    public DeadRussians(String type, String quantity, String income) {
        this.type = type;
        this.quantity = quantity;
        this.income = income;
    }

    public String getType() {
        return type;
    }

    public String getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "DeadRussians{" +
                "type='" + type + '\'' +
                ", quantity='" + quantity + '\'' +
                ", income='" + income + '\'' +
                '}';
    }

    public String getIncome() {
        return income;
    }
}
