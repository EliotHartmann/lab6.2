public class Light {

    private boolean lightIsOn;

    public String lightTurnOn(){
        this.lightIsOn = true;
        return "Свет велючился";
    }
    public String lightTurnOff(){
        this.lightIsOn = false;
        return "Свет погас";
    }

    public boolean isLightIsOn() {
        return this.lightIsOn;
    }

    @Override
    public String toString(){
        return this.lightIsOn ? "Свет включен" : "Свет выключен";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Light)) return false;
        Light light = (Light) o;
        return lightIsOn == light.lightIsOn;
    }
}
