//import java.util.Random;
//public class Battle {
//
//    public String fight(Policeman policeman, Criminal criminal){
//        Random random = new Random();
//        if (random.nextBoolean()) {
//            policeman.setHealthPoint(0);
//            return "Преступник " + criminal.getName() + " победил в драке, полицейский " + policeman.getName() + " погиб";
//        }
//        else {
//            criminal.setHealthPoint(0);
//            return "Полицейский " + policeman.getName() + " победил в драке, преступник " + criminal.getName() + " погиб";
//        }
//    }
//
//    public String shoot(Criminal criminal, Policeman policeman){
//        Random random = new Random();
//        if (random.nextBoolean())
//            return criminal.getHurt();
//        else
//            return policeman.getHurt();
//
//    }
//
//    public void startBattle(){
//
//        int selectActivity;
//        Battle battle = new Battle();
//
//        Policeman Пётр = new Policeman("Андрей", 22, "abc");
//        Policeman Сергей = new Policeman("Андрей", 22, "abc");
//        Policeman Иван = new Policeman("Андрей", 22, "abc");
//        Criminal СаняМалой = new Criminal("Саня Малой");
//        Criminal ВасяПуля = new Criminal("Вася Пуля");
//        Criminal ЛёхаКастет = new Criminal("Лёха Кастет");
//
//        Пётр.setHealthPoint(100);
//        Сергей.setHealthPoint(100);
//        Иван.setHealthPoint(100);
//        СаняМалой.setHealthPoint(100);
//        ВасяПуля.setHealthPoint(100);
//        ЛёхаКастет.setHealthPoint(100);
//
//        battle.fight(Пётр, СаняМалой);
//        battle.fight(Сергей, ВасяПуля);
//        battle.fight(Иван, ЛёхаКастет);
//
//        QueueBattle queueBattle = new QueueBattle();
//        queueBattle.queuePoliceman.add(Пётр);
//        queueBattle.queuePoliceman.add(Сергей);
//        queueBattle.queuePoliceman.add(Иван);
//        queueBattle.queueCriminal.add(СаняМалой);
//        queueBattle.queueCriminal.add(ВасяПуля);
//        queueBattle.queueCriminal.add(ЛёхаКастет);
//
//        System.out.println("Битва началась");
//
//        while ((queueBattle.queuePoliceman.size()!=0) && (queueBattle.queueCriminal.size()!=0)){
//            selectActivity = 1 + (int)(Math.random()*14);
//            switch (selectActivity) {
//                case 1:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(queueBattle.queueCriminal.peek().fallThrought());
//                        if (queueBattle.queueCriminal.peek().getHealthPoint() <= 0)
//                            queueBattle.queueCriminal.remove();
//                        break;
//                    }
//                    else break;
//                case 2:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(queueBattle.queueCriminal.peek().run());
//                        break;
//                    }
//                    else break;
//                case 3:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(queueBattle.queueCriminal.peek().persuePoliceman());
//                        break;
//                    }
//                    else break;
//                case 4:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(queueBattle.queueCriminal.peek().toFall());
//                        if (queueBattle.queueCriminal.peek().getHealthPoint() <= 0)
//                            queueBattle.queueCriminal.remove();
//                        break;
//                    }
//                    else break;
//                case 5:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(queueBattle.queueCriminal.peek().toSink());
//                        queueBattle.queueCriminal.remove();
//                        break;
//                    }
//                    else break;
//                case 6:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(queueBattle.queueCriminal.peek().sinkPoliceman(queueBattle.queuePoliceman.peek()));
//                        queueBattle.queuePoliceman.remove();
//                        break;
//                    }
//                    else break;
//                case 7:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(queueBattle.queuePoliceman.peek().fallThrought());
//                        if (queueBattle.queuePoliceman.peek().getHealthPoint() <= 0)
//                            queueBattle.queuePoliceman.remove();
//                        break;
//                    }
//                    else break;
//                case 8:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(queueBattle.queuePoliceman.peek().persueCriminal());
//                        break;
//                    }
//                    else break;
//                case 9:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(queueBattle.queuePoliceman.peek().sinkCriminal(queueBattle.queueCriminal.peek()));
//                        queueBattle.queueCriminal.remove();
//                        break;
//                    }
//                    else break;
//                case 10:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(queueBattle.queuePoliceman.peek().toFall());
//                        if (queueBattle.queuePoliceman.peek().getHealthPoint() <= 0)
//                            queueBattle.queuePoliceman.remove();
//                        break;
//                    }
//                    else break;
//                case 11:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(queueBattle.queuePoliceman.peek().toSink());
//                        queueBattle.queuePoliceman.remove();
//                        break;
//                    }
//                    else break;
//                case 12:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(queueBattle.queuePoliceman.peek().useVehicle());
//                        break;
//                    }
//                    else break;
//                case 13:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(queueBattle.queueCriminal.peek().useVehicle());
//                        break;
//                    }
//                    else break;
//                case 14:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(battle.shoot(queueBattle.queueCriminal.peek(), queueBattle.queuePoliceman.peek()));
//                        if (queueBattle.queuePoliceman.peek().getHealthPoint() <= 0)
//                            queueBattle.queuePoliceman.remove();
//                        else
//                            queueBattle.queueCriminal.remove();
//                        break;
//                    }
//                    else break;
//
//                case 15:
//                    if ((queueBattle.queueCriminal.size() != 0) || (queueBattle.queuePoliceman.size() !=0)) {
//                        System.out.println(battle.fight(queueBattle.queuePoliceman.peek(), queueBattle.queueCriminal.peek()));
//                        if (queueBattle.queuePoliceman.peek().getHealthPoint() <= 0)
//                            queueBattle.queuePoliceman.remove();
//                        else
//                            queueBattle.queueCriminal.remove();
//                        break;
//                    }
//                    else break;
//            }
//        }
//    }
//}
