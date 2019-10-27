This project calls NASA's [Mars Rover Photos API](https://api.nasa.gov/) and selects a random photo taken by the Mars Rover [Opportunity](https://en.wikipedia.org/wiki/Opportunity_(rover)) on a given date.

The user may choose to download the photos locally or display them in the browser in separate tabs (or both).

> Note: All photos are saved in the `\Downloads` folder.

The application downloads photos taken by Opportunity's panoramic camera (PANCAM).

---

## How to Run the Application
1. Clone the nasaApiExercise repo to a local directory
2. Run the commands `java -jar nasa/target/nasa-0.0.1-SNAPSHOT.jar`
3. Go to `http://localhost:8090/photo`

Make sure to save an `imageDates.txt` file in the `/Downloads` folder with a list of dates before starting the application.

All dates should be in the following formats:
* __02/27/17__
* __June 2, 2018__
* __Jul-13-2016__
* __April 31, 2018__
 
With each date listed on a new line.

The application runs on port __8090__ with logging level set to `DEBUG`.

---

## API

```/photo/download``` _only_ download photos without showing them in the browser.

```/photo/display``` _only_ show photos in the browser without downloading them.

---

## Limitations

* The application has only been tested on Macs and not on Windows or Linux, so it might not show the photos in the browser correctly on those operating systems. This is because I don't have a Windows machine handy.

* If there're no photos for a particular date, then the application returns status 404 with an error message. There may be more graceful ways of handling exceptions.

* A frontend client is ostensibly missing and the tests are incomplete, mostly due to time constraints :p