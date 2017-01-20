/*
 * VARIABLES
 */

/* Base time for the timer */
var base_time = 3000

/* Set the value of the timer */
var time = base_time;

/* Object for the interval */
var interval;

/*
 * FUNCTIONS
 */

/* Launches the timer */
function start() {
    console.log('start the timer');
    interval = setInterval(function () {
        tick();
        if (time <= 0) {
            clearInterval(interval);
        }
    }, 10);

}

/* Stops the timer */
function stop() {
    clearInterval(interval);
}

/* Initializes the timer to the base time */
function init() {
    console.log('init the timer');
    time = base_time;
}

/* Handles every iteration of the timer */
function tick() {
    time--;

    var ratio = ((time / base_time) * 100);
    var s = Math.floor(time / 100);
    var m = Math.floor(((time / 100) - s) * 100);

    m = (m < 10) ? 0 + "" + m : m;

    $("#secondes").html(s);
    $("#ms").html(m);
    $("#inner-bar").css("width", ((time / base_time) * 100) + "%");

    var r = 255 - ((ratio * 255) / 100);
    var v = ((ratio * 255) / 100);

    $("#inner-bar").css("background-color", "rgb(" + r + ", " + v + ", 128)");
}

init();
start();