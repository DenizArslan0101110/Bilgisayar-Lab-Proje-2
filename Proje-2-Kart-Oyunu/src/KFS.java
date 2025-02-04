public class KFS extends KaraAraclari
{
    public int havaVurusAvantaji;
    public KFS(int seviyePuani,int dayaniklilik,int vurus,String sinif,String altsinif,int denizVurusAvantaji,int havaVurusAvantaji)
    {
        super(seviyePuani, dayaniklilik,vurus,sinif,altsinif,denizVurusAvantaji);
        this.havaVurusAvantaji = havaVurusAvantaji;
    }
    public void Stat_Goster()
    {
        System.out.println(havaVurusAvantaji);
        super.Stat_Goster();
    }
    public int KartPuaniGoster() { return seviyePuani; }
    public int DurumGuncelle(int damage,int xp)
    {
        dayaniklilik -= damage;
        if(dayaniklilik <= 0)
        {
            if(xp == 0)
            {
                seviyePuani = 10;
                xp = 10;
            }
            else seviyePuani += xp;
            return xp;
        }
        else return 0;
    }
}