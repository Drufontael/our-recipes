import type { Config } from "tailwindcss";

export default {
  content: [
    "./src/pages/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/components/**/*.{js,ts,jsx,tsx,mdx}",
    "./src/app/**/*.{js,ts,jsx,tsx,mdx}",
  ],
  theme: {
    extend: {
      colors: {
        background: "var(--background)",
        foreground: "var(--foreground)",
        orange: {
          500: '#FF7F50', 
          600: '#FF6347', // Tomato
        },
        green: {
          500: '#6B8E23', // Olive Drab
          600: '#556B2F', // Dark Olive Green
        },
        brown: {
          300: '#D2B48C', // Tan
          400: '#A0522D', // Sienna
          500: '#8B4513',
          600: '#431407',
          700: '#450a0a',
        },
        cream: '#FFF8DC', // Cornsilk
        beige: '#F5DEB3', // Wheat
      },
      fontFamily: {
        title: ['"Playfair Display"', 'serif'],
        body: ['Roboto', 'sans-serif'],
      },
    },
  },
  plugins: [],
} satisfies Config;
