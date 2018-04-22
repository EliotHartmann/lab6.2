public class Policeman {
    private int healthPoint;
    private String name;
    private String describition = "Вооруженные до зубов";
    private Transport transport;
    private int ID;
    private float x;
    private float y;
    Policeman(String name){
        this.name = name;
        }
    public String persueCriminal(){
        return "Полицейский " +  this.name + " гонится за преступником";
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public String useVehicle(){
        this.transport = Transport.values()[(int)(Math.random()*Transport.values().length)];
        return this.name + " едет на " + this.transport.getName();
    }

    public String toFall(){
        this.healthPoint = healthPoint - 20;
        if (healthPoint <= 0)
            return "Полицейский" + this.name + " упал и умер";
        return this.name + " упал";
    }

    public String fallThrought(){
        this.healthPoint = healthPoint - 35;
        if (healthPoint <= 0)
            return "Полицейский " + this.name + " провалился и умер";
        return "Полицейский " + this.name + " провалился";
    }

    public String toSink(){
        this.healthPoint = 0;
        return this.name + " утонул";
    }

    public String sinkCriminal(Criminal criminal){
        criminal.setHealthPoint(0);
        return "Полицейский утопил преступника, по имени " + criminal.getName();
    }


    public String getHurt(){
        this.healthPoint = healthPoint - 30 + (int)(Math.random()*30);
        if (healthPoint <= 0)
            return "Полицейский " + name + " умирает";
        return "Полицейский " + name + " ранен";
    }

    public int getHealthPoint() {
        return healthPoint;
    }

    public void setHealthPoint(int healthPoint) {
        this.healthPoint = healthPoint;
    }

    public String getDescribition() {
        return describition;
    }

    public void setDescribition(String describition) {
        this.describition = describition;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString(){
        return "Это полицесйкий " + this.name + " и у него осталось " + this.healthPoint + " % здоровья";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass() )
            return false;
        Policeman other = (Policeman) obj;
        return name.equals(other.name) &&  healthPoint == other.healthPoint;
    }
}
