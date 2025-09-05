document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("contactForm");
  const nameInput = document.getElementById("name");
  const nameError = document.getElementById("nameError");
  const emailInput = document.getElementById("email");
  const emailError = document.getElementById("emailError");
  const messageInput = document.getElementById("message");
  const messageError = document.getElementById("messageError");
  const warning = document.getElementById("warning");

  const lang = window.location.pathname.startsWith("/en") ? "en" : "gr";

  const messages = {
    required: {
      en: "This field is required.",
      gr: "Αυτό το πεδίο είναι υποχρεωτικό."
    },
    invalidEmail: {
      en: "Please enter a valid email address.",
      gr: "Παρακαλώ δώστε μια έγγυρη διεύθυνση email."
    },
    warning: {
      en: "One or more fields have an error. Please check and try again.",
      gr: "Ένα ή περισσότερα πεδία έχουν σφάλματα. Ελέγξτε και δοκιμάστε ξανά."
    }
  };

  form.addEventListener("submit", (e) => {

    let valid = true;
    
    [nameError, emailError, messageError].forEach(el => {
        if (el) el.textContent = "";
    });

    if (warning) warning.textContent = "";

    validateRequired(nameInput, nameError);
    validateRequired(emailInput, emailError);
    validateEmail(emailInput, emailError)
    validateRequired(messageInput, messageError);

    if (!valid) {
      e.preventDefault(); 

      if (warning) {
        warning.textContent = messages.warning[lang];
        warning.scrollIntoView({ behavior: "smooth", block: "center" });
      }
    }

    function validateRequired(input, error) {
      if (!input || !error) return;
      if (!input.value.trim()) {
        error.textContent = messages.required[lang];
        valid = false;
      }
    }
    
    function validateEmail(input, error) {
      if (!input || !error) return;
      if (input.value.trim() && !/^[^@\s]+@[^@\s]+\.[^@\s]+$/.test(input.value)) {
        error.textContent = messages.invalidEmail[lang];
        valid = false;
      }
    }
  })
});
