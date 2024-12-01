abstract class KaraAraclari extends SavasAraclari
{
    public int denizVurusAvantaji;
    public String altSinif;
    public KaraAraclari(int seviyePuani, int dayaniklilik, int vurus, String sinif,String altSinif, int denizVurusAvantaji)
    {
        super(seviyePuani,dayaniklilik,vurus,sinif);
        this.denizVurusAvantaji = denizVurusAvantaji;
        this.altSinif = altSinif;
    }
    public void Stat_Goster()
    {
        super.Stat_Goster();
        System.out.println(denizVurusAvantaji);
    }
    public int KartPuaniGoster() { return 5;}
    public int DurumGuncelle(int damage,int xp)
    {
        dayaniklilik -= damage;
        if(dayaniklilik <= 0)
        {
            if(xp == 0) seviyePuani = 10;
            else seviyePuani += xp;
            return -1;
        }
        else return 1;
    }
}