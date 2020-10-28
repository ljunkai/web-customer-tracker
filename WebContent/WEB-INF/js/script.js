var coll = document.getElementsByClassName("collapsible");
var i;

for (i = 0; i < coll.length; i++) {
	console.log("clicked");
  coll[i].addEventListener("click", function() {
    this.classList.toggle("active");
    var content = this.nextElementSibling;
    if (content.style.display === "block") {
    	console.log("none");
      content.style.display = "none";
    } else {
    	console.log("block");
      content.style.display = "block";
    }
  });
}