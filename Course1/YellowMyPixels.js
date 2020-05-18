// write your code here

img = new SimpleImage(200,200);

for (var pixel of img.values()) {
    pixel.setRed(255);
    pixel.setBlue(0);
    pixel.setGreen(255);
}

print(img);
