# EightQueens
8 Vezir problemini Hill Climbing algoritması ile çözen uygulama.

Hill climbing algoritması bir local search algoritmasıdır. Yapay zeka alanında optimizasyon problemleri için kullanılmaktadır. Ana mantığı şu anki state(durum)'e komşu olan state'ler arasındaki en iyi state'e geçerek hedeflenen state'e erişilmesidir.

8 vezir problemi ise satranç tahtasına 8 tane vezirin birbirlerini tehdit etmeyecek durumlarda yerleştirilmesi ve birbirini yemedikleri bu durumların bulunmasını içeren bir problemdir.

Bu uygulama 8 vezir problemini hill climgbing ile çözen bir Java uygulamasıdır. State class'ı tahtanın o anki durumunu ifade etmektedir. Main classta ise hill climbing algoritması ve onun işlemesi için gereken bazı fonksiyonlar bulunmaktadır. State class'ında satranç tahtası tek boyutlu dizi olarak tutulmuştur ve problem daha etkin çözülmüştür. Bu dizinin her index'i bir sütunu ifade etmektedir. Bu dizinin her bir elemanı ise o sütunda kaçıncı satırda vezir olduğunu ifade etmektedir. Main class'ta ise sürekli olarak şu anki durumdan daha iyi duruma ilerlenerek çözüm bulunmaktadır. Eğer local optimumda takılınırsa (şu anki durumdan daha iyi bir komşu durumun olmaması durumu) random restart ile algoritma tekrardan çalışmaktadır.
