public enum HairColor {
    WHILE,
    BLOND,
    RED,
    BROWN,
    BLACK;

    @Override
    public String toString() {
        return name().toLowerCase();
    }
}
