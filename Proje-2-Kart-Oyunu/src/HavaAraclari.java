abstract class HavaAraclari extends SavasAraclari
{
    public int karaVurusAvantaji;
    public String altSinif;

    public HavaAraclari(int seviyePuani,int dayaniklilik,int vurus,String sinif,String altSinif,int karaVurusAvantaji){
        super(seviyePuani,dayaniklilik,vurus,sinif);
        this.karaVurusAvantaji = karaVurusAvantaji;
        this.altSinif = altSinif;
    }



    public void Stat_Goster() {
        super.Stat_Goster();
        System.out.println(karaVurusAvantaji);
    }

    public int KartPuaniGoster(){
        return 5;
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