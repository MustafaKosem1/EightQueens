import java.util.ArrayList;
//Satranç tahtasının o anki durumunu temsil eden class
public class State {
    //Satranç tahtasının her bir sütunu indexine denk gelecek şekilde, o sütunun kaçıncı satırında vezir
    //olduğunu tutan liste
    private ArrayList<Integer> rows;
    //Bu durumda tahtada birbini yiyen vezir çifti sayısı
    private int score;

    public State(ArrayList<Integer> rows) {
        this.rows = rows;
        score=0;
    }

    public State(){}

    public ArrayList<Integer> getRows() {
        return rows;
    }

    public void setRows(ArrayList<Integer> rows) {
        this.rows = rows;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public String toString() {
        return "State{" +
                "rows=" + rows +
                ", score=" + score +
                '}';
    }

    //Birbirini yiyen vezir çiftlerini hesaplayan fonksiyon
    public void calculateScore(){
        score=0;
        //tüm sütunları dolaşan döngü
        for(int column=0; column<8; column++){
            int row= rows.get(column);
            //aynı satırda başka vezir var mı diye kontrol ediliyor
            for (int x: rows) {
                if(x==row){
                    score++;
                }
            }
            //üstte kendisini de farklı veziy gibi saydığı için bir eksiltiyoruz
            score--;
            //çapraz yeme durumlarının kontrolü
            for (int x=0; x<8; x++){
                //kendisi ile kıyaslamadığının kontrolü
                if(column!=x){
                    //satır sütun sayısı toplamı aynı ise sola yatık çaprazda aynı hizada olur.
                    if(((column+row)==(rows.get(x)+x))){
                        score++;
                    }
                    //satır sütun farkı aynı ise sağa yatık çaprazda aynı hizada olur.
                    if(((row-column)==(rows.get(x)-x))){
                        score++;
                    }
                }
            }
        }
        //kaç çift olduğunu aradığımız için yarısını alıyoruz.
        score=score/2;
    }

}
