public class Siha extends HavaAraclari
{
    public int denizVurusAvantaji;
    public Siha(int seviyePuani,int dayaniklilik,int vurus,String sinif,String altsinif,int karaVurusAvantaji,int denizVurusAvantaji)
    {
        super(seviyePuani, dayaniklilik,vurus,sinif,altsinif,karaVurusAvantaji);
        this.denizVurusAvantaji = denizVurusAvantaji;
    }
    public void Stat_Goster()
    {
        System.out.println(denizVurusAvantaji);
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