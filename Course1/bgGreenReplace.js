// write your code here

var fImage = new SimpleImage('drewRobert.png');
var bImage = new SimpleImage('dinos.png');
var op = new SimpleImage(fImage.getWidth(), fImage.getHeight());

for (var pixel of fImage.values()) {
    x = pixel.getX();
    y = pixel.getY();
    if (pixel.getGreen() < pixel.getBlue() + pixel.getRed()) {
        op.setPixel(x, y, pixel);
    }
    else {
        op.setPixel(x, y, bImage.getPixel(x, y));
    }
}

print(op);