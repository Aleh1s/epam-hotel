const fileInput = document.getElementById('imagePicker');
const imagePreview = document.getElementById('imagePreview');

fileInput.addEventListener('change', (event) => {
    const file = event.target.files[0];

    if (file) {
        const reader = new FileReader();

        reader.readAsDataURL(file);
        reader.addEventListener('load', (event) => {
            const imageUrl = event.target.result;
            const image = new Image();

            image.src = imageUrl;
            imagePreview.innerHTML = '';
            imagePreview.appendChild(image);
        });
    }
});

const trimOnSubmit = () => {
    const inputs = document.querySelectorAll('input[type="text"], textarea');
    for (let i = 0; i < inputs.length; i++) {
        const input = inputs[i];
        input.value = input.value.trim();
    }
}
