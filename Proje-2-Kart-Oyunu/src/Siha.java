public class Siha extends HavaAraclari
{
    private int denizVurusAvantaji;

    public Siha(int seviyePuani,int dayaniklilik,int vurus,String sinif,String altsinif,int karaVurusAvantaji,int denizVurusAvantaji){
        super(seviyePuani, dayaniklilik,vurus,sinif,altsinif,karaVurusAvantaji);
        this.denizVurusAvantaji = denizVurusAvantaji;
    }



    public void Stat_Goster() {
        System.out.println(denizVurusAvantaji);
        super.Stat_Goster();
    }

    public int KartPuaniGoster(){
        return 5;
    }
    public void DurumGuncelle(){
        System.out.println("sa1");
    }
}