public class Obus extends KaraAraclari
{
    public Obus(int seviyePuani,int dayaniklilik,int vurus,String sinif,String altSinif,int denizVurusAvantaji)
    {
        super(seviyePuani,dayaniklilik,vurus,sinif,altSinif,denizVurusAvantaji);
    }
    public void Stat_Goster() {
        super.Stat_Goster();
    }
    public int KartPuaniGoster(){
        return seviyePuani;
    }
    public int DurumGuncelle(int damage,int xp){
        dayaniklilik -= damage;
        if(dayaniklilik <= 0)
        {
            if(seviyePuani + xp <= 10)seviyePuani = 10;
            else seviyePuani += xp;
            return seviyePuani;
        }
        else return 0;
    }
}