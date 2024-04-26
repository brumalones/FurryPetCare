package br.com.furrypetcare.domain.pet;

public enum PetGender {

    MALE("Male"),
    FEMALE("Female");

    private final String displayName;

    PetGender(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
