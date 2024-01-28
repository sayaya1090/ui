import terser from '@rollup/plugin-terser';
import resolve from '@rollup/plugin-node-resolve';

export default {
    input: 'index.js',
    output: {
        file: 'src/test/webapp/js/bundle.js',
        format: 'cjs'
    },
    plugins: [
        resolve(),
        terser()
    ]
};