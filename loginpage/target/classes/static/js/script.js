document.addEventListener('DOMContentLoaded', () => {
    const form = document.querySelector('form');
    const usernameInput = document.getElementById('username');
    const passwordInput = document.getElementById('password');
    const emailError = document.getElementById('email-error');
    const passwordError = document.getElementById('password-error');

    form.addEventListener('submit', (event) => {
        let valid = true;

        // Reset error messages
        emailError.textContent = '';
        passwordError.textContent = '';

        // Email validation
        const usernameValue = usernameInput.value.trim();
        

        // Password validation
        const passwordValue = passwordInput.value.trim();
        if (passwordValue.length < 6) {
            passwordError.textContent = 'Password must be at least 6 characters long.';
            valid = false;
        }

        // Prevent form submission if validation fails
        if (!valid) {
            event.preventDefault();
        }
    });

    function validateEmail(email) {
        // Basic email pattern
        const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return emailPattern.test(email);
    }
});
