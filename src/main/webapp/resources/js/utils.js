/**
 * JavaScript utils
 */

function updateClock() {
	var months = [ 'January', 'February', 'March', 'April', 'May', 'June',
			'July', 'August', 'September', 'October', 'November', 'December' ];

	var daysWeek = [ 'Sunday', 'Monday', 'Tuesday', 'Wednesday', 'Thursday',
			'Friday', 'Saturday' ];

	var currentTime = new Date();
	var currentYear = currentTime.getFullYear();
	var currentDate = currentTime.getDate();
	var currentDayWeek = daysWeek[currentTime.getDay()];
	var currentMonth = months[currentTime.getMonth()];
	var currentHours = currentTime.getHours();
	var currentMinutes = currentTime.getMinutes();
	var currentSeconds = currentTime.getSeconds();

	// Pad the minutes and seconds with leading zeros, if required
	currentMinutes = (currentMinutes < 10 ? "0" : "") + currentMinutes;
	currentSeconds = (currentSeconds < 10 ? "0" : "") + currentSeconds;

	// Choose either "AM" or "PM" as appropriate
	var timeOfDay = (currentHours < 12) ? "AM" : "PM";

	// Convert the hours component to 12-hour format if needed
	currentHours = (currentHours > 12) ? currentHours - 12 : currentHours;

	// Convert an hours component of "0" to "12"
	currentHours = (currentHours === 0) ? 12 : currentHours;

	// Compose the string for display
	var currentTimeString = currentDayWeek + ", " + currentMonth + " " + currentDate + " " + currentYear + " - " + currentHours
	+ ":" + currentMinutes + ":" + currentSeconds + " "
			+ timeOfDay;
	$("#time").html(currentTimeString);
}

$(document).ready(function() {
	setInterval('updateClock()', 100);
});