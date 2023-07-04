const path = require('path');
const HtmlWebpackPlugin = require('html-webpack-plugin');
const webpack = require('webpack');
const MiniCssExtractPlugin = require('mini-css-extract-plugin');
const { merge } = require('webpack-merge');
const Dotenv = require('dotenv-webpack');
const devServerConfig = require('./webpackDevServer.config');

module.exports = merge(devServerConfig, {
  entry: './src/index.js',
  output: {
    path: path.resolve(__dirname, 'build'),
    filename: '[fullhash:8].js',
    publicPath: '/',
  },
  module: {
    rules: [
      {
        test: /\.(js|jsx)$/,
        exclude: /node_modules/,
        use: 'babel-loader',
      },
      {
        test: /\.scss$/,
        use: ['style-loader', 'css-loader', 'sass-loader'],
      },
    ],
  },
  resolve: {
    extensions: ['.js', '.jsx'],
  },
  mode: 'development',
  plugins: [
    new HtmlWebpackPlugin({
      filename: 'index.html',
      template: 'public/index.template.html',
      //favicon: 'public/favicon.ico',
    }),
    new Dotenv(),
    new MiniCssExtractPlugin({ filename: '[fullhash:8].css' }),
    new webpack.LoaderOptionsPlugin({
      options: { presets: ['@babel/preset-env', '@babel/react'] },
    }),
  ],
});
