public class Sida extends DenizAraclari
{
    public int karaVurusAvantaji;

    public Sida(int seviyePuani, int dayaniklilik, int vurus, String sinif,String altsinif,int havaVurusAvantaji, int karaVurusAvantaji){
        super(seviyePuani,dayaniklilik,vurus,sinif,altsinif,havaVurusAvantaji);
        this.karaVurusAvantaji = karaVurusAvantaji;

    }
}