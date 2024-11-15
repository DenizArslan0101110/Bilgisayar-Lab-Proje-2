public class Ucak extends HavaAraclari
{
    public Ucak(int seviyePuani, int dayaniklilik, int vurus, String sinif, String altSinif ,int karaVurusAvantaji)
    {
        super(seviyePuani, dayaniklilik,vurus,sinif,altSinif,karaVurusAvantaji);
    }

    public void Stat_Goster() {
        super.Stat_Goster();
    }
    public int KartPuaniGoster(){return seviyePuani; }
    public int DurumGuncelle(int damage,int xp){
        dayaniklilik -= damage;
        if(dayaniklilik <= 0){
            if(xp == 0){
                seviyePuani = 10;
            }
            else
                seviyePuani += xp;
            return -1;
        }

        else
            return 1;
    }
}