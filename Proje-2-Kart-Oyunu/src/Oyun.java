import javax.swing.Timer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Oyun
{
    static int selected_card;
    static int NumbersClicked = 0;
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        final int NUMBER_OF_CARDS = 6;
        byte Turn_number = 1;

        ArrayList<CardForGraphics> cardsinfo = new ArrayList<>();
        ArrayList<CardForGraphics> framesandmap = new ArrayList<>();
        ArrayList<CardForGraphics> safecardsinfo = new ArrayList<>();

        Oyuncu Player = new Oyuncu(true,"Oyuncu",0);
        Oyuncu bilgisayar = new Oyuncu(false,"Bilgisayar",0);
        
        ArrayList<String> stringass = new ArrayList<>();
        for(short i=0; i<8 ;i++) stringass.add("fumo");

        MakeFramesAndMap(framesandmap);

        GraphicalUserInterface gui = new GraphicalUserInterface(cardsinfo, framesandmap, safecardsinfo, Player.Playing_Cards, stringass);
        gui.window.setBounds(0, 0, gui.window.getWidth(), gui.window.getHeight());



        gui.window.add(gui);



 

        Random random = new Random();

        Player.ShuffleCards(NUMBER_OF_CARDS);
        bilgisayar.ShuffleCards(NUMBER_OF_CARDS);

        System.out.println("Insanlarin kartlari :");
        Player.CopyCards(cardsinfo);
        System.out.println("Bilgisayarin kartlari :");
        bilgisayar.CopyCards(cardsinfo);

        safecardsinfo.addAll(cardsinfo);




        // LAMBDA IS FINE ACTUALLY
        ActionListener screen_refresher = evt ->
        {
            OrderCardsIntoADeck(cardsinfo);
            ReloadImages(cardsinfo);
            gui.setBackground(Color.DARK_GRAY);
            gui.repaint();

        };

        Timer screen_refresh_timer = new Timer(1000/60, screen_refresher);
        screen_refresh_timer.start();


        // marks the main game loop
        for (; Turn_number < 50 ; Turn_number++)
        {
            NumbersClicked = 0;

            System.out.println("Kart numarasi giriniz: ");
            int kart1 = 0;
            int kart2 = 1;
            int kart3 = 2;
            int kart1Pc = 0;
            int kart2Pc = 0;
            int kart3Pc = 0;
            int randomNumber = 0;
            int numberOfUsedCardsPc= 0;
            boolean checkTheDeck = true;
            boolean checkTheDeckPc = true;
            boolean check1 = false,check2 = false;
            boolean check1Computer = false, check2Computer = false;
            LinkedHashSet<Integer> isChosen1 = new LinkedHashSet<>();


            while(isChosen1.size() < 3) {

                randomNumber = random.nextInt(bilgisayar.Playing_Cards.size());


                if(isChosen1.size() == 0){          /////////////Kart1
                    for(SavasAraclari tempt : bilgisayar.Playing_Cards){

                        if(tempt.is_used)
                            numberOfUsedCardsPc++;
                        if (numberOfUsedCardsPc == bilgisayar.Playing_Cards.size()) {
                            checkTheDeckPc = false;
                            break;
                        }
                    }

                    if(!checkTheDeckPc){      ///If all of the cards from deck is used, open all of them.
                        for(SavasAraclari tempt : bilgisayar.Playing_Cards){
                            tempt.is_used = false;
                        }
                    }


                    if(!bilgisayar.Playing_Cards.get(randomNumber).is_used){
                        isChosen1.add(randomNumber);
                        kart1Pc = randomNumber;
                    }
                }

                if(isChosen1.size() == 1){  /////////////////Kart2
                    bilgisayar.Playing_Cards.get(kart1Pc).is_used = true;

                    for(SavasAraclari tempt : bilgisayar.Playing_Cards){

                        if(tempt.is_used)
                            numberOfUsedCardsPc++;
                        if (numberOfUsedCardsPc == bilgisayar.Playing_Cards.size()) {
                            checkTheDeckPc = false;
                            break;
                        }
                    }

                    if(!checkTheDeckPc){
                        for(int i = 0 ; i < bilgisayar.Playing_Cards.size(); i++){
                            if(i != kart1Pc){
                                bilgisayar.Playing_Cards.get(i).is_used = false;
                            }
                        }
                    }

                    randomNumber = random.nextInt(bilgisayar.Playing_Cards.size());

                    if(bilgisayar.Playing_Cards.get(randomNumber).is_used == false){
                        isChosen1.add(randomNumber);
                        kart2Pc = randomNumber;
                    }
                }
                if(isChosen1.size() == 2){          ///////Kart3
                     bilgisayar.Playing_Cards.get(kart2).is_used = true;

                    for(SavasAraclari tempt : bilgisayar.Playing_Cards){

                        if(tempt.is_used)
                           numberOfUsedCardsPc++;
                        if (numberOfUsedCardsPc == bilgisayar.Playing_Cards.size()) {
                          checkTheDeckPc = false;
                          break;
                        }
                    }

                    if(!checkTheDeckPc){
                        for(int i = 0 ; i < bilgisayar.Playing_Cards.size(); i++){
                            if(i != kart1Pc || i != kart2Pc){
                                bilgisayar.Playing_Cards.get(i).is_used = false;
                            }
                        }
                    }

                    randomNumber = random.nextInt(bilgisayar.Playing_Cards.size());
                    if(bilgisayar.Playing_Cards.get(randomNumber).is_used == false){
                        isChosen1.add(randomNumber);
                        kart3Pc = randomNumber;
                    }
                }
                bilgisayar.Playing_Cards.get(kart3Pc).is_used = true;
            }

            ArrayList<Integer> isChosen = new ArrayList<>(isChosen1);

            int numberOfUsedCards = 0;

            for(SavasAraclari tempt : Player.Playing_Cards){

                if(tempt.is_used == true)
                    numberOfUsedCards++;
                if (numberOfUsedCards == Player.Playing_Cards.size()) {
                    checkTheDeck = false;
                    break;
                }
            }

            if(checkTheDeck == false){      ///If all of the cards from deck is used, open all of them.
                for(SavasAraclari tempt : Player.Playing_Cards){
                    tempt.is_used = false;
                }
            }



            while(NumbersClicked < 3)
            {
                if(NumbersClicked == 0)
                {
                    //System.out.println("Kart 1 Seçiliyor");        ///COCONUT.JPG
                    int tempt = selected_card;
                    //for(CardForGraphics card: cardsinfo)
                      //  if(card.index_in_deck==selected_card && card.owners_id && !card.used_or_not)
                            if(Player.Playing_Cards.get(tempt).is_used == false){
                                kart1 = tempt;
                            }


                    for(CardForGraphics card: cardsinfo)
                    {
                        if(card.index_in_deck==kart1 && card.owners_id && !card.is_selected_rn&&Player.Playing_Cards.get(selected_card).is_used == false)
                        {
                            card.is_selected_rn=true;
                            GiveCardInfoAsText(stringass, card, Player, bilgisayar);
                        }
                        else if(card.index_in_deck!=kart1) card.is_selected_rn=false;
                    }

                    //System.out.println(kart1);
                }
                if(NumbersClicked == 1)
                {
                    Player.Playing_Cards.get(kart1).is_used = true;

                    if(!check1){
                        numberOfUsedCards = 0;
                        for(SavasAraclari tempt : Player.Playing_Cards){
                            if(tempt.is_used == true)
                                numberOfUsedCards++;
                            if(numberOfUsedCards == Player.Playing_Cards.size())
                                checkTheDeck = false;
                        }
                        if(checkTheDeck == false){
                            for(int i = 0 ; i < Player.Playing_Cards.size(); i++){
                                if(i != kart1){
                                    Player.Playing_Cards.get(i).is_used = false;
                                }

                            }
                        }
                        check1 = true;
                    }


                    int tempt = selected_card;
                    Oyuncu.SendCardToWar(kart1,1,cardsinfo,Turn_number,true);
                    //Oyuncu.SendCardToWar(isChosen.get(0),1,cardsinfo,Turn_number,false);
                    //System.out.println("Kart 2 Seçiliyor");        ///COCONUT.JPG



                    //for(CardForGraphics card: cardsinfo)
                      //  if(card.index_in_deck==selected_card && card.owners_id && !card.used_or_not)
                            if(Player.Playing_Cards.get(tempt).is_used == false) {
                                kart2 = tempt;
                            }


                    for(CardForGraphics card: cardsinfo)
                    {
                        if(card.index_in_deck==kart2 && card.owners_id && !card.is_selected_rn&&Player.Playing_Cards.get(selected_card).is_used == false)
                        {
                            card.is_selected_rn=true;
                            GiveCardInfoAsText(stringass, card, Player, bilgisayar);
                        }
                        else if(card.index_in_deck!=kart2) card.is_selected_rn=false;
                    }
                    //System.out.println(kart2);
                }
                if(NumbersClicked == 2)
                {
                    Player.Playing_Cards.get(kart2).is_used = true;
                    if(!check2) {
                        numberOfUsedCards = 0;
                        for (SavasAraclari tempt : Player.Playing_Cards) {
                            if (tempt.is_used == true)
                                numberOfUsedCards++;
                            if (numberOfUsedCards == Player.Playing_Cards.size())
                                checkTheDeck = false;
                        }
                        if (checkTheDeck == false) {
                            for (int i = 0; i < Player.Playing_Cards.size(); i++) {
                                if (i == kart1 || i == kart2) {
                                    continue;
                                }
                                else
                                    Player.Playing_Cards.get(i).is_used = false;

                            }
                        }
                        check2 = true;
                    }

                    int tempt = selected_card;
                    Oyuncu.SendCardToWar(kart2,2,cardsinfo,Turn_number,true);
                    //Oyuncu.SendCardToWar(isChosen.get(1),2,cardsinfo,Turn_number,false);
                    //System.out.println("Kart 3 Seçiliyor");        ///COCONUT.JPG
                    //for(CardForGraphics card: cardsinfo)
                      //  if(card.index_in_deck==selected_card && card.owners_id && !card.used_or_not)
                            if(Player.Playing_Cards.get(tempt).is_used == false){
                                kart3 = tempt;
                            }


                    for(CardForGraphics card: cardsinfo)
                    {
                        if(card.index_in_deck==kart3 && card.owners_id && !card.is_selected_rn && Player.Playing_Cards.get(selected_card).is_used == false)
                        {
                            card.is_selected_rn=true;
                            GiveCardInfoAsText(stringass, card, Player, bilgisayar);
                        }
                        else if(card.index_in_deck!=kart3) card.is_selected_rn=false;
                    }
                    //System.out.println(kart3);
                }
            }
            Player.Playing_Cards.get(kart3).is_used = true;

            for(CardForGraphics card: cardsinfo) card.is_selected_rn = false;
            Oyuncu.SendCardToWar(kart3,3,cardsinfo,true);
            Thread.sleep(800);
            Oyuncu.SendCardToWar(isChosen.get(0),1,cardsinfo,false);
            Thread.sleep(400);
            Oyuncu.SendCardToWar(isChosen.get(1),2,cardsinfo,false);
            Thread.sleep(400);
            Oyuncu.SendCardToWar(isChosen.get(2),3,cardsinfo,false);
            System.out.println(kart1 + " " + kart2 + " " + kart3);

            System.out.println(kart1 +" "+kart2 +" "+kart3);

            Thread.sleep(3000);

            // JsonBitirici'nin torunu
            SaldiriHesapla(Player,bilgisayar,kart1,kart2,kart3,isChosen.get(0),isChosen.get(1),isChosen.get(2));

            // give them a break they did their best
            Oyuncu.SendCardBackHome(kart1,cardsinfo);
            Oyuncu.SendCardBackHome(kart2,cardsinfo);
            Oyuncu.SendCardBackHome(kart3,cardsinfo);

            // fucking kill those who havent tried hard enough
            FuckingKill(Player,bilgisayar);
            Player.SkorGoster();
            bilgisayar.SkorGoster();

            // below is where we humiliate the loser with extra cards
            if(Player.Playing_Cards.size() < 3 && !Player.Playing_Cards.isEmpty()) Player.ShuffleCards(3-Player.Playing_Cards.size());
            else Player.ShuffleCards(1);

            if(bilgisayar.Playing_Cards.size() < 3 && !bilgisayar.Playing_Cards.isEmpty()) bilgisayar.ShuffleCards(3-bilgisayar.Playing_Cards.size());
            else bilgisayar.ShuffleCards(1);


            // we kindly ask the universe to stop so we can sort shit out
            screen_refresh_timer.stop();

            Thread.sleep(100); // coconut.jpg, reality breaks if you remove this

            cardsinfo.clear();
            Player.CopyCards(cardsinfo);
            bilgisayar.CopyCards(cardsinfo);
            safecardsinfo.clear();
            safecardsinfo.addAll(cardsinfo);

            screen_refresh_timer.start();
            // universe starts back up, feelin refreshed


        }






    }

    //public static void MaintenanceTimeStop()

    // reloads and rotates the images according to their stats
    public static void ReloadImages(ArrayList<CardForGraphics> cardsinfo)
    {
        boolean lift_the_veil_player = true;
        for(CardForGraphics card : cardsinfo)
        {
            if(!card.in_battle_rn && !card.used_or_not && card.owners_id)
            {
                lift_the_veil_player = false;
                break;
            }
        }
        for (CardForGraphics card : cardsinfo)
        {
            card.image = GraphicalUserInterface.loadbimg(card.path);
            card.image = GraphicalUserInterface.rotate(card.rotation, card);
            if(card.used_or_not && !card.in_battle_rn && !lift_the_veil_player && card.owners_id) card.image = GraphicalUserInterface.ApplyTransparency(card, 0.5F);
        }
    }

    // simply enough, it forces the cards into a deck formation on both sides, ignores the cards that have been played this turn
    public static void OrderCardsIntoADeck(ArrayList<CardForGraphics> cardsinfo)
    {
        byte players_deck_size = 0;
        byte dealers_deck_size = 0;
        byte iforplayer = 0;
        byte ifordealer = 0;
        for (CardForGraphics card : cardsinfo)
        {
            if (card.owners_id && !card.in_battle_rn) players_deck_size++;
            else if (!card.owners_id && !card.in_battle_rn) dealers_deck_size++;
        }
        byte angle_increment_player = 90;
        byte angle_increment_dealer = 90;
        if(players_deck_size!=0) angle_increment_player = (byte)(90/players_deck_size);
        if(dealers_deck_size!=0) angle_increment_dealer = (byte)(90/dealers_deck_size);
        for (CardForGraphics card : cardsinfo)
        {
            if (card.owners_id && !card.in_battle_rn) {

                card.SetXPos((short) (663 - (30 * players_deck_size) + (iforplayer * 60)));
                card.SetYPos((short) (638 + (Math.pow(Math.abs(card.rotation / 3), 1.7))));
                card.SetRotation(-45 + ((iforplayer + 0.5) * angle_increment_player));
                //cardsinfo.get(i).AddToRotation(180);
                iforplayer++;
            } else if (!card.owners_id && !card.in_battle_rn) {

                card.SetXPos((short) (603 + (30 * dealers_deck_size) - (ifordealer * 60)));
                card.SetYPos((short) (50 - (Math.pow(Math.abs((180 - card.rotation) / 3), 1.7))));
                card.SetRotation(-45 + 180 + (ifordealer + 0.5) * angle_increment_dealer);
                //cardsinfo.get(i).AddToRotation(180);
                ifordealer++;
            }
        }
        for (CardForGraphics card : cardsinfo)
        {
            if(card.is_selected_rn) card.SetYPos((short)(card.y_pos-50));
        }
    }

    // bigass function
    public static void SaldiriHesapla(Oyuncu Player,Oyuncu bilgisayar,int kart1 ,int kart2 ,int kart3, int pckart1, int pckart2, int pckart3)
    {
        ArrayList<Integer> Cards;           ///Hangi sayilari seçtiğimi tutmak için liste
        ArrayList<Integer> CardsForComputer;        ///Üstteki ama bilgisayarlı versiyonu

        System.out.println("\nPlayer Playing_Cards tam liste: "+Player.Playing_Cards);

        System.out.println("\nPlayer Playing_Cards tam liste: "+bilgisayar.Playing_Cards);



        Cards = Player.kartSec(kart1,kart2,kart3);          ///Seçilen x,y,z kartlarini kart seç ile seç.
        CardsForComputer = bilgisayar.kartSec(pckart1, pckart2, pckart3);            ///Üsttekinin aynısı ama bilgisayar hali bilgisayar random atama yapıyor.

        System.out.println(Cards);
        System.out.println(CardsForComputer);


        for (int i = 0; i < 3; i++)
        {       /// Her kartin karşısandakiyle savaşması için 3 kere dönüyor (sanirim)(sanırım değil ama yazması komik).

            int damageForPlayer = Player.Playing_Cards.get(Cards.get(i)).vurus;
            int damageForComputer = bilgisayar.Playing_Cards.get(CardsForComputer.get(i)).vurus;

            Player.Playing_Cards.get(Cards.get(i)).is_used = true;
            bilgisayar.Playing_Cards.get(CardsForComputer.get(i)).is_used = true;

            System.out.println("Oyunucunun " + (i + 1) +". karti sudur : "+ Player.Playing_Cards.get(Cards.get(i)));
            Player.Playing_Cards.get(Cards.get(i)).Stat_Goster();
            System.out.println("Bilgisayarin " + (i+1) +". karti sudur : "+ bilgisayar.Playing_Cards.get(CardsForComputer.get(i)));
            bilgisayar.Playing_Cards.get(CardsForComputer.get(i)).Stat_Goster();
            System.out.println("asdasdasfasd :" + bilgisayar.Playing_Cards.get(CardsForComputer.get(i)).dayaniklilik);

            ///Damage for player

            if(Player.Playing_Cards.get(Cards.get(i)) instanceof Siha tempt && bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof DenizAraclari) damageForPlayer += (tempt).denizVurusAvantaji;
            else if(Player.Playing_Cards.get(Cards.get(i)) instanceof Siha tempt && bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof KaraAraclari) damageForPlayer += tempt.karaVurusAvantaji;
            else if(Player.Playing_Cards.get(Cards.get(i)) instanceof Ucak tempt && bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof KaraAraclari) damageForPlayer += tempt.karaVurusAvantaji;

            if(Player.Playing_Cards.get(Cards.get(i)) instanceof KFS tempt && bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof DenizAraclari) damageForPlayer += tempt.denizVurusAvantaji;
            else if (Player.Playing_Cards.get(Cards.get(i)) instanceof KFS tempt && bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof HavaAraclari) damageForPlayer += tempt.havaVurusAvantaji;
            else if(Player.Playing_Cards.get(Cards.get(i)) instanceof Obus tempt && bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof DenizAraclari) damageForPlayer += tempt.denizVurusAvantaji;

            if(Player.Playing_Cards.get(Cards.get(i)) instanceof Sida tempt && bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof KaraAraclari) damageForPlayer += tempt.karaVurusAvantaji;
            else if (Player.Playing_Cards.get(Cards.get(i)) instanceof Sida tempt && bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof HavaAraclari) damageForPlayer += tempt.havaVurusAvantaji;
            else if(Player.Playing_Cards.get(Cards.get(i)) instanceof Firkateyn tempt && bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof HavaAraclari) damageForPlayer += tempt.havaVurusAvantaji;

            ///Damage for computer

            if(bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof Siha tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof DenizAraclari) damageForComputer += (tempt).denizVurusAvantaji;
            else if(bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof Siha tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof KaraAraclari) damageForComputer += tempt.karaVurusAvantaji;
            else if(bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof Ucak tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof KaraAraclari) damageForComputer += tempt.karaVurusAvantaji;

            if(bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof KFS tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof DenizAraclari) damageForComputer += tempt.denizVurusAvantaji;
            else if (bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof KFS tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof HavaAraclari) damageForComputer += tempt.havaVurusAvantaji;
            else if(bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof Obus tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof DenizAraclari) damageForComputer += tempt.denizVurusAvantaji;

            if(bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof Sida tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof KaraAraclari) damageForComputer += tempt.karaVurusAvantaji;
            else if (bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof Sida tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof HavaAraclari) damageForComputer += tempt.havaVurusAvantaji;
            else if(bilgisayar.Playing_Cards.get(CardsForComputer.get(i)) instanceof Firkateyn tempt && Player.Playing_Cards.get(Cards.get(i)) instanceof HavaAraclari) damageForComputer += tempt.havaVurusAvantaji;


            int real_XP1 = Player.Playing_Cards.get(Cards.get(i)).seviyePuani;
            int real_XP = bilgisayar.Playing_Cards.get(CardsForComputer.get(i)).seviyePuani;

            System.out.println("Insanlarin dayanikliliği : " + Player.Playing_Cards.get(Cards.get(i)).dayaniklilik);
            System.out.println("Bilgisayarin dayanikliliği : " + bilgisayar.Playing_Cards.get(CardsForComputer.get(i)).dayaniklilik);

            bilgisayar.card_score += Player.Playing_Cards.get(Cards.get(i)).DurumGuncelle(damageForComputer,real_XP);        ///Xp ve hasar yolla
            Player.card_score += bilgisayar.Playing_Cards.get(CardsForComputer.get(i)).DurumGuncelle(damageForPlayer,real_XP1);         ///Xp ve hasar yolla


            System.out.println("\nOyuncu hasari sudur : " +damageForPlayer);
            System.out.println("Bilgisayar hasari sudur : " + damageForComputer);

            System.out.println("\nInsanlarin dayanikliliği : " + Player.Playing_Cards.get(Cards.get(i)).dayaniklilik);
            System.out.println("Bilgisayin dayanikliliği : " + bilgisayar.Playing_Cards.get(CardsForComputer.get(i)).dayaniklilik);
        }
    }

    // this one might be killing Im not too sure tho
    public static void FuckingKill(Oyuncu Player, Oyuncu bilgisayar)
    {
        for(byte i = (byte)(Player.Playing_Cards.size()-1); i >= 0 ;i--)
        {
            if (Player.Playing_Cards.get(i).dayaniklilik <= 0)
            {
                Player.Playing_Cards.remove(i);
                System.out.print("\nOyuncu karti öldü !\n");
            }

        }
        for(byte i = (byte)(bilgisayar.Playing_Cards.size()-1); i >= 0 ;i--)
        {
            if (bilgisayar.Playing_Cards.get(i).dayaniklilik <= 0)
            {
                bilgisayar.Playing_Cards.remove(i);
                System.out.print("\nBilgisayarin karti öldü !\n");
            }
        }
    }

    public static void GiveCardInfoAsText(ArrayList<String> stringass, CardForGraphics card, Oyuncu Player, Oyuncu Bilgisayar)
    {
        stringass.set(0, " Kart: "+card.machine_id);
        stringass.set(1, " Dayanıklılık: "+card.hp+"/"+card.maxhp);
        stringass.set(2, " Vuruş: "+card.atk);
        stringass.set(3, " Seviye puanı: "+card.xp);
        stringass.set(4, " Oyuncu Skor: "+Player.skor);
        stringass.set(5, " Bilgisayar Skor: "+Bilgisayar.skor);
    }

    // displays 6 card selection slots on map (yes its by hand Im not making a for loop with 3 elements)
    public static void MakeFramesAndMap(ArrayList<CardForGraphics> framesandmap)
    {
        framesandmap.add(new CardForGraphics((short)0, (short)0, (short)736, (short)490, (short)0, "Visual/wood.jpg"));
        framesandmap.getFirst().image = GraphicalUserInterface.scale(framesandmap.getFirst().image, 1366, 876);
        framesandmap.add(new CardForGraphics((short)30, (short)240, (short)400, (short)350, (short)0, "Visual/textbox.jpg"));
        framesandmap.add(new CardForGraphics((short)483, (short)433, (short)104, (short)154, (short)0, "Visual/frame.png"));
        framesandmap.add(new CardForGraphics((short)633, (short)433, (short)104, (short)154, (short)0, "Visual/frame.png"));
        framesandmap.add(new CardForGraphics((short)783, (short)433, (short)104, (short)154, (short)0, "Visual/frame.png"));
        framesandmap.add(new CardForGraphics((short)483, (short)233, (short)104, (short)154, (short)0, "Visual/frame.png"));
        framesandmap.add(new CardForGraphics((short)633, (short)233, (short)104, (short)154, (short)0, "Visual/frame.png"));
        framesandmap.add(new CardForGraphics((short)783, (short)233, (short)104, (short)154, (short)0, "Visual/frame.png"));

    }
}
