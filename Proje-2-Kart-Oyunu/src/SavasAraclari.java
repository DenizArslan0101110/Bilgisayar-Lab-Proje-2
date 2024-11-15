abstract class SavasAraclari
{
    public int dayaniklilik;
    public int seviyePuani;
    public int vurus;
    public String sinif;
    public boolean isUsed;

    public SavasAraclari(int seviyePuani,int dayaniklilik,int vurus,String sinif){
        this.dayaniklilik= dayaniklilik;
        this.seviyePuani = seviyePuani;
        this.vurus = vurus;
        this.sinif = sinif;
        this.isUsed = false;
    }
    public void Stat_Goster(){
        System.out.println(dayaniklilik);
        System.out.println(seviyePuani);
        System.out.println(vurus);
        System.out.println(sinif);
    }
    abstract public int KartPuaniGoster();
    abstract int DurumGuncelle(int damage,int xp);




}