package org.kiennguyenfpt.datingapp.enums;

public enum SubscriptionPlanType {
    FREE(1),
    TRIAL(2),
    PREMIUM(3);
    private final int value;

    SubscriptionPlanType(int value) {
        this.value = value;
    }

    public int value() {
        return this.value;
    }

}
