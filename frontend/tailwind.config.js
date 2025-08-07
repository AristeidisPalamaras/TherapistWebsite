/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["../src/**/*.{html,js}"],
  theme: {
    extend: {
      fontFamily: {
        sans: [ 
          'Manrope',
          'system-ui',
          '-apple-system',
          'BlinkMacSystemFont',
          '"Segoe UI"',
          'Roboto',
          '"Helvetica Neue"',
          'sans-serif',
        ],
      },
      fontSize: {
        '1.5sm': ['0.9375rem', { lineHeight: '1.375rem' }], // 15px
        '1.5xl': ['1.375rem', { lineHeight: '2rem' }], // 22px
        '3.5xl': ['2rem', { lineHeight: '2.5rem' }], // 32px
      },
      colors: {
        "menu-primary": "#D0C9C0",
        "footer-primary": "#beb4a7",
        "btn-primary": "#817C5F",
        "btn-secondary": "#9f9977",
      },
      height: {
        "ribbon": "3rem",
        "menu": "8rem",
        "banner": "calc(80vh - 3rem - 8rem)", // % viewport height - ribbon height - menu height 
        "banner-full": "calc(100vh - 3rem - 8rem)", // viewport height - ribbon height - menu height 
      }
    },
  },
  plugins: [],
}

