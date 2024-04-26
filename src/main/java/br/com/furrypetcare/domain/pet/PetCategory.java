package br.com.furrypetcare.domain.pet;

public enum PetCategory {

    DOG("Dog"),
    CAT("Cat"),
    BIRD("Bird"),
    REPTILE_AMPHIBIAN("Reptile/Amphibian"),
    FISH("Fish");

    private final String displayName;

    PetCategory(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
