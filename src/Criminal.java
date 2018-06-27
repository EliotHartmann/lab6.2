public class Criminal {
    private boolean masked;
    private String name;
    private int healthPoint;
    private Transport transport;


    Criminal(String name){
        this.name = name;
    }

    public String persuePoliceman(){
        return "Преступник " + this.name + " гонится за полицеским";
    }

    public String useVehicle(){
        this.transport = Transport.values()[(int)(Math.random()*Transport.values().length)];
        return this.name + " едет на " + this.transport.getName();
    }

    public String run(){
        return "Преступник " + this.name + " бежит";
    }

    public String toFall(){
    this.healthPoint = healthPoint - 20;
    if (healthPoint <= 0)
        return this.name + " споткнулся и умер";
    return this.name + " споткнулся";
    }

    public String fallThrought(){
    this.healthPoint = healthPoint - 35;
    if (healthPoint <= 0)
        return this.name + " провалился и умер";
    return "Преступник " + this.name + " провалился";
    }

    public String toSink(){
    this.healthPoint = 0;
    return this.name + " утонул";
    }

//    public String sinkPoliceman(Policeman policeman){
//        policeman.setHealthPoint(0);
//        return this.name + " замочил(утопил) полицеского, по имени " + policeman.getName();
//    }
//
//    public String getHurt(){
//        this.healthPoint = healthPoint - 30 + (int)(Math.random()*30);
//        if (healthPoint <= 0)
//            return "Преступник " + name + " покидает эту бренную землю";
//        return this.name + " словил маслину";
//
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
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    @Override
//    public boolean equals(Object obj) {
//        if (this == obj)
//            return true;
//        if (obj == null || getClass() != obj.getClass() )
//            return false;
//        Criminal other = (Criminal) obj;
//        return masked == other.masked && name.equals(other.name) &&  healthPoint == other.healthPoint;
//    }
//    @Override
//    public String toString(){
//        if (masked)
//            return "Преступник " + this.name + " в маске, и у него осталось " + this.healthPoint + " здоровья";
//        else
//            return "Преступник " + this.name + " не в маске, и у него осталось " + this.healthPoint + " здоровья";
//    }
}
