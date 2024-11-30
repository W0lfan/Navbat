package BatailleNavale.utils.errors;

public class ShipPilingUp extends Exception {
    public ShipPilingUp(int width, int height, int x, int y) {
        super(String.format("Ship of size %dx%d positioned at (%d,%d) piles up with another ship.",width,height,x,y));
    }
}
