public class Noize {
    private int noizeVolume;
    final private int MAX_VOLUME = 100;
    final private int MIN_VOLUME = 0;

    public void setNoizeVolume(int noizeVolume) {
        if (noizeVolume <= MAX_VOLUME && noizeVolume >= MIN_VOLUME)
            this.noizeVolume = noizeVolume;
    }
    public int getNoizeVolume(){
        return noizeVolume;
    }
    @Override
    public String toString(){
        return "Уровень шума - " + noizeVolume + '%';
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass() )
            return false;
        Noize other = (Noize) obj;
        return noizeVolume == other.noizeVolume;
    }

    public int getMAX_VOLUME() {
        return MAX_VOLUME;
    }

    public int getMIN_VOLUME() {
        return MIN_VOLUME;
    }
}
