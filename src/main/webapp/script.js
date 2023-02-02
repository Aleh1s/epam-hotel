const errorMessage = document.getElementById("error-message");
const errorContainer = document.getElementById("error-container");
const dateOfEntry = document.getElementById("date-of-entry")
const dateOfLeaving = document.getElementById("date-of-leaving")

function onErrorMessagePresent() {
    if (errorMessage.innerText) {
        errorContainer.classList.add("active");
    }
}

function setMinForDatePickers() {
    let currDate = new Date();
    dateOfEntry.min = currDate.toISOString().split("T")[0];
    currDate.setDate(currDate.getDate() + 1);
    dateOfLeaving.min = currDate.toISOString().split("T")[0];
}

setMinForDatePickers();
onErrorMessagePresent();
