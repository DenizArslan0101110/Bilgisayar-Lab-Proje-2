abstract class KaraAraclari extends SavasAraclari
{
    public int denizVurusAvantaji;
    public String altSinif;

    public KaraAraclari(int seviyePuani, int dayaniklilik, int vurus, String sinif,String altSinif, int denizVurusAvantaji){
        super(seviyePuani,dayaniklilik,vurus,sinif);
        this.denizVurusAvantaji = denizVurusAvantaji;
        this.altSinif = altSinif;
    }

    public int KartPuaniGoster(){
        return 5;
    }
    public void DurumGuncelle(){
        System.out.println("sa1");
    }
}