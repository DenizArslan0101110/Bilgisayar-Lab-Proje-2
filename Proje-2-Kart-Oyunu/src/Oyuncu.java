import java.util.ArrayList;
import java.util.Random;


public class Oyuncu
{
    Random random = new Random();


    Ucak ucak = new Ucak(0,15,10,"Hava","Uçak",10);         ///uçak
    Siha siha = new Siha(0,20,10,"Hava","Siha",10,20);      ///Siha
    Obus obus = new Obus(0,20,10,"Kara","Obus",5);          ///Obüs
    KFS kfs = new KFS(0,10,10,"Kara","KFS",10,20);          ///Zenci
    Firkateyn firkateyn = new Firkateyn(0,25,10,"Deniz","Firkateyn",5);         ///Firkateyn
    Sida sida = new Sida(0,15,10,"Deniz","Sida",10,10);     ///Sida


    int OyuncuID; // 1 insan, 0 makine
    String OyuncuAdi;
    int skor;
    int card_score = 0;
    int random_int;


    Oyuncu(int OyuncuID,String OyuncuAdi,int skor){
        this.OyuncuID = OyuncuID;
        this.OyuncuAdi = OyuncuAdi;
        this.skor = skor;
    }

    private final ArrayList<SavasAraclari> Playing_Cards = new ArrayList<>();     /// Bu bizim destemiz.

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
    public void ShowCards(){

        for (SavasAraclari playingCard : Playing_Cards) {
            if (playingCard instanceof Ucak tempt) {
                System.out.println(tempt.altSinif);
            }
            else if (playingCard instanceof Obus tempt) {
                System.out.println(tempt.altSinif);
            }
            else if (playingCard instanceof Firkateyn tempt) {
                System.out.println(tempt.altSinif);
            }
        }
    }




}
