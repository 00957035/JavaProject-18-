package Game;

public class Questions {
    String question, option1, option2, option3, option4, ans;
    int error;
    public Questions(String question, int error, String option1, String option2, String option3, String option4, String ans){
        this.question = question;
        this.error = error;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.ans = ans;
    }

    public String toString() {
        return  question+ " " + error + " " + ans;
    }

}
