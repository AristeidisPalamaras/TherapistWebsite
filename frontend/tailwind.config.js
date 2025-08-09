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
        '1.5sm': ['0.9375rem', { lineHeight: '1.375rem' }], // 15px -- footer
        
        '1.5xl': ['1.375rem', { lineHeight: '1.875rem' }], // 22px

        '2.3xl': ['1.625rem', { lineHeight: '2.125rem' }], // 26px
        '2.6xl': ['1.75rem', { lineHeight: '2.125rem' }], // 28px

        '3.3xl': ['2rem', { lineHeight: '2.375rem' }], // 32px
        '3.6xl': ['2.125rem', { lineHeight: '2.375rem' }], // 34px

        '4.2xl': ['2.375rem', { lineHeight: '2.625rem' }], // 38px
      },
      colors: {
        "menu-primary": "#D0C9C0",
        "footer-primary": "#beb4a7",
        "btn-primary": "#817C5F",
        "btn-secondary": "#9f9977",
      },
      height: {
        "banner": "calc(80vh - 3rem - 8rem)", // % viewport height - ribbon height (min 3rem) - menu height (min 8rem)
        "banner-full": "calc(100vh - 3rem - 8rem)", // viewport height - ribbon height (min 3rem) - menu height (min 8rem)
      }
    },
  },
  plugins: [],
}

