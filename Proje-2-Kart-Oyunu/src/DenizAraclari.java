abstract class DenizAraclari extends SavasAraclari
{
    public int havaVurusAvantaji;
    public String altSinif;

    public DenizAraclari(int seviyePuani,int dayaniklilik,int vurus,String sinif,String altSinif,int havaVurusAvantaji){
        super(seviyePuani,dayaniklilik,vurus,sinif);
        this.havaVurusAvantaji = havaVurusAvantaji;
        this.altSinif = altSinif;
    }

    public int KartPuaniGoster(){
        return 5;
    }
    public void DurumGuncelle(){
        System.out.println("sa1");
    }
}