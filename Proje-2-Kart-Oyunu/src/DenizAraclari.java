abstract class DenizAraclari extends SavasAraclari
{
    public int havaVurusAvantaji;
    public String altSinif;

    public DenizAraclari(int seviyePuani,int dayaniklilik,int vurus,String sinif,String altSinif,int havaVurusAvantaji){
        super(seviyePuani,dayaniklilik,vurus,sinif);
        this.havaVurusAvantaji = havaVurusAvantaji;
        this.altSinif = altSinif;
    }

    public void Stat_Goster() {
        super.Stat_Goster();
        System.out.println(havaVurusAvantaji);
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
            return -1;
        }

        else
            return 1;
    }
}