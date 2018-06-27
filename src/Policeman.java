import java.io.Serializable;
import java.time.LocalDateTime;

public class Policeman implements Serializable{

    private int ID_Policeman;

    private String name;

    private int age;

    private int ID;

    private int x1;

    private int y1;

    private int height;

    private int width;

    @toJson
    LocalDateTime createTime;

    private String colour;

    Policeman(PolicemanBuilder policemanBuilder){
        this.ID_Policeman = policemanBuilder.getID_Policeman();
        this.name = policemanBuilder.getName();
        this.age = policemanBuilder.getAge();
        this.colour = policemanBuilder.getColour();
        this.x1 = policemanBuilder.getX1();
        this.y1 = policemanBuilder.getY1();
        this.height = policemanBuilder.getHeight();
        this.width = policemanBuilder.getWidth();
        this.createTime = policemanBuilder.getCreateTime();
        }

        public Policeman(){}
    public int getX1() {
        return x1;
    }

    public int getY1() {
        return y1;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String persueCriminal(){
        return "Полицейский " +  this.name + " гонится за преступником";
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }
//
//
//
//    public String toFall(){
//        this.healthPoint = healthPoint - 20;
//        if (healthPoint <= 0)
//            return "Полицейский" + this.name + " упал и умер";
//        return this.name + " упал";
//    }
//
//    public String fallThrought(){
//        this.healthPoint = healthPoint - 35;
//        if (healthPoint <= 0)
//            return "Полицейский " + this.name + " провалился и умер";
//        return "Полицейский " + this.name + " провалился";
//    }
//
//    public String toSink(){
//        this.healthPoint = 0;
//        return this.name + " утонул";
//    }
//
//    public String sinkCriminal(Criminal criminal){
//        criminal.setHealthPoint(0);
//        return "Полицейский утопил преступника, по имени " + criminal.getName();
//    }
//
//
//    public String getHurt(){
//        this.healthPoint = healthPoint - 30 + (int)(Math.random()*30);
//        if (healthPoint <= 0)
//            return "Полицейский " + name + " умирает";
//        return "Полицейский " + name + " ранен";
//    }
//
//    public int getHealthPoint() {
//        return healthPoint;
//    }
//
//    public void setHealthPoint(int healthPoint) {
//        this.healthPoint = healthPoint;
//    }
//
//    public String getDescribition() {
//        return describition;
//    }
//
//    public void setDescribition(String describition) {
//        this.describition = describition;
//    }
//
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
//
//    @Override
//    public String toString(){
//        return "Это полицесйкий " + this.name + " и у него осталось " + this.healthPoint + " % здоровья";
//    }
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null || getClass() != obj.getClass() )
//            return false;
//        Policeman other = (Policeman) obj;
//        return name.equals(other.name) &&  healthPoint == other.healthPoint;
//    }
}
