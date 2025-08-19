/** @type {import('tailwindcss').Config} */
module.exports = {
  content: ["../src/**/*.{html,js}"],
  theme: {
    extend: {
      screens: {
        'xs': '480px',
      },
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
        '1.5xl': ['1.375rem', { lineHeight: '1.875rem' }], // 22px

        // '2.3xl': ['1.625rem', { lineHeight: '2.125rem' }], // 26px
        '2.6xl': ['1.75rem', { lineHeight: '2.125rem' }], // 28px

        '3.3xl': ['2rem', { lineHeight: '2.375rem' }], // 32px
        // '3.6xl': ['2.125rem', { lineHeight: '2.375rem' }], // 34px
      },
      colors: {
        "menu-primary": "#d4cdc4",
        "btn-primary": "#817C5F",
        "btn-secondary": "#9f9977",
        "footer-primary": "#dfdad3",
      },
      height: {
        "banner": "calc(80vh - 3rem - 8rem)", // 80% viewport height - ribbon height (min 3rem) - menu height (min 8rem)
      }
    },
  },
  plugins: [],
}

