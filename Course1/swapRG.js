// write your code here

function swapRG(img){
    
    var img = new SimpleImage(img);
    
    // var width = img.getWidth();
    
    for (var pixel of img.values()){
       x = pixel.getRed();
       y = pixel.getGreen();
       pixel.setRed(y);
       pixel.setGreen(x);
    }
    
    return (img);
}

print(swapRG('hilton.jpg'));