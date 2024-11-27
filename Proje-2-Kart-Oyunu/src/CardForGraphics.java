import java.awt.image.BufferedImage;

public class CardForGraphics
{
    static byte turn_number = 0;
    boolean owners_id, used_or_not = false, in_battle_rn = false;
    byte index_in_deck, hp;
    short x_pos, y_pos, display_x_pos, display_y_pos, width, height;
    double rotation;
    String path, machine_id;
    BufferedImage image;

    CardForGraphics(short x_pos, short y_pos, short width, short height, double rotation, String path, String machine_id, boolean owners_ID, byte index_in_deck, byte hp, boolean used_or_not, boolean in_battle_rn)
    {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.display_x_pos = x_pos;
        this.display_y_pos = y_pos;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.path = path;
        this.machine_id = machine_id;
        this.image = GraphicalUserInterface.loadbimg(this.path);
        this.owners_id = owners_ID;
        this.index_in_deck = index_in_deck;
        this.hp = hp;
        this.used_or_not = used_or_not;
        this.in_battle_rn = in_battle_rn;
    }

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
