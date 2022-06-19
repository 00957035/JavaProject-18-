package Game;

import java.util.Comparator;

public class QuestionSort  implements Comparator<Questions> {
    public int compare(Questions a, Questions b) 	{
        return b.error - a.error;
    }
}
