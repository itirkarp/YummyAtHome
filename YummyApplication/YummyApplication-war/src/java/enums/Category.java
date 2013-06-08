package enums;

/**
 *
 * @author Prakriti
 */
public enum Category {

    STARTERS("Starters"),
    MAINS("Mains"),
    RICE("Rice"),
    DRINKS("Drinks"),
    BREADS("Breads");

    private String label;

    private Category(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }
}