package hutchison_teach.ca.testme;

/**
 * Created by Mr. Hutchison on 2017-05-24.
 */

public class Answer {
    int num;
    String text;

    public Answer(int n, String s){
        num = n;
        text = s;
    }

    @Override
    public String toString(){
        return text + " survey says " + num;
    }

}
