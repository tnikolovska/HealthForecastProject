
const email = document.getElementById('email');

const existedUsername = document.getElementById('hidden');



form.addEventListener('submit', e => {
    e.preventDefault();

    validateInputs();
});

const setError = (element, message) => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = message;
    inputControl.classList.add('error');
    inputControl.classList.remove('success')
}

const setSuccess = element => {
    const inputControl = element.parentElement;
    const errorDisplay = inputControl.querySelector('.error');

    errorDisplay.innerText = '';
    inputControl.classList.add('success');
    inputControl.classList.remove('error');
};

const isValidEmail = () => {
    return existedUsername==null;
}

const validateInputs = () => {
    if (isValidEmail(existedUsername.value.trim())) {
        setError(email, 'Email already exists');
    } else {
        setSuccess(email);
    }

};
