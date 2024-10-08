package tests.data;

public enum Gender {
    MALE("Male"), FEMALE("Female"), OTHER("Other");
    public final String description;

    Gender(String description) {
        this.description = description;
    }
}

