public class Attacks {
    private String name;
    private int cost;
    private int damage;
    private String original;
    private int RequiredLevel;

    public Attacks(String name,int cost,int damage,String original,int RequiredLevel){
        this.name=name;
        this.cost=cost;
        this.damage=damage;
        this.original=original;
        this.RequiredLevel=RequiredLevel;
    }
    public String getName(){return this.name;}
    public int getCost(){return this.cost;}
    public int getDamage(){return this.damage;}
    public String getOriginal(){return this.original;}
    public int getRequiredLevel(){return this.RequiredLevel;}

}