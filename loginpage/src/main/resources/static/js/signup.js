// signup.js

document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    const nameInput = document.getElementById('name');
    const emailInput = document.getElementById('email');
    const passwordInput = document.getElementById('password');
    const confirmPasswordInput = document.getElementById('confirm-password');

    form.addEventListener('submit', (event) => {
        let isValid = true;

        // Clear previous error messages
        clearErrors();

        // Validate Name
        if (nameInput.value.trim() === '') {
            showError(nameInput, 'Name is required.');
            isValid = false;
        }

        // Validate Email
        if (!validateEmail(emailInput.value.trim())) {
            showError(emailInput, 'Please enter a valid email address.');
            isValid = false;
        }

        // Validate Password
        if (passwordInput.value.length < 6) {
            showError(passwordInput, 'Password must be at least 6 characters long.');
            isValid = false;
        }

        // Validate Confirm Password
        if (passwordInput.value !== confirmPasswordInput.value) {
            showError(confirmPasswordInput, 'Passwords do not match.');
            isValid = false;
        }
		console.log("validated");

        
        
        if (passwordInput === confirmPasswordInput ){
		const data = {
			nameInput, emailInput, passwordInput,confirmPasswordInput
		}
		
		console.log("validated");
		const jsonData = JSON.stringify(data);
		fetch('/req/signup', {
			method:'POST',
			header:{
				'Content-Type': 'application/json'
			},
			body: jsonData,
			
			
		}).then(response => {
			console.log(response);
		});
		 
		
			
		}
        
    });

    function showError(input, message) {
        const errorElement = document.createElement('div');
        errorElement.classList.add('error-message');
        errorElement.textContent = message;
        input.parentElement.appendChild(errorElement);
    }

    function clearErrors() {
        document.querySelectorAll('.error-message').forEach(error => {
            error.remove();
        });
    }

    function validateEmail(email) {
        const re = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,6}$/;
        return re.test(email);
    }
});
