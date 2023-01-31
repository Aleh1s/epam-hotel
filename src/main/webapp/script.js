const errorMessage = document.getElementById("error-message");
const errorContainer = document.getElementById("error-container");

function onErrorMessagePresent() {
    if (errorMessage.innerText) {
        errorContainer.classList.add("active");
    }
}

onErrorMessagePresent();