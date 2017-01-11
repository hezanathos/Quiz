var base_time = 3000
var time = base_time ;
var interval ;

interval = setInterval(function(){
    tick();
    if (time <= 0){
        clearInterval(interval);
    }
}, 10);



function tick(){
    time--;
    var ratio = ((time/base_time)*100) ;
    var s = Math.floor(time/100);
    var m = Math.floor(((time/100) - s)*100);
    m = (m < 10) ? 0+""+m : m ;
    $("#secondes").html(s) ;
    $("#ms").html(m) ;
    $("#inner-bar").css("width", ((time/base_time)*100) + "%");
    var r = 255-((ratio*255)/100) ;
    var v = ((ratio*255)/100) ;
    $("#inner-bar").css("background-color", "rgb("+r+", "+v+", 128)");
}