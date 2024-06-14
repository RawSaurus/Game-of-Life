public class Coordinates {

    private int X;
    private int Y;

    Coordinates(){

    }

    Coordinates(int x, int y){
        this.X = x;
        this.Y = y;
    }

    public int getX(){
        return this.X;
    }

    public int getY(){
        return this.Y;
    }

    @Override
    public String toString(){
        return "X =" + X + " Y = " + Y;
    }

    public boolean contains(int x, int y){
        if(this.X == x && this.Y == y)
            return true;
        return false;
    }
}
