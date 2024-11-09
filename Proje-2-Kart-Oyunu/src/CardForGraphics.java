import java.awt.image.BufferedImage;

public class CardForGraphics
{
    boolean owners_ID, used_or_not = false;
    short x_pos, y_pos, display_x_pos, display_y_pos, width, height;
    double rotation;
    String path;
    BufferedImage image;

    CardForGraphics(short x_pos, short y_pos, short width, short height, double rotation, String path, boolean owners_ID)
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
        this.owners_ID = owners_ID;
    }

    public void SetXPos(short x_pos)
    {
        this.x_pos = x_pos;
    }

    public void SetYPos(short y_pos)
    {
        this.y_pos = y_pos;
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

    public void SetRotation(double value)
    {
        this.rotation = value%360;
    }
}
