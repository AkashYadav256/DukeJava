function ccolor(){
  var x = document.getElementById("d1");
  x.style.backgroundColor = "blue";
}

function chcolor(){
  var x = document.getElementById("d1");
  var y = document.getElementById("col");
  var color = y.value;
  x.style.backgroundColor = color;
}

function onsld() {
  var x = document.getElementById("d1");
  var y = document.getElementById("sld");
  var sid = y.value;
  var ctx = x.getContext("2d");
  ctx.clearRect(0,0,x.width,x.height);
  ctx.fillStyle = "yellow";
  ctx.fillRect(10,10,sid,sid);
  ctx.fillRect(20+parseInt(sid),10,sid,sid);
}