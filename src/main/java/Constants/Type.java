package Constants;

public enum Type {
    MILITARY(0, 30), FARMER(1, 20), TRADER(2, 25), ARTISAN(3, 20);

    private int value;

    private int foodPerDay;

    Type(int value, int foodPerDay) {
        this.value = value;
        this.foodPerDay = foodPerDay;
    }

    public int getFoodPerDay() {
        return foodPerDay;
    }

    public int getValue() {
        return value;
    }

    public static Type getType(int value) {
        switch (value) {
            case 0:
                return MILITARY;
            case 1:
                return FARMER;
            case 2:
                return TRADER;
            case 3:
                return ARTISAN;
            default:
                return null;
        }
    }
}
