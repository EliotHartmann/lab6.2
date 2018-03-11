public enum Transport {
    CAR("Машинке"),
    BUS("Автобусике"),
    HELICOPTER("Вертолётике"),
    AIRCRAFT("Самолётике"),
    TRAIN("Поездике"),
    BOAT("Катерке"),
    SHIP("Параходике"),
    SUBMARINE("Подлодочке");
    private String name;
    Transport(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
}
