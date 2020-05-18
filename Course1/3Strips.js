// write your code here

function DreiStreifen(img){
    
    var img = new SimpleImage(img);
    
    var width = img.getWidth();
    
    for (var pixel of img.values()){
        if (pixel.getX() < width/3){
            pixel.setRed(255);
        }
        else if (pixel.getX() >= width/3 && pixel.getX() < 2*width/3){
            pixel.setGreen(255);
        }
        else {
            pixel.setBlue(255);
        }
    }
    
    return (img);
}

print(DreiStreifen('hilton.jpg'))