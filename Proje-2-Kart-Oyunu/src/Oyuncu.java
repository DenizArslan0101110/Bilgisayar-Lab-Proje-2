import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;


public class Oyuncu
{
    Random random = new Random();


    Ucak ucak = new Ucak(0,15,10,"Hava","Uçak",10);                             ///uçak
    Siha siha = new Siha(0,20,10,"Hava","Siha",10,20);                          ///Siha
    Obus obus = new Obus(0,20,10,"Kara","Obüs",5);                              ///Obüs
    KFS kfs = new KFS(0,10,10,"Kara","KFS",10,20);                              ///Zenci
    Firkateyn firkateyn = new Firkateyn(0,25,10,"Deniz","Fırkateyn",5);         ///Firkateyn
    Sida sida = new Sida(0,15,10,"Deniz","Sida",10,10);                         ///Sida


    boolean OyuncuID; // 1 insan, 0 makine
    String OyuncuAdi;
    int skor;
    int card_score = 0;
    int random_int;


    Oyuncu(boolean OyuncuID,String OyuncuAdi,int skor){
        this.OyuncuID = OyuncuID;
        this.OyuncuAdi = OyuncuAdi;
        this.skor = skor;
    }

    public ArrayList<SavasAraclari> Playing_Cards = new ArrayList<>();     /// Bu bizim destemiz.

    public void ShuffleCards(int card_number) {

        for(int i = 0 ; i < card_number ; i++){     /// Kart sayisini temsil ediyor.

            random_int = random.nextInt(6);
            switch(random_int){ /// Random sayi atıyor buna göre oyuncu karakterinde kart dağıtımı yapılacak.
                case 0:
                    Playing_Cards.add(ucak);
                    break;
                case 1:
                    Playing_Cards.add(obus);
                    break;
                case 2:
                    Playing_Cards.add(firkateyn);
                    break;
                case 3:
                    if(card_score >= 20)
                        Playing_Cards.add(siha);
                    else
                        Playing_Cards.add(ucak);
                    break;
                case 4:
                    if(card_score >= 20)
                        Playing_Cards.add(kfs);
                    else
                        Playing_Cards.add(obus);
                    break;
                case 5:
                    if(card_score >= 20)
                        Playing_Cards.add(sida);
                    else
                        Playing_Cards.add(firkateyn);
                    break;
            }
        }
    }
    public void ShowCards(ArrayList<CardForGraphics> cardsinfo)
    {
        short kaydir = 0;
        for (SavasAraclari playingCard : Playing_Cards)
        {
            if (playingCard instanceof Ucak tempt)
            {
                if(!this.OyuncuID) cardsinfo.add(new CardForGraphics((short)(250+kaydir), (short)50, (short)100, (short)150, 0, "Visual/uçak.png", this.OyuncuID));
                if(this.OyuncuID) cardsinfo.add(new CardForGraphics((short)(250+kaydir), (short)650, (short)100, (short)150, 0, "Visual/uçak.png", this.OyuncuID));
                System.out.println(tempt.altSinif);
            }
            else if (playingCard instanceof Obus tempt)
            {
                if(!this.OyuncuID) cardsinfo.add(new CardForGraphics((short)(250+kaydir), (short)50, (short)100, (short)150, 0, "Visual/obüs.png", this.OyuncuID));
                if(this.OyuncuID) cardsinfo.add(new CardForGraphics((short)(250+kaydir), (short)650, (short)100, (short)150, 0, "Visual/obüs.png", this.OyuncuID));
                System.out.println(tempt.altSinif);
            }
            else if (playingCard instanceof Firkateyn tempt)
            {
                if(!this.OyuncuID) cardsinfo.add(new CardForGraphics((short)(250+kaydir), (short)50, (short)100, (short)150, 0, "Visual/fırkateyn.png", this.OyuncuID));
                if(this.OyuncuID) cardsinfo.add(new CardForGraphics((short)(250+kaydir), (short)650, (short)100, (short)150, 0, "Visual/fırkateyn.png", this.OyuncuID));
                System.out.println(tempt.altSinif);
            }
            kaydir += 150;
        }
        // yalan söylüyo warning bu arada, bu fonksiyon basbaya ekleme yapıyo yapmasa kod patlar
        //cardsinfo = GraphicalUserInterface.fillImagesInAccordanceToTheirInfo(cardsinfo);
        // edit: patlamıyomuş ne iş
    }

    public ArrayList<Integer> kartSec(int x, int y, int z){       /// Insan için girilen 3 parametre için kart seç.
        LinkedHashSet<Integer> isChosen1 = new LinkedHashSet<>();

        isChosen1.add(x);
        isChosen1.add(y);
        isChosen1.add(z);

        ArrayList<Integer> isChosen = new ArrayList<>(isChosen1);
        return isChosen;
    }

    public ArrayList<Integer> kartSec(){                         /// Bilgisayar için kart seç.
        LinkedHashSet<Integer> isChosen1 = new LinkedHashSet<>();

        while(isChosen1.size() < 3) {
            isChosen1.add(random.nextInt(Playing_Cards.size()));
        }
        ArrayList<Integer> isChosen = new ArrayList<>(isChosen1);
        return isChosen;
    }

    public void SkorGöster(){
        for (int i = 0; i < Playing_Cards.size(); i++) {
            card_score += Playing_Cards.get(i).seviyePuani;
        }

        //return skor;      /// Normalde return değeri vardi yedim onu.
    }
}
