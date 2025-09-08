document.addEventListener("DOMContentLoaded", function () {
  const form = document.getElementById("contactForm");
  const nameInput = document.getElementById("name");
  const nameError = document.getElementById("nameError");
  const emailInput = document.getElementById("email");
  const emailError = document.getElementById("emailError");
  const telephoneInput = document.getElementById("telephone");
  const telephoneError = document.getElementById("telephoneError");
  const messageInput = document.getElementById("message");
  const messageError = document.getElementById("messageError");
  const warning = document.getElementById("warning");
  const summary = document.getElementById("summary");

  const lang = window.location.pathname.startsWith("/en") ? "en" : "gr";

  const text = {
    required: {
      en: "This field is required.",
      gr: "Αυτό το πεδίο είναι υποχρεωτικό."
    },
    invalidEmail: {
      en: "Please enter a valid email address.",
      gr: "Παρακαλώ δώστε μια έγγυρη διεύθυνση email."
    },
    invalidTelephone: {
      en: "Please enter a valid telephone number.",
      gr: "Παρακαλώ δώστε έναν έγγυρο αριθμό τηλεφώνου."
    },
    warning: {
      en: "One or more fields have an error. Please check and try again.",
      gr: "Ένα ή περισσότερα πεδία έχουν σφάλματα. Ελέγξτε και δοκιμάστε ξανά."
    }
  };

  // On load
  if (document.querySelector(".backend-msg:not(:empty)")) {
    setTimeout(() => {
      summary.scrollIntoView({ behavior: "smooth", block: "center" });
    }, 250);
  }

  // On submit
  form.addEventListener("submit", (e) => {

    document.querySelectorAll(".msg").forEach(el => {
      el.textContent = "";
      el.style.display = "none";
    });

    let valid = true;

    // TEST
    if (nameInput.value.trim() === "SKIP") {startSpinner(); return};

    validateRequired(nameInput, nameError);
    validateRequired(emailInput, emailError);
    validateEmail(emailInput, emailError);
    validateTelephone(telephoneInput, telephoneError);
    validateRequired(messageInput, messageError);

    if (!valid) {
      e.preventDefault(); 

      warning.textContent = text.warning[lang];
      warning.style.display = "block";
      summary.scrollIntoView({ behavior: "smooth", block: "center" });
      return;
    }

    startSpinner();


    function validateRequired(input, error) {
      if (!input.value.trim()) {
        error.textContent = text.required[lang];
        error.style.display = "block";
        valid = false;
      }
    }
    
    function validateEmail(input, error) {
      const emailRegex = /^[^@\s]+@[^@\s]+\.[^@\s]+$/;
      if (input.value.trim() && !emailRegex.test(input.value)) {
        error.textContent = text.invalidEmail[lang];
        error.style.display = "block";
        valid = false;
      }
    }

    function validateTelephone(input, error) {
      const phoneRegex = /^[0-9+\-()\s]*$/;
      if (input.value.trim() && !phoneRegex.test(input.value)) {
        error.textContent = text.invalidTelephone[lang];
        error.style.display = "block";
        valid = false;
      }
    }

    function startSpinner() {
      const spinner = document.getElementById("spinner");
      const button = form.querySelector("button[type=submit]");

      spinner.style.display = "inline-block";
      button.disabled = true;
    }
  })
});
