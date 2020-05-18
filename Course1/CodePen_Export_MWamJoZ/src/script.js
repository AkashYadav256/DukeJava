function change() {
  alert('It was clicked');
}

function ask() {
  var ans = confirm('It was clicked');
  if (ans) {
    alert('You pressed OK');
  }
  else{
    confirm('Are you sure you want to exit?');
  }
}

function ccolor() {
  var x = document.getElementById("d1");
  var y = document.getElementById("d2");
  
  x.className = "blackback";
  y.className = "redback";
}

function swap() {
  var x = document.getElementById("d1");
  var y = document.getElementById("d2");
  
  var t1 = x.innerHTML;
  
  x.innerHTML = y.innerHTML;
  y.innerHTML = t1;
}