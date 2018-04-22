public class Shorty extends Spectator {
    private String name;
    boolean isSleeping;

    Shorty(String name){
        this.name = name;
    }

    public String toThinkAboutTakingSeat(){
        return "Коротышка " + this.name + " сообразил, что пора бы занять место";
    }

    public String takeSeat(){
        CinemaSeat cinemaSeat = new CinemaSeat();
        return "Коротышка " + this.name + " занял место номер " + cinemaSeat.calculateNumberOfSeat();
    }

    public static String cry(){
        return "коротышки плачут";
    }

    @Override
    public String goToSleep(){
        CinemaSeat cinemaSeat = new CinemaSeat();
        cinemaSeat.setSeatTaken(true);
        this.isSleeping = true;
        this.energy = MIN_ENERGY;
        return "Коротышка " + this.name + " уснул, недождавшись конца картины";
    }

    @Override
    public boolean isSleeping() {
        return isSleeping;
    }

    @Override
    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass() )
            return false;
        Shorty other = (Shorty) obj;
        return name.equals(other.name) && isSleeping == other.isSleeping;
    }
    @Override
    public String toString(){
        if (isSleeping)
            return this.name + " устал, спит на кресле";
        else
            return this.name + "Не спит";
    }
}
