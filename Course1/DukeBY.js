// write your code here
// write your code here

function DukeBY(img){
    
    var img = new SimpleImage(img);
    
    // var width = img.getWidth();
    
    for (var pixel of img.values()){
        if (pixel.getRed() < 200){
            pixel.setRed(255);
            pixel.setGreen(255);
            pixel.setBlue(0);
        }
    }
    
    return (img);
}

print(DukeBY('duke_blue_devil.png'));
