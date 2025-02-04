import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Random;

public class Oyuncu
{
    Random random = new Random();
    boolean OyuncuID; // true insan, false makine
    String OyuncuAdi;
    int skor;
    short card_score = 0;
    byte random_byte;

    Oyuncu(boolean OyuncuID,String OyuncuAdi,int skor){
        this.OyuncuID = OyuncuID;
        this.OyuncuAdi = OyuncuAdi;
        this.skor = skor;
    }

    public ArrayList<SavasAraclari> Playing_Cards = new ArrayList<>();

    public void ShuffleCards(int card_number)
    {
        for(int i = 0 ; i < card_number ; i++)
        {
            random_byte = (byte)random.nextInt(6);
            switch(random_byte)
            {
                case 0:
                    Playing_Cards.add(new Ucak(skor,20,10,"Hava","Uçak",10)); break;
                case 1:
                    Playing_Cards.add(new Obus(skor,20,10,"Kara","Obüs",5)); break;
                case 2:
                    Playing_Cards.add(new Firkateyn(skor,25,10,"Deniz","Fırkateyn",5)); break;
                case 3:
                    if(card_score >= 20) Playing_Cards.add(new Siha(skor,15,10,"Hava","Siha",10,20));
                    else Playing_Cards.add(new Ucak(skor,20,10,"Hava","Uçak",10));
                    break;
                case 4:
                    if(card_score >= 20) Playing_Cards.add(new KFS(skor,10,10,"Kara","KFS",10,20));
                    else Playing_Cards.add(new Obus(skor,20,10,"Kara","Obüs",5));
                    break;
                case 5:
                    if(card_score >= 20) Playing_Cards.add(new Sida(skor,15,10,"Deniz","Sida",10,10));
                    else Playing_Cards.add(new Firkateyn(skor,25,10,"Deniz","Fırkateyn",5));
                    break;
            }
        }
    }

    public void CopyCards(ArrayList<CardForGraphics> cardsinfo)
    {
        byte iforplayer=0;
        byte iforpc=0;
        for (SavasAraclari playingCard : Playing_Cards)
        {
            if (playingCard instanceof Ucak tempt && playingCard.dayaniklilik>0)
            {
                if(!this.OyuncuID){cardsinfo.add(new CardForGraphics((short)0, (short)0, (short)100, (short)150, 0, "Visual/backside.png", "ucak", this.OyuncuID, iforpc, (byte)playingCard.dayaniklilik, (short)playingCard.seviyePuani, (byte)playingCard.vurus, playingCard.is_used, false));iforpc++;}
                if(this.OyuncuID){cardsinfo.add(new CardForGraphics((short)0, (short)0, (short)100, (short)150, 0, "Visual/uçak.png", "ucak", this.OyuncuID, iforplayer, (byte)playingCard.dayaniklilik, (short)playingCard.seviyePuani, (byte)playingCard.vurus, playingCard.is_used, false));iforplayer++;}
                System.out.println(this.OyuncuID+" "+tempt.altSinif+" hp: "+playingCard.dayaniklilik);
            }
            else if (playingCard instanceof Obus tempt && playingCard.dayaniklilik>0)
            {
                if(!this.OyuncuID){cardsinfo.add(new CardForGraphics((short)0, (short)0, (short)100, (short)150, 0, "Visual/backside.png", "obus", this.OyuncuID, iforpc, (byte)playingCard.dayaniklilik, (short)playingCard.seviyePuani, (byte)playingCard.vurus, playingCard.is_used, false));iforpc++;}
                if(this.OyuncuID){cardsinfo.add(new CardForGraphics((short)0, (short)0, (short)100, (short)150, 0, "Visual/obüs.png", "obus", this.OyuncuID, iforplayer, (byte)playingCard.dayaniklilik, (short)playingCard.seviyePuani, (byte)playingCard.vurus, playingCard.is_used, false));iforplayer++;}
                System.out.println(this.OyuncuID+" "+tempt.altSinif+playingCard.dayaniklilik);
            }
            else if (playingCard instanceof Firkateyn tempt && playingCard.dayaniklilik>0)
            {
                if(!this.OyuncuID){cardsinfo.add(new CardForGraphics((short)0, (short)0, (short)100, (short)150, 0, "Visual/backside.png", "firkateyn", this.OyuncuID, iforpc, (byte)playingCard.dayaniklilik, (short)playingCard.seviyePuani, (byte)playingCard.vurus, playingCard.is_used, false));iforpc++;}
                if(this.OyuncuID){cardsinfo.add(new CardForGraphics((short)0, (short)0, (short)100, (short)150, 0, "Visual/fırkateyn.png", "firkateyn", this.OyuncuID, iforplayer, (byte)playingCard.dayaniklilik, (short)playingCard.seviyePuani, (byte)playingCard.vurus, playingCard.is_used, false));iforplayer++;}
                System.out.println(this.OyuncuID+" "+tempt.altSinif+" hp: "+playingCard.dayaniklilik);
            }
            else if (playingCard instanceof KFS tempt && playingCard.dayaniklilik>0)
            {
                if(!this.OyuncuID){cardsinfo.add(new CardForGraphics((short)0, (short)0, (short)100, (short)150, 0, "Visual/backside.png", "kfs", this.OyuncuID, iforpc, (byte)playingCard.dayaniklilik, (short)playingCard.seviyePuani, (byte)playingCard.vurus, playingCard.is_used, false));iforpc++;}
                if(this.OyuncuID){cardsinfo.add(new CardForGraphics((short)0, (short)0, (short)100, (short)150, 0, "Visual/KFS.png", "kfs", this.OyuncuID, iforplayer, (byte)playingCard.dayaniklilik, (short)playingCard.seviyePuani, (byte)playingCard.vurus, playingCard.is_used, false));iforplayer++;}
                System.out.println(this.OyuncuID+" "+tempt.altSinif+playingCard.dayaniklilik);
            }
            else if (playingCard instanceof Sida tempt && playingCard.dayaniklilik>0)
            {
                if(!this.OyuncuID){cardsinfo.add(new CardForGraphics((short)0, (short)0, (short)100, (short)150, 0, "Visual/backside.png", "sida", this.OyuncuID, iforpc, (byte)playingCard.dayaniklilik, (short)playingCard.seviyePuani, (byte)playingCard.vurus, playingCard.is_used, false));iforpc++;}
                if(this.OyuncuID){cardsinfo.add(new CardForGraphics((short)0, (short)0, (short)100, (short)150, 0, "Visual/sida.png", "sida", this.OyuncuID, iforplayer, (byte)playingCard.dayaniklilik, (short)playingCard.seviyePuani, (byte)playingCard.vurus, playingCard.is_used, false));iforplayer++;}
                System.out.println(this.OyuncuID+" "+tempt.altSinif+" hp: "+playingCard.dayaniklilik);
            }
            else if (playingCard instanceof Siha tempt && playingCard.dayaniklilik>0)
            {
                if(!this.OyuncuID){cardsinfo.add(new CardForGraphics((short)0, (short)0, (short)100, (short)150, 0, "Visual/backside.png", "siha", this.OyuncuID, iforpc, (byte)playingCard.dayaniklilik, (short)playingCard.seviyePuani, (byte)playingCard.vurus, playingCard.is_used, false));iforpc++;}
                if(this.OyuncuID){cardsinfo.add(new CardForGraphics((short)0, (short)0, (short)100, (short)150, 0, "Visual/siha.png", "siha", this.OyuncuID, iforplayer, (byte)playingCard.dayaniklilik, (short)playingCard.seviyePuani, (byte)playingCard.vurus, playingCard.is_used, false));iforplayer++;}
                System.out.println(this.OyuncuID+" "+tempt.altSinif+" hp: "+playingCard.dayaniklilik);
            }
        }
    }

    public ArrayList<Integer> kartSec(int x, int y, int z)
    {
        LinkedHashSet<Integer> isChosen1 = new LinkedHashSet<>();
        isChosen1.add(x);
        isChosen1.add(y);
        isChosen1.add(z);
        return new ArrayList<>(isChosen1);
    }

    public static void SkorGoster(Graphics g, ArrayList<String> stringer)
    {
        g.drawString(stringer.get(4), 20, 800);
        g.drawString(stringer.get(5), 1000, 60);
    }

    public static void SendCardToWar(int index_in_deck, int index_in_battle, ArrayList<CardForGraphics> cardsinfo, boolean player_called_this)
    {
        for(CardForGraphics card : cardsinfo)
        {
            if(player_called_this && card.index_in_deck == index_in_deck && card.owners_id)
            {
                card.SetYPos((short)435);
                card.SetXPos((short)(335+index_in_battle*150));
                card.SetRotation(0);
                card.in_battle_rn = true;
                card.used_or_not = true;
            }
            else if(!player_called_this && card.index_in_deck == index_in_deck && !card.owners_id)
            {
                card.SetYPos((short)235);
                card.SetXPos((short)(335+index_in_battle*150));
                card.SetRotation(0);
                card.in_battle_rn = true;
                card.used_or_not = true;
                card.path = ReturnPath(card.machine_id);
            }
        }
    }

    public static String ReturnPath(String machine_id)
    {
        return switch (machine_id) {
            case "ucak" -> "Visual/uçak.png";
            case "obus" -> "Visual/obüs.png";
            case "firkateyn" -> "Visual/fırkateyn.png";
            case "siha" -> "Visual/siha.png";
            case "kfs" -> "Visual/KFS.png";
            case "sida" -> "Visual/sida.png";
            default -> "null";
        };
    }

    public static void SendCardBackHome(int index_in_deck, ArrayList<CardForGraphics> cardsinfo)
    {
        for(CardForGraphics card : cardsinfo)
        {
            if      (card.owners_id && card.index_in_deck == index_in_deck)     card.in_battle_rn = false;
            else if (!card.owners_id && card.index_in_deck == index_in_deck)
            {
                card.in_battle_rn = false;
                card.path = "Visual/backside.png";
            }

        }
    }

}
