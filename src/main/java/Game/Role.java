package Game;

public class Role {
    private String name;
    private int hp;
    private int offense;
    private int defense;

    public Role(String name, int hp, int offense, int defense) {
        this.name = name;
        this.hp = hp;
        this.offense = offense;
        this.defense = defense;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }

    public int getOffense() {
        return offense;
    }

    public void setOffense(int offense) {
        this.offense = offense;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public void attack(Role enemy) {
        int damage = offense - enemy.getDefense();
        enemy.setHp(enemy.getHp() - damage);
    }

    @Override
    public String toString() {
        return "Role [name=" + name + ", hp=" + hp + ", offense=" + offense + ", defense=" + defense + "]";
    }
}
