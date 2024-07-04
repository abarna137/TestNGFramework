package com.luma.qa.profile;

public enum TestProfiles {
    DEV("dev"),
    QA("qa"),
    UAT("uat");
    private final String value;

    TestProfiles(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public String getValue() {
        return value;
    }

    public TestProfiles fromString(String value) {
        for (TestProfiles profile : TestProfiles.values()) {
            if (profile.getValue().equalsIgnoreCase(value)) {
                return profile;
            }
        }
        throw new IllegalArgumentException(value + " is not a valid name for TestProfile.");
    }
}
