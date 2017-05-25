package hutchison_teach.ca.testme;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr. Hutchison on 2017-05-24.
 */

public class Question {
    String question;
    String _id;
    ArrayList<Answer> answers;

    public Question(int i, String s){
        _id=Integer.toString(i);
        question = s;
        answers = new ArrayList<>();
    }

    public void addAnswer(Answer ans){
        answers.add(ans);
    }
    public List<Answer> getAnswers(){
        return answers;
    }
    public int numAnswers(){
        return answers.size();
    }
    public String getQuestion(){
        return question;
    }


}
