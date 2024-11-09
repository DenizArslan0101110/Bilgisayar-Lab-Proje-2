import java.awt.image.BufferedImage;

public class CardForGraphics
{
    short x_pos;
    short y_pos;
    short display_x_pos;
    short display_y_pos;
    short width;
    short height;
    double rotation;
    String path;
    BufferedImage image;

    CardForGraphics(short x_pos, short y_pos, short width, short height, double rotation, String path)
    {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.display_x_pos = x_pos;
        this.display_y_pos = y_pos;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.path = path;
        this.image = GraphicalUserInterface.loadbimg(this.path);
    }

    /*public void Set_W(short width)
    {
        this.width = width;
    }

    public void Set_H(short height)
    {
        this.height = height;
    }*/

    public void set_Image(BufferedImage image)
    {
        this.image = image;
    }

    public void AddToRotation(double increase)
    {
        this.rotation += increase;
        if(this.rotation >= 360) this.rotation-=360;
    }
}
