public class KFS extends KaraAraclari
{
    private int havaVurusAvantaji;

    public KFS(int seviyePuani,int dayaniklilik,int vurus,String sinif,String altsinif,int denizVurusAvantaji,int havaVurusAvantaji){
        super(seviyePuani, dayaniklilik,vurus,sinif,altsinif,denizVurusAvantaji);
        this.havaVurusAvantaji = havaVurusAvantaji;
    }
    public int KartPuaniGoster(){
        return 5;
    }
    public void DurumGuncelle(){
        System.out.println("sa1");
    }
}