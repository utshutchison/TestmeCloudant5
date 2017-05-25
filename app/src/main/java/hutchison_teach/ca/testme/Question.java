package hutchison_teach.ca.testme;

import java.util.ArrayList;

/**
 * Created by Mr. Hutchison on 2017-05-24.
 */

public class Question {
    String question;
    ArrayList<Answer> answers;

    public Question(String s){
        question = s;
        answers = new ArrayList<>();
    }

    public void addAnswer(Answer ans){
        answers.add(ans);
    }
    public int numAnswers(){
        return answers.size();
    }
    public String getQuestion(){
        return question;
    }


}
