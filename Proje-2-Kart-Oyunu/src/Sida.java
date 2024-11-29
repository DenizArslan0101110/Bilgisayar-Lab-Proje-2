public class Sida extends DenizAraclari
{
    public int karaVurusAvantaji;

    public Sida(int seviyePuani, int dayaniklilik, int vurus, String sinif,String altsinif,int havaVurusAvantaji, int karaVurusAvantaji){
        super(seviyePuani,dayaniklilik,vurus,sinif,altsinif,havaVurusAvantaji);
        this.karaVurusAvantaji = karaVurusAvantaji;
    }
    public void Stat_Goster() {
        System.out.println(karaVurusAvantaji);
        super.Stat_Goster();
    }
    public int KartPuaniGoster(){
        return seviyePuani;
    }
    public int DurumGuncelle(int damage,int xp){
        dayaniklilik -= damage;
        if(dayaniklilik <= 0){
            if(xp == 0){
                seviyePuani = 10;
            }
            else
                seviyePuani += xp;
            return seviyePuani;
        }

        else
            return 0;
    }
}