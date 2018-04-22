public class Spectator implements Doingable {
    int energy;
    private boolean isSleeping;
    private String Describition = "Бедные";
    final private int MAX_ENERGY = 100;
    final int MIN_ENERGY = 0;


    public String toScream(){
            return "Зрители кричат: АААААААААААА СТРАШНА ВЫРУБАЙ!!!!!!";
    }

    public String wakeUp(){
        this.energy = MAX_ENERGY;
        this.isSleeping = false;
        return "зрители проспали " + Time.ALLDAY.getName() + " " + Time.ALLEVENING.getName() + " и проснулись только на " + Time.NEXTMORNING.getName();
    }

    public void getTired(){
        this.energy = MIN_ENERGY + (int)(Math.random()*energy);
    }


    public static String toWatchHorror(){
        return "Смотрит на ужасы";
    }


    @Override
    public String goToSleep(){
        this.energy = MIN_ENERGY;
        CinemaSeat cinemaSeat = new CinemaSeat();
        return (cinemaSeat.toFoldBackrest() + " *тихое посапывание*");
    }




    public int getEnergy() {
        return energy;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }

    public String getDescribition() {
        return Describition;
    }

    public void setDescribition(String describition) {
        Describition = describition;
    }

    public void setEnergy(int energy){
        this.energy = energy;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass() )
            return false;
        Spectator other = (Spectator) obj;
        return energy == other.energy && isSleeping == other.isSleeping && Describition == other.Describition;
    }
    @Override
    public String toString(){
        return isSleeping ? "Энергия закончилась, спит" : "Энергии - " + energy + " %, не спит";
    }
}


