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

    CardForGraphics(short x_pos, short y_pos, short width, short height, double rotation, String path)
    {
        this.x_pos = x_pos;
        this.y_pos = y_pos;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
        this.path = path;
    }

    public void AddToRotation(double increase)
    {
        this.rotation += increase;
        if(this.rotation >= 360) this.rotation-=360;
    }
}
