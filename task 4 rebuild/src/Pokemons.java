import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
public class Pokemons{
    private final Pokemon[] POKEMONS;
    private final Attacks[] ATTACKS;
    private final Attacks scratch,flameTail,fieryBlast,liveCoal,fireClaws,assistingHeater,fireWing,quickAttack,electroBall,
            electricSurfer,flop,zapKick,thunder,thunderPunch,kick;
    private final Pokemon electabuzz,blitzle,pichu,moltres,salandit,charmander;
    public Pokemons(){
        POKEMONS=new Pokemon[6];
        ATTACKS=new Attacks[15];

        charmander=new Pokemon("charmander", "fire","charmander",1, 80, 40,80,40);
        salandit=new Pokemon("salandit","fire","salandit",1,100,60,100,60);
        moltres=new Pokemon("moltres","fire","moltres",1,120,60,120,60);
        pichu=new Pokemon("pichu","elecricity","pichu",1,40,30,40,30);
        blitzle=new Pokemon("blitzle","elecricity","blitzle",1,90,35,90,35);
        electabuzz=new Pokemon("electabuzz","elecricity","electabuzz",1,30,100,30,100);

        scratch=new Attacks("scratch",15,new Random().nextInt(6)+25,"charmander",1);
        flameTail=new Attacks("flame tail",40,new Random().nextInt(21)+30,"charmander",2);
        fieryBlast=new Attacks("fiery blast",50,50,"charmander",3);
        liveCoal=new Attacks("live coal",10,new Random().nextInt(26),"salandit",1);                         //salandit
        fireClaws=new Attacks("fire claws",25,new Random().nextInt(51),"salandit",2);
        assistingHeater=new Attacks("Assisting Heater",30,new Random().nextInt(11)+50,"moltres",1); //moltres
        fireWing=new Attacks("fire wing",30,30,"moltres",1);
        quickAttack=new Attacks("quick attack",5,10,"pichu",1);                                          //pichu
        electroBall=new Attacks("electro ball",10,new Random().nextInt(11)+30,"pichu",2);
        electricSurfer=new Attacks("electric surfer",60,new Random().nextInt(21)+100,"pichu",3);
        flop=new Attacks("flop",20,new Random().nextInt(6)+20,"blitzle",1);                          //blitzle
        zapKick=new Attacks("zap kick",30,new Random().nextInt(6)+30,"blitzle",2);
        thunder=new Attacks("thunder",60,new Random().nextInt(11)+40,"electabuzz",1);                   //electabuzz
        thunderPunch=new Attacks("thunder punch",80,new Random().nextInt(71)+50,"electabuzz",2);
        kick=new Attacks("kick",0,2,"all",1);
    }
    public static Pokemon currentPlayer,player1,player2;
    public static int charge=0,counter=3,gameOver=0,specialAttack1=1,specialAttack2=1,tripleDamage1=1,tripleDamage2=1,possible;
    public void game(Pokemons pokemons) {
        createPlayers(pokemons);
        System.out.println();
        player1.setAttackPoints(player1.getAttackPoints()*3/4);
        player2.setAttackPoints(player2.getAttackPoints()*3/4);
        do {
            if(counter%2==0){
                currentPlayer=player2;
            }else {
                currentPlayer=player1;
            }
            printPlayerStats();
            if(gameOver!=0){
                break;
            }
            printOptions(pokemons);
            System.out.println();
            charge+=5;
            if(currentPlayer.getMaxHitPoints()-currentPlayer.getHitPoints()<=4){
                currentPlayer.setHitPoints(currentPlayer.getHitPoints()*0+currentPlayer.getMaxHitPoints());
            }else {
                currentPlayer.setHitPoints(currentPlayer.getHitPoints()+4);
            }
            if(currentPlayer.getMaxAttackPoints()-currentPlayer.getAttackPoints()<=4){
                currentPlayer.setAttackPoints(currentPlayer.getAttackPoints()*0+currentPlayer.getMaxAttackPoints());
            }else {
                currentPlayer.setAttackPoints(currentPlayer.getAttackPoints()+4);
            }
            counter++;
        } while (gameOver==0);
    }
    public static void printOptions(Pokemons pokemons) {
        Scanner scanner = new Scanner(System.in);
        final String option1="1",option2="2",option3="3",option4="4";
        String option;
        do{
            System.out.println("type 1 to attack");
            System.out.println("type 2 to pass");
            isEvolvePossible();
            isSpecialAttackPossible();
            option = scanner.next();
            if(possible==0 && option.equals("3")){
                System.out.println("YOU CANT EVOLVE!!");
                printOptions(pokemons);
            }
        } while (!option.equals(option1) && !option.equals(option2) && !option.equals(option3) && !option.equals(option4));
        if (option.equals(option1)) {
            attackMenu(pokemons);
        }
        if (option.equals(option2)) {
            passOption();
        }
        if (option.equals(option3)&&possible==1) {
            evolvePlayer(pokemons);
        }
        if (option.equals(option4)) {
            specialAttack(pokemons);
        }
    }
    public static void listOfAttacks(Pokemons pokemons){
        pokemons.ATTACKS[0]=pokemons.scratch;
        pokemons.ATTACKS[1]=pokemons.flameTail;
        pokemons.ATTACKS[2]=pokemons.fieryBlast;
        pokemons.ATTACKS[3]=pokemons.liveCoal;
        pokemons.ATTACKS[4]=pokemons.fireClaws;
        pokemons.ATTACKS[5]=pokemons.assistingHeater;
        pokemons.ATTACKS[6]=pokemons.fireWing;
        pokemons.ATTACKS[7]=pokemons.quickAttack;
        pokemons.ATTACKS[8]=pokemons.electroBall;
        pokemons.ATTACKS[9]=pokemons.electricSurfer;
        pokemons.ATTACKS[10]=pokemons.flop;
        pokemons.ATTACKS[11]=pokemons.zapKick;
        pokemons.ATTACKS[12]=pokemons.thunder;
        pokemons.ATTACKS[13]=pokemons.thunderPunch;
        pokemons.ATTACKS[14]=pokemons.kick;
    }
    public static void attackMenu(Pokemons pokemons) {
        Scanner scanner=new Scanner(System.in);
        listOfAttacks(pokemons);

        int counter=0;
        int[] currentPlayerAttacks=new int[1];
        for(int i=0; i<pokemons.ATTACKS.length;i++) {
            if(currentPlayer.getOriginal().equals(pokemons.ATTACKS[i].getOriginal())&&currentPlayer.getLevel()>=pokemons.ATTACKS[i].getRequiredLevel()){
                currentPlayerAttacks[counter]=i;
                currentPlayerAttacks=Arrays.copyOf(currentPlayerAttacks,currentPlayerAttacks.length+1);
                counter++;
                System.out.println("press "+counter+" for "+pokemons.ATTACKS[i].getName());
            }
        }
        currentPlayerAttacks[counter]=14;
        int kick=counter+1;
        System.out.println("press "+kick+" for "+pokemons.kick.getName());
        int option1=scanner.nextInt();
        String option=Integer.toString(option1);
        String counterToString=Integer.toString(kick);
        final String attackOption1="1",attackOption2="2",attackOption3="3";
        if(!option.equals(attackOption1) && !option.equals(attackOption2)&& !option.equals(attackOption3) && !option.equals(counterToString)){
            attackMenu(pokemons);
        }
        float add=0, damage=1;
        if(currentPlayer.getType().equals("electricity")){
             add=charge;
        }
        if(currentPlayer.getAttackPoints()-pokemons.ATTACKS[currentPlayerAttacks[option1-1]].getCost()>=0){
            checkPlayerType();
            if(currentPlayer.equals(player1)) {
                currentPlayer.setAttackPoints(currentPlayer.getAttackPoints()-pokemons.ATTACKS[currentPlayerAttacks[option1-1]].getCost());
                damage=pokemons.ATTACKS[currentPlayerAttacks[option1-1]].getDamage()*add/100;
                player2.setHitPoints(player2.getHitPoints()-pokemons.ATTACKS[currentPlayerAttacks[option1-1]].getDamage()*tripleDamage1-damage);
                tripleDamage1=1;
            }else {
                currentPlayer.setAttackPoints(currentPlayer.getAttackPoints()-pokemons.ATTACKS[currentPlayerAttacks[option1-1]].getCost());
                player1.setHitPoints(player1.getHitPoints()-pokemons.ATTACKS[currentPlayerAttacks[option1-1]].getDamage()*tripleDamage2-damage);
                tripleDamage2=1;
            }
        }else {
            System.out.println("YOUR ATTACK POINTS ARE TOO LOW!!");
            attackMenu(pokemons);
        }
    }

    public static void passOption() {
        int bonusType = new Random().nextInt(3);
        Random range1 = new Random(30 - 5);
        Random range2 = new Random(40);
        if(bonusType==0) {
            if (currentPlayer.getMaxHitPoints() - currentPlayer.getHitPoints() <= range1.nextInt() + 6) {
                currentPlayer.setHitPoints(currentPlayer.getHitPoints() + range1.nextInt() + 6);
                System.out.println("you got hit points bonus");
            } else {
                currentPlayer.setHitPoints(currentPlayer.getHitPoints() * 0 + currentPlayer.getMaxHitPoints());
                System.out.println("you got hit points bonus");
            }
        }
        if(bonusType==1) {
            if (currentPlayer.getMaxAttackPoints() - currentPlayer.getAttackPoints() <= range2.nextInt() + 1) {
                currentPlayer.setAttackPoints(currentPlayer.getAttackPoints() + range2.nextInt() + 1);
                System.out.println("you got attack points bonus");
            } else {
                currentPlayer.setAttackPoints(currentPlayer.getAttackPoints() * 0 + currentPlayer.getMaxAttackPoints());
                System.out.println("you got attack points bonus");
            }
        }
        if (bonusType == 2) {
            if (currentPlayer.equals(player1) && tripleDamage1 == 1) {
                tripleDamage1 = 3;
                System.out.println("you got triple attack damage in the next round");
            }else {
                if(currentPlayer.equals(player1) && tripleDamage1 == 3) {
                    System.out.println("SORRY, YOU ALREADY HAVE TRIPLE DAMAGE!!");
                }
            }
            if (currentPlayer.equals(player2) && tripleDamage2 == 1) {
                tripleDamage2 = 3;
                System.out.println("you got triple attack damage in the next round");
            }else {
                if(currentPlayer.equals(player2)&& tripleDamage2==3) {
                    System.out.println("SORRY, YOU ALREADY HAVE TRIPLE DAMAGE!!");
                }
            }
        }
    }

    public static void isEvolvePossible() {
        if (currentPlayer.getLevel() == 3 || currentPlayer.getName().equals("moltres")) {
            System.out.println("MAX LEVEL!!");
            possible=0;
            return;
        }else {
            if (currentPlayer.getLevel() == 2 && (currentPlayer.getName().equals("salazzle") || currentPlayer.getName().equals("zebstrika") || currentPlayer.getName().equals("electivire"))) {
                System.out.println("MAX LEVEL!!");
                possible=0;
                return;
            }
        }
        if(currentPlayer.getLevel()==1 && (currentPlayer.getHitPoints()<=20||currentPlayer.getAttackPoints()<25)){
            System.out.println("YOUR HIT POINTS OR ATTACK POINTS ARE TOO LOW TO EVOLVE!!");
            possible=0;
            return;
        }
        if(currentPlayer.getLevel()==2 && (currentPlayer.getHitPoints()<=30||currentPlayer.getAttackPoints()<40)){
            System.out.println("YOUR HIT POINTS OR ATTACK POINTS ARE TOO LOW TO EVOLVE!!");
            possible=0;
            return;
        }
        System.out.println("type 3 to evolve");
        possible=1;
    }
    public static void printPlayerStats(){
        if(currentPlayer.getHitPoints()<=0) {
            if (counter % 2 == 0) {
                System.out.println(player1.getName()+" WON!!");
            }else {
                System.out.println(player2.getName()+" WON!!");
            }
            gameOver+=1;
        }else {
            System.out.println(currentPlayer.getName()+":"+" hit points: "+currentPlayer.getHitPoints()+" attack points: "+currentPlayer.getAttackPoints());
        }
    }
    public static void createPlayers(Pokemons pokemons){
        pokemons.POKEMONS[0]=pokemons.charmander;
        pokemons.POKEMONS[1]=pokemons.salandit;
        pokemons.POKEMONS[2]=pokemons.moltres;
        pokemons.POKEMONS[3]=pokemons.pichu;
        pokemons.POKEMONS[4]=pokemons.blitzle;
        pokemons.POKEMONS[5]=pokemons.electabuzz;
        Random randomPokemon1=new Random();
        Random randomPokemon2=new Random();
        player1=pokemons.POKEMONS[randomPokemon1.nextInt(pokemons.POKEMONS.length)];
        player2=pokemons.POKEMONS[randomPokemon2.nextInt(pokemons.POKEMONS.length)];
        System.out.println("player 1 is: "+player1.getName());
        System.out.println("player 2 is: "+player2.getName());
    }

    public static void evolvePlayer(Pokemons pokemons) {
        float hitPoints,attackPoints;
        String previousName=currentPlayer.getName();
        hitPoints=currentPlayer.getHitPoints();
        attackPoints=currentPlayer.getAttackPoints();
        if(currentPlayer.getLevel()==1 && !currentPlayer.getName().equals(pokemons.moltres.getName())){
            if (currentPlayer.getName().equals(pokemons.charmander.getName())) {
                currentPlayer.setName("charmeleon");currentPlayer.setLevel(2); currentPlayer.setMaxHitPoints(90);currentPlayer.setMaxAttackPoints(60);
            }
            if (currentPlayer.getName().equals(pokemons.salandit.getName())) {
                currentPlayer.setName("salazzle");currentPlayer.setLevel(2); currentPlayer.setMaxHitPoints(160);currentPlayer.setMaxAttackPoints(80);
            }
            if (currentPlayer.getName().equals(pokemons.pichu.getName())) {
                currentPlayer.setName("pikachu");currentPlayer.setLevel(2); currentPlayer.setMaxHitPoints(50);currentPlayer.setMaxAttackPoints(40);
            }
            if (currentPlayer.getName().equals(pokemons.blitzle.getName())) {
                currentPlayer.setName("zebstrika");currentPlayer.setLevel(2); currentPlayer.setMaxHitPoints(100);currentPlayer.setMaxAttackPoints(50);

            }
            if (currentPlayer.getName().equals(pokemons.electabuzz.getName())) {
                currentPlayer.setName("electivire");currentPlayer.setLevel(2); currentPlayer.setMaxHitPoints(35);currentPlayer.setMaxAttackPoints(120);
            }
            currentPlayer.setHitPoints(hitPoints- 20);
            currentPlayer.setAttackPoints(attackPoints- 25);
            System.out.println(previousName+" EVOLVED TO: "+currentPlayer.getName()+"!!");
            return;
        }
        if(currentPlayer.getLevel()==2){
            if (currentPlayer.getName().equals("charmeleon")) {
                currentPlayer.setName("charizard");currentPlayer.setLevel(3); currentPlayer.setMaxHitPoints(130);currentPlayer.setMaxAttackPoints(80);
            }
            if (currentPlayer.getName().equals("pikachu")) {
                currentPlayer.setName("raichu");currentPlayer.setLevel(3); currentPlayer.setMaxHitPoints(160);currentPlayer.setMaxAttackPoints(80);
            }
            currentPlayer.setHitPoints(hitPoints - 30);
            currentPlayer.setAttackPoints(attackPoints - 40);
            System.out.println(previousName+" EVOLVED TO: "+currentPlayer.getName()+"!!");
        }

    }
    public static void checkPlayerType(){
        if(currentPlayer.getType().equals("fire")){
            Random chance=new Random();
            if(chance.nextInt(3)==1){
                Random minus=new Random();
                int set=minus.nextInt(3,11);
                currentPlayer.setHitPoints(currentPlayer.getHitPoints()-set);
                System.out.println("self damage: "+set);
            }
        }
    }
    public static void specialAttack(Pokemons pokemons){
        if((currentPlayer.equals(player1)&& specialAttack1==0)||(currentPlayer.equals(player2)&& specialAttack2==0)){
            System.out.println("YOU ALREADY USED YOUR SPECIAL ATTACK!!");
            printOptions(pokemons);
        }
        if(currentPlayer.getType().equals("fire")){
            currentPlayer.setHitPoints(currentPlayer.getHitPoints()*1/2);
            currentPlayer.setAttackPoints(currentPlayer.getAttackPoints()*0);
            listOfAttacks(pokemons);
            int counter=0;
            int[] currentPlayerAttacks=new int[1];
            for(int i=0; i<pokemons.ATTACKS.length;i++) {
                if(currentPlayer.getOriginal().equals(pokemons.ATTACKS[i].getOriginal())&&currentPlayer.getLevel()>=pokemons.ATTACKS[i].getRequiredLevel()){
                    currentPlayerAttacks[counter]=i;
                    currentPlayerAttacks=Arrays.copyOf(currentPlayerAttacks,currentPlayerAttacks.length+1);
                    counter++;
                }
            }
            //currentPlayerAttacks=Arrays.copyOf(currentPlayerAttacks,currentPlayerAttacks.length-1);
            Random randomAttack1 =new Random(currentPlayerAttacks.length);
            Random randomAttack2=new Random(currentPlayerAttacks.length);
            if(currentPlayer.equals(player1)) {
                player2.setHitPoints(player2.getHitPoints()-(pokemons.ATTACKS[currentPlayerAttacks[randomAttack1.nextInt(currentPlayerAttacks.length)]].getDamage()+pokemons.ATTACKS[currentPlayerAttacks[randomAttack2.nextInt(currentPlayerAttacks.length)]].getDamage()));
            }else {
                player1.setHitPoints(player1.getHitPoints()-(pokemons.ATTACKS[currentPlayerAttacks[randomAttack1.nextInt(currentPlayerAttacks.length)]].getDamage()+pokemons.ATTACKS[currentPlayerAttacks[randomAttack2.nextInt(currentPlayerAttacks.length)]].getDamage()));
            }
        }else {
            currentPlayer.setHitPoints(currentPlayer.getHitPoints()*0+currentPlayer.getMaxHitPoints());
            currentPlayer.setAttackPoints(currentPlayer.getAttackPoints()*0+currentPlayer.getMaxAttackPoints());
        }
        if(currentPlayer.equals(player1)){
            specialAttack1-=1;
        }else {
            specialAttack2-=1;
        }
    }
    public static void isSpecialAttackPossible(){
        if(currentPlayer.equals(player1)&& specialAttack1==0){
            System.out.println("NO SPECIAL ATTACK LEFT!!");
        }else {
            if(currentPlayer.equals(player2)&& specialAttack2==0) {
                System.out.println("type 4 for special attack");
            }
        }
        if(currentPlayer.equals(player2) && specialAttack2==0){
            System.out.println("NO SPECIAL ATTACK LEFT!!");
        }else{
            System.out.println("type 4 for special attack");
        }
    }
}