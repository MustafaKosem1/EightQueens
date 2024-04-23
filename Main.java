import java.util.ArrayList;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        //15 kez problem çözülüyor.
        for(int x=0;x<15;x++) {
            long startTime = System.nanoTime();
            int randomRestartCounter = 0;
            int yerDegistirmeCounter = 0;
            //hill climbing algoritması başında rastgele bir durumda başlıyor
            State currentState = new State(randomRestart());
            currentState.calculateScore();
            while (true) {
                //eğer hiç birbirini yeme durumu yoksa çözüm bulunmuştur ve döngüden çıkar.
                if (currentState.getScore() == 0) {
                    break;
                }
                //önce tüm komşuları bulunuyor. sonra bunlar arasından en iyisi bulunuyor.
                ArrayList<State> neighbors = findNeighbors(currentState);
                State bestNeighbor = findBestNeighbor(neighbors);
                //eğer en iyi komşu şu anki durumdan daha iyi değilse yerel optimumda takılmış demektir.
                //bu durumda random restart yapılıyor.
                if (!(bestNeighbor.getScore() < currentState.getScore())) {
                    currentState = new State(randomRestart());
                    currentState.calculateScore();
                    randomRestartCounter++;
                }
                //eğer komşu daha iyiyse yeni durumu bu komşu yapıyoruz
                else {
                    currentState = bestNeighbor;
                }
                yerDegistirmeCounter++;
            }
            long stopTime = System.nanoTime();
            //çıktıların konsola yazdırılması
            System.out.println((x+1)+". Tekrar");
            System.out.println("Random restart sayısı: " + randomRestartCounter);
            System.out.println("Yer değiştirme sayısı: " + yerDegistirmeCounter);
            System.out.println("İşlem süresi(microsecond): " + (stopTime - startTime)/1000);
            printChessBoard(currentState.getRows());
            System.out.println("********************************************************************");
        }
    }
    //bütün komşuları bulan fonksiyon
    public static ArrayList<State> findNeighbors(State state){
        ArrayList<State> stateList=new ArrayList<State>();
        ArrayList<Integer> rows=state.getRows();
        //her sütundaki veziri sadece o sütun içerisinde olacak şekilde tüm satırlara koyarak yeni durumları
        //buluyor. toplam 56 komşu durum ortaya çıkar.
        for(int column=0; column<8; column++){
            int row=rows.get(column);
            for(int x=0; x<8; x++){
                ArrayList<Integer> tempRows=new ArrayList<>(rows);
                if(x!=row){
                    tempRows.set(column,x);
                    State neighborState=new State(tempRows);
                    neighborState.calculateScore();
                    stateList.add(neighborState);
                }
            }
        }
        return stateList;
    }

    //en iyi komşuyu bulan fonksiyon
    public static State findBestNeighbor(ArrayList<State> states){
        State best=new State();
        best=states.get(0);
        for (State state:states) {
            if(state.getScore()<best.getScore()){
                best=state;
            }
        }
        return best;
    }

    //her sütuna bir veziri rastgele koyacak şekilde yeniden başlatan random restart fonksiyonu
    public static ArrayList<Integer> randomRestart(){
        Random random= new Random();
        ArrayList<Integer> rows= new ArrayList<Integer>(8);
        for(int i=0; i<8;i++){
            rows.add(random.nextInt(8));
        }
        return rows;
    }

    //satranç tahtasını konsola çizen fonksiyon
    public static void printChessBoard(ArrayList<Integer> rows) {
        for (int i = 0; i < 8; i++) {
            System.out.print("|");
            for (int j = 0; j < 8; j++) {
                if (i == rows.get(j)) {
                    System.out.print("X|");
                } else {
                    System.out.print(" |");
                }
            }
            System.out.println();
        }
    }
}
