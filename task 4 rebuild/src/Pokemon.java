public class Pokemon {
    private String name;
    private String type;
    private String original;
    private int level;
    private int maxHitPoints;
    private int maxAttackPoints;
    private float hitPoints;
    private float attackPoints;

    public Pokemon(String name,String type,String original, int level,int maxHitPoints,int maxAttackPoints, float hitPoints, float attackPoints) {
        this.name = name;
        this.type=type;
        this.original=original;
        this.level = level;
        this.maxHitPoints=maxHitPoints;
        this.maxAttackPoints=maxAttackPoints;
        this.hitPoints = hitPoints;
        this.attackPoints = attackPoints;
    }
    public String getName(){return this.name;}
    public void setName(String name){this.name=name;}
    public String getType(){return this.type;}
    public void setType(String type){this.type=type;}
    public String getOriginal(){return this.original;}
    public int getLevel(){return this.level;}
    public void setLevel(int level){this.level=level;}
    public int getMaxHitPoints(){return this.maxHitPoints;}
    public void setMaxHitPoints(int maxHitPoints){this.maxHitPoints=maxHitPoints;}
    public int getMaxAttackPoints(){return this.maxAttackPoints;}
    public void setMaxAttackPoints(int maxAttackPoints){this.maxAttackPoints=maxAttackPoints;}
    public float getHitPoints(){return this.hitPoints;}
    public void setHitPoints(float hitPoints){this.hitPoints=hitPoints;}
    public float getAttackPoints(){return this.attackPoints;}
    public void setAttackPoints(float attackPoints){this.attackPoints=attackPoints;}
    private String getStats(){return"hit points:"+this.hitPoints+" damage: "+this.attackPoints;}
    public String toString(){return getStats();}
}