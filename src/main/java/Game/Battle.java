package Game;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Battle{
    protected Role role1;
    protected Role role2;
    public static final int CONTINUE = 1;
    public static final int OVER = 2;
    private JFrame gameFrame;
    private JTextField player1HpField;
    private JTextField player2HpField;

    public Battle(Role role1, Role role2, GameGUI gameFrame) {
        this.role1 = role1;
        this.role2 = role2;
        this.gameFrame = gameFrame;
        this.player1HpField = gameFrame.getPlayer1HpField();
        this.player2HpField = gameFrame.getPlayer2HpField();
    }

    public int play(boolean firstPlayer) {
        if(firstPlayer){
            role1.attack(role2);
            if(role2.getHp() < 0) role2.setHp(0);
        }
        else{
            role2.attack(role1);
            if(role1.getHp() < 0) role1.setHp(0);
        }
        player1HpField.setText(Integer.toString(role1.getHp()));
        player2HpField.setText(Integer.toString(role2.getHp()));
        if(role1.getHp() == 0 || role2.getHp() == 0) return OVER;
        return CONTINUE;
    }
}
